package com.example;

public class Game {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 20;
    public static final int GAMESPEED = 500;
    // ---------------------------------------------------------------add a menu with score board

    public static void main(String[] args) {
        Snake snake = new Snake(WIDTH, HEIGHT);
        GamePanel gamePanel = new GamePanel(WIDTH, HEIGHT, snake);
        InputHandler inputHandler = null;

        try {
            inputHandler = new InputHandler();
            boolean isGameOver = false;
            Key currentDirection = Key.RIGHT;

            while (!isGameOver) {
                Key userInput = inputHandler.readInput();
                if (userInput != null) {                    // gets DIRECTION from INPUT
                    currentDirection = userInput;
                }

                snake.updSnake(currentDirection);           // moves SNAKE

                if (snake.isCollide()) {                    // checks GAMEOVER
                    isGameOver = true;
                    System.out.println("GAME OVER!");
                    break;
                }

                gamePanel.updateGrid();                     // updates GRID

                Thread.sleep(GAMESPEED);                    // sets GAMESPEED
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (inputHandler != null) {
                inputHandler.cleanup();
            }
        }


    }
}
