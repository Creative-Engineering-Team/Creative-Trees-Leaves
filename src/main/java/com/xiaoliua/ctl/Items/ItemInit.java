package com.xiaoliua.ctl.Items;

import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.ctl;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.client.model.obj.ObjMaterialLibrary;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

//@Mod(ctl.MODID)
public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ctl.MODID);

    //public static final Supplier<Item> RUBY = ITEMS.register("hayrack",() -> new Item(new Item.Properties()));

    public static final Supplier<Item> RUBY_BLOCK = ITEMS.register("hayrack",()->new BlockItem(BlockInit.HAYRACK_BLOCK.get(),
            new Item.Properties()));
    public static final Supplier<Item> LEAF = ITEMS.register("leaf",()->new LeafItem(new Item.Properties()));
    public static final Supplier<Item> DRY_LEAF = ITEMS.register("dry_leaf",()->new DryLeafItem(new Item.Properties()));
}

