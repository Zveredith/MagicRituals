package xyz.zveredith.magicrituals.ritual;

import net.minecraft.init.Blocks;
import xyz.zveredith.magicrituals.block.ModBlocks;

import java.util.ArrayList;

public class RitualComponentList {
    public ArrayList<RitualComponent> ritualAirInfusion;

    public RitualComponentList() {
        ritualAirInfusion = new ArrayList<RitualComponent>();
        ritualAirInfusion.add(new RitualComponent(Blocks.AIR, 1, 1));
        ritualAirInfusion.add(new RitualComponent(ModBlocks.basicChalkMark, 1, 0));
        ritualAirInfusion.add(new RitualComponent(Blocks.AIR, 1, -1));
        ritualAirInfusion.add(new RitualComponent(ModBlocks.basicChalkMark, 0, 1));
        ritualAirInfusion.add(new RitualComponent(ModBlocks.basicChalkMark, 0, -1));
        ritualAirInfusion.add(new RitualComponent(Blocks.AIR, -1, 1));
        ritualAirInfusion.add(new RitualComponent(ModBlocks.basicChalkMark, -1, 0));
        ritualAirInfusion.add(new RitualComponent(Blocks.AIR, -1, -1));

    }


}
