public class Snake {

    private int[] snake;            // initialise RING BUFFER
    private int snakeLength;
    private int w;      // width and height
    private int h;
    private int x;      // cords of head
    private int y;
    private int head;   // head and tail (stores index)
    private int tail;
    private Key prevDirection; // previous key press

    public Snake(int width, int height) { // initialise snake
        this.w = width;
        this.h = height;
        this.x = 2;
        this.y = width - 2;   // start in left down corner
        this.head = 0;        // set as 0
        this.tail = 0;
        this.snake = new int[width*height];
        this.prevDirection = RIGHT;
        this.snakeLength = 1;
    }


    // Helper methods //
    private int increment(int i) {
        if (snake[i] != w*h) {
            return i++;
        } else {
            return i = 0;
        }
    }

    private boolean isPrevDirection(Key curDirection) { // if prevkey, returns true
        if (curDirection == prevDirection) {
            return true;
        } else {
            return false;
        }
    }
    
    private void moveHead() {
        Key curDirection = getDirection();
                                            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! IMPLEMENT KEY STUFF LATER
        if (isPrevDirection(curDirection)) {
            switch (curDirection) {
                case UP:
                    y--;
                    break;
                case RIGHT:
                    x--;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x++;
                    break;
                default:
                    break;
            }
        } else {
            switch (curDirection) {   
                case UP:
                    y++;
                    break;
                case RIGHT:
                    x++;
                    break;
                case DOWN:
                    y--;
                    break;
                case LEFT:
                    x--;
                    break;
                default:
                    break;
            }
        }
    }

    private void updSnakeLength() {
        if (tail > head) {
            this.snakeLength = head + w*h - tail;
        } else {
            this.snakeLength = head - tail;
        }
    }

    
    // updating head and tail //
    private void cutTail() {
        snake[tail] = -1; // set garbage value to -1
        tail = increment(tail);
    }

    private void growHead() {
        int lastHead = head;
        head = increment(head);
        moveHead();

        prevDirection = getDirection();
        snake[head] = Util.XYtoIndex(x, y, w);
    }


    //  self collission //
    private boolean isSelfCollide() {         /// !!!!!!!!!!!!!!!!! add into game over later
        int counter = 0;
        for (int pos : snake) {
            if (snake[head] == pos) {
                counter++;
            }
        }
        if (counter < 2) { // head will occur once on it self 
            return false;
        } else {
            return true;
        }
    }


    //// update snake ////
    private void updSnake() {
        growHead();
        cutTail();
        updSnakeLength();
    }



    // getters and setters //
    public int getHeadPosition() {
        return snake[head];
    }
    public int getHead() {
        return head;
    }
    public int getTail() {
        return tail;
    }
    public int[] getSnake() {
        return snake;
    }
    public int getSnakeLength() {
        return snakeLength;
    }
}