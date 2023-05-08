package com.inxane.killtoheal.mixin;

import com.inxane.killtoheal.KillToHeal;
import com.inxane.killtoheal.enchantments.BloodshedEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.world.tick.TickScheduler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DamageTracker.class)
public class DamageMixin {
	@Inject(at = @At("TAIL"), method = "onDamage")
	private void onDamage(DamageSource damageSource, float originalHealth, float damage, CallbackInfo ci) {
		DamageTracker tracker = (DamageTracker)(Object)this;

		PlayerEntity player = tracker.getEntity().getWorld().getClosestPlayer(tracker.getEntity(), 3);
		if (player != null && tracker.getEntity() != player) {
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