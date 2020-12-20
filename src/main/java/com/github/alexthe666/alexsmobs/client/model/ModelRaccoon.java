package com.github.alexthe666.alexsmobs.client.model;

import com.github.alexthe666.alexsmobs.entity.EntityGorilla;
import com.github.alexthe666.alexsmobs.entity.EntityRaccoon;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelRaccoon extends AdvancedEntityModel<EntityRaccoon> {
    public AdvancedModelBox root;
    public AdvancedModelBox body;
    public AdvancedModelBox tail;
    public AdvancedModelBox arm_left;
    public AdvancedModelBox arm_right;
    public AdvancedModelBox leg_left;
    public AdvancedModelBox leg_right;
    public AdvancedModelBox head;
    public AdvancedModelBox ear_left;
    public AdvancedModelBox ear_right;
    public AdvancedModelBox snout;
    public ModelAnimator animator;

    public ModelRaccoon() {
        super();
        textureWidth = 64;
        textureHeight = 64;

        root = new AdvancedModelBox(this);
        root.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new AdvancedModelBox(this);
        body.setRotationPoint(0.0F, -11.0F, 0.5F);
        root.addChild(body);
        body.setTextureOffset(0, 0).addBox(-5.5F, -4.0F, -7.5F, 11.0F, 8.0F, 15.0F, 0.0F, false);

        tail = new AdvancedModelBox(this);
        tail.setRotationPoint(0.5F, -1.0F, 7.5F);
        body.addChild(tail);
        tail.setTextureOffset(0, 24).addBox(-3.0F, -2.0F, 0.0F, 5.0F, 5.0F, 19.0F, 0.0F, false);

        arm_left = new AdvancedModelBox(this);
        arm_left.setRotationPoint(3.0F, 4.0F, -5.5F);
        body.addChild(arm_left);
        arm_left.setTextureOffset(0, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        arm_right = new AdvancedModelBox(this);
        arm_right.setRotationPoint(-3.0F, 4.0F, -5.5F);
        body.addChild(arm_right);
        arm_right.setTextureOffset(0, 24).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, true);

        leg_left = new AdvancedModelBox(this);
        leg_left.setRotationPoint(3.0F, 4.0F, 6.5F);
        body.addChild(leg_left);
        leg_left.setTextureOffset(9, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        leg_right = new AdvancedModelBox(this);
        leg_right.setRotationPoint(-3.0F, 4.0F, 6.5F);
        body.addChild(leg_right);
        leg_right.setTextureOffset(9, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, true);

        head = new AdvancedModelBox(this);
        head.setRotationPoint(0.0F, 0.5F, -8.5F);
        body.addChild(head);
        head.setTextureOffset(30, 30).addBox(-4.5F, -4.0F, -4.0F, 9.0F, 7.0F, 5.0F, 0.0F, false);

        ear_left = new AdvancedModelBox(this);
        ear_left.setRotationPoint(3.5F, -4.0F, -2.0F);
        head.addChild(ear_left);
        ear_left.setTextureOffset(9, 24).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        ear_right = new AdvancedModelBox(this);
        ear_right.setRotationPoint(-3.5F, -4.0F, -2.0F);
        head.addChild(ear_right);
        ear_right.setTextureOffset(9, 24).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, 0.0F, true);

        snout = new AdvancedModelBox(this);
        snout.setRotationPoint(0.0F, 1.5F, -5.0F);
        head.addChild(snout);
        snout.setTextureOffset(0, 0).addBox(-2.0F, -1.5F, -2.0F, 4.0F, 3.0F, 3.0F, 0.0F, false);
        this.updateDefaultPose();
        animator = ModelAnimator.create();
    }

    @Override
    public Iterable<AdvancedModelBox> getAllParts() {
        return ImmutableList.of(root, body, leg_left, leg_right, arm_left, arm_right, tail, head, ear_left, ear_right, snout);
    }

    @Override
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(root);
    }

    public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4) {
        this.resetToDefaultPose();
        animator.update(entity);
    }

    @Override
    public void setRotationAngles(EntityRaccoon entityRaccoon, float limbSwing, float limbSwingAmount, float ageInTicks, float v3, float v4) {
        this.animate(entityRaccoon, limbSwing, limbSwingAmount, ageInTicks, v3, v4);
        float partialTicks = Minecraft.getInstance().getRenderPartialTicks();
        float normalProgress = 5F;
        float walkSpeed = 1F;
        float walkDegree = 0.8F;
        float idleSpeed = 0.1F;
        float idleDegree = 0.2F;
        float runProgress = 5F * limbSwingAmount;
        float begProgress = entityRaccoon.prevBegProgress + (entityRaccoon.begProgress - entityRaccoon.prevBegProgress) * partialTicks;
        float standProgress0 = entityRaccoon.prevStandProgress + (entityRaccoon.standProgress - entityRaccoon.prevStandProgress) * partialTicks;
        float standProgress = Math.max(begProgress, standProgress0);
        float washProgress = entityRaccoon.prevWashProgress + (entityRaccoon.washProgress - entityRaccoon.prevWashProgress) * partialTicks;
        progressRotationPrev(body, standProgress, (float) Math.toRadians(-70), 0, 0, 5f);
        progressRotationPrev(arm_left, standProgress, (float) Math.toRadians(70), 0, 0, 5f);
        progressRotationPrev(arm_right, standProgress, (float) Math.toRadians(70), 0, 0, 5f);
        progressRotationPrev(leg_left, standProgress, (float) Math.toRadians(70), 0, 0, 5f);
        progressRotationPrev(leg_right, standProgress, (float) Math.toRadians(70), 0, 0, 5f);
        progressRotationPrev(head, standProgress, (float) Math.toRadians(70), 0, 0, 5f);
        progressPositionPrev(head, standProgress, 0, -2F, 0, 5f);
        progressPositionPrev(body, standProgress, 0, -3F, 0, 5f);
        progressPositionPrev(leg_left, standProgress, 0, -2F, 0, 5f);
        progressPositionPrev(leg_right, standProgress, 0, -2F, 0, 5f);
        progressPositionPrev(arm_left, standProgress, 0, 1F, 0, 5f);
        progressPositionPrev(arm_right, standProgress, 0, 1F, 0, 5f);
        progressRotationPrev(tail, standProgress, (float) Math.toRadians(80), 0, 0, 5f);
        progressRotationPrev(body, normalProgress, (float) Math.toRadians(10), 0, 0, 5f);
        progressRotationPrev(head, normalProgress, (float) Math.toRadians(-10), 0, 0, 5f);
        progressRotationPrev(arm_left, normalProgress, (float) Math.toRadians(-10), 0, 0, 5f);
        progressRotationPrev(arm_right, normalProgress, (float) Math.toRadians(-10), 0, 0, 5f);
        progressRotationPrev(leg_left, normalProgress, (float) Math.toRadians(-10), 0, 0, 5f);
        progressRotationPrev(leg_right, normalProgress, (float) Math.toRadians(-10), 0, 0, 5f);
        progressPositionPrev(body, normalProgress, 0, 1F, 0, 5f);
        progressPositionPrev(arm_left, normalProgress, 0, -1.9F, 0, 5f);
        progressPositionPrev(arm_right, normalProgress, 0, -1.9F, 0, 5f);
        progressPositionPrev(leg_left, normalProgress, 0, 0F, -1, 5f);
        progressPositionPrev(leg_right, normalProgress, 0, 0F, -1, 5f);
        progressRotationPrev(tail, 5F - runProgress, (float) Math.toRadians(-35), 0, 0, 5f);
        progressRotationPrev(tail, runProgress, (float) Math.toRadians(-10), 0, 0, 5f);
        progressRotationPrev(ear_left, Math.max(runProgress, begProgress), (float) Math.toRadians(-20), 0, (float) Math.toRadians(20), 5f);
        progressRotationPrev(ear_right, Math.max(runProgress, begProgress), (float) Math.toRadians(-20), 0, (float) Math.toRadians(-20), 5f);
        progressRotationPrev(arm_right, begProgress, (float) Math.toRadians(-25), 0, 0, 5f);
        progressRotationPrev(arm_left, begProgress, (float) Math.toRadians(-25), 0, 0, 5f);
        if (begProgress > 0) {
            this.walk(head, 0.7F, 0.2F, false, 2F, -0.2F, ageInTicks, begProgress * 0.2F);
            this.walk(arm_right, 0.7F, 1.2F, false, 0F, -1.0F, ageInTicks, begProgress * 0.2F);
            this.flap(arm_right, 0.7F, 0.25F, false, -1F, 0.2F, ageInTicks, begProgress * 0.2F);
            this.walk(arm_left, 0.7F, 1.2F, false, 0F, -1.0F, ageInTicks, begProgress * 0.2F);
            this.flap(arm_left, 0.7F, 0.25F, true, -1F, 0.2F, ageInTicks, begProgress * 0.2F);
        }
        progressRotationPrev(body, washProgress, (float) Math.toRadians(15), 0, 0, 5f);
        progressRotationPrev(arm_left, washProgress, (float) Math.toRadians(-90), 0, 0, 5f);
        progressRotationPrev(arm_right, washProgress, (float) Math.toRadians(-90), 0, 0, 5f);
        progressRotationPrev(leg_left, washProgress, (float) Math.toRadians(-15), 0, 0, 5f);
        progressRotationPrev(leg_right, washProgress, (float) Math.toRadians(-15), 0, 0, 5f);
        progressRotationPrev(tail, washProgress, (float) Math.toRadians(-15), 0, 0, 5f);
        progressRotationPrev(head, washProgress, (float) Math.toRadians(15), 0, 0, 5f);
        progressPositionPrev(head, washProgress, 0, -3F, 0.4F, 5f);
        progressPositionPrev(body, washProgress, 0, 1.5F, -10, 5f);
        progressPositionPrev(arm_left, washProgress, 0, 1F, -1.4F, 5f);
        progressPositionPrev(arm_right, washProgress, 0, 1F, -1.4F, 5f);
        if (washProgress > 0) {
            this.arm_left.rotationPointY -= (float) (-Math.abs(Math.sin(ageInTicks * 0.5F) * (double) washProgress * 0.2D * 1));
            this.arm_left.rotationPointZ -= (float) (-Math.abs(Math.sin(ageInTicks * 0.25F) * (double) washProgress * 0.2D * 3));
            this.arm_right.rotationPointY -= (float) (-Math.abs(Math.sin(ageInTicks * 0.5F) * (double) washProgress * 0.2D * 1));
            this.arm_right.rotationPointZ -= (float) (-Math.abs(Math.cos(ageInTicks * 0.25F) * (double) washProgress * 0.2D * 3));
            this.swing(arm_right, 0.5F, 0.25F, false, 2F, -0.1F, ageInTicks, washProgress * 0.2F);
            this.swing(arm_left, 0.5F, 0.25F, true, 2F, -0.1F, ageInTicks, washProgress * 0.2F);
            float bodyFlap = (float) (Math.sin(ageInTicks * 0.5F) * (double) washProgress * 0.2D * 0.15F);
            body.rotateAngleZ += bodyFlap;
            tail.rotateAngleY += bodyFlap;
            head.rotateAngleZ -= bodyFlap;
            leg_left.rotateAngleZ -= bodyFlap;
            leg_right.rotateAngleZ -= bodyFlap;
        }else{
            this.faceTarget(v3, v4, 1.3F, head);
        }
        if (standProgress <= 0) {
            this.walk(arm_right, walkSpeed, walkDegree * 1.1F, true, 0F, 0F, limbSwing, limbSwingAmount);
            this.walk(arm_left, walkSpeed, walkDegree * 1.1F, false, 0F, 0F, limbSwing, limbSwingAmount);
        }
        this.swing(tail, idleSpeed, idleDegree, false, 2F, 0F, ageInTicks, 1);
        this.swing(body, walkSpeed, walkDegree * 0.3F, false, 3F, 0F, limbSwing, limbSwingAmount);
        this.swing(tail, walkSpeed, walkDegree * 1, false, 4F, 0F, limbSwing, limbSwingAmount);
        this.walk(leg_right, walkSpeed, walkDegree * 1.1F, false, 0F, 0F, limbSwing, limbSwingAmount);
        this.walk(leg_left, walkSpeed, walkDegree * 1.1F, true, 0F, 0F, limbSwing, limbSwingAmount);

    }
}
