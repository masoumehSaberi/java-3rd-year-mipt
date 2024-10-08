mport java.util.Scanner;


public class Main {

    public static int GCD(int a, int b) {
        return (b == 0) ? a : GCD(b, a % b);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        System.out.println(GCD(a, b));
    }
}
