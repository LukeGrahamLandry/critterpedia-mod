package ca.lukegrahamlandry.critterpedia.content.client.models;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.FlyEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.MantaRayEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.ModFishEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MantaRayGeoModel extends AnimatedGeoModel<MantaRayEntity> {
    @Override
    public ResourceLocation getModelLocation(MantaRayEntity object) {
        return new ResourceLocation(ModMain.MOD_ID, "geo/manta_ray.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MantaRayEntity object) {
        return new ResourceLocation(ModMain.MOD_ID, "textures/entity/fish/manta_ray.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MantaRayEntity object) {
        return new ResourceLocation(ModMain.MOD_ID, "animations/manta_ray.animation.json");
    }
}