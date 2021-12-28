package ca.lukegrahamlandry.critterpedia.base.api;

import ca.lukegrahamlandry.critterpedia.ModMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class CritterType {
    public final ResourceLocation id;
    public final CritterCategory category;
    private Supplier<Item> icon;
    private Supplier<EntityType> entity;

    public CritterType(String id, CritterCategory category){
        this(id.contains(":") ? new ResourceLocation(id) : new ResourceLocation(ModMain.MOD_ID, id), category);
    }

    public CritterType(ResourceLocation id, CritterCategory category){
        this.id = id;
        this.category = category;
    }

    public CritterType icon(Item icon){
        this.icon = () -> icon;
        return this;
    }

    // either use this OR subtypes
    public CritterType entity(EntityType entity){
        this.entity = () -> entity;
        return this;
    }

    public Item getIcon(){
        return this.icon.get();
    }

    public boolean contains(Entity creature){
        return this.entity.get().equals(creature.getType());
    }
}
