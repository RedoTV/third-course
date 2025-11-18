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
    
    public static String removeComments(String text) {
        StringBuilder cleanedText = new StringBuilder(text);
        
        while (true) {
            int commentStart = -1;
            int commentEnd = -1;
            
            for (int i = 0; i < cleanedText.length() - 1; i++) {
                if (cleanedText.charAt(i) == '/' && cleanedText.charAt(i + 1) == '*') {
                    commentStart = i;
                    break;
                }
            }
            
            if (commentStart == -1) {
                break;
            }
            
            for (int i = commentStart + 2; i < cleanedText.length() - 1; i++) {
                if (cleanedText.charAt(i) == '*' && cleanedText.charAt(i + 1) == '/') {
                    commentEnd = i + 2;
                    break;
                }
            }

            if (commentEnd != -1) {
                cleanedText.delete(commentStart, commentEnd);
            } else {
                break;
            }
        }
        
        return cleanedText.toString().trim();
    }
}
