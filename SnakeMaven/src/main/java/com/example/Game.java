package com.example;

import org.jline.terminal.Terminal;

public class Game {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 20;
    public static final int GAMESPEED = 2000; // in ms
    // ---------------------------------------------------------------add a menu with score board

    public static void main(String[] args) {
        Snake snake = new Snake(WIDTH, HEIGHT);
        InputHandler inputHandler = null;

        try {
            inputHandler = new InputHandler();
            Terminal terminal = inputHandler.getTerminal();
            GamePanel gamePanel = new GamePanel(WIDTH, HEIGHT, snake, terminal);


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
