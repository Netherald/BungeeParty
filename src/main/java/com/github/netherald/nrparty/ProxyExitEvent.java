package com.github.netherald.partybungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyExitEvent implements Listener {
    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent e) {
        if (PartyData.partyData.get(e.getPlayer()) != null) {
            for (ProxiedPlayer proxiedPlayer : PartyData.partyData.get(e.getPlayer())) {
                proxiedPlayer.sendMessage(new TextComponent(ChatColor.RED + "파티장이 나가여서 서버가 해체되었습니다"));

            }
            PartyData.partyData.remove(e.getPlayer());
            return;
        }
        ProxiedPlayer player = PartyFunctions.wasEnterPartyAndGetParty(e.getPlayer());
        if (player != null) {
            player.sendMessage(new TextComponent(ChatColor.RED+e.getPlayer().getName()+"님이 서버를 나가여 자동으로 추방되었습니다."));
            PartyData.partyData.get(player).remove(e.getPlayer());
        }
    }
}
