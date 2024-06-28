package com.xiaoliua.ctl;

import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.Items.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ctlRecipeProvider extends RecipeProvider {
    public ctlRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_251297_) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.FLINT)
                .requires(Items.GRAVEL,3)
                .unlockedBy("has_gravel",has(Items.GRAVEL))
                .save(p_251297_);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GOLDEN_APPLE,9)
                .requires(Items.ENCHANTED_GOLDEN_APPLE)
                .unlockedBy("has_enchanted_golden_apple",has(Items.ENCHANTED_GOLDEN_APPLE))
                .save(p_251297_);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ENCHANTED_GOLDEN_APPLE)
                .requires(Items.GOLDEN_APPLE,9)
                .unlockedBy("has_golden_apple",has(Items.GOLDEN_APPLE))
                .save(p_251297_);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.FIBRE.get(),3)
                .requires(ItemInit.DRY_LEAF.get(),2)
                .unlockedBy("has_dry_leaf",has(ItemInit.DRY_LEAF.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.HAYRACK_BLOCK.get())
                .pattern("aaa")
                .pattern("a a")
                .pattern("a a")
                .define('a',Items.STICK)
                .unlockedBy("has_stick",has(Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.ROPE.get())
                .pattern("aaa")
                .pattern("a a")
                .pattern("aaa")
                .define('a',ItemInit.FIBRE.get())
                .unlockedBy("has_fibre",has(ItemInit.FIBRE.get()))
                .save(p_251297_);
    }
}
