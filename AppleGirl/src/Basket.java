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
        if (x == positionStart()) {
            x = positionEnd();
        } else {
            x--;
        }  
    }
    public void goRight() {
        if (x == positionEnd()) {
            x = positionStart();
        }
        x++;
    }


    
    public int positionEnd() {
        int position = game.getWidth() - 1;
        return position; // implement get width in AppleGirlGame
    }
    public int positionStart() {
        int position = 0;
        return position;
    }


    /////// Setters and getters //////

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public char getBasket() {
        return V;
    }



}