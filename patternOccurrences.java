import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static int[] getPrefixArray(String pattern, int pLength) {
        int[] prefix = new int[pLength];
        for (int i = 1; i < pLength; i++) {
            int k = prefix[i - 1];
            while (k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
                k = prefix[k - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(k)) {
                k++;
            }
            prefix[i] = k;
        }
        return prefix;
    }

    public static void getPatternIndex(String pattern, String string, StringBuffer occurrences) {
        int pLength = pattern.length();
        int sLength = string.length();
        int[] prefix = getPrefixArray(pattern, pLength);
        int j = 0;
        for (int i = 0; i < sLength; i++) {
            while (j > 0 && string.charAt(i) != pattern.charAt(j)) {
                j = prefix[j - 1];
            }
            if (string.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pLength) {
                occurrences.append((i - pLength + 1)).append(" ");
                j = prefix[j - 1];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String pattern = input.readLine();
        String string = input.readLine();
        
        StringBuffer occurrences = new StringBuffer();
        getPatternIndex(pattern, string, occurrences);
        
        System.out.print(occurrences);
    }
}
