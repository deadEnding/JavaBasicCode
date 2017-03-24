package basic.datastructures;

/**
 * @author: deadend
 * @date: 12:13 PM 3/13/17
 * @version: 1.0
 * @description:
 */


public class BST {

    class TreeNode {
        int val, dup, leftCount;    // 节点值，重复数，左子树节点个数
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.dup = 1;
        }
    }

    private TreeNode root;

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else if (val == root.val) {
            root.dup++;
        } else if (val < root.val) {
            root.leftCount++;
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    /**
     * 将val插入BST
     * @param val
     */
    public void insert(int val) {
        root = insert(root, val);
    }

    private int search(TreeNode root, int val) {
        if (root == null) {
            return 0;
        } else if (val == root.val) {
            return root.leftCount + root.dup;
        } else if (val < root.val) {
            return search(root.left, val);
        } else {
            return root.leftCount + root.dup + search(root.right, val);
        }
    }

    /**
     * 搜索BST中小于等于val的数的个数
     * @param val
     * @return
     */
    public int search(int val) {
        return search(root, val);
    }
}
