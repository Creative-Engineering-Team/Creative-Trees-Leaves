package com.xiaoliua.ctl.Menus;

import com.xiaoliua.ctl.Items.ItemInit;
import com.xiaoliua.ctl.ShapedRecipeClass;
import com.xiaoliua.ctl.ShapelessRecipeClass;
import com.xiaoliua.ctl.ctl;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class simpleCraftingTableMenu extends CraftingMenu {
    private static final List<ShapedRecipeClass> RecipesShaped= new ArrayList<>();
    private static final List<ShapelessRecipeClass> RecipesShapeless = new ArrayList<>();
    public simpleCraftingTableMenu(int p_39353_, Inventory p_39354_) {
        super(p_39353_, p_39354_);
        //this.addSlot(new ResultSlot(p_39354_.player, ))
    }

//    @Override
//    public ItemStack quickMoveStack(Player p_39391_, int p_39392_) {
//        return new ItemStack(Items.STONE);
//    }

    @Override
    public void slotsChanged(Container p_39366_) {
        //super.slotsChanged(p_39366_);
        //if (super.getSlot(1) == new Slot())
        //super.setItem(0,1,new ItemStack(Items.STONE));
        //super.getSlot(0).set(new ItemStack(Items.STONE));
//        Slot slot = this.slots.get(0);
//        ctl.LOGGER.info(this.slots.get(1).getItem().getItem().toString());
//        if (this.slots.get(1).getItem().getItem().equals(Items.STONE)){
//            ctl.LOGGER.info("tmp");
//            slot.set(new ItemStack(ItemInit.LEAF.get(),2));
//            this.broadcastChanges();
//        }
        //shaped
        for (ShapedRecipeClass shapedRecipeClass : RecipesShaped) {
            boolean flag = true;
            for (int j = 0; j <= 8; j++) {
                if (this.slots.get(j + 1).getItem().getItem() != shapedRecipeClass.Res.get(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                slots.get(0).set(new ItemStack(shapedRecipeClass.Res.get(9)));
                break;
            } else {
                slots.get(0).set(new ItemStack(Items.AIR));
            }
        }
        //shapeless
        for (int i = 0; i <RecipesShapeless.size(); i++){
            int sum = 0;
            boolean flag = true;
            for (int j = 1; j <= 9; j++){
                if (this.slots.get(j).getItem().getItem() != RecipesShapeless.get(i).item &&
                        this.slots.get(j).getItem().getItem() != Items.AIR){
                    ctl.LOGGER.info(j+"is"+this.slots.get(j).getItem().getItem()+"failed");
                    flag = false;
                    break;
                }
                if (this.slots.get(j).getItem().getItem() == RecipesShapeless.get(i).item){
                    sum++;
                }
            }
            ctl.LOGGER.info("shapeless,"+sum+" "+RecipesShapeless.get(i).item);
            flag = sum == RecipesShapeless.get(i).num;
            if (flag){
                this.slots.get(0).set(new ItemStack(RecipesShapeless.get(i).result,RecipesShapeless.get(i).num));
                break;
            }else {
                slots.get(0).set(new ItemStack(Items.AIR));
            }
        }
        this.broadcastChanges();
    }

    static {
        Item a = Items.CLAY_BALL;
        Item n = Items.AIR;
        Item b = ItemInit.FIBRE.get();
        Item s = Items.STICK;
        RecipesShaped.add(new ShapedRecipeClass(
                a,a,n,
                a,n,n,
                n,n,n,
                ItemInit.UNFIRED_CLAY_AXE.get()));

        RecipesShaped.add(new ShapedRecipeClass(
                a,a,n,
                n,n,n,
                n,n,n,
                ItemInit.UNFIRED_CLAY_HOE.get()));

        RecipesShaped.add(new ShapedRecipeClass(
                a,a,a,
                n,n,n,
                n,n,n,
                ItemInit.UNFIRED_CLAY_PICKAXE.get()));

        RecipesShaped.add(new ShapedRecipeClass(
                n,a,n,
                n,n,n,
                n,n,n,
                ItemInit.UNFIRED_CLAY_SHOVEL.get()));

        RecipesShaped.add(new ShapedRecipeClass(
                n,a,n,
                n,a,n,
                n,n,n,
                ItemInit.UNFIRED_CLAY_SWORD.get()));

        RecipesShaped.add(new ShapedRecipeClass(
                b,b,b,
                b,n,b,
                b,b,b,
                ItemInit.ROPE.get()));

        RecipesShaped.add(new ShapedRecipeClass(
                s,s,s,
                s,n,s,
                s,n,s,
                ItemInit.RUBY_BLOCK.get()));

        RecipesShapeless.add(new ShapelessRecipeClass(ItemInit.DRY_PLIABLE_BRANCH.get(), 2,ItemInit.FIBRE.get()));
    }
}
