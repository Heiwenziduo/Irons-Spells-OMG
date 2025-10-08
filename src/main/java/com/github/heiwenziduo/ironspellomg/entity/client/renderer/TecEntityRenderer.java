package com.github.heiwenziduo.ironspellomg.entity.client.renderer;

import com.github.heiwenziduo.ironspellomg.entity.TecEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.NotNull;

public abstract class TecEntityRenderer<T extends TecEntity> extends EntityRenderer<T> {
    protected TecEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        // super 里只有名字渲染逻辑
        //super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected boolean shouldShowName(T pEntity) {
        return false;
    }

}
