package com.github.heiwenziduo.ironspellomg.spell.timelessvoid;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.entity.ChronoSphereEntity;
import com.github.heiwenziduo.ironspellomg.spell.OMGHookedSpell;
import io.redspace.ironsspellbooks.api.config.DefaultConfig;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.registry.SchoolRegistry;
import io.redspace.ironsspellbooks.api.spells.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import static com.github.heiwenziduo.ironspellomg.IronsSpellOMG.resource;

/// 时间结界
@AutoSpellConfig
public class ChronoSphere extends OMGHookedSpell {

    @Override
    public List<MutableComponent> getUniqueInfo(int spellLevel, LivingEntity caster) {
        return List.of(
                Component.translatable("ui.ironspellomg.radius", getSphereRadius(spellLevel)),
                Component.translatable("ui.ironspellomg.effect_length", getSphereEffectLength(spellLevel))
        );
    }

    private final DefaultConfig defaultConfig = new DefaultConfig()
            .setMinRarity(SpellRarity.LEGENDARY)
            .setSchoolResource(SchoolRegistry.ENDER_RESOURCE)
            .setMaxLevel(3)
            .setCooldownSeconds(180)
            .build();

    public ChronoSphere() {
        this.manaCostPerLevel = 50;
        this.baseSpellPower = 4;
        this.spellPowerPerLevel = 1;
        this.castTime = 10;
        this.baseManaCost = 200;
    }

    private float getSphereRadius(int spellLevel) {
        return 5 + 2 * spellLevel;
    }

    private float getSphereEffectLength(int spellLevel) {
        return 4 + 1.5f * spellLevel;
    }

    @Override
    public void onCast(Level world, int spellLevel, LivingEntity caster, CastSource castSource, MagicData playerMagicData) {
        Level level = caster.level();
        float radius = getSphereRadius(spellLevel);
        Vec3 summonPos = caster.position().add(0, caster.getEyeHeight(), 0).add(caster.getLookAngle().scale(radius / 3));

        ChronoSphereEntity sphere = new ChronoSphereEntity(level, getSphereEffectLength(spellLevel), radius, caster);
        sphere.setPos(summonPos);

        level.addFreshEntity(sphere);

        super.onCast(world, spellLevel, caster, castSource, playerMagicData);
    }

    @Override
    public ResourceLocation getSpellResource() {
        return resource("chrono_sphere");
    }

    @Override
    public DefaultConfig getDefaultConfig() {
        return defaultConfig;
    }

    @Override
    public CastType getCastType() {
        return CastType.LONG;
    }
}
