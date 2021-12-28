package ca.lukegrahamlandry.critterpedia.base.client.gui;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.api.CritterType;
import ca.lukegrahamlandry.critterpedia.base.api.Critters;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CritterpediaScreen extends Screen {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(ModMain.MOD_ID, "textures/gui/critterpedia_general.png");
    private int imageWidth = 195;
    private int imageHeight = 118;

    private int squareSize = 18;
    private int rowLength = 6;


    // todo: pass in list of the player's unlocked critters with packet
    public CritterpediaScreen() {
        super(new TextComponent("Critterpedia"));
    }

    @Override
    protected void init() {
        super.init();

        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        int startX = 9 + i;
        int startY = 21 + j;
        int rowPos = 0;
        int colPos = 0;
        List<ItemButton> buttons = new ArrayList<>();
        for (CritterType critter : Critters.getCritters()){
            int x = startX + (rowPos * squareSize);
            int y = startY + (colPos * squareSize);

            buttons.add(new ItemButton(x, y, squareSize, squareSize, this::onItemButtonPress, critter.getIcon(), (button, stack, X, Y) -> {
                this.renderTooltip(stack, new TextComponent(critter.id.toString()), X, Y);
            }));

            rowPos++;
            if (rowPos >= rowLength){
                rowPos = 0;
                colPos++;
            }
        }

        // this reverses the order that they'll render so tooltips arent under the next button
        for (int index=buttons.size()-1;index>=0;index--){
            this.addRenderableWidget(buttons.get(index));
        }
    }

    private void onItemButtonPress(Button button){
        System.out.println("button");
    }

    @Override
    public void render(PoseStack stack, int p_96563_, int p_96564_, float p_96565_) {
        this.renderBackground(stack);
        super.render(stack, p_96563_, p_96564_, p_96565_);

    }

    @Override
    public void renderBackground(PoseStack stack) {
        super.renderBackground(stack);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }
}
