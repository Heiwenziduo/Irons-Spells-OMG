package com.github.heiwenziduo.ironspellomg.mixin;

import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

//@Mixin(targets = "net.minecraft.world.item.ItemCooldowns$CooldownInstance")
@Mixin(ItemCooldowns.class)
public class TestMixin {
    //todo how public a inner class?

//    public void test() {
//        new ItemCooldowns.CooldownInstance();
//    }
}
