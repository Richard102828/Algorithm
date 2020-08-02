package tree;

/**
 * 《剑指Offer》第二版，面试题 33: 二叉搜索树的后序遍历序列
 *
 *      输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true,否则返回false。
 *      假设输入的数组的任意两个数字都互不相同。
 *
 * 较好的解法：
 *      1. 根据二叉搜索树的特点来观察这个后序遍历序列
 *          a. 每棵树的根节点始终是在这颗树的后序遍历序列的末尾
 *          b. 左子树的节点 < 根节点
 *          c. 右子树的节点 > 根节点
 *          d. 满足这些条件，即题目可以返回true了
 */
public class ThirtyThree {

    public static void main(String[] args) {

    }

    private static boolean verifySequenceOfBST(int[] sequence, int length) {
        //鲁棒性
        if (sequence == null || length <= 0)
            return false;
        //算法
        int root = sequence[length - 1];
        //找左子树
        int i = 0;
        for (; i < length - 1; i++) {
            if (sequence[i] > root)
                break;
        }
        //找右子树
        int j = i;
        for (; j < length - 1; j++) {
            if (sequence[j] < root)
                return false;
        }
        boolean left = true;
        if (i > 0)
            left = verifySequenceOfBST(sequence, i);
        boolean right = true;
        if (j < length - 1)
            right = verifySequenceOfBST(sequence, length - i);
        return left && right;
    }
}
