package ca.lukegrahamlandry.critterpedia.base.api;

import ca.lukegrahamlandry.critterpedia.ModMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class IDHasIcon {
    public final ResourceLocation id;
    private CritterType.RenderCritterIcon icon;

    public IDHasIcon(ResourceLocation id){
        this.id = id;
    }

    public IDHasIcon(String id){
        this(id.contains(":") ? new ResourceLocation(id) : new ResourceLocation(ModMain.MOD_ID, id));
    }

    public IDHasIcon icon(Item icon){
        ItemStack thestack = new ItemStack(icon);

        this.icon = (stack, x, y) -> {
            Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(thestack, x, y);
        };
        return this;
    }

    LivingEntity renderCache;

    public IDHasIcon icon(Supplier<EntityType> icon){
        this.icon = (stack, x, y) -> {
            if (Minecraft.getInstance().level == null) return;
            if (renderCache == null) {
                Entity toRender = icon.get().create(Minecraft.getInstance().level);
                if (! (toRender instanceof LivingEntity)) return;
                renderCache = (LivingEntity) toRender;
            }
            InventoryScreen.renderEntityInInventory(x, y, 30, 0, 0, renderCache);
        };
        return this;
    }

    public CritterType.RenderCritterIcon getIcon(){
        return this.icon;
    }
}
