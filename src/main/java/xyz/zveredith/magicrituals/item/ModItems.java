package xyz.zveredith.magicrituals.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.zveredith.magicrituals.MagicRituals;

import static xyz.zveredith.magicrituals.block.ModBlocks.basicChalkMark;

public class ModItems {

    public static BasicChalk basicChalk;

    public static void init() {
        basicChalk = register((BasicChalk) new BasicChalk("itemBasicChalk", basicChalkMark).setCreativeTab(MagicRituals.ritualTab));
    }

    private static <temp extends Item> temp register(temp item) {
        GameRegistry.register(item);

        if (item instanceof ItemBase) {
            ((ItemBase) item).registerItemModel();
        }
        return item;
    }
}
