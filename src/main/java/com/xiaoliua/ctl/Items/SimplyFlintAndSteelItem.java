package com.xiaoliua.ctl.Items;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SimplyFlintAndSteelItem extends FlintAndSteelItem {
    private static final String FAILURE_COUNT_TAG = "FailureCount";
    public SimplyFlintAndSteelItem(Properties p_41295_) {
        super(p_41295_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        ItemStack itemStack = context.getItemInHand();
        Player player = context.getPlayer();
        level.playSound(player,pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS,
                1.0f,level.getRandom().nextFloat()*0.4f+0.8f);
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
                    itemStack.hurtAndBreak(1,context.getPlayer(),(Pplayer)->{
                        Pplayer.broadcastBreakEvent(context.getHand());
                    });
                    return InteractionResult.PASS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
