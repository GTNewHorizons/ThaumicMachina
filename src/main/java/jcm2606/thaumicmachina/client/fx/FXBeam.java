
package jcm2606.thaumicmachina.client.fx;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXBeam
extends EntityFX {
    private float rotYaw = 0.0f;
    private float rotPitch = 0.0f;
    private float prevYaw = 0.0f;
    private float prevPitch = 0.0f;
    private double targetX = 0.0;
    private double targetY = 0.0;
    private double targetZ = 0.0;
    public boolean pulse;
    private final float prevSize = 0.0f;
    private float length = 0.0f;
    public double rotationSpeed = 1.0;
    public double speed = 1.0;
    public boolean slide;
    public String texture;
    public float size = 1.0f;
    public int colour = 0xFFFFFF;
    public float opacity = 0.125f;

    public FXBeam(World par1World, double f, double f2, double f4, double par8, double par10, double par12, int par14, int par15, String texture) {
        super(par1World, f, f2, f4, 0.0, 0.0, 0.0);
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.particleScale = 2.5f;
        this.particleBlue = 0.25f;
        this.particleGreen = 0.25f;
        this.particleRed = 0.25f;
        this.particleMaxAge = par15;
        this.targetX = par8;
        this.targetY = par10;
        this.targetZ = par12;
        double dx = this.posX - par8;
        double dy = this.posY - par10;
        double dz = this.posZ - par12;
        double var7 = MathHelper.sqrt_double((double)(dx * dx + dz * dz));
        this.rotYaw = (float)(Math.atan2(dx, dz) * 180.0 / Math.PI);
        this.rotPitch = (float)(Math.atan2(dy, var7) * 180.0 / Math.PI);
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        this.noClip = true;
        this.pulse = false;
        this.slide = true;
        this.texture = texture;
    }

    public FXBeam(World world, double f, double f2, double f3, double d, double d2, double d3, int par4, String texture) {
        this(world, f, f2, f3, d, d2, d3, par4, par4, texture);
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        tessellator.draw();
        GL11.glPushMatrix();
        float var9 = 1.0f;
        float slide = this.worldObj.getTotalWorldTime();
        double rot = (double)this.worldObj.provider.getWorldTime() % (360.0 / this.rotationSpeed) * this.rotationSpeed + this.rotationSpeed * (double)f;
        float op = this.opacity;
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.texture));
        GL11.glTexParameterf((int)3553, (int)10242, (float)10497.0f);
        GL11.glTexParameterf((int)3553, (int)10243, (float)10497.0f);
        GL11.glDisable((int)2884);
        float var11 = slide + f;
        if (!this.slide) {
            var11 = 0.0f;
        }
        float var12 = (float)((double)(-var11) * this.speed - (double)MathHelper.floor_float((float)(-var11 * 0.1f)));
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glDepthMask((boolean)false);
        float xx = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)f - interpPosX);
        float yy = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)f - interpPosY);
        float zz = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)f - interpPosZ);
        GL11.glTranslated((double)xx, (double)yy, (double)zz);
        float ry = this.prevYaw + (this.rotYaw - this.prevYaw) * f;
        float rp = this.prevPitch + (this.rotPitch - this.prevPitch) * f;
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(180.0f + ry), (float)0.0f, (float)0.0f, (float)-1.0f);
        GL11.glRotatef((float)rp, (float)1.0f, (float)0.0f, (float)0.0f);
        double var44 = -0.15 * (double)this.size;
        double var17 = 0.15 * (double)this.size;
        double var44b = -0.15 * (double)this.size * 1.0;
        double var17b = 0.15 * (double)this.size * 1.0;
        Color c = new Color(this.colour);
        GL11.glRotated((double)rot, (double)0.0, (double)1.0, (double)0.0);
        for (int t = 0; t < 2; ++t) {
            double var29 = this.length * this.size * var9;
            double var31 = 0.001;
            double var33 = 1.0;
            double var35 = -1.0f + var12 + (float)t * 1.0f;
            double var37 = (double)(this.length * this.size * var9) + var35;
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F((float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, op);
            tessellator.setBrightness(200);
            tessellator.addVertexWithUV(var44b, var29, 0.0, var33, var37);
            tessellator.addVertexWithUV(var44, 0.0, 0.0, var33, var35);
            tessellator.addVertexWithUV(var17, 0.0, 0.0, var31, var35);
            tessellator.addVertexWithUV(var17b, var29, 0.0, var31, var37);
            tessellator.draw();
        }
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        tessellator.startDrawingQuads();
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "textures/particle/particles.png"));
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        float xd = (float)(this.posX - this.targetX);
        float yd = (float)(this.posY - this.targetY);
        float zd = (float)(this.posZ - this.targetZ);
        this.length = MathHelper.sqrt_float((float)(xd * xd + yd * yd + zd * zd));
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
    }
}

