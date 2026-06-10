
package jcm2606.thaumicmachina.wand.augmentation;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.items.wands.ItemWandCasting;

public class AugmentationContactDischarge implements IAugmentationWand {

    public static final AugmentationContactDischarge INSTANCE = new AugmentationContactDischarge();

    @Override
    public String getID() {
        return "contact_discharge";
    }

    @Override
    public String getAliasID() {
        return "Contact Discharge";
    }

    @Override
    public String getAugmentationResearch() {
        return "WAND_AUGMENTATION_CONTACT_DISCHARGE";
    }

    @Override
    public String getRecipeName() {
        return "TM_WA_CONTACT_DISCHARGE";
    }

    @Override
    public int getInfusionInstability() {
        return 14;
    }

    @Override
    public AspectList getInfusionAspects() {
        return new AspectList().add(Aspect.DARKNESS, 8)
            .add(Aspect.WEAPON, 32)
            .add(Aspect.TOOL, 16)
            .add(Aspect.MAGIC, 24)
            .add(Aspect.ENERGY, 16);
    }

    @Override
    public ItemStack[] getInfusionComponents() {
        return new ItemStack[] { new ItemStack(Blocks.cactus, 1) };
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
    public void handleAugmentation(ItemStack stack, ItemWandCasting wand) {}
}
