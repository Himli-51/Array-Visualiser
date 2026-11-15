import javax.swing.*;
import java.awt.*;

public class ArraySortVisualiser extends JPanel implements Runnable {
	public final int ARRAY_LENGTH = 1280;

	public int[] array;
	public Color[] elementColors;

	private int panelWidth;
	private int panelHeight;
	private byte sort;
	private String sortName;
	private int radixLSDBase;

	private SortingAlgorithms sortingAlgorithms;

	public ArraySortVisualiser(int width, int height) {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(width, height));

		sort = 0;
		sortingAlgorithms = new SortingAlgorithms(this);
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
		radixLSDBase = 4;
		sortName = "Start";
		update(0);
		sleep(1000);
		while(true) {
			setSortName();
			sortingAlgorithms.shuffle();
			sleep(1000);
			switch (sort) {
				case 0:
					sortingAlgorithms.startQuickSort();
					break;
				case 1:
					sortingAlgorithms.startHeapSort();
					break;
				case 2:
					sortingAlgorithms.startRadixLSDSort(radixLSDBase);
					break;
				case 3:
					sortingAlgorithms.startMergeSort();
					break;
				case 4:
					sortingAlgorithms.shellSort();
					break;
				case 5:
					sortingAlgorithms.insertionSort();
					break;
				case 6:
					sortingAlgorithms.bubbleSort();
					break;
				case 7:
					sortingAlgorithms.selectionSort();
					break;
				case 8:
					sortingAlgorithms.gnomeSort();
			}
			sort++;
			sort %= 9;
			sleep(1000);
		}
	}

	private void setSortName() {
		switch (sort) {
			case 0:
				sortName = "Quick Sort";
				break;
			case 1:
				sortName = "Heap Sort";
				break;
			case 2:
				sortName = "Radix LSD Sort (Base " + radixLSDBase + ")";
				break;
			case 3:
				sortName = "Merge Sort";
				break;
			case 4:
				sortName = "Shell Sort";
				break;
			case 5:
				sortName = "Insertion Sort";
				break;
			case 6:
				sortName = "Bubble Sort";
				break;
			case 7:
				sortName = "Selection Sort";
				break;
			case 8:
				sortName = "Gnome Sort";
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
