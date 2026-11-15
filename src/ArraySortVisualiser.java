import javax.swing.*;
import java.awt.*;

public class ArraySortVisualiser extends JPanel implements Runnable {
	public final int ARRAY_LENGTH = 1280;

	public int[] array;
	public Color[] elementColors;

	private int panelWidth;
	private int panelHeight;
	private Algorithm sort;
	private String sortName;
	private int radixLSDBase;

	private SortingAlgorithms sortingAlgorithms;

	public ArraySortVisualiser(int width, int height) {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(width, height));

		sort = Algorithm.QUICK_SORT;
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
				case QUICK_SORT:
					sortingAlgorithms.startQuickSort();
					sort = Algorithm.HEAP_SORT;
					break;
				case HEAP_SORT:
					sortingAlgorithms.startHeapSort();
					sort = Algorithm.RADIX_SORT;
					break;
				case RADIX_SORT:
					sortingAlgorithms.startRadixLSDSort(radixLSDBase);
					sort = Algorithm.MERGE_SORT;
					break;
				case MERGE_SORT:
					sortingAlgorithms.startMergeSort();
					sort = Algorithm.SHELL_SORT;
					break;
				case SHELL_SORT:
					sortingAlgorithms.shellSort();
					sort = Algorithm.INSERTION_SORT;
					break;
				case INSERTION_SORT:
					sortingAlgorithms.insertionSort();
					sort = Algorithm.BUBBLE_SORT;
					break;
				case BUBBLE_SORT:
					sortingAlgorithms.bubbleSort();
					sort = Algorithm.SELECTION_SORT;
					break;
				case SELECTION_SORT:
					sortingAlgorithms.selectionSort();
					sort = Algorithm.GNOME_SORT;
					break;
				case GNOME_SORT:
					sortingAlgorithms.gnomeSort();
					sort = Algorithm.QUICK_SORT;
			}
			sleep(1000);
		}
	}

	private void setSortName() {
		switch (sort) {
			case QUICK_SORT:
				sortName = "Quick Sort";
				break;
			case HEAP_SORT:
				sortName = "Heap Sort";
				break;
			case RADIX_SORT:
				sortName = "Radix LSD Sort (Base " + radixLSDBase + ")";
				break;
			case MERGE_SORT:
				sortName = "Merge Sort";
				break;
			case SHELL_SORT:
				sortName = "Shell Sort";
				break;
			case INSERTION_SORT:
				sortName = "Insertion Sort";
				break;
			case BUBBLE_SORT:
				sortName = "Bubble Sort";
				break;
			case SELECTION_SORT:
				sortName = "Selection Sort";
				break;
			case GNOME_SORT:
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
