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

public class FloridaHogFishModel<T extends ModFishEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(ModMain.MOD_ID, "florida_hog_fish"), "main");
	private final ModelPart root;
	private final ModelPart Body;
	private final ModelPart Tail;
	private final ModelPart Tailfin;
	private final ModelPart LeftFin;
	private final ModelPart RightFin;
	private final ModelPart MouthLower;

	public FloridaHogFishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.Body = this.root.getChild("Body");
		this.Tail = this.Body.getChild("Tail");
		this.Tailfin = this.Tail.getChild("Tailfin");
		this.LeftFin = this.Body.getChild("LeftFin");
		this.RightFin = this.Body.getChild("RightFin");
		this.MouthLower = this.Body.getChild("MouthLower");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, -6.0F));

		PartDefinition MouthLower = Body.addOrReplaceChild("MouthLower", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.01F, 6.0F, 0.0F));

		PartDefinition MouthLowerTwo = MouthLower.addOrReplaceChild("MouthLowerTwo", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition MouthUpper = Body.addOrReplaceChild("MouthUpper", CubeListBuilder.create().texOffs(14, 0).addBox(-1.0F, 0.0F, -4.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition Snouth = MouthUpper.addOrReplaceChild("Snouth", CubeListBuilder.create().texOffs(0, 4).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -4.0F));

		PartDefinition Forhead1 = MouthUpper.addOrReplaceChild("Forhead1", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(27, 0).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 10.0F));

		PartDefinition DorsalFin2 = Tail.addOrReplaceChild("DorsalFin2", CubeListBuilder.create().texOffs(26, 13).addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition Tailfin = Tail.addOrReplaceChild("Tailfin", CubeListBuilder.create().texOffs(26, 18).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition AnalFin = Tail.addOrReplaceChild("AnalFin", CubeListBuilder.create().texOffs(45, 13).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition Dorsal = Body.addOrReplaceChild("Dorsal", CubeListBuilder.create().texOffs(26, 1).addBox(0.0F, -6.0F, 0.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition AnalFinTwo = Body.addOrReplaceChild("AnalFinTwo", CubeListBuilder.create().texOffs(36, 15).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 3.0F));

		PartDefinition LeftFin = Body.addOrReplaceChild("LeftFin", CubeListBuilder.create().texOffs(12, 18).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 6.0F, 4.0F));

		PartDefinition RightFin = Body.addOrReplaceChild("RightFin", CubeListBuilder.create().texOffs(12, 18).addBox(0.0F, -2.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 6.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = ageInTicks;
		float f1 = 0.3F;
		float speed = 5.0F;
		float degree = 1.0F;

		this.Body.x = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 0.1F) * f1 * 0.5F;
		this.Tail.yRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 0.7F) * f1 * 0.5F;
		this.Tailfin.yRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * 0.7F) * f1 * 0.5F;
		this.Body.yRot = Mth.cos((f * speed * 0.1F) + (float) Math.PI) * (degree * -0.1F) * f1 * 0.5F;
		this.LeftFin.yRot = Mth.cos((f * speed * 0.05F) + (float) Math.PI) * (degree * -1.0F) * f1 * 0.5F + 0.1F;
		this.RightFin.yRot = Mth.cos((f * speed * 0.05F) + (float) Math.PI) * (degree * 1.0F) * f1 * 0.5F + -0.1F;
		this.MouthLower.xRot = Mth.cos(-1.0F + (f * speed * 0.06F) + (float) Math.PI) * (degree * 1.0F) * f1 * 0.5F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, buffer, packedLight, packedOverlay);
	}
}