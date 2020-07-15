package tree;

/**
 * 《剑指Offer》第二版，面试题 7: 重建二叉树
 *
 *      输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树
 *      假设输入的前序遍历和中序遍历的结果中都不含重复的数字
 *      例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重新构建出二叉树并返回
 *
 * 较好的解法：
 *      1. 这种重建二叉树的规律：去找一个父节点，然后通过这两种序列去找这个父节点的左右子树。
 *      所以使用递归的方式即可。
 *          前序遍历的第一个结点就是树的根结点。在中序遍历里找到这个结点，其左边的结点都是根结点的左子树，
 *          其右边的结点都是根结点的右子树。假如根结点左边有M个结点，那么在前序序列中，
 *          根结点后的M个结点也是属于根结点的左子树的。前序序列中余下的后面的结点自然属于根结点的右子树
 */
public class Seven {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode tree = constructTree(pre, in);
        //前序遍历
        printTreePre(tree);
    }

    private static void printTreePre(TreeNode root) {
        if (root != null) {
            System.out.println(root.value);
        }
        if (root.left != null) {
            System.out.println(root.left.value);
        }
        if (root.right != null) {
            System.out.println(root.right.value);
        }
        printTreePre(root.left);
        printTreePre(root.right);
    }

    private static TreeNode constructTree(int[] pre, int[] in) {
        return constructCore(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    /**
     * 使用递归方式
     * 有问题，函数调用栈溢出
     * @param pre
     * @param preStart
     * @param preEnd
     * @param in
     * @param inStart
     * @param inEnd
     * @return
     */
    private static TreeNode constructCore(int[] pre, int preStart, int preEnd,
                                          int[] in, int inStart, int inEnd) {
        //鲁棒性
        if (pre == null || in == null) {
            return null;
        }

        //算法
        TreeNode root = new TreeNode(pre[0]);
        //对叶子节点进行处理
        if (preStart == preEnd || inStart == inEnd) {
            return root;
        }

        int rootInOrder = inStart;
        while (in[rootInOrder] != root.value && rootInOrder <= inEnd) {
            rootInOrder++;
        }
        int leftLength = rootInOrder - inStart;
        int leftEnd = leftLength + preStart;
        if (leftLength > 0) {
            root.left = constructCore(pre, preStart + 1, leftEnd, in, inStart, rootInOrder - 1);
        }
        if (leftLength < preEnd - preStart) {
            root.right = constructCore(pre, leftEnd + 1, preEnd, in, rootInOrder + 1, inEnd);
        }
        return root;
    }
}