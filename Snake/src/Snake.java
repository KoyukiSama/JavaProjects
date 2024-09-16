public class Snake {

    private int[] snake;
    private int w;      // width and height
    private int h;
    private int x;      // cords of head
    private int y;
    private int head;   // head and tail (stores index)
    private int tail;
    private int prevKey; // previous key press

    public Snake(int width, int height) { // initialise snake
        this.w = width;
        this.h = height;
        this.x = 2;
        this.y = width - 2;   // start in left down corner
        this.snake = new int[width*height];
        this.prevKey = LEFT;
    }


    // increment position //
    private int increment(int i) {
        if (snake[i] != w*h) {
            i++;
        } else {
            i = 0;
        }
    }

    // updating head and tail //
    private void cutTail() {
        snake[tail] = -1;
        increment(tail);
    }

    private void growHead() {
        int lastHead = head;
        increment(head);

        switch (getKey()) {    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! IMPLEMENT KEY STUFF LATER
            case UP:              // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! IMPLEMENT ENUM DIRECTIONS
                if (prevKey == DOWN) {
                    y--;
                } else {
                    y++;
                }
                break;
            case RIGHT:
                if (prevKey == LEFT) {
                    x--;
                } else {
                    x++;
                }
                break;
            case DOWN:
                if (prevKey == UP) {
                    y++;
                } else {
                    y--;
                }
                break;
            case LEFT:
                if (prevKey == RIGHT) {
                    x++;
                } else {
                    x--;
                }
                break;
            default:
                break;
        }
        snake[head] = y*w+x;
    }

}