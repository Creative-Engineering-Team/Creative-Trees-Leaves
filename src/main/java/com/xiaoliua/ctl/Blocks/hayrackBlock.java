package com.xiaoliua.ctl.Blocks;

import com.xiaoliua.ctl.Items.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class hayrackBlock extends BaseEntityBlock {
    //public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty HaveLeaf = BooleanProperty.create("have_leaf");
    public static final BooleanProperty LeafOK = BooleanProperty.create("leaf_ok");
    //public static final BooleanProperty EndTimet = BooleanProperty.create("start_time");
    //public static final IntegerProperty StartTime = IntegerProperty.create("start_timet",0,70);
    //public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    //public static final IntegerProperty hayrack_gets_num = IntegerProperty.create("hayrack_gets_num",0,100);
    protected hayrackBlock() {
        super(Properties.of().strength(5f).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(HaveLeaf,false));
        this.registerDefaultState(this.defaultBlockState().setValue(LeafOK,false));
        //this.registerDefaultState(this.defaultBlockState().setValue(StartTime,0));
        //this.registerDefaultState(this.defaultBlockState().setValue(EndTimet,true));
    }

//    public BlockState rotate(BlockState p_54125_, Rotation p_54126_) {
//        return p_54125_.setValue(FACING, p_54126_.rotate(p_54125_.getValue(FACING)));
//    }
//
//    public BlockState mirror(BlockState p_54122_, Mirror p_54123_) {
//        return p_54122_.rotate(p_54123_.getRotation(p_54122_.getValue(FACING)));
//    }

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
            p_60506_.setItemSlot(EquipmentSlot.MAINHAND,new ItemStack(ItemInit.DRY_LEAF.get(),hayrackBlockEntity.getLeafNum()));
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
        //p_49915_.add(FACING);
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
}
