package com.xiaoliua.ctl.Blocks;

import com.xiaoliua.ctl.ctl;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ctl.MODID);
    public static final Supplier<BlockEntityType<hayrackBlockEntity>> HAYRACK_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("hayrack_block_entity",()->
                    BlockEntityType.Builder.of(hayrackBlockEntity::new,
                    BlockInit.HAYRACK_BLOCK.get()).build(null));
}
