package ca.lukegrahamlandry.critterpedia.content.client.render;

import ca.lukegrahamlandry.critterpedia.content.client.geo.GenericGeoModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GenericGeoRender<E extends LivingEntity & IAnimatable> extends GeoEntityRenderer<E> {
    public GenericGeoRender(EntityRendererProvider.Context renderManager, String name) {
        super(renderManager, new GenericGeoModel<E>(name));
        this.shadowRadius = 0.0F;
    }

    @Override
    public RenderType getRenderType(E animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}