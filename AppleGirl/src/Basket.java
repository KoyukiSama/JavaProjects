public class Basket implements Runnable {

    private int x = 0; // fields
    private int y = 0;
    private static char V = 'V';
    private boolean leftKey = false;
    private boolean rightKey = false;
    private int moveSpeed = 200;

    public Basket(int x, int y) { // constructor
        this.x = x;
        this.y = y;
    }


    ////// logic methods //////

    // goLeft
    @Override
    public void run() {   // 
        while (leftKey || rightKey) {
            try {
                if (leftKey) {
                    goLeft();
                } else if (rightKey) {
                    goRight();
                }
                Thread.sleep(moveSpeed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void goLeft() { // moving input logic
        x--;
    }
    public void goRight() {
        x++;
    }


    // goRight


    /////// Setters and getters //////

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

}