package lab4.taskA;

/*
* Task A
*    16. Найти самое длинное симметричное слово заданного предложения.
*/
public class Task16 {
    public static void main(String[] args) {
        String input = "мама мыла шалаш около потока";
        System.out.println("Вход: " + input);
        System.out.println("Самое длинное симметричное слово: " + findLongestPalindrome(input));
    }
    
    public static String findLongestPalindrome(String sentence) {
        String[] words = sentence.split(" ");
        String longestPalindrome = "";
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            
            boolean isPalindrome = true;
            int len = word.length();
            
            for (int j = 0; j < len / 2; j++) {
                if (word.charAt(j) != word.charAt(len - 1 - j)) {
                    isPalindrome = false;
                    break;
                }
            }
            
            if (isPalindrome && word.length() > longestPalindrome.length()) {
                longestPalindrome = word;
            }
        }
        
        return longestPalindrome;
    }
}