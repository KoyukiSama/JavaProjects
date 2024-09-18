package com.example;

import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;

public class GamePanel {
    private char[] grid;
    private int gridLength;
    private int w;
    private int h;

    private Snake snake;
    private Terminal terminal;

    public GamePanel(int width, int height, Snake snake, Terminal terminal) {
        this.grid = new char[width*height];
        this.gridLength = width*height;
        this.w = width;
        this.h = height;
        this.snake = snake;
        this.terminal = terminal;
    }

    private void printScore() {
        terminal.writer().println("SCORE: " /*+ Score.getScore()*/);  // update with score later
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

    private void printGrid() {
        for (int i = 0; i < gridLength; i++) {
            int x = Util.IndextoX(i, w); // converts the current index to x y values
            terminal.writer().print(grid[i]);
            if (x == w-1) {
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

        printGrid(); // print whole grid
    }

    
}