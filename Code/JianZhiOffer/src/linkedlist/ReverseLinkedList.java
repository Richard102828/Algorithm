package linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 反转链表，两个两个的反转
 * 例子：123456 -> 214365
 *
 * 解法：使用四个指针，两个指针进行交换的操作，另外两个指针分别保存前一个节点、后一个节点
 *      1. 为什么是四个指针呢？
 *          a. 多设置出来的两个指针
 *              i. 是因为在进行一次两两反转之后，会丢失后面的节点，这样就不能继续进行下去了，所以使用一个指针来保存后面的节点，
 *              使反转能够继续进行下去
 *              ii. 而我们在反转之后还要将节点连接起来，但是我们指针因为要进行下一次反转移到了后面去了，前面的节点就丢失了，
 *              所以要使用一个指针来保存前面的节点，用来连接链表
 */
public class ReverseLinkedList {
    static class Node {
        int value;
        Node next;
    }

    private Node reversNode(Node head) {
        if (head == null || head.next == null)
            return null;
        Node cur = head;
        Node next = head.next;
        Node nextNext = null;
        if (next.next != null)
            nextNext = next.next;
        Node prev = null;
        //考虑边界值：只有两个节点的情况
        if (nextNext == null) {
            next.next = cur;
            cur.next = null;
        }
        while (next != null && nextNext != null) {
            //连接链表
            cur.next = prev;
            prev = cur;
            //反转链表
            next.next = cur;
            //持续
            cur = nextNext;
            next = nextNext.next;
        }
        return next;
    }
}
