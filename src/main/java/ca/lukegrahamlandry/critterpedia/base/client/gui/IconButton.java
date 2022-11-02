package ca.lukegrahamlandry.critterpedia.base.client.gui;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.api.CritterType;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

public class IconButton extends Button {
    public static final ResourceLocation BACKGROUND = new ResourceLocation(ModMain.MOD_ID, "textures/gui/critterpedia_general.png");

    public CritterType.RenderCritterIcon icon;
    public boolean pressed = false;

    public IconButton(int x, int y, int width, int height, OnPress action, CritterType.RenderCritterIcon icon, OnTooltip tooltip, boolean pressed) {
        super(x, y, width, height, new TextComponent(""), action, tooltip);
        this.icon = icon;
        this.pressed = pressed;
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
        this.icon.render(stack, this.x, this.y);

        if (this.isHoveredOrFocused()) {
            this.renderToolTip(stack, mouseX, mouseY);
        }
    }
}
