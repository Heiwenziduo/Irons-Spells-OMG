package com.github.heiwenziduo.ironspellomg.entity;

import com.github.heiwenziduo.ironspellomg.initializer.OMGEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import static com.github.heiwenziduo.ironspellomg.manager.client.TimelockManagerClient.AnimLength;

public class TimelockPhantom extends TecEntity implements GeoEntity {
    public TimelockPhantom(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public TimelockPhantom(Level pLevel) {
        this(OMGEntities.TIMELOCK_PHANTOM.get(), pLevel);

        setDataRemainTick(AnimLength);
    }

    public static double animSpeed = .833 * 20 / AnimLength; // .833是animation.json中的动画长度

    @Override
    public void tick() {
        super.tick();

    }

    // *** gecko ***

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "spawning", 0, this::animPredicate));
    }

    private PlayState animPredicate(AnimationState animationState) {
        animationState.getController()
                .setAnimationSpeed(animSpeed)
                .setAnimation(RawAnimation.begin().then("animation.model.attack", Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
}
