package ca.lukegrahamlandry.critterpedia.content.client.render;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.client.models.BowheadGuitarFishModel;
import ca.lukegrahamlandry.critterpedia.content.entity.ModFishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BowheadGuitarFishRender extends MobRenderer<ModFishEntity, BowheadGuitarFishModel<ModFishEntity>> {
    private ResourceLocation TEXTURE = new ResourceLocation(ModMain.MOD_ID, "textures/entity/fish/bow_head_guitar_fish.png");

    public BowheadGuitarFishRender(EntityRendererProvider.Context ctx) {
        super(ctx, new BowheadGuitarFishModel<>(ctx.bakeLayer(BowheadGuitarFishModel.LAYER_LOCATION)), 0.3F);
    }

    public ResourceLocation getTextureLocation(ModFishEntity bat) {
        return TEXTURE;
    }
}