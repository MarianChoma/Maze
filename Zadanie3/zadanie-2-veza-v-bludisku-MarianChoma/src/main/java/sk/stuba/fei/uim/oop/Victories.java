package sk.stuba.fei.uim.oop;

import javax.swing.*;

public class Victories extends JLabel {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Victories(String text,int number) {
        super(text + ": " + number);
        this.number = number;
    }
    @Override
    public void setText(String text) {
        super.setText(text);
    }


}
