package ca.lukegrahamlandry.critterpedia.base.network;

import ca.lukegrahamlandry.critterpedia.base.api.FishingOptionLoader;
import ca.lukegrahamlandry.critterpedia.base.api.FishingRarity;
import ca.lukegrahamlandry.critterpedia.base.client.gui.FishingMiniGameGUI;
import ca.lukegrahamlandry.critterpedia.base.event.FishingDataManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StartFishingMiniGamePacket {
    String fishRarityJsonData;
    public StartFishingMiniGamePacket(FishingRarity rarity) { // server only
        this.fishRarityJsonData = rarity.toJson();
    }

    public StartFishingMiniGamePacket(String fishRarity) {
        this.fishRarityJsonData = fishRarity;
    }

    public static StartFishingMiniGamePacket decode(FriendlyByteBuf buf) {
        return new StartFishingMiniGamePacket(buf.readUtf());
    }

    public static void encode(StartFishingMiniGamePacket packet, FriendlyByteBuf buf) {
        buf.writeUtf(packet.fishRarityJsonData);
    }

    public static void handle(StartFishingMiniGamePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> doOpen(packet));
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void doOpen(StartFishingMiniGamePacket packet){
        Screen gui = new FishingMiniGameGUI(new FishingRarity(packet.fishRarityJsonData));
        Minecraft.getInstance().setScreen(gui);
    }
}
