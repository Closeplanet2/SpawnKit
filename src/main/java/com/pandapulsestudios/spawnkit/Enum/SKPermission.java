package com.pandapulsestudios.spawnkit.Enum;

public enum SKPermission {
    RELOAD_CONFIGS("SpawnKit.RELOAD_CONFIGS", "#fa3448You do not have the permissions to use this command!"),
    RESET_CONFIGS("SpawnKit.RESET_CONFIGS", "#fa3448You do not have the permissions to use this command!"),
    ENABLE_SYSTEM("SpawnKit.ENABLE_SYSTEM", "#fa3448You do not have the permissions to use this command!"),
    COMMAND_HELP_MENU("SpawnKit.COMMAND_HELP_MENU", "#fa3448You do not have the permissions to view this menu!"),
    CREATE_SPAWN_KIT("SpawnKit.CREATE_SPAWN_KIT", "#fa3448You do not have the permissions to create a spawn kit!"),
    Delete_SPAWN_KIT("SpawnKit.Delete_SPAWN_KIT", "#fa3448You do not have the permissions to delete a spawn kit!"),
    OPEN_SPAWN_KIT("SpawnKit.OPEN_SPAWN_KIT", "#fa3448You do not have the permissions to open a spawn kit!"),
    CURRENT_SPAWN_KIT("SpawnKit.CURRENT_SPAWN_KIT", "#fa3448You do not have the permissions to set the current spawn kit!"),
    GET_SPAWN_KIT("SpawnKit.GET_SPAWN_KIT", "#fa3448You do not have the permissions to get a spawn kit!");

    public final String permission;
    public final String error;
    SKPermission(String permission, String error){
        this.permission = permission;
        this.error = error;
    }
}
