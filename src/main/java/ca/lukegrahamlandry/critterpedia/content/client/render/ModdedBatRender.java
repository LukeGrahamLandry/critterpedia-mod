package ca.lukegrahamlandry.critterpedia.content.client.render;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.client.models.ModdedBatModel;
import ca.lukegrahamlandry.critterpedia.content.entity.ModdedBatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class ModdedBatRender extends MobRenderer<ModdedBatEntity, ModdedBatModel> {
    private ResourceLocation TEXTURE;

    public ModdedBatRender(EntityRendererProvider.Context ctx, Function<EntityRendererProvider.Context, ModdedBatModel> model) {
        super(ctx, model.apply(ctx), 0.3F);
    }

    public ResourceLocation getTextureLocation(ModdedBatEntity bat) {
        if (TEXTURE == null){
            TEXTURE = new ResourceLocation(ModMain.MOD_ID, "textures/entity/bat/" + bat.getType().getRegistryName().getPath() + ".png");
        }

        return TEXTURE;
    }
}