package com.pandapulsestudios.spawnkit.Configs;

import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulseconfig.Enums.SaveableType;
import com.pandapulsestudios.pulseconfig.Interfaces.Config.PulseConfig;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableHashmap;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.spawnkit.Enum.SKPermission;
import com.pandapulsestudios.spawnkit.SpawnKit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnKitPermissions implements PulseConfig {
    @Override
    public String documentID() { return "SpawnKitPermissions"; }

    @Override
    public JavaPlugin mainClass() {
        return SpawnKit.SpawnKit;
    }

    @Override
    public boolean useSubFolder() {
        return false;
    }

    @Override
    public void FirstLoad() {
        for(var x : SKPermission.values()){
            if(!permissions.hashMap.containsKey(x)) permissions.hashMap.put(x, x.permission);
        }
    }

    @Override
    public void AfterLoad() {
        for(var x : SKPermission.values()){
            if(!permissions.hashMap.containsKey(x)) permissions.hashMap.put(x, x.permission);
        }
        ConfigAPI.Save(this, false);
    }

    @Override
    public void BeforeSave() {
        for(var x : SKPermission.values()){
            if(!permissions.hashMap.containsKey(x)) permissions.hashMap.put(x, x.permission);
        }
    }

    public SaveableHashmap<SKPermission, String> permissions = new SaveableHashmap<>(SaveableType.CONFIG, SKPermission.class, String.class);

    public SpawnKitPermissions(boolean debugLoad){
        ConfigAPI.Load(this, debugLoad);
    }

    public boolean DoesPlayerHavePermission(SKPermission skPermission, Player player, boolean sendError){
        if(skPermission == null || player == null) return false;
        var storedPermission = permissions.hashMap.getOrDefault(skPermission, skPermission.permission);
        if(!player.hasPermission(storedPermission) && sendError) ChatAPI.chatBuilder().messageType(MessageType.Player).playerToo(player).SendMessage(SKPermission.ENABLE_SYSTEM.error);
        return player.hasPermission(storedPermission);
    }
}
