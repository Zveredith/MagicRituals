package xyz.zveredith.magicrituals.block;


import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import xyz.zveredith.magicrituals.tile.TileVacuousChalkMark;

import javax.annotation.Nullable;

public class ChalkMarkVacuous extends ChalkMarkBase {
    public ChalkMarkVacuous() {
        super("blockVacuousChalkMark");
    }
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileVacuousChalkMark tile = (TileVacuousChalkMark) getTileEntity(world, pos);
            IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
            if (heldItem == null) {
                player.setHeldItem(hand, itemHandler.extractItem(0, 1, false));
            }
            else {
                player.setHeldItem(hand, itemHandler.insertItem(0, heldItem, false));
            }
            tile.markDirty();
        }
        return true;
    }
    public TileEntity getTileEntity(IBlockAccess world, BlockPos pos) {
        return world.getTileEntity(pos);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileVacuousChalkMark tile = (TileVacuousChalkMark) getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (stack != null) {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntityInWorld(item);
        }
        super.breakBlock(world, pos, state);
    }

    @Nullable
    @Override
    public TileVacuousChalkMark createTileEntity(World world, IBlockState state) {
        return new TileVacuousChalkMark();
    }
}
