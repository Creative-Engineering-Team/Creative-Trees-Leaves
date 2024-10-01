package com.xiaoliua.ctl.Blocks;

import com.xiaoliua.ctl.Items.unassembledClayItems;
import com.xiaoliua.ctl.ctl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.ToIntFunction;

public class bonfireBlock extends BaseEntityBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty IGNITABLE = BooleanProperty.create("ignitable");
    public static final BooleanProperty HAS_FUEL = BooleanProperty.create("fuel");
    public static final BooleanProperty COMPLETED = BooleanProperty.create("completed");
    public static final boolean HurtFrostWalker = false;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    protected bonfireBlock() {
        super(Properties.of().mapColor(MapColor.PODZOL).instrument(NoteBlockInstrument.BASS)
                .strength(2.0F).sound(SoundType.WOOD).lightLevel(litBlockEmission(15))
                .noOcclusion().ignitedByLava().pushReaction(PushReaction.DESTROY).forceSolidOff());
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, Boolean.FALSE).setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED,false).setValue(HAS_FUEL,false).setValue(IGNITABLE,false)
                .setValue(COMPLETED,false));

    }

    private static ToIntFunction<BlockState> litBlockEmission(int p_50760_) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? p_50760_ : 0;
        };
    }

    public static void hurtByFire(BlockState state, Level world, BlockPos pos, Entity entity,boolean ignoreLit,int damage){
        if (entity == null){
            return;
        }
        if ((ignoreLit || state.getValue(LIT))&& entity instanceof LivingEntity){
            if (EnchantmentHelper.hasFrostWalker((LivingEntity) entity) && !HurtFrostWalker){
                return;
            }
            entity.hurt(world.damageSources().inFire(),damage);
        }
    }

    public static void dowse(@javax.annotation.Nullable Entity p_152750_, LevelAccessor p_152751_, BlockPos p_152752_, BlockState p_152753_) {
        if (p_152751_.isClientSide()) {
//            for(int i = 0; i < 20; ++i) {
//                makeParticles((Level)p_152751_, p_152752_, p_152753_.getValue(SIGNAL_FIRE), true);
//            }
        }

        BlockEntity blockentity = p_152751_.getBlockEntity(p_152752_);
        if (blockentity instanceof CampfireBlockEntity) {
            ((CampfireBlockEntity) blockentity).dowse();
        }

        p_152751_.gameEvent(p_152750_, GameEvent.BLOCK_CHANGE, p_152752_);
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        LevelAccessor levelaccessor = p_49820_.getLevel();
        BlockPos blockpos = p_49820_.getClickedPos();
        boolean flag = levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER;
        return super.defaultBlockState().setValue(LIT,false).setValue(FACING,p_49820_.getHorizontalDirection())
                .setValue(WATERLOGGED,flag).setValue(HAS_FUEL,false);
    }

    @Override
    public @NotNull BlockState rotate(BlockState p_60530_, Rotation p_60531_) {
        return p_60530_.setValue(FACING, p_60531_.rotate(p_60530_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_60528_, Mirror p_60529_) {
        return p_60528_.rotate(p_60529_.getRotation(p_60528_.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(LIT,FACING,WATERLOGGED,HAS_FUEL,IGNITABLE,COMPLETED);
        //super.createBlockStateDefinition(p_49915_);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos p_153215_, @NotNull BlockState p_153216_) {
        return new bonfireBlockEntity(p_153215_,p_153216_);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public void entityInside(BlockState p_60495_, Level p_60496_, BlockPos p_60497_, Entity p_60498_) {
        hurtByFire(p_60495_,p_60496_,p_60497_,p_60498_,false,1);
        super.entityInside(p_60495_, p_60496_, p_60497_, p_60498_);
    }

    @Override
    public BlockState updateShape(BlockState p_60541_, Direction p_60542_, BlockState p_60543_, LevelAccessor p_60544_, BlockPos p_60545_, BlockPos p_60546_) {
        if (p_60541_.getValue(WATERLOGGED)){
            p_60544_.scheduleTick(p_60545_,Fluids.WATER,Fluids.WATER.getTickDelay(p_60544_));
        }
        return super.updateShape(p_60541_, p_60542_, p_60543_, p_60544_, p_60545_, p_60546_);
    }

//    @Override
//    public boolean placeLiquid(LevelAccessor p_51257_, BlockPos p_51258_, BlockState p_51259_, FluidState p_51260_) {
//        if (!p_51259_.getValue(BlockStateProperties.WATERLOGGED) && p_51260_.getType() == Fluids.WATER) {
//            boolean flag = p_51259_.getValue(LIT);
//            if (flag) {
//                if (!p_51257_.isClientSide()) {
//                    p_51257_.playSound(null, p_51258_, SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.BLOCKS, 1.0F, 1.0F);
//                }
//
//                dowse(null, p_51257_, p_51258_, p_51259_);
//            }
//
//            p_51257_.setBlock(p_51258_, p_51259_.setValue(WATERLOGGED, Boolean.valueOf(true)).setValue(LIT, Boolean.valueOf(false)), 3);
//            p_51257_.scheduleTick(p_51258_, p_51260_.getType(), p_51260_.getType().getTickDelay(p_51257_));
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public void onProjectileHit(Level p_51244_, BlockState p_51245_, BlockHitResult p_51246_, Projectile p_51247_) {
        BlockPos blockpos = p_51246_.getBlockPos();
        if (!p_51244_.isClientSide && p_51247_.isOnFire() && p_51247_.mayInteract(p_51244_, blockpos) && !p_51245_.getValue(LIT) && !p_51245_.getValue(WATERLOGGED)) {
            p_51244_.setBlock(blockpos, p_51245_.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
        }
    }

    @Override
    public FluidState getFluidState(BlockState p_51318_) {
        return p_51318_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_51318_);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult result) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (state.getValue(COMPLETED)){
            level.setBlock(pos, state.setValue(COMPLETED, false), 3);
            if (!player.getMainHandItem().getItem().equals(Items.STICK) && !(player.getMainHandItem().getItem() instanceof DiggerItem) &&
                    !(player.getMainHandItem().getItem() instanceof TieredItem)){
                hurtByFire(state,level,pos,player,true,3);
                hurtByFire(state,level,pos,player,true,3);
                hurtByFire(state,level,pos,player,true,3);
            }
            bonfireBlockEntity bonfireBlockEntity = (com.xiaoliua.ctl.Blocks.bonfireBlockEntity) blockEntity;
            com.xiaoliua.ctl.Blocks.bonfireBlockEntity.out(level,pos,state,bonfireBlockEntity);
            return InteractionResult.SUCCESS;
        } else if (player.getMainHandItem().is(Items.STICK) && !state.getValue(HAS_FUEL)){
            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(HAS_FUEL, true), 3);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player,
                        state.setValue(HAS_FUEL, true)));
                //player.getMainHandItem().copy();
                player.setItemSlot(EquipmentSlot.MAINHAND,player.getMainHandItem().
                        copyWithCount(player.getMainHandItem().getCount()-1));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }else if (blockEntity instanceof bonfireBlockEntity bonfireblockentity && state.getValue(HAS_FUEL) &&
                (bonfireblockentity.getCookableRecipe(player.getMainHandItem()).isPresent() ||
                        player.getMainHandItem().getItem() instanceof unassembledClayItems)){
            ItemStack itemStack = player.getMainHandItem();
            Optional<CampfireCookingRecipe> optional = bonfireblockentity.getCookableRecipe(itemStack);
            if (optional.isPresent()){
                if (!level.isClientSide && bonfireblockentity.placeFood(player,player.getAbilities().instabuild ?
                        itemStack.copy() : itemStack,optional.get().getCookingTime())){
                    level.setBlock(pos,state.setValue(IGNITABLE,true),11);
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.CONSUME;
            }else if (itemStack.getItem().asItem() instanceof unassembledClayItems){
                if (!level.isClientSide && bonfireblockentity.placeFood(player,player.getAbilities().instabuild ?
                        itemStack.copy() : itemStack, ctl.RANDOM.nextInt(45,60))){
                    level.setBlock(pos,state.setValue(IGNITABLE,true),3);
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.sidedSuccess(true);
        }
        return InteractionResult.PASS;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        if (p_153212_.isClientSide){
            return p_153213_.getValue(LIT) ? createTickerHelper(p_153214_,BlockEntityInit.BONFIRE_BLOCK_ENTITY.get(),
                    bonfireBlockEntity::particleTick) : null;
        }else {
            return p_153213_.getValue(LIT) ? createTickerHelper(p_153214_,BlockEntityInit.BONFIRE_BLOCK_ENTITY.get()
                    ,bonfireBlockEntity::cookTick) : null;
        }
    }

    @Override
    public void animateTick(BlockState p_220918_, Level p_220919_, BlockPos p_220920_, RandomSource p_220921_) {
        if (p_220918_.getValue(LIT)) {
            if (p_220921_.nextInt(10) == 0) {
                p_220919_.playLocalSound((double)p_220920_.getX() + 0.5D, (double)p_220920_.getY() + 0.5D, (double)p_220920_.getZ() + 0.5D, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 0.5F + p_220921_.nextFloat(), p_220921_.nextFloat() * 0.7F + 0.6F, false);
            }
            if (p_220921_.nextInt(5) == 0) {
                for(int i = 0; i < p_220921_.nextInt(1) + 1; ++i) {
                    p_220919_.addParticle(ParticleTypes.LAVA, (double)p_220920_.getX() + 0.5D, (double)p_220920_.getY() + 0.5D, (double)p_220920_.getZ() + 0.5D, p_220921_.nextFloat() / 2.0F, 5.0E-5D, p_220921_.nextFloat() / 2.0F);
                }
            }
        }
    }
//    @Override
//    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
//        //ctl.LOGGER.debug("nc" + level.getFluidState(fromPos).getType()+level.getBlockState(fromPos));
//        if (level.getFluidState(fromPos).getType() == Fluids.FLOWING_WATER || level.getFluidState(fromPos).getType() == Fluids.WATER) {
//            breakBlockAndDropItem(level, pos, state);
//        }
//    }

    private void breakBlockAndDropItem(Level level, BlockPos pos, BlockState state) {
        //ctl.LOGGER.debug("nc1");
        Block.dropResources(state, level, pos);
        level.removeBlock(pos, false);
    }

    @Override
    public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, @NotNull BlockState p_60518_, boolean p_60519_) {
        ctl.LOGGER.debug("{} {} {} {} {}", p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
        if (!p_60518_.is(BlockInit.BONFIRE_BLOCK.get())){
            bonfireBlockEntity bonfireBlockEntity = (com.xiaoliua.ctl.Blocks.bonfireBlockEntity) p_60516_.getBlockEntity(p_60517_);
            com.xiaoliua.ctl.Blocks.bonfireBlockEntity.out(p_60516_,p_60517_,p_60515_,bonfireBlockEntity);
        }
        super.onRemove(p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
    }
}
