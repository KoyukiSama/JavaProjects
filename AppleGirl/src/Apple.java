public class Apple implements Runnable{
    
    private int x = 0; // fields
    private int y = 0;
    private static char apple = 'O';
    private boolean isFalling = true;
    private int fallspeed = 1000;

    public Apple(int x, int y) { // constructor
        this.x = x;
        this.y = y;
    }


    ////// logic /////
    
    //display apple?

    @Override
    public void run() {
        while (isFalling) {
            try {
                Thread.sleep(fallspeed);
                fall();
            } catch (InterruptedException e) { // for catch apple or end game
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void fall() { // fall
        y++;
    }


    /////// getters, setters ///////
    public int getX() {   // get x and y
        return x;
    }
    public int getY() {
        return y;
    }
    public char getApple() {
        return apple;
    }
    
    public void setFallspeed(int speed) { // set speed
        this.fallspeed = speed;
    }
    public void setIsFalling(boolean bool) {
        this.isFalling = bool;
    }

}