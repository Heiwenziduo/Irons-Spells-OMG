package com.github.heiwenziduo.ironspellomg.initializer;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.curio.OMGHookedCurio;
import com.github.heiwenziduo.ironspellomg.curio.Refresher;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

import static com.github.heiwenziduo.fvlib.library.api.FvAttribute.EVASION;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class OMGItems {
    private static final UUID StaticUUID = UUID.fromString("948618ff-66f3-4e0c-86d9-2accae69aefe");

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronsSpellOMG.ModId);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<OMGHookedCurio> BUTTERFLY = ITEMS.register("butterfly", () -> new OMGHookedCurio()
            .addAttribute(EVASION, new AttributeModifier(StaticUUID, "butterfly", 0.3, ADDITION))
            .addAttribute(ATTACK_SPEED, new AttributeModifier(StaticUUID, "butterfly", 0.3, ADDITION))
            .addAttribute(ATTACK_DAMAGE, new AttributeModifier(StaticUUID, "butterfly", 0.3, MULTIPLY_BASE))
            .addAttribute(ARMOR, new AttributeModifier(StaticUUID, "butterfly", 0.10, MULTIPLY_BASE)));
    public static final RegistryObject<OMGHookedCurio> REFRESHER = ITEMS.register("refresher", Refresher::new);

}
