package com.xiaoliua.ctl.Blocks;

import com.xiaoliua.ctl.Config;
import com.xiaoliua.ctl.Enums.hayrackPuts;
import com.xiaoliua.ctl.ctl;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class hayrackBlockEntity extends BlockEntity {
    private int NeedTick = 0;
    private int LeafNum = 0;
    private String puts;
    public hayrackBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityInit.HAYRACK_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState,hayrackBlockEntity pBlockEntity){
        //pBlockEntity.NeedTick = pState.getValue(hayrackBlock.StartTime);
        //pBlockEntity.LeafNum= pState.getValue(hayrackBlock.EndTimet);
        if (pBlockEntity.LeafOk()) return;
        ctl.LOGGER.info("BLock {} tick", pState.getBlock());
        if (pLevel!=null && !pLevel.isClientSide){
            if (pState.getValue(hayrackBlock.HaveLeaf) && !pState.getValue(hayrackBlock.LeafOK)){
                pBlockEntity.LeafTick();
                ctl.LOGGER.info("hayrack {} change from {} to {}", pState.getBlock(),pBlockEntity.NeedTick+1,
                        pBlockEntity.NeedTick);
                //pLevel.setBlock(pPos,pState,3);
            }
            if (pBlockEntity.LeafOk()){
                pLevel.setBlock(pPos,pState.cycle(hayrackBlock.LeafOK),3);
            }
        }
    }

    public void putLeaf(int leafNum){
        NeedTick = Config.waitTime;
        LeafNum = leafNum;
        puts = "leaf";
        setChanged();
    }

    public void putBranch(int branchNum){
        NeedTick = Config.waitTime;
        LeafNum = branchNum;
        puts = "pliableBranch";
        setChanged();
    }

    public String  putsType(){
        return puts;
    }

    public boolean LeafOk(){
        return NeedTick == 0;
    }

    public void LeafTick(){
        if (LeafOk())return;
        NeedTick--;
        setChanged();
    }

    public int getLeafNum(){
        return LeafNum;
    }

    @Override
    public void load(CompoundTag p_155245_) {
        NeedTick = p_155245_.getInt("need_tick");
        LeafNum = p_155245_.getInt("leaf_num");
        puts = p_155245_.getString("puts_type");
    super.load(p_155245_);
    }

    @Override
    protected void saveAdditional(CompoundTag p_187471_) {
        super.saveAdditional(p_187471_);
        p_187471_.putInt("need_tick",NeedTick);
        p_187471_.putInt("leaf_num",LeafNum);
        p_187471_.putString("puts_type",puts);
    }
}
