package com.github.heiwenziduo.ironspellomg.manager;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.manager.server.TimelockManagerServer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IronsSpellOMG.ModId, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventBus {

    @SubscribeEvent
    static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            TimelockManagerServer.getInstance().tick(event.getServer());
        }
    }
}
