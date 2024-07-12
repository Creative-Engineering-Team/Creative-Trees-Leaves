package com.xiaoliua.ctl;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsInit {
    public static final TagKey<Block> useTool = create("use_tool");
    public static final TagKey<Block> notTool = create("not_tool");
    private static TagKey<Block> create(String id)
    {
        return TagKey.create(BuiltInRegistries.BLOCK.key(), new ResourceLocation(ctl.MODID, id));
    }
}