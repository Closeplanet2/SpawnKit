package com.pandapulsestudios.spawnkit.Configs;

import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulseconfig.Enums.SaveableType;
import com.pandapulsestudios.pulseconfig.Interfaces.Config.PulseConfig;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableHashmap;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.spawnkit.Enum.SKMessage;
import com.pandapulsestudios.spawnkit.SpawnKit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnKitMessages implements PulseConfig {
    @Override
    public String documentID() { return "SpawnKitMessages"; }

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
        for(var x : SKMessage.values()){
            if(!messages.hashMap.containsKey(x)) messages.hashMap.put(x, x.message);
        }
    }

    @Override
    public void AfterLoad() {
        for(var x : SKMessage.values()){
            if(!messages.hashMap.containsKey(x)) messages.hashMap.put(x, x.message);
        }
        ConfigAPI.Save(this, false);
    }

    @Override
    public void BeforeSave() {
        for(var x : SKMessage.values()){
            if(!messages.hashMap.containsKey(x)) messages.hashMap.put(x, x.message);
        }
    }

    public SaveableHashmap<SKMessage, String> messages = new SaveableHashmap<>(SaveableType.CONFIG, SKMessage.class, String.class);

    public SpawnKitMessages(boolean debugLoad){
        ConfigAPI.Load(this, debugLoad);
    }

    public void SendMessageToPlayer(SKMessage skMessage, Player player){
        if(player == null || skMessage == null) return;
        var storedMessage = messages.hashMap.getOrDefault(skMessage, skMessage.message);
        ChatAPI.chatBuilder().messageType(MessageType.Player).playerToo(player).SendMessage(storedMessage);
    }
}
