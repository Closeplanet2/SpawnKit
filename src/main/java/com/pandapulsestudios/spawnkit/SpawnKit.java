package com.pandapulsestudios.spawnkit;

import com.pandapulsestudios.pulsecommands.PulseCommands;
import com.pandapulsestudios.pulsecore.Java.ClassAPI;
import com.pandapulsestudios.spawnkit.API.SpawnKitConfigAPI;
import com.pandapulsestudios.spawnkit.Configs.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpawnKit extends JavaPlugin {

    public static String BuildString(String[] reason, char middle){
        var builder = new StringBuilder();
        for(var x : reason) builder.append(String.format("%s%c", x, middle));
        var message = builder.toString();
        return message.substring(0, message.length() - 1);
    }

    public static SpawnKit SpawnKit;
    public static SpawnKitMenu SpawnKitMenu;
    public static SpawnKitPermissions SpawnKitPermissions;
    public static SpawnKitSettings SpawnKitSettings;
    public static SpawnKitMessages SpawnKitMessages;

    @Override
    public void onEnable() {
        SpawnKit = this;
        ClassAPI.RegisterClasses(this);
        PulseCommands.RegisterRaw(this);
        SpawnKitConfigAPI.ResetConfigs(false);
    }
}
