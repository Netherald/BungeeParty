package com.github.netherald.partybungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import java.util.ArrayList;

public class PartyFunctions {
    public static void invitePlayer(ProxiedPlayer sender_player, ProxiedPlayer player) {
        if (PartyData.sendData.get(sender_player) != null) {
            if (wasEnterParty(sender_player, player)) {
                sender_player.sendMessage(new TextComponent(ChatColor.RED + "이미 다른파티에 있습니다!"));
                return;
            }
            if (inviteCheck(sender_player, player)) {
                sender_player.sendMessage(new TextComponent(ChatColor.RED + "이미 초대를 보넸습니다!"));
            }
        } else {
            ArrayList<ProxiedPlayer> arrayList = new ArrayList<>();
            arrayList.add(player);
            PartyData.partyData.put(sender_player, arrayList);
            sender_player.sendMessage(new TextComponent(ChatColor.GREEN + player.getName() + "님에게 초대를 보넸습니다!"));
            player.sendMessage(new TextComponent(ChatColor.GREEN + sender_player.getName() + "님이 초대를 보넸습니다!"));
        }
    }

    public static void acceptParty(ProxiedPlayer sender, ProxiedPlayer player) {
        if (wasEnterParty(sender, player)) {
            sender.sendMessage(new TextComponent(ChatColor.RED + "이미 다른파티에 있습니다!"));
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
            sender.sendMessage(new TextComponent(ChatColor.GREEN+player.getName()+"님이 파티신청을 받았습니다!"));
            player.sendMessage(new TextComponent(ChatColor.GREEN+sender.getName()+"님의 파티신청을 받았습니다!"));
        } else {
            player.sendMessage(new TextComponent(ChatColor.GREEN+sender.getName()+"님에게 파티신청을 받은적이 없습니다!"));
        }
    }


    public static void denyParty(ProxiedPlayer sender, ProxiedPlayer player) {
        if (PartyData.partyData.get(sender) != null) {
            PartyData.sendData.get(sender).remove(player);
            sender.sendMessage(new TextComponent(ChatColor.RED+player.getName()+"님이 파티신청을 거절했습니다"));
            player.sendMessage(new TextComponent(ChatColor.RED+sender.getName()+"님의 파티신청을 거절했습니다!"));
        } else {
            player.sendMessage(new TextComponent(ChatColor.GREEN+sender.getName()+"님에게 파티신청을 받은적이 없습니다!"));
        }
    }


    public static boolean inviteCheck(ProxiedPlayer sender, ProxiedPlayer player) {
        boolean playerCheck = false;
        if (PartyData.partyData.get(sender) != null) {
            for (ProxiedPlayer proxiedPlayer : PartyData.sendData.get(sender)) {
                if (proxiedPlayer == player) {
                    playerCheck = true;
                    break;
                }
            }
            return playerCheck;
        }
        return false;
    }

    public static boolean wasEnterParty(ProxiedPlayer sender, ProxiedPlayer player) {
        for (ProxiedPlayer key : PartyData.sendData.get(sender)) {
            if (key == player) {
                return true;
            }
        }
        return false;
    }
}
