package com.example;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Level {
    private int level;
    private int w;
    private int h;
    private Snake snake;
    private int foodPos;

    private ArrayList<Integer> enemyXList;

    public Level(int width, int height, Snake snake, Food food) {

        this.level = 0;
        this.w = width;
        this.h = height;

        this.snake = snake;
        this.foodPos = food.getFood();
        this.enemyXList = new ArrayList<>();
    }

    private void calcEnemyX() {
        for (int i = 0; i < enemyXList.size(); i++) {
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
                for (int snakePos : arrSnake) {
                    if (snakePos == index) {
                        valid = false;
                    }
                }
                if (foodPos == index) {
                    valid = false;
                }
            }
            enemyXList.set(i, index); 
        }
    }
    private void addEnemyX(int allowed) { // allowed = how many are allowed on the field at the current level
        if (enemyXList.size() < allowed) {
            enemyXList.add(null);
        }
        calcEnemyX();
    }
    private void enemyXSpawn() { // works in reversed way, starts at bottom
        if (level >= 15) {
            addEnemyX(3);
        } else if (level >= 10) {
            addEnemyX(2);
        } else if (level >= 5) {
            addEnemyX(1);
        }
    }

    public void updLevel() {
        level += 1;
        enemyXSpawn();
    }

    public ArrayList<Integer> getEnemyX() {
        return enemyXList;
    }
    public int getLevel() {
        return level;
    }

    public String getStrLevel() {
        String strLevel = Integer.toString(level);
        return strLevel;
    }
}