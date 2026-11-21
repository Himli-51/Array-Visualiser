package org.fiveone.arrayvisualiser;

import org.fiveone.arrayvisualiser.algorithm.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArraySortVisualiser extends JPanel implements Runnable {
    public static final int ARRAY_LENGTH = 640;

    public int[] array;
    public Color[] elementColors;
    public String sortName;

    private int panelWidth;
    private int panelHeight;

    private ArrayList<Algorithm> algorithms;
    private byte sortIndex;
    private Shuffle shuffle;

    public ArraySortVisualiser(int width, int height) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));

        shuffle = new Shuffle(this);
        algorithms = new ArrayList<Algorithm>(20);
        algorithms.add(new QuickSort(this));
        algorithms.add(new MergeSort(this));
        algorithms.add(new HeapSort(this));
        algorithms.add(new RadixLSDSort(this));
        algorithms.add(new ShellSort(this));
        algorithms.add(new InsertionSort(this));
        algorithms.add(new CombSort(this));
        algorithms.add(new CocktailShakerSort(this));
        algorithms.add(new BubbleSort(this));
        algorithms.add(new SelectionSort(this));
        algorithms.add(new GnomeSort(this));

        sortIndex = 0;

        array = createArray(ARRAY_LENGTH);
        elementColors = new Color[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            elementColors[i] = Color.WHITE;
        }
    }

    private int[] createArray(int length) {
        int[] newArray = new int[length];
        for (int i = 0; i < length; i++) {
            newArray[i] = i + 1;
        }
        return newArray;
    }

    private void drawArray(Graphics g) {
        int rectWidth = panelWidth / ARRAY_LENGTH; // Calculate the width of each rectangle
        int remainingPixels = panelWidth - (rectWidth * ARRAY_LENGTH); // Calculate remaining pixels
        int extraPixelsPerRect = remainingPixels / ARRAY_LENGTH; // Distribute remaining pixels evenly among rectangles
        int extraPixelsLeft = remainingPixels % ARRAY_LENGTH; // Calculate remaining pixels after even distribution
    
        for(int i = 0; i < ARRAY_LENGTH; i++) {
            g.setColor(elementColors[i]);
            int rectHeight = (int) ((double) array[i] / ARRAY_LENGTH * panelHeight);
            int rectY = panelHeight - rectHeight; // Invert the y-coordinate
            int rectX = i * rectWidth + extraPixelsPerRect * i; // Adjust x-coordinate to include gap and extra pixels
            if (i < extraPixelsLeft) {
                rectX += i; // Add one extra pixel for the first 'extraPixelsLeft' rectangles
            } else {
                rectX += extraPixelsLeft; // Distribute remaining pixels evenly among the remaining rectangles
            }
            g.fillRect(rectX, rectY, rectWidth + (i < extraPixelsLeft ? 1 : 0), rectHeight); // Add one extra pixel for the first 'extraPixelsLeft' rectangles
        }
    }

    @Override
    public void run() {
        sortName = "Start";
        update(0);
        sleep(1000);
        while(true) {
            sortName = algorithms.get(sortIndex).getName();
            shuffle.start();
            sleep(1000);
            algorithms.get(sortIndex).start();
            sortIndex++;
            sortIndex %= algorithms.size();
            sleep(1000);
        }
    }

    public void update(int sleep) {
        panelWidth = getWidth();
        panelHeight = getHeight();
        repaint();
        sleep(sleep);
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            elementColors[i] = Color.WHITE;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawArray(g);

        g.setColor(Color.CYAN);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        g.drawString("Sort: " + sortName, 10, 20);
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
