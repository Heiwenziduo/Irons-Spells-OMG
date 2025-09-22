package com.github.heiwenziduo.ironspellomg.client.renderer;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.entity.ChronoSphereEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChronoSphereRenderer extends EntityRenderer<ChronoSphereEntity> {
    private final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(IronsSpellOMG.ModId, "");
    private final ItemRenderer itemRenderer;

    public ChronoSphereRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);

        itemRenderer = pContext.getItemRenderer();
    }

    @Override
    public void render(ChronoSphereEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);

        Level level = pEntity.level();

        pPoseStack.pushPose();
        itemRenderer.renderStatic(
                new ItemStack(Items.DIAMOND_SWORD),
                ItemDisplayContext.GROUND,
                15728640,
                OverlayTexture.NO_OVERLAY,
                pPoseStack,
                pBuffer,
                level,
                pEntity.getId());


        pPoseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(ChronoSphereEntity pEntity) {
        return TEXTURE;
    }
}
