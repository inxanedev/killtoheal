package com.inxane.killtoheal.mixin;

import com.inxane.killtoheal.KillToHeal;
import com.inxane.killtoheal.enchantments.BloodshedEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DamageTracker.class)
public class DamageMixin {
	@Inject(at = @At("TAIL"), method = "onDamage")
	private void onDamage(DamageSource damageSource, float originalHealth, float damage, CallbackInfo ci) {
		if (!KillToHeal.CONFIG.enableMod()) return;

		DamageTracker tracker = (DamageTracker)(Object)this;

		PlayerEntity player = tracker.getEntity().getWorld().getClosestPlayer(tracker.getEntity(), 3);
		if (player != null && tracker.getEntity() != player) {
			if (KillToHeal.CONFIG.simpleMode()) {
				BloodshedEnchantment.doBloodshed(player, tracker.getEntity(), 1, (originalHealth - damage) <= 0.0f);
				BloodshedEnchantment.doBloodshed(player, tracker.getEntity(), 1, (originalHealth - damage) <= 0.0f);
			} else {
				if (damageSource.getAttacker() instanceof PlayerEntity p1 && p1 == player) return;

				ItemStack heldItem = player.getInventory().getMainHandStack();
				if (heldItem.getItem() instanceof SwordItem) {
					int bloodshedLevel = EnchantmentHelper.getLevel(KillToHeal.BLOODSHED, heldItem);
					if (bloodshedLevel != 0) {
						BloodshedEnchantment.doBloodshed(player, tracker.getEntity(), bloodshedLevel, (originalHealth - damage) <= 0.0f);
					}
				}
			}
		}
	}
}