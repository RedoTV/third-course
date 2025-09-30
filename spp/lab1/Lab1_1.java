import java.util.Scanner;

/**
 * Лабораторная работа 1 - Типы данных
 * Задание 1: Найти значение (по варианту).
 * Вариант 7: Определите объём куба, если его ребро равно x см.
 */
public class Lab1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Input side: ");
        
        // Проверяем, является ли введенное значение числом (double)
        if (scanner.hasNextDouble()) {
            double x = scanner.nextDouble();
            
            // Если число, проверяем, является ли оно положительным
            if (x > 0) {
                double volume = Math.pow(x, 3);
                System.out.printf("Cube volume: %.6f cm^3%n", volume);
            } else {
                // Если число не положительное, выводим ошибку
                System.out.println("Error: side length must be positive!");
            }
        } else {
            // Если введено не число, выводим ошибку
            System.out.println("Error: invalid input value!");
        }
        
        // Закрываем сканнер после всех операций, чтобы избежать утечек ресурсов
        scanner.close();
    }
}
