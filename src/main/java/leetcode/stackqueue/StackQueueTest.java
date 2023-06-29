package leetcode.stackqueue;

import org.testng.annotations.Test;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/10 10:36
 */
public class StackQueueTest {

    Solution solution = new Solution();

    @Test
    public void maxSlidingWindowTest() {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] res = new int[nums.length - k + 1];
        res = solution.maxSlidingWindow(nums, k);
        for (int num : res) {
            System.out.print(num + " ");
        }
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
class Solution {

    /*
     * @Description  1019. 链表中的下一个更大节点
     * @author   Edison
     * @date    2023/4/10 10:54
     * @Param   [head]
     * @return  int[]
     */
    public int[] nextLargerNodes(ListNode head) {
//        if (head == null || head.next == null) {
//            return new int[]{0};
//        }
//        List<Integer> list = new ArrayList<>();
//        ListNode slow = head;
//        ListNode fast = slow.next;
//        while (slow != null) {
//            while (fast != null) {
//                if (fast.val > slow.val) {
//                    list.add(fast.val);
//                    break;
//                } else if (fast.next == null) {
//                    list.add(0);
//                }
//                fast = fast.next;
//            }
//            slow = slow.next;
//            fast = slow;
//        }
//        int[] res = new int[list.size()];
//        int k = 0;
//        for (int num : list) {
//            res[k++] = num;
//        }
//        return res;
        if (head == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int size = 0;
        while (head != null) {
            list.add(head.val);
            size++;
            head = head.next;
        }
        int[] res = new int[size];
        Stack<Integer> stack = new Stack<>();
        for (int i = size - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= list.get(i)) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(list.get(i));
        }
        return res;
    }

    /*
     * @Description  20. 有效的括号
     * @author   Edison
     * @date    2023/4/10 14:51
     * @Param   [s]
     * @return  boolean
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == '{' || ch == '(' || ch == '[') {
                stack.push(ch);
            } else if (ch == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (ch == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /*
     * @Description  1047. 删除字符串中的所有相邻重复项
     * @author   Edison
     * @date    2023/4/10 15:05
     * @Param   [s]
     * @return  java.lang.String
     */
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char ch : chars) {
            if (stack.isEmpty() || stack.peek() != ch) {
                stack.push(ch);
            } else {
                stack.pop();
            }
        }
        String str = "";
        while (!stack.isEmpty()) {
            str = stack.pop() + str;
        }
        return str;
    }

    /*
     * @Description  150. 逆波兰表达式求值
     * @author   Edison
     * @date    2023/4/10 15:18
     * @Param   [tokens]
     * @return  int
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            if ("+".equals(str)) {
                stack.push(stack.pop() + stack.pop());
            } else if ("*".equals(str)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("-".equals(str)) {
                stack.push(-stack.pop() + stack.pop());
            } else if ("/".equals(str)) {
                int temp = stack.pop();
                int temp2 = stack.pop();
                stack.push(temp2 / temp);
            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.pop();
    }

    /*
     * @Description  239. 滑动窗口最大值
     * @author   Edison
     * @date    2023/4/10 15:36
     * @Param   [nums, k]
     * @return  int[]
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        int len = nums.length - k + 1;
        int[] res = new int[len];
        int num = 0;
        MyQueue1 queue = new MyQueue1();
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        res[num++] = queue.peek();
        for (int i = k; i < nums.length; i++) {
            queue.pop(nums[i - k]);
            queue.add(nums[i]);
            res[num++] = queue.peek();
        }
        return res;
    }

    /*
     * @Description  347. 前 K 个高频元素
     * @author   Edison
     * @date    2023/4/10 16:09
     * @Param   [nums, k]
     * @return  int[]
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1,pair2)->pair1[1]-pair2[1]);
        for (Map.Entry<Integer, Integer> mp : map.entrySet()) {
            if (pq.size() < k) {
                pq.add(new int[]{mp.getKey(), mp.getValue()});
            } else {
                if (mp.getValue() > pq.peek()[1]) {
                    pq.poll();
                    pq.add(new int[]{mp.getKey(), mp.getValue()});
                }
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll()[0];
        }
        return res;
    }

}

class MyQueue1 {

    Deque<Integer> deque = new LinkedList<>();

    void pop(int val) {
        if (!deque.isEmpty() && deque.peek() == val) {
            deque.poll();
        }
    }

    void add(int val) {
        while (!deque.isEmpty() && val > deque.getLast()) {
            deque.removeLast();
        }
        deque.add(val);
    }

    int peek() {
        return deque.peek();
    }
}

class MyQueue {

    Stack<Integer> stackIn;
    Stack<Integer> stackOut;

    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        if (!stackOut.isEmpty()) {
            return stackOut.pop();
        }
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
        return stackOut.pop();
    }

    public int peek() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    public boolean empty() {
        if (stackIn.isEmpty() && stackOut.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

class MyStack {

    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.offer(x);
        int size = queue.size();
        while (size-- > 1) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {

        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
