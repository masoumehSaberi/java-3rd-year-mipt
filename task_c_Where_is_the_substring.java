import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static int updateIndex(String source, int index, char currentChar, int[] prefix) {
        while (index > 0 && source.charAt(index) != currentChar) {
            index = prefix[index - 1];
        }
        if (source.charAt(index) == currentChar) {
            ++index;
        }
        return index;
    }

    public static String findPatternOccurrences(String pattern, String string) {
        int pLength = pattern.length();
        int sLength = string.length();
        int[] prefix = new int[pLength];
        StringBuilder occurrences = new StringBuilder();
        for (int i = 1; i < pLength; ++i) {
            prefix[i] = updateIndex(pattern, prefix[i - 1], pattern.charAt(i), prefix);
        }
        int j = 0;
        for (int i = 0; i < sLength; ++i) {
            j = updateIndex(pattern, j, string.charAt(i), prefix);
            if (j == pLength) {
                occurrences.append((i - pLength + 1)).append(" ");
                j = prefix[j - 1];
            }
        }
        return occurrences.toString().trim();
    }

    public static void main(String[] args) {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            String pattern = input.readLine();
            String string = input.readLine();
            if (pattern == null || string == null || pattern.isEmpty() || string.isEmpty()) {
                throw new IllegalArgumentException("Pattern and string must be non-empty.");
            }
            String result = findPatternOccurrences(pattern, string);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
