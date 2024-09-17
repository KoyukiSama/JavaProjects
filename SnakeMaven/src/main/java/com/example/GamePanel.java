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
        int snakeSegment[] = snake.getSnake();
        int snakeLength = snake.getSnakeLength();
        int tail = snake.getTail();
        
        for (int i = 0, j = tail; i < snakeLength; i++, j++) {
            if (j == w) {
                j = 0;
            } 
            grid[snakeSegment[j]] = 'O';
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