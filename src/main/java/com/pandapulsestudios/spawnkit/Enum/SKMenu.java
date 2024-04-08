package com.pandapulsestudios.spawnkit.Enum;

public enum SKMenu {
    HELP_MENU(SKPermission.COMMAND_HELP_MENU,"/spawnkit enabled [TRUE/FALSE]", "/spawnkit config reload [TRUE/FALSE]", "/spawnkit config reset [TRUE/FALSE]");

    public final String[] menu;
    public final SKPermission skPermission;
    SKMenu(SKPermission skPermission, String... menu) {
        this.skPermission = skPermission;
        this.menu = menu;
    }
}
