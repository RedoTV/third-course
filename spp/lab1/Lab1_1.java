import java.util.Scanner;

/**
 * Лабораторная работа 1 - Типы данных
 * Задание 1: Найти значение (по варианту).
 * Вариант 7: Определите объём куба, если его ребро равно x см.
 */
public class Lab1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Input side: ");
            
            if (!scanner.hasNextDouble()) {
                System.out.println("Error: invalid input value!");
                return;
            }
            
            double x = scanner.nextDouble();
            
            if (x <= 0) {
                System.out.println("Error: side length must be positive!");
                return;
            }
            
            double volume = Math.pow(x, 3);
            
            System.out.printf("Cube volume: %.6f cm^3%n", volume);
            
        } catch (Exception e) {
            System.out.println("Program execution error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}