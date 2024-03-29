package ca.lukegrahamlandry.critterpedia.base.api;

import ca.lukegrahamlandry.critterpedia.ModMain;
import com.google.gson.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class FishingOptionLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public Map<ResourceLocation, FishingOption> options = new HashMap<>();
    public Map<ResourceLocation, WeightedRandomList<FishingOption>>  wightedOptionsByRod = new HashMap<>();

    public FishingOptionLoader() {
        super(GSON, "fishing");
    }

    protected void apply(Map<ResourceLocation, JsonElement> p_44037_, ResourceManager p_44038_, ProfilerFiller p_44039_) {
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

                ResourceLocation item;
                if (data.has("item")){
                    item = rl(data.get("item"));
                } else {
                    item = new ResourceLocation(name.getNamespace(), "live_" + name.getPath());
                }

                if (ForgeRegistries.ITEMS.containsKey(item)){
                    option.fishItem = item;
                } else {
                    LOGGER.error("skipping fishing option {}, has an unregistered result item {}", name, item);
                    continue;
                }

                if (data.has("rarity")){
                    option.rarity = c_rl(data.get("rarity"));
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

    private ResourceLocation c_rl(JsonElement obj){
        if (obj.getAsString().contains(":")){
            return new ResourceLocation(obj.getAsString());
        } else {
            return new ResourceLocation(ModMain.MOD_ID, obj.getAsString());
        }
    }
}