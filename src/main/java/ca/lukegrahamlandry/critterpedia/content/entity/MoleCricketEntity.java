package ca.lukegrahamlandry.critterpedia.content.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RunAroundLikeCrazyGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MoleCricketEntity extends Animal implements IAnimatable {





    protected void registerGoals() {
      this.goalSelector.addGoal(0, new WanderGoal(this, 0.6D));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
this.goalSelector.addGoal(4, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.0D, 1.2D));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    private final AnimationFactory factory = new AnimationFactory(this);

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    public MoleCricketEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }
}
class WanderGoal extends Goal {
        public WanderGoal(MoleCricketEntity moleCricketEntity, double v) {
        }

        @Override
        public boolean canUse() {
            return false;
        }
    }

    class LookRandomlyGoal extends Goal {
        public LookRandomlyGoal(MoleCricketEntity moleCricketEntity) {
        }

        @Override
        public boolean canUse() {
            return false;
        }
    }


