package jcm2606.thaumicmachina.core.proxy;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import jcm2606.thaumicmachina.ThaumicMachina;
import jcm2606.thaumicmachina.core.TMCreativeTab;
import jcm2606.thaumicmachina.core.TMObjects;
import jcm2606.thaumicmachina.core.config.Config;
import jcm2606.thaumicmachina.core.event.PlayerEventHandler;
import jcm2606.thaumicmachina.core.handler.GuiHandler;
import jcm2606.thaumicmachina.core.implement.IProxyBase;
import jcm2606.thaumicmachina.item.node.ItemNodeAugmentation;
import jcm2606.thaumicmachina.research.ResearchHelper;
import jcm2606.thaumicmachina.wand.WandHelper;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationChargeBuffer;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationContactDischarge;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationTaintCapping;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationTaintedCore;
import jcm2606.thaumicmachina.wand.augmentation.AugmentationVisChannel;
import jcm2606.thaumicmachina.wand.augmentation.FluxWandTrigger;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.common.config.ConfigBlocks;

public class ProxyCommon implements IProxyBase {

    /*
     * WARNING - Removed try catching itself - possible behavior change.
     */
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Config config = new Config(event);
        try {
            config.config.load();
            config.loadConfig();
        } catch (Exception e) {
            ThaumicMachina.log.error("Thaumic Machina had a problem loading its configuration file");
        } finally {
            config.config.save();
        }
        ThaumicMachina.tab = new TMCreativeTab();
        ItemNodeAugmentation.loadAugmentations();
        this.registerWandAugmentations();
        WandTriggerRegistry.registerWandBlockTrigger(new FluxWandTrigger(), 1, ConfigBlocks.blockFluxGas, 0);
        WandTriggerRegistry.registerWandBlockTrigger(new FluxWandTrigger(), 1, ConfigBlocks.blockFluxGoo, 0);
        TMObjects.loadObjects();
        this.registerEntities();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(ThaumicMachina.instance, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        ResearchHelper.loadResearch();
    }

    public void registerWandAugmentations() {
        WandHelper.registerAugmentation(new AugmentationChargeBuffer());
        WandHelper.registerAugmentation(new AugmentationContactDischarge());
        WandHelper.registerAugmentation(new AugmentationTaintCapping());
        WandHelper.registerAugmentation(new AugmentationTaintedCore());
        WandHelper.registerAugmentation(new AugmentationVisChannel());
    }

    public void registerEntities() {}
}
