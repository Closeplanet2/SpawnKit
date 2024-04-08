package com.pandapulsestudios.spawnkit.Configs;

import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulseconfig.Enums.SaveableType;
import com.pandapulsestudios.pulseconfig.Interfaces.Config.PulseConfig;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableHashmap;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableInventory;
import com.pandapulsestudios.spawnkit.Enum.SKMessage;
import com.pandapulsestudios.spawnkit.Enum.SKPermission;
import com.pandapulsestudios.spawnkit.SpawnKit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpawnKitSettings implements PulseConfig, Listener {
    @Override
    public String documentID() { return "SpawnKitSettings"; }

    @Override
    public JavaPlugin mainClass() {
        return SpawnKit.SpawnKit;
    }

    @Override
    public boolean useSubFolder() {
        return false;
    }

    public boolean systemEnabled = true;
    public boolean clearPlayerInventory = true;
    public String currentSpawnKit = "";
    public String inventoryName = "Spawn Kit";
    public int inventorySize = 54;
    public SaveableHashmap<String, SaveableInventory> spawnKits = new SaveableHashmap<>(SaveableType.CONFIG, String.class, SaveableInventory.class);

    public SpawnKitSettings(boolean debugLoad){
        ConfigAPI.Load(this, debugLoad);
    }

    public List<String> ReturnAllSpawnKitNames(String refineName, boolean caseSens){
        var data = new ArrayList<String>();
        for(var key : spawnKits.hashMap.keySet()){
            if(caseSens && key.contains(refineName)) data.add(key);
            else if(!caseSens && key.toLowerCase().contains(refineName.toLowerCase())) data.add(key);
        }
        return data;
    }

    @EventHandler
    public void InventoryClose(InventoryCloseEvent event){
        ConfigAPI.Save(this, false);
    }

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent event){
        var spawnKit = spawnKits.hashMap.getOrDefault(currentSpawnKit, null);
        if(spawnKit == null || !systemEnabled) return;
        var player = event.getPlayer();
        if(!SpawnKit.SpawnKitPermissions.DoesPlayerHavePermission(SKPermission.GET_SPAWN_KIT, event.getPlayer(), true)) return;
        if(clearPlayerInventory) player.getInventory().clear();
        for(var item : spawnKit.GetLiveContents()) player.getInventory().addItem(item);
        SpawnKit.SpawnKitMessages.SendMessageToPlayer(SKMessage.GotSpawnKit, player);
    }
}

