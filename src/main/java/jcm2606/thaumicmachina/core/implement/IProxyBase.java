
package jcm2606.thaumicmachina.core.implement;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IProxyBase {

    public void preInit(FMLPreInitializationEvent var1);

    public void init(FMLInitializationEvent var1);

    public void postInit(FMLPostInitializationEvent var1);

    public static interface IProxyClient extends IProxyBase {

        public void loadRendering();
    }
}
