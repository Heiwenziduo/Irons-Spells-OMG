package com.github.heiwenziduo.ironspellomg.data.provider;

import com.github.heiwenziduo.ironspellomg.IronsSpellOMG;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.github.heiwenziduo.ironspellomg.initializer.registry.OMGDamageType.TIMELOCK;
import static net.minecraft.tags.DamageTypeTags.*;


/// {@link DamageTypeTagsProvider}
public class OMGDamageTypeTagProvider extends DamageTypeTagsProvider {

    public OMGDamageTypeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookup, IronsSpellOMG.ModId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BYPASSES_SHIELD).add(TIMELOCK);
        tag(BYPASSES_EFFECTS);
        tag(BYPASSES_ARMOR);
        tag(AVOIDS_GUARDIAN_THORNS).add(TIMELOCK);
    }
}
