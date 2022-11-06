package ca.lukegrahamlandry.critterpedia.content.entity;

import ca.lukegrahamlandry.critterpedia.content.entity.helpers.AnimationTimer;
import ca.lukegrahamlandry.critterpedia.content.entity.helpers.LoopAnimationHandler;
import ca.lukegrahamlandry.critterpedia.content.goals.AnimatedAttackGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Arrays;
import java.util.List;

public class CommonSnappingTurtleEntity extends Animal implements IAnimatable{



    public CommonSnappingTurtleEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);


    }

    static List<LoopAnimationHandler<CommonSnappingTurtleEntity>> movementAnimations = Arrays.asList(
            new LoopAnimationHandler<>(CommonSnappingTurtleEntity.class, "animation.commonSnappingTurtle.swim", (croc) -> croc.isInWater() && croc.navigation.isInProgress()),
            new LoopAnimationHandler<>(CommonSnappingTurtleEntity.class, "animation.commonSnappingTurtle.float", (croc) -> croc.isInWater() && !croc.navigation.isInProgress()),
            new LoopAnimationHandler<>(CommonSnappingTurtleEntity.class, "animation.commonSnappingTurtle.walk", (croc) -> !croc.isInWater() && croc.navigation.isInProgress()),
            new LoopAnimationHandler<>(CommonSnappingTurtleEntity.class, "animation.commonSnappingTurtle.idle", (croc) -> !croc.isInWater() && !croc.navigation.isInProgress())
    );



    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
    }
    



    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public void registerControllers(AnimationData data) {

    }



    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return false;
    }


    private final AnimationFactory factory = new AnimationFactory(this);

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        movementAnimations.forEach((anim) -> anim.setupData(this));
    }
    }
