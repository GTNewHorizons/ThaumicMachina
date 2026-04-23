
package jcm2606.thaumicmachina.core.implement;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public interface IProxyBase {

    void preInit(FMLPreInitializationEvent var1);

    void init(FMLInitializationEvent var1);

    void postInit(FMLPostInitializationEvent var1);

    interface IProxyClient extends IProxyBase {

        void loadRendering();
    }
}
