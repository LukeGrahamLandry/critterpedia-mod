package ca.lukegrahamlandry.critterpedia.base.api;

import ca.lukegrahamlandry.critterpedia.ModMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class CritterCategory {
    public final ResourceLocation id;
    private Supplier<Item> icon;
    public final String pickupType;

    public CritterCategory(ResourceLocation id, String pickupType){
        this.id = id;
        this.pickupType = pickupType;
    }

    public CritterCategory(String id, String pickupType){
        this(id.contains(":") ? new ResourceLocation(id) : new ResourceLocation(ModMain.MOD_ID, id), pickupType);
    }

    public CritterCategory icon(Item icon){
        this.icon = () -> icon;
        return this;
    }

    public Item getIcon(){
        return this.icon.get();
    }

}
