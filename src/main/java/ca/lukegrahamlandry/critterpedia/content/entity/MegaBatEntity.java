package ca.lukegrahamlandry.critterpedia.content.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;

public class MegaBatEntity extends Bat {
    public MegaBatEntity(EntityType<? extends MegaBatEntity> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
    }

    @Override
    public void tick() {
        super.tick();
        // this.setResting(true);
    }
}
