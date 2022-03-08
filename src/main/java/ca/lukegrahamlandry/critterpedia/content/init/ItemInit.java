package ca.lukegrahamlandry.critterpedia.content.init;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.FlyEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.MantaRayEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.ModFishEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.ModdedBatEntity;
import ca.lukegrahamlandry.critterpedia.content.item.LiveFishItem;
import ca.lukegrahamlandry.critterpedia.content.item.ModFishingRod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ItemInit {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);

    public static RegistryObject<Item> SMALL_ROD = ITEM.register("small_fishing_rod", () -> new ModFishingRod(new Item.Properties().durability(500), new ResourceLocation("critterpedia:small_rod")));

    public static RegistryObject<Item> LARGE_ROD = ITEM.register("large_fishing_rod", () -> new ModFishingRod(new Item.Properties().durability(500), new ResourceLocation("critterpedia:large_rod")));


    public static Map<ResourceLocation, Supplier<Item>> smallFishBuckets = new HashMap<>();
    public static Map<ResourceLocation, Supplier<Item>> rawFish = new HashMap<>();
    public static Map<ResourceLocation, Supplier<Item>> cookedFish = new HashMap<>();
    public static Map<ResourceLocation, Supplier<Item>> liveFish = new HashMap<>();

    static {
        Supplier<Fluid> WATER = () -> Fluids.WATER;
        for (ResourceLocation fish : EntityInit.smallFish){
            Supplier<? extends EntityType<?>> entitySupplier = () -> ForgeRegistries.ENTITIES.getValue(fish);
            Supplier<? extends SoundEvent> sound = () -> SoundEvents.BUCKET_FILL_FISH;
            smallFishBuckets.put(fish, ITEM.register(fish.getPath() + "_bucket", () -> new MobBucketItem(entitySupplier, WATER, sound, new Item.Properties().stacksTo(0))));

            makeFish(fish);
        }
    }

    private static void makeFish(ResourceLocation fish){
        rawFish.put(fish, ITEM.register("raw_" + fish.getPath(), () -> new Item(new Item.Properties())));
        cookedFish.put(fish, ITEM.register("cooked_" + fish.getPath(), () -> new Item(new Item.Properties())));
        liveFish.put(fish, ITEM.register("live_" + fish.getPath(), () -> new LiveFishItem(fish, new Item.Properties().durability(20*20))));
    }
}
