
package jcm2606.thaumicmachina.client.proxy;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import jcm2606.thaumicmachina.client.event.RenderEventHandler;
import jcm2606.thaumicmachina.client.render.tile.RenderMetaphysical;
import jcm2606.thaumicmachina.client.render.tile.RenderMetaphysicalPlant;
import jcm2606.thaumicmachina.client.render.tile.RenderNodeTransmodifier;
import jcm2606.thaumicmachina.core.proxy.ProxyCommon;
import jcm2606.thaumicmachina.tile.TileNodeTransmodifier;
import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysical;
import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysicalRose;

public class ProxyClient extends ProxyCommon {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ClientRegistry.bindTileEntitySpecialRenderer(TileNodeTransmodifier.class, new RenderNodeTransmodifier());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMetaphysical.class, new RenderMetaphysical());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMetaphysicalRose.class, new RenderMetaphysicalPlant());
        MinecraftForge.EVENT_BUS.register(new RenderEventHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

}
