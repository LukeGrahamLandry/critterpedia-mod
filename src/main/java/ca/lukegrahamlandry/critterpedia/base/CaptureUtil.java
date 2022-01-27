package ca.lukegrahamlandry.critterpedia.base;

import ca.lukegrahamlandry.critterpedia.base.api.CritterType;
import ca.lukegrahamlandry.critterpedia.base.api.Critters;
import ca.lukegrahamlandry.critterpedia.base.api.Subtype;
import ca.lukegrahamlandry.critterpedia.base.capability.CritterCapability;
import ca.lukegrahamlandry.critterpedia.base.capability.CritterCollection;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class CaptureUtil {
    public static void capture(Player player, Entity mob){
        player.getCapability(CritterCapability.CAP).ifPresent((critterCap -> {
            handleCapture(critterCap, mob);
        }));
    }

    public static void handleCapture(CritterCollection critterCap, Entity mob){
        CritterType critter = Critters.getCritter(mob);
        if (critter == null) return;
        if (critter.hasSubtypes()){
            Subtype subtype = critter.getSubtype(mob);
            if (subtype != null) {
                critterCap.collectSubType(critter.id, subtype.id);
                return;
            } else {
                System.out.println(mob.getType().getRegistryName().toString() + " is a critter " + critter.id + " and should have a subtype but was null so just capturing the base type for now please report bug on https://github.com/LukeGrahamLandry/critterpedia-mod");
            }
        }

        critterCap.collectCritter(critter.id);
    }
}
