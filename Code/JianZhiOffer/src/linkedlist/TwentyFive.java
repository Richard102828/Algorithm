package linkedlist;

/**
 * 《剑指Offer》第二版，面试题 25: 合并两个排序的链表
 *
 *      输入两个单调递增的链表，输出两个链表合成后的链表，合成后的链表仍然是递增排序的。
 *
 * 较好的解法：
 *      1. 这道题目，要看清楚了，不是数组，而是链表！！！要注意链表不能断了，还有就是鲁棒性的处理！！！
 *          a. 递归的方式
 *              i. 因为每次比较的操作都是相同的
 *          b. 使用循环
 *              i. 注意链表不要断
 *
 */
public class TwentyFive {

    //单链表
    static class Node {
        int value;
        Node pNext;
    }

    public static void main(String[] args) {

    }

    /**
     * 使用递归的方式
     * @param node1
     * @param node2
     * @return
     */
    private static Node merge(Node node1, Node node2) {
        //鲁棒性
        if (node1 == null && node2 != null)
            return node2;
        if (node2 == null && node1 != null)
            return node1;
        if (node1 == null && node2 == null)
            return null;
        //算法
        Node mergeNode = null;
        if (node1.value < node2.value) {
            mergeNode = node1;
            mergeNode.pNext = merge(node1.pNext, node2);
        } else {
            mergeNode = node2;
            mergeNode.pNext = merge(node1, node2.pNext);
        }
        return mergeNode;
    }
}
