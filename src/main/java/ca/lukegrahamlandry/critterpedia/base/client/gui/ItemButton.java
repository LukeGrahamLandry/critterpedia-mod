package ca.lukegrahamlandry.critterpedia.base.client.gui;

import ca.lukegrahamlandry.critterpedia.ModMain;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ItemButton extends Button {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(ModMain.MOD_ID, "textures/gui/critterpedia_general.png");

    Item item;
    public boolean pressed = false;
    private final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

    public ItemButton(int x, int y, int width, int height, OnPress action, Item item, OnTooltip tooltip) {
        super(x, y, width, height, new TextComponent(""), action, tooltip);
        this.item = item;
    }


    public void renderButton(PoseStack stack, int mouseX, int mouseY, float tick) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);

        int topY = this.pressed ? 18 : 0;
        if (this.isHoveredOrFocused()) {
            topY = 36;
        }

        this.blit(stack, this.x, this.y, 195, topY, 18, 18); // 212, this.pressed ? 17 : 35
        this.itemRenderer.renderAndDecorateItem(new ItemStack(this.item), this.x, this.y);

        if (this.isHoveredOrFocused()) {
            this.renderToolTip(stack, mouseX, mouseY);
        }
    }
}
