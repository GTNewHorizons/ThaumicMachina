
package jcm2606.thaumicmachina.core.proxy;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import jcm2606.thaumicmachina.ThaumicMachina;
import jcm2606.thaumicmachina.core.TMCreativeTab;
import jcm2606.thaumicmachina.core.TMObjects;
import jcm2606.thaumicmachina.core.config.Config;
import jcm2606.thaumicmachina.core.event.PlayerEventHandler;
import jcm2606.thaumicmachina.core.handler.GuiHandler;
import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import jcm2606.thaumicmachina.core.implement.IProxyBase;
import jcm2606.thaumicmachina.item.node.ItemNodeAugmentation;
import jcm2606.thaumicmachina.research.ResearchHelper;
import jcm2606.thaumicmachina.wand.WandHelper;
import jcm2606.thaumicmachina.wand.augmentation.FluxWandTrigger;
import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.common.config.ConfigBlocks;

public class ProxyCommon
implements IProxyBase {
    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Config config = new Config(event);
        try {
            config.config.load();
            config.loadConfig();
        }
        catch (Exception e) {
            ThaumicMachina.log.error("Thaumic Machina had a problem loading it's configuration file");
        }
        finally {
            config.config.save();
        }
        ThaumicMachina.tab = new TMCreativeTab();
        ItemNodeAugmentation.loadAugmentations();
        this.registerWandAugmentations();
        WandTriggerRegistry.registerWandBlockTrigger((IWandTriggerManager)new FluxWandTrigger(), (int)1, (Block)ConfigBlocks.blockFluxGas, (int)0);
        WandTriggerRegistry.registerWandBlockTrigger((IWandTriggerManager)new FluxWandTrigger(), (int)1, (Block)ConfigBlocks.blockFluxGoo, (int)0);
        TMObjects.loadObjects();
        this.registerEntities();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)ThaumicMachina.instance, (IGuiHandler)new GuiHandler());
        MinecraftForge.EVENT_BUS.register((Object)new PlayerEventHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ResearchHelper.loadResearch();
    }

    public void registerWandAugmentations() {
        ThaumicMachina.log.info("Registering Wand Augmentations...");
        try {
            ImmutableSet<ClassPath.ClassInfo> set = ClassPath.from((ClassLoader)this.getClass().getClassLoader()).getTopLevelClassesRecursive("jcm2606.thaumicmachina.wand.augmentation");
            for (ClassPath.ClassInfo cinfo : set) {
                Class<?> clas = Class.forName(cinfo.getName());
                if (!IAugmentationWand.class.isAssignableFrom(clas)) continue;
                ThaumicMachina.log.info("Discovered Wand Augmentation within class '" + cinfo.getName() + "'");
                WandHelper.registerAugmentation((IAugmentationWand)clas.newInstance());
            }
        }
        catch (Exception e) {
            ThaumicMachina.log.error("Augmentation loading has failed!");
            e.printStackTrace();
        }
        ThaumicMachina.log.info("Wand Augmntation registration done");
    }

    public void registerEntities() {
        boolean entityID = false;
    }
}

