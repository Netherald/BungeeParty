package com.github.netherald.partybungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;


public class PartyData {
    public static HashMap<ProxiedPlayer, ArrayList<ProxiedPlayer>> partyData = new HashMap<>();
    public static HashMap<ProxiedPlayer, ArrayList<ProxiedPlayer>> sendData = new HashMap<>();
    public static HashMap<ProxiedPlayer, ArrayList<ProxiedPlayer>> joinData = new HashMap<>();
    // 못들어가는 서버 arraylist 입니다
    public static ArrayList<String> arrayList = new ArrayList<>(); 
}
