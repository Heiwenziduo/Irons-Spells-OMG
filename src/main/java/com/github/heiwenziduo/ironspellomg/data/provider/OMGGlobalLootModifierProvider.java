package com.github.heiwenziduo.ironspellomg.data.provider;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.initializer.OMGItems;
import com.github.heiwenziduo.ironspellomg.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.registries.RegistryObject;

import static com.github.heiwenziduo.ironspellomg.initializer.OMGItems.*;

public class OMGGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public OMGGlobalLootModifierProvider(PackOutput output) {
        super(output, IronsSpellOMG.ModId);
    }

    @Override
    protected void start() {
        addEnderCityLoot("timelock", TIMELOCK_CURIO, 0.05f);

        addEnderCityLoot("boot_of_travel", BOOT_OF_TRAVEL, 0.05f);
        addEnderCityLoot("heart_of_dragon", HEART_OF_DRAGON, 0.05f);
        addEnderCityLoot("butterfly", BUTTERFLY, 0.05f);
        addEnderCityLoot("octarine_core", OCTARINE_CORE, 0.05f);
    }

    private void addEnderCityLoot(String name, RegistryObject<? extends Item> item, Float chance) {
        add(name + "_from_end_city_treasure", new AddItemModifier(new LootItemCondition[] {
                LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(chance).build()
        }, item.get()));
    }
}
