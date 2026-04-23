
package jcm2606.thaumicmachina.client.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import jcm2606.thaumicmachina.client.event.RenderEventHandler;
import jcm2606.thaumicmachina.client.render.tile.RenderMetaphysical;
import jcm2606.thaumicmachina.client.render.tile.RenderMetaphysicalPlant;
import jcm2606.thaumicmachina.client.render.tile.RenderNodeTransmodifier;
import jcm2606.thaumicmachina.core.implement.IProxyBase;
import jcm2606.thaumicmachina.core.proxy.ProxyCommon;
import jcm2606.thaumicmachina.tile.TileNodeTransmodifier;
import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysical;
import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysicalRose;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.common.MinecraftForge;

public class ProxyClient
extends ProxyCommon
implements IProxyBase.IProxyClient {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ClientRegistry.bindTileEntitySpecialRenderer(TileNodeTransmodifier.class, (TileEntitySpecialRenderer)new RenderNodeTransmodifier());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMetaphysical.class, (TileEntitySpecialRenderer)new RenderMetaphysical());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMetaphysicalRose.class, (TileEntitySpecialRenderer)new RenderMetaphysicalPlant());
        MinecraftForge.EVENT_BUS.register((Object)new RenderEventHandler());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public void loadRendering() {
    }
}

