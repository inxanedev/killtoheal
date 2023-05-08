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

    public static void doBloodshed(LivingEntity user, Entity target, int level, boolean killed) {
        if (target instanceof LivingEntity entity && user instanceof PlayerEntity player && entity != player && !player.isBlocking()) {
            if (player.getWorld().getTime() - KillToHeal.hardDamage.getOrDefault(player, -(long)KillToHeal.CONFIG.hardDamageTicks() - 1L) < KillToHeal.CONFIG.hardDamageTicks() && KillToHeal.CONFIG.enableHardDamage())
                return;

            if (entity.isDead() || killed) {
                if (level == 1) {
                    player.heal(KillToHeal.CONFIG.levelOneKillHealing() * 2);
                    if (KillToHeal.CONFIG.levelOneAbsorptionDuration() != 0) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, KillToHeal.CONFIG.levelOneAbsorptionDuration() * 20, KillToHeal.CONFIG.levelOneAbsorptionAmplifier()));
                    }
                } else if (level == 2) {
                    player.heal(KillToHeal.CONFIG.levelTwoKillHealing() * 2);
                    if (KillToHeal.CONFIG.levelTwoAbsorptionDuration() != 0) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, KillToHeal.CONFIG.levelTwoAbsorptionDuration() * 20, KillToHeal.CONFIG.levelTwoAbsorptionAmplifier()));
                    }
                } else if (level == 3) {
                    player.heal(KillToHeal.CONFIG.levelThreeKillHealing() * 2);
                    if (KillToHeal.CONFIG.levelThreeAbsorptionDuration() != 0) {
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, KillToHeal.CONFIG.levelThreeAbsorptionDuration() * 20, KillToHeal.CONFIG.levelThreeAbsorptionAmplifier()));
                    }
                }
            } else {
                if (level == 1) player.heal(KillToHeal.CONFIG.levelOneDamageHealing());
                if (level == 2) player.heal(KillToHeal.CONFIG.levelTwoDamageHealing());
                if (level == 3) player.heal(KillToHeal.CONFIG.levelThreeDamageHealing());
            }
        }
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (KillToHeal.CONFIG.enableMod() && !KillToHeal.CONFIG.simpleMode()) doBloodshed(user, target, level, false);
    }
}
