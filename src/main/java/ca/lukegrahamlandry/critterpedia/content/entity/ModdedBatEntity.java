package ca.lukegrahamlandry.critterpedia.content.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;

public class ModdedBatEntity extends Bat {
    public ModdedBatEntity(EntityType<? extends ModdedBatEntity> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
    }

    // TODO: either make this not do the hang on the ceiling goal or make the animation stop when this.isResting()
}
