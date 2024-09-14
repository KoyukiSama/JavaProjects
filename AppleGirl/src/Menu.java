public class Menu {
    
    private char[][] grid; // grid
    private int width;
    private int height;

    Object[][] options = {
        {"Start", 7, 35},
        {"option2", 9, 35},
        {"option3", 11, 35},
    };

    public Menu(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
    }



    ////// draw onto the grids //////
    private void borderGrid() { // populates grid
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
        for (int i = 0; i < options.length; i++) {
            String option = (String) options[i][0];     // converts Object into String
            char[] optionChars = option.toCharArray();  // converts String into charArr
            int yPos = (Integer) options[i][1];         // sets initial y position
            int xPos = (Integer) options[i][2];         // sets initial x position
            for (int j = 0; j < option.length(); j++) {
                grid[yPos][xPos+j] = optionChars[j];    // draws options onto the plate
            }
        }
    }
    private void cursorGrid() { // is for selecting

    }


    /////// display, update , initialise grids /////
    public void printGrid() {  // print out current grid
        System.out.println("");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println("");
        }
    }
    private void updateGrid() { // updates and puts together the grid
        borderGrid();      // v
        optionsGrid();     // v initialise

        printGrid();       // v print
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
        menu.updateGrid();
        menu.printGrid();
    }
}