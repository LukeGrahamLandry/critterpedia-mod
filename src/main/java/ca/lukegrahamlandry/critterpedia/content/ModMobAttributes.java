package ca.lukegrahamlandry.critterpedia.content;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.*;
import ca.lukegrahamlandry.critterpedia.content.init.EntityInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
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
        for (String rl : EntityInit.microbats){
            event.put((EntityType<? extends LivingEntity>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation(ModMain.MOD_ID, rl)), Bat.createAttributes().build());
        }
        for (String rl1 : EntityInit.megabats){
            event.put((EntityType<? extends LivingEntity>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation(ModMain.MOD_ID, rl1)), Bat.createAttributes().build());
        }

        event.put(EntityInit.FLY.get(), FlyEntity.createAttributes().build());
event.put(EntityInit.MAN_O_WAR.get(), UpsidedownjellyEntity.createAttributes().build());
        event.put(EntityInit.FLORIDA_HOG_FISH.get(), AbstractFish.createAttributes().build());
        event.put(EntityInit.BOW_HEAD_GUITAR_FISH.get(), AbstractFish.createAttributes().build());
        event.put(EntityInit.MANTA_RAY.get(), AbstractFish.createAttributes().build());
        event.put(EntityInit.SALT_WATER_CROCODILE.get(), CrocodileEntity.createAttributes().build());
        event.put(EntityInit.PSYCHEDELIC_JELLY.get(), PsychedelicjellyEntity.createAttributes().build());
        event.put(EntityInit.UPSIDE_DOWN_JELLY.get(), UpsidedownjellyEntity.createAttributes().build());
        event.put(EntityInit.COMMON_SNAPPER.get(), CommonSnappingTurtleEntity.createMobAttributes().build());

        event.put(EntityInit.ALLIGATOR_SNAPPER.get(), AlligatorSnappingTurtleEntity.createMobAttributes().build());
   event.put(EntityInit.BLACK_WIDOW.get(), BlackWidowEntity.createMobAttributes().build());
   event.put(EntityInit.BRAZILIAN_PORCUPINE.get(),BrazilianPorcupineEntity.createMobAttributes().build());
   event.put(EntityInit.EMPEROR_SCORPION.get(), EmperorScorpionEntity.createMobAttributes().build());
event.put(EntityInit.VINE_SNAKE.get(), VineSnakeEntity.createMobAttributes().build());




    }


}
