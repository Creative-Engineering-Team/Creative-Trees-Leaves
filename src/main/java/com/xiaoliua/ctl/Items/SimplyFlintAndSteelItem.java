package com.xiaoliua.ctl.Items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SimplyFlintAndSteelItem extends FlintAndSteelItem {
    public SimplyFlintAndSteelItem(Properties p_41295_) {
        super(p_41295_);
    }
    private static final String FAILURE_COUNT_TAG = "FailureCount";

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        ItemStack itemStack = context.getItemInHand();
        if (!level.isClientSide()) {
            CompoundTag tag = itemStack.getOrCreateTag();
            int failureCount = tag.getInt(FAILURE_COUNT_TAG);

            if (failureCount >= 5) {
                super.useOn(context);
                tag.putInt(FAILURE_COUNT_TAG, 0);
                return InteractionResult.SUCCESS;
            } else {
                double randomChance = Math.random();
                if (randomChance < 0.6) {
                    super.useOn(context);
                    tag.putInt(FAILURE_COUNT_TAG, 0);
                    return InteractionResult.SUCCESS;
                } else {
                    tag.putInt(FAILURE_COUNT_TAG, failureCount + 1);
                    itemStack.hurtAndBreak(1,context.getPlayer(),(player)->{
                        player.broadcastBreakEvent(context.getHand());
                    });
                    return InteractionResult.PASS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
