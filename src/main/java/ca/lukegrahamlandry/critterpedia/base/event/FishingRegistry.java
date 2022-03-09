package ca.lukegrahamlandry.critterpedia.base.event;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.api.FishingManager;
import ca.lukegrahamlandry.critterpedia.base.api.FishingRarityLoader;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FishingRegistry {
    @SubscribeEvent
    public static void initCaps(AddReloadListenerEvent event){
        System.out.println("reload listener");
        event.addListener(new FishingManager());
        event.addListener(new FishingRarityLoader());
    }
}