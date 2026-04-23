
package jcm2606.thaumicmachina.wand.augmentation;

import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;

public class AugmentationTaintedCore
implements IAugmentationWand {
    @Override
    public String getAugmentationName() {
        return "Tainted Core";
    }

    @Override
    public String getAugmentationResearch() {
        return "WAND_AUGMENTATION_TAINTED_CORE";
    }

    @Override
    public String getRecipeName() {
        return "TM_WA_TAINTED_CORE";
    }

    @Override
    public int getInfusionInstability() {
        return 8;
    }

    @Override
    public AspectList getInfusionAspects() {
        return new AspectList().add(Aspect.TOOL, 16).add(Aspect.MAGIC, 24).add(Aspect.ENTROPY, 32).add(Aspect.TAINT, 48).add(Aspect.DARKNESS, 48);
    }

    @Override
    public ItemStack[] getInfusionComponents() {
        return new ItemStack[]{ItemApi.getItem((String)"itemResource", (int)11), ItemApi.getItem((String)"itemResource", (int)12), ItemApi.getItem((String)"itemResource", (int)3)};
    }

    @Override
    public IAugmentationWand[] getPrerequisiteAugmentations() {
        return null;
    }

    @Override
    public boolean isCompatibleWith(WandRod rod) {
        return true;
    }

    @Override
    public void handleAugmentation(ItemStack stack, ItemWandCasting wand) {
    }
}

