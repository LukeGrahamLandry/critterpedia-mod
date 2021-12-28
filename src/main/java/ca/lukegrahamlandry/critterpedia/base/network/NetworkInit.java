package ca.lukegrahamlandry.critterpedia.base.network;

import ca.lukegrahamlandry.critterpedia.ModMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class NetworkInit {
    public static SimpleChannel INSTANCE;
    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerPackets(){
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(ModMain.MOD_ID, "packets"), () -> "1.0", s -> true, s -> true);

        // INSTANCE.registerMessage(nextID(), OpenCritterpediaPacket.class, OpenCritterpediaPacket::toBytes, OpenCritterpediaPacket::new, OpenCritterpediaPacket::handle);
    }
}
