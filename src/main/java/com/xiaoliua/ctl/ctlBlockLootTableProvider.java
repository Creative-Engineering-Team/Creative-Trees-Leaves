package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.Items.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ctlBlockLootTableProvider extends BlockLootSubProvider {

    public static final Set<Block> BLOCKS = Set.of(
            BlockInit.HAYRACK_BLOCK.get(),
            BlockInit.SIMPLE_CRAFTING_TABLE.get()
    );

    protected ctlBlockLootTableProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(BlockInit.HAYRACK_BLOCK.get());
        this.dropOther(BlockInit.SIMPLE_CRAFTING_TABLE.get(),BlockInit.SIMPLE_CRAFTING_TABLE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BLOCKS;
    }
}
