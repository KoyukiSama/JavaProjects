public class AppleGirlGame {

    private int width; // fields
    private int height;
    private char[][] grid;

    public AppleGirlGame(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];

        popGrid();
    }

    private void popGrid() { // populates grid
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = ' ';
            }
        }
    }

    ////// getters and setters //////
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public char[][] getGrid() {
        return grid;
    }

    ////// game start ///////

    public static void main(String[] args) {

    }


    ////// extra game logic /////

    public void printGrid() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println("");
        }
    }
}
