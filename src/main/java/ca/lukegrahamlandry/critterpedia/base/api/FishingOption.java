package ca.lukegrahamlandry.critterpedia.base.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class FishingOption implements WeightedEntry {
    public final ResourceLocation id;
    protected ResourceLocation triggeredCritter;
    protected ResourceLocation fishItem;
    protected ResourceLocation rarity;
    private Weight weight;

    public FishingOption(ResourceLocation id) {
        this.id = id;
    }

    public List<ItemStack> getItems(){
        List<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(ForgeRegistries.ITEMS.getValue(fishItem)));
        return items;
    }

    protected void setWeight(int w){
        this.weight = Weight.of(w);
    }

    @Override
    public Weight getWeight() {
        return this.weight;
    }
}
