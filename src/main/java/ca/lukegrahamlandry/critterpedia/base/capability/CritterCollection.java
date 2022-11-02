package ca.lukegrahamlandry.critterpedia.base.capability;

import ca.lukegrahamlandry.critterpedia.base.api.Subtype;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.Set;

public interface CritterCollection {
    boolean hasCritter(ResourceLocation critter);
    boolean hasSubType(ResourceLocation critter, ResourceLocation subtype);
    Set<ResourceLocation> getCollectedCritterTypes();
    Set<ResourceLocation> getCollectedSubtypes(ResourceLocation critter);
    void collectCritter(ResourceLocation critter);
    void collectSubType(ResourceLocation critter, ResourceLocation subtype);
}
