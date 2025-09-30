import java.util.Scanner;

/**
 * Лабораторная работа 1 - Типы данных
 * Задание 2: Вычислить значение выражения
 * Вариант 7: Кусочно-заданная функция
 * 
 * f1(x) = cos(x + ln(x)) + e^x, при x < a
 * f2(x) = x / sqrt(u - x²), при x ∈ [a;b]  
 * f3(x) = 5x / (u + 3), при x > b
 */
public class Lab1_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Input x: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Error: invalid input value for x!");
                return;
            }
            double x = scanner.nextDouble();
            
            System.out.print("Input a: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Error: invalid input value for a!");
                return;
            }
            double a = scanner.nextDouble();
            
            System.out.print("Input b: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Error: invalid input value for b!");
                return;
            }
            double b = scanner.nextDouble();
            
            System.out.print("Input u: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Error: invalid input value for u!");
                return;
            }
            double u = scanner.nextDouble();
            
            if (a > b) {
                System.out.println("Error: interval start cannot be greater than end (a > b)!");
                return;
            }
            
            double result = 0.0;
            String usedFunction = "";
            boolean validCalculation = true;
            
            if (x < a) {
                if (x <= 0) {
                    System.out.println("Error: for f1(x) x must be > 0 (logarithm domain)!");
                    validCalculation = false;
                } else {
                    result = Math.cos(x + Math.log(x)) + Math.exp(x);
                    usedFunction = "f1(x) = cos(x + ln(x)) + e^x";
                }
                
            } else if (x >= a && x <= b) {
                if (u - x * x <= 0) {
                    System.out.println("Error: for f2(x) u - x² must be > 0 (square root domain)!");
                    validCalculation = false;
                } else {
                    result = x / Math.sqrt(u - x * x);
                    usedFunction = "f2(x) = x / sqrt(u - x²)";
                }
                
            } else {
                if (u + 3 == 0) {
                    System.out.println("Error: for f3(x) u ≠ -3 (division by zero)!");
                    validCalculation = false;
                } else {
                    result = 5 * x / (u + 3);
                    usedFunction = "f3(x) = 5x / (u + 3)";
                }
            }
            
            if (validCalculation) {
                System.out.printf("Used function: %s%n", usedFunction);
                System.out.printf("Result: f(%.3f) = %.6f%n", x, result);
            }
            
        } catch (Exception e) {
            System.out.println("Program execution error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}