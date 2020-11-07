package tree;

/**
 * 《剑指Offer》第二版，面试题 54: 二叉搜索树的第K大节点
 *
 *      给定一颗二叉搜索树，请找出排名第k的结点
 *
 * 较好的解法：
 *      1. 因为是二叉搜索树，它的中序遍历序列是递增的，得到了中序遍历序列后，很容易就能找到那个节点了
 */
public class FiftyFour {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    class Solution {
        int k = 0;
        int res = 0;
        public int kthLargest(TreeNode root, int k) {
            if (root == null || k < 1)
                return -1;
            this.k = k;
            core(root);
            return res;
        }

        public void core(TreeNode root) {
            if (root == null)
                return;
            core(root.right);
            if (--this.k == 0)
                res = root.value;
            core(root.left);
        }
    }
}
