package tree;

/**
 * 《剑指Offer》第二版，面试题 28: 对称的二叉树
 *
 *      请实现一个函数，用来判断一颗二叉树是不是对称的。
 *      注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 * 较好的解法：
 *      1. 根据 前序遍历序列 与 对称前序遍历序列 相同，则二叉树为对称的这个规则来思考
 *          a. 注意处理特殊情况，每个节点的数字都相同，但缺少某个节点，导致不对称的情况
 */
public class TwentyEight {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {

    }

    private static boolean isSymmetrical(TreeNode root) {
        return isSymmetricalCore(root, root);
    }

    private static boolean isSymmetricalCore(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        //特殊情况处理
        if (root1 == null || root2 == null)
            return false;
        if (root1.value != root2.value)
            return false;
        return isSymmetricalCore(root1.left, root2.right)
                && isSymmetricalCore(root1.right, root2.left);
    }
}
