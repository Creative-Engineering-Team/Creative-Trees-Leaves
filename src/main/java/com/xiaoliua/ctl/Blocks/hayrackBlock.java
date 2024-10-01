package com.xiaoliua.ctl.Blocks;

import com.xiaoliua.ctl.Items.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class hayrackBlock extends BaseEntityBlock{
    //public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty HaveLeaf = BooleanProperty.create("have_leaf");
    public static final BooleanProperty LeafOK = BooleanProperty.create("leaf_ok");
    //public static final BooleanProperty EndTimet = BooleanProperty.create("start_time");
    //public static final IntegerProperty StartTime = IntegerProperty.create("start_timet",0,70);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    //public static final IntegerProperty hayrack_gets_num = IntegerProperty.create("hayrack_gets_num",0,100);
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.99D, 16.0D);
    protected hayrackBlock() {
        super(Properties.of().strength(3f).noOcclusion().sound(SoundType.WOOD)
                .requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY).forceSolidOff());
        this.registerDefaultState(this.defaultBlockState().setValue(HaveLeaf,false));
        this.registerDefaultState(this.defaultBlockState().setValue(LeafOK,false));
        //this.registerDefaultState(this.defaultBlockState().setValue(StartTime,0));
        //this.registerDefaultState(this.defaultBlockState().setValue(EndTimet,true));`
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return SHAPE;
    }

//    @Override
//    public void setPlacedBy(Level p_49847_, BlockPos p_49848_, BlockState p_49849_, @Nullable LivingEntity p_49850_, ItemStack p_49851_) {
//        if (p_49850_ != null){
//            p_49847_.setBlock(p_49848_,p_49849_.setValue(FACING, getFacing(p_49848_,p_49850_)),3);
//        }
//        super.setPlacedBy(p_49847_, p_49848_, p_49849_, p_49850_, p_49851_);
//    }
//
//    public Direction getFacing(BlockPos pos, LivingEntity entity){
//        Vec3 vec = entity.position();
//        return Direction.getFacingAxis()
//    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        return this.defaultBlockState().setValue(FACING,p_49820_.getHorizontalDirection());
    }

    public BlockState rotate(BlockState p_54125_, Rotation p_54126_) {
        return p_54125_.setValue(FACING, p_54126_.rotate(p_54125_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_54122_, Mirror p_54123_) {
        return p_54122_.rotate(p_54123_.getRotation(p_54122_.getValue(FACING)));
    }

//    @Override
//    public void randomTick(BlockState p_222954_, ServerLevel p_222955_, BlockPos p_222956_, RandomSource p_222957_) {
//        ctl.LOGGER.info("BLock{}tick", p_222954_.getBlock());
//        if (p_222954_.getValue(HaveLeaf) && !p_222954_.getValue(LeafOK)){
//            p_222955_.setBlock(p_222956_,p_222954_.setValue(EndTime,p_222954_.getValue(EndTime)-1),3);
//        }
//        if (!p_222954_.getValue(LeafOK) && p_222954_.getValue(EndTime) == 0){
//            p_222955_.setBlock(p_222956_,p_222954_.cycle(LeafOK),3);
//        }
//        super.randomTick(p_222954_, p_222955_, p_222956_, p_222957_);
//    }

//    @Override
//    public void tick(BlockState p_222945_, ServerLevel p_222946_, BlockPos p_222947_, RandomSource p_222948_) {
//        ctl.LOGGER.info("BLock{}tick", p_222945_.getBlock());
//        if (p_222945_.getValue(HaveLeaf) && !p_222945_.getValue(LeafOK)){
//            p_222946_.setBlock(p_222947_,p_222945_.setValue(EndTime,p_222945_.getValue(EndTime)-1),3);
//        }
//        if (!p_222945_.getValue(LeafOK) && p_222945_.getValue(EndTime) == 0){
//            p_222946_.setBlock(p_222947_,p_222945_.cycle(LeafOK),3);
//        }
//        super.tick(p_222945_, p_222946_, p_222947_, p_222948_);
//    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        //p_60506_.sendSystemMessage(Component.nullToEmpty("hello"+p_60506_.getStringUUID()+p_60506_.getMainHandItem()+ p_60506_.getMainHandItem().getCount()));
        if (p_60504_.isClientSide || p_60507_ != InteractionHand.MAIN_HAND){
            return InteractionResult.PASS;
        }
        if (p_60503_.getValue(HaveLeaf) && p_60503_.getValue(LeafOK)){
            if (!p_60506_.getMainHandItem().is(Items.AIR)) return InteractionResult.PASS;
            var hayrackBlockEntity = (com.xiaoliua.ctl.Blocks.hayrackBlockEntity)p_60504_.getBlockEntity(p_60505_);
            //p_60506_.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(ItemInit.DRY_LEAF.get(),64));
            if (Objects.equals(hayrackBlockEntity.putsType(), "leaf")) {
                p_60506_.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemInit.DRY_LEAF.get(), hayrackBlockEntity.getLeafNum()));
            }else {
                p_60506_.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemInit.DRY_PLIABLE_BRANCH.get(), hayrackBlockEntity.getLeafNum()));
            }
            p_60504_.setBlock(p_60505_,p_60503_.cycle(HaveLeaf).cycle(LeafOK),3);
            //p_60504_.setBlock(p_60505_,p_60503_.cycle(LeafOK),3);
            return InteractionResult.SUCCESS;
        }else if (p_60506_.getMainHandItem().getItem() == ItemInit.LEAF.get()){
            //p_60506_.sendSystemMessage(Component.nullToEmpty("ttt"));
            var hayrackBlockEntity = (com.xiaoliua.ctl.Blocks.hayrackBlockEntity)p_60504_.getBlockEntity(p_60505_);
            hayrackBlockEntity.putLeaf(p_60506_.getMainHandItem().getCount());
            p_60506_.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(Items.AIR));
            p_60504_.setBlock(p_60505_,p_60503_.cycle(HaveLeaf),3);
            return InteractionResult.SUCCESS;
            //
        }else if (p_60506_.getMainHandItem().getItem() == ItemInit.PLIABLE_BRANCH.get()){
            var hayrackBlockEntity = (com.xiaoliua.ctl.Blocks.hayrackBlockEntity)p_60504_.getBlockEntity(p_60505_);
            hayrackBlockEntity.putBranch(p_60506_.getMainHandItem().getCount());
            p_60506_.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(Items.AIR));
            p_60504_.setBlock(p_60505_,p_60503_.cycle(HaveLeaf),3);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
//        private static final VoxelShape SHAPE =
//            Block.box(0,0,0,16,16,16);
//
//    @Override
//    public @NotNull VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
//        return SHAPE;
//    }
//
//    @Nullable
//    @Override
//    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
//        return this.defaultBlockState().setValue(FACING,p_49820_.getHorizontalDirection().getOpposite());
//    }
//
//    @Override
//    public BlockState rotate(BlockState p_54125_, Rotation p_54126_) {
//        return p_54125_.setValue(FACING,p_54126_.rotate(p_54125_.getValue(FACING)));
//    }
//
//    @Override
//    public BlockState mirror(BlockState p_54122_, Mirror p_54123_) {
//        return p_54122_.rotate(p_54123_.getRotation(p_54122_.getValue(FACING)));
//    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(HaveLeaf);
        p_49915_.add(FACING);
        p_49915_.add(LeafOK);
        //p_49915_.add(StartTime);
        //p_49915_.add(EndTimet);
        //p_49915_.add(hayrack_gets_num);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new hayrackBlockEntity(p_153215_,p_153216_);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return p_153212_.isClientSide ? null : createTickerHelper(p_153214_,BlockEntityInit.HAYRACK_BLOCK_ENTITY.get(),
                hayrackBlockEntity::serverTick);
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
    public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, BlockState p_60518_, boolean p_60519_) {
        if (!p_60518_.is(BlockInit.HAYRACK_BLOCK.get())) {
            ItemStack itemStack = null;
            if (p_60515_.getValue(HaveLeaf)) {
                if (p_60515_.getValue(LeafOK)) {
                    var hayrackBlockEntity = (com.xiaoliua.ctl.Blocks.hayrackBlockEntity) p_60516_.getBlockEntity(p_60517_);
                    //p_60506_.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(ItemInit.DRY_LEAF.get(),64));
                    if (Objects.equals(hayrackBlockEntity.putsType(), "leaf")) {
                        itemStack = new ItemStack(ItemInit.DRY_LEAF.get(), hayrackBlockEntity.getLeafNum());
                    } else {
                        itemStack = new ItemStack(ItemInit.DRY_PLIABLE_BRANCH.get(), hayrackBlockEntity.getLeafNum());
                    }
                } else {
                    var hayrackBlockEntity = (com.xiaoliua.ctl.Blocks.hayrackBlockEntity) p_60516_.getBlockEntity(p_60517_);
                    //p_60506_.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(ItemInit.DRY_LEAF.get(),64));
                    if (Objects.equals(hayrackBlockEntity.putsType(), "leaf")) {
                        itemStack = new ItemStack(ItemInit.LEAF.get(), hayrackBlockEntity.getLeafNum());
                    } else {
                        itemStack = new ItemStack(ItemInit.PLIABLE_BRANCH.get(), hayrackBlockEntity.getLeafNum());
                    }
                }
            }
            if (itemStack != null) {
                Containers.dropItemStack(p_60516_, p_60517_.getX(), p_60517_.getY(), p_60517_.getZ(), itemStack);
            }
        }
        super.onRemove(p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
    }
}
