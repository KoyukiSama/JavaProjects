public class Snake {

    private char[] snake;
    private int w;      // width and height
    private int h;
    private int x;      // positions
    private int y;

    Public Snake(int width, int height) { // initialise snake
        this.w = width;
        this.h = height;
        this.x = 2;
        this.y = width - 2;   // start in left down corner
        this.snake = new char[width*height];
    }



}