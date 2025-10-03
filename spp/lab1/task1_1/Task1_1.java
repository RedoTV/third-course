import java.util.Scanner;

/*
    Задание 1: Найти значение 
    Вариант 7: Определите объём куба, если его ребро равно x см.
*/
public class Task1_1 {
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
                    System.out.println("Error: side length must be positive!");
                }
            } else {
                System.out.println("Error: invalid input value!");
                scanner.next();
            }
        }

        double volume = Math.pow(x, 3);
        System.out.printf("Cube volume: %.6f cm^3%n", volume);
    }
}
