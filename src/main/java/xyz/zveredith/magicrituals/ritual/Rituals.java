package xyz.zveredith.magicrituals.ritual;

import xyz.zveredith.magicrituals.ritual.rituals.RitualAirInfusion;

import java.util.ArrayList;

public class Rituals {
    public static RitualComponentList componentList;
    public static RitualAirInfusion ritualAirInfusion;
    public static ArrayList<IRitualBase> ritualList;
    public static void init() {
        componentList = new RitualComponentList();
        ritualList = new ArrayList<IRitualBase>();
        ritualAirInfusion = new RitualAirInfusion();
        ritualList.add(ritualAirInfusion);
    }
}
