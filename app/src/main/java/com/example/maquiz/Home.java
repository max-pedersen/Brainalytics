package com.example.maquiz;

import java.util.ArrayList;

public class Home {
    private String name;
    private String zID;
    private int XP;


    public Home(String name, String zID, int XP) {
        this.name = name;
        this.zID = zID;
        this.XP = XP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getzID() {
        return zID;
    }

    public void setzID(String zID) {
        this.zID = zID;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }



    public static ArrayList<Home> testDetails(){
        ArrayList<Home> list = new ArrayList<>();
        list.add(new Home("John", "z5164310", 1200));
        return list;
    }
}
