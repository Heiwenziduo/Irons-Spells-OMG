package com.github.heiwenziduo.ironspellomg.curio;

import com.github.heiwenziduo.ironspellomg.api.mixin.ItemCooldownsMixinAPI;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Refresher extends AbstractAncientGear {

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!pLevel.isClientSide) {
            refresh(pPlayer);
        }

//        if (!pPlayer.getAbilities().instabuild) {
//            itemstack.shrink(1);
//        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    /// 清空全身装备和饰品和铁魔咒cd
    public static void refresh(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            // iron
            MagicData magicData = MagicData.getPlayerMagicData(serverPlayer);
            magicData.getPlayerCooldowns().clearCooldowns();
            magicData.getPlayerCooldowns().syncToPlayer(serverPlayer);
            // item
            ItemCooldowns cooldowns = serverPlayer.getCooldowns();
            ((ItemCooldownsMixinAPI) cooldowns).irons_Spells_OMG$removeAllCooldown();
        } else {

        }
    }
}
