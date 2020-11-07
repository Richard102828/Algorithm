package tree;

/**
 * 《剑指Offer》第二版，面试题 8：二叉树中序遍历的下一个结点
 *
 *      给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回
 *      注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * 较好的解法：
 *      1. 根据中序遍历的下一个节点为右节点，可以分为两种情况，该节点是否为有右子树
 *          a. 有，则下一个节点为右子树的左节点
 *          b. 没有，则向上找父节点，看该节点是不是父节点的右子树中，直到不是，或者达到根节点时，证明下一个节点就是当前的父节点
 */
public class Eight {

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
    }

    public static void main(String[] args) {
        //构建二叉树，然后传入节点即可
    }

    /**
     * 根据该节点是否为有右子树，分为两种情况
     * @param node
     * @return
     */
    private static TreeNode getNextNode(TreeNode node) {
        //鲁棒性
        if (node == null) {
            return null;
        }

        //算法
        TreeNode nextNode = null;
        TreeNode currentNode = node;
        if (currentNode.right != null) {
            currentNode = currentNode.right;
            while (currentNode.left != null) {
                currentNode = currentNode.left;
            }
            nextNode = currentNode;
        } else if (currentNode.parent != null) {
            while (currentNode.parent != null && currentNode.parent.right == currentNode) {
                currentNode = currentNode.parent;
            }
            nextNode = currentNode.parent;
        }
        return nextNode;
    }

    /**
     * 第二次做题——测试
     */
    private static TreeNode nextNode(TreeNode node) {
        if (node == null)
            return null;
        //右节点是否为空
        TreeNode cur = node;
        if (cur.right != null) {
            cur = cur.right;
            //右子树的左子树是否为空
            while (cur.left != null)
                cur = cur.left;
        } else {
            //向上找父节点，该节点为父节点的右子树，则继续往上找，直到不为右子树
            while (cur.parent != null && cur.parent.left != cur)
                cur = cur.parent;
        }
        return cur;
    }
}