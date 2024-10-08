package com.xiaoliua.ctl;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsInit {
    class Blocks{
        public static final TagKey<Block> useTool = create("use_tool");
        public static final TagKey<Block> notTool = create("not_tool");
        public static final TagKey<Block> createBlockMachines = create("create_machines");
        public static final TagKey<Block> ae2BlockMachines = create("ae2_machines");
        public static final TagKey<Block> IEMachines = create("ie_machines");
        public static final TagKey<Block> MEKMachines = create("mek_machines");
        public static final TagKey<Block> Leaves = create("leaves");
        public static final TagKey<Block> FarmersdelightUseTool = create("farmersdelight_use_tool");
        private static TagKey<Block> create(String id)
        {
            return TagKey.create(BuiltInRegistries.BLOCK.key(), new ResourceLocation(ctl.MODID, id));
        }
    }
    class Items{
        public static final TagKey<Item> Planks = create("planks");
        private static TagKey<Item> create(String id){
            return TagKey.create(BuiltInRegistries.ITEM.key(),new ResourceLocation(ctl.MODID,id));
        }
    }
}