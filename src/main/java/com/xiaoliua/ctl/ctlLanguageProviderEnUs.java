package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Items.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ctlLanguageProviderEnUs extends LanguageProvider {
    public ctlLanguageProviderEnUs(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add(ItemInit.LEAF.get(),"Leaf");
        this.add(ItemInit.DRY_LEAF.get(),"Dry leaf");
        this.add(ItemInit.FIBRE.get(),"Fibre");
        this.add(ItemInit.ROPE.get(),"Rope");
        this.add(ItemInit.UNFIRED_CLAY_PICKAXE.get(),"Unfired clay pickaxe");
        this.add(ItemInit.UNFIRED_CLAY_SHOVEL.get(),"Unfired clay shovel");
        this.add(ItemInit.UNFIRED_CLAY_SWORD.get(),"Unfired clay sword");
        this.add(ItemInit.UNFIRED_CLAY_HOE.get(),"Unfired clay hoe");
        this.add(ItemInit.UNFIRED_CLAY_AXE.get(),"Unfired clay axe");
        this.add(ItemInit.RUBY_BLOCK.get(),"Hayrack");
    }
}
