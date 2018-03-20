package xyz.zveredith.magicrituals.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import xyz.zveredith.magicrituals.MagicRituals;
import xyz.zveredith.magicrituals.client.render.block.RenderVacuousChalkMark;
import xyz.zveredith.magicrituals.tile.TileVacuousChalkMark;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(MagicRituals.modID + ":" + id, "inventory"));
    }
    @Override
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileVacuousChalkMark.class, new RenderVacuousChalkMark());
    }
}
