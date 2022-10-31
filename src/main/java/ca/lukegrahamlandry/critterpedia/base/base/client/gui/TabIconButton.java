package ca.lukegrahamlandry.critterpedia.base.client.gui;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.api.CritterType;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

public class TabIconButton extends IconButton {
    public TabIconButton(int x, int y, int width, int height, OnPress action, CritterType.RenderCritterIcon icon, OnTooltip tooltip, boolean pressed) {
        super(x, y, width, height, action, icon, tooltip, pressed);
    }

    public void renderButton(PoseStack stack, int mouseX, int mouseY, float tick) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);

        int topY = this.pressed ? 148 : 118;

        this.blit(stack, this.x - 5, this.y - 10, 0, topY, 27, 30);
        this.icon.render(stack, this.x, this.y);

        if (this.isHoveredOrFocused()) {
            this.renderToolTip(stack, mouseX, mouseY);
        }
    }
}
