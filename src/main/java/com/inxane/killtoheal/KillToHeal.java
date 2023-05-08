package com.inxane.killtoheal;

import com.inxane.killtoheal.config.ModConfig;
import com.inxane.killtoheal.enchantments.BloodshedEnchantment;
import net.fabricmc.api.ModInitializer;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class KillToHeal implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("killtoheal");
    public static HashMap<PlayerEntity, Long> hardDamage = new HashMap<>();

    public static Enchantment BLOODSHED = new BloodshedEnchantment();
    public static final ModConfig CONFIG = ModConfig.createAndLoad();

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized KillToHeal");
        Registry.register(Registries.ENCHANTMENT, new Identifier("killtoheal", "bloodshed"), BLOODSHED);
    }
}