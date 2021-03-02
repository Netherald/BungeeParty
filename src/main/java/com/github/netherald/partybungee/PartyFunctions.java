package com.github.netherald.partybungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import java.util.ArrayList;

public class PartyFunctions {
    public static void invitePlayer(ProxiedPlayer sender_player, ProxiedPlayer player) {
        if (PartyData.sendData.get(sender_player) != null) {
            if (wasEnterParty(player)) {
                sender_player.sendMessage(new TextComponent(ChatColor.RED + "이미 다른파티에 있습니다!"));
                return;
            }
            if (inviteCheck(sender_player, player)) {
                sender_player.sendMessage(new TextComponent(ChatColor.RED + "이미 초대를 보넸습니다!"));
            }
            if (wasSendParty(sender_player, player)) {
                sender_player.sendMessage(new TextComponent(ChatColor.RED + "이미 초대를 보넸습니다!"));
                return;
            }
            PartyData.sendData.get(sender_player).add(player);
        } else {
            ArrayList<ProxiedPlayer> arrayList = new ArrayList<>();
            arrayList.add(player);
            PartyData.sendData.put(sender_player, arrayList);
        }
        sender_player.sendMessage(new TextComponent(ChatColor.GREEN + player.getName() + "님에게 초대를 보넸습니다!"));
        player.sendMessage(new TextComponent(ChatColor.GREEN + sender_player.getName() + "님이 초대를 보넸습니다!"));
    }

    public static void acceptParty(ProxiedPlayer sender, ProxiedPlayer player) {
        if (wasEnterParty(player)) {
            player.sendMessage(new TextComponent(ChatColor.RED + "이미 어떤파티에 있습니다!"));
            return;
        }
        if (inviteCheck(sender, player)) {
            if (PartyData.partyData.get(sender) == null) {
                ArrayList<ProxiedPlayer> arrayList = new ArrayList<>();
                arrayList.add(player);
                PartyData.partyData.put(sender, arrayList);
            } else {
                PartyData.partyData.get(sender).add(player);
            }
            PartyData.sendData.get(sender).remove(player);
            sender.sendMessage(new TextComponent(ChatColor.GREEN+player.getName()+"님이 파티신청을 받았습니다!"));
            player.sendMessage(new TextComponent(ChatColor.GREEN+sender.getName()+"님의 파티신청을 받았습니다!"));
        } else {
            player.sendMessage(new TextComponent(ChatColor.GREEN+sender.getName()+"님에게 파티신청을 받은적이 없습니다!"));
        }
    }


    public static void denyParty(ProxiedPlayer sender, ProxiedPlayer player) {
        if (PartyData.partyData.get(sender) != null) {
            if (wasSendParty(sender, player)) {
                PartyData.sendData.get(sender).remove(player);
                sender.sendMessage(new TextComponent(ChatColor.RED + player.getName() + "님이 파티신청을 거절했습니다"));
                player.sendMessage(new TextComponent(ChatColor.RED + sender.getName() + "님의 파티신청을 거절했습니다!"));
            }
        } else {
            player.sendMessage(new TextComponent(ChatColor.GREEN+sender.getName()+"님에게 파티신청을 받은적이 없습니다!"));
        }
    }


    public static boolean inviteCheck(ProxiedPlayer sender, ProxiedPlayer player) {
        if (PartyData.sendData.get(sender) != null) {
            for (ProxiedPlayer proxiedPlayer : PartyData.sendData.get(sender)) {
                if (proxiedPlayer == player) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean wasEnterParty(ProxiedPlayer player) {
        for (ProxiedPlayer proxiedPlayers : PartyData.partyData.keySet()) {
            for (ProxiedPlayer key : PartyData.partyData.get(proxiedPlayers)) {
                if (key == player) {
                    return true;
                }
            }
        }
        return false;
    }
    public static ProxiedPlayer wasEnterPartyAndGetParty(ProxiedPlayer player) {
        for (ProxiedPlayer proxiedPlayers : PartyData.partyData.keySet()) {
            for (ProxiedPlayer key : PartyData.partyData.get(proxiedPlayers)) {
                if (key == player) {
                    return proxiedPlayers;
                }
            }
        }
        return null;
    }
    public static boolean wasSendParty(ProxiedPlayer sender, ProxiedPlayer player) {
        for (ProxiedPlayer proxiedPlayer : PartyData.sendData.get(sender)) {
            for (ProxiedPlayer key : PartyData.sendData.get(proxiedPlayer)) {
                if (key == player) {
                    return false;
                }
            }
        }
        return true;
    }
}
