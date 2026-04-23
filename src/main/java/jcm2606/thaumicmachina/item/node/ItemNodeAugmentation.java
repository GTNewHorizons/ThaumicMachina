
package jcm2606.thaumicmachina.item.node;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jcm2606.thaumicmachina.augmentation.node.AugmentationDualCharging;
import jcm2606.thaumicmachina.augmentation.node.AugmentationEnhancedRecharging;
import jcm2606.thaumicmachina.core.helper.NBTHelper;
import jcm2606.thaumicmachina.core.implement.IAugmentationNode;
import jcm2606.thaumicmachina.item.TMItem;

public class ItemNodeAugmentation extends TMItem {

    private static final ArrayList<IAugmentationNode> augmentationList = new ArrayList<>();

    public ItemNodeAugmentation() {
        super("nodeAugmentation");
    }

    public static void loadAugmentations() {
        augmentationList.add(new AugmentationEnhancedRecharging());
        augmentationList.add(new AugmentationDualCharging());
    }

    public static IAugmentationNode getAugmentation(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        for (IAugmentationNode augmentation : augmentationList) {
            if (!augmentation.getAugmentationName()
                .equals(
                    NBTHelper.getCompoundFor(stack)
                        .getString("nodeAugmentation")))
                continue;
            return augmentation;
        }
        return null;
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> list) {
        for (IAugmentationNode augmentation : augmentationList) {
            ItemStack stack = new ItemStack(item, 1, 0);
            NBTTagCompound compound = NBTHelper.getCompoundFor(stack);
            compound.setString("nodeAugmentation", augmentation.getAugmentationName());
            list.add(stack);
        }
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanced) {
        NBTTagCompound compound = NBTHelper.getCompoundFor(stack);
        if (compound != null) {
            list.add("Augmentation: " + compound.getString("nodeAugmentation"));
        }
    }
}
