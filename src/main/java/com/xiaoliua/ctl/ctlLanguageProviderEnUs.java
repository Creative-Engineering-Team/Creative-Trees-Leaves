package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Items.ItemInit;
import net.minecraft.Util;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ctlLanguageProviderEnUs extends LanguageProvider {
    public ctlLanguageProviderEnUs(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add(ItemInit.LEAF.get(), "Leaf");
        this.add(ItemInit.DRY_LEAF.get(), "Dry Leaf");
        this.add(ItemInit.FIBRE.get(), "Fibre");
        this.add(ItemInit.ROPE.get(), "Rope");
        this.add(ItemInit.UNFIRED_CLAY_PICKAXE.get(), "Unfired Clay Pickaxe");
        this.add(ItemInit.UNFIRED_CLAY_SHOVEL.get(), "Unfired Clay Shovel");
        this.add(ItemInit.UNFIRED_CLAY_SWORD.get(), "Unfired Clay Sword");
        this.add(ItemInit.UNFIRED_CLAY_HOE.get(), "Unfired Clay Hoe");
        this.add(ItemInit.UNFIRED_CLAY_AXE.get(), "Unfired Clay Axe");
        this.add(ItemInit.RUBY_BLOCK.get(), "Dry Rack");
        this.add(ItemInit.PLIABLE_BRANCH.get(), "Branch");
        this.add(ItemInit.DRY_PLIABLE_BRANCH.get(), "Dry Branch");
        this.add(ItemInit.SIMPLE_CRAFTING_TABLE_BLOCK.get(), "Simple Crafting Table");
    }
}
