package com.github.heiwenziduo.ironspellomg.network.packet;

import com.github.heiwenziduo.ironspellomg.api.packet.VecIO;
import com.github.heiwenziduo.ironspellomg.manager.client.TimelockManagerClient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static java.lang.Math.PI;
import static java.lang.Math.random;

/// 触发{@link com.github.heiwenziduo.ironspellomg.curio.passive.TimelockCurio}的攻击时, 向客户端传来的特效包
public class ClientboundTimelockAttackPacket implements BoundedNetworkPacket, VecIO {
    // client often Not maintain an uuid list, the fast way is to use (concurrent) id. since we don't care if an entity travel through a portal
    // private final String uuid;
    private final int id;
    private final Vec3 direct;

    public ClientboundTimelockAttackPacket(int id, Vec3 direct) {
        this.id = id;
        this.direct = direct;
    }

    public ClientboundTimelockAttackPacket(FriendlyByteBuf buf) {
        id = buf.readInt();
        direct = VecIO.readVec3(buf);
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(id);
        VecIO.writeVec3(direct, buf);
    }

    @Override
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            TimelockManagerClient.getInstance().triggerAnim(id, direct);
        });
        return true;
    }

    public static Vec3 attackDirect(LivingEntity target, LivingEntity attacker) {
        if (attacker == null) return new Vec3(random(), 0, random()).normalize();
        int r = random() < .5 ? 1 : -1;
        // 残影出现于侧面
        // todo: random摇出重复方向重复角度的概率是不是太高了 ?
        float yRadius = (float) ((random() - 1) * PI / 3);
        return Vec3.directionFromRotation(new Vec2(0, attacker.getYRot()))
                .cross(new Vec3(0, 1, 0))
                .scale(r)
                .yRot(yRadius)
                .normalize();
    }
}
