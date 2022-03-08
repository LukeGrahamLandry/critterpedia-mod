package ca.lukegrahamlandry.critterpedia.base.network;

import ca.lukegrahamlandry.critterpedia.base.client.gui.FishingMiniGameGUI;
import ca.lukegrahamlandry.critterpedia.base.event.FishingGameHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EndFishingMiniGamePacket {
    boolean success;
    public EndFishingMiniGamePacket(boolean success) {
        this.success = success;
    }

    public static EndFishingMiniGamePacket decode(FriendlyByteBuf buf) {
        return new EndFishingMiniGamePacket(buf.readBoolean());
    }

    public static void encode(EndFishingMiniGamePacket packet, FriendlyByteBuf buf) {
        buf.writeBoolean(packet.success);
    }

    public static void handle(EndFishingMiniGamePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            FishingGameHandler.finishFishing(ctx.get().getSender(), packet.success);
        });
        ctx.get().setPacketHandled(true);
    }
}
