package ca.lukegrahamlandry.critterpedia.base.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class Subtype extends IDHasIcon {
    public Subtype(ResourceLocation id) {
        super(id);
    }

    @Override
    public Subtype icon(Item icon) {
        return (Subtype) super.icon(icon);
    }

    // END BUILDER

    public interface HasCustomSubtype {
        ResourceLocation getSubtype();
    }
}
