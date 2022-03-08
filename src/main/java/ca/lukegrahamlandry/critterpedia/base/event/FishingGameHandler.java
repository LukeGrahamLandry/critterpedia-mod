package ca.lukegrahamlandry.critterpedia.base.event;

import ca.lukegrahamlandry.critterpedia.base.api.FishingManager;
import ca.lukegrahamlandry.critterpedia.base.api.FishingOption;
import ca.lukegrahamlandry.critterpedia.base.network.NetworkInit;
import ca.lukegrahamlandry.critterpedia.base.network.StartFishingMiniGamePacket;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.UUID;

public class FishingGameHandler {
    static HashMap<UUID, FishingOption> inProgress = new HashMap<>();

    static public void triggerOpenGUI(ServerPlayer player, ResourceLocation rodType){
        FishingOption option = FishingManager.getFish(rodType);
        inProgress.put(player.getUUID(), option);
        NetworkInit.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new StartFishingMiniGamePacket(option.getRarity()));
    }

    static public void finishFishing(ServerPlayer player, boolean success){
        FishingOption option = inProgress.get(player.getUUID());
        inProgress.remove(player.getUUID());
        if (option == null) return;

        if (success){
            player.sendMessage(new TextComponent("you caught a fish! " + option.id.toString()), ChatType.GAME_INFO, player.getUUID());

            for (ItemStack item : option.getItems()){
                makeFishItem(player, item);
            }
        } else {
            player.sendMessage(new TextComponent("oops, the fish escaped"), ChatType.GAME_INFO, player.getUUID());
        }
    }

    static private void makeFishItem(ServerPlayer player, ItemStack itemstack){
        ItemEntity itementity = new ItemEntity(player.level, player.getX()+0.5, player.getY()+1, player.getZ()+0.5, itemstack);
        itementity.setNoPickUpDelay();
        player.level.addFreshEntity(itementity);
        player.level.addFreshEntity(new ExperienceOrb(player.level, player.getX(), player.getY() + 0.5D, player.getZ() + 0.5D, player.getRandom().nextInt(6) + 1));
    }
}
