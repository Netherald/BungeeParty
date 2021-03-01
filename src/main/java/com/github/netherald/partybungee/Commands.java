package com.github.netherald.partybungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static com.github.netherald.partybungee.PartyFunctions.*;

public class Commands extends Command {
    public Commands() {
        super("party", null, "p");
    }
    public void printHelp(CommandSender sender, String helpType) {
        if (helpType==null) {
            sender.sendMessage(new TextComponent("/p <invite/accept/deny> <플레이어이름>"));
            return;
        }
        switch (helpType) {
            case "invite":
                sender.sendMessage(new TextComponent("/p invite <플레이어이름>"));
                return;
            case "deny":
                sender.sendMessage(new TextComponent("/p deny <플레이어이름>"));
                return;
            case "accept":
                sender.sendMessage(new TextComponent("/p accept <플레이어이름>"));
                return;
            default:
                sender.sendMessage(new TextComponent("/p <invite/accept/deny> <플레이어이름>"));
        }
    }


    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            switch (args.length) {
                case 1:
                    switch (args[0]) {
                        case "list":
                            if (PartyData.partyData.get(sender) != null) {
                                for (ProxiedPlayer player : PartyData.partyData.get(sender)) {
                                    sender.sendMessage(new TextComponent(player.getName()));
                                }
                            } else {
                                sender.sendMessage(new TextComponent(ChatColor.RED + "파티가 없습니다!"));
                            }
                            return;
                        default:
                            printHelp(sender, args[0]);
                            return;
                    }
                case 2:
                    if (sender==PartyBungee.proxy.getPlayer(args[1])) {
                        sender.sendMessage(new TextComponent("자기 자신은 최고의 친구지만 서버에서는 놀게 해드릴수가 없네요 :("));
                        return;
                    }
                    switch (args[0]) {
                        case "invite":
                            if (PartyBungee.proxy.getPlayer(args[1]) != null) {
                                if (!PartyFunctions.inviteCheck((ProxiedPlayer) sender, PartyBungee.proxy.getPlayer(args[1]))) {
                                    invitePlayer((ProxiedPlayer) sender, PartyBungee.proxy.getPlayer(args[1]));
                                } else {
                                    sender.sendMessage(new TextComponent(ChatColor.RED + "그 플레이어의 파티에 이미 있습니다!"));
                                }
                            } else {
                                sender.sendMessage(new TextComponent(ChatColor.RED+"그 플레이어는 현재 오프라인입니다!"));
                            }
                            return;
                        case "accept":
                            if (PartyBungee.proxy.getPlayer(args[1]) != null) {
                                acceptParty(PartyBungee.proxy.getPlayer(args[1]), (ProxiedPlayer) sender);
                            } else {
                                sender.sendMessage(new TextComponent(ChatColor.RED+"그 플레이어는 현재 오프라인입니다!"));
                            }
                            return;
                        case "deny":
                            if (PartyBungee.proxy.getPlayer(args[1]) != null) {
                                denyParty(PartyBungee.proxy.getPlayer(args[1]), (ProxiedPlayer) sender);
                            }  else {
                                sender.sendMessage(new TextComponent(ChatColor.RED + "그사람에게서 파티신청이 온것이 없습니다!"));
                            }
                            return;
                        default:
                            printHelp(sender, args[0]);
                            return;
                    }
                default:
                    printHelp(sender, null);
            }
        }
    }
}
