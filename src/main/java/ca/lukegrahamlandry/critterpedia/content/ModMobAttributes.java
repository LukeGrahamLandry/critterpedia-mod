package ca.lukegrahamlandry.critterpedia.content;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.*;
import ca.lukegrahamlandry.critterpedia.content.init.EntityInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModMobAttributes {
    @SubscribeEvent
    public static void register(EntityAttributeCreationEvent event){



        event.put(EntityInit.FLY.get(), FlyEntity.createAttributes().build());
event.put(EntityInit.MAN_O_WAR.get(), UpsidedownjellyEntity.createAttributes().build());
        event.put(EntityInit.FLORIDA_HOG_FISH.get(), AbstractFish.createAttributes().build());
        event.put(EntityInit.BOW_HEAD_GUITAR_FISH.get(), AbstractFish.createAttributes().build());
        event.put(EntityInit.MANTA_RAY.get(), AbstractFish.createAttributes().build());
        event.put(EntityInit.SALT_WATER_CROCODILE.get(), SaltWaterCrocodileEntity.createAttributes().build());
        event.put(EntityInit.PSYCHEDELIC_JELLY.get(), PsychedelicjellyEntity.createAttributes().build());
        event.put(EntityInit.UPSIDE_DOWN_JELLY.get(), UpsidedownjellyEntity.createAttributes().build());
        event.put(EntityInit.COMMON_SNAPPER.get(), CommonSnappingTurtleEntity.createMobAttributes().build());

        event.put(EntityInit.ALLIGATOR_SNAPPER.get(), AlligatorSnappingTurtleEntity.createMobAttributes().build());
           event.put(EntityInit.BLACK_WIDOW.get(), BlackWidowEntity.createMobAttributes().build());
           event.put(EntityInit.BRAZILIAN_PORCUPINE.get(),BrazilianPorcupineEntity.createMobAttributes().build());
           event.put(EntityInit.EMPEROR_SCORPION.get(), EmperorScorpionEntity.createMobAttributes().build());
        event.put(EntityInit.VINE_SNAKE.get(), VineSnakeEntity.createMobAttributes().build());
        event.put(EntityInit.AMAZON_PUFFER.get(), AmazonPufferEntity.createMobAttributes().build());
        event.put(EntityInit.ANTARCTIC_KRILL.get(), AntarcticKrillEntity.createMobAttributes().build());
        event.put(EntityInit.BLUE_RINGED_OCTOPUS.get(), BlueRingedOctopusEntity.createMobAttributes().build());
        event.put(EntityInit.CLOWN_TRIGGERFISH.get(), ClownTriggerfishEntity.createMobAttributes().build());
        event.put(EntityInit.LESSER_MOLE_RAT.get(), LesserMoleRatEntity.createMobAttributes().build() );
        event.put(EntityInit.RED_KNEE_TARANTULA.get(), RedKneeTarantulaEntity.createMobAttributes().build());
        event.put(EntityInit.NAPOLEON_WRASSE.get(), NapoleonWrasseEntity.createMobAttributes().build());
        event.put(EntityInit.MOLE_CRICKET.get(), MoleCricketEntity.createMobAttributes().build());
        event.put(EntityInit.GREAT_WHITE_SHARK.get(), GreatWhiteSharkEntity.createMobAttributes().build());
        event.put(EntityInit.FOOTBALL_FISH.get(), FootballFishEntity.createAttributes().build());
event.put(EntityInit.BIG_EARED_BAT.get(), BigEaredBatEntity.createAttributes().build());
event.put(EntityInit.SPECTACLED_FLYING_FOX.get(), SpectacledFlyingFoxEntity.createAttributes().build());
event.put(EntityInit.HOARY_BAT.get(), HoaryBatEntity.createAttributes().build());
event.put(EntityInit.GOLDEN_CAPPED_FLYING_FOX.get(), GoldenCappedFlyingFoxEntity.createAttributes().build());
event.put(EntityInit.EASTERN_RED_BAT.get(), EasternRedBatEntity.createAttributes().build());
event.put(EntityInit.SPECTRAL_BAT.get(), SpectralBatEntity.createAttributes().build());
event.put(EntityInit.GREATER_HORSESHOE_BAT.get(), GreaterHorseshoeBatEntity.createAttributes().build());
event.put(EntityInit.HAMMERHEAD_BAT.get(), HammerheadBatEntity.createAttributes().build());
event.put(EntityInit.CHAPIN_FREE_TAILED_BAT.get(), ChapinFreeTailedBatEntity.createAttributes().build());









    }




}
