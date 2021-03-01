package com.github.netherald.partybungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class PartyData {
    public static HashMap<ProxiedPlayer, ArrayList<ProxiedPlayer>> partyData = new HashMap<>();
    public static HashMap<ProxiedPlayer, ArrayList<ProxiedPlayer>> sendData = new HashMap<>();
}
