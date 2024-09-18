package com.example;

import org.jline.terminal.Terminal;

public class Game {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 20;
    public static final int HORIZONTALSPEED = 200; // in ms
    public static final int VERTICALSPEED = 400;
    // ---------------------------------------------------------------add a menu with score board

    public static void main(String[] args) {
        Snake snake = new Snake(WIDTH, HEIGHT);
        InputHandler inputHandler = null;

        try {
            inputHandler = new InputHandler();
            Terminal terminal = inputHandler.getTerminal();
            GamePanel gamePanel = new GamePanel(WIDTH, HEIGHT, snake, terminal);
            
            boolean isGameOver = false;

            while (!isGameOver) {
                Key currentDirection = inputHandler.getCurrentDirection();

                snake.updSnake(currentDirection);           // moves SNAKE

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
