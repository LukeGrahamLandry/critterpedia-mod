package ca.lukegrahamlandry.critterpedia.base.network;

import ca.lukegrahamlandry.critterpedia.base.client.gui.FishingMiniGameGUI;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StartFishingMiniGamePacket {
    ResourceLocation fishRarity;
    public StartFishingMiniGamePacket(ResourceLocation fishRarity) {
        this.fishRarity = fishRarity;
    }

    public static StartFishingMiniGamePacket decode(FriendlyByteBuf buf) {
        return new StartFishingMiniGamePacket(buf.readResourceLocation());
    }

    public static void encode(StartFishingMiniGamePacket packet, FriendlyByteBuf buf) {
        buf.writeResourceLocation(packet.fishRarity);
    }

    public static void handle(StartFishingMiniGamePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> doOpen(packet));
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    private static void doOpen(StartFishingMiniGamePacket packet){
        Screen gui = new FishingMiniGameGUI(packet.fishRarity);
        Minecraft.getInstance().setScreen(gui);
    }
}
