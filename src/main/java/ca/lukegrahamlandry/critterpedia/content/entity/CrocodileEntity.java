package ca.lukegrahamlandry.critterpedia.content.entity;

import ca.lukegrahamlandry.critterpedia.content.entity.helpers.AnimationTimer;
import ca.lukegrahamlandry.critterpedia.content.entity.helpers.LoopAnimationHandler;
import ca.lukegrahamlandry.critterpedia.content.goals.AnimatedAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Arrays;
import java.util.List;

public class CrocodileEntity extends Animal implements IAnimatable {
    static AnimationTimer<CrocodileEntity> attackTimer = new AnimationTimer<>(CrocodileEntity.class, "animation.saltwatercroc.bite", 20);
    static List<LoopAnimationHandler<CrocodileEntity>> movementAnimations = Arrays.asList(
            new LoopAnimationHandler<>(CrocodileEntity.class, "animation.saltwatercroc.swim", (croc) -> croc.isInWater() && croc.navigation.isInProgress()),
        new LoopAnimationHandler<>(CrocodileEntity.class, "animation.saltwatercroc.float", (croc) -> croc.isInWater() && !croc.navigation.isInProgress()),
         new LoopAnimationHandler<>(CrocodileEntity.class, "animation.saltwatercroc.walk", (croc) -> !croc.isInWater() && croc.navigation.isInProgress()),
        new LoopAnimationHandler<>(CrocodileEntity.class, "animation.saltwatercroc.idle", (croc) -> !croc.isInWater() && !croc.navigation.isInProgress())
    );

    static {
        attackTimer.setAction(15, (croc -> {
            if (croc.hasTarget()){
                croc.doHurtTarget(croc.getTarget());
            }
        }));
    }

    public CrocodileEntity(EntityType<CrocodileEntity> p_29362_, Level p_29363_) {
        super(p_29362_, p_29363_);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new CrocodileEntity.CrocMoveControl(this);
        this.maxUpStep = 1.0F;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.MOVEMENT_SPEED, 0.3).add(Attributes.ATTACK_DAMAGE, 7F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        attackTimer.setupData(this);
        movementAnimations.forEach((anim) -> anim.setupData(this));
    }

    @Override
    public void tick() {
        super.tick();
        attackTimer.tick(this);
        movementAnimations.forEach((anim) -> anim.tick(this));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new CrocGoToWaterGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new CrocRandomStrollGoal(this, 1.0D, 100));
        this.goalSelector.addGoal(3, new CrocGoToWaterGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new AnimatedAttackGoal<>(this, 1.2D, true, attackTimer));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, this::stayInWaterForAttack));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false, this::stayInWaterForAttack));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return false;
    }


    public MobType getMobType() {
        return MobType.WATER;
    }

    private boolean stayInWaterForAttack(LivingEntity target) {
        return this.isInWater() ? target.isInWater() : true;
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(pTravelVector);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    public boolean hasTarget() {
        return this.getTarget() != null && this.getTarget().isAlive();
    }


    // Animations

    private PlayState attackAnimationPredicate(AnimationEvent<CrocodileEntity> event) {
        return attackTimer.checkAnimation(this, event) ? PlayState.CONTINUE : PlayState.STOP;
    }

    private PlayState moveAnimationPredicate(AnimationEvent<CrocodileEntity> event) {
        movementAnimations.forEach((anim) -> anim.checkAnimation(this, event));
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data) {
       data.addAnimationController(new AnimationController(this, "move", 0, this::moveAnimationPredicate));
       data.addAnimationController(new AnimationController(this, "attack", 0, this::attackAnimationPredicate));
    }

    private AnimationFactory factory = new AnimationFactory(this);
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }



    // Movement controllers. Stolen from animal
    protected PathNavigation createNavigation(Level pLevel) {
        return new CrocPathNavigation(this, pLevel);
    }

    static class CrocGoToWaterGoal extends MoveToBlockGoal {
        private final CrocodileEntity animal;

        CrocGoToWaterGoal(CrocodileEntity p_30262_, double p_30263_) {
            super(p_30262_, p_30262_.isBaby() ? 2.0D : p_30263_, 24);
            this.animal = p_30262_;
            this.verticalSearchStart = -1;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.animal.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.animal.level, this.blockPos);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            if (this.animal.isBaby() && !this.animal.isInWater()) {
                return super.canUse();
            } else {
                return !this.animal.isInWater() && super.canUse();
            }
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        /**
         * Return true to set given position as destination
         */
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(Blocks.WATER);
        }
    }

    static class CrocMoveControl extends MoveControl {
        private final CrocodileEntity animal;

        CrocMoveControl(CrocodileEntity p_30286_) {
            super(p_30286_);
            this.animal = p_30286_;
        }

        private void updateSpeed() {
            if (this.animal.isInWater()) {
                this.animal.setDeltaMovement(this.animal.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
                this.animal.setSpeed(Math.max(this.animal.getSpeed() / 2.0F, 0.08F));
            } else if (this.animal.isOnGround()) {
                this.animal.setSpeed(Math.max(this.animal.getSpeed() / 2.0F, 0.06F));
            }
        }

        public void tick() {
            this.updateSpeed();
            if (this.operation == MoveControl.Operation.MOVE_TO && !this.animal.getNavigation().isDone()) {
                double d0 = this.wantedX - this.animal.getX();
                double d1 = this.wantedY - this.animal.getY();
                double d2 = this.wantedZ - this.animal.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 /= d3;
                float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.animal.setYRot(this.rotlerp(this.animal.getYRot(), f, 90.0F));
                this.animal.yBodyRot = this.animal.getYRot();
                float f1 = (float)(this.speedModifier * this.animal.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.animal.setSpeed(Mth.lerp(0.125F, this.animal.getSpeed(), f1));
                this.animal.setDeltaMovement(this.animal.getDeltaMovement().add(0.0D, (double)this.animal.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.animal.setSpeed(0.0F);
            }
        }
    }

    static class CrocPathNavigation extends WaterBoundPathNavigation {
        CrocPathNavigation(CrocodileEntity p_30294_, Level p_30295_) {
            super(p_30294_, p_30295_);
        }

        /**
         * If on ground or swimming and can swim
         */
        protected boolean canUpdatePath() {
            return true;
        }

        protected PathFinder createPathFinder(int p_30298_) {
            this.nodeEvaluator = new AmphibiousNodeEvaluator(true);
            return new PathFinder(this.nodeEvaluator, p_30298_);
        }

        public boolean isStableDestination(BlockPos pPos) {
            return !this.level.getBlockState(pPos.below()).isAir();
        }
    }

    static class CrocRandomStrollGoal extends RandomStrollGoal {
        private final CrocodileEntity animal;

        CrocRandomStrollGoal(CrocodileEntity p_30303_, double p_30304_, int p_30305_) {
            super(p_30303_, p_30304_, p_30305_);
            this.animal = p_30303_;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return !this.mob.isInWater() && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !this.animal.hasTarget();
        }
    }
}
