package tree;

/**
 * 《剑指Offer》第二版，面试题 55: 二叉树的深度
 *
 *      题目一：输入一棵二叉树，求该树的深度2
 *          从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度
 *      题目二：平衡二叉树
 *          输入一棵二叉树，判断该二叉树是否是平衡二叉树
 *
 * 较好的解法：
 *      题目一：
 *          1. 二叉树的深度是由最长的一条路径来决定的，只要找到最长的路劲即可。
 *              a. 从一个节点出发，找到左右子树中路径长的的一个子树，最长的路劲就是该子树的深度 + 1（加一是因为之前第一个节点我们没有算）
 *      题目二：
 *          1. 使用题目一中的深度函数对每一个节点的平衡因子进行判断，但后面的节点会重复的遍历
 *          2. 题目一中的深度函数，会比较左右子树的深度，这不就是平衡二叉树的判断吗？
 *              a. 我们只需要在节点的平衡因子大于1时返回-1，而小于的时候才返回深度即可
 *          3. 使用后序遍历，并统计深度，这样实现起来其实是从下往上的，所以不会重复的遍历节点
 *
 */
public class FiftyFive {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    /**
     * 第一题
     * @param node
     * @return
     */
    private int getTreeDepth(TreeNode node) {
        //鲁棒性
        if (node == null) {
            return 0;
        }
        int left = 0;
        int right = 0;
        if (node.left != null)
            left = getTreeDepth(node.left);
        if (node.right != null)
            right = getTreeDepth(node.right);
        return (left > right) ? left + 1 : right + 1;
    }

    /**
     * 第二题，第一种方式，深度递归
     * @param node
     * @return
     */
    private boolean isBalanced1(TreeNode node) {
        //程序出口，遍历到了叶子节点
        if (node == null)
            return true;
        int left = 0;
        int right = 0;
        if (node.left != null)
             left = getTreeDepth(node.left);
        if (node.right != null)
            right = getTreeDepth(node.right);
        int diff = Math.abs(left - right);
        if (diff > 1)
            return false;
        return isBalanced1(node.left) && isBalanced1(node.right);
    }

    /**
     * 第二题：第二种方式，处理深度函数的返回值，十分的巧妙
     * @param node
     * @return
     */
    private boolean isBalanced2(TreeNode node) {
        //鲁棒性
        if (node == null)
            return false;
        return getTreeDepth2(node) > 0;
    }

    /**
     * 处理返回值
     * @param node
     * @return
     */
    private int getTreeDepth2(TreeNode node) {
        if (node == null)
            return 0;
        int left = 0;
        int right = 0;
        if (node.left != null)
            left = getTreeDepth2(node.left);
        if (node.right != null)
            right = getTreeDepth2(node.right);
        if (left > 0 && right > 0 && Math.abs(left - right) <= 1)
            return left > right ? left + 1 : right + 1;
        else
            return -1;
    }

    /**
     * 第二题：第三种方式，使用后序遍历
     * @param node
     * @param depth 用来记录深度的，因为java的值传递问题，所以使用数组
     * @return
     */
    private boolean isBalanced3(TreeNode node, int[] depth) {
        if (node == null) {
            depth[0] = 0;
            return true;
        }
        boolean leftIsBalanced = false;
        boolean rightIsBalanced = true;
        if (node.left != null)
             leftIsBalanced = isBalanced3(node.left, depth);
        int leftDepth = depth[0];
        if (node.right != null)
            rightIsBalanced = isBalanced3(node.right, depth);
        int rightDepth = depth[0];
        depth[0] = leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
        if (leftIsBalanced && rightIsBalanced && Math.abs(leftDepth - rightDepth) <= 1)
            return true;
        return false;
    }
}
