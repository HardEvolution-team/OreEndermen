package com.electrifiedded.oreendermen.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/** Tall biped (30-long limbs) taken from the original 1.7.10 mod's model definition. */
public class OreEndermanModel extends ModelBiped {
    public OreEndermanModel() {
        super(0.0F, -14.0F, 64, 32);
        float f = -14.0F;
        bipedHeadwear = new ModelRenderer(this, 0, 16);
        bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, -0.5F);
        bipedHeadwear.setRotationPoint(0.0F, f, 0.0F);
        bipedBody = new ModelRenderer(this, 32, 16);
        bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        bipedBody.setRotationPoint(0.0F, f, 0.0F);
        bipedRightArm = new ModelRenderer(this, 56, 0);
        bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, 0.0F);
        bipedRightArm.setRotationPoint(-3.0F, 2.0F + f, 0.0F);
        bipedLeftArm = new ModelRenderer(this, 56, 0);
        bipedLeftArm.mirror = true;
        bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, 0.0F);
        bipedLeftArm.setRotationPoint(5.0F, 2.0F + f, 0.0F);
        bipedRightLeg = new ModelRenderer(this, 56, 0);
        bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        bipedRightLeg.setRotationPoint(-2.0F, 12.0F + f, 0.0F);
        bipedLeftLeg = new ModelRenderer(this, 56, 0);
        bipedLeftLeg.mirror = true;
        bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, 0.0F);
        bipedLeftLeg.setRotationPoint(2.0F, 12.0F + f, 0.0F);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
                                  float headPitch, float scaleFactor, Entity entity) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        bipedHead.showModel = true;
        float f6 = -14.0F;
        bipedBody.rotateAngleX = 0.0F;
        bipedBody.rotationPointY = f6;
        bipedBody.rotationPointZ = 0.0F;
        bipedRightArm.rotateAngleX = clamp(bipedRightArm.rotateAngleX * 0.5F);
        bipedLeftArm.rotateAngleX = clamp(bipedLeftArm.rotateAngleX * 0.5F);
        bipedRightLeg.rotateAngleX = clamp(bipedRightLeg.rotateAngleX * 0.5F);
        bipedLeftLeg.rotateAngleX = clamp(bipedLeftLeg.rotateAngleX * 0.5F);
        bipedRightArm.rotationPointZ = 0.0F;
        bipedLeftArm.rotationPointZ = 0.0F;
        bipedRightLeg.rotationPointZ = 0.0F;
        bipedLeftLeg.rotationPointZ = 0.0F;
        bipedRightLeg.rotationPointY = 9.0F + f6;
        bipedLeftLeg.rotationPointY = 9.0F + f6;
        bipedHead.rotationPointZ = 0.0F;
        bipedHead.rotationPointY = f6 + 1.0F;
        bipedHeadwear.rotationPointX = bipedHead.rotationPointX;
        bipedHeadwear.rotationPointY = bipedHead.rotationPointY;
        bipedHeadwear.rotationPointZ = bipedHead.rotationPointZ;
        bipedHeadwear.rotateAngleX = bipedHead.rotateAngleX;
        bipedHeadwear.rotateAngleY = bipedHead.rotateAngleY;
        bipedHeadwear.rotateAngleZ = bipedHead.rotateAngleZ;
    }

    private static float clamp(float v) {
        return Math.max(-0.4F, Math.min(0.4F, v));
    }
}
