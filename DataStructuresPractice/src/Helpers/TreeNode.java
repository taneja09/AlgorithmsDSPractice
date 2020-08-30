package Helpers;

import java.util.*;
/*http://www.geeksforgeeks.org/write-a-c-program-to-delete-a-tree/*/

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
        this.val = 0;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }

    /*
     * the number of nodes along the longest path from the root node down to the
     * farthest leaf node. max depth and height of a tree are same
     */
    public static int height(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(height(root.left), height(root.right));
    }

    /*
     * This function traverses tree in post order to to delete each and every node
     * of the tree
     */
    public static void deleteTree(TreeNode node) {
        if (node == null)
            return;

        /* first delete both subtrees */
        deleteTree(node.left);
        deleteTree(node.right);

        /* then delete the node */
        System.out.println("The deleted node is " + node.val);
        node = null;
    }

    public static void print(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + ",");
        print(root.left);
        print(root.right);
    }

    public static TreeNode constructCompleteBinaryTree(int[] arr) {
        TreeNode root = null;
        return constructCompleteBinaryTreeHelper(arr, root, 0);
    }

    public static TreeNode constructCompleteBinaryTreeHelper(int[] arr, TreeNode root, int i) {
        // Base case for recursion
        if (i < arr.length) {
            TreeNode temp = new TreeNode(arr[i]);
            if (arr[i] == -1) {
                temp = null;
            }

            root = temp;

            if (root != null) {
                // insert left child
                root.left = constructCompleteBinaryTreeHelper(arr, root.left, 2 * i + 1);

                // insert right child
                root.right = constructCompleteBinaryTreeHelper(arr, root.right, 2 * i + 2);
            }

        }
        return root;
    }

    // To insert val in BST, returns address of root node
    public static TreeNode insertBST(TreeNode root, char input) {
        // empty tree
        if (root == null) {
            TreeNode newTreeNode = new TreeNode(input);
            root = newTreeNode;
        } else {
            // if val to be inserted is lesser, insert in left subtree.
            if (input <= root.val)
                root.left = insertBST(root.left, input);
                // else, insert in right subtree.
            else
                root.right = insertBST(root.right, input);
        }
        return root;
    }

    public static TreeNode constructBstHelper(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }
        if (data <= root.val)
            root.left = constructBstHelper(root.left, data);
        else
            root.right = constructBstHelper(root.right, data);
        return root;
    }

    public static TreeNode constructBst(int arr[]) {
        int n = arr.length;
        if (n == 0)
            return null;
        TreeNode root = null;

        for (int i = 0; i < n; i++)
            root = constructBstHelper(root, arr[i]);

        return root;
    }

    public static TreeNode stringToTreeNode(String input) {
//		input = input.trim();
//		input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    /******** Functions to create a random binary search tree *********/

    public static int RANDOM_RANGE = 1000;

    public static TreeNode createRandomBST(int numNodes) {
        RANDOM_RANGE = numNodes;

        TreeNode root = new TreeNode();
        root.val = (int) (Math.random() * RANDOM_RANGE);

        int treeSize = countNodes(root);
        while (treeSize < numNodes) {
            int count = numNodes - treeSize;
            while (count-- > 0)
                root.insertInteger((int) (Math.random() * RANDOM_RANGE));
            treeSize = countNodes(root);
        }
        printNode(root);
        return root;
    }

    // Inserts a given number into the BST
    public void insertInteger(int data) {
        if (this.val == data)
            return;
        if (this.val < data) {
            if (this.right == null) {
                this.right = new TreeNode();
                this.right.val = data;
            } else {
                this.right.insertInteger(data);
            }
        } else {
            if (this.left == null) {
                this.left = new TreeNode();
                this.left.val = data;
            } else {
                this.left.insertInteger(data);
            }
        }
    }

    public static int countNodes(TreeNode n) {
        if (n == null)
            return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }


    /************
     * Print the tree like a tree
     ********************/
    public static <T extends Comparable<?>> void printNode(TreeNode root) {
        int maxLevel = maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    public static <T extends Comparable<?>> void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<TreeNode>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    public static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    public static <T extends Comparable<?>> int maxLevel(TreeNode node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    public static <T> boolean isAllElementsNull(List<TreeNode> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
