package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Items.ItemInit;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = ctl.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final String[] itemsBySCT = {
            "ctl:unfired_clay_shovel",
            "ctl:unfired_clay_axe",
            "ctl:unfired_clay_pickaxe",
            "ctl:unfired_clay_shovel",
            "ctl:unfired_clay_sword",
            "ctl:rope",
            "ctl:fibre",
            "ctl:hayrack",
            "ctl:pottery_shovel",
            "ctl:pottery_axe",
            "ctl:pottery_pickaxe",
            "ctl:pottery_shovel",
            "ctl:pottery_sword"
    };

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder()
            .comment("Creative Trees&Leaves settings")
            .push("general");

    public static final ForgeConfigSpec.IntValue WAITING_TIME = BUILDER
            .comment("Hayrack wait time(tick)")
            .defineInRange("wait_time",600,0,Integer.MAX_VALUE);

    public static final ForgeConfigSpec.BooleanValue USE_MINE_ABLE = BUILDER
            .comment("Use mine able")
            .define("useMineAble",true);

    private static final ForgeConfigSpec.BooleanValue LOG_DIRT_BLOCK = BUILDER
            .comment("Whether to log the dirt block on common setup")
            .define("logDirtBlock", true);

    private static final ForgeConfigSpec.IntValue MAGIC_NUMBER = BUILDER
            .comment("A magic number")
            .defineInRange("magicNumber", 42, 0, Integer.MAX_VALUE);

    public static final ForgeConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
            .comment("What you want the introduction message to be for the magic number")
            .define("magicNumberIntroduction", "The magic number is... ");

    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
            .comment("A list of items to log on common setup.")
            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), Config::validateItemName);

    private static final ForgeConfigSpec.ConfigValue<Boolean> ALLOW_WOODEN = BUILDER
            .comment("allow wooden tools")
            .define("allowWooden",true);

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> CAN_CRAFT_WITH_SIMPLE_CRAFTING_TABLE = BUILDER
            .comment("can craft with simple crafting table's  items")
            .define("craftingBySimpleCraftingTable", List.of(itemsBySCT));

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean logDirtBlock;
    public static int magicNumber;
    public static String magicNumberIntroduction;
    public static Set<Item> items;
    public static int waitTime;
    public static boolean useMineAble;
    public static boolean allowWooden;
    public static Set<Item> SCTItems;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        logDirtBlock = LOG_DIRT_BLOCK.get();
        magicNumber = MAGIC_NUMBER.get();
        magicNumberIntroduction = MAGIC_NUMBER_INTRODUCTION.get();
        waitTime = WAITING_TIME.get();
        useMineAble = USE_MINE_ABLE.get();
        allowWooden = ALLOW_WOODEN.get();
        String ops = logDirtBlock+" "+magicNumber+" "+magicNumberIntroduction+" "+waitTime+" "+
                useMineAble+" "+allowWooden;
        ctl.LOGGER.debug("Configs: {}", ops);
        SCTItems = CAN_CRAFT_WITH_SIMPLE_CRAFTING_TABLE.get().stream()
                .map(itemName -> BuiltInRegistries.ITEM.get(
                        new ResourceLocation(itemName)))
                .collect(Collectors.toSet());

        // convert the list of strings into a set of items
        items = ITEM_STRINGS.get().stream()
                .map(itemName -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName)))
                .collect(Collectors.toSet());
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        ExistingFileHelper efh = event.getExistingFileHelper();
        var lp = event.getLookupProvider();

        event.getGenerator().addProvider(
                event.includeServer(),
                //(DataProvider.Factory<ctlRecipeProvider>) pOutput -> new ctlRecipeProvider(pOutput,lp)
                (DataProvider.Factory<ctlRecipeProvider>) ctlRecipeProvider::new
        );

        event.getGenerator().addProvider(
                event.includeServer(),
                (DataProvider.Factory<ctlLootTableProvider>)pOutput-> new ctlLootTableProvider(pOutput, Collections.emptySet(),
                        List.of(
                                new LootTableProvider.SubProviderEntry(ctlBlockLootTableProvider::new, LootContextParamSets.BLOCK)
                        ))
        );

        event.getGenerator().addProvider(
                event.includeServer(),
                (DataProvider.Factory<ctlBlockTagProvider>) pOutput -> new ctlBlockTagProvider(pOutput,lp,ctl.MODID,efh)
        );

        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<ctlLanguageProviderEnUs>) pOutput -> new ctlLanguageProviderEnUs(pOutput,ctl.MODID,"en_us")
        );

        event.getGenerator().addProvider(
                event.includeClient(),
                (DataProvider.Factory<ctlLanguageProviderZhCn>) pOutput -> new ctlLanguageProviderZhCn(pOutput,ctl.MODID,"zh_cn")
        );
    }
}
