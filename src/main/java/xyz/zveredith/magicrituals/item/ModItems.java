package xyz.zveredith.magicrituals.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.zveredith.magicrituals.MagicRituals;

import static xyz.zveredith.magicrituals.block.ModBlocks.*;

public class ModItems {

    public static BasicChalk basicChalk;
    public static ItemBase ritualActivator;
    public static CenterChalk centerChalk;
    public static BasicChalk airChalk;
    public static BasicChalk fireChalk;
    public static BasicChalk earthChalk;
    public static BasicChalk waterChalk;
    public static BasicChalk vacuousChalk;

    public static void init() {
        basicChalk = register((BasicChalk) new BasicChalk("itemBasicChalk", basicChalkMark).setCreativeTab(MagicRituals.ritualTab));
        ritualActivator = register(new ItemBase("itemRitualActivator").setCreativeTab(MagicRituals.ritualTab));
        centerChalk = register((CenterChalk) new CenterChalk().setCreativeTab(MagicRituals.ritualTab));
        airChalk = register((BasicChalk) new BasicChalk("itemAirChalk", airChalkMark).setCreativeTab(MagicRituals.ritualTab));
        fireChalk = register((BasicChalk) new BasicChalk("itemFireChalk", fireChalkMark).setCreativeTab(MagicRituals.ritualTab));
        earthChalk = register((BasicChalk) new BasicChalk("itemEarthChalk", earthChalkMark).setCreativeTab(MagicRituals.ritualTab));
        waterChalk = register((BasicChalk) new BasicChalk("itemWaterChalk", waterChalkMark).setCreativeTab(MagicRituals.ritualTab));
        vacuousChalk = register((BasicChalk) new BasicChalk("itemVacuousChalk", vacuousChalkMark).setCreativeTab(MagicRituals.ritualTab));
    }

    private static <temp extends Item> temp register(temp item) {
        GameRegistry.register(item);

        if (item instanceof ItemBase) {
            ((ItemBase) item).registerItemModel();
        }
        return item;
    }
}
