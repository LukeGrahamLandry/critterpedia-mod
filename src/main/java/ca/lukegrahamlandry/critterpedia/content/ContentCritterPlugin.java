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

    @Override
    public List<CritterType> getTypes() {
        critters.clear();

        ResourceLocation FISH = new ResourceLocation("minecraft:fish");
        ResourceLocation BIRD = new ResourceLocation("minecraft:bird");
        ResourceLocation BAT = new ResourceLocation("minecraft:bat");
        ResourceLocation MAMMAL = new ResourceLocation("minecraft:mammal");
        ResourceLocation INSECT = new ResourceLocation("minecraft:insect");

        return critters;
    }

    @Override
    public List<CritterCategory> getCategories() {
        List<CritterCategory> categories = new ArrayList<>();

        return categories;
    }

    @Override
    public String getId() {
        return ModMain.MOD_ID;
    }
}
