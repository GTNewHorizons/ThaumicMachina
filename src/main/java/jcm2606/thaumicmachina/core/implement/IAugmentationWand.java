
package jcm2606.thaumicmachina.core.implement;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;

public interface IAugmentationWand {

    String getAugmentationName();

    String getAugmentationResearch();

    String getRecipeName();

    int getInfusionInstability();

    AspectList getInfusionAspects();

    ItemStack[] getInfusionComponents();

    IAugmentationWand[] getPrerequisiteAugmentations();

    boolean isCompatibleWith(WandRod var1);

    void handleAugmentation(ItemStack var1, ItemWandCasting var2);
}
