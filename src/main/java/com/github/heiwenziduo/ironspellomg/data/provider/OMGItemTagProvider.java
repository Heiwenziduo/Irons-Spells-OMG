package com.github.heiwenziduo.ironspellomg.data.provider;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static com.github.heiwenziduo.ironspellomg.initializer.OMGItems.*;

public class OMGItemTagProvider extends ItemTagsProvider {
    public static final TagKey<Item> CURIO_OMG_ITEMS_1 = ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", "omg_item_1"));
    public static final TagKey<Item> CURIO_PASSIVE_ABILITY = ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", "passive_ability"));

    public OMGItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider,
                              CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, IronsSpellOMG.ModId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(CURIO_OMG_ITEMS_1).add(BUTTERFLY.get(), REFRESHER.get(), HEART_OF_DRAGON.get(), BOOT_OF_TRAVEL.get(), OCTARINE_CORE.get());

        tag(CURIO_PASSIVE_ABILITY).add(TIMELOCK_CURIO.get());
    }
}
