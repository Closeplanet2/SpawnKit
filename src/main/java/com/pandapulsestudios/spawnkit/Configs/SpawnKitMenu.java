package com.pandapulsestudios.spawnkit.Configs;

import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulseconfig.Enums.SaveableType;
import com.pandapulsestudios.pulseconfig.Interfaces.Config.PulseConfig;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableHashmap;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.spawnkit.Enum.SKMenu;
import com.pandapulsestudios.spawnkit.SpawnKit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnKitMenu implements PulseConfig {
    @Override
    public String documentID() { return "SpawnKitMenu"; }

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
        for(var x : SKMenu.values()){
            if(!menus.hashMap.containsKey(x)) menus.hashMap.put(x, x.menu);
        }
    }

    @Override
    public void AfterLoad() {
        for(var x : SKMenu.values()){
            if(!menus.hashMap.containsKey(x)) menus.hashMap.put(x, x.menu);
        }
        ConfigAPI.Save(this, false);
    }

    @Override
    public void BeforeSave() {
        for(var x : SKMenu.values()){
            if(!menus.hashMap.containsKey(x)) menus.hashMap.put(x, x.menu);
        }
    }

    public SaveableHashmap<SKMenu, String[]> menus = new SaveableHashmap<>(SaveableType.CONFIG, SKMenu.class, String[].class);

    public void DisplayMenuToPlayer(SKMenu skMenu, Player player){
        if(player == null || skMenu == null) return;
        if(!SpawnKit.SpawnKitPermissions.DoesPlayerHavePermission(skMenu.skPermission, player, true)) return;
        for(var line : menus.hashMap.getOrDefault(skMenu, skMenu.menu)){
            ChatAPI.chatBuilder().messageType(MessageType.Player).playerToo(player).SendMessage(line);
        }
    }
}
