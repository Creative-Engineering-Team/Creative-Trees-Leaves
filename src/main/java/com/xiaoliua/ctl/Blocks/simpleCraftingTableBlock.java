package com.xiaoliua.ctl.Blocks;

import com.google.common.graph.Network;
import com.xiaoliua.ctl.Menus.simpleCraftingTableMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class simpleCraftingTableBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public simpleCraftingTableBlock() {
        super(Properties.copy(Blocks.CRAFTING_TABLE));
    }
    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        if (!p_60504_.isClientSide) {
//            p_60506_.openMenu(new SimpleMenuProvider((a,b,c) ->{
//                return new CraftingMenu(a,b,ContainerLevelAccess.create(p_60504_,p_60505_));
//            },Component.literal("t")));
            NetworkHooks.openScreen((ServerPlayer) p_60506_,new SimpleMenuProvider(
                    (id, inventory, playerEntity) -> new simpleCraftingTableMenu(id, inventory),
                    Component.translatable("container.crafting")
            ),p_60505_);
            return InteractionResult.SUCCESS;
        }
        return super.use(p_60503_,p_60504_,p_60505_,p_60506_,p_60507_,p_60508_);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
        return this.defaultBlockState().setValue(FACING,p_49820_.getHorizontalDirection());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(FACING);
    }
//
//    @Nullable
//    @Override
//    public MenuProvider getMenuProvider(BlockState p_60563_, Level p_60564_, BlockPos p_60565_) {
//        return new  SimpleMenuProvider((ta,tb,tc)->{
//            return new CraftingMenu(ta,tb, ContainerLevelAccess.create(p_60564_,p_60565_));
//        }, Component.literal( "Simple Crafting"));
//    }
}