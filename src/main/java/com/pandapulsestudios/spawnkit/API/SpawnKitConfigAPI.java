package com.pandapulsestudios.spawnkit.API;

import com.pandapulsestudios.pulsecore.Java.ClassAPI;
import com.pandapulsestudios.spawnkit.Configs.*;
import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.spawnkit.SpawnKit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class SpawnKitConfigAPI {
    public static void ReloadConfigs(boolean debugLoad){
        ConfigAPI.Load(SpawnKit.SpawnKitMenu, debugLoad);
        ConfigAPI.Load(SpawnKit.SpawnKitPermissions, debugLoad);
        ConfigAPI.Load(SpawnKit.SpawnKitSettings, debugLoad);
        ConfigAPI.Load(SpawnKit.SpawnKitMessages, debugLoad);
    }

    public static void ResetConfigs(boolean debugLoad){
        if(SpawnKit.SpawnKitMenu != null) ConfigAPI.Delete(SpawnKit.SpawnKitMenu);
        if(SpawnKit.SpawnKitPermissions != null) ConfigAPI.Delete(SpawnKit.SpawnKitPermissions);
        if(SpawnKit.SpawnKitSettings != null) ConfigAPI.Delete(SpawnKit.SpawnKitSettings);
        if(SpawnKit.SpawnKitMessages != null) ConfigAPI.Delete(SpawnKit.SpawnKitMessages);
        SpawnKit.SpawnKitMenu = new SpawnKitMenu();
        SpawnKit.SpawnKitPermissions = new SpawnKitPermissions();
        SpawnKit.SpawnKitSettings = new SpawnKitSettings();
        SpawnKit.SpawnKitMessages = new SpawnKitMessages();
        ReloadConfigs(debugLoad);
    }
}
