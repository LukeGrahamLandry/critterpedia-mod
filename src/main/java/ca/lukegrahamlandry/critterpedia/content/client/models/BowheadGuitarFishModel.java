package ca.lukegrahamlandry.critterpedia.content.client.models;// Made with Blockbench 4.1.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.ModFishEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BowheadGuitarFishModel<T extends ModFishEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ModMain.MOD_ID, "bowhead_guitar_fish"), "main");
	private final ModelPart root;
	private final ModelPart Body;
	private final ModelPart Tail;
	private final ModelPart TailTip;
	private final ModelPart TailFin;
	private final ModelPart TailFin2;
	private final ModelPart LeftWing;
	private final ModelPart RightWing;
	private final ModelPart LeftAnalFin;
	private final ModelPart RightAnalFin;
	private final ModelPart DorsalFin2;
	private final ModelPart DorsalFin1;
	private final ModelPart Body2;

	public BowheadGuitarFishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.Body = this.root.getChild("Body");
		this.Body2 = this.Body.getChild("Body2");
		this.Tail = this.Body2.getChild("Tail");
		this.TailTip = this.Tail.getChild("TailTip");
		this.TailFin = this.TailTip.getChild("TailFin");
		this.TailFin2 = this.TailTip.getChild("TailFin2");
		this.LeftWing = this.Body.getChild("LeftWing");
		this.RightWing = this.Body.getChild("RightWing");
		this.LeftAnalFin = this.Tail.getChild("LeftAnalFin");
		this.RightAnalFin = this.Tail.getChild("RightAnalFin");
		this.DorsalFin1 = this.Tail.getChild("DorsalFin1");
		this.DorsalFin2 = this.Tail.getChild("DorsalFin2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 0.0F, 0.0F, 9.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, -8.0F));

		PartDefinition Mouth = Body.addOrReplaceChild("Mouth", CubeListBuilder.create().texOffs(0, 19).addBox(-3.5F, 0.0F, -2.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition Body2 = Body.addOrReplaceChild("Body2", CubeListBuilder.create().texOffs(20, 19).addBox(-3.5F, -2.5F, 0.0F, 7.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.5F, 12.0F));

		PartDefinition Tail = Body2.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(31, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 2.0F));

		PartDefinition TailTip = Tail.addOrReplaceChild("TailTip", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 9.0F));

		PartDefinition TailFin = TailTip.addOrReplaceChild("TailFin", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 4.0F));

		PartDefinition TailFin2 = TailTip.addOrReplaceChild("TailFin2", CubeListBuilder.create().texOffs(0, 34).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 3.0F));

		PartDefinition LeftAnalFin = Tail.addOrReplaceChild("LeftAnalFin", CubeListBuilder.create().texOffs(25, 45).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.5F, 1.0F, 0.0F, 0.0F, 0.3491F));

		PartDefinition RightAnalFin = Tail.addOrReplaceChild("RightAnalFin", CubeListBuilder.create().texOffs(25, 45).mirror().addBox(0.0F, 0.0F, 0.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 0.5F, 1.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition DorsalFin1 = Tail.addOrReplaceChild("DorsalFin1", CubeListBuilder.create().texOffs(15, 29).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition DorsalFin2 = Tail.addOrReplaceChild("DorsalFin2", CubeListBuilder.create().texOffs(30, 29).addBox(-0.5F, -4.0F, 0.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 5.0F));

		PartDefinition LeftWing = Body.addOrReplaceChild("LeftWing", CubeListBuilder.create().texOffs(0, 45).addBox(-7.0F, 0.0F, 0.0F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 4.0F, 3.0F));

		PartDefinition RightWing = Body.addOrReplaceChild("RightWing", CubeListBuilder.create().texOffs(0, 45).mirror().addBox(0.0F, 0.0F, 0.0F, 7.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.5F, 4.0F, 3.0F));

		PartDefinition DorsalRidge = Body.addOrReplaceChild("DorsalRidge", CubeListBuilder.create().texOffs(40, 50).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = ageInTicks;
		float f1 = 0.3F;
		float speed = 4.0F;
		float degree = 1.0F;
		this.Body.yRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 0.7F) * f1 * 0.5F;
		this.Tail.yRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 1.0F) * f1 * 0.5F;
		this.TailTip.yRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 1.0F) * f1 * 0.5F;
		this.TailFin.yRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * -0.5F) * f1 * 0.5F;
		this.TailFin2.yRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * -0.5F) * f1 * 0.5F;
		this.LeftWing.xRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 0.5F) * f1 * 0.5F;
		this.RightWing.xRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 0.5F) * f1 * 0.5F;
		this.LeftAnalFin.xRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * 0.3F) * f1 * 0.5F;
		this.RightAnalFin.xRot = Mth.cos((f * speed * 0.2F) + (float) Math.PI) * (degree * 0.3F) * f1 * 0.5F;
		this.DorsalFin1.zRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * -0.5F) * f1 * 0.5F;
		this.DorsalFin2.zRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 0.5F) * f1 * 0.5F;
		this.LeftWing.zRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 0.5F) * f1 * 0.5F;
		this.RightWing.zRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * -0.5F) * f1 * 0.5F;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, buffer, packedLight, packedOverlay);
	}
}