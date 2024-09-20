package com.example;

public class Score {
    private int score;
   
    public Score() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public String getStrScore() {
        String strScore = Integer.toString(score);
        return strScore;
    }
}