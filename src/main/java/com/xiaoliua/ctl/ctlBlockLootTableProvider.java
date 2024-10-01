package com.xiaoliua.ctl;

import blusunrize.immersiveengineering.api.EnumMetals;
import blusunrize.immersiveengineering.common.register.IEItems;
import com.xiaoliua.ctl.Blocks.BlockInit;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
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
import java.util.Objects;
import java.util.Set;

public class ctlBlockLootTableProvider extends BlockLootSubProvider {

    public static final Set<Block> BLOCKS = Set.of(
            BlockInit.HAYRACK_BLOCK.get(),
            BlockInit.SIMPLE_CRAFTING_TABLE.get(),
            BlockInit.BONFIRE_BLOCK.get(),
            BlockInit.COPPER_ORE_GRAVEL.get(),
            BlockInit.TIN_ORE_GRAVEL.get()
    );

    protected ctlBlockLootTableProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(BlockInit.HAYRACK_BLOCK.get());
        this.dropOther(BlockInit.SIMPLE_CRAFTING_TABLE.get(),BlockInit.SIMPLE_CRAFTING_TABLE.get());
        this.add(BlockInit.BONFIRE_BLOCK.get(), this::BonfireLootTable);
        this.add(BlockInit.COPPER_ORE_GRAVEL.get(),this::CopperOreGravelLootTable);
        this.add(BlockInit.TIN_ORE_GRAVEL.get(),this::TinOreGravelLootTable);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BLOCKS;
    }

    private LootTable.Builder BonfireLootTable(Block block) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.STICK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
                        .when(LootItemRandomChanceCondition.randomChance(1.0F))
                );
    }

    private LootTable.Builder CopperOreGravelLootTable(Block block) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(IEItems.Metals.NUGGETS.get(EnumMetals.COPPER))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3))))
                        .when(LootItemRandomChanceCondition.randomChance(1.0F))
                );
    }

    private LootTable.Builder TinOreGravelLootTable(Block block) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Objects.requireNonNull(MekanismItems.PROCESSED_RESOURCES.
                                        get(ResourceType.NUGGET, PrimaryResource.TIN)))
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                        .when(LootItemRandomChanceCondition.randomChance(1.0F))
                );
    }
}
