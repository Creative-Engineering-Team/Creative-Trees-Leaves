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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ctlBlockLootTableProvider extends BlockLootSubProvider {

    public static final Set<Block> BLOCKS = Set.of(
            BlockInit.HAYRACK_BLOCK.get(),
            BlockInit.SIMPLE_CRAFTING_TABLE.get(),
            BlockInit.BONFIRE_BLOCK.get()
    );

    protected ctlBlockLootTableProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(BlockInit.HAYRACK_BLOCK.get());
        this.dropOther(BlockInit.SIMPLE_CRAFTING_TABLE.get(),BlockInit.SIMPLE_CRAFTING_TABLE.get());
        this.add(BlockInit.BONFIRE_BLOCK.get(), this::createCustomSticksLootTable);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BLOCKS;
    }

    private LootTable.Builder createCustomSticksLootTable(Block block) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.STICK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
                        .when(LootItemRandomChanceCondition.randomChance(1.0F))
                );
    }
}
