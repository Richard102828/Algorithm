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

    private TreeNode getKthNode(TreeNode node, int k) {
        //鲁棒性
        if (node == null)
            return null;
        if (node.left != null) {
            getKthNode(node.left, k);
        }
        k--;
        if (node.right != null) {
            getKthNode(node.right, k);
        }
        if (k == 0)
            return node;
        return null;
    }
}
