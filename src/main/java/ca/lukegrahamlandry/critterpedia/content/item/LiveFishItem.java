package ca.lukegrahamlandry.critterpedia.content.item;

import ca.lukegrahamlandry.critterpedia.content.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.registries.ForgeRegistries;

public class LiveFishItem extends Item {
    ResourceLocation entityTypeId;
    public LiveFishItem(Properties p_41383_) {
        super(p_41383_);
        this.entityTypeId = entityTypeId;
    }

    public InteractionResult useOn(UseOnContext p_43223_) {
        Level level = p_43223_.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemstack = p_43223_.getItemInHand();
            BlockPos blockpos = p_43223_.getClickedPos();
            Direction direction = p_43223_.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            if (ForgeRegistries.ENTITIES.getValue(this.entityTypeId).spawn((ServerLevel) level, null, (Player)null, blockpos1, MobSpawnType.SPAWN_EGG, true, false) != null) {
                itemstack.shrink(1);
                level.gameEvent(p_43223_.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
            }

            return InteractionResult.CONSUME;
        }
    }


    public InteractionResultHolder<ItemStack> use(Level p_43225_, Player p_43226_, InteractionHand p_43227_) {
        ItemStack itemstack = p_43226_.getItemInHand(p_43227_);
        HitResult hitresult = getPlayerPOVHitResult(p_43225_, p_43226_, ClipContext.Fluid.SOURCE_ONLY);
        if (hitresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else if (!(p_43225_ instanceof ServerLevel)) {
            return InteractionResultHolder.success(itemstack);
        } else {
            BlockHitResult blockhitresult = (BlockHitResult)hitresult;
            BlockPos blockpos = blockhitresult.getBlockPos();
            if (!(p_43225_.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
                return InteractionResultHolder.pass(itemstack);
            } else if (p_43225_.mayInteract(p_43226_, blockpos) && p_43226_.mayUseItemAt(blockpos, blockhitresult.getDirection(), itemstack)) {
                if (ForgeRegistries.ENTITIES.getValue(this.entityTypeId).spawn((ServerLevel) p_43225_, null, (Player)null, blockpos, MobSpawnType.SPAWN_EGG, true, false) != null) {
                    return InteractionResultHolder.pass(itemstack);
                } else {
                    if (!p_43226_.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    p_43226_.awardStat(Stats.ITEM_USED.get(this));
                    p_43225_.gameEvent(GameEvent.ENTITY_PLACE, p_43226_);
                    return InteractionResultHolder.consume(itemstack);
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    // todo: this method wont fire while its in a chest. fix that
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity player, int index, boolean selected) {
        super.inventoryTick(stack, level, player, index, selected);

        if (!level.isClientSide){
            stack.setDamageValue(stack.getDamageValue() + 1);
            if (stack.getDamageValue() >= (stack.getMaxDamage() - 1)){
                if (player instanceof Player){
                    ((Player) player).getInventory().setItem(index, new ItemStack(ItemInit.rawFish.get(this.entityTypeId).get(), stack.getCount()));
                }
            }
        }

    }
}
