package com.github.netherald.partybungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class PartyBungee extends Plugin {

    public static ProxyServer proxy;

    @Override
    public void onEnable() {
        getLogger().info("PartyBungee is Enable!");
        getProxy().registerChannel( "PartyBungee:PartyMembers" );
        getProxy().getPluginManager().registerCommand(this, new Commands());
        getProxy().getPluginManager().registerListener(this, new ServerMoveEvent());
        proxy=super.getProxy();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        getLogger().info("PartyBungee is Disable");
        // Plugin shutdown logic
    }
}
