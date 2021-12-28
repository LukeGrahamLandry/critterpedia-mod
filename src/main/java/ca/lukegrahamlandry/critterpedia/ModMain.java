package ca.lukegrahamlandry.critterpedia;

import ca.lukegrahamlandry.critterpedia.base.api.Critters;
import ca.lukegrahamlandry.critterpedia.content.critters.VanillaCritterPlugin;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModMain.MOD_ID)
public class ModMain {
    public static final String MOD_ID = "critterpedia";

    public ModMain() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Critters.register(new VanillaCritterPlugin());
    }
}
