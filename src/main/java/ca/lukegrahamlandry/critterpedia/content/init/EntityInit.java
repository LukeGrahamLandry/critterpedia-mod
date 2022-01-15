package ca.lukegrahamlandry.critterpedia.content.init;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.entity.FlyEntity;
import ca.lukegrahamlandry.critterpedia.content.entity.ModdedBatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITIES, ModMain.MOD_ID);

    public static final String[] bats = new String[]{"speckled_flying_fox", "eastern_red_bat", "hammer_head_bat", "hoary_bat", "big_eared_bat"};

    public static RegistryObject<EntityType<FlyEntity>> FLY = ENTITY.register("fly", () -> EntityType.Builder.of(FlyEntity::new, MobCategory.CREATURE).sized(0.9F, 1F).build("fly"));

    static {
        for (String rl : bats){
            ENTITY.register(rl, () -> EntityType.Builder.of(ModdedBatEntity::new, MobCategory.CREATURE).sized(EntityType.BAT.getWidth(), EntityType.BAT.getHeight()).build(rl));
        }
    }
}
