package ca.lukegrahamlandry.critterpedia.base.api;

import net.minecraft.resources.ResourceLocation;

public class FishingRarity {
    public final int color;
    public float barSize;
    public final int weight;
    public final int timeRequired;
    public float fishSpeed;
    public final float fishMoveChance;
    public final int decay;

    public FishingRarity(int color, float barSize, int weight, int timeRequired, float fishSpeed, float fishMoveChance, int decay){
        this.color = color;
        this.barSize = barSize; // as percentage of the whole thing
        this.weight = weight;
        this.timeRequired = timeRequired;
        this.fishSpeed = fishSpeed;
        this.fishMoveChance = fishMoveChance;
        this.decay = decay;
    }

    public static void createDefaults(){
        FishingRarity common = new FishingRarity(0x55FF55, 0.25F, 3, 50, 0.01F, 0.02F, 2);
        FishingManager.rarities.put(new ResourceLocation("minecraft:common"), common);
        FishingRarity uncommon = new FishingRarity(0xFFFF55, 0.15F, 2, 100, 0.03F, 0.03F, 5);
        FishingManager.rarities.put(new ResourceLocation("minecraft:uncommon"), uncommon);
        FishingRarity rare = new FishingRarity(0xAA0000, 0.5F, 1, 200, 0.05F, 0.04F, 10);
        FishingManager.rarities.put(new ResourceLocation("minecraft:rare"), rare);
    }
}
