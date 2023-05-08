package com.inxane.killtoheal.mixin;

import com.inxane.killtoheal.KillToHeal;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class ShieldMixin {
	@Inject(at = @At("TAIL"), method = "takeShieldHit")
	private void onShieldHit(LivingEntity attacker, CallbackInfo ci) {
		if (!KillToHeal.CONFIG.enableHardDamage() || !KillToHeal.CONFIG.enableMod()) return;

		PlayerEntity player = (PlayerEntity)(Object)this;

		KillToHeal.hardDamage.put(player, player.getWorld().getTime());
		player.sendMessage(Text.translatable("killtoheal.actionBarHardDamageText"), true);
	}
}