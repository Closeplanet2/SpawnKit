package com.pandapulsestudios.spawnkit.Commands;

import com.pandapulsestudios.pulsecommands.Enums.TabType;
import com.pandapulsestudios.pulsecommands.Interface.PCMethod;
import com.pandapulsestudios.pulsecommands.Interface.PCMethodData;
import com.pandapulsestudios.pulsecommands.Interface.PCSignature;
import com.pandapulsestudios.pulsecommands.Interface.PCTab;
import com.pandapulsestudios.pulsecommands.PlayerCommand;
import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import com.pandapulsestudios.spawnkit.API.SpawnKitConfigAPI;
import com.pandapulsestudios.spawnkit.API.SpawnKitSettingsAPI;
import com.pandapulsestudios.spawnkit.Enum.SKMenu;
import com.pandapulsestudios.spawnkit.Enum.SKMessage;
import com.pandapulsestudios.spawnkit.Enum.SKPermission;
import com.pandapulsestudios.spawnkit.SpawnKit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

@PulseAutoRegister
public class SpawnKitCommands extends PlayerCommand {

    public SpawnKitCommands() {
        super("spawnkit", false);
    }

    @Override
    public void NoMethodFound(CommandSender commandSender, String s, String[] strings) {
        SpawnKit.SpawnKitMenu.DisplayMenuToPlayer(SKMenu.HELP_MENU, (Player) commandSender);
    }

    @PCMethod
    @PCSignature({"enable"})
    @PCTab(pos = 1, type = TabType.Pure_Data, data = "[ENABLE SYSTEM]")
    @PCTab(pos = 2, type = TabType.Pure_Data, data = "[DEBUG STATE]")
    public void EnableSystem(CraftPlayer admin, boolean state, boolean debug){
        if(!SpawnKit.SpawnKitPermissions.DoesPlayerHavePermission(SKPermission.ENABLE_SYSTEM, admin, true)) return;
        SpawnKitSettingsAPI.EnableSystem(state, debug);
        var skMessage = SpawnKit.SpawnKitSettings.systemEnabled ? SKMessage.PlayerEnabledSystem : SKMessage.PlayerDisableSystem;
        SpawnKit.SpawnKitMessages.SendMessageToPlayer(skMessage, admin);
    }

    @PCMethod
    @PCSignature({"configs", "reset"})
    @PCTab(pos = 1, type = TabType.Pure_Data, data = "[DEBUG STATE]")
    public void ResetConfigs(CraftPlayer admin, boolean state){
        if(!SpawnKit.SpawnKitPermissions.DoesPlayerHavePermission(SKPermission.RESET_CONFIGS, admin, true)) return;
        SpawnKitConfigAPI.ResetConfigs(state);
        SpawnKit.SpawnKitMessages.SendMessageToPlayer(SKMessage.PlayerResetConfig, admin);
    }

    @PCMethod
    @PCSignature({"configs", "reload"})
    @PCTab(pos = 1, type = TabType.Pure_Data, data = "[DEBUG STATE]")
    public void ReloadConfigs(CraftPlayer admin, boolean state){
        if(!SpawnKit.SpawnKitPermissions.DoesPlayerHavePermission(SKPermission.RELOAD_CONFIGS, admin, true)) return;
        SpawnKitConfigAPI.ReloadConfigs(state);
        SpawnKit.SpawnKitMessages.SendMessageToPlayer(SKMessage.PlayerReloadedConfig, admin);
    }

    @PCMethod
    @PCSignature({"create"})
    @PCTab(pos = 1, type = TabType.Pure_Data, data = "[SET CURRENT]")
    @PCTab(pos = 2, type = TabType.Information_From_Function, data = "CurrentSpawnKitNames")
    public void CreateSpawnKit(CraftPlayer adin, boolean setCurrent, String... spawnKitName){
        if(!SpawnKit.SpawnKitPermissions.DoesPlayerHavePermission(SKPermission.CREATE_SPAWN_KIT, adin, true)) return;
        var creationState = SpawnKitSettingsAPI.CreateSpawnKit(adin,setCurrent,  SpawnKit.BuildString(spawnKitName, ' '));
        var storedMessage = creationState ? SKMessage.CreatedSpawnKit : SKMessage.CannotCreateSpawnKit;
        SpawnKit.SpawnKitMessages.SendMessageToPlayer(storedMessage, adin);
    }

    @PCMethod
    @PCSignature({"open"})
    @PCTab(pos = 1, type = TabType.Information_From_Function, data = "CurrentSpawnKitNames")
    public void OpenSpawnKit(CraftPlayer adin, String... spawnKitName){
        if(!SpawnKit.SpawnKitPermissions.DoesPlayerHavePermission(SKPermission.OPEN_SPAWN_KIT, adin, true)) return;
        var openState = SpawnKitSettingsAPI.OpenSpawnKit(adin.getPlayer(), SpawnKit.BuildString(spawnKitName, ' '));
        if(!openState) SpawnKit.SpawnKitMessages.SendMessageToPlayer(SKMessage.CannotFindSpawnKitWithName, adin);
    }

    @PCMethod
    @PCSignature({"delete"})
    @PCTab(pos = 1, type = TabType.Information_From_Function, data = "CurrentSpawnKitNames")
    public void DeleteSpawnKit(CraftPlayer adin, String... spawnKitName){
        if(!SpawnKit.SpawnKitPermissions.DoesPlayerHavePermission(SKPermission.Delete_SPAWN_KIT, adin, true)) return;
        var deleteState = SpawnKitSettingsAPI.DeleteSpawnKit(SpawnKit.BuildString(spawnKitName, ' '));
        var storedMessage = deleteState ? SKMessage.DeletedSpawnKit : SKMessage.CannotDeleteSpawnKit;
        SpawnKit.SpawnKitMessages.SendMessageToPlayer(storedMessage, adin);
    }

    @PCMethod
    @PCSignature({"current"})
    @PCTab(pos = 1, type = TabType.Information_From_Function, data = "CurrentSpawnKitNames")
    public void CurrentSpawnKit(CraftPlayer adin, String... spawnKitName){
        if(!SpawnKit.SpawnKitPermissions.DoesPlayerHavePermission(SKPermission.CURRENT_SPAWN_KIT, adin, true)) return;
        var currentState = SpawnKitSettingsAPI.CurrentSpawnKit(SpawnKit.BuildString(spawnKitName, ' '));
        var storedMessage = currentState ? SKMessage.SetCurrentSpawnKit : SKMessage.CannotFindSpawnKitWithName;
        SpawnKit.SpawnKitMessages.SendMessageToPlayer(storedMessage, adin);
    }

    @PCMethodData
    public List<String> CurrentSpawnKitNames(String currentInput){
        return SpawnKit.SpawnKitSettings.ReturnAllSpawnKitNames(currentInput, false);
    }
}
