package xyz.zveredith.magicrituals.item;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.zveredith.magicrituals.MagicRituals;
import xyz.zveredith.magicrituals.block.BlockBase;
import xyz.zveredith.magicrituals.block.ModBlocks;
import xyz.zveredith.magicrituals.client.RitualTab;

import java.awt.*;

import static net.minecraft.item.ItemBlock.setTileEntityNBT;

public class BasicChalk extends ItemBase {

    protected BlockBase block;
    public BasicChalk (String name, BlockBase block) {
        super(name);
        this.block = block;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack item, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = world.getBlockState(pos);
        Block block = iblockstate.getBlock();
        System.out.println("Test0");
        if(!block.isReplaceable(world, pos)) {
            pos = pos.offset(side);
        }
        if(block.canPlaceBlockAt(world, pos)) {
            System.out.println("Test1");
            if(player.canPlayerEdit(pos, side, item) && world.canBlockBePlaced(ModBlocks.basicChalkMark, pos, false, side, (Entity)null, item)) {
                System.out.println("Test2");
                int i = this.getMetadata(item.getMetadata());
                IBlockState iblockstate1 = this.block.getStateForPlacement(world, pos, side, hitX, hitY, hitZ, i, player, item);
                if (placeBlockAt(item, player, world, pos, side, hitX, hitY, hitZ, iblockstate1))
                {
                    System.out.println("Test3");
                    SoundType soundtype = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
                    world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    return EnumActionResult.SUCCESS;
                }
            }
        }
        return EnumActionResult.FAIL;
//        if((side == EnumFacing.UP && (block.isOpaqueCube(iblockstate) && block.isFullCube(iblockstate)) || block.isReplaceable(world, pos)) ) {
//            if (!block.isReplaceable(world, pos))
//            {
//                pos = pos.offset(side);
//            }
//            iblockstate = world.getBlockState(pos.down());
//            block = iblockstate.getBlock();
//            if(!block.isFullCube(iblockstate) || !block.isOpaqueCube(iblockstate)) {
//                return EnumActionResult.FAIL;
//            }
//            int i = this.getMetadata(item.getMetadata());
//            IBlockState iblockstate1 = this.block.getStateForPlacement(world, pos, side, hitX, hitY, hitZ, i, player, item);
//            if(player.canPlayerEdit(pos, side, item) && world.canBlockBePlaced(block), pos, false, side, (Entity)null, item)) {
//                if (placeBlockAt(item, player, world, pos, side, hitX, hitY, hitZ, iblockstate))
//                {
//                    SoundType soundtype = world.getBlockState(pos).getBlock().getSoundType(world.getBlockState(pos), world, pos, player);
//                    world.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
//                }
//            }
//            return EnumActionResult.SUCCESS;
//        }
//        return EnumActionResult.FAIL;

    }

    private boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {
        if (!world.setBlockState(pos, newState, 3)) return false;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == this.block)
        {
            setTileEntityNBT(world, player, pos, stack);
            this.block.onBlockPlacedBy(world, pos, state, player, stack);
        }

        return true;
    }
}
