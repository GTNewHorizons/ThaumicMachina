
package jcm2606.thaumicmachina.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jcm2606.thaumicmachina.ThaumicMachina;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class TMBlock
extends Block {
    public IIcon customIcon;
    public String name;
    public String texture;
    public boolean useIconIndex = true;
    public int renderID = 0;
    public boolean renderAsNormalBlock = true;
    public boolean isOpaqueCube = true;

    public TMBlock(String name, Material mat) {
        super(mat);
        this.name = this.texture = name;
        this.setBlockName(name);
        this.setCreativeTab(ThaumicMachina.tab);
    }

    public TMBlock(String name, Material mat, String texture) {
        super(mat);
        this.name = name;
        this.texture = texture;
        this.setBlockName(name);
        this.setCreativeTab(ThaumicMachina.tab);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.customIcon = register.registerIcon("thaumicmachina:" + this.texture);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.customIcon;
    }

    public int getRenderType() {
        return this.renderID;
    }

    public boolean isOpaqueCube() {
        return this.isOpaqueCube;
    }

    public boolean renderAsNormalBlock() {
        return this.renderAsNormalBlock;
    }
}

