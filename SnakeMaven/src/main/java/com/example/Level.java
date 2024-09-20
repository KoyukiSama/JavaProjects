package com.example;
import java.util.concurrent.ThreadLocalRandom;

public class Level {
    private int level;
    private int w;
    private int h;
    private Snake snake;
    private Food food;
    private int foodPos;

    private int enemyX1;

    public Level(int width, int height, Snake snake, Food food) {

        this.level = 0;
        this.w = width;
        this.h = height;

        this.snake = snake;
        this.food = food;
        this.foodPos = food.getFood();

    }

    public void calcEnemyX() {
        int x = 0;
        int y = 0;
        int index = 0;
        int[] arrSnake = snake.getSnake();
        boolean valid = false;
        while (!valid) {
            x = ThreadLocalRandom.current().nextInt(1, w-1);    // generates random number,
            y = ThreadLocalRandom.current().nextInt(1, h-1);    // and excludes borders
            index = Util.XYtoIndex(x, y, w); // food index

            valid = true;
            for (int pos : arrSnake) {
                if (pos == index) {
                    valid = false;
                }
            }
            if (foodPos == index) {
                valid = false;
            }
        }
        enemyX1 = index;
    }

    public void updLevel() {
        level += 1;
        if (level >= 5) {
            calcEnemyX();
        }
    }

    public int getEnemyX() {
        return enemyX1;
    }
    public int getLevel() {
        return level;
    }

    public String getStrLevel() {
        String strLevel = Integer.toString(level);
        return strLevel;
    }
}