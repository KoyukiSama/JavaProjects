public class Menu {
    
    private char[][] grid; // grid
    private int width;
    private int height;

    public Menu(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
    }


    private void popGrid() { // populates grid
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == height-1 || x == 0 || x == width-1) {
                    grid[y][x] = '#';
                } else {
                    grid[y][x] = ' ';
                }
            }
        }
    }
    private void optionsGrid() { // shows options grid
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = ' ';
            }
        }
    }
    public void printSquare() {
        System.out.println("");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int width;
        int height;
        if (args.length == 2) {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        } else {
            width = 100;
            height = 20;
        }

        Menu menu = new Menu(width, height);
        menu.popGrid();
        menu.printSquare();
    }
}