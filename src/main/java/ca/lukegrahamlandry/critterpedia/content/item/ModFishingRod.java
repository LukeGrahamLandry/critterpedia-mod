package ca.lukegrahamlandry.critterpedia.content.item;

import ca.lukegrahamlandry.critterpedia.content.entity.TriggerGuiFishingHook;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class ModFishingRod extends Item implements Vanishable {
    ResourceLocation rodType;
    public ModFishingRod(Item.Properties p_41285_, ResourceLocation rodType) {
        super(p_41285_);
        this.rodType = rodType;
    }


    public InteractionResultHolder<ItemStack> use(Level p_41290_, Player p_41291_, InteractionHand p_41292_) {
        ItemStack itemstack = p_41291_.getItemInHand(p_41292_);
        if (p_41291_.fishing instanceof TriggerGuiFishingHook) {
            if (!p_41290_.isClientSide) {
                int i = p_41291_.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(i, p_41291_, (p_41288_) -> {
                    p_41288_.broadcastBreakEvent(p_41292_);
                });
            }

            p_41290_.playSound((Player)null, p_41291_.getX(), p_41291_.getY(), p_41291_.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (p_41290_.getRandom().nextFloat() * 0.4F + 0.8F));
            p_41290_.gameEvent(p_41291_, GameEvent.FISHING_ROD_REEL_IN, p_41291_);
        } else {
            p_41290_.playSound((Player)null, p_41291_.getX(), p_41291_.getY(), p_41291_.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (p_41290_.getRandom().nextFloat() * 0.4F + 0.8F));
            if (!p_41290_.isClientSide) {
                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack);
                int j = EnchantmentHelper.getFishingLuckBonus(itemstack);
                TriggerGuiFishingHook bobber = new TriggerGuiFishingHook(p_41291_, p_41290_, j, k);
                bobber.rodType = this.rodType;
                p_41290_.addFreshEntity(bobber);

                System.out.println("make bobber");
            }

            p_41291_.awardStat(Stats.ITEM_USED.get(this));
            p_41290_.gameEvent(p_41291_, GameEvent.FISHING_ROD_CAST, p_41291_);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, p_41290_.isClientSide());
    }

    public int getEnchantmentValue() {
        return 1;
    }
}