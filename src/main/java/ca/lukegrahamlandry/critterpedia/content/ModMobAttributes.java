package ca.lukegrahamlandry.critterpedia.content;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.CrocodileEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.FlyEntity;
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
        for (String rl : EntityInit.bats){
            event.put((EntityType<? extends LivingEntity>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation(ModMain.MOD_ID, rl)), Bat.createAttributes().build());
        }

        event.put(EntityInit.FLY.get(), FlyEntity.createAttributes().build());
        event.put(EntityInit.FLORIDA_HOG_FISH.get(), AbstractFish.createAttributes().build());
        event.put(EntityInit.BOW_HEAD_GUITAR_FISH.get(), AbstractFish.createAttributes().build());
        event.put(EntityInit.MANTA_RAY.get(), AbstractFish.createAttributes().build());
        event.put(EntityInit.SALT_WATER_CROCODILE.get(), CrocodileEntity.createAttributes().build());
    }
}
