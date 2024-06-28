package com.xiaoliua.ctl.Blocks;

import com.xiaoliua.ctl.ctl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class hayrackBlockEntity extends BlockEntity {
    private int NeedTick = 0;
    public hayrackBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityInit.HAYRACK_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState,hayrackBlockEntity pBlockEntity){
        pBlockEntity.NeedTick = pState.getValue(hayrackBlock.EndTime);
        if (pBlockEntity.LeafOk()) return;
        ctl.LOGGER.info("BLock {} tick", pState.getBlock());
        if (pLevel!=null && !pLevel.isClientSide){
            if (pState.getValue(hayrackBlock.HaveLeaf) && !pState.getValue(hayrackBlock.LeafOK)){
                pBlockEntity.LeafTick();
                ctl.LOGGER.info("hayrack {} change from {} to {}", pState.getBlock(),pBlockEntity.NeedTick+1,
                        pBlockEntity.NeedTick);
                pLevel.setBlock(pPos,pState.setValue(hayrackBlock.EndTime,pBlockEntity.NeedTick),3);
            }
            if (pBlockEntity.LeafOk()){
                pLevel.setBlock(pPos,pState.cycle(hayrackBlock.LeafOK),3);
            }
        }
    }

    public void putLeaf(){
        NeedTick = 100;
        setChanged();
    }

    public boolean LeafOk(){
        return NeedTick == 0;
    }

    public void LeafTick(){
        if (LeafOk())return;
        NeedTick--;
        setChanged();
    }
}
