package ca.lukegrahamlandry.critterpedia.content.init;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, ModMain.MOD_ID);

    public static final String[] bats = new String[]{"speckled_flying_fox", "eastern_red_bat", "hammer_head_bat", "hoary_bat", "big_eared_bat"};

    public static RegistryObject<EntityType<TriggerGuiFishingHook>> BOBBER = ENTITY.register("bobber", () -> EntityType.Builder.of((EntityType.EntityFactory<TriggerGuiFishingHook>) TriggerGuiFishingHook::new, MobCategory.MISC).sized(0.25F, 0.25F).build("bobber"));

    public static RegistryObject<EntityType<FlyEntity>> FLY = ENTITY.register("fly", () -> EntityType.Builder.of(FlyEntity::new, MobCategory.CREATURE).sized(0.9F, 1F).build("fly"));
    public static RegistryObject<EntityType<ModFishEntity>> FLORIDA_HOG_FISH = ENTITY.register("florida_hog_fish", () -> EntityType.Builder.of(ModFishEntity::new, MobCategory.CREATURE).sized(0.9F, 0.9F).build("florida_hog_fish"));
    public static RegistryObject<EntityType<ModFishEntity>> BOW_HEAD_GUITAR_FISH = ENTITY.register("bow_head_guitar_fish", () -> EntityType.Builder.of(ModFishEntity::new, MobCategory.CREATURE).sized(1.2F, 0.5F).build("bow_head_guitar_fish"));
    public static RegistryObject<EntityType<MantaRayEntity>> MANTA_RAY = ENTITY.register("manta_ray", () -> EntityType.Builder.of(MantaRayEntity::new, MobCategory.CREATURE).sized(1.2F, 0.5F).build("manta_ray"));

    static {
        for (String rl : bats){
            ENTITY.register(rl, () -> EntityType.Builder.of(ModdedBatEntity::new, MobCategory.CREATURE).sized(EntityType.BAT.getWidth(), EntityType.BAT.getHeight()).build(rl));
        }
    }
}
