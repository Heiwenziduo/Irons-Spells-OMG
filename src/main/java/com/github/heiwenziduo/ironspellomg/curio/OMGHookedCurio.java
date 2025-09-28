package com.github.heiwenziduo.ironspellomg.curio;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class OMGHookedCurio extends Item implements ICurioItem {
    private final Multimap<Attribute, AttributeModifier> attributeMap = ArrayListMultimap.create();

    public OMGHookedCurio() {
        super(new Item.Properties().stacksTo(1));
    }

    /// 给饰品添加属性, 可链式调用
    public OMGHookedCurio addAttribute(Attribute attr, AttributeModifier modifier) {
        attributeMap.put(attr, modifier);
        return this;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = new ImmutableMultimap.Builder<>();
        for (Attribute attribute : attributeMap.keySet()) {
            var modifiers = attributeMap.get(attribute);
            for (AttributeModifier attributeModifier : modifiers) {
                attributeBuilder.put(attribute, new AttributeModifier(uuid, attributeModifier.getName(), attributeModifier.getAmount(), attributeModifier.getOperation()));
            }
        }
        return attributeBuilder.build();
    }
}
