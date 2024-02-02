/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sortingtechniques;

/**
 *
 * @author Rowan
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
public class ArrayToSortGUI extends JFrame {

    static int bubbleSortSwaps = 0;
    static int bubbleSortComparisons = 0;
    static int quickSortSwaps = 0;
    static int quickSortComparisons = 0;
    static int countSortSwaps = 0;
    static int countSortComparisons = 0;

    public ArrayToSortGUI() {
        setTitle("Sorting Algorithm Comparison");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Random random = new Random();
        String[] statueTypes = {"Sorted", "Unsorted", "Reversed"};
        int size = 5; // Default size of the array

        for (String type : statueTypes) {
            int[] generatedArray = new int[size];

            switch (type) {
                case "Sorted":
                    for (int i = 0; i < size; i++) {
                        generatedArray[i] = random.nextInt(size) + 1;
                    }
                    Arrays.sort(generatedArray);
                    break;
                case "Unsorted":
                    for (int i = 0; i < size; i++) {
                        generatedArray[i] = random.nextInt(100) + 1;
                    }
                    break;
                case "Reversed":
                    for (int i = 0; i < size; i++) {
                        generatedArray[i] = random.nextInt(size) + 1;
                    }
                    Arrays.sort(generatedArray);
                    for (int i = 0; i < size / 2; i++) {
                        int temp = generatedArray[i];
                        generatedArray[i] = generatedArray[size - i - 1];
                        generatedArray[size - i - 1] = temp;
                    }
                    break;
            }

            long bubbleSortTime, quickSortTime, countSortTime;

            bubbleSortSwaps = 0;
            bubbleSortComparisons = 0;
            long startTime = System.nanoTime();
            int[] bubbleSortArr = Arrays.copyOf(generatedArray, generatedArray.length);
            bubbleSort(bubbleSortArr);
            long endTime = System.nanoTime();
            bubbleSortTime = endTime - startTime;

            quickSortSwaps = 0;
            quickSortComparisons = 0;
            startTime = System.nanoTime();
            int[] quickSortArr = Arrays.copyOf(generatedArray, generatedArray.length);
            quickSort(quickSortArr, 0, quickSortArr.length - 1);
            endTime = System.nanoTime();
            quickSortTime = endTime - startTime;

            countSortSwaps = 0;
            countSortComparisons = 0;
            startTime = System.nanoTime();
            int[] countSortArr = Arrays.copyOf(generatedArray, generatedArray.length);
            countSort(countSortArr);
            endTime = System.nanoTime();
            countSortTime = endTime - startTime;

            dataset.addValue(bubbleSortTime, "Bubble Sort", type);
            dataset.addValue(quickSortTime, "Quick Sort", type);
            dataset.addValue(countSortTime, "Count Sort", type);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Sorting Algorithm Comparison",
                "Array Type",
                "Time (nanoseconds)",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 500));
        setContentPane(chartPanel);
    }

   public static void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            bubbleSortComparisons++;
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                bubbleSortSwaps++;

                System.out.println("Bubble Sort: " + Arrays.toString(arr));
            }
        }
    }
}

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            quickSortComparisons++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                quickSortSwaps++;
                System.out.println("Quick Sort: " + Arrays.toString(arr));
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        quickSortSwaps++;
        System.out.println("Quick Sort: " + Arrays.toString(arr));

        return i + 1;
    }
  // Count Sort (with swap count and comparison count)
   public static void countSort(int[] arr) {
    int max = Arrays.stream(arr).max().orElse(0);
    int min = Arrays.stream(arr).min().orElse(0);
    int range = max - min + 1;

    int[] count = new int[range];
    int[] output = new int[arr.length];

    for (int value : arr) {
        count[value - min]++;
    }

    for (int i = 1; i < count.length; i++) {
        count[i] += count[i - 1];
    }

    for (int i = arr.length - 1; i >= 0; i--) {
        output[count[arr[i] - min] - 1] = arr[i];
        count[arr[i] - min]--;
    }

    System.arraycopy(output, 0, arr, 0, arr.length);
   }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayToSortGUI example = new ArrayToSortGUI();
            example.setVisible(true);
        });
   
    Random random = new Random();

        String[] statueTypes = {"Sorted", "Unsorted", "Reversed"};
        int  size = 5; // Default size of the array

        for (String type : statueTypes) {
            int[] generatedArray = new int[size];

// Generate arrays based on type
            switch (type) {
                case "Sorted":
                    for (int i = 0; i < size; i++) {
                        generatedArray[i] = random.nextInt(size) + 1; // Generate random numbers between 1 and size
                    }
                    Arrays.sort(generatedArray);
                    break;
                case "Unsorted":
                    for (int i = 0; i < size; i++) {
                        generatedArray[i] = random.nextInt(100) + 1; // Generate random numbers between 1 and 100
                    }
                    break;
                case "Reversed":
                    for (int i = 0; i < size; i++) {
                        generatedArray[i] = random.nextInt(size) + 1; // Generate random numbers between 1 and size
                    }
                    Arrays.sort(generatedArray);
                    for (int i = 0; i < size / 2; i++) {
                        int temp = generatedArray[i];
                        generatedArray[i] = generatedArray[size - i - 1];
                        generatedArray[size - i - 1] = temp;
                    }
                    break;
            }

            System.out.println("Statue Type: " + type);
            System.out.println("Before Sorting: " + Arrays.toString(generatedArray));

            long startTime, endTime;
            bubbleSortSwaps = 0;
            bubbleSortComparisons = 0;
            startTime = System.nanoTime();
            int[] bubbleSortArr = Arrays.copyOf(generatedArray, generatedArray.length);
            bubbleSort(bubbleSortArr);
            endTime = System.nanoTime();
            System.out.println("Bubble Sort Time: " + (endTime - startTime) + " nanoseconds");
            System.out.println("Number of Interchanges - Bubble Sort: " + bubbleSortSwaps);
            System.out.println("Number of Comparisons - Bubble Sort: " + bubbleSortComparisons);

            // Quick Sort
            quickSortSwaps = 0;
            quickSortComparisons = 0;
            startTime = System.nanoTime();
            int[] quickSortArr = Arrays.copyOf(generatedArray, generatedArray.length);
            quickSort(quickSortArr, 0, quickSortArr.length - 1);
            endTime = System.nanoTime();
            System.out.println("Quick Sort Time: " + (endTime - startTime) + " nanoseconds");
            System.out.println("Number of Interchanges - Quick Sort: " + quickSortSwaps);
            System.out.println("Number of Comparisons - Quick Sort: " + quickSortComparisons);

            // Count Sort
            countSortSwaps = 0;
            countSortComparisons = 0;
            startTime = System.nanoTime();
            int[] countSortArr = Arrays.copyOf(generatedArray, generatedArray.length);
            countSort(countSortArr);
            endTime = System.nanoTime();
            System.out.println("Count Sort Time: " + (endTime - startTime) + " nanoseconds");
            System.out.println("Number of Interchanges - Count Sort: " + countSortSwaps);
            System.out.println("Number of Comparisons - Count Sort: " + countSortComparisons);

            System.out.println();
        
    
    }
    
    }
}