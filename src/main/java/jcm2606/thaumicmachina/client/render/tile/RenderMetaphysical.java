
package jcm2606.thaumicmachina.client.render.tile;

import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysical;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.nodes.IRevealer;

public class RenderMetaphysical
extends TileEntitySpecialRenderer {
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
        if (tile != null && tile instanceof TileMetaphysical) {
            this.renderTileEntityAt((TileMetaphysical)tile, x, y, z);
        }
    }

    public void renderTileEntityAt(TileMetaphysical tile, double x, double y, double z) {
        Item item;
        ItemStack stack;
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        int slot = 3;
        if (player.getCurrentArmor(slot) != null && (stack = player.getCurrentArmor(slot)).getItem() != null && (item = stack.getItem()) instanceof IRevealer) {
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glDisable((int)3168);
            GL11.glDisable((int)3169);
            GL11.glDisable((int)3170);
            GL11.glDisable((int)3171);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.renderUnitBoxWithUV(tile, (float)x, (float)y, (float)z, Tessellator.instance, 0.0f, 0.0f, 1.0f, 1.0f, "metaphysicalBrick");
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
    }

    public void renderUnitBoxWithUV(TileEntity te, float paramFloat1, float paramFloat2, float paramFloat3, Tessellator paramTessellator, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, String texture) {
        paramTessellator.startDrawingQuads();
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("ThaumicMachina:textures/blocks/" + texture + ".png"));
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)paramFloat2, (double)paramFloat3, (double)paramFloat4, (double)paramFloat5);
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)(paramFloat2 + 1.0f), (double)paramFloat3, (double)paramFloat4, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)(paramFloat2 + 1.0f), (double)paramFloat3, (double)paramFloat6, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)paramFloat2, (double)paramFloat3, (double)paramFloat6, (double)paramFloat5);
        paramTessellator.draw();
        paramTessellator.startDrawingQuads();
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)paramFloat2, (double)(paramFloat3 + 1.0f), (double)paramFloat4, (double)paramFloat5);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)paramFloat2, (double)(paramFloat3 + 1.0f), (double)paramFloat4, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)(paramFloat2 + 1.0f), (double)(paramFloat3 + 1.0f), (double)paramFloat6, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)(paramFloat2 + 1.0f), (double)(paramFloat3 + 1.0f), (double)paramFloat6, (double)paramFloat5);
        paramTessellator.draw();
        paramTessellator.startDrawingQuads();
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)paramFloat2, (double)paramFloat3, (double)paramFloat4, (double)paramFloat5);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)(paramFloat2 + 1.0f), (double)paramFloat3, (double)paramFloat4, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)(paramFloat2 + 1.0f), (double)(paramFloat3 + 1.0f), (double)paramFloat6, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)paramFloat2, (double)(paramFloat3 + 1.0f), (double)paramFloat6, (double)paramFloat5);
        paramTessellator.draw();
        paramTessellator.startDrawingQuads();
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)paramFloat2, (double)paramFloat3, (double)paramFloat4, (double)paramFloat5);
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)paramFloat2, (double)(paramFloat3 + 1.0f), (double)paramFloat4, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)(paramFloat2 + 1.0f), (double)(paramFloat3 + 1.0f), (double)paramFloat6, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)(paramFloat2 + 1.0f), (double)paramFloat3, (double)paramFloat6, (double)paramFloat5);
        paramTessellator.draw();
        paramTessellator.startDrawingQuads();
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)paramFloat2, (double)paramFloat3, (double)paramFloat4, (double)paramFloat5);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)paramFloat2, (double)(paramFloat3 + 1.0f), (double)paramFloat4, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)paramFloat2, (double)(paramFloat3 + 1.0f), (double)paramFloat6, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)paramFloat2, (double)paramFloat3, (double)paramFloat6, (double)paramFloat5);
        paramTessellator.draw();
        paramTessellator.startDrawingQuads();
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)(paramFloat2 + 1.0f), (double)(paramFloat3 + 1.0f), (double)paramFloat4, (double)paramFloat5);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)(paramFloat2 + 1.0f), (double)(paramFloat3 + 1.0f), (double)paramFloat4, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)(paramFloat1 + 1.0f), (double)(paramFloat2 + 1.0f), (double)paramFloat3, (double)paramFloat6, (double)paramFloat7);
        paramTessellator.addVertexWithUV((double)paramFloat1, (double)(paramFloat2 + 1.0f), (double)paramFloat3, (double)paramFloat6, (double)paramFloat5);
        paramTessellator.draw();
    }
}

