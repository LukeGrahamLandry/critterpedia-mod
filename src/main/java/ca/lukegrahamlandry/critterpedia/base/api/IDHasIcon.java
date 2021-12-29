package ca.lukegrahamlandry.critterpedia.base.api;

import ca.lukegrahamlandry.critterpedia.ModMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class IDHasIcon {
    public final ResourceLocation id;
    private Supplier<Item> icon;

    public IDHasIcon(ResourceLocation id){
        this.id = id;
    }

    public IDHasIcon(String id){
        this(id.contains(":") ? new ResourceLocation(id) : new ResourceLocation(ModMain.MOD_ID, id));
    }

    public IDHasIcon icon(Item icon){
        this.icon = () -> icon;
        return this;
    }

    public Item getIcon(){
        return this.icon.get();
    }
}
