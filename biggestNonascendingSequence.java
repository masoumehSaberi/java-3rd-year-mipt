import java.util.*;

public class Main {

    public static int findIndex(int size, int[] sequence,
                                   int[] originalArrayIndex, int[] prevIndex, List<Integer> dp){
        int length = 0;
        for (int i = 0; i < size; i++) {
            int l = 0, r = length;
            while (l < r) {
                int mid = (l + r) / 2;
                if (sequence[dp.get(mid)] >= sequence[i]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            if (l < dp.size()) {
                dp.set(l, i);
            } else {
                dp.add(i);
            }
            originalArrayIndex[l] = i;
            if (l > 0) {
                prevIndex[i] = originalArrayIndex[l - 1];
            }
            if (l + 1 > length) {
                length = l + 1;
            }
        }
        return originalArrayIndex[length - 1];
    }
    
    public static void findNonAscIndices(int size, int[] sequence,  Stack<Integer> nonAscIndices){
        List<Integer> dp = new ArrayList<>();
        int[] originalArrayIndex = new int[size];
        int[] prevIndex = new int[size];
        Arrays.fill(prevIndex, -1);

        int currIndex = findIndex(size, sequence, originalArrayIndex, prevIndex, dp);
        while (currIndex != -1) {
            nonAscIndices.push(currIndex + 1);
            currIndex = prevIndex[currIndex];
        }
    }
    
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int[] sequence = new int[size];
        for (int i = 0; i < size; i++) {
            sequence[i] = input.nextInt();
        }

        Stack<Integer> nonAscIndices = new Stack<>();
        findNonAscIndices(size, sequence, nonAscIndices);

        System.out.println(nonAscIndices.size());
        while (!nonAscIndices.isEmpty()) {
            System.out.print(nonAscIndices.pop() + " ");
        }
    }
}
