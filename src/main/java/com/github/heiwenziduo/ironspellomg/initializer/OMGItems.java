package com.github.heiwenziduo.ironspellomg.initializer;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.curio.Butterfly;
import com.github.heiwenziduo.ironspellomg.curio.OMGHookedCurio;
import com.github.heiwenziduo.ironspellomg.curio.Refresher;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OMGItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronsSpellOMG.ModId);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> BUTTERFLY = ITEMS.register("butterfly", Butterfly::new);
    public static final RegistryObject<OMGHookedCurio> REFRESHER = ITEMS.register("refresher", Refresher::new);

}
