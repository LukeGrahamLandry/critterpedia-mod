package ca.lukegrahamlandry.critterpedia.base.api;

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

public class FishingRarityLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public FishingRarityLoader() {
        super(GSON, "fishingrarity");
    }

    protected void apply(Map<ResourceLocation, JsonElement> p_44037_, ResourceManager p_44038_, ProfilerFiller p_44039_) {
        int count = 0;
        for (Map.Entry<ResourceLocation, JsonElement> entry : p_44037_.entrySet()) {
            ResourceLocation name = entry.getKey();
            if (name.getPath().startsWith("_")) continue; //Forge: filter anything beginning with "_" as it's used for metadata.

            try {
                JsonObject data = entry.getValue().getAsJsonObject();
                FishingManager.rarities.put(name, new FishingRarity(data));
                count++;

            } catch (IllegalArgumentException | JsonParseException error) {
                LOGGER.error("Parsing error loading fishing option {}", name, error);
            }
        }
        LOGGER.info("Loaded {} fishing rarities", count);
    }

}