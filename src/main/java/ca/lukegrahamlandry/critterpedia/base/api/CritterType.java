package ca.lukegrahamlandry.critterpedia.base.api;

import ca.lukegrahamlandry.critterpedia.ModMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class CritterType extends IDHasIcon {
    public final ResourceLocation category;
    private Set<Supplier<EntityType>> entities;
    private Map<ResourceLocation, Subtype> subtypes = new HashMap<>();
    private boolean hasCustomSubtypes = false;

    public CritterType(String id, ResourceLocation category){
        this(id.contains(":") ? new ResourceLocation(id) : new ResourceLocation(ModMain.MOD_ID, id), category);
    }

    public CritterType(ResourceLocation id, ResourceLocation category){
        super(id);
        this.category = category;
    }

    // either use this OR subtypes
    public CritterType entity(EntityType entity){
        this.entities.add(() -> entity);
        return this;
    }

    public CritterType forceSubtypes(){
        this.hasCustomSubtypes = true;
        return this;
    }

    public CritterType subtype(Subtype subtype){
        this.subtypes.put(subtype.id, subtype);
        return this;
    }

    @Override
    public CritterType icon(Item icon) {
        return (CritterType) super.icon(icon);
    }

    // END BUILDER

    public CritterCategory getCategory(){
        return Critters.getCategory(this.category);
    }

    public boolean contains(Entity creature){
        for (Supplier<EntityType> check : this.entities){
            if (check.get().equals(creature.getType())) return true;
        }

        return false;
    }

    public boolean hasSubtypes(){
        return this.entities.size() > 1 || this.hasCustomSubtypes;
    }

    // can override this and #contains when registered if the other mod wants to do subtypes in a custom way
    public Subtype getSubtype(Entity creature){
        if (!this.contains(creature)) return null;

        ResourceLocation typeRL = creature.getType().getRegistryName();
        if (creature instanceof Subtype.HasCustomSubtype){
            typeRL = ((Subtype.HasCustomSubtype) creature).getSubtype();
        }

        return this.subtypes.get(typeRL);
    }

    /* HOW TO USE: 2021/12/28
     * If hasSubtypes() returns false
     * - there is only one entity type that fits in this category
     * - do not display a menu for extra subtypes
     * - use contains(Entity) to check if a certain entity fits this type
     * - getSubtype(Entity) will always return null
     * If hasSubtypes() returns true
     * - there may be multiple entity types that fit in this category
     * - an extra menu in the gui will show each subtype collected
     * - contains(Entity) will return true if it is in any subtype
     * - use getSubtype(Entity) to get the id to add when a certain entity is captured
     * - if you only have one entity type but your entity type impliments Subtype.HasCustomSubtype, call forceSubtypes()
     */
}
