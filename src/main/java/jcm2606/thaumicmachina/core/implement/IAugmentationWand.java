
package jcm2606.thaumicmachina.core.implement;

import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;

public interface IAugmentationWand {
    public String getAugmentationName();

    public String getAugmentationResearch();

    public String getRecipeName();

    public int getInfusionInstability();

    public AspectList getInfusionAspects();

    public ItemStack[] getInfusionComponents();

    public IAugmentationWand[] getPrerequisiteAugmentations();

    public boolean isCompatibleWith(WandRod var1);

    public void handleAugmentation(ItemStack var1, ItemWandCasting var2);
}

