package ca.lukegrahamlandry.critterpedia.base.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Critters {
    private static final HashMap<ResourceLocation, CritterCategory> categories = new HashMap<>();
    private static final HashMap<ResourceLocation, CritterType> critters = new HashMap<>();

    public static void register(CritterPlugin plugin){
        // TODO: check config to allow disabling plugins by id

        for (CritterCategory category : plugin.getCategories()){
            if (!categories.containsKey(category.id)){
                categories.put(category.id, category);
            }
        }

        for (CritterType critter : plugin.getTypes()){
            if (!critters.containsKey(critter.id)){
                critters.put(critter.id, critter);
            }
        }
    }

    // START GETTERS

    public static CritterType getCritter(ResourceLocation id){
        return critters.get(id);
    }

    public static CritterType getCritter(Entity creature){
        for (CritterType critter : critters.values()){
            if (critter.contains(creature)) return critter;
        }

        return null;
    }

    public static CritterCategory getCategory(ResourceLocation id){
        return categories.get(id);
    }

    public static CritterCategory getCategory(String id){
        return categories.get(new ResourceLocation(id));
    }


    public static Collection<CritterCategory> getCategories(){
        return categories.values();
    }

    public static Collection<CritterType> getCritters(){
        return critters.values();
    }
}
