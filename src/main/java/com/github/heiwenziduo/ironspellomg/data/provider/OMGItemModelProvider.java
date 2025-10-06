package com.github.heiwenziduo.ironspellomg.data.provider;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import com.github.heiwenziduo.ironspellomg.initializer.OMGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class OMGItemModelProvider extends ItemModelProvider {
    public OMGItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IronsSpellOMG.ModId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //======================================
        simpleItem(OMGItems.TIMELOCK_CURIO);
        //======================================
        simpleItem(OMGItems.BUTTERFLY);
        simpleItem(OMGItems.REFRESHER);
        simpleItem(OMGItems.BOOT_OF_TRAVEL);
        simpleItem(OMGItems.HEART_OF_DRAGON);
        simpleItem(OMGItems.OCTARINE_CORE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<? extends Item> item) {
        assert item.getId() != null;
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                IronsSpellOMG.resource("item/" + item.getId().getPath()));
    }
}
