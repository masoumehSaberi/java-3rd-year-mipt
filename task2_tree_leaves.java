import java.util.*;

public class Main {

    public static class TreeNode {
        int value;
        TreeNode leftChild, rightChild;

        TreeNode(int value) {
            this.value = value;
            leftChild = rightChild = null;
        }

        public static TreeNode insert(TreeNode root, int value) {
            if (root == null) {
                return new TreeNode(value);
            }
            if (value < root.value) {
                root.leftChild = insert(root.leftChild, value);
            } else if (value > root.value) {
                root.rightChild = insert(root.rightChild, value);
            }
            return root;
        }
    }

    public static void collectLeaves(TreeNode node, List<Integer> leaves) {
        if (node == null) return;

        if (node.leftChild == null && node.rightChild == null) {
            leaves.add(node.value);
        }

        collectLeaves(node.leftChild, leaves);
        collectLeaves(node.rightChild, leaves);
    }


    public static List<Integer> readInput() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> inputValues = new ArrayList<>();

        while (true) {
            try {
                int value = scanner.nextInt();
                if (value == 0){ break;}
                inputValues.add(value);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                inputValues.clear();
                break;
            }
        }
        return inputValues;
    }


    public static TreeNode buildTree(List<Integer> values) {
        TreeNode root = null;
        for (int value : values) {
            root = TreeNode.insert(root, value);
        }
        return root;
    }

    public static void printLeaves(List<Integer> leaves) {
        for (int leaf : leaves) {
            System.out.print(leaf + " ");
        }
    }


    public static void main(String[] args) {
        List<Integer> inputValues = readInput();

        TreeNode root = buildTree(inputValues);

        List<Integer> leaves = new ArrayList<>();
        collectLeaves(root, leaves);

        Collections.sort(leaves);

        printLeaves(leaves);
    }
}
