package questions.question1;

import questions.ListNode;

/**
 * 反转链表
 * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，反转该链表后，返回新链表的表头。
 * 数据范围： 0≤n≤1000
 * 要求：空间复杂度 O(1) ，时间复杂度 O(n) 。
 * 如当输入链表{1,2,3}时，经反转后，原链表变为{3,2,1}，所以对应的输出为{3,2,1}。
 */
public class Question1 {

    public static void main(String[] args) {
        print(get());
        System.out.println("------------");
        print(reverseList(get()));
        System.out.println("------------");
        print(reverseList1(get()));
    }

    private static ListNode get() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        return listNode1;
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
    }

    /**
     * 该方法是个错误方法
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode newHeadNode = null;
        while (head != null) {
            ListNode preNode = newHeadNode;
            newHeadNode = head;
            //  -->  newHeadNode = head; 这一段导致   newHeadNode,head指向同个一个地址引用
            newHeadNode.next = preNode;
            // preNode = null, newHeadNode.next = null -->head.next = null
            head = head.next;
            //这是head就为null了
        }
        return newHeadNode;
    }

    /**
     * 自己要注意的点：
     * 1.   ListNode newHeadNode = head;
     *      while (head.next != null) {...}
     *      链表的节点要一个一个的遍历。
     *
     * 2、注意是引出传递
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {
        ListNode newHeadNode = null;
        while (head != null) {
            ListNode nextIteratorNode = head.next;
            ListNode preNode = newHeadNode;
            newHeadNode = head;
            newHeadNode.next = preNode;
            head = nextIteratorNode;
        }
        return newHeadNode;
    }
}
