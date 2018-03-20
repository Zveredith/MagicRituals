package xyz.zveredith.magicrituals.ritual;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public interface IRitualBase {
    boolean checkValid(World world, BlockPos pos, EntityPlayer player);

    void activate(World world, BlockPos pos, EntityItem requiredItem, EntityPlayer player);
}
