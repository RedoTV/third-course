package lab2.task2_2;

import java.util.Scanner;

/*
    Задание 2: двумерный массив
    Вариант 7: 
        Дана матрица A[n][n]. Расположить на главной диагонали для каждого столбца
        сумму элементов. Сформировать вектор В из строки, в которой элемент на
        главной диагонали имеет максимальной значение. 
        Отсортировать вектор В по убыванию.
    Тип сортировки: Выбором
*/
public class Task2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = readPositiveInt(scanner, "Enter matrix size: ");
        
        int[][] matrix = new int[n][n];
        System.out.println("Enter matrix elements:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        
        System.out.println("\nOriginal matrix:");
        printMatrix(matrix);
        
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += matrix[i][j];
            }
            matrix[j][j] = sum;
        }
        
        System.out.println("\nMatrix after modifying main diagonal:");
        printMatrix(matrix);
        
        int maxRow = 0;
        for (int i = 1; i < n; i++) {
            if (matrix[i][i] > matrix[maxRow][maxRow]) {
                maxRow = i;
            }
        }
        
        int[] vectorB = new int[n];
        for (int j = 0; j < n; j++) {
            vectorB[j] = matrix[maxRow][j];
        }
        
        System.out.println("\nVector B before sorting:");
        printArray(vectorB);
        
        selectionSortDescending(vectorB);
        
        System.out.println("\nVector B after sorting (descending):");
        printArray(vectorB);
        
        scanner.close();
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
    
    private static void selectionSortDescending(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                int temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }
    
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
