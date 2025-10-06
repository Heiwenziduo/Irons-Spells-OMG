package com.github.heiwenziduo.ironspellomg.initializer;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OMGCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IronsSpellOMG.ModId);
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> OMG_TAB = CREATIVE_MODE_TABS.register("ironspellomg",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(OMGItems.TIMELOCK_CURIO.get()))
                    .title(Component.translatable("creativetab.ironspellomg_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        //======================================
                        pOutput.accept(OMGItems.TIMELOCK_CURIO.get());

                        //======================================
                        pOutput.accept(OMGItems.REFRESHER.get());
                        pOutput.accept(OMGItems.BUTTERFLY.get());
                        pOutput.accept(OMGItems.BOOT_OF_TRAVEL.get());
                        pOutput.accept(OMGItems.HEART_OF_DRAGON.get());
                        pOutput.accept(OMGItems.OCTARINE_CORE.get());
                    })
                    .build());


}
