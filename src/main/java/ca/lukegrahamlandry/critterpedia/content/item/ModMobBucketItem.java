package ca.lukegrahamlandry.critterpedia.content.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModMobBucketItem extends MobBucketItem {
    public ModMobBucketItem(Supplier<? extends EntityType<?>> entitySupplier, Supplier<? extends Fluid> fluidSupplier, Supplier<? extends SoundEvent> soundSupplier, Properties properties) {
        super(entitySupplier, fluidSupplier, soundSupplier, properties);
    }


    // the override shouldnt be nessisary anymore but fixed a crash in an older version of forge (39.0.64)
    @Override
    public void checkExtraContent(@Nullable Player p_151146_, Level p_151147_, ItemStack p_151148_, BlockPos p_151149_) {
        if (p_151147_ instanceof ServerLevel) {
            Entity entity = this.getFishType().spawn((ServerLevel) p_151147_, p_151148_, (Player)null, p_151149_, MobSpawnType.BUCKET, true, false);
            if (entity instanceof Bucketable) {
                Bucketable bucketable = (Bucketable)entity;
                bucketable.loadFromBucketTag(p_151148_.getOrCreateTag());
                bucketable.setFromBucket(true);
            }
            p_151147_.gameEvent(p_151146_, GameEvent.ENTITY_PLACE, p_151149_);
        }

    }

}
