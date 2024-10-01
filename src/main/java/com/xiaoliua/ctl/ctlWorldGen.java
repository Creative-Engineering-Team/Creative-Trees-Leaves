package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Features.ctlOreFeatures;
import com.xiaoliua.ctl.Features.ctlOrePlacements;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ctlWorldGen extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ctlOreFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ctlOrePlacements::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ctlBiomeModifiers::bootstrap);

    public ctlWorldGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ctl.MODID));
    }
}