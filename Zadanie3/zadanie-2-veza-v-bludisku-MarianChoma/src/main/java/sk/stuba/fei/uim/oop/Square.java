package sk.stuba.fei.uim.oop;


import java.awt.*;

public class Square extends Cell {
    private int pointx;
    private int pointy;

    public Square(int x,int y) {
        super(x,y);
        this.pointx = x;
        this.pointy = y;
    }

    public int getPointx() {
        return pointx;
    }

    public int getPointy() {
        return pointy;
    }

    public int setPointx(int pointx) {
        this.pointx = pointx;
        return pointx;
    }

    public int setPointy(int pointy) {
        this.pointy = pointy;
        return pointy;
    }
    private void clear(Grid grid){
        grid.field[pointx][pointy].setBackground(Color.WHITE);
        grid.deleteAfterClick();
    }
    public void down(Grid grid){
        try{
            if (grid.field[pointx + 1][pointy].getTop() == 0) {
                clear(grid);
                grid.field[setPointx(pointx + 1)][pointy].setBackground(Color.RED);
            }
        }catch(ArrayIndexOutOfBoundsException ignored){
        }
    }
    public void up(Grid grid){
        try{
            if (grid.field[pointx - 1][pointy].getBottom() == 0) {
                clear(grid);
                grid.field[setPointx(pointx - 1)][pointy].setBackground(Color.RED);
            }
        }catch(ArrayIndexOutOfBoundsException ignored){
        }
    }
    public void left(Grid grid){
        try{
            if (grid.field[pointx][pointy-1].getRight() == 0) {
                clear(grid);
                grid.field[pointx][setPointy(pointy-1)].setBackground(Color.RED);
            }
        }catch(ArrayIndexOutOfBoundsException ignored){
        }
    }
    public void right(Grid grid){
        try{
            if (grid.field[pointx][pointy+1].getLeft() == 0) {
                clear(grid);
                grid.field[pointx][setPointy(pointy+1)].setBackground(Color.RED);
            }
        }catch(ArrayIndexOutOfBoundsException ignored){
        }
    }
}
