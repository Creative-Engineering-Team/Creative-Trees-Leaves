package com.xiaoliua.ctl;

import com.mojang.logging.LogUtils;
import com.xiaoliua.ctl.Blocks.BlockEntityInit;
import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.Items.ItemInit;
import mekanism.api.MekanismAPI;
import mekanism.api.event.MekanismTeleportEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractBannerBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.stringtemplate.v4.ST;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.regex.Pattern;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ctl.MODID)
public class ctl
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "ctl";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "ctl" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    // Creates a creative tab with the id "ctl:ctl_tab" for the ctl item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("ctl_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemInit.LEAF.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemInit.LEAF.get());// Add the ctl item to the tab. For your own tabs, this method is preferred over the event
                output.accept(ItemInit.DRY_LEAF.get());
                output.accept(ItemInit.FIBRE.get());
                output.accept(ItemInit.ROPE.get());
                output.accept(BlockInit.HAYRACK_BLOCK.get());
                output.accept(ItemInit.UNFIRED_CLAY_AXE.get());
                output.accept(ItemInit.UNFIRED_CLAY_HOE.get());
                output.accept(ItemInit.UNFIRED_CLAY_SWORD.get());
                output.accept(ItemInit.UNFIRED_CLAY_SHOVEL.get());
                output.accept(ItemInit.UNFIRED_CLAY_PICKAXE.get());
                output.accept(ItemInit.SIMPLE_CRAFTING_TABLE_BLOCK.get());
                output.accept(ItemInit.PLIABLE_BRANCH.get());
                output.accept(ItemInit.DRY_PLIABLE_BRANCH.get());
            })
            .title(Component.literal("Creative Trees & Leaves")).build());
    //Create a List to match the blocks
    public static final List<String> useToolBlocksNames = new ArrayList<>();
    public ctl()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for mod loading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        //BLOCKS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        //ITEMS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        BlockEntityInit.BLOCK_ENTITY_TYPES.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        //ContainerInit.CONTAINERS.register(modEventBus);
        //ctlRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOW,(PlayerEvent.BreakSpeed event) -> event.setNewSpeed(modifyBreakSpeed(
                event.getEntity(),event.getState(),event.getPosition().orElse(null),event.getNewSpeed())));

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
    }

    public static float modifyBreakSpeed(Player player, BlockState state, @Nullable BlockPos pos, float speed)
    {
        //if (player.getName().equals(Component.literal("Dev")))return 114514;
        return isUsingCorrectToolToMine(state, pos, player) ? speed : 0;
    }

    public static boolean isUsingCorrectToolToMine(BlockState state, @Nullable BlockPos pos, Player player)
    {
        return isUsingCorrectTool(state, pos, player,  true);
    }

    public static boolean needTool(Block block,BlockState state){
        if (state.is(TagsInit.Blocks.useTool)||state.is(TagsInit.Blocks.ae2BlockMachines)||
                state.is(TagsInit.Blocks.createBlockMachines)||state.is(TagsInit.Blocks.IEMachines)||
                state.is(TagsInit.Blocks.MEKMachines)){
            return true;
        }
        for (String useToolBlocksName : useToolBlocksNames) {
            if (Pattern.matches(useToolBlocksName, block.getDescriptionId())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUsingCorrectTool(BlockState state, @Nullable BlockPos pos, Player player,
                                              boolean checkingCanMine) {
        if (player.getName().equals(Component.literal("Dev")))
            player.sendSystemMessage(Component.literal(state.getBlock().getDescriptionId()));
        if (!Config.useMineAble)return true;
        if (state.is(TagsInit.Blocks.notTool)){
            return true;
        }
        if (needTool(state.getBlock(),state) && (player.getMainHandItem().isCorrectToolForDrops(state))){
            return true;
        }
        if (needTool(state.getBlock(),state) && (player.getMainHandItem().getDestroySpeed(state)>1.0f)){
            return true;
        }
        return !needTool(state.getBlock(), state);
    }

    // Add the ctl block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            //MenuScreens.register(ContainerInit.CUSTOM_WORKBENCH.get(), simpleCraftingTableScreen::new);
        }
    }

    static {
        useToolBlocksNames.add("block\\.mekanism\\..*_factory");
        useToolBlocksNames.add("block\\.mekanism\\.chemical.*");
        useToolBlocksNames.add("block\\.mekanism\\..*_fluid_tank");
        useToolBlocksNames.add("block\\.mekanism\\..*_energy_cube");
        useToolBlocksNames.add("block\\.mekanism\\..*_universal_cable");
        useToolBlocksNames.add("block\\.mekanism\\..*_mechanical_pipe");
        useToolBlocksNames.add("block\\.mekanism\\..*_pressurized_tube");
        useToolBlocksNames.add("block\\.mekanism\\..*_logistical_transporter");
        useToolBlocksNames.add("block\\.mekanism\\..*_thermodynamic_conductor");
        useToolBlocksNames.add("block\\.mekanism\\..*_chemical_tank");
        useToolBlocksNames.add("block\\.mekanism\\..*_transporter");
        useToolBlocksNames.add("block\\.mekanism\\.laser.*");
        useToolBlocksNames.add("block\\.mekanism\\.qio.*");
        useToolBlocksNames.add("block\\.mekanism\\..*_glow_panel");
        useToolBlocksNames.add("block\\.mekanism\\..*_glow");
        useToolBlocksNames.add("block\\.immersiveengineering\\.capacitor_.*");
        useToolBlocksNames.add("block\\.immersiveengineering\\.conveyor_.*");
        useToolBlocksNames.add("block\\.immersiveengineering\\.fluid_.*");
        useToolBlocksNames.add("block\\.immersiveengineering\\..*sorter");
        useToolBlocksNames.add("block\\.immersiveengineering\\.turret.*");
        useToolBlocksNames.add("block\\.immersiveengineering\\.coil_.*");
        useToolBlocksNames.add("block\\.immersiveengineering\\..*_engineering");
        useToolBlocksNames.add("block\\.immersiveengineering\\..*_fence");
        useToolBlocksNames.add("block\\.immersiveengineering\\..*_wallmount");
        useToolBlocksNames.add("block\\.immersiveengineering\\..*_post");
        useToolBlocksNames.add("block\\.immersiveengineering\\..*_slope");
        useToolBlocksNames.add("block\\.immersiveengineering\\..*_breaker");
        useToolBlocksNames.add("block\\.immersiveengineering\\.connector_.*");
        useToolBlocksNames.add("block\\.immersiveengineering\\..*_wood_horizontal");
        useToolBlocksNames.add("block\\.immersiveengineering\\.blastbrick.*");
        useToolBlocksNames.add("block\\.immersiveengineering\\.sheetmetal.*");
        useToolBlocksNames.add("block\\.immersiveengineering\\.sheetmetal.*");
        useToolBlocksNames.add("block\\.immersiveengineering\\.sheetmetal.*");
        useToolBlocksNames.add("block\\.ae2\\.spatial.*");
        useToolBlocksNames.add("block\\.ae2\\..*energy_cell");
        useToolBlocksNames.add("block\\.ae2\\..*_crafting_storage");
        useToolBlocksNames.add("block\\.create\\..*water_wheel");
        useToolBlocksNames.add("block\\.create\\..*_valve_handle");
        useToolBlocksNames.add("block\\.create\\.track.*");
        useToolBlocksNames.add("block\\.create\\..*fluid.*");
        useToolBlocksNames.add("block\\.create\\..*cogwheel.*");
        useToolBlocksNames.add("block\\.create\\.mechanical.*");
        useToolBlocksNames.add("block\\.create\\..*_encased_shaft");
        useToolBlocksNames.add("block\\.create\\.redstone_.*");
        useToolBlocksNames.add("block\\.create\\.pulse_.*");
        useToolBlocksNames.add("block\\.create\\.powered_.*");
    }
}