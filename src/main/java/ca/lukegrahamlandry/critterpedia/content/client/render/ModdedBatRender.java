package ca.lukegrahamlandry.critterpedia.content.client.render;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.client.models.ModdedBatModel;
import ca.lukegrahamlandry.critterpedia.content.entity.MegaBatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class ModdedBatRender extends MobRenderer<MegaBatEntity, ModdedBatModel> {
    private ResourceLocation TEXTURE;

    public ModdedBatRender(EntityRendererProvider.Context ctx, Function<EntityRendererProvider.Context, ModdedBatModel> model) {
        super(ctx, model.apply(ctx), 0.3F);
    }

    @Override
    protected void setupRotations(MegaBatEntity bat, PoseStack stack, float p_115319_, float p_115320_, float p_115321_) {
        super.setupRotations(bat, stack, p_115319_, p_115320_, p_115321_);
        if (bat.isResting()){
            stack.translate(0, 0.5, 0);

            String name = bat.getType().getRegistryName().getPath();
            if (name.equals("hammer_head_bat") || name.equals("eastern_red_bat")){
                stack.translate(0, 0, 0.5);
            }
            if (name.equals("speckled_flying_fox")){
                stack.translate(0, 0, -0.75);
            }
            stack.mulPose(Vector3f.XN.rotationDegrees(90));

        }
    }

    public ResourceLocation getTextureLocation(MegaBatEntity bat) {
        if (TEXTURE == null){
            TEXTURE = new ResourceLocation(ModMain.MOD_ID, "textures/entity/bat/" + bat.getType().getRegistryName().getPath() + ".png");
        }

        return TEXTURE;
    }
}