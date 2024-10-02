package com.xiaoliua.ctl;

import blusunrize.immersiveengineering.api.EnumMetals;
import blusunrize.immersiveengineering.common.register.IEItems;
import com.xiaoliua.ctl.Blocks.BlockInit;
import com.xiaoliua.ctl.Items.ItemInit;
import mekanism.common.registries.MekanismItems;
import mekanism.common.resource.PrimaryResource;
import mekanism.common.resource.ResourceType;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

public class ctlRecipeProvider extends RecipeProvider {
    public ctlRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> p_251297_) {
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ItemInit.LEAF.get(),2)
                .requires(ItemInit.PLIABLE_BRANCH.get())
                .unlockedBy("has_leaf",has(ItemInit.LEAF.get()))
                .save(p_251297_);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.FIBRE.get(),3)
                .requires(ItemInit.DRY_PLIABLE_BRANCH.get(),2)
                .unlockedBy("has_dry_pliable_branch",has(ItemInit.DRY_PLIABLE_BRANCH.get()))
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.UNFIRED_CLAY_AXE.get())
                .pattern("aa ")
                .pattern("a  ")
                .pattern("   ")
                .define('a',Items.CLAY_BALL)
                .unlockedBy("has_clay_ball",has(Items.CLAY_BALL))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.UNFIRED_CLAY_HOE.get())
                .pattern("aa ")
                .pattern("   ")
                .pattern("   ")
                .define('a',Items.CLAY_BALL)
                .unlockedBy("has_clay_ball",has(Items.CLAY_BALL))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.UNFIRED_CLAY_PICKAXE.get())
                .pattern("aaa")
                .pattern("   ")
                .pattern("   ")
                .define('a',Items.CLAY_BALL)
                .unlockedBy("has_clay_ball",has(Items.CLAY_BALL))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.UNFIRED_CLAY_SHOVEL.get())
                .pattern(" a ")
                .pattern("   ")
                .pattern("   ")
                .define('a',Items.CLAY_BALL)
                .unlockedBy("has_clay_ball",has(Items.CLAY_BALL))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemInit.UNFIRED_CLAY_SWORD.get())
                .pattern(" a ")
                .pattern(" a ")
                .pattern("   ")
                .define('a',Items.CLAY_BALL)
                .unlockedBy("has_clay_ball",has(Items.CLAY_BALL))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("iii")
                .pattern("www")
                .pattern("iii")
                .define('i',MekanismItems.BRONZE_INGOT)
                .define('w', TagsInit.Items.Planks)
                .unlockedBy("has_iron_ingot",has(Items.IRON_INGOT))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,BlockInit.SIMPLE_CRAFTING_TABLE.get())
                .pattern("ps")
                .pattern("ss")
                .define('p',Items.LEATHER)
                .define('s',Items.STICK)
                .unlockedBy("has_stick",has(Items.STICK))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ItemInit.BONFIRE_BLOCK_ITEM.get())
                .pattern(" g ")
                .pattern("sgs")
                .pattern("s s")
                .define('g',ItemInit.DRY_PLIABLE_BRANCH.get())
                .define('s',Items.STICK)
                .unlockedBy("has_dry_pliable_branch",has(ItemInit.DRY_PLIABLE_BRANCH.get()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ItemInit.POTTERY_SHOVEL.get())
                .pattern("a")
                .pattern("b")
                .pattern("c")
                .define('a',ItemInit.UNASSEMBLED_POTTERY_SHOVEL.get())
                .define('b',ItemInit.ROPE.get())
                .define('c',Items.STICK)
                .unlockedBy("has_rope",has(ItemInit.ROPE.get()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ItemInit.POTTERY_AXE.get())
                .pattern("a")
                .pattern("b")
                .pattern("c")
                .define('a',ItemInit.UNASSEMBLED_POTTERY_AXE.get())
                .define('b',ItemInit.ROPE.get())
                .define('c',Items.STICK)
                .unlockedBy("has_rope",has(ItemInit.ROPE.get()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ItemInit.POTTERY_SWORD.get())
                .pattern("a")
                .pattern("b")
                .pattern("c")
                .define('a',ItemInit.UNASSEMBLED_POTTERY_SWORD.get())
                .define('b',ItemInit.ROPE.get())
                .define('c',Items.STICK)
                .unlockedBy("has_rope",has(ItemInit.ROPE.get()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ItemInit.POTTERY_HOE.get())
                .pattern("a")
                .pattern("b")
                .pattern("c")
                .define('a',ItemInit.UNASSEMBLED_POTTERY_HOE.get())
                .define('b',ItemInit.ROPE.get())
                .define('c',Items.STICK)
                .unlockedBy("has_rope",has(ItemInit.ROPE.get()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ItemInit.POTTERY_PICKAXE.get())
                .pattern("a")
                .pattern("b")
                .pattern("c")
                .define('a',ItemInit.UNASSEMBLED_POTTERY_PICKAXE.get())
                .define('b',ItemInit.ROPE.get())
                .define('c',Items.STICK)
                .unlockedBy("has_rope",has(ItemInit.ROPE.get()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ItemInit.SIMPLE_FLINT_AND_STEEL.get())
                .pattern("s ")
                .pattern(" s")
                .define('s',Items.FLINT)
                .unlockedBy("has_flint",has(Items.FLINT))
                .save(p_251297_);

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ItemInit.UNFIRED_CLAY_AXE.get()),RecipeCategory.MISC,
                        ItemInit.UNASSEMBLED_POTTERY_AXE.get(), 0f,1200)
                .unlockedBy("has_clay_ball",has(Items.CLAY_BALL))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ItemInit.NUGGET_TIN_AND_COPPER.get())
                .pattern("ccc")
                .pattern("ctc")
                .pattern("ccc")
                .define('c', IEItems.Metals.NUGGETS.get(EnumMetals.COPPER))
                .define('t', Objects.requireNonNull(MekanismItems.PROCESSED_RESOURCES.get(ResourceType.NUGGET, PrimaryResource.TIN)))
                .unlockedBy("has_nugget_copper",has(IEItems.Metals.NUGGETS.get(EnumMetals.COPPER)))
                .save(p_251297_);

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ItemInit.NUGGET_TIN_AND_COPPER.get()),RecipeCategory.MISC,
                MekanismItems.BRONZE_INGOT, 0f,14000)
                .unlockedBy("has_nugget_tin_and_copper",has(ItemInit.NUGGET_TIN_AND_COPPER.get()))
                .save(p_251297_);
    }
}
