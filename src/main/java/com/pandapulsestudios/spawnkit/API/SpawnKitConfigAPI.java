package com.pandapulsestudios.spawnkit.API;

import com.pandapulsestudios.pulsecore.Java.ClassAPI;
import com.pandapulsestudios.spawnkit.Configs.*;
import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.spawnkit.SpawnKit;

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
        SpawnKit.SpawnKitMenu = new SpawnKitMenu(debugLoad);
        SpawnKit.SpawnKitPermissions = new SpawnKitPermissions(debugLoad);
        SpawnKit.SpawnKitSettings = new SpawnKitSettings(debugLoad);
        SpawnKit.SpawnKitMessages = new SpawnKitMessages(debugLoad);
        ClassAPI.RegisterListener(SpawnKit.SpawnKit, SpawnKit.SpawnKitSettings);
    }
}
