package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class Cell extends JPanel {
    public static final int CELL_SIZE = 1;
    private final int xPos;
    private final int yPos;
    private boolean visited = false;
    private int top = 1, left = 1, bottom = 1, right = 1;
    MatteBorder Border = BorderFactory.createMatteBorder(top,left,bottom,right, Color.BLACK);

    public Cell (int x, int y) {
        xPos = x;
        yPos = y;
        setBorder(BorderFactory.createBevelBorder(CELL_SIZE));
        setBorder(Border);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
        setBorder(BorderFactory.createMatteBorder(top,left,bottom,right,Color.BLACK));
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
        setBorder(BorderFactory.createMatteBorder(top,left,bottom,right,Color.BLACK));
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
        setBorder(BorderFactory.createMatteBorder(top,left,bottom,right,Color.BLACK));
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
        setBorder(BorderFactory.createMatteBorder(top,left,bottom,right,Color.BLACK));
    }

    @Override
    public MatteBorder getBorder() {
        return Border;
    }

    public void setBorder(MatteBorder border) {
        this.Border = border;
    }

}
