package ca.lukegrahamlandry.critterpedia.base.client;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.client.gui.CritterpediaScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class KeyboardEvents {
    public static final KeyMapping OPEN = new KeyMapping("key.open_critters", GLFW.GLFW_KEY_M, "key.categories.critterpedia");

    @SubscribeEvent
    public static void onPress(InputEvent.KeyInputEvent event){
        if (Minecraft.getInstance().player == null) return;

        if (OPEN.isDown() && Minecraft.getInstance().screen == null) {
            // NetworkInit.INSTANCE.sendToServer(new OpenCritterpediaPacket());
            Minecraft.getInstance().setScreen(new CritterpediaScreen());
        }
    }
}