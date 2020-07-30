package tree;

/**
 * 《剑指Offer》第二版，面试题 27：二叉树的镜像
 *
 *      请实现一个函数，输入一颗二叉树，该函数输出它的镜像。
 *
 * 较好的解法：
 *      1. 使用递归的方式，前序遍历树，先对根节点的左右子树进行交换，再对左右子树的左右节点进行交换
 *          a. 如果这个树是对称的，则直接返回
 *      2. 使用循环的方式，都一样的
 */
public class TwentySeven {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {

    }

    /**
     * 使用递归的方式
     * @param treeNode
     * @return
     */
    private static TreeNode mirrorRecursively(TreeNode treeNode) {
        //鲁棒性
        if (treeNode == null)
            return null;
        if (treeNode.left == null && treeNode.right == null)
            return null;

        TreeNode mirrorTreeNode = treeNode;
        //处理对称的二叉树
        if (mirrorTreeNode.left.value == mirrorTreeNode.right.value) {
            mirrorTreeNode.left = mirrorRecursively(mirrorTreeNode.left);
            mirrorTreeNode.right = mirrorRecursively(mirrorTreeNode.right);
        } else {
            mirrorTreeNode.left = treeNode.right;
            mirrorTreeNode.right = treeNode.left;
        }
        if (mirrorTreeNode.left != null)
            mirrorTreeNode.left = mirrorRecursively(mirrorTreeNode.left);
        if (mirrorTreeNode.right != null)
            mirrorTreeNode.right = mirrorRecursively(mirrorTreeNode.right);
        return mirrorTreeNode;
    }

    /**
     * 使用循环的方式
     * 只能在原来的参数上修改了
     * 有问题，这样左子树只管了左子树的左子树，一直都是左子树，右子树也是一样的，都缺少了相对子树的修改
     * @param root
     * @return
     */
    private static void mirrorCycle(TreeNode root) {
        //鲁棒性
        if (root == null)
            return;
        if (root.left == null && root.right == null)
            return;
        TreeNode tempRoot = root;
        while (root.left != null || root.right != null) {
            if (root.left.value != root.right.value) {
                TreeNode temp = root.right;
                root.right = root.left;
                root.left = temp;
            }
            root = root.left;
        }

        root = tempRoot;
        while (root.left != null || root.right != null) {
            if (root.left.value != root.right.value) {
                TreeNode temp = root.right;
                root.right = root.left;
                root.left = temp;
            }
            root = root.right;
        }
    }
}
