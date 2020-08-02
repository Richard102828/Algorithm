package tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 《剑指Offer》第二版，面试题 34: 二叉树中和为某一值的路径
 *
 *      输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 *      路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 *
 * 较好的解法：
 *      1. 找规律
 *          a. 找路径的过程就相当于是前序遍历一样，不同的是，到达叶子结点不能满足时，要返回到父节点
 *              i. 对于二叉树的前序遍历，使用递归即可实现
 *          b. 找路径这个过程中，要记录节点，以便于输出
 *              i. 不满足时，记录的节点要删除，我们可以使用栈来实现
 */
public class ThirtyFour {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {

    }

    /**
     * 规定整数大于0
     * @param node
     * @param sum
     */
    private static void findPath(TreeNode node, int sum) {
        //鲁棒性
        if (node == null || sum <= 0) {
            return;
        }
        LinkedList<Integer> pathStack = new LinkedList<>();
        int currentSum = 0;
        findPathCore(node, pathStack, sum, currentSum);
    }

    private static void findPathCore(TreeNode node, LinkedList<Integer> pathStack,
                                     int sum, int currentSum) {
        pathStack.push(node.value);
        currentSum += node.value;
        //相等，并且为叶子节点
        if (currentSum == sum && node.left == null && node.right == null) {
            System.out.println(Arrays.toString(pathStack.toArray()));
            pathStack.pop();
        }
        if (node.left != null)
            findPathCore(node.left, pathStack, sum, currentSum);
        if (node.right != null)
            findPathCore(node.right, pathStack, sum, currentSum);
        //返回之前，删除节点
        pathStack.pop();
    }
}
