package linkedlist;

import java.util.LinkedList;

/**
 * 《剑指Offer》第二版，面试题 6: 从尾到头打印链表
 *
 *      输入一个链表的头节点，从尾到头打印链表每个节点的值
 *
 * 较好的解法：
 *      1. 这种从尾到头的打印类似栈的后进先出，可以使用栈来存放，然后顺序打印即可
 *      2. 递归本质上也是一种栈结构，所以可以使用递归来实现
 */
public class Six {

    /**
     * 单链表
     */
    static class Node {
        int value;
        Node next;
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node1.value = 10;
        node2.value = 11;
        node3.value = 12;
        node4.value = 13;
        node5.value = 14;
        printLinkedListByReverseOrder(node1);
        printLinkedListByStack(node1);
    }

    /**
     * 使用递归的方式
     * @param headPointer
     */
    private static void printLinkedListByReverseOrder(Node headPointer) {
        //鲁棒性
        if (headPointer == null) {
            return;
        }

        //算法
        if (headPointer.next != null) {
            printLinkedListByReverseOrder(headPointer.next);
        }
        System.out.println(headPointer.value);
    }

    /**
     * 使用栈的方式，用LinkedList来充当栈
     * @param headPointer
     */
    private static void printLinkedListByStack(Node headPointer) {
        //鲁棒性
        if (headPointer == null) {
            return;
        }

        //算法
        LinkedList<Node> stack = new LinkedList<>();
        while (headPointer != null) {
            stack.push(headPointer);
            headPointer = headPointer.next;
        }

        while (!stack.isEmpty()) {
            headPointer = stack.pop();
            System.out.println(headPointer.value);
        }
    }
}
