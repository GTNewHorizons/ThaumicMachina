
package jcm2606.thaumicmachina.wand.augmentation;

import net.minecraft.item.ItemStack;

import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;

public class AugmentationChargeBuffer implements IAugmentationWand {

    public static final AugmentationChargeBuffer INSTANCE = new AugmentationChargeBuffer();

    @Override
    public String getID() {
        return "charge_buffer";
    }

    @Override
    public String getAliasID() {
        return "Charge Buffer";
    }

    @Override
    public String getAugmentationResearch() {
        return "WAND_AUGMENTATION_CHARGE_BUFFER";
    }

    @Override
    public String getRecipeName() {
        return "TM_WA_CHARGE_BUFFER";
    }

    @Override
    public int getInfusionInstability() {
        return 12;
    }

    @Override
    public AspectList getInfusionAspects() {
        return new AspectList().add(Aspect.TOOL, 16)
            .add(Aspect.MAGIC, 24)
            .add(Aspect.ENERGY, 32)
            .add(Aspect.VOID, 48);
    }

    @Override
    public ItemStack[] getInfusionComponents() {
        return new ItemStack[] { new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1) };
    }

    @Override
    public void handleAugmentation(ItemStack stack, ItemWandCasting wand) {}

    @Override
    public IAugmentationWand[] getPrerequisiteAugmentations() {
        return null;
    }

    @Override
    public boolean isCompatibleWith(WandRod rod) {
        return true;
    }
}
