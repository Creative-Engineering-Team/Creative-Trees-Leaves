package com.xiaoliua.ctl.Items;

import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.ctl;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
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
            ()-> new unassembledPotteryPickaxe(new Item.Properties()));
    public static final Supplier<Item> UNASSEMBLED_POTTERY_HOE = ITEMS.register("unassembled_pottery_hoe",
            ()-> new unassembledPotteryPickaxe(new Item.Properties()));
    public static final Supplier<Item> UNASSEMBLED_POTTERY_SHOVEL = ITEMS.register("unassembled_pottery_shovel",
            ()-> new unassembledPotteryPickaxe(new Item.Properties()));
    public static final Supplier<Item> UNASSEMBLED_POTTERY_AXE = ITEMS.register("unassembled_pottery_axe",
            ()-> new unassembledPotteryPickaxe(new Item.Properties()));
    public static final Supplier<Item> POTTERY_PICKAXE = ITEMS.register("pottery_pickaxe",
            ()-> new potteryPickaxe(new Item.Properties()));
    public static final Supplier<Item> POTTERY_AXE = ITEMS.register("pottery_axe",
            ()-> new potteryAxe(new Item.Properties()));
    public static final Supplier<Item> POTTERY_HOE = ITEMS.register("pottery_hoe",
            ()-> new potteryHoe(new Item.Properties()));
    public static final Supplier<Item> POTTERY_SHOVEL = ITEMS.register("pottery_shovel",
            ()-> new potteryShovel(new Item.Properties()));
    public static final Supplier<Item> POTTERY_SWORD = ITEMS.register("pottery_sword",
            ()-> new potterySword(new Item.Properties()));
}
