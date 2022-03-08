package ca.lukegrahamlandry.critterpedia.base.api;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class FishingManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogManager.getLogger();

    // todo: these should be not static and gotten from level.getServer().getServerResources() somehow
    public static Map<ResourceLocation, FishingOption> options = new HashMap<>();
    public static Map<ResourceLocation, WeightedRandomList<FishingOption>>  wightedOptionsByRod = new HashMap<>();

    public static Map<ResourceLocation, FishingRarity> rarities = new HashMap<>();

    public FishingManager() {
        super(GSON, "fishing");
    }

    // todo: clearing every apply seems like a bad idea cause it might run another time for each data pack
    // so instead the WeightedRandomList should be another map so it just overwrites when it loads another

    protected void apply(Map<ResourceLocation, JsonElement> p_44037_, ResourceManager p_44038_, ProfilerFiller p_44039_) {
        options.clear();
        wightedOptionsByRod.clear();
        Map<ResourceLocation, List<FishingOption>> optionsByRod = new HashMap<>();

        for (Map.Entry<ResourceLocation, JsonElement> entry : p_44037_.entrySet()) {
            ResourceLocation name = entry.getKey();
            if (name.getPath().startsWith("_")) continue; //Forge: filter anything beginning with "_" as it's used for metadata.

            try {
                JsonObject data = entry.getValue().getAsJsonObject();

                FishingOption option = new FishingOption(name);

                if (data.has("critter")){
                    option.triggeredCritter = rl(data.get("critter"));
                } else {
                    option.triggeredCritter = name;
                }

                ResourceLocation item = rl(data.get("item"));
                if (ForgeRegistries.ITEMS.containsKey(item)){
                    option.fishItem = item;
                } else {
                    LOGGER.error("skipping fishing option {}, has an unregistered result item {}", name, item);
                    continue;
                }

                if (data.has("rarity")){
                    if (rarities.containsKey(rl(data.get("rarity")))){
                        option.rarity = rl(data.get("rarity"));
                    } else {
                        LOGGER.error("skipping fishing option {}, undefined rarity {}", name, rl(data.get("rarity")));
                        continue;
                    }
                } else {
                    LOGGER.error("skipping fishing option {}, no rarity", name);
                    continue;
                }

                option.setWeight(data.has("weight") ? data.get("weight").getAsInt() : 1);

                for (JsonElement rod : data.get("rod").getAsJsonArray()){
                    if (!optionsByRod.containsKey(rl(rod))) optionsByRod.put(rl(rod), new ArrayList<>());
                    optionsByRod.get(rl(rod)).add(option);
                }
                options.put(name, option);

            } catch (IllegalArgumentException | JsonParseException error) {
                LOGGER.error("Parsing error loading fishing option {}", name, error);
            }
        }

        for (Map.Entry<ResourceLocation, List<FishingOption>> entry : optionsByRod.entrySet()){
            wightedOptionsByRod.put(entry.getKey(), WeightedRandomList.create(entry.getValue()));
        }

        LOGGER.info("Loaded {} fishing options", options.size());
    }

    private ResourceLocation rl(JsonElement obj){
        return new ResourceLocation(obj.getAsString());
    }

    // todo: consider biome, etc which will require manually looping through all options to generate the list for that circumstance
    static Random rand = new Random();
    public static FishingOption getFish(ResourceLocation rod){
        Optional<FishingOption> result = wightedOptionsByRod.get(rod).getRandom(rand);
        return result.orElse(null);
    }
}