package ca.lukegrahamlandry.critterpedia.content.entity.helpers;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Panda;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import java.util.HashMap;
import java.util.function.Consumer;

public class AnimationTimer<E extends LivingEntity & IAnimatable> {
    private final String name;
    public final int lengthTicks;
    private final EntityDataAccessor<Integer> currentTick;
    private final HashMap<Integer, Consumer<E>> actions = new HashMap<>();

    public AnimationTimer(Class<E> clazz, String name, int lengthTicks){
        this.name = name;
        this.lengthTicks = lengthTicks;
        this.currentTick = SynchedEntityData.defineId(clazz, EntityDataSerializers.INT);
    }

    public void setAction(int tick, Consumer<E> action){
        this.actions.put(tick, action);
    }

    public void setupData(E entity){
        entity.getEntityData().define(this.currentTick, 0);
    }

    public void tick(E entity){
        if (!entity.level.isClientSide()){
            int tick = entity.getEntityData().get(this.currentTick);
            if (tick > 0){
                System.out.println(tick);
                int actionTick = this.lengthTicks - tick;
                if (actions.containsKey(actionTick)) actions.get(actionTick).accept(entity);
                entity.getEntityData().set(this.currentTick, tick - 1);
            }
        }
    }

    public boolean isPlaying(E entity){
        return entity.getEntityData().get(this.currentTick) > 0;
    }

    public void startPlaying(E entity){
        entity.getEntityData().set(this.currentTick, this.lengthTicks);
    }

    public void stopPlaying(E entity){
        entity.getEntityData().set(this.currentTick, 0);
    }

    public boolean checkAnimation(E entity, AnimationEvent<E> event){
        if (this.isPlaying(entity)){
            event.getController().setAnimation(new AnimationBuilder().addAnimation(this.name, true));
            return true;
        }
        return false;
    }
}
