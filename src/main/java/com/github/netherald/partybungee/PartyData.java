package com.github.netherald.partybungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;



// 서버 주소는 highright.tk
// 플러그인 테스트좀 도와줄사람만 와주세요

public class PartyData {
    public static HashMap<ProxiedPlayer, ArrayList<ProxiedPlayer>> partyData = new HashMap<>();
    public static HashMap<ProxiedPlayer, ArrayList<ProxiedPlayer>> sendData = new HashMap<>();
    // 못들어가는 서버 arraylist 입니다
    public static ArrayList<String> arrayList = new ArrayList<>(); 
}
