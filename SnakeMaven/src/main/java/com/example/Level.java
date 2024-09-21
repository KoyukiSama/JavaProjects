package com.example;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Level {
    private int level;
    private int w;
    private int h;
    private Snake snake;
    private Food food;

    private ArrayList<int[]> enemyXList;
    private ArrayList<int[]> enemyBList;

    public Level(int width, int height, Snake snake, Food food) {

        this.level = 0;
        this.w = width;
        this.h = height;

        this.snake = snake;
        this.food = food;
        this.enemyXList = new ArrayList<>();
        this.enemyBList = new ArrayList<>();
    }

    
    private void calcEnemyX() {                                              ///// calc of enemy positions /////
        for (int i = 0; i < enemyXList.size(); i++) {
            int[] arrSnake = snake.getSnake();
            int[] enemyX = new int[1];
            int index = 0;
            boolean valid = false;
            while (!valid) {
                int x = ThreadLocalRandom.current().nextInt(1, w-1);    // generates random number,
                int y = ThreadLocalRandom.current().nextInt(1, h-1);    // and excludes borders
                index = Util.XYtoIndex(x, y, w); // food index

                valid = true;
                for (int snakePos : arrSnake) {
                    if (snakePos == index) {
                        valid = false;
                    }
                }
                if (food.getFood() == index) {
                    valid = false;
                }
            }
            enemyX[0] = index;
            enemyXList.set(i, enemyX); 
        }
    }
    private void calcEnemyB() {
        for (int j = 0; j < enemyBList.size(); j++) {
            int HorY = ThreadLocalRandom.current().nextInt(0, 2);
            int length = ThreadLocalRandom.current().nextInt(3, 10); // length of wall
            int[] enemyB = new int[length];
            int snakeHead = snake.getHeadPosition();
            boolean valid = false;
            while (!valid) {
                int x = ThreadLocalRandom.current().nextInt(1, w-1);    // generates random number,
                int y = ThreadLocalRandom.current().nextInt(1, h-1);    // and excludes borders
                valid = true;
                if (HorY == 0) {                                // horizontal
                    for (int i = 0, nx = x; i < length; i++, nx++) {     // populate array
                        enemyB[i] = Util.XYtoIndex(nx, y, w);
                        if (enemyB[i] == snakeHead || nx == w-1 || enemyB[i] == food.getFood()) {
                            valid = false;
                        }
                    } 
                } else if (HorY == 1) {                         // vertical
                    for (int i = 0, ny = y; i < length; i++, ny++) {
                        enemyB[i] = Util.XYtoIndex(x, ny, w);
                        if (enemyB[i] == snakeHead || ny == h-1 || enemyB[i] == food.getFood()) {
                            valid = false;
                        }
                    }
                }
            }
            enemyBList.set(j, enemyB);
        }
    }

    private void enemyXSpawn() { // works in reversed way, starts at bottom     ////// level spawns of enemies /////
        if (level >= 15) {
            addEnemy(enemyXList, 3);
        } else if (level >= 10) {
            addEnemy(enemyXList, 2);
        } else if (level >= 5) {
            addEnemy(enemyXList, 1);
        }
        calcEnemyX();
    }
    private void enemyBSpawn() {
        if (level >= 30) {
            addEnemy(enemyBList, 4);
        } else if (level >= 20) {
            addEnemy(enemyBList, 3);
        } else if (level >= 15) {
            addEnemy(enemyBList, 2);
        } else if (level >= 10) {
            addEnemy(enemyBList, 1);
        }
        calcEnemyB();
    }

    private void addEnemy(ArrayList<int[]> enemy, int allowed) { // allowed = how many are allowed on the field at the current level
        if (enemy.size() < allowed) {
            enemy.add(null);
        }
    }

    public void updLevel() {
        level += 1;
        enemyXSpawn();
        enemyBSpawn();
    }


    public ArrayList<int[]> getEnemyX() {
        return enemyXList;
    }
    public ArrayList<int[]> getEnemyB() {
        return enemyBList;
    }

    public int getLevel() {
        return level;
    }

    public String getStrLevel() {
        String strLevel = Integer.toString(level);
        return strLevel;
    }
}