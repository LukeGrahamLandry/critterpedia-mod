package ca.lukegrahamlandry.critterpedia.content.client.geo;

import ca.lukegrahamlandry.critterpedia.ModMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericGeoModel<E extends LivingEntity & IAnimatable> extends AnimatedGeoModel<E> {
    ResourceLocation texture;
    ResourceLocation model;
    ResourceLocation animation;
    public GenericGeoModel(String name){
        this.animation = new ResourceLocation(ModMain.MOD_ID, "animations/" + name + ".animation.json");
        this.model = new ResourceLocation(ModMain.MOD_ID, "geo/" + name + ".geo.json");
        this.texture = new ResourceLocation(ModMain.MOD_ID, "textures/entity/" + name + ".png");
    }
    @Override
    public ResourceLocation getModelLocation(E object) {
        return this.model;
    }

    @Override
    public ResourceLocation getTextureLocation(E object) {
        return this.texture;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(E object) {
        return this.animation;
    }
}