package com.xiaoliua.ctl;

import com.mojang.logging.LogUtils;
import com.xiaoliua.ctl.Blocks.BlockEntityInit;
import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.Blocks.bonfireBlock;
import com.xiaoliua.ctl.Items.ItemInit;
import com.xiaoliua.ctl.Items.unassembledClayItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ctl.MODID)
public class ctl
{
    public static final Random RANDOM = new Random();
    // Define mod id in a common place for everything to reference
    public static final String MODID = "ctl";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "ctl" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    // Creates a creative tab with the id "ctl:ctl_tab" for the ctl item, that is placed after the combat tab
    public static final RegistryObject<CreativeModeTab> CTL_TAB = CREATIVE_MODE_TABS.register("ctl_tab", () -> CreativeModeTab.builder()
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
                output.accept(ItemInit.UNASSEMBLED_POTTERY_AXE.get());
                output.accept(ItemInit.UNASSEMBLED_POTTERY_HOE.get());
                output.accept(ItemInit.UNASSEMBLED_POTTERY_SWORD.get());
                output.accept(ItemInit.UNASSEMBLED_POTTERY_PICKAXE.get());
                output.accept(ItemInit.UNASSEMBLED_POTTERY_SHOVEL.get());
                output.accept(ItemInit.POTTERY_PICKAXE.get());
                output.accept(ItemInit.POTTERY_AXE.get());
                output.accept(ItemInit.POTTERY_HOE.get());
                output.accept(ItemInit.POTTERY_SHOVEL.get());
                output.accept(ItemInit.POTTERY_SWORD.get());
                output.accept(ItemInit.BONFIRE_BLOCK_ITEM.get());
                output.accept(ItemInit.SIMPLE_FLINT_AND_STEEL.get());
            })
            .title(Component.literal("Creative Trees & Leaves")).build());
    //Create a List to match the blocks
    public static final List<String> useToolBlocksNames = new ArrayList<>();

    public static final ArtifactVersion version = ModLoadingContext.get().getActiveContainer().getModInfo()
            .getVersion();

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

    public ctl()
    {
        LOGGER.info("Welcome use ctl,version: "+version.toString() + " by xiaoliu_a&mc100212");
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
        MinecraftForge.EVENT_BUS.addListener((BlockEvent.BreakEvent event) -> {
            Player player = event.getPlayer();
            Block block = event.getState().getBlock();
            if (block == BlockInit.BONFIRE_BLOCK.get() && player.getMainHandItem().getItem().equals(Items.AIR)){
                bonfireBlock.hurtByFire(event.getState(),player.level(),
                        event.getPos(),player);
            }
        });
        MinecraftForge.EVENT_BUS.addListener((PlayerEvent.ItemCraftedEvent event) -> {
            for (int i = 0; i < event.getInventory().getContainerSize(); i++) {
                ItemStack stack = event.getInventory().getItem(i);


                if (!stack.isEmpty() && stack.getItem() instanceof unassembledClayItems) {
                    // 检查是否有耐久减少标签
                    if (stack.getOrCreateTag().getBoolean(unassembledClayItems.DURABILITY_PENALTY_TAG)) {
                        // 减少耐久度
                        //stack.setDamageValue(stack.getDamageValue() - 10);
                        event.getCrafting().setDamageValue(10);

                        // 如果耐久用完，移除物品
                        if (stack.getDamageValue() >= stack.getMaxDamage()) {
                            stack.shrink(1);
                        }
                    }
                }
            }
        });
        MinecraftForge.EVENT_BUS.addListener((BlockEvent.BreakEvent event)->{
            Player player = event.getPlayer();
            Level world = player.level();
            BlockState blockState = event.getState();
            if (blockState.is(TagsInit.Blocks.Leaves) && !player.getMainHandItem().getItem().equals(Items.SHEARS)&&
                    !player.isCreative()){
                event.setExpToDrop(0);
                //event.setCanceled(true);
                //List<ItemStack> drop = Block.getDrops(blockState, (ServerLevel) world,event.getPos(),null);
                //drop.clear();
                //LOGGER.info("ttt");
                //if (RANDOM.nextFloat() < 0.75){
                    ItemStack realDrop = new ItemStack(ItemInit.PLIABLE_BRANCH.get());
                    world.addFreshEntity(new ItemEntity(world,event.getPos().getX(),event.getPos().getY(),event.getPos().getZ(),realDrop));
                    if (RANDOM.nextFloat() < 0.1){
                        world.addFreshEntity(new ItemEntity(world,event.getPos().getX(),event.getPos().getY(),event.getPos().getZ(),realDrop));
                    }
                //}
            }
        });
        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static float modifyBreakSpeed(Player player, BlockState state, @Nullable BlockPos pos, float speed)
    {
        //if (player.getName().equals(Component.literal("Dev")))return 114514;
        if (isUsingCorrectToolToMine(state, player)){
            return speed;
        }
        return 0;
    }

    public static boolean isUsingCorrectToolToMine(BlockState state, Player player)
    {
        return isUsingCorrectTool(state, player);
    }

    public static boolean needTool(Block block,BlockState state){
        if (state.is(TagsInit.Blocks.useTool)||state.is(TagsInit.Blocks.ae2BlockMachines)||
                state.is(TagsInit.Blocks.createBlockMachines)||state.is(TagsInit.Blocks.IEMachines)||
                state.is(TagsInit.Blocks.MEKMachines) || state.is(TagsInit.Blocks.FarmersdelightUseTool)){
            //LOGGER.debug("need tool,modified");
            return true;
        }
        for (String useToolBlocksName : useToolBlocksNames) {
            if (Pattern.matches(useToolBlocksName, block.getDescriptionId())) {
                //LOGGER.debug("need tool,modified");
                return true;
            }
        }
        //LOGGER.debug("another1,modified");
        return false;
    }

    public static boolean isUsingCorrectTool(BlockState state, Player player) {
        if (!Config.useMineAble) {
            //LOGGER.debug("not use MineAble,pass");
            return true;
        }
        if (state.is(TagsInit.Blocks.notTool)){
            //LOGGER.debug("block not use MineAble,pass");
            return true;
        }
        if (needTool(state.getBlock(),state) && (player.getMainHandItem().isCorrectToolForDrops(state))){
            //LOGGER.debug("correct tool,pass");
            return true;
        }
        if (needTool(state.getBlock(),state) && (player.getMainHandItem().getDestroySpeed(state)>1.0f)){
            //LOGGER.debug("another,pass");
            return true;
        }
        return !needTool(state.getBlock(), state);
    }

    public static Item.Properties copyPropertiesFrom(Item existingItem) {
        Item.Properties properties = new Item.Properties();

        // 手动复制常见的属性
        properties.stacksTo(existingItem.getMaxStackSize());
        properties.durability(existingItem.getMaxDamage());

        // 根据需要添加其他属性

        return properties;
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
    }

    // Add the ctl block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    @SubscribeEvent
    public void kaboom(ExplosionEvent.Detonate event) {
        LOGGER.info("Kaboom! Something just blew up in {}!", event.getLevel());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        RecipeManager recipeManager = event.getServer().getRecipeManager();
        ResourceLocation woodenAxeRecipeId = new ResourceLocation("minecraft", "wooden_axe");
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
        }
    }
}