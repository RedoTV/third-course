package lab2.task2_1;

import java.util.Scanner;

/*
    Задание 1: одномерный массив
    Вариант 7: 
        Дан массив A[n]. Найти произведение элементов, расположенных между
        первым по порядку минимальным и последним по порядку максимальным
        элементами массива.
*/
public class Task2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = readPositiveInt(scanner, "Enter array size: ");
        
        int[] arr = new int[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = readArrayElement(scanner, i);
        }
        
        int minIndex = findFirstMinIndex(arr);
        int maxIndex = findLastMaxIndex(arr);
        
        int startIndex = minIndex;
        int endIndex = maxIndex;
        if (minIndex > maxIndex) {
            startIndex = maxIndex;
            endIndex = minIndex;
        }
        
        if (endIndex - startIndex <= 1) {
            System.out.println("No elements between min and max");
        } else {
            int product = 1;
            for (int i = startIndex + 1; i < endIndex; i++) {
                product *= arr[i];
            }
            System.out.println("Product of elements between min and max: " + product);
        }
    }
    
    private static int readPositiveInt(Scanner scanner, String message) {
        int value;
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value > 0) {
                    return value;
                }
            } else {
                scanner.next();
            }
            System.out.println("Error! Enter a positive integer.");
        }
    }

    private static int readArrayElement(Scanner scanner, int index) {
        int value;
        while (true) {
            System.out.print("A[" + index + "]=");
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                return value;
            } else {
                scanner.next();
                System.out.println("Error! Enter a valid integer.");
            }
        }
    }
    
    private static int findFirstMinIndex(int[] arr) {
        int minIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    private static int findLastMaxIndex(int[] arr) {
        int maxIndex = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
