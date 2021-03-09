package com.github.netherald.partybungee;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Collection;

public class ServerMoveEvent implements Listener {
    @EventHandler
    public void onPlayerConnect(ServerConnectEvent e) {
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
    @EventHandler
    public void onPlayerConnected(ServerConnectedEvent e) {
        Collection<ProxiedPlayer> networkPlayers = PartyBungee.proxy.getPlayers();
        // perform a check to see if globally are no players
        if (networkPlayers == null || networkPlayers.isEmpty() ) { return; }
        if (PartyData.partyData.get(e.getPlayer()) == null) { return; }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        for (ProxiedPlayer player : PartyData.partyData.get(e.getPlayer())) {
            out.writeUTF(player.getName()); // this data could be whatever you want
        }
        e.getPlayer().getServer().getInfo().sendData("PartyBungee:PartyMembers", out.toByteArray());
    }
}
