package ca.lukegrahamlandry.critterpedia.content.client.render;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.client.models.FloridaHogFishModel;
import ca.lukegrahamlandry.critterpedia.content.entity.ModFishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FloridaHogFishRender extends MobRenderer<ModFishEntity, FloridaHogFishModel<ModFishEntity>> {
    private ResourceLocation TEXTURE = new ResourceLocation(ModMain.MOD_ID, "textures/entity/fish/florida_hog_fish.png");

    public FloridaHogFishRender(EntityRendererProvider.Context ctx) {
        super(ctx, new FloridaHogFishModel<>(ctx.bakeLayer(FloridaHogFishModel.LAYER_LOCATION)), 0.3F);
    }

    public ResourceLocation getTextureLocation(ModFishEntity bat) {
        return TEXTURE;
    }
}