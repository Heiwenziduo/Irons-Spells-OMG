package com.github.heiwenziduo.ironspellomg.initializer;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.entity.client.model.ChronoSphereModel;
import com.github.heiwenziduo.ironspellomg.entity.client.renderer.ChronoSphereRenderer;
import com.github.heiwenziduo.ironspellomg.entity.client.renderer.TimelockPhantomRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = IronsSpellOMG.ModId, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OMGClientSetUp {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

    }

    //vanilla
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(OMGEntities.CHRONO_SPHERE_ENTITY.get(), ChronoSphereRenderer::new);

        // gecko
        event.registerEntityRenderer(OMGEntities.TIMELOCK_PHANTOM.get(), TimelockPhantomRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ChronoSphereModel.LAYER_LOCATION, ChronoSphereModel::createBodyLayer);
    }
}