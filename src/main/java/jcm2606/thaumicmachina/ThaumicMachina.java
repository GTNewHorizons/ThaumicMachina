
package jcm2606.thaumicmachina;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import jcm2606.thaumicmachina.command.TMCommand;
import jcm2606.thaumicmachina.core.TMCreativeTab;
import jcm2606.thaumicmachina.core.config.Config;
import jcm2606.thaumicmachina.core.proxy.ProxyCommon;
import net.minecraft.command.ICommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid="ThaumicMachina", name="Thaumic Machina", dependencies="required-after:Thaumcraft")
public class ThaumicMachina {
    public static final String MOD_ID = "ThaumicMachina";
    public static final String MOD_NAME = "Thaumic Machina";
    @Mod.Instance(value="ThaumicMachina")
    public static ThaumicMachina instance;
    @SidedProxy(clientSide="jcm2606.thaumicmachina.client.proxy.ProxyClient", serverSide="jcm2606.thaumicmachina.core.proxy.ProxyCommon")
    public static ProxyCommon proxy;
    public static TMCreativeTab tab;
    public static final Logger log;
    public static Config config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        log.info("Mod loading has commenced, please be patient...");
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
        log.info("Mod loading has finished");
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new TMCommand());
    }

    static {
        log = LogManager.getLogger((String)MOD_NAME);
    }
}

