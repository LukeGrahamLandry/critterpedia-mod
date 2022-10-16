package ca.lukegrahamlandry.critterpedia.content.client.geo;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.FlyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FlyGeoModel extends AnimatedGeoModel<FlyEntity> {
    @Override
    public ResourceLocation getModelLocation(FlyEntity object) {
        return new ResourceLocation(ModMain.MOD_ID, "geo/fly.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FlyEntity object) {
        return new ResourceLocation(ModMain.MOD_ID, "textures/entity/fly.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FlyEntity object) {
        return new ResourceLocation(ModMain.MOD_ID, "animations/fly.animation.json");
    }
}