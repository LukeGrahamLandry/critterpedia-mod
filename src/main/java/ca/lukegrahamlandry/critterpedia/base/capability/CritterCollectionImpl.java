package ca.lukegrahamlandry.critterpedia.base.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CritterCollectionImpl implements CritterCollection, INBTSerializable<CompoundTag> {
    HashMap<ResourceLocation, Set<ResourceLocation>> collected = new HashMap<>();

    @Override
    public boolean hasCritter(ResourceLocation critter) {
        return collected.containsKey(critter);
    }

    @Override
    public boolean hasSubType(ResourceLocation critter, ResourceLocation subtype) {
        return collected.containsKey(critter) && collected.get(critter).contains(subtype);
    }

    @Override
    public Set<ResourceLocation> getCollectedCritterTypes() {
        return collected.keySet();
    }

    @Override
    public Set<ResourceLocation> getCollectedSubtypes(ResourceLocation critter) {
        return collected.containsKey(critter) ? collected.get(critter) : new HashSet<>();
    }

    @Override
    public void collectCritter(ResourceLocation critter) {
        if (!this.collected.containsKey(critter)){
            this.collected.put(critter, new HashSet<>());
        }
    }

    @Override
    public void collectSubType(ResourceLocation critter, ResourceLocation subtype) {
        collectCritter(critter);
        this.collected.get(critter).add(subtype);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();


        int i = 0;
        for (ResourceLocation critter : this.getCollectedCritterTypes()){
            CompoundTag critterData = new CompoundTag();
            critterData.putString("name", critter.toString());

            int j = 0;
            for (ResourceLocation subtype : this.getCollectedSubtypes(critter)){
                critterData.putString(String.valueOf(j), subtype.toString());
                j++;
            }

            tag.put(String.valueOf(i), critterData);
            i++;
        }

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        int i = 0;
        while (nbt.contains(String.valueOf(i))){
            CompoundTag critterData = nbt.getCompound(String.valueOf(i));
            ResourceLocation critter = new ResourceLocation(critterData.getString("name"));
            this.collectCritter(critter);

            int j = 0;
            while (critterData.contains(String.valueOf(j))){
                ResourceLocation subtype = new ResourceLocation(critterData.getString(String.valueOf(j)));
                this.collectSubType(critter, subtype);
                j++;
            }

            i++;
        }
    }
}
