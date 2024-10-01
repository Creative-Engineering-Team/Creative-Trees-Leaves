package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.Items.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ctlLanguageProviderZhCn extends LanguageProvider {
    public ctlLanguageProviderZhCn(PackOutput output) {
        super(output, ctl.MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        this.add(ItemInit.LEAF.get(),"树叶");
        this.add(ItemInit.DRY_LEAF.get(),"干燥树叶");
        this.add(ItemInit.FIBRE.get(),"纤维");
        this.add(ItemInit.ROPE.get(),"粗制绳子");
        this.add(ItemInit.UNFIRED_CLAY_PICKAXE.get(),"未成型的粘土稿");
        this.add(ItemInit.UNFIRED_CLAY_SHOVEL.get(),"未成型的粘土铲");
        this.add(ItemInit.UNFIRED_CLAY_SWORD.get(),"未成型的粘土剑");
        this.add(ItemInit.UNFIRED_CLAY_HOE.get(),"未成型的粘土锄");
        this.add(ItemInit.UNFIRED_CLAY_AXE.get(),"未成型的粘土斧");
        this.add(ItemInit.RUBY_BLOCK.get(),"干燥架");
        this.add(ItemInit.PLIABLE_BRANCH.get(),"枝条");
        this.add(ItemInit.DRY_PLIABLE_BRANCH.get(),"干燥枝条");
        this.add(ItemInit.SIMPLE_CRAFTING_TABLE_BLOCK.get(), "简易工作台");
        this.add(ItemInit.UNASSEMBLED_POTTERY_PICKAXE.get(), "陶土镐头");
        this.add(ItemInit.UNASSEMBLED_POTTERY_SWORD.get(), "陶土剑刃");
        this.add(ItemInit.UNASSEMBLED_POTTERY_HOE.get(), "陶土锄头");
        this.add(ItemInit.UNASSEMBLED_POTTERY_SHOVEL.get(), "陶土锹刃");
        this.add(ItemInit.UNASSEMBLED_POTTERY_AXE.get(), "陶土斧头");
        this.add(ItemInit.POTTERY_PICKAXE.get(), "陶土镐");
        this.add(ItemInit.POTTERY_SWORD.get(), "陶剑");
        this.add(ItemInit.POTTERY_HOE.get(), "陶土锄");
        this.add(ItemInit.POTTERY_SHOVEL.get(), "陶土锹");
        this.add(ItemInit.POTTERY_AXE.get(), "陶土斧");
        this.add("text.ctl.DurabilityPenalty","这个物品合成的物品将损耗10点耐久");
        this.add("text.ctl.NotDurabilityPenalty","这是个正常的物品,用它合成的物品耐久正常");
        this.add(ItemInit.SIMPLE_FLINT_AND_STEEL.get(),"简易打火石");
        //this.add(ItemInit.BONFIRE_BLOCK_ITEM.get(),"火堆");
        this.add(BlockInit.BONFIRE_BLOCK.get(),"火堆");
        this.add(BlockInit.COPPER_ORE_GRAVEL.get(),"铜矿沙砾");
        this.add(BlockInit.TIN_ORE_GRAVEL.get(),"锡矿沙砾");
        this.add(ItemInit.NUGGET_TIN_AND_COPPER.get(), "铜锡混合粒");
    }
}
