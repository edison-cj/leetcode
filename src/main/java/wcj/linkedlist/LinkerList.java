package wcj.linkedlist;

import org.testng.annotations.Test;

import java.util.List;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/2/14 14:09
 */
public class LinkerList {

    @Test
    public void TestRemoveElements() {
        System.out.println("203. 移除链表元素");

    }

    @Test
    public void TestMyLinkedList() {
        System.out.println("707. 设计链表");


    }

    @Test
    public void TestSwapPairs() {
        System.out.println("24. 两两交换链表中的节点");
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val; this.next = next;
    }
}
class Solution {

    public ListNode removeElements(ListNode head, int val) {

//         while (head != null && head.val == val) {
//             head = head.next;
//         }
//
//         ListNode cur = head;
//         while (cur.next != null) {
//             while (cur.next != null && cur.next.val == val) {
//                 cur.next = cur.next.next;
//             }
//             cur = cur.next;
//         }
//
//         return head;

        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1,head);

        while (dummy != null) {
            while (dummy.next != null && dummy.next.val == val) {
                dummy.next = dummy.next.next;
            }

            dummy = dummy.next;
        }

        return dummy.next;
    }

    public ListNode reverseList(ListNode head) {
//        ListNode cur = head;
//        ListNode pre = null;
//        ListNode temp = null;
//        while (cur != null) {
//            temp = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = temp;
//        }
//
//        return pre;
        return reverse(null,head);
    }

    private ListNode reverse(ListNode pre, ListNode cur){
        if (cur == null) {
            return pre;
        }
        ListNode temp = cur.next;
        cur.next = pre;

        return reverse(cur,temp);
    }

    /*
     * @Description  24. 两两交换链表中的节点
     * @author   Edison
     * @date    2023/2/22 12:27
     * @Param   [head]
     * @return  wcj.linkedlist.ListNode
     */
    public ListNode swapPairs(ListNode head) {
        ListNode headDummy = new ListNode(0);
        headDummy.next = head;
        ListNode cur = headDummy;
        ListNode temp = null;
        ListNode temp1 = null;
        ListNode temp2 = null;

        while (cur.next != null && cur.next.next != null) {
            temp1 = cur.next;
            temp2 = cur.next.next;
            temp = cur.next.next.next;
            cur.next = temp2;
            temp2.next = temp1;
            temp1.next = temp;
            cur = cur.next.next;

        }
        return headDummy.next;
    }

    /*
     * @Description  24. 两两交换链表中的节点(递归)
     * @author   Edison
     * @date    2023/2/22 13:08
     * @Param   [head]
     * @return  wcj.linkedlist.ListNode
     */
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newNode = swapPairs2(next.next);
        next.next = head;
        head.next = newNode;
        return next;
    }

    /*
     * @Description  19. 删除链表的倒数第 N 个结点
     * @author   Edison
     * @date    2023/2/22 13:28
     * @Param   [head, n]
     * @return  wcj.linkedlist.ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;

        while (n >= 0 && fast != null) {
            fast = fast.next;
            n--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyHead.next;
    }


    /*
     * @Description  160. 相交链表
     * @author   Edison
     * @date    2023/2/22 14:06
     * @Param   [headA, headB]
     * @return  wcj.linkedlist.ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0;
        int lenB = 0;

        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }

        curA = headA;
        curB = headB;
        if (lenB > lenA) {
            int len = lenA;
            lenA = lenB;
            lenB = len;
            ListNode cur = curA;
            curA = curB;
            curB = cur;
        }

        int gap = lenA - lenB;
        while ((gap--) > 0) {
            curA = curA.next;
        }

        while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }

        return null;
    }


    /*
     * @Description  142. 环形链表 II
     * @author   Edison
     * @date    2023/2/22 14:46
     * @Param   [head]
     * @return  wcj.linkedlist.ListNode
     */
    public ListNode detectCycle(ListNode head) {
        ///1判断是否有环
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode index1 = fast;
                ListNode index2 = head;
                while (true) {
                    if (index1 == index2) {
                        return index1;
                    }
                    index1 = index1.next;
                    index2 = index2.next;
                }
            }
        }
        return null;
    }
}


class MyLinkedList {

    int size;

    //虚拟头节点
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
        //包含虚拟头节点，循环index+1次
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }

        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;

        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        ListNode addListNode = new ListNode(val);
        addListNode.next = pre.next;
        pre.next = addListNode;
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

        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
    }
}

class MyDoubleLinkedList {

    public class DoubleListNode{
        int val;
        DoubleListNode pre,next;
        public DoubleListNode(){};
        public DoubleListNode(int val) {
            this.val = val;
        }
    }

    int size;
    DoubleListNode head,tail;


    public MyDoubleLinkedList() {
        size = 0;
        this.head = new DoubleListNode(0);
        this.tail = new DoubleListNode(0);
        head.next = tail;
        tail.pre = head;
    }
    public int get(int index) {

        if (index < 0 || index >= size) {
            return -1;
        }

        DoubleListNode cur = this.head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    public void addAtIndex(int index, int val) {
        if (index >= size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;

        DoubleListNode toAdd = new DoubleListNode(val);
        DoubleListNode cur = this.head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        toAdd.next = cur.next;
        cur.next.pre = toAdd;
        toAdd.pre =cur;
        cur.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index< 0 || index >= size) {
            return;
        }

        DoubleListNode cur = this.head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next.next.pre = cur;
        cur.next = cur.next.next;
    }

}
