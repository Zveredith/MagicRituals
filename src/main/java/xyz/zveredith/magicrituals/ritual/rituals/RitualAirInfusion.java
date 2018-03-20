package xyz.zveredith.magicrituals.ritual.rituals;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.zveredith.magicrituals.item.ModItems;
import xyz.zveredith.magicrituals.ritual.IRitualBase;
import xyz.zveredith.magicrituals.ritual.RitualComponent;
import xyz.zveredith.magicrituals.ritual.Rituals;

import java.util.ArrayList;
import java.util.List;

public class RitualAirInfusion implements IRitualBase {
    private ArrayList<RitualComponent> components;

    public RitualAirInfusion() {
        this.components = Rituals.componentList.ritualAirInfusion;
    }
    @Override
    public boolean checkValid(World world, BlockPos pos, EntityPlayer player) {
        if(!(world.isRemote)) {
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            List itemEntities = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1));
            if(itemEntities.isEmpty()) {
                return false;
            }
            boolean hasRequiredItem = false;
            EntityItem item = null;
            for(int i = 0; i < itemEntities.size(); i++) {
                item = (EntityItem) itemEntities.get(i);
                if(item.getEntityItem().getItem() == ModItems.basicChalk) {
                    hasRequiredItem = true;
                    break;
                }
            }
            if(!hasRequiredItem) {
                return false;
            }
            for(int i = 0; i < components.size(); i++) {
                RitualComponent tempComponent = components.get(i);
                int xOffset = tempComponent.getXOffset();
                int zOffset = tempComponent.getZOffset();
                Block block = tempComponent.getType();
                if(!(world.getBlockState(pos.add(xOffset, 0, zOffset)).getBlock() == block)) {
                    return false;
                }
            }
            activate(world, pos, item, player);
            return true;
        }
        return false;
    }
    @Override
    public void activate(World world, BlockPos pos, EntityItem requiredItem, EntityPlayer player) {
        world.removeEntity(requiredItem);
        world.destroyBlock(pos, false);
        for(int i = 0; i < components.size(); i++) {
            RitualComponent component = components.get(i);
            world.destroyBlock(pos.add(component.getXOffset(), 0, component.getZOffset()), false);
        }
        world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.airChalk, 1)));

    }

}
