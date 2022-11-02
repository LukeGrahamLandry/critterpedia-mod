package ca.lukegrahamlandry.critterpedia;

import ca.lukegrahamlandry.critterpedia.base.api.Critters;
import ca.lukegrahamlandry.critterpedia.base.network.NetworkInit;
import ca.lukegrahamlandry.critterpedia.content.VanillaCritterPlugin;
import ca.lukegrahamlandry.critterpedia.content.init.EntityInit;
import ca.lukegrahamlandry.critterpedia.content.init.ItemInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(ModMain.MOD_ID)
public class ModMain {
    public static final String MOD_ID = "critterpedia";

    public ModMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        GeckoLib.initialize();
        NetworkInit.registerPackets();
        modEventBus.addListener(this::registerPlugins);
        EntityInit.ENTITY.register(modEventBus);
        ItemInit.ITEM.register(modEventBus);

    }

    private void registerPlugins(final FMLCommonSetupEvent event) {
        Critters.register(new VanillaCritterPlugin());
    }
}
