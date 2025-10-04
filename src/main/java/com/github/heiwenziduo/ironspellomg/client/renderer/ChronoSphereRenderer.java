package com.github.heiwenziduo.ironspellomg.client.renderer;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.client.model.ChronoSphereModel;
import com.github.heiwenziduo.ironspellomg.entity.ChronoSphereEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.github.heiwenziduo.ironspellomg.IronsSpellOMG.resource;
import static com.github.heiwenziduo.ironspellomg.entity.ChronoSphereEntity.DATA_RADIUS;

@OnlyIn(Dist.CLIENT)
public class ChronoSphereRenderer extends TecEntityRenderer<ChronoSphereEntity> {
    private final ResourceLocation TEXTURE = resource("textures/entity/chrono_sphere.png");
    private final ItemRenderer itemRenderer;
    private final EntityModel model;

    public ChronoSphereRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);

        model = new ChronoSphereModel(pContext.bakeLayer(ChronoSphereModel.LAYER_LOCATION));
        itemRenderer = pContext.getItemRenderer();
    }

    @Override
    public void render(ChronoSphereEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        Level level = pEntity.level();
        float radius = pEntity.getEntityData().get(DATA_RADIUS);

        // ================= test ==============
        if (pEntity.tickCount % 10 == 0){
            //System.out.println(radius);
        }

        // ================= test ==============
        pPoseStack.pushPose();
        pPoseStack.scale(radius * 2, radius * 2, radius * 2);
        //todo 特效优化
        pPoseStack.translate(0, -0.5, 0);

        /// {@link net.minecraft.client.renderer.entity.LivingEntityRenderer#render}
        RenderType rendertype = RenderType.entityTranslucent(getTextureLocation(pEntity));
        VertexConsumer vertexconsumer = pBuffer.getBuffer(rendertype);
        //int i = getOverlayCoords(pEntity, this.getWhiteOverlayProgress(pEntity, pPartialTicks));
        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, 1, 1.0F, 1.0F, 1.0F, 0.5f);
        pPoseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(ChronoSphereEntity pEntity) {
        return TEXTURE;
    }
}
