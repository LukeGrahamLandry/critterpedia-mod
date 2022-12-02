package ca.lukegrahamlandry.critterpedia.content.init;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, ModMain.MOD_ID);

    public static final String[] microbats = new String[]{ "eastern_red_bat", "hammer_head_bat", "hoary_bat", "big_eared_bat", "chapmins_free_tailed_bat", "greater_horseshoe_bat", "spectral_bat"};
    public static final String[] megabats = new String[]{"spectacled_flying_fox", "golden_capped_flying_fox" };

    public static RegistryObject<EntityType<TriggerGuiFishingHook>> BOBBER = ENTITY.register("bobber", () -> EntityType.Builder.of((EntityType.EntityFactory<TriggerGuiFishingHook>) TriggerGuiFishingHook::new, MobCategory.MISC).sized(0.25F, 0.25F).build("bobber"));

    public static RegistryObject<EntityType<FlyEntity>> FLY = ENTITY.register("fly", () -> EntityType.Builder.of(FlyEntity::new, MobCategory.CREATURE).sized(0.9F, 1F).build("fly"));
    public static RegistryObject<EntityType<ModFishEntity>> FLORIDA_HOG_FISH = ENTITY.register("florida_hog_fish", () -> EntityType.Builder.of(ModFishEntity::new, MobCategory.CREATURE).sized(0.9F, 0.9F).build("florida_hog_fish"));
    public static RegistryObject<EntityType<ModFishEntity>> BOW_HEAD_GUITAR_FISH = ENTITY.register("bow_head_guitar_fish", () -> EntityType.Builder.of(ModFishEntity::new, MobCategory.CREATURE).sized(1.2F, 0.5F).build("bow_head_guitar_fish"));
    public static RegistryObject<EntityType<MantaRayEntity>> MANTA_RAY = ENTITY.register("manta_ray", () -> EntityType.Builder.of(MantaRayEntity::new, MobCategory.CREATURE).sized(1.2F, 0.5F).build("manta_ray"));
    public static RegistryObject<EntityType<SaltWaterCrocodileEntity>> SALT_WATER_CROCODILE = ENTITY.register("salt_water_crocodile", () -> EntityType.Builder.of(SaltWaterCrocodileEntity::new, MobCategory.CREATURE).sized(1.2F, 0.5F).build("salt_water_crocodile"));
    public static RegistryObject<EntityType<CommonSnappingTurtleEntity>> COMMON_SNAPPER = ENTITY.register("common_snapping_turtle", () -> EntityType.Builder.of(CommonSnappingTurtleEntity::new, MobCategory.CREATURE).sized(1.2F, 0.5F).build("common_snapping_turtle"));
public static RegistryObject <EntityType<AlligatorSnappingTurtleEntity>> ALLIGATOR_SNAPPER = ENTITY.register("alligator_snapping_turtle", () -> EntityType.Builder.of(AlligatorSnappingTurtleEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("alligator_snapping_turtle"));
public static RegistryObject<EntityType<BrazilianPorcupineEntity>> BRAZILIAN_PORCUPINE = ENTITY.register("brazilian_porcupine", () -> EntityType.Builder.of(BrazilianPorcupineEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("brazilian_porcupine"));
public static RegistryObject<EntityType<BlackWidowEntity>> BLACK_WIDOW = ENTITY.register("black_widow", () -> EntityType.Builder.of(BlackWidowEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("black_widow"));
public static RegistryObject<EntityType<EmperorScorpionEntity>> EMPEROR_SCORPION = ENTITY.register("emperor_scorpion", () -> EntityType.Builder.of(EmperorScorpionEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("emperor_scorpion"));
    public static RegistryObject<EntityType<ManowarEntity>> MAN_O_WAR = ENTITY.register("man_of_war", () -> EntityType.Builder.of(ManowarEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.5F).build("man_of_war"));
    public static RegistryObject<EntityType<UpsidedownjellyEntity>> UPSIDE_DOWN_JELLY= ENTITY.register("upside_down_jelly", () -> EntityType.Builder.of(UpsidedownjellyEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.5F).build("upside_down_jelly"));
    public static RegistryObject<EntityType<PsychedelicjellyEntity>> PSYCHEDELIC_JELLY = ENTITY.register("psychedelic_jelly", () -> EntityType.Builder.of(PsychedelicjellyEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.5F).build("psychedelic_jelly"));
public static RegistryObject<EntityType<VineSnakeEntity>> VINE_SNAKE = ENTITY.register("vine_snake", () -> EntityType.Builder.of(VineSnakeEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("vine_snake"));
public static RegistryObject<EntityType<LesserMoleRatEntity>> LESSER_MOLE_RAT = ENTITY.register("lesser_mole_rat", () -> EntityType.Builder.of(LesserMoleRatEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("lesser_mole_rat"));
public static RegistryObject<EntityType<RedKneeTarantulaEntity>> RED_KNEE_TARANTULA = ENTITY.register("red_knee_tarantula", () -> EntityType.Builder.of(RedKneeTarantulaEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("red_knee_tarantula"));
public static RegistryObject<EntityType<ClownTriggerfishEntity>> CLOWN_TRIGGERFISH = ENTITY.register("clown_triggerfish", () -> EntityType.Builder.of(ClownTriggerfishEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.5F).build("clown_triggerfish"));

public static RegistryObject<EntityType<BlueRingedOctopusEntity>> BLUE_RINGED_OCTOPUS = ENTITY.register("blue_ringed_octopus", () -> EntityType.Builder.of(BlueRingedOctopusEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.5F).build("blue_ringed_octopus"));
public static RegistryObject<EntityType<AmazonPufferEntity>> AMAZON_PUFFER = ENTITY.register("amazon_puffer", () -> EntityType.Builder.of(AmazonPufferEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.5F).build("amazon_puffer"));
public static RegistryObject<EntityType<AntarcticKrillEntity>> ANTARCTIC_KRILL = ENTITY.register("antarctic_krill", () -> EntityType.Builder.of(AntarcticKrillEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.5F).build("antarctic_krill"));
public static RegistryObject<EntityType<MoleCricketEntity>> MOLE_CRICKET = ENTITY.register("mole_cricket", () -> EntityType.Builder.of(MoleCricketEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("mole_cricket"));

public static RegistryObject<EntityType<NapoleonWrasseEntity>> NAPOLEON_WRASSE = ENTITY.register("napoleon_wrasse", () -> EntityType.Builder.of(NapoleonWrasseEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.5F).build("napoleon_wrasse"));

public static RegistryObject<EntityType<GreatWhiteSharkEntity>> GREAT_WHITE_SHARK = ENTITY.register("great_white_shark", () -> EntityType.Builder.of(GreatWhiteSharkEntity::new, MobCategory.WATER_CREATURE).sized(0.5F, 0.5F).build("great_white_shark"));
public static RegistryObject<EntityType<FootballFishEntity>> FOOTBALL_FISH = ENTITY.register("football_fish", () -> EntityType.Builder.of(FootballFishEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("football_fish"));

public static RegistryObject<EntityType<MegaBatEntity>> MEGA_BAT = ENTITY.register("mega_bat", () -> EntityType.Builder.of(MegaBatEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("mega_bat"));
public static RegistryObject<EntityType<MicroBatEntity>> MICRO_BAT = ENTITY.register("micro_bat", () -> EntityType.Builder.of(MicroBatEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("micro_bat"));






    public static List<ResourceLocation> smallFish = new ArrayList<>();

    static {
        List<RegistryObject<? extends EntityType<? extends LivingEntity>>> fishObj = Arrays.asList(BOW_HEAD_GUITAR_FISH, FLORIDA_HOG_FISH);
        fishObj.forEach((obj) -> smallFish.add(obj.getId()));

        for (String rl : microbats) {
            ENTITY.register(rl, () -> EntityType.Builder.of(MicroBatEntity::new, MobCategory.CREATURE).sized(EntityType.BAT.getWidth(), EntityType.BAT.getHeight()).build(rl));
        }

        for (String rl1: megabats) {
            ENTITY.register(rl1, () -> EntityType.Builder.of(MegaBatEntity::new, MobCategory.CREATURE).sized(EntityType.BAT.getWidth(), EntityType.BAT.getHeight()).build(rl1));

        }
    }
}
