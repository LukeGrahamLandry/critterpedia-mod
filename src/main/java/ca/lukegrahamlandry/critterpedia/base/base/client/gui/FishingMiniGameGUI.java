package ca.lukegrahamlandry.critterpedia.base.client.gui;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.base.api.FishingRarity;
import ca.lukegrahamlandry.critterpedia.base.network.EndFishingMiniGamePacket;
import ca.lukegrahamlandry.critterpedia.base.network.NetworkInit;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.BossEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

public class FishingMiniGameGUI extends Screen {
    private static final ResourceLocation BACKGROUND = new ResourceLocation(ModMain.MOD_ID, "textures/gui/critterpedia_general.png");
    private int imageWidth = 195;
    private int imageHeight = 118;

    private static final ResourceLocation PROGRESS_BAR_TEXTURE = new ResourceLocation("textures/gui/bars.png");
    private static final ResourceLocation FISH_BAR = new ResourceLocation("textures/gui/bars.png");

    FishingRarity rarity;
    float barPosition = 0.5F; // 0-1
    float barVelocity = 0;
    float completion = 0.5F; // 0-1

    float fishPosition = 0.45F; // 0-1
    float fishTargetPos = fishPosition;

    public FishingMiniGameGUI(FishingRarity rarity) {
        super(new TextComponent("Fishing"));
        this.rarity = rarity;
    }

    @Override
    protected void init() {
        super.init();
    }

    static final Random rand = new Random();

    @Override
    public void tick() {
        super.tick();
        
        float direction = this.fishTargetPos - this.fishPosition;
        if (Math.abs(direction) > 0.05){
            this.fishPosition += Math.signum(direction) * this.rarity.fishSpeed;
        } else {
            if (rand.nextFloat() < this.rarity.fishMoveChance){
                this.fishTargetPos = rand.nextFloat();
            }
        }

        boolean between = (this.fishPosition > this.barPosition) && (this.fishPosition < (this.barPosition + rarity.barSize));
        if (between){
            this.completion += this.rarity.progressGainRate;
        } else {
            this.completion -= this.rarity.progressLossRate;
        }

        this.barPosition += this.barVelocity;
        this.barVelocity = (float) Math.max(this.barVelocity - rarity.barGravity, -0.1);
        if (barPosition > 1) {
            barPosition = 1;
            this.barVelocity = Math.min(0, this.barVelocity);
        }
        if (barPosition < 0) {
            barPosition = 0;
            this.barVelocity = Math.max(0, this.barVelocity);
        }

        if (this.completion >= 1){
            this.sendEndGame(true);
        }
        if (this.completion <= 0){
            this.sendEndGame(false);
        }
    }

    @Override
    public void render(PoseStack matrix, int p_96563_, int p_96564_, float p_96565_) {
        this.renderBackground(matrix);
        super.render(matrix, p_96563_, p_96564_, p_96565_);

        int progressBarX = 150;
        int progressBarY = 10;
        int ordinal_idk = BossEvent.BossBarColor.WHITE.ordinal() * 5 * 2;

        int lengthofBarInPixels = 183;

        matrix.pushPose();
        RenderSystem.setShaderTexture(0, PROGRESS_BAR_TEXTURE);
        matrix.translate(progressBarX, progressBarY, 0);
        matrix.mulPose(Vector3f.ZP.rotationDegrees(-90));
        matrix.translate(-progressBarX - lengthofBarInPixels, -progressBarY, 0);

        // progress bar //

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.blit(matrix, progressBarX, progressBarY, 0, BossEvent.BossBarColor.WHITE.ordinal() * 5 * 2, lengthofBarInPixels, 5);
        int filledPixels = (int)( this.completion * lengthofBarInPixels);
        if (filledPixels > 0) {
            this.blit(matrix, progressBarX, progressBarY, 0, BossEvent.BossBarColor.GREEN.ordinal() * 5 * 2, filledPixels, 5);
        }

        matrix.popPose();

        matrix.pushPose();
        RenderSystem.setShaderTexture(0, FISH_BAR);
        progressBarX = 100;
        matrix.translate(progressBarX, progressBarY, 0);
        matrix.mulPose(Vector3f.ZP.rotationDegrees(-90));
        matrix.translate(-progressBarX - lengthofBarInPixels, -progressBarY, 0);

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        this.blit(matrix, progressBarX, progressBarY, 0, BossEvent.BossBarColor.WHITE.ordinal() * 5 * 2, lengthofBarInPixels, 5);
        if (filledPixels > 0) {
            int offset = (int) (barPosition * lengthofBarInPixels);
            int size = (int) (this.rarity.barSize * lengthofBarInPixels);
            if ((offset + size) > lengthofBarInPixels){
                size = size - (offset + size - lengthofBarInPixels);
            }
            this.blit(matrix, progressBarX + offset, progressBarY, 0, BossEvent.BossBarColor.RED.ordinal() * 5 * 2, size, 5);
        }

        matrix.popPose();

        int fishY = progressBarY + (int) ((1-this.fishPosition) * lengthofBarInPixels);
        // TODO: scale
        Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(fishStack, progressBarX-5, fishY);
    }

    static final ItemStack fishStack = new ItemStack(Items.COD);
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

    @Override
    public boolean keyPressed(int key, int p_96553_, int p_96554_) {
        if (key == GLFW.GLFW_KEY_SPACE){
            barVelocity += this.rarity.barForce;
        }
        return super.keyPressed(key, p_96553_, p_96554_);
    }

    private void sendEndGame(boolean success){
        Minecraft.getInstance().setScreen(null);
        NetworkInit.INSTANCE.sendToServer(new EndFishingMiniGamePacket(success));
    }
}
