package xyz.zveredith.magicrituals.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ChalkMarkBase extends BlockBase {

    public ChalkMarkBase(String name) {
        super(Material.CIRCUITS, name);
    }
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return new AxisAlignedBB(0f, 0f, 0f, 0f, 0f, 0f);
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0f, 0f, 0f, 1f, .0625f, 1f);
    }
    public boolean isFullCube(IBlockState iBlockState) {
        return false;
    }
    public boolean isOpaqueCube(IBlockState iBlockState) {
        return false;
    }
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn)
    {
        if (!world.isRemote)
        {
            if (!this.canPlaceBlockAt(world, pos))
            {
                world.setBlockToAir(pos);
            }
        }
    }
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()).isFullyOpaque();
    }
    @Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
}
