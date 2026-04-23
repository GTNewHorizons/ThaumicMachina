
package jcm2606.thaumicmachina.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import jcm2606.thaumicmachina.core.implement.IAugmentationNode;
import jcm2606.thaumicmachina.inv.container.ContainerNodeTransmodifier;
import jcm2606.thaumicmachina.item.node.ItemNodeAugmentation;
import jcm2606.thaumicmachina.tile.TileNodeTransmodifier;

public class GuiNodeTransmodifier extends GuiContainer {

    private static final ResourceLocation GUI = new ResourceLocation(
        "ThaumicMachina:textures/gui/nodeTransmodifier.png");
    public TileNodeTransmodifier tileTransmodifier;
    public int x;
    public int y;

    public GuiNodeTransmodifier(TileNodeTransmodifier tileTransmodifier, InventoryPlayer inv) {
        super((Container) new ContainerNodeTransmodifier(tileTransmodifier, inv));
        this.tileTransmodifier = tileTransmodifier;
    }

    public void initGui() {
        super.initGui();
        this.x = (this.width - this.xSize) / 2;
        this.y = (this.height - this.ySize) / 2;
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glPushMatrix();
        GL11.glEnable((int) 3042);
        GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
        this.drawBackground();
        GL11.glDisable((int) 3042);
        GL11.glPopMatrix();
    }

    public void drawBackground() {
        this.mc.renderEngine.bindTexture(GUI);
        this.drawTexturedModalRect(this.x, this.y + 77, 0, 0, this.xSize, 89);
        int cx = this.x + 10;
        for (int i = 0; i < 4; ++i) {
            if (this.tileTransmodifier.getStackInSlot(i) == null) {
                this.drawTexturedModalRect(cx, this.y + 37, 176, 0, 36, 36);
            } else {
                this.drawTexturedModalRect(cx, this.y + 37, 176, 36, 36, 36);
                if (this.tileTransmodifier.receivingIndirectRedstoneSignal) {
                    IAugmentationNode augmentation;
                    IAugmentationNode[] augmentationList = new IAugmentationNode[4];
                    for (int n = 0; n < 4; ++n) {
                        ItemStack stack = this.tileTransmodifier.getStackInSlot(n);
                        if (ItemNodeAugmentation.getAugmentation(stack) == null) continue;
                        augmentationList[n] = ItemNodeAugmentation.getAugmentation(stack);
                    }
                    ItemStack stack = this.tileTransmodifier.getStackInSlot(i);
                    if (stack != null && ItemNodeAugmentation.getAugmentation(stack) != null
                        && (augmentation = ItemNodeAugmentation.getAugmentation(stack)).compatible(augmentationList)) {
                        GL11.glPushMatrix();
                        float alpha = 0.5f
                            + (MathHelper.sin((float) ((float) this.tileTransmodifier.getTicks() / 2.0f)) * 0.2f
                                - 0.2f);
                        GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) alpha);
                        this.drawTexturedModalRect(cx, this.y + 37, 176, 72, 36, 36);
                        GL11.glColor4f((float) 1.0f, (float) 1.0f, (float) 1.0f, (float) 1.0f);
                        GL11.glPopMatrix();
                    }
                }
            }
            cx += 40;
        }
    }
}
