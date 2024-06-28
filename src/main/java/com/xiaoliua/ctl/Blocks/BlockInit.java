package com.xiaoliua.ctl.Blocks;

import com.xiaoliua.ctl.ctl;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.client.model.obj.ObjMaterialLibrary;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

//@Mod(ctl.MODID)
public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,ctl.MODID
    );
//    public static final RegistryObject<Block> HAYRACK_BLOCK = BLOCKS.register("hayrack",
//            ()->new hayrackBlock((BlockBehaviour.Properties.of().strength(5f))));
    //public static final Supplier<Block> HAYRACK_BLOCK = BLOCKS.register("hayrack",hayrackBlock::new);
    public static final RegistryObject<Block> HAYRACK_BLOCK = BLOCKS.register("hayrack",
            hayrackBlock::new);
}
