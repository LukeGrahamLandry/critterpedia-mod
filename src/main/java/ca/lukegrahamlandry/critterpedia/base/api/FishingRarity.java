package ca.lukegrahamlandry.critterpedia.base.api;

import net.minecraft.resources.ResourceLocation;

public class FishingRarity {
    public final int color;
    public float barSize;
    public final int weight;
    public final int timeRequired;
    public final int speed;
    public final int activity;
    public final int decay;

    public FishingRarity(int color, float barSize, int weight, int timeRequired, int speed, int activity, int decay){
        this.color = color;
        this.barSize = barSize; // as percentage of the whole thing
        this.weight = weight;
        this.timeRequired = timeRequired;
        this.speed = speed;
        this.activity = activity;
        this.decay = decay;
    }

    public static void createDefaults(){
        FishingRarity common = new FishingRarity(0x55FF55, 0.25F, 3, 50, 50, 20, 2);
        FishingManager.rarities.put(new ResourceLocation("minecraft:common"), common);
        FishingRarity uncommon = new FishingRarity(0xFFFF55, 0.15F, 2, 100, 75, 15, 5);
        FishingManager.rarities.put(new ResourceLocation("minecraft:uncommon"), uncommon);
        FishingRarity rare = new FishingRarity(0xAA0000, 0.5F, 1, 200, 100, 10, 10);
        FishingManager.rarities.put(new ResourceLocation("minecraft:rare"), rare);
    }
}
