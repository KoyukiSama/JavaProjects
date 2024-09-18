package com.example;


public class GamePanel {
    private char[] grid;
    private int gridLength;
    private int w;
    private int h;

    private Snake snake;

    public GamePanel(int width, int height, Snake snake) {
        this.grid = new char[width*height];
        this.gridLength = width*height;
        this.w = width;
        this.h = height;
        this.snake = snake;
    }

    private void printScore() {
        System.out.println("SCORE: " /*+ Score.getScore()*/);  // update with score later
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
            System.out.print(grid[i]);
            if (x == w-1) {
                System.out.println();
            }
        }
    }

    public void updateGrid() {
        printScore(); // score display

        cleanGrid();  // builds grid
        snakeGrid();  // adds snake

        printGrid(); // print whole grid
    }

    public static void main(String[] args) {
        int width = 50;
        int height = 20;
        Snake snake = new Snake(width, height);
        GamePanel gamePanel = new GamePanel(width, height, snake);
        gamePanel.updateGrid();
    }
}