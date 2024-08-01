package com.xiaoliua.ctl;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ShapedRecipeClass {
    public List<Item> Res = new ArrayList<>();
    public ShapedRecipeClass(Item i1, Item i2, Item i3, Item i4, Item i5, Item i6, Item i7, Item i8, Item i9, Item i10){
        Res.add(i1);
        Res.add(i2);
        Res.add(i3);
        Res.add(i4);
        Res.add(i5);
        Res.add(i6);
        Res.add(i7);
        Res.add(i8);
        Res.add(i9);
        Res.add(i10);
    }
}
