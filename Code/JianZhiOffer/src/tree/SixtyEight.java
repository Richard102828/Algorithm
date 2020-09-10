package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 《剑指Offer》第二版，面试题 68: 树中两个节点的最低公共祖先
 *
 *      输入一棵树的两个结点，返回它们的最低公共祖先。
 *
 * 较好的解法：
 *      1. 题目没有说明是二叉树？二叉搜索树？还是普通的树？
 *          a. 二叉搜索树
 *              i. 根据二叉搜索树的特点，去比较节点的值
 *                  * 比输入节点都大，去该节点的左子树中找
 *                  * 比输入节点都小，去该节点的右子树中找
 *                  * 比输入节点一个大，一个小（即在这两个节点之间），则该节点就是这两个节点的最低公共祖先
 *          b. 普通树，含父指针
 *              i. 由于节点有父指针，这个题可以转换为两个链表求第一个公共节点，从这两个节点开始，往上去找相同的。
 *          c. 普通树
 *              i. 对于最低公共祖先来说，这两个节点肯定在它的子树中，在一个节点的子树中找到这两个节点的话就能判断这个节点是最低公共祖先
 *                  * 如果是从上往下递归的方式，那么可能会有节点被遍历了很多遍
 *                  * 所以，可以从下往上去遍历，并使用链表来保存我们已经遍历的节点，如果该节点的子树中没有找到这两个节点，那么就返回，
 *                  并删除该节点
 *              ii. 改良版：转换为求两个链表的第一个公共节点。从上往下，肯定有两条路径分别含这两个节点的一个，保存这条路径，
 *              然后去找公共节点即可
 */
public class SixtyEight {
    static class Node {
        int value;
        Node left;
        Node right;
    }

    static class NodeP {
        //这里就弄一个父指针，其他指针不管
        NodeP parent;
        int value;
    }

    static class NodeC {
        int value;
        List<NodeC> children;
    }

    /**
     * 二叉搜索树的情况
     * @param root
     * @return
     */
    private Node getLastSameInBST(Node root, Node node1, Node node2) {
        if (root == null || node1 == null || node2 == null)
            return null;
        Node pNode = root;
        while (pNode != null) {
            if (node1.value > root.value && node2.value > root.value)
                pNode = pNode.right;
            else if (node1.value < root.value && node2.value < root.value)
                pNode = pNode.left;
            else
                return pNode;
        }
        return null;
    }

    /**
     * 普通树，树的节点含有父节点的指针
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private NodeP getLastSameInParentPointNode(NodeP root, NodeP node1, NodeP node2) {
        if (root == null || node1 == null || node2 == null
                || node1.parent == null || node2.parent == null)
            return null;
        //问题转换为两个链表找第一个公共节点，由于两个节点处于的树的深度未知是否相同，所以先遍历一遍找差值
        NodeP one = node1;
        NodeP two = node2;
        int oneHigh = 0;
        int twoHigh = 0;
        int diff = 0;
        while (one.parent != null) {
            one = one.parent;
            oneHigh++;
        }
        while (two.parent != null) {
            two = two.parent;
            twoHigh++;
        }
        diff = Math.abs(oneHigh - twoHigh);
        one = node1;
        two = node2;
        //深的那个节点先走
        while (diff != 0) {
            if (oneHigh > twoHigh)
                one = one.parent;
            else
                two = two.parent;
            diff--;
        }
        //一起走
        while (one != two) {
            one = one.parent;
            two = two.parent;
        }
        return one;
    }

    /**
     * 普通树，不含父节点的指针
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private NodeC getLastSameCommon(NodeC root, NodeC node1, NodeC node2) {
        if (root == null || node1 == null || node2 == null)
            return null;
        LinkedList<NodeC> listOne = new LinkedList<>();
        LinkedList<NodeC> listTwo = new LinkedList<>();
        getPathIncludeNode(listOne, root, node1);
        NodeC node = null;
        //从头开始比较
        for (int i = 0; i < listOne.size(); i++) {
            node = listOne.peek();
            if (listOne.poll() == listTwo.poll())
                return node;
        }
        return node;
    }

    private void getPathIncludeNode(LinkedList<NodeC> nodeList, NodeC root, NodeC node) {
        if (root != null) {
            if (root == node)
                return;
            nodeList.add(root);
            for(NodeC child: nodeList) {
                getPathIncludeNode(nodeList, child, node);
            }
            nodeList.removeLast();
        }
    }
}
