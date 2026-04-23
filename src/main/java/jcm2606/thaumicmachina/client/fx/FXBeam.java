
package jcm2606.thaumicmachina.client.fx;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class FXBeam extends EntityFX {

    private final float rotYaw;
    private final float rotPitch;
    private float prevYaw;
    private float prevPitch;
    private final double targetX;
    private final double targetY;
    private final double targetZ;
    public final boolean pulse;
    private float length = 0.0f;
    public final double rotationSpeed = 1.0;
    public double speed = 1.0;
    public final boolean slide;
    public final String texture;
    public final float size = 1.0f;
    public int colour = 0xFFFFFF;
    public float opacity = 0.125f;

    public FXBeam(World world, double sourceX, double sourceY, double sourceZ, double targetX, double targetY,
        double targetZ, int maxAge, String texture) {
        super(world, sourceX, sourceY, sourceZ, 0.0, 0.0, 0.0);
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.particleScale = 2.5f;
        this.particleBlue = 0.25f;
        this.particleGreen = 0.25f;
        this.particleRed = 0.25f;
        this.particleMaxAge = maxAge;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        double dx = this.posX - targetX;
        double dy = this.posY - targetY;
        double dz = this.posZ - targetZ;
        double distance = MathHelper.sqrt_double(dx * dx + dz * dz);
        this.rotYaw = (float) (Math.atan2(dx, dz) * 180.0 / Math.PI);
        this.rotPitch = (float) (Math.atan2(dy, distance) * 180.0 / Math.PI);
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        this.noClip = true;
        this.pulse = false;
        this.slide = true;
        this.texture = texture;
    }

    @Override
    public void renderParticle(Tessellator tessellator, float x, float y, float z, float f3, float f4, float f5) {
        tessellator.draw();
        GL11.glPushMatrix();
        float var9 = 1.0f;
        float slide = this.worldObj.getTotalWorldTime();
        double rot = (double) this.worldObj.provider.getWorldTime() % (360.0 / this.rotationSpeed) * this.rotationSpeed
            + this.rotationSpeed * (double) x;
        float op = this.opacity;
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.texture));
        GL11.glTexParameterf(3553, 10242, 10497.0f);
        GL11.glTexParameterf(3553, 10243, 10497.0f);
        GL11.glDisable(2884);
        float var11 = slide + x;
        if (!this.slide) {
            var11 = 0.0f;
        }
        float var12 = (float) ((double) (-var11) * this.speed - (double) MathHelper.floor_float(-var11 * 0.1f));
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glDepthMask(false);
        float xx = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) x - interpPosX);
        float yy = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) y - interpPosY);
        float zz = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) z - interpPosZ);
        GL11.glTranslated(xx, yy, zz);
        float ry = this.prevYaw + (this.rotYaw - this.prevYaw) * x;
        float rp = this.prevPitch + (this.rotPitch - this.prevPitch) * x;
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(180.0f + ry, 0.0f, 0.0f, -1.0f);
        GL11.glRotatef(rp, 1.0f, 0.0f, 0.0f);
        double var44 = -0.15 * (double) this.size;
        double var17 = 0.15 * (double) this.size;
        double var44b = -0.15 * (double) this.size * 1.0;
        double var17b = 0.15 * (double) this.size * 1.0;
        Color c = new Color(this.colour);
        GL11.glRotated(rot, 0.0, 1.0, 0.0);
        for (int t = 0; t < 2; ++t) {
            double var29 = this.length * this.size * var9;
            double var31 = 0.001;
            double var33 = 1.0;
            double var35 = -1.0f + var12 + (float) t;
            double var37 = (double) (this.length * this.size * var9) + var35;
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F(
                (float) c.getRed() / 255.0f,
                (float) c.getGreen() / 255.0f,
                (float) c.getBlue() / 255.0f,
                op);
            tessellator.setBrightness(200);
            tessellator.addVertexWithUV(var44b, var29, 0.0, var33, var37);
            tessellator.addVertexWithUV(var44, 0.0, 0.0, var33, var35);
            tessellator.addVertexWithUV(var17, 0.0, 0.0, var31, var35);
            tessellator.addVertexWithUV(var17b, var29, 0.0, var31, var37);
            tessellator.draw();
        }
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2884);
        GL11.glPopMatrix();
        tessellator.startDrawingQuads();
        Minecraft.getMinecraft().renderEngine
            .bindTexture(new ResourceLocation("minecraft", "textures/particle/particles.png"));
    }

    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        float xd = (float) (this.posX - this.targetX);
        float yd = (float) (this.posY - this.targetY);
        float zd = (float) (this.posZ - this.targetZ);
        this.length = MathHelper.sqrt_float(xd * xd + yd * yd + zd * zd);
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }
    }
}
