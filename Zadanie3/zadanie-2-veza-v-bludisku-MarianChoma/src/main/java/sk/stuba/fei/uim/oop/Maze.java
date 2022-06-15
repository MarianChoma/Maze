package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Maze{
    public Maze() {
        playGame();
    }

    public void playGame() {
        JFrame frame = new JFrame();
        var application = new Grid();
        var buttonPanel = new JPanel();
        var menu=new JPanel();
        var victories=application.getNumberOfWins();
        application.draw();

        menu.setBackground(Color.WHITE);
        menu.setLayout(new BorderLayout());
        menu.add(victories,BorderLayout.PAGE_START);

        buttonPanel.setVisible(true);

        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        var up = (new JButton("^"));
        c.gridx = 1;
        c.gridy = 0;
        up.setPreferredSize(new Dimension(50, 30));
        buttonPanel.add(up, c);

        JButton left = new JButton("<");
        c.gridx = 0;
        c.gridy = 1;
        left.setPreferredSize(new Dimension(50, 30));
        buttonPanel.add(left, c);

        JButton down = new JButton("v");
        c.gridx = 1;
        c.gridy = 1;
        down.setPreferredSize(new Dimension(50, 30));
        buttonPanel.add(down, c);

        JButton right = new JButton(">");
        c.gridx = 2;
        c.gridy = 1;
        right.setPreferredSize(new Dimension(50, 30));
        buttonPanel.add(right, c);

        JButton reset = new JButton("Reset");
        menu.add(reset,BorderLayout.PAGE_END);

        buttonPanel.setBackground(Color.CYAN);
        reset.setPreferredSize(new Dimension(50, 60));

        frame.setLayout(new BorderLayout());
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(application, BorderLayout.CENTER);
        frame.add(menu, BorderLayout.LINE_START);
        menu.add(buttonPanel,BorderLayout.CENTER);
        frame.setFocusable(true);
        frame.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                frame.requestFocusInWindow();
            }
        });

        down.addActionListener(e -> {application.moveDown();
            clickButton(application,frame);
        });
        up.addActionListener(e -> {application.moveTop();
            clickButton(application,frame);
        });
        left.addActionListener(e -> {application.moveLeft();
            clickButton(application,frame);
        });
        right.addActionListener(e -> {application.moveRight();
            clickButton(application,frame);
        });
        reset.addActionListener(e -> {
            application.restart();
            frame.requestFocusInWindow();
        });

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        application.moveTop();
                        application.checkWin();
                        break;
                    case KeyEvent.VK_DOWN:
                        application.moveDown();
                        application.checkWin();
                        break;
                    case KeyEvent.VK_LEFT:
                        application.moveLeft();
                        application.checkWin();
                        break;
                    case KeyEvent.VK_RIGHT:
                        application.moveRight();
                        application.checkWin();
                        break;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    private void clickButton(Grid application,JFrame frame){
        application.checkWin();
        frame.requestFocusInWindow();
    }
}
