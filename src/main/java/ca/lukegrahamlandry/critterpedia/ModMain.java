package ca.lukegrahamlandry.critterpedia;

import ca.lukegrahamlandry.critterpedia.base.api.Critters;
import ca.lukegrahamlandry.critterpedia.base.capability.CritterCapability;
import ca.lukegrahamlandry.critterpedia.base.capability.CritterCollection;
import ca.lukegrahamlandry.critterpedia.content.VanillaCritterPlugin;
import ca.lukegrahamlandry.critterpedia.content.entity.FlyEntity;
import ca.lukegrahamlandry.critterpedia.content.init.EntityInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.GeckoLib;

@Mod(ModMain.MOD_ID)
public class ModMain {
    public static final String MOD_ID = "critterpedia";

    public ModMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        GeckoLib.initialize();
        EntityInit.ENTITY.register(modEventBus);
        modEventBus.addListener(ModMain::mobAttributes);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Critters.register(new VanillaCritterPlugin());
    }

    public static void mobAttributes(EntityAttributeCreationEvent event){
        for (String rl : EntityInit.bats){
            event.put((EntityType<? extends LivingEntity>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation(ModMain.MOD_ID, rl)), Bat.createAttributes().build());
        }

        event.put(EntityInit.FLY.get(), FlyEntity.createAttributes().build());
    }


}
