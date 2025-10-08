package com.github.heiwenziduo.ironspellomg.initializer;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.entity.ChronoSphereEntity;
import com.github.heiwenziduo.ironspellomg.entity.TimelockPhantom;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.heiwenziduo.ironspellomg.IronsSpellOMG.resource;

public class OMGEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, IronsSpellOMG.ModId);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final RegistryObject<EntityType<ChronoSphereEntity>> CHRONO_SPHERE_ENTITY =
            ENTITIES.register("chrono_sphere", () -> EntityType.Builder
                    .<ChronoSphereEntity>of(ChronoSphereEntity::new, MobCategory.MISC)
                    .sized(1F, 1F)
                    .clientTrackingRange(16)
                    .build(resource("chrono_sphere").toString()));

    // =========================================================================================== Client Effect

    public static final RegistryObject<EntityType<TimelockPhantom>> TIMELOCK_PHANTOM =
            ENTITIES.register("timelock_phantom", () -> EntityType.Builder
                    .<TimelockPhantom>of(TimelockPhantom::new, MobCategory.MISC)
                    .sized(0.8F, 2F)
                    .build(resource("timelock_phantom").toString()));
}
