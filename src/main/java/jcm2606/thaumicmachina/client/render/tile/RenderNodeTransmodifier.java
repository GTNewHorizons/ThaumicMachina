
package jcm2606.thaumicmachina.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import jcm2606.thaumicmachina.client.model.ModelNodeTransmodifier;
import jcm2606.thaumicmachina.tile.TileNodeTransmodifier;

public class RenderNodeTransmodifier extends TileEntitySpecialRenderer {

    public final ModelNodeTransmodifier model = new ModelNodeTransmodifier();

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        this.render((TileNodeTransmodifier) tile, x, y, z, f);
    }

    public void render(TileNodeTransmodifier tile, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);
        GL11.glScalef(1.0f, -1.0f, -1.0f);
        this.renderTransmodifier(tile);
        this.renderSpinningCube(tile);
        GL11.glPopMatrix();
    }

    public void renderTransmodifier(TileNodeTransmodifier tile) {
        Minecraft.getMinecraft().renderEngine
            .bindTexture(new ResourceLocation("ThaumicMachina:textures/model/nodeTransmodifier.png"));
        this.model.renderTile();
    }

    public void renderSpinningCube(TileNodeTransmodifier tile) {}
}
