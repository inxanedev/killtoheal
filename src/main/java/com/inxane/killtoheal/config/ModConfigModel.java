package com.inxane.killtoheal.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;

@Modmenu(modId = "killtoheal")
@Config(name = "killtoheal", wrapperName = "ModConfig")
@SuppressWarnings("unused")
public class ModConfigModel {
    public boolean enableMod = true;
    public boolean simpleMode = false;
    public boolean enableHardDamage = true;
    public float hardDamageTicks = 5 * 20;
    public float levelOneDamageHealing = 0.5f;
    public float levelTwoDamageHealing = 1.0f;
    public float levelThreeDamageHealing = 1.5f;
    public float levelOneKillHealing = 5;
    public float levelTwoKillHealing = 8;
    public float levelThreeKillHealing = 10;
    public int levelOneAbsorptionDuration = 0;
    public int levelTwoAbsorptionDuration = 5;
    public int levelThreeAbsorptionDuration = 10;
    public int levelOneAbsorptionAmplifier = 0;
    public int levelTwoAbsorptionAmplifier = 1;
    public int levelThreeAbsorptionAmplifier = 3;

}
