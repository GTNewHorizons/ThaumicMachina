
package jcm2606.thaumicmachina.wand.augmentation;

import net.minecraft.item.ItemStack;

import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

public class AugmentationVisChannel implements IAugmentationWand {

    public static final AugmentationVisChannel INSTANCE = new AugmentationVisChannel();

    @Override
    public String getID() {
        return "vis_channel";
    }

    @Override
    public String getAliasID() {
        return "Vis Channel";
    }

    @Override
    public String getAugmentationResearch() {
        return "WAND_AUGMENTATION_VIS_CHANNEL";
    }

    @Override
    public String getRecipeName() {
        return "TM_WA_VIS_CHANNEL";
    }

    @Override
    public int getInfusionInstability() {
        return 10;
    }

    @Override
    public AspectList getInfusionAspects() {
        return new AspectList().add(Aspect.TOOL, 16)
            .add(Aspect.MAGIC, 24)
            .add(Aspect.WATER, 64)
            .add(Aspect.ORDER, 48)
            .add(Aspect.METAL, 16);
    }

    @Override
    public ItemStack[] getInfusionComponents() {
        return new ItemStack[] { new ItemStack(ConfigItems.itemResource, 1, 2),
            new ItemStack(ConfigItems.itemResource, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 3) };
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
