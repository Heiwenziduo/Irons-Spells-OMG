package com.github.heiwenziduo.ironspellomg.entity;

import com.github.heiwenziduo.fvlib.FvLib;
import com.github.heiwenziduo.fvlib.library.FvUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.github.heiwenziduo.ironspellomg.initializer.OMGEntities.CHRONO_SPHERE_ENTITY;

public class ChronoSphereEntity extends TecEntity implements TraceableEntity {
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(ChronoSphereEntity.class, EntityDataSerializers.FLOAT);

    private LivingEntity caster = null;

    public ChronoSphereEntity(EntityType<ChronoSphereEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public ChronoSphereEntity(Level pLevel, float effectLength, float radius, @Nullable LivingEntity pCaster) {
        this(CHRONO_SPHERE_ENTITY.get(), pLevel);

        setDataRemainTick((int) (effectLength * 20));
        entityData.set(DATA_RADIUS, radius);
        caster = pCaster;
    }

    @Override
    public void tick() {
        //todo: 1.将remainTick写入AdditionalSaveData 2.使之认主(team?)
        if (tickCount % 5 == 0) {
            // 将结界中的活物时停
            float radius = entityData.get(DATA_RADIUS);
            AABB detectBox = AABB.ofSize(position(), radius * 2, radius * 2, radius * 2);
            List<? extends Entity> entitiesList = level().getEntities(this, detectBox,
                    //
                    e -> e instanceof LivingEntity && e.isPickable() && e.isAlive() && e != getOwner());
            for (var e : entitiesList) {
                if (e.position().distanceToSqr(position()) <= radius * radius)
                    FvUtil.setTimeLock((LivingEntity) e, 10);
            }
        }
        super.tick();
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(DATA_REMAIN_TICK, 100);
        entityData.define(DATA_RADIUS, 3f);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Radius", CompoundTag.TAG_FLOAT)) {
            entityData.set(DATA_RADIUS, pCompound.getFloat("Radius"));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putFloat("Radius", entityData.get(DATA_RADIUS));
    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity);
    }

    @Override
    public @Nullable Entity getOwner() {
        return caster;
    }
}
