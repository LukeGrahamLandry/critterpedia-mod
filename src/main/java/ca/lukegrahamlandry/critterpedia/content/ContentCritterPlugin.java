package ca.lukegrahamlandry.critterpedia.content;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.api.CritterCategory;
import ca.lukegrahamlandry.critterpedia.base.api.CritterPlugin;
import ca.lukegrahamlandry.critterpedia.base.api.CritterType;
import ca.lukegrahamlandry.critterpedia.content.data.RegisterDataGen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;


public class ContentCritterPlugin implements CritterPlugin {
    private static final List<CritterType> critters = new ArrayList<>();

    private CritterType make(String id, ResourceLocation category, Item icon){
        CritterType t = new CritterType(new ResourceLocation(ModMain.MOD_ID, id), category).icon(icon);
        critters.add(t);
        return t;
    }

    private CritterType make(String id, ResourceLocation category, EntityType icon){
        CritterType t = new CritterType(new ResourceLocation(ModMain.MOD_ID, id), category).icon(() -> icon);
        critters.add(t);
        return t;
    }

    ResourceLocation MAMMAL = new ResourceLocation("critter:mammal");
    ResourceLocation SEA_CRITTER = new ResourceLocation("critter:sea_critter");
    ResourceLocation FISH = new ResourceLocation("critter:fish");

    ResourceLocation AMPHIBIAN = new ResourceLocation("critter:amphibian");
    ResourceLocation REPTILE = new ResourceLocation("critter:reptile");
    @Override
    public List<CritterType> getTypes() {
        critters.clear();


        return critters;
    }

    @Override
    public List<CritterCategory> getCategories() {
        List<CritterCategory> categories = new ArrayList<>();


        categories.add(new CritterCategory(AMPHIBIAN, "bucket").icon(Items.AXOLOTL_BUCKET));
        categories.add(new CritterCategory(REPTILE, "net").icon(Items.TURTLE_EGG));
        categories.add(new CritterCategory(MAMMAL, "net").icon(Items.MILK_BUCKET));

        categories.add(new CritterCategory(SEA_CRITTER, "bucket").icon(Items.SEA_PICKLE));
        return categories;
    }

    @Override
    public String getId() {
        return ModMain.MOD_ID;
    }
}

