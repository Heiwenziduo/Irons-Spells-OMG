package com.github.heiwenziduo.ironspellomg.mixin;

import com.github.heiwenziduo.ironspellomg.api.mixin.ItemCooldownsMixinAPI;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ConcurrentModificationException;
import java.util.Map;

@Mixin(ItemCooldowns.class)
public abstract class ItemCooldownsMixin implements ItemCooldownsMixinAPI {

    @Shadow @Final private Map<Item, Object> cooldowns;

    @Shadow protected abstract void onCooldownEnded(Item pItem);

    @Unique
    public void irons_Spells_OMG$removeAllCooldown() {
        var iterator = cooldowns.keySet().iterator();
        try {
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item == null) continue;
                iterator.remove();
                onCooldownEnded(item);
            }
        } catch (ConcurrentModificationException ignored) {}
    }
}
