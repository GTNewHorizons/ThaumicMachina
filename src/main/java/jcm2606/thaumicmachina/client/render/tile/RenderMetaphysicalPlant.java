
package jcm2606.thaumicmachina.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysicalRose;
import thaumcraft.api.nodes.IRevealer;

public class RenderMetaphysicalPlant extends TileEntitySpecialRenderer {

    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        if (tile != null && tile instanceof TileMetaphysicalRose) {
            this.renderTileEntityAt((TileMetaphysicalRose) tile, x, y, z);
        }
    }

    public void renderTileEntityAt(TileMetaphysicalRose tile, double x, double y, double z) {
        Item item;
        ItemStack stack;
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        int slot = 3;
        if (player.getCurrentArmor(slot) != null && (stack = player.getCurrentArmor(slot)).getItem() != null
            && (item = stack.getItem()) instanceof IRevealer) {
            GL11.glPushMatrix();
            Tessellator tess = Tessellator.instance;
            tess.startDrawingQuads();
            tess.setColorOpaque_F(1.0f, 1.0f, 1.0f);
            tess.setBrightness(
                tile.getWorldObj()
                    .getBlock(tile.xCoord, tile.yCoord, tile.zCoord)
                    .getMixedBrightnessForBlock(
                        (IBlockAccess) tile.getWorldObj(),
                        tile.xCoord,
                        tile.yCoord,
                        tile.zCoord));
            Minecraft.getMinecraft().renderEngine
                .bindTexture(new ResourceLocation("ThaumicMachina:textures/blocks/metaphysicalRose.png"));
            this.drawCrossedSquares(
                tile.getWorldObj()
                    .getBlock(tile.xCoord, tile.yCoord, tile.zCoord)
                    .getIcon(0, 0),
                x,
                y,
                z,
                1.0f,
                tess);
            tess.draw();
            GL11.glPopMatrix();
        }
    }

    public void drawCrossedSquares(IIcon icon, double par3, double par5, double par7, float par9,
        Tessellator tessellator) {
        double d3 = 0.0;
        double d4 = 0.0;
        double d5 = 1.0;
        double d6 = 1.0;
        double d7 = 0.45 * (double) par9;
        double d8 = par3 + 0.5 - d7;
        double d9 = par3 + 0.5 + d7;
        double d10 = par7 + 0.5 - d7;
        double d11 = par7 + 0.5 + d7;
        tessellator.addVertexWithUV(d8, par5 + (double) par9, d10, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0, d10, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0, d11, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + (double) par9, d11, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + (double) par9, d11, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0, d11, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0, d10, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + (double) par9, d10, d5, d4);
        tessellator.addVertexWithUV(d8, par5 + (double) par9, d11, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0, d11, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0, d10, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + (double) par9, d10, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + (double) par9, d10, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0, d10, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0, d11, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + (double) par9, d11, d5, d4);
    }
}
