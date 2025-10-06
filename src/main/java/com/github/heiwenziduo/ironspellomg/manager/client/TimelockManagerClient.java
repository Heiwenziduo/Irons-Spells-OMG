package com.github.heiwenziduo.ironspellomg.manager.client;

import com.github.heiwenziduo.fvlib.library.FvUtil;
import com.github.heiwenziduo.ironspellomg.curio.passive.TimelockCurio;
import com.github.heiwenziduo.ironspellomg.util.Utils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
/// should only be used by client side
public class TimelockManagerClient {
    private static final TimelockManagerClient INSTANCE = new TimelockManagerClient();
    public static TimelockManagerClient getInstance() {
        return INSTANCE;
    }

    /// 时停时长
    public static final int TimelockLength = 12;
    /// 再次造成伤害的间隔
    public static final int RedamageDelay = 8;
    /// 动画时长
    public static final int AnimLength = 12;

    private final Multimap<String, TimelockContext> entityMap = ArrayListMultimap.create();

    /// 将一次延时伤害加入map
    public void triggerTimelock(LivingEntity target, LivingEntity attacker, float damage, byte life) {
        if (target.level().isClientSide) return;

        Level level = target.level();
        level.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        String uuid = target.getStringUUID();
        entityMap.put(uuid, new TimelockContext(damage, target.getServer().getTickCount(), attacker.getStringUUID(), life));
        FvUtil.setTimeLock(target, TimelockLength);
        syncToClient();
    }

    /// 清理map
    public void tick(MinecraftServer server) {
        int currentTime = server.getTickCount();

        if (currentTime % 20 == 0) {
            System.out.println("server tick 20 --- " + currentTime);
            System.out.println(entityMap.entries());
        }
        entityMap.entries().removeIf(entry -> {
            LivingEntity target = (LivingEntity) Utils.findEntityByUuid(server, entry.getKey());
            LivingEntity attacker = (LivingEntity) Utils.findEntityByUuid(server, entry.getValue().attackerUUID);

            boolean flag0 = target != null;
            boolean flag1 = target == null || !target.isAlive();
            boolean flag2 = currentTime > entry.getValue().startTick + RedamageDelay;

            System.out.println("removeIf flag:  " + flag0 + flag1 + flag2);
            if (flag0 && !flag1 && flag2) {
                // 仅实体存在时触发
                TimelockCurio.doTimelockAttack(target, attacker, entry.getValue().damage, entry.getValue().life);
            }
            return flag1 || flag2;
        });
    }

    private record TimelockContext(float damage, long startTick, String attackerUUID, byte life) {}

    private void syncToClient() {

    }
}
