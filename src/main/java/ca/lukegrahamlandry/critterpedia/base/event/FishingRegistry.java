package ca.lukegrahamlandry.critterpedia.base.event;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.api.FishingManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FishingRegistry {
    @SubscribeEvent
    public static void initCaps(AddReloadListenerEvent event){
        event.addListener(new FishingManager());
    }
}