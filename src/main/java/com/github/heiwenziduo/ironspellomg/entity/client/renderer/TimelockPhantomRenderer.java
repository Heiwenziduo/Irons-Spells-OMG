package com.github.heiwenziduo.ironspellomg.entity.client.renderer;

import com.github.heiwenziduo.ironspellomg.entity.TimelockPhantom;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import static com.github.heiwenziduo.ironspellomg.IronsSpellOMG.resource;

public class TimelockPhantomRenderer extends GeoEntityRenderer<TimelockPhantom> {

    public TimelockPhantomRenderer(EntityRendererProvider.Context context) {
        super(context, new DefaultedEntityGeoModel<>(resource("timelock_phantom")));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(TimelockPhantom pEntity) {
        return resource("textures/entity/timelock_phantom.png");
    }

    @Override
    public void render(TimelockPhantom entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        // System.out.println("render yaw: " + entityYaw + "    yRot: " + entity.getYRot()); // identical
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public void preRender(PoseStack poseStack, TimelockPhantom entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        // it seems like our entity can't handle rotate itself (why?). we handle it here
        poseStack.mulPose(Axis.YP.rotationDegrees(entity.getYRot() * -1));
    }

    @Override
    public Color getRenderColor(TimelockPhantom animatable, float partialTick, int packedLight) {
        return Color.ofRGBA(0.9f, 0.6f, 1f, animatable.getOpacity());
    }

    @Override
    public RenderType getRenderType(TimelockPhantom animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}
