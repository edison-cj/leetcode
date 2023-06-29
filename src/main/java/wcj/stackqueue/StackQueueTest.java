package wcj.stackqueue;

import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.StreamSupport;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/3/6 13:31
 */
public class StackQueueTest {

    MyQueue myQueue = new MyQueue();
    Solution solution = new Solution();

    @Test
    public void TestMyQueue() {
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());

    }

    @Test
    public void TestIsValid() {
        String s = "{()}";
        System.out.println(solution.isValid(s));
    }

    @Test
    public void TestRemoveDuplicates() {
        String s = "abbaca";
        System.out.println(solution.removeDuplicates(s));
    }

    @Test
    public void TestEvalRPN() {
        String[] s = {"4","13","5","/","+"};
        System.out.println(solution.evalRPN(s));
    }

    @Test
    public void TestMaxSlidingWindow() {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        nums = solution.maxSlidingWindow(nums,3);
        for (int num : nums) {
            System.out.print(num + " ");
        }
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
        dumpstackIn();
        return stackOut.pop();
    }

    private void dumpstackIn() {
        if (!stackOut.isEmpty()) return;
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }

    public int peek() {
        dumpstackIn();
        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
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

class Solution {

    /*
     * @Description  20. 有效的括号
     * @author   Edison
     * @date    2023/3/6 15:40
     * @Param   [s]
     * @return  boolean
     */
    public boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (ch == '{') {
                deque.push('}');
            } else if (ch == '(') {
                deque.push(')');
            } else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            } else {
                deque.pop();
            }
        }

        return deque.isEmpty();
    }

    /*
     * @Description  1047. 删除字符串中的所有相邻重复项
     * @author   Edison
     * @date    2023/3/6 15:40
     * @Param   [s]
     * @return  java.lang.String
     */
    public String removeDuplicates(String s) {

        ArrayDeque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            if (deque.isEmpty() || deque.peek() != s.charAt(i)) {
                deque.push(s.charAt(i));
            } else {
                deque.pop();
            }
        }
        String str = "";
        while (!deque.isEmpty()) {
            str += deque.pop();
        }
        return str;
    }

    /*
     * @Description  150. 逆波兰表达式求值
     * @author   Edison
     * @date    2023/3/6 16:01
     * @Param
     * @return
     */
    public int evalRPN(String[] tokens) {
        Deque<Integer> deque = new LinkedList<>();
        for (String s : tokens) {
            if (s == "+") {
                deque.push(deque.pop() + deque.pop());
            } else if (s == "*") {
                deque.push(deque.pop() * deque.pop());
            } else if (s == "-") {
                deque.push(-deque.pop() + deque.pop());
            } else if (s == "/") {
                int temp = deque.pop();
                int temp2 = deque.pop();
                deque.push(temp2 / temp);
            } else {
                deque.push(Integer.valueOf(s));
            }
        }
        return deque.pop();
    }

    /*
     * @Description  239. 滑动窗口最大值
     *
     * 给你一个整数数组 nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k个数字。滑动窗口每次只向右移动一位。
        返回 滑动窗口中的最大值 。
        *
        * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
        输出：[3,3,5,5,6,7]
        解释：
        滑动窗口的位置                最大值
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
        1 [3  -1  -3] 5  3  6  7       3
        1  3 [-1  -3  5] 3  6  7       5
        1  3  -1 [-3  5  3] 6  7       5
        1  3  -1  -3 [5  3  6] 7       6
        1  3  -1  -3  5 [3  6  7]      7

     * @author   Edison
     * @date    2023/3/6 16:24
     * @Param
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums.length == 1) {
            return nums;
        }

        int len = nums.length - k + 1;
        int[] res = new int[len];
        int num = 0;
        MyQueue2 myQueue = new MyQueue2();

        for (int i = 0; i < k; i++) {
            myQueue.add(nums[i]);
        }
        res[num++] = myQueue.peek();
        for (int i = k; i < nums.length; i++) {
            myQueue.pop(nums[i - k]);
            myQueue.add(nums[i]);
            res[num++] = myQueue.peek();
        }
        return res;
    }

    /*
     * @Description  347. 前 K 个高频元素
       给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案
     * @author   Edison
     * @date    2023/3/6 17:05
     * @Param   [nums, k]
     * @return  int[]
     */
    public int[] topKFrequent(int[] nums, int k) {

        /*大顶堆
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((pair1, pair2)->pair2[1]-pair1[1]);
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){//大顶堆需要对所有元素进行排序
            queue.add(new int[]{entry.getKey(),entry.getValue()});
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }
        return res;
        */

        //小顶堆
        Map<Integer,Integer> map = new HashMap<>();//key为数组元素值,val为对应出现次数
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //在优先队列中存储二元组(num,cnt),cnt表示元素值num在数组中的出现次数
        //出现次数按从队头到队尾的顺序是从小到大排,出现次数最低的在队头(相当于小顶堆)
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1,pair2)->pair1[1]-pair2[1]);
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){//小顶堆只需要维持k个元素有序
            if(pq.size()<k){//小顶堆元素个数小于k个时直接加
                pq.add(new int[]{entry.getKey(),entry.getValue()});
            }else{
                if(entry.getValue()>pq.peek()[1]){//当前元素出现次数大于小顶堆的根结点(这k个元素中出现次数最少的那个)
                    pq.poll();//弹出队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                    pq.add(new int[]{entry.getKey(),entry.getValue()});
                }
            }
        }
        int[] ans = new int[k];
        for(int i=k-1;i>=0;i--){//依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}

class MyQueue2 {
    Deque<Integer> deque = new LinkedList<>();

    //弹出元素时，比较当前要弹出的数值是否等于队列出口的数值，如果相等则弹出
    //同时判断队列当前是否为空
    void pop(int val) {
        if (!deque.isEmpty() && val == deque.peek()) {
            deque.poll();
        }
    }

    //添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
    //保证队列元素单调递减
    //比如此时队列元素3,1，2将要入队，比1大，所以1弹出，此时队列：3,2
    void add(int val) {
        while (!deque.isEmpty() && val > deque.getLast()) {
            deque.removeLast();
        }
        deque.add(val);
    }

    //队列队顶元素始终为最大值
    int peek() {
        return deque.peek();
    }
}
