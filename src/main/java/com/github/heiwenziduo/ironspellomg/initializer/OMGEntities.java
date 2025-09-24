package com.github.heiwenziduo.ironspellomg.initializer;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.entity.ChronoSphereEntity;
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
            ENTITIES.register("entity_chrono_sphere", () -> EntityType.Builder
                    .<ChronoSphereEntity>of(ChronoSphereEntity::new, MobCategory.MISC)
                    .sized(1F, 1F)
                    .clientTrackingRange(16)
                    .build(resource("entity_chrono_sphere").toString())
            );
}
