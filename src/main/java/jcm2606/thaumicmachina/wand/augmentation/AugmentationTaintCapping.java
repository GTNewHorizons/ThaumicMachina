
package jcm2606.thaumicmachina.wand.augmentation;

import net.minecraft.item.ItemStack;

import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

public class AugmentationTaintCapping implements IAugmentationWand {

    public static final AugmentationTaintCapping INSTANCE = new AugmentationTaintCapping();

    @Override
    public String getAugmentationName() {
        return "Taint Capping";
    }

    @Override
    public String getAugmentationResearch() {
        return "WAND_AUGMENTATION_TAINT_CAPPING";
    }

    @Override
    public String getRecipeName() {
        return "TM_WA_TAINT_CAPPING";
    }

    @Override
    public int getInfusionInstability() {
        return 12;
    }

    @Override
    public AspectList getInfusionAspects() {
        return new AspectList().add(Aspect.TOOL, 16)
            .add(Aspect.MAGIC, 24)
            .add(Aspect.ENTROPY, 32)
            .add(Aspect.TAINT, 48)
            .add(Aspect.WATER, 32)
            .add(Aspect.AURA, 16);
    }

    @Override
    public ItemStack[] getInfusionComponents() {
        return new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 11),
            new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemNugget, 1, 5) };
    }

    @Override
    public IAugmentationWand[] getPrerequisiteAugmentations() {
        return new IAugmentationWand[] { AugmentationTaintedCore.INSTANCE };
    }

    @Override
    public boolean isCompatibleWith(WandRod rod) {
        return true;
    }

    @Override
    public void handleAugmentation(ItemStack stack, ItemWandCasting wand) {}
}
