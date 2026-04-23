
package jcm2606.thaumicmachina.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import jcm2606.thaumicmachina.block.metaphysical.BlockMetaphysical;
import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysical;

public class RenderMetaphysical extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        if (tile instanceof TileMetaphysical te) {
            this.renderTileEntityAt(te, x, y, z);
        }
    }

    public void renderTileEntityAt(TileMetaphysical tile, double x, double y, double z) {
        if (!BlockMetaphysical.hasGoggles(Minecraft.getMinecraft().thePlayer)) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHT0);
        GL11.glDisable(GL11.GL_LIGHT1);
        GL11.glDisable(GL11.GL_LIGHT2);
        GL11.glDisable(GL11.GL_LIGHT3);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.renderUnitBoxWithUV(
            tile,
            (float) x,
            (float) y,
            (float) z,
            Tessellator.instance,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            "metaphysicalBrick");

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    public void renderUnitBoxWithUV(TileEntity te, float x, float y, float z, Tessellator tess, float u1, float v1,
        float u2, float v2, String texture) {
        tess.startDrawingQuads();
        Minecraft.getMinecraft().renderEngine
            .bindTexture(new ResourceLocation("ThaumicMachina:textures/blocks/" + texture + ".png"));
        tess.addVertexWithUV(x, y, z, u1, v1);
        tess.addVertexWithUV(x, y + 1.0f, z, u1, v2);
        tess.addVertexWithUV(x + 1.0f, y + 1.0f, z, u2, v2);
        tess.addVertexWithUV(x + 1.0f, y, z, u2, v1);
        tess.draw();
        tess.startDrawingQuads();
        tess.addVertexWithUV(x, y, z + 1.0f, u1, v1);
        tess.addVertexWithUV(x + 1.0f, y, z + 1.0f, u1, v2);
        tess.addVertexWithUV(x + 1.0f, y + 1.0f, z + 1.0f, u2, v2);
        tess.addVertexWithUV(x, y + 1.0f, z + 1.0f, u2, v1);
        tess.draw();
        tess.startDrawingQuads();
        tess.addVertexWithUV(x + 1.0f, y, z, u1, v1);
        tess.addVertexWithUV(x + 1.0f, y + 1.0f, z, u1, v2);
        tess.addVertexWithUV(x + 1.0f, y + 1.0f, z + 1.0f, u2, v2);
        tess.addVertexWithUV(x + 1.0f, y, z + 1.0f, u2, v1);
        tess.draw();
        tess.startDrawingQuads();
        tess.addVertexWithUV(x, y, z, u1, v1);
        tess.addVertexWithUV(x, y, z + 1.0f, u1, v2);
        tess.addVertexWithUV(x, y + 1.0f, z + 1.0f, u2, v2);
        tess.addVertexWithUV(x, y + 1.0f, z, u2, v1);
        tess.draw();
        tess.startDrawingQuads();
        tess.addVertexWithUV(x + 1.0f, y, z, u1, v1);
        tess.addVertexWithUV(x + 1.0f, y, z + 1.0f, u1, v2);
        tess.addVertexWithUV(x, y, z + 1.0f, u2, v2);
        tess.addVertexWithUV(x, y, z, u2, v1);
        tess.draw();
        tess.startDrawingQuads();
        tess.addVertexWithUV(x, y + 1.0f, z + 1.0f, u1, v1);
        tess.addVertexWithUV(x + 1.0f, y + 1.0f, z + 1.0f, u1, v2);
        tess.addVertexWithUV(x + 1.0f, y + 1.0f, z, u2, v2);
        tess.addVertexWithUV(x, y + 1.0f, z, u2, v1);
        tess.draw();
    }
}
