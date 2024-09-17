public class Util {

    public static int XYtoIndex(int x, int y, int width) {
        int arrayIndex = y*width + x;
        return arrayIndex;
    }

    public static int IndextoX(int arrayIndex, int width) {
        int x = arrayIndex%width;
        return x;
    }

    public static int IndextoY(int arrayIndex, int width) {
        int x = arrayIndex/width;
        return x;
    }
}