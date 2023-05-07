package com.inxane.killtoheal;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.player.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class KillToHeal implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("killtoheal");

    public static HashMap<PlayerEntity, Long> hardDamage = new HashMap<>();

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized KillToHeal");
    }
}