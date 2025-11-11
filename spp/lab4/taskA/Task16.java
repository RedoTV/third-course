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
            StringBuilder word = new StringBuilder(words[i]);
            
            for (int j = word.length() - 1; j >= 0; j--) {
                char ch = word.charAt(j);
                if (ch == '.' || ch == ',' || ch == '!' || ch == '?' || ch == ';' || ch == ':') {
                    word.delete(j, j + 1);
                }
            }
            
            String cleanWord = word.toString();
            int length = cleanWord.length();
            boolean isPalindrome = true;
            
            for (int j = 0; j < length / 2; j++) {
                if (cleanWord.charAt(j) != cleanWord.charAt(length - 1 - j)) {
                    isPalindrome = false;
                    break;
                }
            }
            
            if (isPalindrome && cleanWord.length() > maxPalindrome.length()) {
                maxPalindrome = cleanWord;
            }
        }
        
        return maxPalindrome;
    }
}