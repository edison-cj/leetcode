package leetcode.linkedlist;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/4 10:37
 */
public class LinkedListTest {

}

class ListNode {

    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {

    /*
     * @Description  203. 移除链表元素
     * @author   Edison
     * @date    2023/4/4 10:39
     * @Param   [head, val]
     * @return  leetcode.linkedlist.ListNode
     */
    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val != val) {
                cur = cur.next;
            } else {
                cur.next = cur.next.next;
            }
        }

        return dummyHead.next;
    }

    /*
     * @Description  206. 反转链表
     * @author   Edison
     * @date    2023/4/4 11:11
     * @Param   [head]
     * @return  leetcode.linkedlist.ListNode
     */
    public ListNode reverseList(ListNode head) {

        //迭代
//        ListNode pre = null;
//        ListNode cur = head;
//        while (cur != null) {
//            ListNode temp = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = temp;
//        }
//        return pre;

        //递归
        return reverse(null, head);
    }
    ListNode reverse(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }
        ListNode temp = cur.next;
        cur.next = pre;
        return reverse(cur, temp);
    }

    /*
     * @Description  24. 两两交换链表中的节点
     * @author   Edison
     * @date    2023/4/4 11:30
     * @Param   [head]
     * @return  leetcode.linkedlist.ListNode
     */
    public ListNode swapPairs(ListNode head) {
        //迭代
//        ListNode dummyHead = new ListNode(-1, head);
//        ListNode cur = dummyHead;
//        ListNode temp;
//        ListNode firstNode;
//        ListNode secondNode;
//        while (cur.next != null && cur.next.next != null) {
//            temp = cur.next.next.next;
//            firstNode = cur.next;
//            secondNode = cur.next.next;
//            cur.next = secondNode;
//            secondNode.next = firstNode;
//            firstNode.next = temp;
//            cur = firstNode;
//        }
//        return dummyHead.next;

        //递归
        return reverseSwapPairs(head);
    }
    ListNode reverseSwapPairs(ListNode cur) {
        if (cur == null || cur.next == null) {
            return cur;
        }
        ListNode next = cur.next;
        ListNode newNode = reverseSwapPairs(next.next);
        next.next = cur;
        cur.next = newNode;
        return next;
    }

    /*
     * @Description  19. 删除链表的倒数第 N 个结点
     * @author   Edison
     * @date    2023/4/4 14:22
     * @Param   [head, n]
     * @return  leetcode.linkedlist.ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummtHead = new ListNode(-1, head);
        ListNode fast = dummtHead;
        ListNode slow = dummtHead;
        while (n >= 0) {
            fast = fast.next;
            n--;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummtHead.next;
    }

    /*
     * @Description  面试题 02.07. 链表相交
     * @author   Edison
     * @date    2023/4/4 14:34
     * @Param   [headA, headB]
     * @return  leetcode.linkedlist.ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode Ahead = headA;
        ListNode Bhead = headB;
        while (Ahead != null) {
            lenA++;
            Ahead = Ahead.next;
        }
        while (Bhead != null) {
            lenB++;
            Bhead = Bhead.next;
        }
        int len = lenA - lenB;
        if (len < 0) {
            ListNode temp = headA;
            headA = headB;
            headB = temp;
            len = -len;
        }
        while (len > 0) {
            len--;
            headA = headA.next;
        }
        while (headA != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    /*
     * @Description  142. 环形链表 II
     * @author   Edison
     * @date    2023/4/4 14:52
     * @Param   [head]
     * @return  leetcode.linkedlist.ListNode
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode index1 = head;
                ListNode index2 = slow;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }



}

class MyLinkedList {

    int size;
    ListNode head; //虚拟头节点

    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;
        ListNode cur = head;
        while (index > 0) {
            index--;
            cur = cur.next;
        }
        ListNode newNode = new ListNode(val);
        ListNode temp = cur.next;
        cur.next = newNode;
        newNode.next = temp;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index > size) {
            return;
        }
        size--;
        if (index == 0) {
            head = head.next;
            return;
        }
        ListNode cur = head;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        cur.next = cur.next.next;
    }
}
