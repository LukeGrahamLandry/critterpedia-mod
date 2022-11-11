package ca.lukegrahamlandry.critterpedia.content.init;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.item.LiveFishItem;
import ca.lukegrahamlandry.critterpedia.content.item.ModFishingRod;
import ca.lukegrahamlandry.critterpedia.content.item.ModMobBucketItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
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

    public static RegistryObject<Item> SMALL_ROD = ITEM.register("small_fishing_rod", () -> new ModFishingRod(new Item.Properties().durability(500).tab(CreativeModeTab.TAB_TOOLS), new ResourceLocation("critterpedia:fishing_pole")));

    public static RegistryObject<Item> LARGE_ROD = ITEM.register("large_fishing_rod", () -> new ModFishingRod(new Item.Properties().durability(500).tab(CreativeModeTab.TAB_TOOLS), new ResourceLocation("critterpedia:large_fishing_pole")));

    public static Map<ResourceLocation, Supplier<Item>> smallFishBuckets = new HashMap<>();

    public static Map<ResourceLocation, Supplier<Item>> wideBucketItem = new HashMap<>();
    public static Map<ResourceLocation, Supplier<Item>> rawFish = new HashMap<>();
    public static Map<ResourceLocation, Supplier<Item>> cookedFish = new HashMap<>();
    public static Map<ResourceLocation, Supplier<Item>> liveFish = new HashMap<>();



    static {
        Supplier<Fluid> WATER = () -> Fluids.WATER;
        Supplier<? extends SoundEvent> bucketSound = () -> SoundEvents.BUCKET_FILL_FISH;
        for (ResourceLocation fish : EntityInit.smallFish){
            Supplier<? extends EntityType<?>> entitySupplier = () -> ForgeRegistries.ENTITIES.getValue(fish);
            smallFishBuckets.put(fish, ITEM.register(fish.getPath() + "_bucket", () -> new ModMobBucketItem(entitySupplier, WATER, bucketSound, new Item.Properties().stacksTo(1).tab(ModCreativeTab.fish))));



            makeFish(fish);
        }


    }

    private static void makeFish(ResourceLocation fish){
        rawFish.put(fish, ITEM.register("raw_" + fish.getPath(), () -> new Item(new Item.Properties().tab(ModCreativeTab.fish))));
        cookedFish.put(fish, ITEM.register("cooked_" + fish.getPath(), () -> new Item(new Item.Properties().tab(ModCreativeTab.fish))));
        liveFish.put(fish, ITEM.register("live_" + fish.getPath(), () -> new LiveFishItem(fish, new Item.Properties().durability(20*20))));
    }
}
