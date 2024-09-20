package com.example;

import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;

public class GamePanel {
    private char[] grid;
    private int gridLength;
    private int w;
    private int h;

    private Snake snake;
    private Food food;
    private Score score;
    private Level level;
    private Terminal terminal;

    public GamePanel(int width, int height, Snake snake, Food food, Score score, Level level, Terminal terminal) {
        this.grid = new char[width*height];
        this.gridLength = width*height;
        this.w = width;
        this.h = height;
        this.snake = snake;
        this.food = food;
        this.score = score;
        this.level = level;
        this.terminal = terminal;
    }

    private void printScore() {
        terminal.writer().println("SCORE: " + score.getStrScore());  // update with score later
    }

    private void cleanGrid() {
        for (int i = 0; i < gridLength; i++) {
            int x = Util.IndextoX(i, w); // converts the current index to x y values
            int y = Util.IndextoY(i, w);
            if (x == 0 || x == w-1 || y == 0 || y == h-1) {
                grid[i] = '#';
            } else {
                grid[i] = ' ';
            }
        }
    }

    private void snakeGrid() {
        int snakeSegments[] = snake.getSnake();
        int snakeLength = snake.getSnakeLength();
        int tail = snake.getTail();
        int gridSize = w*h;
  
        for (int i = 0, j = tail; i < snakeLength; i++, j++) {
            if (j >= snakeSegments.length) {  // snakeSegments is actually the whole grid long not just snake body
                j = 0;
            } 
            int snakeSegment = snakeSegments[j];
            if (snakeSegment >= 0 && snakeSegment < gridSize) {
                grid[snakeSegment] = 'O';  // Mark the snake segment on the grid
            } else {
                System.out.println("Warning: Snake segment out of grid bounds: " + snakeSegment);
            }
        }
    }

    private void foodGrid() {
        int foodLocation = food.getFood();
        grid[foodLocation] = '@';
    }

    private void enemyXGrid() {
        int enemyX1Location = level.getEnemyX();
        grid[enemyX1Location] = 'X';
    }

    private void printGrid() {
        for (int i = 0; i < gridLength; i++) {
            int x = Util.IndextoX(i, w); // converts the current index to x y values
            int y = Util.IndextoY(i, w);
            terminal.writer().print(grid[i]);
            if (x == w-1) {
                if (y == 0) {                           //!!!!!// prints stats
                    terminal.writer().print("  | Level: " + level.getStrLevel()); // print level
                }                                          


                terminal.writer().println();    // writer prints into a buffer that flush will print out later
            }
        }
        terminal.flush();
    }

    //// MAIN METHODS TO BE USED ////

    public void updateGrid() {
        /// clearing screen ///
        terminal.puts(Capability.clear_screen);
        terminal.flush();

        /// printing grid ///
        printScore(); // score display

        cleanGrid();  // builds grid
        snakeGrid();  // adds snake
        foodGrid(); // adds food
        int curLevel = level.getLevel();
        if (curLevel >= 5) {
            enemyXGrid();
        }
        printGrid(); // print whole grid
    }

    
}