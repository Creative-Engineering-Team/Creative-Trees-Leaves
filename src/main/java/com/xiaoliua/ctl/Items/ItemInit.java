package com.xiaoliua.ctl.Items;

import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.ctl;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

//@Mod(ctl.MODID)
public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ctl.MODID);

    //public static final Supplier<Item> RUBY = ITEMS.register("hayrack",() -> new Item(new Item.Properties()));

    public static final Supplier<Item> RUBY_BLOCK = ITEMS.register("hayrack",
            ()->new BlockItem(BlockInit.HAYRACK_BLOCK.get(),
            new Item.Properties()));
    public static final Supplier<Item> SIMPLE_CRAFTING_TABLE_BLOCK = ITEMS.register("simple_crafting_table",
            ()->new BlockItem(BlockInit.SIMPLE_CRAFTING_TABLE.get(),
            new Item.Properties()));
    public static final Supplier<Item> LEAF = ITEMS.register("leaf",
            ()->new LeafItem(new Item.Properties()));
    public static final Supplier<Item> DRY_LEAF = ITEMS.register("dry_leaf",
            ()->new DryLeafItem(new Item.Properties()));
    public static final Supplier<Item> FIBRE = ITEMS.register("fibre",
            ()->new fibreItem(new Item.Properties()));
    public static final Supplier<Item> ROPE = ITEMS.register("rope",
            ()->new ropeItem(new Item.Properties()));
    public static final Supplier<Item> UNFIRED_CLAY_AXE = ITEMS.register("unfired_clay_axe",
            ()->new unfiredClayAxeItem(new Item.Properties()));
    public static final Supplier<Item> UNFIRED_CLAY_HOE = ITEMS.register("unfired_clay_hoe",
            ()->new unfiredClayHoeItem(new Item.Properties()));
    public static final Supplier<Item> UNFIRED_CLAY_PICKAXE = ITEMS.register("unfired_clay_pickaxe",
            ()->new unfiredClayPickaxeItem(new Item.Properties()));
    public static final Supplier<Item> UNFIRED_CLAY_SHOVEL = ITEMS.register("unfired_clay_shovel",
            ()->new unfiredClayShovelItem(new Item.Properties()));
    public static final Supplier<Item> UNFIRED_CLAY_SWORD = ITEMS.register("unfired_clay_sword",
            ()->new unfiredClaySwordItem(new Item.Properties()));
    public static final Supplier<Item> PLIABLE_BRANCH = ITEMS.register("pliable_branch",
            ()->new pliableBrancheItem(new Item.Properties()));
    public static final Supplier<Item> DRY_PLIABLE_BRANCH = ITEMS.register("dry_pliable_branch",
            ()->new dryPliableBrancheItem(new Item.Properties()));
    public static final Supplier<Item> UNASSEMBLED_POTTERY_PICKAXE = ITEMS.register("unassembled_pottery_pickaxe",
            ()-> new unassembledPotteryPickaxe(new Item.Properties()));
    public static final Supplier<Item> UNASSEMBLED_POTTERY_SWORD = ITEMS.register("unassembled_pottery_sword",
            ()-> new unassembledPotterySword(new Item.Properties()));
    public static final Supplier<Item> UNASSEMBLED_POTTERY_HOE = ITEMS.register("unassembled_pottery_hoe",
            ()-> new unassembledPotteryHoe(new Item.Properties()));
    public static final Supplier<Item> UNASSEMBLED_POTTERY_SHOVEL = ITEMS.register("unassembled_pottery_shovel",
            ()-> new unassembledPotteryShovel(new Item.Properties()));
    public static final Supplier<Item> UNASSEMBLED_POTTERY_AXE = ITEMS.register("unassembled_pottery_axe",
            ()-> new unassembledPotteryAxe(new Item.Properties()));
    public static final Supplier<Item> POTTERY_PICKAXE = ITEMS.register("pottery_pickaxe",
            ()-> new potteryPickaxe(ctl.copyPropertiesFrom(Items.WOODEN_PICKAXE)));
    public static final Supplier<Item> POTTERY_AXE = ITEMS.register("pottery_axe",
            ()-> new potteryAxe(ctl.copyPropertiesFrom(Items.WOODEN_AXE)));
    public static final Supplier<Item> POTTERY_HOE = ITEMS.register("pottery_hoe",
            ()-> new potteryHoe(ctl.copyPropertiesFrom(Items.WOODEN_HOE)));
    public static final Supplier<Item> POTTERY_SHOVEL = ITEMS.register("pottery_shovel",
            ()-> new potteryShovel(ctl.copyPropertiesFrom(Items.WOODEN_SHOVEL)));
    public static final Supplier<Item> POTTERY_SWORD = ITEMS.register("pottery_sword",
            ()-> new potterySword(ctl.copyPropertiesFrom(Items.WOODEN_SWORD)));
    public static final Supplier<Item> BONFIRE_BLOCK_ITEM = ITEMS.register("bonfire",
            ()->new BlockItem(BlockInit.BONFIRE_BLOCK.get(),
                    new Item.Properties()));
    public static final Supplier<Item> SIMPLE_FLINT_AND_STEEL = ITEMS.register("simply_flint_and_steel",
            () -> new SimplyFlintAndSteelItem(new Item.Properties().stacksTo(1).durability(30)));
    @SuppressWarnings("unused")
    public static final Supplier<Item> COPPER_ORE_GRAVEL_ITEM = ITEMS.register("copper_ore_gravel",
            ()->new BlockItem(BlockInit.COPPER_ORE_GRAVEL.get(),
                    new Item.Properties()));
    @SuppressWarnings("unused")
    public static final Supplier<Item> TIN_ORE_GRAVEL_ITEM = ITEMS.register("tin_ore_gravel",
            ()->new BlockItem(BlockInit.TIN_ORE_GRAVEL.get(),
                    new Item.Properties()));
    public static final Supplier<Item> NUGGET_TIN_AND_COPPER = ITEMS.register("nugget_tin_and_copper",
            ()->new nuggetTinAndCopper(new Item.Properties()));
}
