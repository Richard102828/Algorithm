package tree;

/**
 * 《剑指Offer》第二版，面试题 26：树的子结构
 *
 *      输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *          树节点结构如下：
 *          static class TreeNode {
 *              double value;   //注意这里的value值是小数，对小数的比较要做好处理
 *              TreeNode left;
 *              TreeNode right;
 *          }
 *
 * 较好的解法：
 *      1. 使用递归的方式
 *          a. 从树B的根节点开始，在树A中找到一个与根节点值相同的，就遍历树A的左右子树
 *          b. 没有找到与B根节点相同的，就继续往下找，没有则没有，找到了又重复a
 *      2. 注意对小数的处理，因为计算机表示小数（float、double）都有误差，所以我们要自己处理
 *      3. 鲁棒性做好判断，树的操作很多指针
 */
public class TwentySix {

    /**
     * 树节点结构
     */
    static class TreeNode {
        double value;
        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {

    }

    /**
     * 使用递归的方式来判断A中是否有与B根节点值相等的节点，并进行处理
     * @param tree1
     * @param tree2
     * @return
     */
    private static boolean hasSubTree(TreeNode tree1, TreeNode tree2) {
        //鲁棒性
        if (tree1 == null)
            return false;
        if (tree2 == null)
            return false;
        //算法
        boolean result = false;
        if (equal(tree1.value, tree2.value))
            result = isSameTreeConstruct(tree1, tree2);
        if (!result)
            result = hasSubTree(tree1.left, tree2);
        if (!result)
            result = hasSubTree(tree1.right, tree2);
        return result;
    }

    private static boolean isSameTreeConstruct(TreeNode treeNode1, TreeNode treeNode2) {
        //树B中的叶子节点，则说明相同
        if (treeNode2 == null)
            return true;
        //树A中的叶子节点，则说明不相同
        if (treeNode1 == null)
            return false;
        boolean result = false;
        if (equal(treeNode1.value, treeNode2.value))
            result = isSameTreeConstruct(treeNode1.left, treeNode2.left)
                    && isSameTreeConstruct(treeNode1.right, treeNode2.right);
        else
            result = false;
        return result;
    }

    /**
     * 处理小数
     * @param num1
     * @param num2
     * @return
     */
    private static boolean equal(double num1, double num2) {
        if (num1 - num2 > -0.0000001 && num2 - num1 < 0.0000001)
            return true;
        else
            return false;
    }
}
