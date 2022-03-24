package ca.lukegrahamlandry.critterpedia.content.goals;

import ca.lukegrahamlandry.critterpedia.content.entity.helpers.AnimationTimer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import software.bernie.geckolib3.core.IAnimatable;

public class AnimatedAttackGoal<E extends PathfinderMob & IAnimatable> extends MeleeAttackGoal {
    AnimationTimer<E> animation;
    E owner;
    public AnimatedAttackGoal(E pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, AnimationTimer animation) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.animation = animation;
        this.owner = pMob;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        double d0 = this.getAttackReachSqr(pEnemy);
        if (!this.animation.isPlaying(this.owner) && pDistToEnemySqr <= d0 && this.getTicksUntilNextAttack() <= 0) {
            animation.startPlaying(this.owner);
        }
    }
}
