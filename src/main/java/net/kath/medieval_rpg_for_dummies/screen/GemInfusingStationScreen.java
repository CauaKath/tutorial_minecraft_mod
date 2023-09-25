package net.kath.medieval_rpg_for_dummies.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.kath.medieval_rpg_for_dummies.MedievalRpgMod;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GemInfusingStationScreen extends AbstractContainerScreen<GemInfusingStationMenu> {
  private static final ResourceLocation TEXTURE =
          new ResourceLocation(MedievalRpgMod.MOD_ID,"textures/gui/gem_infusing_station_gui.png");
  public GemInfusingStationScreen(GemInfusingStationMenu menu, Inventory inventory, Component component) {
    super(menu, inventory, component);
  }

  @Override
  protected void init() {
    super.init();
  }

  @Override
  protected void renderBg(PoseStack stack, float partialTick, int mouseX, int mouseY) {
    RenderSystem.setShader(GameRenderer::getPositionTexShader);
    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.setShaderTexture(0, TEXTURE);
    int x = (width - imageWidth) / 2;
    int y = (height - imageHeight) / 2;

    this.blit(stack, x, y, 0, 0, imageWidth, imageHeight);

    renderProgressArrow(stack, x, y);
  }

  private void renderProgressArrow(PoseStack pPoseStack, int x, int y) {
    if(menu.isCrafting()) {
      blit(pPoseStack, x + 105, y + 33, 176, 0, 8, menu.getScaledProgress());
    }
  }

  @Override
  public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
    renderBackground(stack);
    super.render(stack, mouseX, mouseY, delta);
    renderTooltip(stack, mouseX, mouseY);
  }
}
