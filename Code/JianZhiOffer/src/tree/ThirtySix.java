package tree;

/**
 * 《剑指Offer》第二版，面试题 36: 二叉搜索树与双向链表
 *
 *      输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 *      要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 较好的解法：
 *      1. 要求最后的结果为排好序的双向链表，就可以想到二叉搜索树的中序遍历得到的序列就是一个排好序的序列
 *          a. 所以使用递归执行中序遍历
 *          b. 多设置一个参数进行传递，因为当前遍历的节点需要与上一个遍历的节点想连接
 */
public class ThirtySix {
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }
    private static TreeNode lastNode;
    public static void main(String[] args) {
        TreeNode no = new TreeNode();
        no.value = 2334;
        t(no);
        System.out.println(no.value);
    }

    private static void t(TreeNode node) {
        TreeNode node1 = new TreeNode();
        node1.value = 23;
        node = node1;
    }

    /**
     * 转换后，返回头节点
     * @param root
     * @return
     */
    private static TreeNode convertTreeToLinkedList(TreeNode root) {
        //鲁棒性
        if (root == null)
            return null;
        convertCore(root);
        TreeNode head = root;
        while (head.left != null)
            head = head.left;
        return head;
    }

    /**
     * 转换并连接：中序遍历
     * @param root
     */
    private static void convertCore(TreeNode root) {
        //递归出口
        if (root == null)
            return;
        if (root.left != null)
            convertCore(root.left);
        root.left = lastNode;
        if (lastNode != null)
            lastNode.right = root;
        lastNode = root;
        if (root.right != null)
            convertCore(root.right);
    }
}
