package com.xiaoliua.ctl.Menus;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraftforge.items.ItemStackHandler;

public class simpleCraftingTableMenu extends CraftingMenu {
    private final ItemStackHandler itemStackHandler = new ItemStackHandler(9); // 3x3 grid

    public simpleCraftingTableMenu(int p_39353_, Inventory p_39354_) {
        super(p_39353_, p_39354_);
        //this.addSlot(new ResultSlot(p_39354_.player, ))
    }

    @Override
    public ItemStack quickMoveStack(Player p_39391_, int p_39392_) {
        return new ItemStack(Items.STONE);
    }

//    @Override
//    public void slotsChanged(Container p_39366_) {
//        super.slotsChanged(p_39366_);
//        //super.setItem(0,0,new ItemStack(Items.STONE));
//        super.getSlot(0).set(new ItemStack(Items.STONE));
//    }
}
