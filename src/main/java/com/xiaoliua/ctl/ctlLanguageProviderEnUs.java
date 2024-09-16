package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.Items.ItemInit;
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
        this.add(ItemInit.FIBRE.get(), "Fiber");
        this.add(ItemInit.ROPE.get(), "Rope");
        this.add(ItemInit.UNFIRED_CLAY_PICKAXE.get(), "Unfired Clay Pickaxe");
        this.add(ItemInit.UNFIRED_CLAY_SHOVEL.get(), "Unfired Clay Shovel");
        this.add(ItemInit.UNFIRED_CLAY_SWORD.get(), "Unfired Clay Sword");
        this.add(ItemInit.UNFIRED_CLAY_HOE.get(), "Unfired Clay Hoe");
        this.add(ItemInit.UNFIRED_CLAY_AXE.get(), "Unfired Clay Axe");
        this.add(ItemInit.RUBY_BLOCK.get(), "Rack");
        this.add(ItemInit.PLIABLE_BRANCH.get(), "Pliable Branch");
        this.add(ItemInit.DRY_PLIABLE_BRANCH.get(), "Dry Pliable Branch");
        this.add(ItemInit.SIMPLE_CRAFTING_TABLE_BLOCK.get(), "Simple Crafting Table");
        this.add(ItemInit.UNASSEMBLED_POTTERY_PICKAXE.get(), "Unassembled Pottery Pickaxe Head");
        this.add(ItemInit.UNASSEMBLED_POTTERY_SWORD.get(), "Unassembled Pottery Sword Blade");
        this.add(ItemInit.UNASSEMBLED_POTTERY_HOE.get(), "Unassembled Pottery Hoe Head");
        this.add(ItemInit.UNASSEMBLED_POTTERY_SHOVEL.get(), "Unassembled Pottery Shovel Blade");
        this.add(ItemInit.UNASSEMBLED_POTTERY_AXE.get(), "Unassembled Pottery Axe Head");
        this.add(ItemInit.POTTERY_PICKAXE.get(), "Pottery Pickaxe");
        this.add(ItemInit.POTTERY_SWORD.get(), "Pottery Sword");
        this.add(ItemInit.POTTERY_HOE.get(), "Pottery Hoe");
        this.add(ItemInit.POTTERY_SHOVEL.get(), "Pottery Shovel");
        this.add(ItemInit.POTTERY_AXE.get(), "Pottery Axe");
        this.add("text.ctl.DurabilityPenalty","The combined item of this item will consume 10 points of durability");
        this.add("text.ctl.NotDurabilityPenalty","This is a normal item, and the item synthesized with it will last normally");
        this.add(ItemInit.SIMPLE_FLINT_AND_STEEL.get(),"Simple flint & sheet");
        //this.add(ItemInit.BONFIRE_BLOCK_ITEM.get(),"Bonfire");
        this.add(BlockInit.BONFIRE_BLOCK.get(),"Bonfire");
    }
}
