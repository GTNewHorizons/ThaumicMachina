
package jcm2606.thaumicmachina.wand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import jcm2606.thaumicmachina.core.crafting.infusion.RecipeInfusionWandAugmentation;
import jcm2606.thaumicmachina.core.helper.NBTHelper;
import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.config.ConfigItems;

public class WandHelper {

    public static final HashMap<String, IAugmentationWand> augmentationMap = new HashMap<>();
    public static final List<String> augmentationIDs = new ArrayList<>();

    public static void registerAugmentation(IAugmentationWand augmentation) {
        augmentationMap.putIfAbsent(augmentation.getID(), augmentation);
        augmentationIDs.add(augmentation.getID());

        if (augmentation.getAliasID() != null) {
            augmentationMap.putIfAbsent(augmentation.getAliasID(), augmentation);
        }
    }

    public static InfusionRecipe buildInfusionRecipe(IAugmentationWand augmentation, boolean isResearchRecipe) {
        return new RecipeInfusionWandAugmentation(augmentation, isResearchRecipe);
    }

    public static ItemStack buildWandItemStackWithAugmentations(IAugmentationWand[] augmentations) {
        ItemStack stack = new ItemStack(ConfigItems.itemWandCasting, 1, Short.MAX_VALUE);
        WandHelper.addAugmentationsTo(stack, augmentations, false);
        return stack;
    }

    public static boolean hasAugmentations(ItemStack stack) {
        return stack.getTagCompound() != null && NBTHelper.getCompoundFor(stack)
            .hasKey("Augmentations", Constants.NBT.TAG_LIST);
    }

    public static boolean hasAugmentation(ItemStack stack, IAugmentationWand augmentation) {
        if (!hasAugmentations(stack)) {
            return false;
        }
        NBTTagList list = NBTHelper.getCompoundFor(stack)
            .getTagList("Augmentations", Constants.NBT.TAG_STRING);

        for (int i = 0; i < list.tagCount(); ++i) {
            String tag = list.getStringTagAt(i);
            if (tag.equals(augmentation.getID()) || tag.equals(augmentation.getAliasID())) {
                return true;
            }
        }
        return false;
    }

    public static List<IAugmentationWand> getAugmentations(ItemStack stack) {
        NBTTagCompound compound;
        List<IAugmentationWand> augmentations = new ArrayList<>();

        if (stack.getTagCompound() != null
            && (compound = NBTHelper.getCompoundFor(stack)).hasKey("Augmentations", Constants.NBT.TAG_LIST)) {
            NBTTagList list = compound.getTagList("Augmentations", Constants.NBT.TAG_STRING);
            for (int i = 0; i < list.tagCount(); ++i) {
                String tag;
                if (list.getStringTagAt(i) == null || (tag = list.getStringTagAt(i)) == null) continue;
                IAugmentationWand augment = augmentationMap.get(tag);
                if (augment == null) continue;
                augmentations.add(augment);
            }
        }

        return augmentations;
    }

    public static ItemStack addAugmentationsTo(ItemStack inputStack, IAugmentationWand[] augmentations,
        boolean duplicateItemStack) {
        ItemStack stack = inputStack;
        if (duplicateItemStack) {
            stack = inputStack.copy();
        }
        NBTTagCompound compound = NBTHelper.getCompoundFor(stack);
        NBTTagList list = compound.getTagList("Augmentations", Constants.NBT.TAG_STRING);
        for (IAugmentationWand augmentation : augmentations) {
            NBTTagString tag = new NBTTagString(augmentation.getID());
            list.appendTag(tag);
        }
        compound.setTag("Augmentations", list);
        return stack;
    }

    public static ItemStack removeAugmentationsFrom(ItemStack inputStack, IAugmentationWand[] augmentations,
        boolean duplicateItemStack) {
        ItemStack stack = inputStack;
        if (duplicateItemStack) {
            stack = inputStack.copy();
        }
        NBTTagCompound compound = NBTHelper.getCompoundFor(stack);
        NBTTagList list = compound.getTagList("Augmentations", Constants.NBT.TAG_STRING);
        for (IAugmentationWand augmentation : augmentations) {
            for (int i = 0; i < list.tagCount(); ++i) {
                String tag = list.getStringTagAt(i);
                if (tag.equals(augmentation.getID()) || tag.equals(augmentation.getAliasID())) {
                    list.removeTag(i);
                    i--;
                }
            }
        }

        compound.setTag("Augmentations", list);
        return stack;
    }
}
