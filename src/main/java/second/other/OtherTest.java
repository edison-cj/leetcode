package second.other;

import com.beust.ah.A;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: edison
 * @CreateTime: 2023-07-12  09:52
 * @Description: 其他题目
 * @Version: 1.0
 */
public class OtherTest {
}

class Solution {

    /*
     * @description: 1365. 有多少小于当前数字的数字
     * @author: edison 
     * @date: 2023/7/12 9:53
     * @param: [nums]
     * @return: int[]
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = Arrays.copyOf(nums, nums.length);
        Arrays.sort(res);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < res.length; i++) {
            if (!map.containsKey(res[i])) {
                map.put(res[i], i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            res[i] = map.get(nums[i]);
        }
        return res;
    }

    /*
     * @description: 941. 有效的山脉数组
     * @author: edison 
     * @date: 2023/7/12 10:01
     * @param: [arr]
     * @return: boolean
     */
    public boolean validMountainArray(int[] arr) {
        int left = 0;
        while (left < arr.length - 1 && arr[left] < arr[left + 1]) {
            left++;
        }
        if (left == 0 || left == arr.length - 1) return false;
        while (left < arr.length - 1 && arr[left] > arr[left + 1]) {
            left++;
        }
        return left == arr.length - 1;
    }

    /*
     * @description: 1207. 独一无二的出现次数
     * @author: edison 
     * @date: 2023/7/12 10:11
     * @param: [arr]
     * @return: boolean
     */
    public boolean uniqueOccurrences(int[] arr) {
        int[] temp = new int[2001];
        for (int num : arr) {
            temp[num + 1000]++;
        }
        boolean[] flag = new boolean[1001];
        for (int num : temp) {
            if (num != 0) {
                if (!flag[num]) flag[num] = true;
                else return false;
            }
        }
        return true;
    }

    /*
     * @description: 283. 移动零
     * @author: edison 
     * @date: 2023/7/12 10:18
     * @param: [nums]
     * @return: void
     */
    public void moveZeroes(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow++] = temp;
            }
        }
    }

    /*
     * @description: 189. 轮转数组
     * @author: edison 
     * @date: 2023/7/12 10:21
     * @param: [nums, k]
     * @return: void
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            int temp = nums[begin];
            nums[begin++] = nums[end];
            nums[end--] = temp;
        }
    }

    /*
     * @description: 724. 寻找数组的中心下标
     * @author: edison 
     * @date: 2023/7/12 10:27
     * @param: [nums]
     * @return: int
     */
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            if (left == sum) return i;
            left += nums[i];
        }
        return -1;
    }

    /*
     * @description: 34. 在排序数组中查找元素的第一个和最后一个位置
     * @author: edison 
     * @date: 2023/7/12 10:31
     * @param: [nums, target]
     * @return: int[]
     */
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right--;
            } else if (nums[mid] < target) {
                left++;
            } else {
                int[] res = new int[2];
                int temp = mid;
                while (mid >= 1 && nums[mid] == nums[mid - 1]) mid--;
                res[0] = mid;
                while (temp < nums.length - 1 && nums[temp] == nums[temp + 1]) temp++;
                res[1] = temp;
                return res;
            }
        }
        return new int[]{-1, -1};
    }

    /*
     * @description: 922. 按奇偶排序数组 II
     * @author: edison 
     * @date: 2023/7/12 10:38
     * @param: [nums]
     * @return: int[]
     */
    public int[] sortArrayByParityII(int[] nums) {
        int index = 1;
        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] % 2 == 1) {
                while (nums[index] % 2 == 1) index += 2;
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
        return nums;
    }

    /*
     * @description: 35. 搜索插入位置
     * @author: edison 
     * @date: 2023/7/12 10:48
     * @param: [nums, target]
     * @return: int
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /*
     * @description: 24. 两两交换链表中的节点
     * @author: edison 
     * @date: 2023/7/12 10:55
     * @param: [head]
     * @return: ListNode
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
     * @description: 234. 回文链表
     * @author: edison 
     * @date: 2023/7/12 11:07
     * @param: [head]
     * @return: boolean
     */
    public boolean isPalindrome(ListNode head) {
        if (head.next == null) return true;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverse(slow.next);
        while (slow != null) {
            if (head.val != slow.val) return false;
            slow = slow.next;
            head = head.next;
        }
        return true;
    }
    ListNode reverse(ListNode cur) {
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /*
     * @description: 143. 重排链表
     * @author: edison 
     * @date: 2023/7/12 11:35
     * @param: [head]
     * @return: void
     */
    public void reorderList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        right = reverse(right);
        slow = head;
        while (right != null) {
            ListNode temp1 = slow.next;
            ListNode temp2 = right.next;
            slow.next = right;
            right.next = temp1;
            right = temp2;
            slow = temp1;
        }
    }

    /*
     * @description: 141. 环形链表
     * @author: edison 
     * @date: 2023/7/12 11:50
     * @param: [head]
     * @return: boolean
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /*
     * @description: 面试题 02.07. 链表相交
     * @author: edison 
     * @date: 2023/7/12 11:55
     * @param: [headA, headB]
     * @return: second.other.ListNode
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
        Ahead = headA;
        Bhead = headB;
        if (len < 0) {
            len = -len;
            ListNode temp = Ahead;
            Ahead = Bhead;
            Bhead = temp;
        }
        while (len-- > 0) {
            Ahead = Ahead.next;
        }
        while (Ahead != null) {
            if (Ahead == Bhead) return Ahead;
            Ahead = Ahead.next;
            Bhead = Bhead.next;
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
