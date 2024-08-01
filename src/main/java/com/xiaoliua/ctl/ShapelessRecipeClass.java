package com.xiaoliua.ctl;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ShapelessRecipeClass {
    public Item item,result;
    public int num;
    public ShapelessRecipeClass(Item item, int num, Item result){
        this.num=num;
        this.item=item;
        this.result=result;
    }
}
