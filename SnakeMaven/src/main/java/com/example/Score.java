package com.example;

public class Score {
    private int score;

    private Level level;
   
    public Score(Level level) {
        this.score = 0;
        this.level = level;
    }

    public void updScore() {
        score += level.getLevel() * 100;
    }

    public int getScore() {
        return score;
    }

    public String getStrScore() {
        String strScore = Integer.toString(score);
        return strScore;
    }
}