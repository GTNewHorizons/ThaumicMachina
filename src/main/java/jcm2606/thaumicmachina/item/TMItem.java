
package jcm2606.thaumicmachina.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jcm2606.thaumicmachina.ThaumicMachina;

public class TMItem extends Item {

    public String name;
    public String texture;

    public TMItem(String name) {
        this.name = this.texture = name;
        this.setUnlocalizedName(this.name);
        this.setCreativeTab(ThaumicMachina.tab);
        GameRegistry.registerItem((Item) this, (String) name, (String) "ThaumicMachina");
    }

    public TMItem(String name, String texture) {
        this.name = name;
        this.texture = texture;
        this.setUnlocalizedName(this.name);
        this.setCreativeTab(ThaumicMachina.tab);
        GameRegistry.registerItem((Item) this, (String) name, (String) "ThaumicMachina");
    }

    @SideOnly(value = Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.itemIcon = register.registerIcon("ThaumicMachina:" + this.texture);
    }
}
