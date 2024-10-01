package com.xiaoliua.ctl.Blocks;

import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class TinOreGravel extends FallingBlock {
    public TinOreGravel() {
        super (Properties.of().mapColor(MapColor.STONE)
                .strength(0.6f).sound(SoundType.GRAVEL)
                .requiresCorrectToolForDrops());
    }
}
