package com.github.heiwenziduo.ironspellomg.curio.passive;

import com.github.heiwenziduo.fvlib.library.registry.FvDamageType;
import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.curio.AbstractPassiveAbility;
import com.github.heiwenziduo.ironspellomg.manager.server.TimelockManagerServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;

import static com.github.heiwenziduo.ironspellomg.initializer.OMGItems.TIMELOCK_CURIO;
import static com.github.heiwenziduo.ironspellomg.initializer.registry.OMGDamageType.TIMELOCK;

@Mod.EventBusSubscriber(modid = IronsSpellOMG.ModId, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TimelockCurio extends AbstractPassiveAbility {
    public static final float TimelockChance = 0.33f;
    /// 单次攻击最多循环触发x次
    public static final byte MaxLoopLife = 7;

    @SubscribeEvent
    static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().is(TIMELOCK)) return; // 循环触发使用自定义逻辑

        // in AttackEntityEvent, we don't know damage. so use this
        Entity e = event.getSource().getDirectEntity(); // 通过投射物的伤害不会触发
        if (e instanceof LivingEntity attacker) {
            LivingEntity target = event.getEntity();

            // System.out.println("client?: " + target.level().isClientSide); // always false
            if (target.level().isClientSide) return;
            CuriosApi.getCuriosInventory(attacker).ifPresent(curiosInventory -> {
                curiosInventory.findFirstCurio(TIMELOCK_CURIO.get()).ifPresent(curio -> {
                    if (Math.random() > TimelockChance) return;

                    TimelockManagerServer.getInstance().triggerTimelock(target, attacker, event.getAmount(), (byte) 0);
                });
            });
        }
    }

    ///
    public static void doTimelockAttack(LivingEntity target, @Nullable LivingEntity attacker, float damage, byte life) {
        int tmp = target.invulnerableTime;
        target.invulnerableTime = 0;
        target.hurt(FvDamageType.source(target.level().registryAccess(), TIMELOCK, attacker, attacker), damage);
        target.invulnerableTime = tmp; // 时停攻击忽视无敌帧

        //System.out.println("doTimelockAttack-----life: " + life);
        // define loop trigger
        if (attacker != null && life < MaxLoopLife) {
            float coefficient = (float) (1 / Math.pow(2, life)); // 循环触发概率降低
            if (Math.random() > TimelockChance * coefficient) return;
            TimelockManagerServer.getInstance().triggerTimelock(target, attacker, damage, (byte) (life + 1)); // 可以再度触发
        }
    }

}
