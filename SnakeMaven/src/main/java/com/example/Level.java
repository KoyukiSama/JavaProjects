package com.example;

public class Level {
    private int level;

    public Level() {

        this.level = 0;
    }

    public int getLevel() {
        return level;
    }

    public String getStrLevel() {
        String strLevel = Integer.toString(level);
        return strLevel;
    }
}