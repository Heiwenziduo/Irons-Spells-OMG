package com.github.heiwenziduo.ironspellomg.data;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.data.provider.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = IronsSpellOMG.ModId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OMGDataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        boolean server = event.includeServer(), client = event.includeClient();

        generator.addProvider(event.includeClient(), new OMGItemModelProvider(packOutput, existingFileHelper));

        // item tags
        OMGBlockTagProvider btp = generator.addProvider(server, new OMGBlockTagProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(server, new OMGItemTagProvider(packOutput, lookupProvider, btp.contentsGetter(), existingFileHelper));

        // damage type
        RegistrySetBuilder registrySetBuilder = new RegistrySetBuilder();
        OMGDamageTypeProvider.register(registrySetBuilder);
        DatapackBuiltinEntriesProvider datapackRegistryProvider = new DatapackBuiltinEntriesProvider(packOutput, lookupProvider, registrySetBuilder, Set.of(IronsSpellOMG.ModId));

        generator.addProvider(server, datapackRegistryProvider);
        generator.addProvider(server, new OMGDamageTypeTagProvider(packOutput, datapackRegistryProvider.getRegistryProvider(), existingFileHelper));

        //
    }
}
