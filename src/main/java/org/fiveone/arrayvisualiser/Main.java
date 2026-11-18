package org.fiveone.arrayvisualiser;

import javax.swing.*;
import java.awt.Dimension;

public class Main {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 640;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Array Visualiser");

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArraySortVisualiser visualiser = new ArraySortVisualiser(FRAME_WIDTH, FRAME_HEIGHT);
        frame.add(visualiser);
        frame.setVisible(true);
        frame.pack();

        Thread visualiserThread = new Thread(visualiser);
        visualiserThread.start();
    }
}
