package ca.lukegrahamlandry.critterpedia.content.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTab extends CreativeModeTab {
    public static final ModCreativeTab fish = new ModCreativeTab(CreativeModeTab.TABS.length, "critter_fish");
    private ModCreativeTab(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Items.COD);
    }
}