
package jcm2606.thaumicmachina.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TMCreativeTab extends CreativeTabs {

    public TMCreativeTab() {
        super("thaumicMachina");
    }

    public Item getTabIconItem() {
        return TMObjects.wandAugmentationCore;
    }
}
