package com.github.heiwenziduo.ironspellomg.spell.nature;

import com.github.heiwenziduo.ironspellomg.initializer.OMGEffects;
import com.github.heiwenziduo.ironspellomg.spell.OMGHookedSpell;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.CastType;
import io.redspace.ironsspellbooks.api.spells.SpellAnimations;
import io.redspace.ironsspellbooks.api.spells.SpellRarity;
import io.redspace.ironsspellbooks.api.util.AnimationHolder;
import io.redspace.ironsspellbooks.api.util.Utils;
import io.redspace.ironsspellbooks.network.spell.ClientboundOakskinParticles;
import io.redspace.ironsspellbooks.registries.SoundRegistry;
import io.redspace.ironsspellbooks.setup.Messages;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Optional;

import static com.github.heiwenziduo.ironspellomg.IronsSpellOMG.resource;

/// 活体护甲
public class LivingArmor extends OMGHookedSpell {
    public static final float LivingArmor_Armor = 2;
    public static final float LivingArmor_LifeGen = 0.2f;

    @Override
    public ResourceLocation getSpellResource() {
        return resource("living_armor");
    }

    @Override
    public CastType getCastType() {
        return CastType.INSTANT;
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.ironspellomg.passive_regen", Utils.stringTruncation(LivingArmor_LifeGen * spellLevel, 1)),
                Component.translatable("ui.ironspellomg.armor", Utils.stringTruncation(LivingArmor_Armor * spellLevel, 1)),
                Component.translatable("ui.ironspellomg.effect_length", Utils.timeFromTicks(getSpellPower(spellLevel, caster) * 20, 1))

        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.COMMON)
            .setSchoolResource(SchoolRegistry.NATURE_RESOURCE)
            .setMaxLevel(8)
            .setCooldownSeconds(30)
            .build();

    public LivingArmor() {
        this.manaCostPerLevel = 5;
        this.baseSpellPower = 20;
        this.spellPowerPerLevel = 5;
        this.castTime = 0;
        this.baseManaCost = 15;
    }

    @Override
    public void onCast(Level level, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData) {
        entity.addEffect(new MobEffectInstance(OMGEffects.LIVING_ARMOR.get(), (int) (getSpellPower(spellLevel, entity) * 20), spellLevel - 1, false, false, true));

        Messages.sendToPlayersTrackingEntity(new ClientboundOakskinParticles(entity.position()), entity, true);
        super.onCast(level, spellLevel, entity, castSource, playerMagicData);
    }

    @Override
    public AnimationHolder getCastStartAnimation() {
        return SpellAnimations.SELF_CAST_ANIMATION;
    }

    @Override
    public Optional<SoundEvent> getCastFinishSound() {
        return Optional.of(SoundRegistry.OAKSKIN_CAST.get());
    }
}
