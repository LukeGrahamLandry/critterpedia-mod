package ca.lukegrahamlandry.critterpedia.content.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;

public class MicroBatEntity extends Bat {
    public MicroBatEntity(EntityType<? extends MicroBatEntity> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
    }

    @Override
    public void tick() {
        super.tick();
        // this.setResting(true);
    }
}
