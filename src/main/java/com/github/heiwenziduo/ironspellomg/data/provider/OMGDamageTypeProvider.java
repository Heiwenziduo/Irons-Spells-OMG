package com.github.heiwenziduo.ironspellomg.data.provider;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

import static com.github.heiwenziduo.ironspellomg.initializer.registry.OMGDamageType.*;


public class OMGDamageTypeProvider implements RegistrySetBuilder.RegistryBootstrap<DamageType> {

    /** Registers this provider with the registry set builder */
    public static void register(RegistrySetBuilder builder) {
        builder.add(Registries.DAMAGE_TYPE, new OMGDamageTypeProvider());
    }

    @Override
    public void run(BootstapContext<DamageType> context) {
        String id = IronsSpellOMG.ModId + ".";
        context.register(TIMELOCK, new DamageType(id + TIMELOCK.location().getPath(), DamageScaling.NEVER, 0.1f));

    }
}
