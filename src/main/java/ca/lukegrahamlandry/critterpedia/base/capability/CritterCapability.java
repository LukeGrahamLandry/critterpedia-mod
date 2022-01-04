package ca.lukegrahamlandry.critterpedia.base.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CritterCapability extends CapabilityProvider<CritterCapability> implements ICapabilitySerializable {

    public static final Capability<CritterCollection> CAP = CapabilityManager.get(new CapabilityToken<>(){});

    LazyOptional<CritterCollection> data = LazyOptional.of(CritterCollectionImpl::new);

    public CritterCapability() {
        super(CritterCapability.class);
    }

    @Override
    public Tag serializeNBT() {
        return ((CritterCollectionImpl) data.orElseGet(CritterCollectionImpl::new)).serializeNBT();
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        ((CritterCollectionImpl) data.orElseGet(CritterCollectionImpl::new)).deserializeNBT((CompoundTag) nbt);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CAP){
            return data.cast();
        }
        return super.getCapability(cap, side);
    }
}