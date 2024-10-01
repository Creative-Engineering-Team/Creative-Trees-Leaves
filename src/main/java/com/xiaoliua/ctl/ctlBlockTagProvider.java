package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Blocks.BlockInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ctlBlockTagProvider extends BlockTagsProvider {
    public ctlBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ctl.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(BlockInit.HAYRACK_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(BlockInit.TIN_ORE_GRAVEL.get())
                .add(BlockInit.COPPER_ORE_GRAVEL.get());
        tag(BlockTags.CAMPFIRES)
                .add(BlockInit.BONFIRE_BLOCK.get());
    }
}
