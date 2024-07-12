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

    public static final Supplier<Item> RUBY_BLOCK = ITEMS.register("hayrack",()->new BlockItem(BlockInit.HAYRACK_BLOCK.get(),
            new Item.Properties()));
    public static final Supplier<Item> LEAF = ITEMS.register("leaf",()->new LeafItem(new Item.Properties()));
    public static final Supplier<Item> DRY_LEAF = ITEMS.register("dry_leaf",()->new DryLeafItem(new Item.Properties()));
    public static final Supplier<Item> FIBRE = ITEMS.register("fibre",()->new fibreItem(new Item.Properties()));
    public static final Supplier<Item> ROPE = ITEMS.register("rope",()->new ropeItem(new Item.Properties()));
    public static final Supplier<Item> UNFIRED_CLAY_AXE = ITEMS.register("unfired_clay_axe",()->new unfiredClayAxeItem(new Item.Properties()));
    public static final Supplier<Item> UNFIRED_CLAY_HOE = ITEMS.register("unfired_clay_hoe",()->new unfiredClayHoeItem(new Item.Properties()));
    public static final Supplier<Item> UNFIRED_CLAY_PICKAXE = ITEMS.register("unfired_clay_pickaxe",()->new unfiredClayPickaxeItem(new Item.Properties()));
    public static final Supplier<Item> UNFIRED_CLAY_SHOVEL = ITEMS.register("unfired_clay_shovel",()->new unfiredClayShovelItem(new Item.Properties()));
    public static final Supplier<Item>UNFIRED_CLAY_SWORD = ITEMS.register("unfired_clay_sword",()->new unfiredClaySwordItem(new Item.Properties()));
}
