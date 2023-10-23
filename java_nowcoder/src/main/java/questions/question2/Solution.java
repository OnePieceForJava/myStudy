package questions.question2;

import questions.ListNode;

/**
 * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)，空间复杂度 O(1)。
 * 例如：
 * 给出的链表为 1→2→3→4→5→NULL, m=2,n=4,
 * 返回 1→4→3→2→5→NULL.
 * 数据范围： 链表长度 0<size≤1000，0<m≤n≤size，
 * 链表中每个节点的值满足 ∣val∣≤1000
 * 要求：时间复杂度 O(n) ，空间复杂度 O(n)
 * 进阶：时间复杂度 O(n)，空间复杂度 O(1)
 */
public class Solution {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        //两者相等则不存在部分链表需要反转
        if(m==n){
            return head;
        }

        ListNode returnNewNode = head;
        ListNode remainingNode = head;//记录最后一个不需要改变的节点
        ListNode reverseNode = null;
        //第一次需要逆转的节点 的next节点是 第n+1个节点
        ListNode firstChangeNode = null;
        int index = 1;
        while (head != null) {
            ListNode nextIterator = head.next;

            if (index < m) {
                remainingNode = head;
            }else if (index <= n) {
                ListNode preNode = reverseNode;
                reverseNode = head;
                reverseNode.next = preNode;
                if (index == m) {
                    firstChangeNode = reverseNode;
                }
                if (index == n) {
                    remainingNode.next = reverseNode;
                    if(nextIterator !=null){
                        firstChangeNode.next = nextIterator;
                    }
                    head = null;//结束循环
                }
            }
            index++;
            head = nextIterator;
        }
        return returnNewNode;
    }
}