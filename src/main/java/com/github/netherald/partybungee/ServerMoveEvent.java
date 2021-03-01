package com.github.netherald.partybungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerMoveEvent implements Listener {
    @EventHandler
    public void onPlayerConnected(ServerConnectedEvent e) {
        if (PartyData.partyData.get(e.getPlayer()) != null) {
            for (ProxiedPlayer player : PartyData.partyData.get(e.getPlayer())) {
                player.connect(e.getPlayer().getServer().getInfo());
            }
        }
    }
}
