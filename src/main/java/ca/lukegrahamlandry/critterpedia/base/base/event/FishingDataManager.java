package ca.lukegrahamlandry.critterpedia.base.event;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.api.FishingOption;
import ca.lukegrahamlandry.critterpedia.base.api.FishingOptionLoader;
import ca.lukegrahamlandry.critterpedia.base.api.FishingRarity;
import ca.lukegrahamlandry.critterpedia.base.api.FishingRarityLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerResources;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FishingDataManager {
    static HashMap<ServerResources, FishingOptionLoader> fishingOptions = new HashMap<>();
    static HashMap<ServerResources, FishingRarityLoader> fishingRarity = new HashMap<>();

    @SubscribeEvent
    public static void initCaps(AddReloadListenerEvent event){
        System.out.println("reload listener");

        FishingOptionLoader optionsLoader = new FishingOptionLoader();
        fishingOptions.put(event.getDataPackRegistries(), optionsLoader);
        event.addListener(optionsLoader);

        FishingRarityLoader rarityLoader = new FishingRarityLoader();
        fishingRarity.put(event.getDataPackRegistries(), rarityLoader);
        event.addListener(rarityLoader);
    }

    private static FishingOptionLoader getFishingOptions(ServerLevel world){
        return fishingOptions.get(world.getServer().getServerResources());
    }

    private static FishingRarityLoader getFishingRarities(ServerLevel world){
        return fishingRarity.get(world.getServer().getServerResources());
    }

    // todo: consider biome, etc which will require manually looping through all options to generate the list for that circumstance
    static Random rand = new Random();
    public static FishingOption getFish(Player player, ResourceLocation rod){
        FishingOptionLoader options = getFishingOptions((ServerLevel) player.level);
        Optional<FishingOption> result = options.wightedOptionsByRod.get(rod).getRandom(rand);
        return result.orElse(null);
    }

    public static FishingRarity getRarity(ServerLevel level, ResourceLocation rarityId) {
        return getFishingRarities(level).rarities.get(rarityId);
    }
}