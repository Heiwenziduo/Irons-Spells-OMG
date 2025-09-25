package com.github.heiwenziduo.ironspellomg;

import com.github.heiwenziduo.ironspellomg.initializer.OMGEffects;
import com.github.heiwenziduo.ironspellomg.initializer.OMGEntities;
import com.github.heiwenziduo.ironspellomg.initializer.OMGItems;
import com.github.heiwenziduo.ironspellomg.initializer.OMGSpells;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

/// 铁咒: &nbsp;技能征召
@Mod(IronsSpellOMG.ModId)
public class IronsSpellOMG {
    public static final String ModId = "ironspellomg";

    public IronsSpellOMG(FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();

        OMGEffects.register(eventBus);
        OMGEntities.register(eventBus);
        OMGItems.register(eventBus);
        OMGSpells.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }

    public static ResourceLocation resource(@NotNull String name) {
        return ResourceLocation.fromNamespaceAndPath(ModId, name);
    }
}
