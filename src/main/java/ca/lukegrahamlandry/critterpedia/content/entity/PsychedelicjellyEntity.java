package ca.lukegrahamlandry.critterpedia.content.entity;

import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PsychedelicjellyEntity extends AbstractFish implements IAnimatable {


    public PsychedelicjellyEntity(EntityType<PsychedelicjellyEntity> p_29362_, Level p_29363_) {

        super(p_29362_, p_29363_);
    }


    private AnimationFactory factory = new AnimationFactory(this);

    @Override
    public void registerControllers(AnimationData data) {

    }

    private float tx;
    private float ty;
    private float tz;

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PsychedelicjellyEntity.SquidRandomMovementGoal(this));
        this.goalSelector.addGoal(1, new PsychedelicjellyEntity.SquidFleeGoal());
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.isUnderWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.PsychedelicJelly.swim", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.PsychedelicJelly.idle", true));
        }

        return PlayState.CONTINUE;
    }


    //note: I just wanna do this math. 5.0D = 2.5 hearts, sounds good
    public static AttributeSupplier.Builder createAttributes() {
        return Parrot.createAttributes().add(Attributes.MAX_HEALTH, 5.0D).add(Attributes.ATTACK_DAMAGE, 2.0F);

    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }


    @Override
    public ItemStack getBucketItemStack() {
        return null;
    }

    public void playerTouch(Player pEntity) {

        if (pEntity instanceof ServerPlayer && pEntity.hurt(DamageSource.mobAttack(this), (float) (1))) {
            if (!this.isSilent()) {
                ((ServerPlayer) pEntity).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0F));
            }

            pEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }


    }

    private void touch(PsychedelicjellyEntity p_29606_) {

        if (p_29606_.hurt(DamageSource.mobAttack(this), (float) (1))) {
            p_29606_.addEffect(new MobEffectInstance(MobEffects.POISON, 60 * 1, 0), this);
            this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0F, 1.0F);
        }
    }

    class SquidRandomMovementGoal extends Goal {
        private final PsychedelicjellyEntity squid;

        public SquidRandomMovementGoal(PsychedelicjellyEntity p_30004_) {
            this.squid = p_30004_;
        }


        public boolean canUse() {
            return true;
        }



        public void tick() {
            int i = this.squid.getNoActionTime();
            if (i > 100) {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.squid.getRandom().nextInt(reducedTickDelay(50)) == 0 || !this.squid.isInWaterOrBubble() || !this.squid.hasMovementVector()) {
                float f = this.squid.getRandom().nextFloat() * ((float) Math.PI * 2F);
                float f1 = Mth.cos(f) * 0.2F;
                float f2 = -0.1F + this.squid.getRandom().nextFloat() * 0.2F;
                float f3 = Mth.sin(f) * 0.2F;
                this.squid.setMovementVector(f1, f2, f3);
            }

        }
    }
    public void setMovementVector(float pRandomMotionVecX, float pRandomMotionVecY, float pRandomMotionVecZ) {
        this.tx = pRandomMotionVecX;
        this.ty = pRandomMotionVecY;
        this.tz = pRandomMotionVecZ;
    }
    public boolean hasMovementVector() {
        return this.tx != 0.0F || this.ty != 0.0F || this.tz != 0.0F;
    }

    class SquidFleeGoal extends Goal {
        private static final float SQUID_FLEE_SPEED = 3.0F;
        private static final float SQUID_FLEE_MIN_DISTANCE = 5.0F;
        private static final float SQUID_FLEE_MAX_DISTANCE = 10.0F;
        private int fleeTicks;

        @Override
        public boolean canUse() {
            return false;
        }
    }

}

