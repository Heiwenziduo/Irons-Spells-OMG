package com.github.heiwenziduo.ironspellomg.initializer;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.curio.BootOfTravel;
import com.github.heiwenziduo.ironspellomg.curio.HeartOfDragon;
import com.github.heiwenziduo.ironspellomg.curio.OMGHookedCurio;
import com.github.heiwenziduo.ironspellomg.curio.Refresher;
import com.github.heiwenziduo.ironspellomg.curio.passive.TimelockCurio;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

import static com.github.heiwenziduo.fvlib.library.registry.FvAttribute.*;
import static io.redspace.ironsspellbooks.api.registry.AttributeRegistry.*;
import static net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

public class OMGItems {
    /// since Curio provide uuid for each slot, here only for syntax
    private static final UUID StaticUUID = UUID.fromString("948618ff-66f3-4e0c-86d9-2accae69aefe");

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IronsSpellOMG.ModId);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // ========================================== passive abilities ===========================================================
    ///
    public static final RegistryObject<OMGHookedCurio> TIMELOCK_CURIO = ITEMS.register("timelock", TimelockCurio::new);

    // ========================================== special relics ==============================================================
    /// 刷新球
    public static final RegistryObject<OMGHookedCurio> REFRESHER = ITEMS.register("refresher", () -> new Refresher()
            .addAttribute(PASSIVE_REGEN, new AttributeModifier(StaticUUID, "refresher", 1, ADDITION))
            .addAttribute(MANA_REGEN.get(), new AttributeModifier(StaticUUID, "refresher", 5, ADDITION)));
    /// 远行鞋
    public static final RegistryObject<OMGHookedCurio> BOOT_OF_TRAVEL = ITEMS.register("boot_of_travel", () -> new BootOfTravel()
            .addAttribute(MOVEMENT_SPEED, new AttributeModifier(StaticUUID, "boot_of_travel", 0.2, MULTIPLY_BASE)));
    /// 魔龙之心
    public static final RegistryObject<OMGHookedCurio> HEART_OF_DRAGON = ITEMS.register("heart_of_dragon", () -> new HeartOfDragon()
            .addAttribute(MAX_HEALTH, new AttributeModifier(StaticUUID, "heart_of_dragon", 1.5, MULTIPLY_TOTAL)));

    // ========================================== Common items ================================================================
    /// 蝴蝶
    public static final RegistryObject<OMGHookedCurio> BUTTERFLY = ITEMS.register("butterfly", () -> new OMGHookedCurio()
            .addAttribute(EVASION, new AttributeModifier(StaticUUID, "butterfly", 0.3, MULTIPLY_BASE))
            .addAttribute(ATTACK_SPEED, new AttributeModifier(StaticUUID, "butterfly", 0.3, MULTIPLY_BASE))
            .addAttribute(ATTACK_DAMAGE, new AttributeModifier(StaticUUID, "butterfly", 0.3, MULTIPLY_BASE))
            .addAttribute(ARMOR, new AttributeModifier(StaticUUID, "butterfly", 0.1, MULTIPLY_BASE)));
    /// 玲珑心
    public static final RegistryObject<OMGHookedCurio> OCTARINE_CORE = ITEMS.register("octarine_core", () -> new OMGHookedCurio()
            .addAttribute(MAX_HEALTH, new AttributeModifier(StaticUUID, "octarine_core", 10, ADDITION))
            .addAttribute(MAX_MANA.get(), new AttributeModifier(StaticUUID, "octarine_core", 100, ADDITION))
            .addAttribute(MANA_REGEN.get(), new AttributeModifier(StaticUUID, "octarine_core", 2, ADDITION))
            .addAttribute(COOLDOWN_REDUCTION.get(), new AttributeModifier(StaticUUID, "octarine_core", 25, ADDITION))
            .addAttribute(ITEM_COOLDOWN_REDUCTION, new AttributeModifier(StaticUUID, "octarine_core", 0.25, MULTIPLY_BASE)));
}
