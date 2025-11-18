package lab4.taskA;

/*
* Task A
*   3. Вводится строка. Требуется удалить из нее повторяющиеся символы и все
*   пробелы. Например, если было введено "abc cde def", то должно быть выведено
*   "abcdef"
*/
public class Task3 {
    public static void main(String[] args) {
        String input = "abc cde def";
        System.out.println("Вход: " + input);
        System.out.println("Результат: " + removeRepeatingCharsAndSpaces(input));
    }
    
    public static String removeRepeatingCharsAndSpaces(String str) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            
            if (current == ' ') {
                continue;
            }
            
            boolean found = false;
            for (int j = 0; j < result.length(); j++) {
                if (result.charAt(j) == current) {
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                result.append(current);
            }
        }
        
        return result.toString();
    }
}