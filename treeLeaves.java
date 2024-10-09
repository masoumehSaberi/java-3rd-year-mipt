import java.util.*;

public class Main {
    public static class tNode {
        int value;
        tNode leftNode, rightNode;
        tNode(int value) {
            this.value = value;
            leftNode = rightNode = null;
        }

        public static tNode setNewLeaf(tNode root, int value) {
            if (root == null) {
                return new tNode(value);
            }
            if (value < root.value) {
                root.leftNode = setNewLeaf(root.leftNode, value);
            } else if (value > root.value) {
                root.rightNode = setNewLeaf(root.rightNode, value);
            }
            return root;
        }

    }

    public static void getLeaves(tNode node, List<Integer> leaves) {
        if (node == null) return;

        if (node.leftNode == null && node.rightNode == null) {
            leaves.add(node.value);
        }

        getLeaves(node.leftNode, leaves);
        getLeaves(node.rightNode, leaves);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        tNode tRoot = null;

        while (true) {
            int newNode = input.nextInt();
            if (newNode == 0)
            {  break;}
            tRoot = tNode.setNewLeaf(tRoot, newNode);
        }

        List<Integer> treeLeaves = new ArrayList<>();

        getLeaves(tRoot, treeLeaves);

        Collections.sort(treeLeaves);

        for (int l : treeLeaves) {
            System.out.print(l + " ");
        }
    }
}
