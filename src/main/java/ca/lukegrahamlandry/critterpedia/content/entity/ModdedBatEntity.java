package ca.lukegrahamlandry.critterpedia.content.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ModdedBatEntity extends Bat {
    public ModdedBatEntity(EntityType<? extends ModdedBatEntity> p_27412_, Level p_27413_) {
        super(p_27412_, p_27413_);
    }

    @Override
    public void tick() {
        super.tick();
        // this.setResting(true);
    }
}
