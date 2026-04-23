
package jcm2606.thaumicmachina.core.crafting.infusion;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import jcm2606.thaumicmachina.core.TMObjects;
import jcm2606.thaumicmachina.core.helper.NBTHelper;
import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import jcm2606.thaumicmachina.wand.WandHelper;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.items.wands.ItemWandCasting;

public class RecipeInfusionWandAugmentation extends InfusionRecipe {

    public IAugmentationWand augmentation;
    public boolean isResearchRecipe;

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
        ItemStack stack = ItemApi.getItem((String) "itemWandCasting", (int) Short.MAX_VALUE);
        if (research && augmentation.getPrerequisiteAugmentations() != null) {
            stack = WandHelper.addAugmentationsTo(stack, augmentation.getPrerequisiteAugmentations(), true);
        }
        return stack;
    }

    public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
        if (this.getRecipeInput() == null) {
            return false;
        }
        if (this.research.length() > 0 && !ThaumcraftApiHelper
            .isResearchComplete((String) player.getCommandSenderName(), (String) this.research)) {
            return false;
        }
        if (WandHelper.hasAugmentation(central, this.augmentation)) {
            return false;
        }
        boolean b = false;
        if (this.augmentation.getPrerequisiteAugmentations() != null) {
            for (IAugmentationWand augmentation_ : this.augmentation.getPrerequisiteAugmentations()) {
                if (WandHelper.hasAugmentation(central, augmentation_)) continue;
                b = true;
            }
        }
        if (b) {
            return false;
        }
        if (!(this.getRecipeInput()
            .getItem() instanceof ItemWandCasting)) {
            return false;
        }
        ArrayList<ItemStack> ii = new ArrayList<ItemStack>();
        for (ItemStack is : input) {
            ii.add(is.copy());
        }
        for (ItemStack comp : this.getComponents()) {
            b = false;
            for (int a = 0; a < ii.size(); ++a) {
                ItemStack i2 = ((ItemStack) ii.get(a)).copy();
                if (comp.getItemDamage() == Short.MAX_VALUE) {
                    i2.setItemDamage(Short.MAX_VALUE);
                }
                if (!this.areItemStacksEqualCustom(i2, comp, false)) continue;
                ii.remove(a);
                b = true;
                break;
            }
            if (b) continue;
            return false;
        }
        if (ii.size() == 0) {
            // empty if block
        }
        return ii.size() == 0;
    }

    private static ItemStack[] getComponents(ItemStack[] augmentationComponents) {
        ArrayList<ItemStack> componentsList = new ArrayList<ItemStack>();
        componentsList.add(new ItemStack(TMObjects.wandAugmentationCore));
        componentsList.add(ItemApi.getItem((String) "itemResource", (int) 14));
        componentsList.add(ItemApi.getItem((String) "itemResource", (int) 15));
        for (ItemStack stack : augmentationComponents) {
            componentsList.add(stack);
        }
        ItemStack[] components = new ItemStack[componentsList.size()];
        for (int i = 0; i < componentsList.size(); ++i) {
            components[i] = (ItemStack) componentsList.get(i);
        }
        return components;
    }

    public ItemStack getRecipeOutput(ItemStack inputStack) {
        ItemStack stack = inputStack.copy();
        NBTTagCompound compound = NBTHelper.getCompoundFor(stack);
        NBTTagList list = new NBTTagList();
        if (compound.getTagList("Augmentations", 8) != null) {
            list = compound.getTagList("Augmentations", 8);
        }
        NBTTagString tag = new NBTTagString(this.augmentation.getAugmentationName());
        list.appendTag((NBTBase) tag);
        compound.setTag("Augmentations", (NBTBase) list);
        return stack;
    }

    private boolean areItemStacksEqualCustom(ItemStack stack0, ItemStack stack1, boolean fuzzy) {
        ItemStack[] ores;
        int od;
        if (stack0 == null && stack1 != null) {
            return false;
        }
        if (stack0 != null && stack1 == null) {
            return false;
        }
        if (stack0 == null && stack1 == null) {
            return true;
        }
        boolean t1 = ThaumcraftApiHelper.areItemStackTagsEqualForCrafting((ItemStack) stack0, (ItemStack) stack1);
        if (!t1) {
            return false;
        }
        if (fuzzy && (od = OreDictionary.getOreID((ItemStack) stack0)) != -1
            && ThaumcraftApiHelper.containsMatch(
                (boolean) false,
                (ItemStack[]) new ItemStack[] { stack1 },
                (ItemStack[]) (ores = OreDictionary.getOres((Integer) od)
                    .toArray(new ItemStack[0])))) {
            return true;
        }
        return stack0.getItem() == stack1.getItem();
    }
}
