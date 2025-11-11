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
            String cleanedWord = cleanWord(words[i]);
            
            if (isPalindrome(cleanedWord) && cleanedWord.length() > maxPalindrome.length()) {
                maxPalindrome = cleanedWord;
            }
        }
        
        return maxPalindrome;
    }
    
    private static boolean isPalindrome(String word) {
        int length = word.length();
        
        for (int i = 0; i < length / 2; i++) {
            if (word.charAt(i) != word.charAt(length - 1 - i)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static String cleanWord(String word) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            
            if ((ch >= 'а' && ch <= 'я') || 
                (ch >= 'А' && ch <= 'Я') ||
                (ch >= 'a' && ch <= 'z') ||
                (ch >= 'A' && ch <= 'Z') ||
                (ch >= '0' && ch <= '9')) {
                result.append(ch);
            }
        }
        
        return result.toString();
    }
}
