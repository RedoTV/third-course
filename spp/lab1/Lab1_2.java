import java.util.Scanner;

/**
 * Лабораторная работа 1 - Типы данных
 * Задание 2: Вычислить значение выражения
 * Вариант 7: Кусочно-заданная функция
 *
 * f1(x) = cos(x + ln(x)) + e^x, при x < a
 * f2(x) = x / sqrt(u - x^2), при x ∈ [a;b]
 * f3(x) = 5x / (u + 3), при x > b
 */
public class Lab1_2 {

    private static double readDouble(Scanner scanner, String varName) {
        System.out.print("Input " + varName + ": ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Error: invalid input value for " + varName + "!");
            scanner.close();
            System.exit(1);
        }
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double x = readDouble(scanner, "x");
        double a = readDouble(scanner, "a");
        double b = readDouble(scanner, "b");
        double u = readDouble(scanner, "u");

        if (a > b) {
            System.out.println("Error: interval start cannot be greater than end (a > b)!");
            scanner.close();
            return;
        }

        double result;
        String usedFunction;

        if (x < a) {
            if (x <= 0) {
                System.out.println("Error: for f1(x) x must be > 0 (logarithm domain)!");
                scanner.close();
                return;
            }
            result = Math.cos(x + Math.log(x)) + Math.exp(x);
            usedFunction = "f1(x) = cos(x + ln(x)) + e^x";

        } else if (x <= b) {
            double underSqrt = u - x * x;
            if (underSqrt <= 0) {
                System.out.println("Error: for f2(x) u - x^2 must be > 0 (square root domain)!");
                scanner.close();
                return;
            }
            result = x / Math.sqrt(underSqrt);
            usedFunction = "f2(x) = x / sqrt(u - x^2)";

        } else {
            if (u + 3 == 0) {
                System.out.println("Error: for f3(x) u != -3 (division by zero)!");
                scanner.close();
                return;
            }
            result = 5 * x / (u + 3);
            usedFunction = "f3(x) = 5x / (u + 3)";
        }

        System.out.printf("Used function: %s%n", usedFunction);
        System.out.printf("Result: f(%.3f) = %.6f%n", x, result);

        scanner.close();
    }
}