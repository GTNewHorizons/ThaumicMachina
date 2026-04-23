
package jcm2606.thaumicmachina.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNodeTransmodifier extends ModelBase {

    ModelRenderer Base1;
    ModelRenderer Centre;
    ModelRenderer Base2;
    ModelRenderer TopPlate;
    ModelRenderer Arm1;
    ModelRenderer Arm2;
    ModelRenderer Arm3;
    ModelRenderer Arm4;

    public ModelNodeTransmodifier() {
        this.textureWidth = 256;
        this.textureHeight = 128;
        this.Base1 = new ModelRenderer((ModelBase) this, 0, 0);
        this.Base1.addBox(0.0f, 0.0f, 0.0f, 16, 3, 16);
        this.Base1.setRotationPoint(-8.0f, 21.0f, -8.0f);
        this.Base1.setTextureSize(256, 128);
        this.Base1.mirror = true;
        this.setRotation(this.Base1, 0.0f, 0.0f, 0.0f);
        this.Centre = new ModelRenderer((ModelBase) this, 0, 21);
        this.Centre.addBox(0.0f, 0.0f, 0.0f, 14, 1, 14);
        this.Centre.setRotationPoint(-7.0f, 20.0f, -7.0f);
        this.Centre.setTextureSize(256, 128);
        this.Centre.mirror = true;
        this.setRotation(this.Centre, 0.0f, 0.0f, 0.0f);
        this.Base2 = new ModelRenderer((ModelBase) this, 0, 0);
        this.Base2.addBox(0.0f, 0.0f, 0.0f, 16, 3, 16);
        this.Base2.setRotationPoint(-8.0f, 17.0f, -8.0f);
        this.Base2.setTextureSize(256, 128);
        this.Base2.mirror = true;
        this.setRotation(this.Base2, 0.0f, 0.0f, 0.0f);
        this.TopPlate = new ModelRenderer((ModelBase) this, 0, 47);
        this.TopPlate.addBox(0.0f, 0.0f, 0.0f, 12, 1, 12);
        this.TopPlate.setRotationPoint(-6.0f, 16.0f, -6.0f);
        this.TopPlate.setTextureSize(256, 128);
        this.TopPlate.mirror = true;
        this.setRotation(this.TopPlate, 0.0f, 0.0f, 0.0f);
        this.Arm1 = new ModelRenderer((ModelBase) this, 0, 38);
        this.Arm1.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Arm1.setRotationPoint(-6.5f, 13.5f, -0.5f);
        this.Arm1.setTextureSize(256, 128);
        this.Arm1.mirror = true;
        this.setRotation(this.Arm1, 0.0f, 0.0f, -0.5235988f);
        this.Arm2 = new ModelRenderer((ModelBase) this, 12, 38);
        this.Arm2.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Arm2.setRotationPoint(5.5f, 13.0f, -0.5f);
        this.Arm2.setTextureSize(256, 128);
        this.Arm2.mirror = true;
        this.setRotation(this.Arm2, 0.0f, 0.0f, 0.5235988f);
        this.Arm3 = new ModelRenderer((ModelBase) this, 18, 38);
        this.Arm3.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Arm3.setRotationPoint(-0.5f, 13.5f, -6.5f);
        this.Arm3.setTextureSize(256, 128);
        this.Arm3.mirror = true;
        this.setRotation(this.Arm3, 0.5235988f, 0.0f, 0.0f);
        this.Arm4 = new ModelRenderer((ModelBase) this, 6, 38);
        this.Arm4.addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Arm4.setRotationPoint(-0.5f, 13.0f, 5.5f);
        this.Arm4.setTextureSize(256, 128);
        this.Arm4.mirror = true;
        this.setRotation(this.Arm4, -0.5235988f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.Base1.render(f5);
        this.Centre.render(f5);
        this.Base2.render(f5);
        this.TopPlate.render(f5);
        this.Arm1.render(f5);
        this.Arm2.render(f5);
        this.Arm3.render(f5);
        this.Arm4.render(f5);
    }

    public void renderTile() {
        float f5 = 0.0625f;
        this.Base1.render(f5);
        this.Centre.render(f5);
        this.Base2.render(f5);
        this.TopPlate.render(f5);
        this.Arm1.render(f5);
        this.Arm2.render(f5);
        this.Arm3.render(f5);
        this.Arm4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
