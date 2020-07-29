package linkedlist;

/**
 * 《剑指Offer》第二版，面试题 24: 反转链表
 *
 *      输入一个链表的头结点，反转链表后，并返回反转链表的头结点。
 *
 * 较好的解法：
 *      1. 用了三个指针，就一个节点的前后节点加上自己，目的是为了防止链表断裂，导致找不到下一个节点
 */
public class TwentyFour {

    static class Node {
        String value;
        Node next;
    }

    public static void main(String[] args) {

    }

    private static Node reverseLinkedList(Node head) {
        //鲁棒性
        if (head == null || head.next == null)
            return null;
        //算法
        Node pPreNode = null;
        Node pNode = head;
        Node pReverseNode = null;
        while (pNode != null) {
            Node next = pNode.next;
            if (next == null)
                pReverseNode = pNode;
            pNode.next = pPreNode;
            pPreNode = pNode;
            pNode = next;
        }
        return pReverseNode;
    }
}
