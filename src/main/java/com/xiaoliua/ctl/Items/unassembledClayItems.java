package com.xiaoliua.ctl.Items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class unassembledClayItems extends Item {
    public static final String DURABILITY_PENALTY_TAG = "DurabilityPenalty";
    public unassembledClayItems(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (Math.random() < 0.15)
            tag.putBoolean(DURABILITY_PENALTY_TAG, true); // 标记物品需要耐久减少
        return super.getCraftingRemainingItem(itemStack);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        if (p_41421_.getTag() != null && p_41421_.getTag().getBoolean(DURABILITY_PENALTY_TAG)) {
            p_41423_.add(Component.translatable("text.ctl.DurabilityPenalty"));
        } else {
            p_41423_.add(Component.translatable("text.ctl.NotDurabilityPenalty"));
        }
    }
}
