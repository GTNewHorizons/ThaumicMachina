
package jcm2606.thaumicmachina.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jcm2606.thaumicmachina.ThaumicMachina;

public class TMBlock extends Block {

    public IIcon customIcon;
    public final String name;
    public final String texture;
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

    @Override
    @SideOnly(value = Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.customIcon = register.registerIcon("thaumicmachina:" + this.texture);
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.customIcon;
    }

    @Override
    public int getRenderType() {
        return this.renderID;
    }

    @Override
    public boolean isOpaqueCube() {
        return this.isOpaqueCube;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return this.renderAsNormalBlock;
    }
}
