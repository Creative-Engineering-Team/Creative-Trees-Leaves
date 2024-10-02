package com.xiaoliua.ctl.Blocks;

import com.xiaoliua.ctl.Items.unassembledClayItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class bonfireBlockEntity extends BlockEntity {
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    private final int[] cookingTime = new int[1];
    private final int[] cookingProgress = new int[1];
    private final RecipeManager.CachedCheck<Container, CampfireCookingRecipe> quickCheck = RecipeManager.createCheck(RecipeType.CAMPFIRE_COOKING);
    public bonfireBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityInit.BONFIRE_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    public static void cookTick(Level p_155307_, BlockPos p_155308_, BlockState p_155309_, bonfireBlockEntity p_155310_) {
        if (p_155309_.getValue(bonfireBlock.IGNITABLE)) {
            p_155307_.setBlock(p_155308_, p_155309_.setValue(bonfireBlock.IGNITABLE, false)
                    .setValue(bonfireBlock.HAS_FUEL,false), 11);
        }
        //ctl.LOGGER.debug("bonfire tick");
        boolean flag = false;

        for(int i = 0; i < p_155310_.items.size(); ++i) {
            ItemStack itemstack = p_155310_.items.get(i);
            if (!itemstack.isEmpty()) {
                flag = true;
                int j = p_155310_.cookingProgress[i]++;
                if (p_155310_.cookingProgress[i] >= p_155310_.cookingTime[i]) {
                    Container container = new SimpleContainer(itemstack);
                    ItemStack itemstack1 = p_155310_.quickCheck.getRecipeFor(container, p_155307_)
                            .map((p_270054_) -> p_270054_.assemble(container, p_155307_.registryAccess())).orElse(itemstack);
                    if (itemstack1.isItemEnabled(p_155307_.enabledFeatures())) {
                        if (itemstack1.getItem().asItem() instanceof  unassembledClayItems){
                            if (Math.random() < 0.15){
                                CompoundTag tag = itemstack1.getOrCreateTag();
                                tag.putBoolean(unassembledClayItems.DURABILITY_PENALTY_TAG,true);
                            }
                        }
                        //Containers.dropItemStack(p_155307_, p_155308_.getX(), p_155308_.getY(), p_155308_.getZ(), itemstack1);
                        //p_155310_.items.set(i, ItemStack.EMPTY);
                        p_155307_.sendBlockUpdated(p_155308_, p_155309_, p_155309_, 11);
                        p_155307_.gameEvent(GameEvent.BLOCK_CHANGE, p_155308_, GameEvent.Context.of(p_155309_));
                        p_155307_.setBlock(p_155308_,p_155309_.setValue(bonfireBlock.HAS_FUEL,false)
                                .setValue(bonfireBlock.LIT,false).setValue(bonfireBlock.COMPLETED,true)
                                .setValue(bonfireBlock.IGNITABLE,false),11);
                    }
                }
            }
        }

        if (flag) {
            setChanged(p_155307_, p_155308_, p_155309_);
        }

    }

    public static void out(Level p_155307_, BlockPos p_155308_, BlockState p_155309_, bonfireBlockEntity p_155310_){
        if (!p_155309_.getValue(bonfireBlock.COMPLETED)){
            ItemStack itemstack = p_155310_.items.get(0);
            Containers.dropItemStack(p_155307_, p_155308_.getX(), p_155308_.getY(), p_155308_.getZ(), itemstack);
        } else {
            ItemStack itemstack = p_155310_.items.get(0);
            Container container = new SimpleContainer(itemstack);
            ItemStack itemstack1 = p_155310_.quickCheck.getRecipeFor(container, p_155307_).map((p_270054_) ->
                    p_270054_.assemble(container, p_155307_.registryAccess())).orElse(itemstack);
            Containers.dropItemStack(p_155307_, p_155308_.getX(), p_155308_.getY(), p_155308_.getZ(), itemstack1);
        }
        p_155310_.items.set(0, ItemStack.EMPTY);
    }

    public static void particleTick(Level p_155319_, BlockPos p_155320_, BlockState p_155321_, bonfireBlockEntity p_155322_) {
        RandomSource randomsource = p_155319_.random;
        if (randomsource.nextFloat() < 0.11F) {
            for(int i = 0; i < randomsource.nextInt(2) + 2; ++i) {
                CampfireBlock.makeParticles(p_155319_, p_155320_, false, false);
            }
        }

        int l = p_155321_.getValue(CampfireBlock.FACING).get2DDataValue();

        for(int j = 0; j < p_155322_.items.size(); ++j) {
            if (!p_155322_.items.get(j).isEmpty() && randomsource.nextFloat() < 0.2F) {
                Direction direction = Direction.from2DDataValue(Math.floorMod(j + l, 4));
                ///float f = 0.3125F;
                double d0 = (double)p_155320_.getX() + 0.5D - (double)((float)direction.getStepX() * 0.3125F) + (double)((float)direction.getClockWise().getStepX() * 0.3125F);
                double d1 = (double)p_155320_.getY() + 0.5D;
                double d2 = (double)p_155320_.getZ() + 0.5D - (double)((float)direction.getStepZ() * 0.3125F) + (double)((float)direction.getClockWise().getStepZ() * 0.3125F);

                for(int k = 0; k < 4; ++k) {
                    p_155319_.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 5.0E-4D, 0.0D);
                }
            }
        }

    }

    public Optional<CampfireCookingRecipe> getCookableRecipe(ItemStack p_59052_) {
        if (this.level != null) {
            return this.items.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty() :
                    this.quickCheck.getRecipeFor(new SimpleContainer(p_59052_), this.level);
        }
        return Optional.empty();
    }

    public boolean placeFood(@Nullable Entity p_238285_, ItemStack p_238286_, int p_238287_) {
        for(int i = 0; i < this.items.size(); ++i) {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty()) {
                this.cookingTime[i] = p_238287_;
                this.cookingProgress[i] = 0;
                this.items.set(i, p_238286_.split(1));
                if (this.level != null) {
                    this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(p_238285_, this.getBlockState()));
                }
                this.markUpdated();
                return true;
            }
        }

        return false;
    }

    private void markUpdated() {
        this.setChanged();
        Objects.requireNonNull(this.getLevel()).sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public void load(@NotNull CompoundTag p_155312_) {
        super.load(p_155312_);
        this.items.clear();
        ContainerHelper.loadAllItems(p_155312_, this.items);
        if (p_155312_.contains("CookingTimes", 11)) {
            int[] aint = p_155312_.getIntArray("CookingTimes");
            System.arraycopy(aint, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, aint.length));
        }

        if (p_155312_.contains("CookingTotalTimes", 11)) {
            int[] aint1 = p_155312_.getIntArray("CookingTotalTimes");
            System.arraycopy(aint1, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, aint1.length));
        }

    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag p_187486_) {
        super.saveAdditional(p_187486_);
        ContainerHelper.saveAllItems(p_187486_, this.items, true);
        p_187486_.putIntArray("CookingTimes", this.cookingProgress);
        p_187486_.putIntArray("CookingTotalTimes", this.cookingTime);
    }
}
