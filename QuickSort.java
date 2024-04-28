import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();
    private static boolean repeat = true;

    public static void main(String[] args) {
        while (repeat) {
            try {
                System.out.println("Enter number of elements in the array:");
                int n = sc.nextInt();
                long[] array = new long[n];
                
                for (int i = 0; i < n; i++) {
                    array[i] = rand.nextLong() % (n * 10L); // Generate random long integers
                }
                System.out.println("Array created!");

                System.out.println("Choose pivot: 1. First element 2. Last element 3. Random element");
                int pivotChoice = sc.nextInt();

                long startTime = System.currentTimeMillis();
                quickSort(array, 0, n - 1, pivotChoice);
                long endTime = System.currentTimeMillis();

                System.out.println("\nExecution time for quickSort: " + (endTime - startTime) + "ms");

                System.out.println("Do you want to print the sorted array? 1. Yes 2. No");
                int printChoice = sc.nextInt();
                if (printChoice == 1) {
                    System.out.println("Sorted array:");
                    for (long value : array) {
                        System.out.print(value + " ");
                    }
                    System.out.println(); // Print a newline for better formatting
                }

                System.out.println("\n1. Try again 2. Exit");
                int choice = sc.nextInt();
                repeat = (choice == 1);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.next(); // Clear the buffer
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        sc.close();
    }

    public static void quickSort(long[] array, int low, int high, int pivotChoice) {
        if (low < high) {
            int pivot = partition(array, low, high, pivotChoice);
            quickSort(array, low, pivot - 1, pivotChoice);
            quickSort(array, pivot + 1, high, pivotChoice);
        }
    }

    public static int partition(long[] array, int low, int high, int pivotChoice) {
        long pivot = array[high]; // Default to last element
        int pivotIndex = high;

        switch (pivotChoice) {
            case 1:
                pivot = array[low];
                pivotIndex = low;
                break;
            case 2:
                break; // already set to last element
            case 3:
                pivotIndex = low + rand.nextInt(high - low);
                pivot = array[pivotIndex];
                break;
            default:
                System.out.println("Invalid pivot choice, defaulting to last element.");
                break;
        }

        swap(array, pivotIndex, high);

        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
