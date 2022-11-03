package ca.lukegrahamlandry.critterpedia.content.client.models;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.MegaBatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.function.Supplier;

public class ModdedBatModel extends EntityModel<MegaBatEntity> {
    protected ModelPart root;
    private ModelPart Body;
    private ModelPart LeftWing;
    private ModelPart LeftWingTwo;
    private ModelPart Body2;
    private ModelPart Legs;
    private ModelPart RightWing;
    private ModelPart Head;
    private ModelPart RightWingTwo;
    private ModelPart Snout;
    private ModelPart Ears;

    public ModdedBatModel(ModelPart r) {
        this.root = r.getChild("root");
        this.Body = this.root.getChild("Body");
        this.LeftWing = Body.getChild("LeftWing");
        this.LeftWingTwo = LeftWing.getChild("LeftWingTwo");
        // this.Body2 = Body.getChild("Body2");
        try {
            this.Legs = Body.getChild("Legs");
        } catch (Exception ignored){

        }
        this.RightWing = Body.getChild("RightWing");
        try {
            this.Head = Body.getChild("Head");
        } catch (Exception ignored){

        }
        this.RightWingTwo = RightWing.getChild("RightWingTwo");
        // this.Snout = Head.getChild("Snout");
        // this.Ears = Head.getChild("Ears");
    }

    public ModdedBatModel(EntityRendererProvider.Context ctx, String rl) {
        this(ctx.bakeLayer(new ModelLayerLocation(new ResourceLocation(ModMain.MOD_ID, rl), "main")));
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.translate(0, 1, 0);
        this.root.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(MegaBatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = ageInTicks;
        float f1 = 0.3F;

        float speed = 1.0F;
        float degree = 1.0F;


        if (entity.isResting()){
            boolean isWeird = entity.getType().getRegistryName().getPath().equals("hoary_bat") || entity.getType().getRegistryName().getPath().equals("big_eared_bat");


            if (isWeird) {
                // gotta do animation

            } else {
                this.RightWing.zRot = - (degree * 5.0F) * f1 * 1;
                this.LeftWing.zRot = - (degree * -5.0F) * f1 * 1;

                this.RightWing.yRot = - (degree * 1.0F) * f1 * 1F;
                this.LeftWing.yRot = - (degree * -1.0F) * f1 * 1;

                this.RightWingTwo.yRot = - (degree * 5.0F) * f1 * 1.2F;
                this.LeftWingTwo.yRot = - (degree * -5.0F) * f1 * 1.2F;
            }

        } else {
            this.RightWing.zRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * 5.0F) * f1 * 0.5F;
            this.RightWingTwo.yRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * 5.0F) * f1 * 0.5F;
            this.RightWing.yRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * 1.0F) * f1 * 0.5F;
            this.LeftWing.zRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * -5.0F) * f1 * 0.5F;
            this.LeftWingTwo.yRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * -5.0F) * f1 * 0.5F;
            this.LeftWing.yRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * -1.0F) * f1 * 0.5F;
            this.Body.y = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * 1.0F) * f1 * 0.5F;
            this.Body.xRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * 1.0F) * f1 * 0.5F;
            this.Body.xRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * -1.0F) * f1 * 0.5F;
            if (this.Head != null){
                this.Head.y = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * -0.2F) * f1 * 0.5F;
                this.Head.xRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * -0.2F) * f1 * 0.5F;
            }
            if (this.Legs != null){
                this.Legs.xRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * -1.0F) * f1 * 0.5F;
            }
        }
    }


    public static Supplier<LayerDefinition> getModelFor(String rl){
        switch (rl){
            case "speckled_flying_fox":
                return () -> {
                    MeshDefinition meshdefinition = new MeshDefinition();
                    PartDefinition partdefinition = meshdefinition.getRoot();

                    PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
                    PartDefinition Body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(25, 0).addBox(-3.5F, -2.0F, 0.0F, 7.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -5.0F));
                    PartDefinition LeftWing = Body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(-8.0F, -1.0F, 0.0F, 8.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 0.5F, 1.0F, 1.5708F, 0.0F, 0.0F));
                    PartDefinition LeftWingTwo = LeftWing.addOrReplaceChild("LeftWingTwo", CubeListBuilder.create().texOffs(21, 11).addBox(-13.0F, -1.0F, 0.0F, 13.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, 0.0F, 0.0F));
                    PartDefinition Body2 = Body.addOrReplaceChild("Body2", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));
                    PartDefinition Legs = Body.addOrReplaceChild("Legs", CubeListBuilder.create().texOffs(0, 50).addBox(-3.0F, -0.5F, 0.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 5.0F));
                    PartDefinition RightWing = Body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, -1.0F, 0.0F, 8.0F, 15.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.5F, 1.0F, 1.5708F, 0.0F, 0.0F));
                    PartDefinition RightWingTwo = RightWing.addOrReplaceChild("RightWingTwo", CubeListBuilder.create().texOffs(21, 11).mirror().addBox(0.0F, -1.0F, 0.0F, 13.0F, 13.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 0.0F, 0.0F));
                    PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 30).addBox(-3.0F, -2.5F, -4.0F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
                    PartDefinition Snout = Head.addOrReplaceChild("Snout", CubeListBuilder.create().texOffs(30, 30).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -7.0F));
                    PartDefinition Ears = Head.addOrReplaceChild("Ears", CubeListBuilder.create().texOffs(23, 45).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, -2.0F));

                    return LayerDefinition.create(meshdefinition, 64, 64);
                };
            case "eastern_red_bat":
                return () -> {
                    MeshDefinition meshdefinition = new MeshDefinition();
                    PartDefinition partdefinition = meshdefinition.getRoot();

                    PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
                    PartDefinition Body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.0F, 0.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -1.0F));
                    PartDefinition LeftWing = Body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(-5.0F, -1.0F, 0.0F, 5.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 0.5F, 0.0F, 1.5708F, 0.0F, 0.0F));
                    PartDefinition LeftWingTwo = LeftWing.addOrReplaceChild("LeftWingTwo", CubeListBuilder.create().texOffs(13, 11).addBox(-9.0F, -1.0F, 0.0F, 9.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 0.0F, 0.0F));
                    PartDefinition RightWing = Body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, -1.0F, 0.0F, 5.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.5F, 0.0F, 1.5708F, 0.0F, 0.0F));
                    PartDefinition RightWingTwo = RightWing.addOrReplaceChild("RightWingTwo", CubeListBuilder.create().texOffs(13, 11).mirror().addBox(0.0F, -1.0F, 0.0F, 9.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, 0.0F));
                    PartDefinition Legs = Body.addOrReplaceChild("Legs", CubeListBuilder.create().texOffs(3, 26).addBox(-2.5F, -0.5F, 0.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.99F, 6.0F));
                    PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(28, 2).addBox(-2.5F, -2.0F, -4.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.01F, 0.0F, 0.0F));
                    PartDefinition Ear1 = Head.addOrReplaceChild("Ear1", CubeListBuilder.create().texOffs(31, 19).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -2.0F, -3.0F));
                    PartDefinition Ear2 = Head.addOrReplaceChild("Ear2", CubeListBuilder.create().texOffs(31, 19).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -2.0F, -3.0F));

                    return LayerDefinition.create(meshdefinition, 64, 32);
                };

            case "hammer_head_bat":
                return () -> {
                    MeshDefinition meshdefinition = new MeshDefinition();
                    PartDefinition partdefinition = meshdefinition.getRoot();

                    PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
                    PartDefinition Body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.0F, 0.0F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -1.0F));
                    PartDefinition LeftWing = Body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(-5.0F, -1.0F, 0.0F, 5.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 0.5F, 0.0F, 1.5708F, 0.0F, 0.0F));
                    PartDefinition LeftWingTwo = LeftWing.addOrReplaceChild("LeftWingTwo", CubeListBuilder.create().texOffs(14, 11).addBox(-8.0F, -1.0F, 0.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 0.0F, 0.0F));
                    PartDefinition RightWing = Body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, -1.0F, 0.0F, 5.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.5F, 0.0F, 1.5708F, 0.0F, 0.0F));
                    PartDefinition RightWingTwo = RightWing.addOrReplaceChild("RightWingTwo", CubeListBuilder.create().texOffs(14, 11).mirror().addBox(0.0F, -1.0F, 0.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, 0.0F));
                    PartDefinition Legs = Body.addOrReplaceChild("Legs", CubeListBuilder.create().texOffs(18, 0).addBox(-3.5F, -0.5F, 0.0F, 7.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.99F, 6.0F));
                    PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(30, 15).addBox(-2.0F, -2.0F, -7.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
                    PartDefinition Ears = Head.addOrReplaceChild("Ears", CubeListBuilder.create().texOffs(39, 3).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -2.0F));

                    return LayerDefinition.create(meshdefinition, 64, 32);
                };

            case "hoary_bat":
                return () -> {
                    MeshDefinition meshdefinition = new MeshDefinition();
                    PartDefinition partdefinition = meshdefinition.getRoot();

                    PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
                    PartDefinition body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 20).addBox(-2.5F, -2.0F, -3.5F, 5.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));
                    PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(10, 15).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 4.5F));
                    PartDefinition nose = body.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(19, 24).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.5F));
                    PartDefinition wing1Left = body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(-7, 11).mirror().addBox(-6.0F, 0.0F, -4.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, 1.0F, 2.5F));
                    PartDefinition wing2Left = wing1Left.addOrReplaceChild("LeftWingTwo", CubeListBuilder.create().texOffs(-7, 2).addBox(-6.0F, 0.0F, -1.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 0.0F, -3.0F));
                    PartDefinition wing1Right = body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(-7, 11).addBox(0.0F, 0.0F, -4.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 1.0F, 2.5F));
                    PartDefinition wing2Right = wing1Right.addOrReplaceChild("RightWingTwo", CubeListBuilder.create().texOffs(-7, 2).mirror().addBox(0.0F, 0.0F, -1.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 0.0F, -3.0F));
                    PartDefinition earRight = body.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(1, 21).addBox(0.0F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -1.0F, -2.5F));
                    PartDefinition earLeft = body.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(1, 21).mirror().addBox(-1.0F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, -1.0F, -2.5F));

                    return LayerDefinition.create(meshdefinition, 64, 32);
                };

            case "big_eared_bat":
                return () -> {
                    MeshDefinition meshdefinition = new MeshDefinition();
                    PartDefinition partdefinition = meshdefinition.getRoot();

                    PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
                    PartDefinition body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 22).addBox(-2.5F, -2.0F, -3.5F, 5.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));
                    PartDefinition earLeft = body.addOrReplaceChild("earLeft", CubeListBuilder.create().texOffs(0, 13).addBox(-1.0F, -6.0F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -1.0F, -2.0F, 0.0F, 0.0F, -0.2618F));
                    PartDefinition earRight = body.addOrReplaceChild("earRight", CubeListBuilder.create().texOffs(0, 13).mirror().addBox(-2.0F, -6.0F, -0.5F, 3.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.5F, -1.0F, -2.0F, 0.0F, 0.0F, 0.2618F));
                    PartDefinition wing1Right = body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(1, 13).addBox(0.0F, 0.0F, -4.0F, 7.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 1.0F, 0.5F));
                    PartDefinition wing2Right = wing1Right.addOrReplaceChild("RightWingTwo", CubeListBuilder.create().texOffs(-8, 4).mirror().addBox(0.0F, 0.0F, -1.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 0.0F, -3.0F));
                    PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(9, 8).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 2.5F));
                    PartDefinition nose = body.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(17, 24).addBox(-1.5F, -1.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -3.5F));
                    PartDefinition wing1Left = body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(1, 13).mirror().addBox(-7.0F, 0.0F, -4.0F, 7.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.5F, 1.0F, 0.5F));
                    PartDefinition wing2Left = wing1Left.addOrReplaceChild("LeftWingTwo", CubeListBuilder.create().texOffs(-8, 4).addBox(-6.0F, 0.0F, -1.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, 0.0F, -3.0F));

                    return LayerDefinition.create(meshdefinition, 32, 32);
                };
        }

        return null;
    }
}
