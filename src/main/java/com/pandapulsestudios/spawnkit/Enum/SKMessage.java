package com.pandapulsestudios.spawnkit.Enum;

public enum SKMessage {
    PlayerEnabledSystem("#7fff36You have enabled the system!"),
    PlayerDisableSystem("#fa3448You have disabled the system!"),
    PlayerReloadedConfig("#7fff36You have successfully reload the configs!"),
    PlayerResetConfig("#7fff36You have successfully reset the configs!"),
    CannotFindSpawnKitWithName("#fa3448Cannot find spawn kit with given name"),
    CreatedSpawnKit("#7fff36You have created a spawn kit!"),
    CannotCreateSpawnKit("#fa3448YThe Spawn kit with given name exists!"),
    DeletedSpawnKit("#7fff36You have deleted a spawn kit!"),
    CannotDeleteSpawnKit("#fa3448YThe Spawn kit with given cannot be deleted! Someone Could be editing it!"),
    SetCurrentSpawnKit("#7fff36You have set the current spawn kit!"),
    GotSpawnKit("#7fff36You have been given the current spawn kit!");

    public final String message;
    SKMessage(String message){
        this.message = message;
    }
}
