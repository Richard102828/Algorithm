package linkedlist;

/**
 * 《剑指Offer》第二版，面试题 35: 复杂链表的复制
 *
 *      输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点或者为null）
 *      返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 *
 * 较好的解法：(题目较复杂，一个步骤一个步骤的来)
 *      1. 使用空间换时间(空间：O(n)、时间：O(n)，创建一个哈希表，每次复制都向表中存入：<N,N'>（即原始节点与对应的复制节点）
 *         a. 第一步复制链表，并连接。
 *         b. 第二步找复制链表每个节点中的随机节点，因为有哈希表，所以，只需O(1)的时间复杂度就能找到了
 *              i. 如：<A, A'><B, B'>，B节点为A节点的随机节点，则A'的随机节点就是B'
 *      2. 不用辅助空间，实现O(n)的时间复杂度
 *          a. 复制连接：将复制节点连接在原始节点后面: A -> A' -> B -> B'···
 *          b. 找随机节点，A' 的随机节点就在A的随机节点后面
 *          c. 拆分节点，将原始节点与复制的节点拆开即完成复制
 */
public class ThirtyFive {
    static class ComplexNode {
        int value;
        ComplexNode next;
        ComplexNode random;
    }
    public static void main(String[] args) {

    }

    /**
     * 第二种方法
     * @param head
     * @return
     */
    private static ComplexNode copyComplexNode(ComplexNode head) {
        //鲁棒性
        if (head == null)
            return null;
        copyComplexNodeConnectionStep(head);
        copyComplexNodeFindRandomStep(head);
        return copyComplexNodeDivideStep(head);
    }

    /**
     * 第二种方法，复制连接步骤
     */
    private static void copyComplexNodeConnectionStep(ComplexNode node) {
        ComplexNode pTemp = node;
        while (pTemp != null) {
            ComplexNode pClone = new ComplexNode();
            pClone.value = pTemp.value;
            pClone.next = pTemp.next;
            pClone.random = null;
            pTemp = pClone.next;
        }
    }

    /**
     * 第二种方法，找随机节点步骤
     * @param node
     */
    private static void copyComplexNodeFindRandomStep(ComplexNode node) {
        ComplexNode pTemp = node;
        ComplexNode pClone = pTemp.next;
        while (pClone.next != null) {
            pClone.random = pTemp.random.next;
            pTemp = pClone.next;
            pClone = pTemp.next;
        }
    }

    /**
     * 第二种方法，拆分节点步骤
     * @param node
     * @return 返回头部节点
     */
    private static ComplexNode copyComplexNodeDivideStep(ComplexNode node) {
        ComplexNode pTemp = node;
        ComplexNode pClone = pTemp.next;
        while (pTemp != null || pClone != null) {
            pTemp.next = pClone.next;
            pClone.next = pTemp.next;
            pTemp = pTemp.next;
            pClone = pClone.next;
        }
        return node;
    }
}
