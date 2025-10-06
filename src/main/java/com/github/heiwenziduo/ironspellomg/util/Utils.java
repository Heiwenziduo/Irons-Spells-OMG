package com.github.heiwenziduo.ironspellomg.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Utils {
    /// 在服务器(所有维度)搜索给定uuid的实体
    @Nullable
    public static Entity findEntityByUuid(MinecraftServer server, UUID uuid) {
        for (ServerLevel level : server.getAllLevels()) {
            Entity entity = level.getEntity(uuid);
            if (entity != null) {
                return entity;
            }
        }
        return null;
    }

    /// 在服务器(所有维度)搜索给定uuid的实体
    @Nullable
    public static Entity findEntityByUuid(MinecraftServer server, String uuid) {
        return findEntityByUuid(server, UUID.fromString(uuid));
    }
}
