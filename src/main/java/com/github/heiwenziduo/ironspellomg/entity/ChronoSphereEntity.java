package com.github.heiwenziduo.ironspellomg.entity;

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
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import static com.github.heiwenziduo.ironspellomg.initializer.OMGEntities.CHRONO_SPHERE_ENTITY;

/// "Chronosquare"
public class ChronoSphereEntity extends TecEntity implements TraceableEntity {
    public static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(ChronoSphereEntity.class, EntityDataSerializers.FLOAT);

    private LivingEntity caster = null;
    private String casterUUID = "";

    public ChronoSphereEntity(EntityType<ChronoSphereEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public ChronoSphereEntity(Level pLevel, float effectLength, float radius, @Nullable LivingEntity pCaster) {
        this(CHRONO_SPHERE_ENTITY.get(), pLevel);

        setDataRemainTick((int) (effectLength * 20));
        entityData.set(DATA_RADIUS, radius);
        caster = pCaster;
        if (pCaster != null)
            casterUUID = pCaster.getStringUUID();
    }

    @Override
    public void tick() {
        //todo: 1.使caster的仆从不被停滞  2.给予caster及其仆从100%闪避
        if (tickCount % 5 == 0) {
            // 将结界中的活物时停
            float radius = entityData.get(DATA_RADIUS);
            AABB detectBox = AABB.ofSize(position(), radius * 2, radius * 2, radius * 2);
            List<? extends Entity> entitiesList = level().getEntities(this, detectBox,
                    //
                    e -> e instanceof LivingEntity && e.isPickable() && e.isAlive() && e != getOwner());
            for (var e : entitiesList) {
                if (Objects.equals(casterUUID, e.getStringUUID())) continue;
                //if (e.position().distanceToSqr(position()) <= radius * radius)
                FvUtil.setTimeLock((LivingEntity) e, 7);
            }
        }
        super.tick();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_RADIUS, 3f);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Radius", CompoundTag.TAG_FLOAT)) {
            entityData.set(DATA_RADIUS, pCompound.getFloat("Radius"));
        }
        if (pCompound.contains("CasterUUID", CompoundTag.TAG_STRING)) {
            casterUUID = pCompound.getString("CasterUUID");
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putFloat("Radius", entityData.get(DATA_RADIUS));
        pCompound.putString("CasterUUID", casterUUID);
    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        return super.isAlliedTo(pEntity);
    }

    @Override
    public @Nullable Entity getOwner() {
        return caster;
    }

    /// 基于半径重设碰撞箱
    @Override
    protected AABB makeBoundingBox() {
        Vec3 pos = position();
        float radius = entityData.get(DATA_RADIUS);
        return AABB.ofSize(pos, radius * 2, radius * 2, radius * 2);
    }
}
