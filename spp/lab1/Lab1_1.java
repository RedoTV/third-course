import java.util.Scanner;

/**
 * Лабораторная работа 1 - Типы данных
 * Задание 1: Найти значение (по варианту).
 * Вариант 7: Определите объём куба, если его ребро равно x см.
 */
public class Lab1_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double x;
        while (true) {
            System.out.print("Input side: ");

            if (scanner.hasNextDouble()) {
                x = scanner.nextDouble();
                if (x > 0) {
                    break;
                } else {
                    System.out.println("Error: side length must be positive! Please try again.");
                }
            } else {
                System.out.println("Error: invalid input value! Please try again.");
                scanner.next();
            }
        }

        double volume = Math.pow(x, 3);
        System.out.printf("Cube volume: %.6f cm^3%n", volume);
    }
}
