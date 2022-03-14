package ca.lukegrahamlandry.critterpedia.base.event;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.CaptureUtil;
import ca.lukegrahamlandry.critterpedia.base.capability.CritterCapability;
import ca.lukegrahamlandry.critterpedia.base.capability.CritterCollection;
import ca.lukegrahamlandry.critterpedia.base.client.gui.CritterpediaScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CaptureEvents {
    @SubscribeEvent
    public static void initCaps(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player){
            event.addCapability(new ResourceLocation(ModMain.MOD_ID, "collected_critters"), new CritterCapability());
        }
    }

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(CritterCollection.class);
    }

    @SubscribeEvent
    public static void onPress(PlayerInteractEvent.EntityInteract event){
        boolean holdingWaterBucket = event.getPlayer().getItemInHand(event.getHand()).is(Items.WATER_BUCKET);
        boolean isBucketable = event.getTarget() instanceof Bucketable;
        System.out.println(event.getEntity().level.isClientSide + " " + holdingWaterBucket + " " + isBucketable + " " + event.getPlayer().getItemInHand(event.getHand()).getItem().getRegistryName().toString() + " " + event.getTarget().getType().getRegistryName().toString());
        if (holdingWaterBucket && isBucketable){
            CaptureUtil.capture(event.getPlayer(), event.getTarget());
        }
    }
}