package com.example;

import org.jline.terminal.Terminal;

public class Game {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 20;
    public static int HORIZONTALSPEED = 200; // in ms
    public static int VERTICALSPEED = HORIZONTALSPEED*2;
    // ---------------------------------------------------------------add a menu with score board

    public static void main(String[] args) {
        Snake snake = new Snake(WIDTH, HEIGHT);
        Food food = new Food(WIDTH, HEIGHT, snake);
        snake.setFood(food);
        Level level = new Level(WIDTH, HEIGHT, snake, food);
        snake.setLevel(level);
        Score score = new Score(level);
        InputHandler inputHandler = null;

        try {
            inputHandler = new InputHandler();
            Terminal terminal = inputHandler.getTerminal();
            GamePanel gamePanel = new GamePanel(WIDTH, HEIGHT, snake, food, score, level, terminal);
            
            boolean isGameOver = false;

            while (!isGameOver) {
                Key currentDirection = inputHandler.getCurrentDirection();

                snake.updSnake(currentDirection);           // moves SNAKE
                if (snake.isEatFood()) {
                    level.updLevel();
                    score.updScore();
                    food.updFood();
                    HORIZONTALSPEED *= 0.98;    // makes speed go faster every level
                    VERTICALSPEED *= 0.98;
                }
                

                if (snake.isCollide()) {                    // checks GAMEOVER
                    isGameOver = true;
                    System.out.println("GAME OVER!");
                    break;
                }

                gamePanel.updateGrid();                     // updates GRID

                if (currentDirection == Key.LEFT || currentDirection == Key.RIGHT) {
                    Thread.sleep(HORIZONTALSPEED);
                } else if (currentDirection == Key.UP || currentDirection == Key.DOWN) {
                    Thread.sleep(VERTICALSPEED);
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (inputHandler != null) {
                inputHandler.stop();
            }
        }


    }
}
