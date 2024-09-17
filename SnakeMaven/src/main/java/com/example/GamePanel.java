public class GamePanel {
    private char[] grid;
    private int gridLength;
    private int w;
    private int h;

    public GamePanel(int width, int height) {
        this.grid = new char[width*height];
        this.gridLength = width*height;
        this.w = width;
        this.h = height;
    }

    private void printScore() {
        System.out.println("SCORE: " + Score.getScore());  // update with score later
    }

    private void printGrid() {
        for (int i = 0; i < gridLength; i++) {
            int x = Util.IndextoX(i, w); // converts the current index to x y values
            int y = Util.IndextoY(i, w);
            if (x == 0 || x == w || y == 0 || y == h) {
                System.out.println("#");
            } else {
                System.out.println(" ");
            }
        }
    }
}