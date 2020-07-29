package linkedlist;

/**
 * 《剑指Offer》第二版，面试题 23: 链表中环的入口节点
 *
 *      一个链表中包含环，请找出该链表的环的入口结点。
 *
 * 较好的解法：
 *      1. 双指针法，关于这个题目，要依次解决三个问题
 *          a. 输入的链表是否有环？
 *              i. 两个指针一快一慢，如果快的追上了慢的，则有环
 *          b. 如何确定链表中环的节点个数？
 *              ii. 上个问题中有相遇的节点，设置计数器，然后再走一个环，回到这个节点，就找到了
 *          c. 如何确定入口节点？
 *              iii. 一个指针先走环的节点数的节点，另一个指针在开始走，速度相同，当两个节点相遇，则相遇的节点为环的入口节点
 *
 */
public class TwentyThree {

    static class Node {
        String value;
        Node next;
    }

    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        node1.value = "node1";
        node1.next = node2;
        node2.value = "node2";
        node2.next = node1;
        node3.value = "node3";
        node3.next = node4;
        node4.value = "node4";
        node4.next = node5;
        node5.value = "node5";
        node5.next = node6;
        node6.value = "node6";
        node6.next = null;
        System.out.println(entryNodeOfLoop(node1).value);
    }

    /**
     * 从头节点开始
     * @param head
     * @return
     */
    private static Node entryNodeOfLoop(Node head) {
        //鲁棒性
        if (head == null)
            return null;
        Node meetingNode = findMeetingNode(head);
        int loopCount = 0;
        Node temp = meetingNode;
        //出来在加个一
        while (temp.next != meetingNode) {
            if (temp.next != null) {
                temp = temp.next;
                loopCount++;
            } else
                return null;
        }
        loopCount++;

        Node node1 = head;
        Node node2 = null;
        for (int i = 0; i < loopCount; i++) {
            node1 = node1.next;
        }
        node2 = head;
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }

    /**
     * 确定相遇的节点，以便找到环的数量
     * @param head
     * @return 返回相遇的节点，没有则返回null
     */
    private static Node findMeetingNode(Node head) {
        Node pFast = head;
        Node pSlow = head;
        while (pFast != pSlow) {
            if (pFast.next != null && pSlow.next != null ) {
                pFast = pFast.next;
                if (pFast != null)
                    pFast = pFast.next;
                pSlow = pSlow.next;
            } else
                return null;
        }
        return pFast;
    }
}
