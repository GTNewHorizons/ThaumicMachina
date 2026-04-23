
package jcm2606.thaumicmachina.item.wand.rod;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jcm2606.thaumicmachina.item.TMItem;

public class ItemWandCore extends TMItem {

    public String[] types = new String[] { "celestial" };
    public IIcon[] icons;

    public ItemWandCore() {
        super("wandCore");
        this.setHasSubtypes(true);
        this.setNoRepair();
        this.setMaxDamage(0);
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.icons = new IIcon[this.types.length];
        for (int i = 0; i < this.types.length; ++i) {
            this.icons[i] = register.registerIcon("ThaumicMachina:" + this.types[i]);
        }
    }

    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + this.types[stack.getItemDamage()];
    }
}
