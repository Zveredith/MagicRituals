package xyz.zveredith.magicrituals;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.zveredith.magicrituals.block.ModBlocks;
import xyz.zveredith.magicrituals.client.RitualTab;
import xyz.zveredith.magicrituals.item.ModItems;
import xyz.zveredith.magicrituals.proxy.CommonProxy;

@Mod(modid = MagicRituals.modID, name = MagicRituals.name, version = MagicRituals.version, acceptedMinecraftVersions = "[1.10.2]")
public class MagicRituals {
    public static final String modID = "magicrituals";
    public static final String name = "Magic Rituals";
    public static final String version = "1.10.2-0.0.1";
    public static final RitualTab ritualTab = new RitualTab();

    @SidedProxy(serverSide = "xyz.zveredith.magicrituals.proxy.CommonProxy", clientSide = "xyz.zveredith.magicrituals.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(modID)
    public static MagicRituals instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.init();
        ModItems.init();
    }
    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) {

    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
