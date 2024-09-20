package com.example;
import java.util.concurrent.ThreadLocalRandom;

public class Food {
    private int food;
    private int w;
    private int h;

    private Snake snake;
   
    public Food(int width, int height, Snake snake) {
        this.w = width;
        this.h = height;
        this.snake = snake;
    }

    private void calcFood() {
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
        }
        food = index;
    }

    public void updFood() {
        calcFood();
    }

    public int getFood() {
        return food;
    }
}