package com.example;

public class Level {
    private int level;

    public Level() {

        this.level = 0;
    }

    public String getLevel() {
        String strLevel = Integer.toString(level);
        return strLevel;
    }
}