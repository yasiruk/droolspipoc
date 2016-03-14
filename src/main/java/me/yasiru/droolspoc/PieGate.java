package me.yasiru.droolspoc;

/**
 * Created by yasiru on 3/14/16.
 */
public class PieGate {
    int inputOne;
    int inputTwo;
    public static int HIGH = 1;
    public static int LOW = 0;
    public PieGate() {
    }

    public PieGate(int inputOne, int inputTwo) {
        this.inputOne = inputOne;
        this.inputTwo = inputTwo;
    }

    public int getInputOne() {
        return inputOne;
    }

    public void setInputOne(int inputOne) {
        this.inputOne = inputOne;
    }

    public int getInputTwo() {
        return inputTwo;
    }

    public void setInputTwo(int inputTwo) {
        this.inputTwo = inputTwo;
    }
}
