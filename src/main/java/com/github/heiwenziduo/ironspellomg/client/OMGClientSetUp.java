package com.github.heiwenziduo.ironspellomg.client;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.client.model.ChronoSphereModel;
import com.github.heiwenziduo.ironspellomg.client.renderer.ChronoSphereRenderer;
import com.github.heiwenziduo.ironspellomg.initializer.OMGEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = IronsSpellOMG.ModId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OMGClientSetUp {
    //gecko
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

    }

    //vanilla
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OMGEntities.CHRONO_SPHERE_ENTITY.get(), ChronoSphereRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ChronoSphereModel.LAYER_LOCATION, ChronoSphereModel::createBodyLayer);
    }
}