package second.linkedlist;

import java.util.function.LongUnaryOperator;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/5/29 10:02
 */
public class LinkedListTest {

}

class Solution {

    /*
     * @Description  203. 移除链表元素
     * @author   Edison
     * @date    2023/5/30 9:48
     * @Param   [head, val]
     * @return  ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1, head);
        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                continue;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }

    /*
     * @Description  206. 反转链表
     * @author   Edison
     * @date    2023/5/30 10:25
     * @Param   [head]
     * @return  second.linkedlist.ListNode
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    /*
     * @Description  24. 两两交换链表中的节点
     * @author   Edison
     * @date    2023/5/30 10:35
     * @Param   [head]
     * @return  second.linkedlist.ListNode
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode temp1 = cur.next;
            ListNode temp2 = cur.next.next;
            ListNode temp3 = cur.next.next.next;
            cur.next = temp2;
            temp2.next = temp1;
            temp1.next = temp3;
            cur = cur.next.next;
        }
        return dummy.next;
    }

    /*
     * @Description  19. 删除链表的倒数第 N 个结点
     * @author   Edison
     * @date    2023/5/30 10:43
     * @Param   [head, n]
     * @return  second.linkedlist.ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (n-- >= 0 && fast != null) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /*
     * @Description  TDOO
     * @author   Edison
     * @date    2023/5/30 10:51
     * @Param   [headA, headB]
     * @return  second.linkedlist.ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aHead = headA;
        ListNode bHead = headB;
        int lenA = 0;
        int lenB = 0;
        while (aHead != null) {
            lenA++;
            aHead = aHead.next;
        }
        while (bHead != null) {
            lenB++;
            bHead = bHead.next;
        }
        aHead = headA;
        bHead = headB;
        int len = lenA - lenB;
        if (len < 0) {
            len = -len;
            ListNode temp = aHead;
            aHead = bHead;
            bHead = temp;
        }
        while (len-- > 0) {
            aHead = aHead.next;
        }
        while (aHead != null) {
            if (aHead == bHead) {
                return aHead;
            }
            aHead = aHead.next;
            bHead = bHead.next;
        }
        return null;
    }

    /*
     * @Description  142.环形链表II
     * @author   Edison
     * @date    2023/5/30 11:08
     * @Param   [head]
     * @return  second.linkedlist.ListNode
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode index1 = slow;
                ListNode index2 = head;
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

class MyLinkedList {

    int size;
    ListNode head;

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
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        ListNode toAdd = new ListNode(val);
        toAdd.next = cur.next;
        cur.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        if (index == 0) {
            head = head.next;
            return;
        }
        ListNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
    }
}
