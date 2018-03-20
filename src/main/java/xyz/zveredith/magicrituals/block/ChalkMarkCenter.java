package xyz.zveredith.magicrituals.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.zveredith.magicrituals.MagicRituals;
import xyz.zveredith.magicrituals.item.ModItems;
import xyz.zveredith.magicrituals.ritual.Rituals;

import javax.annotation.Nullable;

public class ChalkMarkCenter extends ChalkMarkBase {
    public ChalkMarkCenter() {
        super("blockCenterChalkMark");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(heldItem != null && heldItem.getItem() == ModItems.ritualActivator) {
            for (int i = 0; i < Rituals.ritualList.size(); i++) {
                if (Rituals.ritualList.get(i).checkValid(world, pos, player)) {
                    return true;
                }
            }
        }
        return true;
    }
}
