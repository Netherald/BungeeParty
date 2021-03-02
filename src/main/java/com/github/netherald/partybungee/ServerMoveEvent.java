package com.github.netherald.partybungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerMoveEvent implements Listener {
    @EventHandler
    public void onPlayerConnected(ServerConnectEvent e) {
        if (PartyData.partyData.get(e.getPlayer()) != null) {
            for (String serverName : PartyData.arrayList)
                if (e.getTarget().getName().equals(serverName)) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(new TextComponent(ChatColor.RED+"파티를 가지고 그서버로 갈수 없습니다"));
                }
            for (ProxiedPlayer player : PartyData.partyData.get(e.getPlayer())) {
                player.connect(e.getTarget());
            }
        }
    }
}
