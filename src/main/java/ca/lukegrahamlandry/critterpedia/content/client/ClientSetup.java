package ca.lukegrahamlandry.critterpedia.content.client;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.client.models.BowheadGuitarFishModel;
import ca.lukegrahamlandry.critterpedia.content.client.models.FloridaHogFishModel;
import ca.lukegrahamlandry.critterpedia.content.client.models.ModdedBatModel;
import ca.lukegrahamlandry.critterpedia.content.client.render.*;
import ca.lukegrahamlandry.critterpedia.content.init.EntityInit;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        for (String rl : EntityInit.microbats){
            byRL(rl, (ctx) -> new ModdedBatRender(ctx, (ctxx) -> new ModdedBatModel(ctxx, rl)));
            for (String rl1 :EntityInit.megabats){
                byRL(rl1, (ctx) -> new ModdedBatRender(ctx,(ctxx) -> new ModdedBatModel(ctxx, rl1)));
            }
        }


        EntityRenderers.register(EntityInit.FLY.get(), FlyGeoRender::new);
        EntityRenderers.register(EntityInit.MANTA_RAY.get(), (ctx) -> new GenericGeoRender<>(ctx, "manta_ray"));
        EntityRenderers.register(EntityInit.SALT_WATER_CROCODILE.get(), (ctx) -> new GenericGeoRender<>(ctx, "salt_water_crocodile"));
        EntityRenderers.register(EntityInit.BOW_HEAD_GUITAR_FISH.get(), BowheadGuitarFishRender::new);
        EntityRenderers.register(EntityInit.MAN_O_WAR.get(),(ctx -> new GenericGeoRender<>(ctx,"man_o_war")));
        EntityRenderers.register(EntityInit.FLORIDA_HOG_FISH.get(), FloridaHogFishRender::new);
EntityRenderers.register(EntityInit.PSYCHEDELIC_JELLY.get(), (ctx) -> new GenericGeoRender<> (ctx, "psychedelic_jelly"));
        EntityRenderers.register(EntityInit.UPSIDE_DOWN_JELLY.get(), (ctx) -> new GenericGeoRender<> (ctx, "upsidedown_jelly"));
        EntityRenderers.register(EntityInit.COMMON_SNAPPER.get(), (ctx) -> new GenericGeoRender<>(ctx, "common_snapping_turtle"));


        EntityRenderers.register(EntityInit.EMPEROR_SCORPION.get(), (ctx) -> new GenericGeoRender<> (ctx, "emperor_scorpion"));
        EntityRenderers.register(EntityInit.ALLIGATOR_SNAPPER.get(), (ctx) -> new GenericGeoRender<>(ctx, "alligator_snapping_turtle"));
        EntityRenderers.register(EntityInit.BLACK_WIDOW.get(), (ctx) -> new GenericGeoRender<>(ctx,"black_widow"));
      EntityRenderers.register(EntityInit.BRAZILIAN_PORCUPINE.get(), (ctx) -> new GenericGeoRender<>(ctx,"brazilian_porcupine"));
      EntityRenderers.register(EntityInit.VINE_SNAKE.get(), (ctx) -> new GenericGeoRender<>(ctx, "vine_snake"));

      EntityRenderers.register(EntityInit.AMAZON_PUFFER.get(), (ctx) -> new GenericGeoRender<>(ctx, "amazon_puffer"));
EntityRenderers.register(EntityInit.ANTARCTIC_KRILL.get(), (ctx) -> new GenericGeoRender<>(ctx, "antarctic_krill"));
EntityRenderers.register(EntityInit.BLUE_RINGED_OCTOPUS.get(), (ctx) -> new GenericGeoRender<>(ctx, "blue_ringed_octopus"));
EntityRenderers.register(EntityInit.LESSER_MOLE_RAT.get(), (ctx) -> new GenericGeoRender<>(ctx, "lesser_mole_rat"));
EntityRenderers.register(EntityInit.RED_KNEE_TARANTULA.get(), (ctx) -> new GenericGeoRender<>(ctx, "red_knee_tarantula"));
EntityRenderers.register(EntityInit.CLOWN_TRIGGERFISH.get(), (ctx) -> new GenericGeoRender<>(ctx, "clown_triggerfish"));
EntityRenderers.register(EntityInit.MOLE_CRICKET.get(), (ctx) -> new GenericGeoRender<>(ctx, "mole_cricket"));
EntityRenderers.register(EntityInit.NAPOLEON_WRASSE.get(), (ctx) -> new GenericGeoRender<>(ctx, "football_fish"));
EntityRenderers.register(EntityInit.GREAT_WHITE_SHARK.get(), (ctx) -> new GenericGeoRender<>(ctx, "great_white_shark"));
EntityRenderers.register(EntityInit.FOOTBALL_FISH.get(), (ctx) -> new GenericGeoRender<>(ctx, "football_fish"));




        // the string renders wrong
        EntityRenderers.register(EntityInit.BOBBER.get(), FishingHookRenderer::new);
    }

    public static void byRL(String registryName, EntityRendererProvider renderer){
        EntityRenderers.register(Objects.requireNonNull(ForgeRegistries.ENTITIES.getValue(new ResourceLocation(ModMain.MOD_ID, registryName))), renderer);
    }

    @SubscribeEvent
    public static void layers(EntityRenderersEvent.RegisterLayerDefinitions event){
        for (String rl : EntityInit.microbats){
            makeLayer(event, rl, ModdedBatModel.getModelFor(rl));
        }
        for (String rl1 : EntityInit.megabats){
            makeLayer(event, rl1, ModdedBatModel.getModelFor(rl1));
        }
        event.registerLayerDefinition(FloridaHogFishModel.LAYER_LOCATION, FloridaHogFishModel::createBodyLayer);
        event.registerLayerDefinition(BowheadGuitarFishModel.LAYER_LOCATION, BowheadGuitarFishModel::createBodyLayer);
    }

    public static void makeLayer(EntityRenderersEvent.RegisterLayerDefinitions event, String registryName, Supplier<LayerDefinition> supplier){
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(ModMain.MOD_ID, registryName), "main"), supplier);
    }
}