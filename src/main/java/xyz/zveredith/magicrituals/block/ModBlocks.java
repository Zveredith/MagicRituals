package xyz.zveredith.magicrituals.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.zveredith.magicrituals.MagicRituals;

public class ModBlocks {

    public static BlockBase basicChalkMark;
    public static void init() {
        basicChalkMark = register(new ChalkMarkBase("blockBasicChalkMark").setCreativeTab(MagicRituals.ritualTab));
    }

    private static <temp extends Block> temp register(temp block, ItemBlock itemBlock) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);
        if (block instanceof BlockBase) {
            ((BlockBase)block).registerItemModel(itemBlock);
        }
        return block;

    }
    private static <T extends Block> T register(T block) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);
    }
}
