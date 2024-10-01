package com.xiaoliua.ctl.Blocks;

import com.xiaoliua.ctl.ctl;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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
    public static final RegistryObject<Block> SIMPLE_CRAFTING_TABLE =BLOCKS.register("simple_crafting_table",
            simpleCraftingTableBlock::new);
    public static final RegistryObject<Block> BONFIRE_BLOCK = BLOCKS.register("bonfire",
            bonfireBlock::new);
    public static final RegistryObject<Block> COPPER_ORE_GRAVEL = BLOCKS.register("copper_ore_gravel",
            CopperOreGravel::new);
    public static final RegistryObject<Block> TIN_ORE_GRAVEL = BLOCKS.register("tin_ore_gravel",
            TinOreGravel::new);
}
