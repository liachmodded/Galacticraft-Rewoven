package com.hrznstudio.galacticraft.blocks.machines.compressor;

import com.hrznstudio.galacticraft.api.blocks.AbstractHorizontalDirectionalBlock;
import com.hrznstudio.galacticraft.container.GalacticraftContainers;
import com.hrznstudio.galacticraft.util.Rotatable;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.ChatFormat;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class CompressorBlock extends AbstractHorizontalDirectionalBlock implements Rotatable, BlockEntityProvider {
    public CompressorBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new CompressorBlockEntity();
    }

    @Override
    public final void buildTooltip(ItemStack itemStack_1, BlockView blockView_1, List<Component> list_1, TooltipContext tooltipContext_1) {
        if (Screen.hasShiftDown()) {
            list_1.add(new TranslatableComponent(getTooltipKey()).setStyle(new Style().setColor(ChatFormat.GRAY)));
        } else {
            list_1.add(new TranslatableComponent("tooltip.galacticraft-rewoven.press_shift").setStyle(new Style().setColor(ChatFormat.GRAY)));
        }
    }

    protected String getTooltipKey() {
        return "tooltip.galacticraft-rewoven.compressor";
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState_1) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public final boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient) {
            return true;
        }

        openContainer(playerEntity, blockPos);
        return true;
    }

    protected void openContainer(PlayerEntity playerEntity, BlockPos blockPos) {
        ContainerProviderRegistry.INSTANCE.openContainer(GalacticraftContainers.COMPRESSOR_CONTAINER, playerEntity, packetByteBuf -> packetByteBuf.writeBlockPos(blockPos));
    }

    @Override
    public void onBreak(World world, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity) {
        super.onBreak(world, blockPos, blockState, playerEntity);

        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity != null) {
            if (blockEntity instanceof CompressorBlockEntity) {
                CompressorBlockEntity be = (CompressorBlockEntity) blockEntity;

                for (int i = 0; i < be.inventory.getSlotCount(); i++) {
                    ItemStack itemStack = be.inventory.getInvStack(i);

                    if (itemStack != null) {
                        world.spawnEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), itemStack));
                    }
                }
            }
        }
    }

}