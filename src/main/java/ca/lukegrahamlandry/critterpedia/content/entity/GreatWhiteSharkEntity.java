package ca.lukegrahamlandry.critterpedia.content.entity;

import ca.lukegrahamlandry.critterpedia.content.goals.AttackJumpGoal;
import ca.lukegrahamlandry.critterpedia.content.goals.WaterJumpGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GreatWhiteSharkEntity extends WaterAnimal implements IAnimatable {


    public GreatWhiteSharkEntity(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
    }

    protected void registerGoals(){
        this.goalSelector.addGoal(5, new AttackJumpGoal(this, 10));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

    }
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isInWaterOrBubble()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.great_white_shark.swim", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.great_white_shark.flop", true));
        }

        return PlayState.CONTINUE;
    }
    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
