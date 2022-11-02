package ca.lukegrahamlandry.critterpedia.content;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.api.CritterCategory;
import ca.lukegrahamlandry.critterpedia.base.api.CritterPlugin;
import ca.lukegrahamlandry.critterpedia.base.api.CritterType;
import ca.lukegrahamlandry.critterpedia.base.api.Critters;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;


public class VanillaCritterPlugin implements CritterPlugin {
    private static final List<CritterType> critters = new ArrayList<>();

    private CritterType make(String id, ResourceLocation category, Item icon){
        CritterType t = new CritterType(new ResourceLocation("minecraft", id), category).icon(icon);
        critters.add(t);
        return t;
    }

    private CritterType make(String id, ResourceLocation category, EntityType icon){
        CritterType t = new CritterType(new ResourceLocation("minecraft", id), category).icon(() -> icon);
        critters.add(t);
        return t;
    }

    @Override
    public List<CritterType> getTypes() {
        critters.clear();

        ResourceLocation FISH = new ResourceLocation("minecraft:fish");
        ResourceLocation BIRD = new ResourceLocation("minecraft:bird");

        make("cod", FISH, Items.COD).entity(EntityType.COD);
        make("salmon", FISH, Items.SALMON).entity(EntityType.SALMON);
        make("pufferfish", FISH, Items.PUFFERFISH).entity(EntityType.PUFFERFISH);
        make("tropical_fish.json", FISH, Items.TROPICAL_FISH).entity(EntityType.TROPICAL_FISH);

        make("parrot", BIRD, Items.RED_DYE).entity(EntityType.PARROT);
        make("chicken", BIRD, Items.FEATHER).entity(EntityType.CHICKEN);

        return critters;
    }

    @Override
    public List<CritterCategory> getCategories() {
        List<CritterCategory> categories = new ArrayList<>();

        categories.add(new CritterCategory("minecraft:fish", "bucket").icon(Items.SALMON));
        categories.add(new CritterCategory("minecraft:bird", "net").icon(Items.FEATHER));

        return categories;
    }

    @Override
    public String getId() {
        return ModMain.MOD_ID;
    }
}
