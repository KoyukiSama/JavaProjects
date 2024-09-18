package com.example;

public enum Key {
    UP, RIGHT, DOWN, LEFT;


    public static Key getOppositeKey(Key key) {
        Key opposite = Key.RIGHT;
        switch (key) {
            case UP:
                opposite = Key.DOWN;
                break;
            case RIGHT:
                opposite = Key.LEFT;
                break;
            case DOWN:
                opposite = Key.UP;
                break;
            case LEFT:
                opposite = Key.RIGHT;
                break;
            default:
                break;
        }
        return opposite;
    }

    public static Boolean checkOpposite(Key prevKey, Key curKey) {
        curKey = getOppositeKey(curKey);
        if (curKey == prevKey) {
            return true;
        } else {
            return false;
        }
    }
}