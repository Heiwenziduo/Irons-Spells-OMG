package com.github.heiwenziduo.ironspellomg.manager.client;

import com.github.heiwenziduo.ironspellomg.manager.server.TimelockManagerServer;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
/// should only be used by client side
public class TimelockManagerClient {
    private static final TimelockManagerClient INSTANCE = new TimelockManagerClient();
    public static TimelockManagerClient getInstance() {
        return INSTANCE;
    }

    public static final int AnimLength = TimelockManagerServer.AnimLength;
    public static final int ANIM_INSTANCE_NUMBER = 4;

    private final Multimap<Integer, TimelockAnim> animMap = ArrayListMultimap.create();

    /// 将一次延时伤害加入map
    public void triggerAnim(int id, Vec3 dir) {
        var collection = animMap.get(id);
        if (collection.size() > ANIM_INSTANCE_NUMBER) {
            collection.stream().findFirst().ifPresent(collection::remove); // 存在动画过多时弹出最早一个
        }
        // "Changes to the returned collection will update the underlying multimap, and vice versa."
        collection.add(new TimelockAnim(Minecraft.getInstance().level.getGameTime(), dir));
    }

    /// 清理map
    public void tick(ClientLevel level) {
        if (level == null) return;
        long currentTime = level.getGameTime();

        if (currentTime % 40 == 0) {
            System.out.println("client tick 40 --- " + currentTime);
        }
        animMap.entries().removeIf(entry -> {
            Entity entity = level.getEntity(entry.getKey());
            return entity == null || !entity.isAlive() || currentTime > entry.getValue().startTime + AnimLength;
        });
    }

    private record TimelockAnim(long startTime, Vec3 dir) {}

}
