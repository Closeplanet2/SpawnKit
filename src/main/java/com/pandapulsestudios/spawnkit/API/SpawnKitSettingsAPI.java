package com.pandapulsestudios.spawnkit.API;

import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveAbleInventoryConfig;
import com.pandapulsestudios.spawnkit.Configs.SpawnKitSettings;
import com.pandapulsestudios.spawnkit.SpawnKit;
import org.bukkit.entity.Player;

public class SpawnKitSettingsAPI {
    public static void EnableSystem(boolean state, boolean debugLoad){
        SpawnKit.SpawnKitSettings.systemEnabled = state;
        ConfigAPI.Save(SpawnKit.SpawnKitSettings, debugLoad);
    }

    public static boolean IsSystemEnabled(){
        return SpawnKit.SpawnKitSettings.systemEnabled;
    }

    public static boolean CreateSpawnKit(Player player, boolean setCurrent, String spawnKitName){
        if(SpawnKit.SpawnKitSettings.spawnKits.hashMap.containsKey(spawnKitName) || !IsSystemEnabled()) return false;
        var inventoryName = SpawnKit.SpawnKitSettings.inventoryName;
        var inventorySize = SpawnKit.SpawnKitSettings.inventorySize;
        var spawnKit = new SaveAbleInventoryConfig(inventoryName, inventorySize);
        if(player != null) spawnKit.OpenInventory(player);
        SpawnKit.SpawnKitSettings.spawnKits.hashMap.put(spawnKitName, spawnKit);
        if(setCurrent) SpawnKit.SpawnKitSettings.currentSpawnKit = spawnKitName;
        ConfigAPI.Save(SpawnKit.SpawnKitSettings, false);
        return true;
    }

    public static boolean OpenSpawnKit(Player player, String spawnKitName){
        var spawnKit = SpawnKit.SpawnKitSettings.spawnKits.hashMap.getOrDefault(spawnKitName, null);
        if(spawnKit == null || !IsSystemEnabled() || player == null) return false;
        spawnKit.OpenInventory(player);
        return true;
    }

    public static boolean DeleteSpawnKit(String spawnKitName){
        var spawnKit = SpawnKit.SpawnKitSettings.spawnKits.hashMap.getOrDefault(spawnKitName, null);
        if(spawnKit == null || !spawnKit.ReturnViewers().isEmpty() || !IsSystemEnabled()) return false;
        SpawnKit.SpawnKitSettings.spawnKits.hashMap.remove(spawnKitName);
        ConfigAPI.Save(SpawnKit.SpawnKitSettings, false);
        return true;
    }

    public static boolean CurrentSpawnKit(String spawnKitName){
        var spawnKit = SpawnKit.SpawnKitSettings.spawnKits.hashMap.getOrDefault(spawnKitName, null);
        if(spawnKit == null || !IsSystemEnabled()) return false;
        SpawnKit.SpawnKitSettings.currentSpawnKit = spawnKitName;
        ConfigAPI.Save(SpawnKit.SpawnKitSettings, false);
        return true;
    }
}
