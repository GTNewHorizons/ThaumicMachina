
package jcm2606.thaumicmachina.core.crafting.infusion;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.oredict.OreDictionary;

import jcm2606.thaumicmachina.core.TMObjects;
import jcm2606.thaumicmachina.core.helper.NBTHelper;
import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import jcm2606.thaumicmachina.wand.WandHelper;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

public class RecipeInfusionWandAugmentation extends InfusionRecipe {

    public final IAugmentationWand augmentation;
    public final boolean isResearchRecipe;

    public RecipeInfusionWandAugmentation(IAugmentationWand augmentation, boolean isResearchRecipe) {
        super(
            "@" + augmentation.getAugmentationResearch(),
            null,
            augmentation.getInfusionInstability(),
            augmentation.getInfusionAspects(),
            RecipeInfusionWandAugmentation.buildStackIfResearch(augmentation, isResearchRecipe),
            RecipeInfusionWandAugmentation.getComponents(augmentation.getInfusionComponents()));
        this.augmentation = augmentation;
        this.isResearchRecipe = isResearchRecipe;
    }

    private static ItemStack buildStackIfResearch(IAugmentationWand augmentation, boolean research) {
        ItemStack stack = new ItemStack(ConfigItems.itemWandCasting, 1, Short.MAX_VALUE);
        IAugmentationWand[] prereqs = augmentation.getPrerequisiteAugmentations();
        if (research && prereqs != null) {
            stack = WandHelper.addAugmentationsTo(stack, prereqs, true);
        }
        return stack;
    }

    @Override
    public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
        if (this.getRecipeInput() == null) {
            return false;
        }
        if (!this.research.isEmpty()
            && !ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), this.research)) {
            return false;
        }
        if (WandHelper.hasAugmentation(central, this.augmentation)) {
            return false;
        }
        if (this.augmentation.getPrerequisiteAugmentations() != null) {
            for (IAugmentationWand augmentation_ : this.augmentation.getPrerequisiteAugmentations()) {
                if (WandHelper.hasAugmentation(central, augmentation_)) continue;
                return false;
            }
        }
        if (!(this.getRecipeInput()
            .getItem() instanceof ItemWandCasting)) {
            return false;
        }
        ArrayList<ItemStack> items = new ArrayList<>();
        for (ItemStack is : input) {
            items.add(is.copy());
        }
        outer: for (ItemStack comp : this.getComponents()) {
            for (int a = 0; a < items.size(); a++) {
                ItemStack item = items.get(a)
                    .copy();
                if (comp.getItemDamage() == Short.MAX_VALUE) {
                    item.setItemDamage(Short.MAX_VALUE);
                }
                if (areItemStacksEqualCustom(item, comp, false)) {
                    items.remove(a);
                    continue outer;
                }
            }
            return false;
        }
        return items.isEmpty();
    }

    private static ItemStack[] getComponents(ItemStack[] augmentationComponents) {
        ArrayList<ItemStack> componentsList = new ArrayList<>();
        componentsList.add(new ItemStack(TMObjects.wandAugmentationCore));
        componentsList.add(new ItemStack(ConfigItems.itemResource, 1, 14));
        componentsList.add(new ItemStack(ConfigItems.itemResource, 1, 15));
        componentsList.addAll(Arrays.asList(augmentationComponents));
        ItemStack[] components = new ItemStack[componentsList.size()];
        for (int i = 0; i < componentsList.size(); ++i) {
            components[i] = componentsList.get(i);
        }
        return components;
    }

    @Override
    public ItemStack getRecipeOutput(ItemStack inputStack) {
        ItemStack stack = inputStack.copy();
        NBTTagCompound compound = NBTHelper.getCompoundFor(stack);
        NBTTagList list = compound.getTagList("Augmentations", Constants.NBT.TAG_STRING);
        list.appendTag(new NBTTagString(this.augmentation.getID()));
        compound.setTag("Augmentations", list);
        return stack;
    }

    private static boolean areItemStacksEqualCustom(ItemStack stack0, ItemStack stack1, boolean fuzzy) {
        if (stack0 == null && stack1 != null || stack0 != null && stack1 == null) {
            return false;
        }
        if (stack0 == null) {
            return true;
        }
        if (!ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(stack0, stack1)) {
            return false;
        }
        for (int oreID : OreDictionary.getOreIDs(stack0)) {
            String s = OreDictionary.getOreName(oreID);
            OreDictionary.getOres(s);
            if (ThaumcraftApiHelper.containsMatch(
                false,
                new ItemStack[] { stack1 },
                OreDictionary.getOres(s)
                    .toArray(new ItemStack[0]))) {
                return true;
            }
        }

        return stack0.getItem() == stack1.getItem();
    }
}
