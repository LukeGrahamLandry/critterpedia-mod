package ca.lukegrahamlandry.critterpedia.content.entity.helpers;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import java.util.function.Function;

public class LoopAnimationHandler<E extends LivingEntity & IAnimatable> {
    private final String name;
    private final EntityDataAccessor<Boolean> isActive;
    private final Function<E, Boolean> condition;

    public LoopAnimationHandler(Class<E> clazz, String name, Function<E, Boolean> condition){
        this.name = name;
        this.condition = condition;
        this.isActive = SynchedEntityData.defineId(clazz, EntityDataSerializers.BOOLEAN);
    }

    public void setupData(E entity){
        entity.getEntityData().define(this.isActive, false);
    }

    public void tick(E entity){
        if (!entity.level.isClientSide()){
            entity.getEntityData().set(this.isActive, this.condition.apply(entity));
        }
    }

    public boolean isPlaying(E entity){
        return entity.getEntityData().get(this.isActive);
    }

    public boolean checkAnimation(E entity, AnimationEvent<E> event){
        if (this.isPlaying(entity)){
            event.getController().setAnimation(new AnimationBuilder().addAnimation(this.name, true));
            return true;
        }
        return false;
    }
}
