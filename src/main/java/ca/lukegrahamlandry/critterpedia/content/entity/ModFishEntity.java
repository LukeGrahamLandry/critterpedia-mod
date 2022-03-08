package ca.lukegrahamlandry.critterpedia.content.entity;

import ca.lukegrahamlandry.critterpedia.content.init.EntityInit;
import ca.lukegrahamlandry.critterpedia.content.init.ItemInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ModFishEntity extends AbstractSchoolingFish {
    public ModFishEntity(EntityType<? extends ModFishEntity> p_29790_, Level p_29791_) {
        super(p_29790_, p_29791_);
    }

    public int getMaxSchoolSize() {
        return 5;
    }

    public ItemStack getBucketItemStack() {
        ResourceLocation id = this.getType().getRegistryName();
        return new ItemStack(ItemInit.smallFishBuckets.getOrDefault(id, ItemStack.EMPTY::getItem).get());
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_29795_) {
        return SoundEvents.SALMON_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }
}