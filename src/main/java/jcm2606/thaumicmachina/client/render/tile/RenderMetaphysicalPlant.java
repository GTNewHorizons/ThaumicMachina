
package jcm2606.thaumicmachina.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import jcm2606.thaumicmachina.block.metaphysical.BlockMetaphysical;
import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysicalRose;

public class RenderMetaphysicalPlant extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        if (tile instanceof TileMetaphysicalRose te) {
            this.renderTileEntityAt(te, x, y, z);
        }
    }

    public void renderTileEntityAt(TileMetaphysicalRose tile, double x, double y, double z) {
        if (!BlockMetaphysical.hasGoggles(Minecraft.getMinecraft().thePlayer)) {
            return;
        }
        GL11.glPushMatrix();
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        tess.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        tess.setBrightness(
            tile.getWorldObj()
                .getBlock(tile.xCoord, tile.yCoord, tile.zCoord)
                .getMixedBrightnessForBlock(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord));
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

    public void drawCrossedSquares(IIcon icon, double x, double y, double z, float size, Tessellator tessellator) {
        double u1 = 0.0;
        double v1 = 0.0;
        double u2 = 1.0;
        double v2 = 1.0;
        double offset = 0.45 * (double) size;
        double x1 = x + 0.5 - offset;
        double x2 = x + 0.5 + offset;
        double z1 = z + 0.5 - offset;
        double z2 = z + 0.5 + offset;
        tessellator.addVertexWithUV(x1, y + (double) size, z1, u1, v1);
        tessellator.addVertexWithUV(x1, y + 0.0, z1, u1, v2);
        tessellator.addVertexWithUV(x2, y + 0.0, z2, u2, v2);
        tessellator.addVertexWithUV(x2, y + (double) size, z2, u2, v1);
        tessellator.addVertexWithUV(x2, y + (double) size, z2, u1, v1);
        tessellator.addVertexWithUV(x2, y + 0.0, z2, u1, v2);
        tessellator.addVertexWithUV(x1, y + 0.0, z1, u2, v2);
        tessellator.addVertexWithUV(x1, y + (double) size, z1, u2, v1);
        tessellator.addVertexWithUV(x1, y + (double) size, z2, u1, v1);
        tessellator.addVertexWithUV(x1, y + 0.0, z2, u1, v2);
        tessellator.addVertexWithUV(x2, y + 0.0, z1, u2, v2);
        tessellator.addVertexWithUV(x2, y + (double) size, z1, u2, v1);
        tessellator.addVertexWithUV(x2, y + (double) size, z1, u1, v1);
        tessellator.addVertexWithUV(x2, y + 0.0, z1, u1, v2);
        tessellator.addVertexWithUV(x1, y + 0.0, z2, u2, v2);
        tessellator.addVertexWithUV(x1, y + (double) size, z2, u2, v1);
    }
}
