package com.github.heiwenziduo.ironspellomg.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/// 触发{@link com.github.heiwenziduo.ironspellomg.curio.passive.TimelockCurio}的攻击时, 向客户端传来的特效包
public class ClientboundTimelockAttackPacket implements BoundedNetworkPacket {

    public ClientboundTimelockAttackPacket(FriendlyByteBuf buf) {

    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        return true;
    }
}
