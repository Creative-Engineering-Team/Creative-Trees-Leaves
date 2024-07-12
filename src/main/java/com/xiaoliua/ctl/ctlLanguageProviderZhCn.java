package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Items.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ctlLanguageProviderZhCn extends LanguageProvider {
    public ctlLanguageProviderZhCn(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        this.add(ItemInit.LEAF.get(),"树叶");
        this.add(ItemInit.DRY_LEAF.get(),"干燥树叶");
        this.add(ItemInit.FIBRE.get(),"纤维");
        this.add(ItemInit.ROPE.get(),"绳子");
        this.add(ItemInit.UNFIRED_CLAY_PICKAXE.get(),"未成型的粘土稿");
        this.add(ItemInit.UNFIRED_CLAY_SHOVEL.get(),"未成型的粘土铲");
        this.add(ItemInit.UNFIRED_CLAY_SWORD.get(),"未成型的粘土剑");
        this.add(ItemInit.UNFIRED_CLAY_HOE.get(),"未成型的粘土锄");
        this.add(ItemInit.UNFIRED_CLAY_AXE.get(),"未成型的粘土斧");
    }
}
