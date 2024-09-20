package com.example;
import java.util.ArrayList;

public class Snake {

    private int[] snake;            // initialise RING BUFFER
    private int snakeLength;
    private int w;      // width and height
    private int h;
    private int x;      // cords of head
    private int y;
    private int head;   // head and tail (stores index)
    private int tail;
    private Key prevDirection; // previous key press

    private Food food;
    private Level level;

    public Snake(int width, int height) { // initialise snake
        this.w = width;
        this.h = height;
        this.x = 1;
        this.y = height - 2;   // start in left down corner
        this.head = 0;        // set as 0
        this.tail = 0;
        this.snake = new int[width*height];
        this.prevDirection = Key.RIGHT;
        this.snakeLength = 1;
        snake[tail] = Util.XYtoIndex(x, y, width);
        snake[head] = Util.XYtoIndex(x, y, width);
    }


    // Helper methods //
    public int increment(int i) {
        return (i + 1) % (w * h);
    }
    
    private void moveHead(Key curDirection) {
                                           
        if (Key.checkOpposite(prevDirection, curDirection)) { // checks if opposite is true
            curDirection = Key.getOppositeKey(curDirection);
        }
        switch (curDirection) {   
            case UP:
                y--;
                break;
            case RIGHT:
                x++;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            default:
                break;
        }
        prevDirection = curDirection;
    }

    private void updSnakeLength() {
        if (tail > head) {
            this.snakeLength = (head + w*h - tail) + 1;
        } else {
            this.snakeLength = (head - tail) + 1;
        }
    }

    
    // updating head and tail //
    private void cutTail() {
        snake[tail] = -1; // set garbage value to -1
        tail = increment(tail);
    }

    private void growHead(Key curKey) {
        head = increment(head);
        moveHead(curKey);
        snake[head] = Util.XYtoIndex(x, y, w);
    }

    // collission //
    private boolean isWallCollide() {
        if (x == 0 || x == w-1 || y == 0 || y == w-1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSelfCollide() {         /// !!!!!!!!!!!!!!!!! add into game over later
        int counter = 0;
        for (int pos : snake) {
            if (snake[head] == pos) {
                counter++;
            }
        }
        return counter > 1;
    }

    private boolean isEnemyCollide() {
        ArrayList<Integer> enemyXList = level.getEnemyX();
        int enemyXListLEN = enemyXList.size();
        for (int i = 0; i < enemyXListLEN; i++) { // go through enemyX
            if (snake[head] == enemyXList.get(i)) { return true; }
        }
        return false;
    }

    private boolean isFoodCollide() {
        return snake[head] == food.getFood();
    }

    ///// MAIN METHODS TO BE USED /////
    //// death collission ////
    public boolean isCollide() {
        if (isSelfCollide() || isWallCollide() || isEnemyCollide()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEatFood() {
        return isFoodCollide();
    }

    //// update snake ////
    public void updSnake(Key curKey) {
        growHead(curKey);
        if (!isEatFood()) {
            cutTail();
        }
        updSnakeLength();
    }



    // getters and setters //
    public int getHeadPosition() {
        return snake[head];
    }
    public int getHead() {
        return head;
    }
    public int getTail() {
        return tail;
    }
    public int[] getSnake() {
        return snake;
    }
    public int getSnakeLength() {
        return snakeLength;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    public void setLevel(Level level) {
        this.level = level;
    }
}