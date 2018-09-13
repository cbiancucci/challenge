package com.mercadolibre.desafio;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Puzzle extends JFrame implements ActionListener {

    private static final long serialVersionUID = 387273093383621728L;
    private static final int PUZZLE_ROWS = 20;
    private static final int PUZZLE_COLS = 20;

    private JButton toBeSwapped;

    private JButton[][] splitImgs = null;

    public Puzzle(String name) {

        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        /**
         * Add Split Images
         */
        addSplitImage();

    }//end constructor

    private void addSplitImage() {
        String img = null;
        ImageIcon icon = null;
        splitImgs = new JButton[PUZZLE_ROWS][PUZZLE_COLS];
        List imgVec = Shuffle.shuffle(1, 400);

        for (int i = 0; i < PUZZLE_ROWS; i++) {

            for (int j = 0; j < PUZZLE_COLS; j++) {
                img = "/Users/cpbiancucci/Downloads/desafio/" + imgVec.get((i * PUZZLE_COLS) + j) + ".png";
                icon = new ImageIcon(img);
                this.splitImgs[i][j] = new JButton();
                this.splitImgs[i][j].setIcon(icon);

                /**
                 * Set split image positions
                 */
                this.splitImgs[i][j].setBounds(j * 40, i * 40, 40, 40);
                this.splitImgs[i][j].addActionListener(this);
                this.getContentPane().add(this.splitImgs[i][j]);
            }
        }//end for
    }

    public void actionPerformed(ActionEvent ae) {

        for (int i = 0; i < PUZZLE_ROWS; i++) {

            for (int j = 0; j < PUZZLE_COLS; j++) {

                if (ae.getSource() == this.splitImgs[i][j]) {
                    Icon clikedImg = this.splitImgs[i][j].getIcon();
                    log("In actionPerformed clikedImg :: " + clikedImg);
                    checkAndMoveButton(i, j);
                }
            }
        }//end for

    }//end action method

    private void checkAndMoveButton(int row, int col) {

        JButton curButton = this.splitImgs[row][col];

        if (toBeSwapped != null) {
            swapIcon(curButton, toBeSwapped);
            toBeSwapped = null;
        } else {
            toBeSwapped = curButton;
        }
        /*
        if (isSwapAllowed(swapButton = getTopButton(row, col))) {
            swapIcon(curButton, swapButton);
        } else if (isSwapAllowed(swapButton = getBottomButton(row, col))) {
            swapIcon(curButton, swapButton);
        } else if (isSwapAllowed(swapButton = getLeftButton(row, col))) {
            swapIcon(curButton, swapButton);
        } else if (isSwapAllowed(swapButton = getRightButton(row, col))) {
            swapIcon(curButton, swapButton);
        }*/

    }

    /**
     * Swap possible only if the button available and its empty.
     *
     * @param swapButton
     * @return
     */
    private boolean isSwapAllowed(JButton swapButton) {
        return swapButton != null && swapButton.getIcon() == null;
    }

    private void swapIcon(JButton curButton, JButton swapButton) {

        if (swapButton != null && swapButton.getIcon() != null) {
            Icon oldIcon = swapButton.getIcon();
            swapButton.setIcon(curButton.getIcon());
            curButton.setIcon(oldIcon);
        }
    }

    private JButton getTopButton(int row, int col) {
        return getButton(row - 1, col);
    }

    private JButton getBottomButton(int row, int col) {
        return getButton(row + 1, col);
    }

    private JButton getLeftButton(int row, int col) {
        return getButton(row, col - 1);
    }

    private JButton getRightButton(int row, int col) {
        return getButton(row, col + 1);
    }

    private JButton getButton(int row, int col) {

        if (row < 0 || row >= PUZZLE_ROWS || col < 0 || col >= PUZZLE_COLS) {
            return null;
        }

        return this.splitImgs[row][col];
    }

    public static void log(String msg) {
        System.out.println(msg);
    }

    /**
     * Start the puzzle.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            String name = "Puzzle";

            Puzzle p = new Puzzle(name);
            p.setSize(1400, 850);
            p.setLocation(20, 20);
            p.setVisible(true);
            p.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end main

}//end class