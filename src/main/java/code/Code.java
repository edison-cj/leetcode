package code;

import java.util.*;

/**
 * @Author: edison
 * @CreateTime: 2023-08-01  11:26
 * @Description: TODO
 * @Version: 1.0
 */
public class Code {

    Solution solution = new Solution();


}

class Solution {

    /*
     * @description: 4. 寻找两个正序数组的中位数
     * @author: edison 
     * @date: 2023/8/1 11:27
     * @param: [nums1, nums2]
     * @return: double
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, l) + findKth(nums1, 0, nums2, 0, r)) / 2.0;
    }
    int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];
        if (k == 1) return Math.min(nums1[i], nums2[j]);
        int mid1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int mid2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        return mid1 < mid2 ? findKth(nums1, i + k / 2, nums2, j , k - k / 2) : findKth(nums1, i, nums2, j + k / 2, k - k / 2);
    }

    /*
     * @description: 10. 正则表达式匹配
     * @author: edison 
     * @date: 2023/8/1 14:40
     * @param: [s, p]
     * @return: boolean
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 2; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') dp[0][i] = dp[0][i - 2];
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    else dp[i][j] = dp[i][j - 2];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /*
     * @description: 11. 盛最多水的容器
     * @author: edison
     * @date: 2023/8/1 15:11
     * @param: [height]
     * @return: int
     */
    public int maxArea(int[] height) {
        int ans = 0;
        int len = height.length;
        int left = 0, right = len - 1;
        while (left < right) {
            ans = Math.max(ans, (right - left) * Math.min(height[left], height[right]));
            if (height[right] > height[left]) left++;
            else right--;
        }
        return ans;
    }

    /*
     * @description: 22. 括号生成
     * @author: edison 
     * @date: 2023/8/1 15:34
     * @param: [n]
     * @return: List<String>
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        String str = "";
        generate(list, str, n, n);
        return list;
    }
    void generate(List<String> list, String str, int left, int right) {
        if (left == 0 && right == 0) {
            list.add(str);
            return;
        }
        if (left > 0) generate(list, str + "(", left - 1, right);
        if (right > left) generate(list, str + ")", left, right - 1);
    }

    /*
     * @description: 23. 合并 K 个升序链表
     * @author: edison 
     * @date: 2023/8/1 16:00
     * @param: [lists]
     * @return: ListNode
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || lists == null) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (ListNode node : lists) {
            if (node != null) pq.add(node);
        }
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) pq.add(cur.next);
        }
        return dummy.next;
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


