package lab4.taskB;

/*
 * 2. Дан текст и список слов. Найти в тексте все слова, каждое из которых
 * отличается от некоторого слова из списка одной буквой, и исправить такие слова на
 * слова из списка.
*/
public class Task2 {
    public static void main(String[] args) {
        String text = "Привет мир, это тект с ошибкоми. Я люблю програмирование.";
        String wordList = "текст ошибками программирование";
        
        System.out.println("Исходный текст: " + text);
        System.out.println("Список слов: " + wordList);
        System.out.println("Результат: " + correctWords(text, wordList));
    }
    
    public static String correctWords(String text, String wordList) {
        String[] correctWords = wordList.split(" ");
        String[] textWords = text.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < textWords.length; i++) {
            String currentWord = textWords[i];
            String cleanWord = removeEndPunctuation(currentWord);
            String punctuation = currentWord.substring(cleanWord.length());
            
            boolean corrected = false;
            
            for (int j = 0; j < correctWords.length; j++) {
                if (differsByOneChar(cleanWord, correctWords[j])) {
                    result.append(correctWords[j]).append(punctuation);
                    corrected = true;
                    break;
                }
            }
            
            if (!corrected) {
                result.append(currentWord);
            }
            
            if (i < textWords.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    private static boolean differsByOneChar(String word1, String word2) {
        if (word1.length() != word2.length() && 
            word1.length() != word2.length() + 1 && 
            word1.length() + 1 != word2.length()) {
            return false;
        }
        
        if (word1.length() == word2.length()) {
            int differences = 0;
            for (int i = 0; i < word1.length(); i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    differences++;
                    if (differences > 1) {
                        return false;
                    }
                }
            }
            return differences == 1;
        }
        
        String shorter = word1.length() < word2.length() ? word1 : word2;
        String longer = word1.length() < word2.length() ? word2 : word1;
        
        int i = 0;
        int j = 0;
        int differences = 0;
        
        while (i < shorter.length() && j < longer.length()) {
            if (shorter.charAt(i) != longer.charAt(j)) {
                differences++;
                if (differences > 1) {
                    return false;
                }
                j++;
            } else {
                i++;
                j++;
            }
        }
        
        return true;
    }
    
    private static String removeEndPunctuation(String word) {
        int endIndex = word.length();
        
        for (int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (c == '.' || c == ',' || c == '!' || c == '?' || c == ';' || c == ':') {
                endIndex = i;
            } else {
                break;
            }
        }
        
        return word.substring(0, endIndex);
    }
}