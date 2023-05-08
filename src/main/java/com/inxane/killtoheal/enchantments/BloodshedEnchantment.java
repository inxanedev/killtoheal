package com.inxane.killtoheal.enchantments;

import com.inxane.killtoheal.KillToHeal;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class BloodshedEnchantment extends Enchantment {
    public BloodshedEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity entity && user instanceof PlayerEntity player && entity != player && !player.isBlocking()) {
            if (player.getWorld().getTime() - KillToHeal.hardDamage.getOrDefault(player, -101L) < 100)
                return;

            if (entity.isDead()) {
                if (level == 1) {
                    // i have no idea why i have to divide this by two
                    // onTargetDamaged seems to be called twice for no reason
                    // and it's not a server/client issue
                    player.heal((5 * 2) / 2.0f);
                } else if (level == 2) {
                    player.heal((8 * 2) / 2.0f);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 5 * 20, 1));
                } else if (level == 3) {
                    player.heal((10 * 2) / 2.0f);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 10 * 20, 3));
                }
            } else {
                player.heal(level / 2.0f);
            }
        }
    }
}
