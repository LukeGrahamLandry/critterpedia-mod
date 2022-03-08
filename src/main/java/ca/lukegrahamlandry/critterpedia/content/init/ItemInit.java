package ca.lukegrahamlandry.critterpedia.content.init;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.FlyEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.MantaRayEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.ModFishEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.ModdedBatEntity;
import ca.lukegrahamlandry.critterpedia.content.item.ModFishingRod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, ModMain.MOD_ID);

    public static RegistryObject<Item> SMALL_ROD = ITEM.register("small_fishing_rod", () -> new ModFishingRod(new Item.Properties().durability(500), new ResourceLocation("critterpedia:small_rod")));

    public static RegistryObject<Item> LARGE_ROD = ITEM.register("large_fishing_rod", () -> new ModFishingRod(new Item.Properties().durability(500), new ResourceLocation("critterpedia:large_rod")));

}
