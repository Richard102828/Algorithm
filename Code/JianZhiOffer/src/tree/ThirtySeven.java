package tree;

/**
 * 《剑指Offer》第二版，面试题 37：序列化二叉树
 *
 *      请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * 较好的解法：
 *      1. 分步骤，根据前序遍历序列。对空指针使用一个字符代替，如："#"
 *          a. 根节点
 *          b. 左子树
 *          c. 右子树
 */
public class ThirtySeven {
    //用作反序列化的数组指针
    static int index = 0;

    static class TreeNode {
        String value;
        TreeNode left;
        TreeNode right;
    }
    public static void main(String[] args) {

    }

    /**
     * 序列化过程，使用StringBuilder来存数据，最后返回String即可
     * @param node
     * @return
     */
    private static String serialize(TreeNode node) {
        //鲁棒性
        if (node == null)
            return null;
        StringBuilder sb = new StringBuilder();
        sb.append(node.value);
        if (node.left != null)
            serialize(node.left);
        else
            sb.append("#");
        if (node.right != null)
            serialize(node.right);
        else
            sb.append("#");
        return sb.toString();
    }

    /**
     * 反序列化过程，将前序遍历序列转为二叉树
     * @param seq
     * @return
     */
    private static TreeNode deSerialize(String[] seq) {
        //鲁棒性
        if (seq == null)
            return null;
        //放在比较"#"前面，因为是"#"的话，还是要往后移指针
        index++;
        if (seq[index] == "#")
            return null;
        TreeNode node = new TreeNode();
        node.value = seq[index];
        node.left = deSerialize(seq);
        node.right = deSerialize(seq);
        return node;
    }
}
