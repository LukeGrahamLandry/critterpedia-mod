package ca.lukegrahamlandry.critterpedia.content.client.render;

import ca.lukegrahamlandry.critterpedia.content.client.models.FlyGeoModel;
import ca.lukegrahamlandry.critterpedia.content.client.models.MantaRayGeoModel;
import ca.lukegrahamlandry.critterpedia.content.entity.FlyEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.MantaRayEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class MantaRayGeoRender extends GeoEntityRenderer<MantaRayEntity> {
    public MantaRayGeoRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MantaRayGeoModel());
        this.shadowRadius = 0.8F;
    }

    @Override
    public RenderType getRenderType(MantaRayEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}