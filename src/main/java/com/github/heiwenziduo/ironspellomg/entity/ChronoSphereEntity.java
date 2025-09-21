package com.github.heiwenziduo.ironspellomg.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ChronoSphereEntity extends TecEntity implements TraceableEntity {

    public ChronoSphereEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        return false;
    }

    @Override
    public @Nullable Entity getOwner() {
        return null;
    }
}
