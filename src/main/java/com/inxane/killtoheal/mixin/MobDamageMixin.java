package com.inxane.killtoheal.mixin;

import com.inxane.killtoheal.KillToHeal;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DamageTracker.class)
public class MobDamageMixin {
	@Inject(at = @At("TAIL"), method = "onDamage")
	private void onDamage(DamageSource damageSource, float originalHealth, float damage, CallbackInfo ci) {
		DamageTracker tracker = (DamageTracker)(Object)this;

		PlayerEntity player = tracker.getEntity().getWorld().getClosestPlayer(tracker.getEntity(), 3);
		if (player != null && player != tracker.getEntity() && !player.isBlocking()) {
			if (player.getWorld().getTime() - KillToHeal.hardDamage.getOrDefault(player, -101L) < 100) return;
			if (tracker.getEntity().isDead()) {
				player.heal(16);
			} else {
				player.heal(1);
			}
		}
	}
}