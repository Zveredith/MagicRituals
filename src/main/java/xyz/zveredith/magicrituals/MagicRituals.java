package xyz.zveredith.magicrituals;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import xyz.zveredith.magicrituals.block.ModBlocks;
import xyz.zveredith.magicrituals.client.RitualTab;
import xyz.zveredith.magicrituals.item.ModItems;
import xyz.zveredith.magicrituals.network.PacketRequestUpdateVacuousChalkMark;
import xyz.zveredith.magicrituals.network.PacketUpdateVacuousChalkMark;
import xyz.zveredith.magicrituals.proxy.CommonProxy;
import xyz.zveredith.magicrituals.ritual.Rituals;
import xyz.zveredith.magicrituals.tile.TileVacuousChalkMark;

@Mod(modid = MagicRituals.modID, name = MagicRituals.name, version = MagicRituals.version, acceptedMinecraftVersions = "[1.10.2]")
public class MagicRituals {
    public static final String modID = "magicrituals";
    public static final String name = "Magic Rituals";
    public static final String version = "1.10.2-0.0.1";
    public static final RitualTab ritualTab = new RitualTab();
    public static SimpleNetworkWrapper network;

    @SidedProxy(serverSide = "xyz.zveredith.magicrituals.proxy.CommonProxy", clientSide = "xyz.zveredith.magicrituals.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(modID)
    public static MagicRituals instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(modID);
        network.registerMessage(new PacketUpdateVacuousChalkMark.Handler(), PacketUpdateVacuousChalkMark.class, 0, Side.CLIENT);
        network.registerMessage(new PacketRequestUpdateVacuousChalkMark.Handler(), PacketRequestUpdateVacuousChalkMark.class, 1, Side.SERVER);
        ModBlocks.init();
        ModItems.init();
        GameRegistry.registerTileEntity(TileVacuousChalkMark.class, modID + 0);
        proxy.registerRenderers();
    }
    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) {
        Rituals.init();
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
