import java.awt.Color;
import java.util.Random;
import java.util.Arrays;

public class SortingAlgorithms {
	private class SubAlgorithms {
		public void swap(int id1, int id2) {
			int tmp = visualiser.array[id1];
			visualiser.array[id1] = visualiser.array[id2];
			visualiser.array[id2] = tmp;
	
			visualiser.elementColors[id1] = Color.RED;
			visualiser.elementColors[id2] = Color.RED;
		}
	}

	private final int SHUFFLE_DELAY = 1;
	private final int MERGE_SORT_DELAY = 1;
	private final int HEAP_SORT_DELAY = 1;

	private final int SHELL_SORT_RATE = 1;
	private final int SELECTION_SORT_RATE = 20;
	private final int BUBBLE_SORT_RATE = 18;
	private final int INSERTION_SORT_RATE = 10;
	private final int GNOME_SORT_RATE = 24;

	private ArraySortVisualiser visualiser;
	private SubAlgorithms algorithms;
	private Random random;

	public SortingAlgorithms(ArraySortVisualiser visualiser) {
		this.visualiser = visualiser;
		algorithms = new SubAlgorithms();
		random = new Random();
	}

	public void shuffle() {
		for (int i = visualiser.ARRAY_LENGTH - 1; i > 0; i--) {
			algorithms.swap(i, random.nextInt(i+1));
			visualiser.update(SHUFFLE_DELAY);
		}
		visualiser.update(0);
	}

	public void reverse() {
		
	}

	public void bubbleSort() {
		byte updateFrame = 0;
		boolean isSolved;
		for (int i = 1; i < visualiser.ARRAY_LENGTH; i++) {
			isSolved = true;
			for (int j = 0; j < visualiser.ARRAY_LENGTH - i; j++) {
				if (i > 1) {
					visualiser.elementColors[visualiser.ARRAY_LENGTH - i + 1] = Color.GREEN;
				}
				if (visualiser.array[j] > visualiser.array[j + 1]) {
					algorithms.swap(j, j + 1);
					isSolved = false;
				} else {
					visualiser.elementColors[j] = new Color(255, 60, 0);
				}
				updateFrame++;
				updateFrame %= BUBBLE_SORT_RATE;
				if (updateFrame == 0) {
					visualiser.update(1);
				}
			}
			if (isSolved) {
				break;
			}
		}
		visualiser.update(0);
	}

	public void insertionSort() {
		byte updateFrame = 0;
		for (int i = 1; i < visualiser.ARRAY_LENGTH; i++) {
			int j = i;
			while (j > 0 && visualiser.array[j] < visualiser.array[j - 1]) {
				algorithms.swap(j, j - 1);
				visualiser.elementColors[i] = Color.GREEN;
				j--;
				updateFrame++;
				updateFrame %= INSERTION_SORT_RATE;
				if (updateFrame == 0) {
					visualiser.update(1);
				}
			}
		}
		visualiser.update(0);
	}

	public void gnomeSort() {
		int i = 0;
		byte updateFrame = 0;

		while (i < visualiser.ARRAY_LENGTH - 1) {
			if (visualiser.array[i] >  visualiser.array[i + 1]) {
				algorithms.swap(i, i + 1);
				i += (i > 0) ? -1 : 1;
			} else {
				visualiser.elementColors[i] = new Color(255, 60, 0);
				visualiser.elementColors[i + 1] = new Color(255, 60, 0);
				i++;
			}
			updateFrame++;
			updateFrame %= GNOME_SORT_RATE;
			if (updateFrame == 0) {
				visualiser.update(1);
			}
		}

		visualiser.update(0);
	}

	public void selectionSort() {
		int smallest;
		byte updateFrame = 0;
		for (int i = 0; i < visualiser.ARRAY_LENGTH - 1; i++) {
			smallest = i;
			for (int j = i; j < visualiser.ARRAY_LENGTH; j++) {
				if (visualiser.array[j] < visualiser.array[smallest]) {
					smallest = j;
				}

				visualiser.elementColors[smallest] = new Color(255, 60, 0);
				visualiser.elementColors[j] = Color.RED;
				if (i > 0) {
					visualiser.elementColors[i - 1] = Color.GREEN;
				}

				updateFrame++;
				updateFrame %= SELECTION_SORT_RATE;
				if (updateFrame == 0) {
					visualiser.update(1);
				}
			}
			algorithms.swap(smallest, i);

			visualiser.update(1);
		}
		visualiser.update(0);
	}

	public void shellSort() {
		byte updateFrame = 0;
		for (int gap = visualiser.ARRAY_LENGTH / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < visualiser.ARRAY_LENGTH; i++) {
				int j = i;
				while (j >= gap && visualiser.array[j] < visualiser.array[j - gap]) {
					algorithms.swap(j, j - gap);
					visualiser.elementColors[i] = Color.GREEN;
					j -= gap;

					updateFrame++;
					updateFrame %= SHELL_SORT_RATE;
					if (updateFrame == 0) {
						visualiser.update(1);
					}
				}
			}
		}
		visualiser.update(0);
	}

	private void mergeSort(int[] array, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(array, left, mid);
			mergeSort(array, mid + 1, right);
			merge(array, left, mid, right);
		}
	}
	
	private void merge(int[] array, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;
	
		int[] L = new int[n1];
		int[] R = new int[n2];
	
		for (int i = 0; i < n1; ++i)
			L[i] = array[left + i];
		for (int j = 0; j < n2; ++j)
			R[j] = array[mid + 1 + j];
	
		int i = 0, j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				array[k] = L[i];
				visualiser.elementColors[k] = Color.RED;
				i++;
			} else {
				array[k] = R[j];
				visualiser.elementColors[k] = Color.RED;
				j++;
			}
			visualiser.array = array;
			visualiser.update(MERGE_SORT_DELAY);
			k++;
		}
	
		while (i < n1) {
			array[k] = L[i];
			visualiser.elementColors[k] = Color.RED;
			visualiser.array = array;
			visualiser.update(MERGE_SORT_DELAY);
			i++;
			k++;
		}
	
		while (j < n2) {
			array[k] = R[j];
			visualiser.elementColors[k] = Color.RED;
			visualiser.array = array;
			visualiser.update(MERGE_SORT_DELAY);
			j++;
			k++;
		}
	}
	
	// To initiate Merge Sort, call this method
	public void startMergeSort() {
		mergeSort(visualiser.array, 0, visualiser.ARRAY_LENGTH - 1);
		visualiser.update(0);
	}

	private void heapSort() {
		int n = visualiser.ARRAY_LENGTH;
	
		// Build max heap
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(visualiser.array, n, i);
		}
	
		// Heap sort
		for (int i = n - 1; i > 0; i--) {
			algorithms.swap(0, i); // Move current root to end
			visualiser.update(HEAP_SORT_DELAY);
			heapify(visualiser.array, i, 0); // Heapify reduced heap
		}
	
		visualiser.update(0); // Final update after sorting
	}
	
	private void heapify(int[] array, int n, int i) {
		int largest = i; // Initialize largest as root
		int left = 2 * i + 1; // Left child
		int right = 2 * i + 2; // Right child
	
		// If left child is larger than root
		if (left < n && array[left] > array[largest]) {
			largest = left;
		}
	
		// If right child is larger than largest so far
		if (right < n && array[right] > array[largest]) {
			largest = right;
		}
	
		// If largest is not root
		if (largest != i) {
			algorithms.swap(i, largest); // Swap root and largest
			visualiser.update(HEAP_SORT_DELAY);
			heapify(array, n, largest); // Recursively heapify the affected sub-tree
		}
	}
	
	// To initiate Heap Sort, call this method
	public void startHeapSort() {
		heapSort();
	}

	private void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int pi = partition(array, low, high);
			quickSort(array, low, pi - 1);
			quickSort(array, pi + 1, high);
		}
	}
	
	private int partition(int[] array, int low, int high) {
		int pivot = array[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (array[j] < pivot) {
				i++;
				algorithms.swap(i, j); // Swap elements and color them red
				visualiser.update(1);
			} else {
				visualiser.elementColors[j] = new Color(255, 60, 0); // Color for comparison
				visualiser.update(1);
			}
		}
		algorithms.swap(i + 1, high); // Swap pivot to its correct position
		visualiser.update(1);
		return i + 1;
	}
	
	// To initiate Quick Sort, call this method
	public void startQuickSort() {
		quickSort(visualiser.array, 0, visualiser.ARRAY_LENGTH - 1);
		visualiser.update(0); // Final update after sorting
	}

	public void radixLSDSort(int base) {
		int max = visualiser.ARRAY_LENGTH;
		for (int exp = 1; max / exp > 0; exp *= base) {
			countSort(visualiser.array, exp, base);
		}
		visualiser.update(0); // Final update after sorting
	}
	
	public void countSort(int[] array, int exp, int base) {
		int n = array.length;
		int[] output = new int[n];
		int[] count = new int[base];
		Arrays.fill(count, 0);
	
		for (int i = 0; i < n; i++) {
			count[(array[i] / exp) % base]++;
		}
	
		for (int i = 1; i < base; i++) {
			count[i] += count[i - 1];
		}
	
		for (int i = n - 1; i >= 0; i--) {
			output[count[(array[i] / exp) % base] - 1] = array[i];
			count[(array[i] / exp) % base]--;
		}
	
		for (int i = 0; i < n; i++) {
			array[i] = output[i];
			visualiser.array = array;
			visualiser.update(1);
		}
	}
	
	// To initiate Radix LSD Sort with custom base, call this method
	public void startRadixLSDSort(int base) {
		if (base < 2) {
			throw new IllegalArgumentException("Input lower than 2");
		}
		radixLSDSort(base);
	}
}
