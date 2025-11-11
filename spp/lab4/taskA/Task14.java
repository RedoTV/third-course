package lab4.taskA;

//* Task A
//* 14. Исключить из строки группы символов, расположенные между символами «/*»,
//*    «*/» включая границы. Предполагается, что нет вложенных скобок.
public class Task14 {
    public static void main(String[] args) {
        String input = "Это /* комментарий */ код /* еще */ конец";
        System.out.println("Вход: " + input);
        System.out.println("Результат: " + removeComments(input));
    }
    
    public static String removeComments(String str) {
        StringBuilder result = new StringBuilder(str);
        
        while (true) {
            int startIndex = -1;
            int endIndex = -1;
            
            for (int i = 0; i < result.length() - 1; i++) {
                if (result.charAt(i) == '/' && result.charAt(i + 1) == '*') {
                    startIndex = i;
                    break;
                }
            }
            
            if (startIndex == -1) {
                break;
            }
            
            for (int i = startIndex + 2; i < result.length() - 1; i++) {
                if (result.charAt(i) == '*' && result.charAt(i + 1) == '/') {
                    endIndex = i + 2;
                    break;
                }
            }
            
            if (endIndex != -1) {
                result.delete(startIndex, endIndex);
            } else {
                break;
            }
        }
        
        return result.toString().trim();
    }
}