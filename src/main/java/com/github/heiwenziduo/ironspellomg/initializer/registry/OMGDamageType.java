package com.github.heiwenziduo.ironspellomg.initializer.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

import static com.github.heiwenziduo.ironspellomg.IronsSpellOMG.resource;

public class OMGDamageType {
    public static final ResourceKey<DamageType> TIMELOCK = create("timelock");

    /** Creates a new damage type tag */
    private static ResourceKey<DamageType> create(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, resource(name));
    }
}
