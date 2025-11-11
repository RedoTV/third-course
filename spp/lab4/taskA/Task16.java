package lab4.taskA;

/*
* Task A
*    16. Найти самое длинное симметричное слово заданного предложения.
*/
public class Task16 {
    public static void main(String[] args) {
        String input = "мама, мыла! шалаш. около? потока;";
        System.out.println("Вход: " + input);
        System.out.println("Самое длинное симметричное слово: " + findLongestPalindrome(input));
    }
    
    public static String findLongestPalindrome(String sentence) {
        String[] words = sentence.split(" ");
        String maxPalindrome = "";
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int length = word.length();
            boolean isPalindrome = true;
            
            for (int j = 0; j < length / 2; j++) {
                char left = word.charAt(j);
                char right = word.charAt(length - 1 - j);
                
                if (left == '.' || left == ',' || left == '!' || left == '?' || left == ';' || left == ':') {
                    continue;
                }
                if (right == '.' || right == ',' || right == '!' || right == '?' || right == ';' || right == ':') {
                    continue;
                }
                
                if (left != right) {
                    isPalindrome = false;
                    break;
                }
            }
            
            if (isPalindrome && word.length() > maxPalindrome.length()) {
                maxPalindrome = word;
            }
        }
        
        return maxPalindrome;
    }
}
