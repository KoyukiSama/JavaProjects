public class Menu implements Runnable{
    
    private char[][] grid; // grid
    private int width;
    private int height;

    private boolean upKey = false;
    private boolean downKey = false;

    private final int arrowXPos = opXPos - 5;
    private int arrowCur = 0;
    
    private final static int opXPos = 40;
    private final Object[][] options = { // 0 "option", 1 y, 2 option x
        {"Start", 7, opXPos,},
        {"option2", 9, opXPos},
        {"option3", 11, opXPos}
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
    private void cursorGrid() { // draws cursor onto screen
        String cursor = "---#";
        String cursor2 = "#---";
        char[] cursorChars = cursor.toCharArray();
        char[] cursorChars2 = cursor2.toCharArray();
        int yPos = (Integer) options[arrowCur][1];
        int xPos = arrowXPos;
        String curOption = (String) options[arrowCur][0];
        int optionLEN = curOption.length();
        for (int i = 0; i < cursor.length(); i++) {
            grid[yPos][xPos+i] = cursorChars[i];
            grid[yPos][xPos+i+6+optionLEN] = cursorChars2[i];
        }
    }

    ////// cursor moving ///////
    @Override
    public void run() {
        while (upKey || downKey) {
            try {
                if (upKey) {
                    moveUp();
                } else if (downKey) {
                    moveDown();
                }
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    private void moveUp() {
        int firstOption = 0;
        if (arrowCur == firstOption) {
            arrowCur = options.length - 1;
        } else {
            arrowCur--;
        }
    }
    private void moveDown() {
        int lastOption = options.length - 1;
        if (arrowCur == lastOption) {
            arrowCur = 0;
        } else {
            arrowCur++;
        }
    }

    /////// display, update , initialise grids /////
    private void printGrid() {  // print out current grid
        System.out.println("");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println("");
        }
    }
    public void updateGrid() { // updates and puts together the grid
        borderGrid();      // v
        optionsGrid();     // v initialise
        cursorGrid();

        printGrid();       // v print
    }


    ////// Setters and getters //////
    public void setDownKey(boolean bool) {
        this.downKey = bool;
    }
    public void setUpKey(boolean bool) {
        this.upKey = bool;
    }
    
    public String getArrowSelection() {
        return (String) options[arrowCur][0];
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
    }
}