package com.github.heiwenziduo.ironspellomg.initializer;

import com.github.heiwenziduo.fvlib.library.api.DispelType;
import com.github.heiwenziduo.fvlib.library.api.FvAttribute;
import com.github.heiwenziduo.fvlib.library.effect.FvHookedEffect;
import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.effect.NormalDefinition;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.effect.MobEffectCategory.BENEFICIAL;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.ADDITION;

public class OMGEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, IronsSpellOMG.ModId);
    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }

    public static final RegistryObject<MobEffect> LIVING_ARMOR = EFFECTS.register("living_armor",
            () -> new FvHookedEffect(BENEFICIAL, 0x8CFF00, DispelType.BASIC)
                    .addAttributeModifier(FvAttribute.PASSIVE_REGEN, "c74429aa-44a7-48c3-8441-49077a041321", NormalDefinition.LivingArmor_LifeGen, ADDITION)
                    .addAttributeModifier(Attributes.ARMOR, "21922e3d-ee29-42c1-bf2d-0e61985bd702", NormalDefinition.LivingArmor_Armor, ADDITION));
}
