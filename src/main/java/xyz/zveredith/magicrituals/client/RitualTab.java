package xyz.zveredith.magicrituals.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import xyz.zveredith.magicrituals.MagicRituals;
import xyz.zveredith.magicrituals.item.ModItems;

public class RitualTab extends CreativeTabs {
    public RitualTab() {
        super(MagicRituals.modID);
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.basicChalk;
    }
}
