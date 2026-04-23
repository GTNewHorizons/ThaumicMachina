
package jcm2606.thaumicmachina.wand;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

import jcm2606.thaumicmachina.core.crafting.infusion.RecipeInfusionWandAugmentation;
import jcm2606.thaumicmachina.core.helper.NBTHelper;
import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import thaumcraft.api.ItemApi;
import thaumcraft.api.crafting.InfusionRecipe;

public class WandHelper {

    public static final HashMap<String, IAugmentationWand> augmentationMap = new HashMap();

    public static void registerAugmentation(IAugmentationWand augmentation) {
        if (!augmentationMap.containsKey(augmentation.getAugmentationName())) {
            augmentationMap.put(augmentation.getAugmentationName(), augmentation);
        }
    }

    public static InfusionRecipe buildInfusionRecipe(IAugmentationWand augmentation, boolean isResearchRecipe) {
        return new RecipeInfusionWandAugmentation(augmentation, isResearchRecipe);
    }

    public static ItemStack buildWandItemStackWithAugmentations(IAugmentationWand[] augmentations) {
        ItemStack stack = ItemApi.getItem((String) "itemWandCasting", (int) Short.MAX_VALUE);
        WandHelper.addAugmentationsTo(stack, augmentations, false);
        return stack;
    }

    public static boolean hasAugmentations(ItemStack stack) {
        NBTTagCompound compound;
        boolean b = false;
        if (stack.getTagCompound() != null && (compound = NBTHelper.getCompoundFor(stack)).hasKey("Augmentations")) {
            b = true;
        }
        return b;
    }

    public static boolean hasAugmentation(ItemStack stack, IAugmentationWand augmentation) {
        NBTTagCompound compound;
        boolean b = false;
        if (stack.getTagCompound() != null && (compound = NBTHelper.getCompoundFor(stack)).hasKey("Augmentations")) {
            NBTTagList list = compound.getTagList("Augmentations", 8);
            for (int i = 0; i < list.tagCount(); ++i) {
                String tag = list.getStringTagAt(i);
                if (tag == null || !tag.equals(augmentation.getAugmentationName())) continue;
                b = true;
            }
        }
        return b;
    }

    public static String[] getAugmentationNames(ItemStack stack) {
        NBTTagCompound compound;
        String[] slist = null;
        int index = 0;
        if (stack.getTagCompound() != null && (compound = NBTHelper.getCompoundFor(stack)).hasKey("Augmentations")) {
            NBTTagList list = compound.getTagList("Augmentations", 8);
            slist = new String[list.tagCount()];
            for (int i = 0; i < list.tagCount(); ++i) {
                String tag;
                if (list.getStringTagAt(i) == null || (tag = list.getStringTagAt(i)) == null) continue;
                slist[index] = tag;
                ++index;
            }
        }
        return slist;
    }

    public static ItemStack addAugmentationsTo(ItemStack inputStack, IAugmentationWand[] augmentations,
        boolean duplicateItemStack) {
        ItemStack stack = inputStack;
        if (duplicateItemStack) {
            stack = inputStack.copy();
        }
        NBTTagCompound compound = NBTHelper.getCompoundFor(stack);
        NBTTagList list = new NBTTagList();
        if (compound.getTagList("Augmentations", 8) != null) {
            list = compound.getTagList("Augmentations", 8);
        }
        for (IAugmentationWand augmentation : augmentations) {
            NBTTagString tag = new NBTTagString(augmentation.getAugmentationName());
            list.appendTag((NBTBase) tag);
        }
        compound.setTag("Augmentations", (NBTBase) list);
        return stack;
    }

    public static ItemStack removeAugmentationsFrom(ItemStack inputStack, IAugmentationWand[] augmentations,
        boolean duplicateItemStack) {
        ItemStack stack = inputStack;
        if (duplicateItemStack) {
            stack = inputStack.copy();
        }
        NBTTagCompound compound = NBTHelper.getCompoundFor(stack);
        NBTTagList list = new NBTTagList();
        if (compound.getTagList("Augmentations", 8) != null) {
            list = compound.getTagList("Augmentations", 8);
        }
        for (IAugmentationWand augmentation : augmentations) {
            for (int i = 0; i < list.tagCount(); ++i) {
                String tag = list.getStringTagAt(i);
                if (!tag.equals(augmentation.getAugmentationName())) continue;
                list.removeTag(i);
            }
        }
        compound.setTag("Augmentations", (NBTBase) list);
        return stack;
    }
}
