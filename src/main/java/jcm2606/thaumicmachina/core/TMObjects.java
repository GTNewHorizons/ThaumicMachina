
package jcm2606.thaumicmachina.core;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import jcm2606.thaumicmachina.block.BlockNodeTransmodifier;
import jcm2606.thaumicmachina.block.metaphysical.BlockMetaphysicalBrick;
import jcm2606.thaumicmachina.block.metaphysical.BlockMetaphysicalRose;
import jcm2606.thaumicmachina.core.crafting.infusion.RecipeInfusionWandAugmentation;
import jcm2606.thaumicmachina.core.implement.IAugmentationWand;
import jcm2606.thaumicmachina.item.node.ItemNodeAugmentation;
import jcm2606.thaumicmachina.item.wand.ItemWandAugmentationCore;
import jcm2606.thaumicmachina.item.wand.rod.ItemWandCore;
import jcm2606.thaumicmachina.wand.WandHelper;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigResearch;

public class TMObjects {

    public static Block nodeTransmodifier;
    public static Block metaphysicalBrick;
    public static Block metaphysicalRose;
    public static Item nodeAugmentation;
    public static Item wandAugmentationCore;
    public static Item wandCore;
    public static WandRod rodCelestial;

    public static void loadObjects() {
        nodeTransmodifier = new BlockNodeTransmodifier("nodeTransmodifier", Material.rock);
        metaphysicalBrick = new BlockMetaphysicalBrick();
        metaphysicalRose = new BlockMetaphysicalRose();
        nodeAugmentation = new ItemNodeAugmentation();
        wandAugmentationCore = new ItemWandAugmentationCore();
        wandCore = new ItemWandCore();
        TMObjects.loadRecipes();
    }

    private static void loadRecipes() {
        TMObjects.loadArcaneWorkbenchRecipes();
        TMObjects.loadInfusionRecipes();
    }

    private static void loadInfusionRecipes() {
        for (IAugmentationWand augmentation : WandHelper.augmentationMap.values()) {
            RecipeInfusionWandAugmentation recipe = (RecipeInfusionWandAugmentation) WandHelper
                .buildInfusionRecipe(augmentation, false);
            ThaumcraftApi.getCraftingRecipes()
                .add(recipe);
            recipe = (RecipeInfusionWandAugmentation) WandHelper.buildInfusionRecipe(augmentation, true);
            ConfigResearch.recipes.put(augmentation.getRecipeName(), recipe);
        }
    }

    private static void loadArcaneWorkbenchRecipes() {
        ConfigResearch.recipes.put(
            "TM_WAND_AUGMENTATION_CORE",
            ThaumcraftApi.addArcaneCraftingRecipe(
                (String) "@WAND_AUGMENTATION",
                (ItemStack) new ItemStack(wandAugmentationCore, 1),
                (AspectList) new AspectList().add(Aspect.ORDER, 25),
                (Object[]) new Object[] { "CBC", "BAB", "CBC", Character.valueOf('A'),
                    ItemApi.getItem((String) "itemResource", (int) 3), Character.valueOf('B'), Items.gold_ingot,
                    Character.valueOf('C'), ItemApi.getItem((String) "itemResource", (int) 14) }));
    }
}
