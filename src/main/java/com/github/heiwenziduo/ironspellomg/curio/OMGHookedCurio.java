package com.github.heiwenziduo.ironspellomg.curio;

import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public abstract class OMGHookedCurio extends Item implements ICurioItem {
    public OMGHookedCurio() {
        super(new Item.Properties().stacksTo(1));
    }
}
