package ca.lukegrahamlandry.critterpedia.content.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.controller.AnimationController;









//make it spawn in frozen oceans
//make it spawn in frozen oceans
//make it spawn in deep frozen oceans

public class AntarcticKrillEntity extends AbstractFish implements IAnimatable {
    public AntarcticKrillEntity(EntityType<? extends AbstractFish> p_27461_, Level p_27462_) {
        super(p_27461_, p_27462_);
    }

    public SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
            return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }
//make it spawn in frozen oceans

    private final AnimationFactory factory = new AnimationFactory(this);
    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return null;
    }

    //make it spawn in frozen oceans

    @Override
    public void registerControllers(AnimationData data) {

    }







    @Override
        public AnimationFactory getFactory() {
            return factory;
        }
}
