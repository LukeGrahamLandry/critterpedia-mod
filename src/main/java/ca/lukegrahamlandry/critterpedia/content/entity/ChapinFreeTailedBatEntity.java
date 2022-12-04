package ca.lukegrahamlandry.critterpedia.content.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ChapinFreeTailedBatEntity extends Bat implements IAnimatable {

    public ChapinFreeTailedBatEntity(EntityType<? extends Bat> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
    }

    @Override
    public void tick() {
        super.tick();
        // this.setResting(true);
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isResting()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bat.cellingidle", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bat.fly", true));
        }
        if(this.isOnGround())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.bat.groundidle", true));
        }

        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }


    private AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}


