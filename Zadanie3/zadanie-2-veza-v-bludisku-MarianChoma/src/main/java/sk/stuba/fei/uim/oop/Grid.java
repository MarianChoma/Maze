package sk.stuba.fei.uim.oop;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Stack;

public class Grid extends JPanel{
    private final Victories numberOfWins;
    private static final int GRID_SIZE = 11;
    private final Square point;
    protected Cell[][] field = new Cell[GRID_SIZE][GRID_SIZE];
    private Cell helpCell;
    private boolean click=false;

    public Victories getNumberOfWins() {
        return numberOfWins;
    }
    public Grid() {
        numberOfWins=new Victories("VICTORIES",0);
        setBackground(Color.WHITE);
        this.point=new Square(0,0);
        setPreferredSize(new Dimension(500, 500));
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public void draw(){
        Stack<Cell> stack = new Stack<>();
        ArrayList<Cell> nextMove=new ArrayList<>();
        for(int x = 0; x < GRID_SIZE; x++){
            for(int y = 0; y <GRID_SIZE; y++){
                Cell cell = new Cell(x, y);
                add(cell);
                cell.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(click) {
                            if(nextMove.stream().anyMatch(cell1 -> (cell1.getxPos()==cell.getxPos() && cell1.getyPos()==cell.getyPos()))) {
                                nextMove.clear();
                                click = false;
                                if (cell.getyPos()==GRID_SIZE-1 && cell.getxPos()==GRID_SIZE-1){
                                    clickVictory();
                                }
                                else {
                                    possibleCell(helpCell,255);
                                    cell.setBackground(Color.RED);
                                    if(cell.getyPos()!=point.getPointy() || cell.getxPos()!=point.getPointx()) {
                                        field[point.getPointx()][point.getPointy()].setBackground(Color.WHITE);
                                    }
                                    point.setPointy(cell.getyPos());
                                    point.setPointx(cell.getxPos());
                                }
                            }
                        }
                        else if(cell.getyPos()==point.getPointy() && cell.getxPos()==point.getPointx()) {
                            helpCell = cell;
                            nextMove.addAll(possibleCell(cell, 0));
                            click = true;
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        if(cell.getxPos()==point.getPointx() && cell.getyPos()==point.getPointy()) {
                            nextMove.addAll(possibleCell(cell, 0));
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if(cell.getxPos()==point.getPointx() && cell.getyPos()==point.getPointy()) {
                            if (!click) {
                                possibleCell(cell, 255);
                            }
                        }
                    }
                });
                field[x][y] = cell;
            }
        }

        Cell currF = field[0][0];
        currF.setBackground(Color.RED);
        field[10][10].setBackground(Color.GREEN);
        Cell nextF = randomize(currF);

        boolean generationComplete = false;
        while(!generationComplete) {
            if (nextF != null) {
                nextF.setVisited(true);
                deleteWalls(currF, nextF);
                stack.push(currF);
                currF = nextF;
            } else if (!(stack.isEmpty())) {
                currF = stack.pop();
            }
            if (stack.isEmpty()) {
                generationComplete = true;
            }
            nextF = randomize(currF);
        }

    }

    public Cell randomize(Cell cell){
        Stack<Cell> neighbours = new Stack<>();

        int row  = cell.getxPos();
        int col = cell.getyPos();

        Cell top;
        Cell right;
        Cell bottom;
        Cell left;

        if (row == 0) {
            top = null;
        } else {
            top = field[row - 1][col];
        }

        if (col == GRID_SIZE - 1) {
            right = null;
        } else {
            right = field[row][col + 1];
        }

        if (row == GRID_SIZE - 1) {
            bottom = null;
        } else {
            bottom = field[row + 1][col];
        }
        if (col == 0) {
            left = null;
        } else {
            left = field[row][col - 1];
        }

        if ((top != null) && !top.isVisited()) {
            neighbours.push(top);
        }
        if ((right != null) && !right.isVisited()) {
            neighbours.push(right);
        }
        if((bottom != null) && !(bottom.isVisited())){
            neighbours.push(bottom);
        }
        if((left != null) && !(left.isVisited())){
            neighbours.push(left);
        }
        if(!(neighbours.isEmpty())){
            int random;
            random = (int) Math.floor(Math.random() * neighbours.size());
            return neighbours.get(random);
        }else{
            return null;
        }
    }

    public void deleteWalls(Cell cell1, Cell cell2){
        int x = cell1.getyPos() - cell2.getyPos();
        if(x==1){
            cell1.setLeft(0);
            cell2.setRight(0);
        }else if(x==-1){
            cell1.setRight(0);
            cell2.setLeft(0);
        }

        int y = cell1.getxPos() - cell2.getxPos();

        if(y==1){
            cell1.setTop(0);
            cell2.setBottom(0);
        }else if(y==-1){
            cell1.setBottom(0);
            cell2.setTop(0);
        }
    }
    public void moveRight(){
        point.right(this);
    }
    public void moveLeft() {
        point.left(this);
    }
    public void moveTop() {
        point.up(this);
    }
    public void moveDown() {
        point.down(this);
    }
    private ArrayList<Cell> possibleCell(Cell cell, int r){
        ArrayList<Cell> freeCell=new ArrayList<>();
        if(cell.getxPos()==point.getPointx() && cell.getyPos()==point.getPointy()) {
            int i = 1;
            while ( cell.getyPos()+i<GRID_SIZE && field[cell.getxPos()][cell.getyPos() + i].getLeft() == 0) {
                field[cell.getxPos()][cell.getyPos() + i].setBackground(new Color(r,255,255));
                freeCell.add(field[cell.getxPos()][cell.getyPos() + i]);
                i++;
            }
            i=1;
            while (cell.getxPos()+i<GRID_SIZE && field[cell.getxPos()+i][cell.getyPos()].getTop() == 0) {
                field[cell.getxPos()+i][cell.getyPos()].setBackground(new Color(r,255,255));
                freeCell.add(field[cell.getxPos()+i][cell.getyPos()]);
                i++;
            }
            i=1;
            while (cell.getyPos()-i>=0 && field[cell.getxPos()][cell.getyPos()-i].getRight() == 0) {
                field[cell.getxPos()][cell.getyPos()-i].setBackground(new Color(r,255,255));
                freeCell.add(field[cell.getxPos()][cell.getyPos() - i]);
                i++;
            }
            i=1;
            while (cell.getxPos()-i>=0 && field[cell.getxPos()-i][cell.getyPos()].getBottom() == 0) {
                field[cell.getxPos()-i][cell.getyPos()].setBackground(new Color(r,255,255));
                freeCell.add(field[cell.getxPos()-i][cell.getyPos()]);
                i++;
            }
            field[GRID_SIZE-1][GRID_SIZE-1].setBackground(Color.GREEN);
        }
        return freeCell;
    }

    public void clickVictory(){
        removeAll();
        revalidate();
        draw();
        point.setPointx(0);
        point.setPointy(0);
        numberOfWins.setNumber(numberOfWins.getNumber()+1);
        numberOfWins.setText("VICTORIES: "+numberOfWins.getNumber());
    }
    public void restart(){
        numberOfWins.setNumber(0);
        numberOfWins.setText("VICTORIES: "+numberOfWins.getNumber());
        removeAll();
        revalidate();
        draw();
        point.setPointy(0);
        point.setPointx(0);
    }
    public void checkWin(){
        if (point.getPointx() == Grid.GRID_SIZE - 1 && point.getPointy() == Grid.GRID_SIZE - 1) {
            clickVictory();
        }
    }
    public void deleteAfterClick(){
        possibleCell(field[point.getPointx()][point.getPointy()], 255);
        field[GRID_SIZE-1][GRID_SIZE-1].setBackground(Color.GREEN);

    }
}
