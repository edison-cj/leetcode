package code;

import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.*;

/**
 * @Author: edison
 * @CreateTime: 2023-08-01  11:26
 * @Description: TODO
 * @Version: 1.0
 */
public class Code {

    Solution solution = new Solution();

    @Test
    public void test() {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        int[] a = new int[58];
        int[] b = new int[58];
        for (char ch : t.toCharArray()) {
            a[ch - 'A']++;
        }
        int left = 0, right = 0, count = 0;
        String str = "";
        while (right < s.length()) {
            b[s.charAt(right) - 'A']++;
            if (b[s.charAt(right) - 'A'] <= a[s.charAt(right) - 'A']) count++;
            while (left <= right && b[s.charAt(left) - 'A'] > a[s.charAt(left) - 'A']) {
                b[s.charAt(left) - 'A']--;
                left++;
            }
            if (count == t.length() && (str.isEmpty() || str.length() > right - left + 1)) {
                str = s.substring(left , right + 1);
            }
            right++;
        }
        System.out.println(str);
    }

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

    /*
     * @description: 15. 三数之和
     * @author: edison 
     * @date: 2023/8/2 10:23
     * @param: [nums]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) left++;
                else if (sum > 0) right--;
                else {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return list;
    }

    /*
     * @description: 5. 最长回文子串
     * @author: edison 
     * @date: 2023/8/2 10:31
     * @param: [s]
     * @return: java.lang.String
     */
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int index = 0;
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) dp[i][j] = true;
                    else if (dp[i + 1][j - 1]) dp[i][j] = true;
                }
                if (dp[i][j] && j - i + 1 > len) {
                    index = i;
                    len = j - i + 1;
                }
            }
        }
        return s.substring(index, index + len);
    }

    /*
     * @description: 17. 电话号码的字母组合
     * @author: edison 
     * @date: 2023/8/2 10:58
     * @param: [digits]
     * @return: java.util.List<java.lang.String>
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits.length() == 0 || digits == null) return list;
        String[] str = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTrack(digits, str, list, 0);
        return list;
    }
    StringBuilder sb = new StringBuilder();
    void backTrack(String digits, String[] str, List<String> list, int index) {
        if (index == digits.length()) {
            list.add(sb.toString());
            return;
        }
        String string = str[digits.charAt(index) - '0'];
        for (int i = 0; i < string.length(); i++) {
            sb.append(string.charAt(i));
            backTrack(digits, str, list, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /*
     * @description: 19. 删除链表的倒数第 N 个结点
     * @author: edison 
     * @date: 2023/8/2 11:09
     * @param: [head, n]
     * @return: code.ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (n-- >= 0) fast = fast.next;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    /*
     * @description: 20. 有效的括号
     * @author: edison 
     * @date: 2023/8/2 11:14
     * @param: [s]
     * @return: boolean
     */
    public boolean isValid(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            switch (ch) {
                case ')':
                    if (deque.isEmpty() || deque.pop() != '(') return false;
                    break;
                case ']':
                    if (deque.isEmpty() || deque.pop() != '[') return false;
                    break;
                case '}':
                    if (deque.isEmpty() || deque.pop() != '{') return false;
                    break;
                default: deque.push(ch);
                    break;
            }
        }
        return deque.isEmpty();
    }

    /*
     * @description: 21. 合并两个有序链表
     * @author: edison 
     * @date: 2023/8/2 11:33
     * @param: [list1, list2]
     * @return: code.ListNode
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /*
     * @description: 39. 组合总和
     * @author: edison 
     * @date: 2023/8/2 11:46
     * @param: [candidates, target]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backTrack(list, path, candidates, target, 0);
        return list;
    }
    void backTrack(List<List<Integer>> list, LinkedList<Integer> path, int[] candidates, int target, int index) {
        if (target == 0) {
            list.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) return;
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            backTrack(list, path, candidates, target - candidates[i], i);
            path.removeLast();
        }
    }

    /*
     * @description: 42. 接雨水
     * @author: edison 
     * @date: 2023/8/2 11:53
     * @param: [height]
     * @return: int
     */
    public int trap(int[] height) {
        if (height.length <= 2) return 0;
        int ans = 0;
        Deque<Integer> deque = new LinkedList<>();
        deque.push(0);
        for (int i = 1; i < height.length; i++) {
            if (height[i] < height[deque.peek()]) deque.push(i);
            else if (height[i] == height[deque.peek()]) {
                deque.pop();
                deque.push(i);
            } else {
                while (!deque.isEmpty() && height[i] > height[deque.peek()]) {
                    int mid = deque.pop();
                    if (!deque.isEmpty()) {
                        int h = Math.min(height[deque.peek()], height[i]) - height[mid];
                        int w = i - deque.peek() - 1;
                        ans += h * w;
                    }
                }
                deque.push(i);
            }
        }
        return ans;
    }

    /*
     * @description: 46. 全排列
     * @author: edison
     * @date: 2023/8/2 15:07
     * @param: [nums]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(nums, list, path, used);
        return list;
    }
    void backTrack(int[] nums, List<List<Integer>> list, LinkedList<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            path.add(nums[i]);
            used[i] = true;
            backTrack(nums, list, path, used);
            path.removeLast();
            used[i] = false;
        }
    }

    /*
     * @description: 48. 旋转图像
     * @author: edison
     * @date: 2023/8/2 15:26
     * @param: [matrix]
     * @return: void
     */
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        int left = 0;
        int right = matrix.length - 1;
        while (left < right) {
            for (int i = 0; i < matrix.length; i++) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
            }
            left++;
            right--;
        }
    }

    /*
     * @description: 49. 字母异位词分组
     * @author: edison
     * @date: 2023/8/2 15:42
     * @param: [strs]
     * @return: java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String string = new String(ch);
            List<String> list = map.getOrDefault(string, new ArrayList<>());
            list.add(str);
            map.put(string, list);
        }
        return new ArrayList<>(map.values());
    }

    /*
     * @description: 53. 最大子数组和
     * @author: edison 
     * @date: 2023/8/2 15:53
     * @param: [nums]
     * @return: int
     */
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > ans) ans = dp[i];
        }
        return ans;
    }

    /*
     * @description: 55. 跳跃游戏
     * @author: edison 
     * @date: 2023/8/2 15:58
     * @param: [nums]
     * @return: boolean
     */
    public boolean canJump(int[] nums) {
        int len = 0;
        for (int i = 0; i <= len; i++) {
            int count = i + nums[i];
            len = Math.max(len, count);
            if (len >= nums.length - 1) return true;
        }
        return false;
    }

    /*
     * @description: 56. 合并区间
     * @author: edison 
     * @date: 2023/8/2 16:08
     * @param: [intervals]
     * @return: int[][]
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                list.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        list.add(new int[]{start, end});
        return list.toArray(new int[0][]);
    }

    /*
     * @description: 62. 不同路径
     * @author: edison 
     * @date: 2023/8/2 16:16
     * @param: [m, n]
     * @return: int
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * @description: 64. 最小路径和
     * @author: edison
     * @date: 2023/8/2 16:21
     * @param: [grid]
     * @return: int
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * @description: 70. 爬楼梯
     * @author: edison 
     * @date: 2023/8/2 16:27
     * @param: [n]
     * @return: int
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    /*
     * @description: 75. 颜色分类
     * @author: edison 
     * @date: 2023/8/2 16:38
     * @param: [nums]
     * @return: void
     */
    public void sortColors(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        int t = 0;
        while (i <= j) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i++] = nums[t];
                nums[t++] = temp;
            } else if (nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j--] = temp;
            } else {
                i++;
            }
        }
    }

    /*
     * @description: 76. 最小覆盖子串
     * @author: edison 
     * @date: 2023/8/2 17:35
     * @param: [s, t]
     * @return: java.lang.String
     */
    public String minWindow(String s, String t) {
        int[] a = new int[58];
        int[] b = new int[58];
        int left = 0, right = 0, count = 0;
        String str = "";
        for (char ch : t.toCharArray()) a[ch - 'A']++;
        while (right < s.length()) {
            b[s.charAt(right) - 'A']++;
            if (b[s.charAt(right) - 'A'] <= a[s.charAt(right) - 'A']) count++;
            while (left < right && b[s.charAt(left) - 'A'] > a[s.charAt(left) - 'A']) {
                b[s.charAt(left) - 'A']--;
                left++;
            }
            if (count == t.length() && (str.isEmpty() || str.length() > right - left + 1)) str = s.substring(left, right + 1);
            right++;
        }
        return str;
    }

    /*
     * @description: 977. 有序数组的平方
     * @author: edison 
     * @date: 2023/8/3 9:52
     * @param: [nums]
     * @return: int[]
     */
    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int k = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                ans[k--] = nums[right] * nums[right];
                right--;
            } else {
                ans[k--] = nums[left] * nums[left];
                left++;
            }
        }
        return ans;
    }

    /*
     * @description: 722. 删除注释
     * @author: edison
     * @date: 2023/8/3 10:05
     * @param: [source]
     * @return: java.util.List<java.lang.String>
     */
    public List<String> removeComments(String[] source) {
        List<String> list = new ArrayList<>();
        boolean blockComment = false;
        StringBuilder sb = new StringBuilder();
        for (String s : source) {
            int len = s.length();
            for (int i = 0; i < len; i++) {
                if (blockComment) {
                    if (i + 1 < len && s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                        blockComment = false;
                        i++;
                    }
                } else {
                    if (i + 1 < len && s.charAt(i) == '/' && s.charAt(i + 1) == '*') {
                        blockComment = true;
                        i++;
                    } else if (i + 1 < len && s.charAt(i) == '/' && s.charAt(i + 1) == '/') {
                        break;
                    } else {
                        sb.append(s.charAt(i));
                    }
                }
            }
            if (!blockComment && sb.length() > 0) {
                list.add(sb.toString());
                sb.setLength(0);
            }
        }
        return list;
    }

    /*
     * @description: 79. 单词搜索
     * @author: edison
     * @date: 2023/8/3 10:31
     * @param: [board, word]
     * @return: boolean
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word.toCharArray(), i, j, 0)) return true;
            }
        }
        return false;
    }
    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word[k]) return false;
        if (k == word.length - 1) return true;
        board[i][j] = '\0';
        boolean ans = dfs(board, word, i - 1, j, k + 1) || dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i, j - 1, k + 1) || dfs(board, word, i, j + 1, k + 1);
        board[i][j] = word[k];
        return ans;
    }

    /*
     * @description: 94. 二叉树的中序遍历
     * @author: edison 
     * @date: 2023/8/3 10:57
     * @param: [root]
     * @return: java.util.List<java.lang.Integer>
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }
    void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    /*
     * @description: 96. 不同的二叉搜索树
     * @author: edison 
     * @date: 2023/8/3 11:04
     * @param: [n]
     * @return: int
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[i - j] * dp[j - 1];
            }
        }
        return dp[n];
    }

    /*
     * @description: 98. 验证二叉搜索树
     * @author: edison 
     * @date: 2023/8/3 11:07
     * @param: [root]
     * @return: boolean
     */
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST(root.left);
        if (pre != null && pre.val >= root.val) return false;
        pre = root;
        boolean right = isValidBST(root.right);
        return left && right;
    }

    /*
     * @description: 101. 对称二叉树
     * @author: edison 
     * @date: 2023/8/3 11:11
     * @param: [root]
     * @return: boolean
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }
    boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /*
     * @description: 102. 二叉树的层序遍历
     * @author: edison 
     * @date: 2023/8/3 11:15
     * @param: [root]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.add(list);
        }
        return ans;
    }
    void bfs(TreeNode root, List<List<Integer>> list, int depth) {
        if (root == null) return;
        depth++;
        if (list.size() < depth) {
            List<Integer> item = new ArrayList<>();
            list.add(item);
        }
        list.get(depth - 1).add(root.val);
        bfs(root.left, list, depth);
        bfs(root.right, list, depth);
    }

    /*
     * @description: 105. 从前序与中序遍历序列构造二叉树
     * @author: edison 
     * @date: 2023/8/3 11:25
     * @param: [preorder, inorder]
     * @return: code.TreeNode
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }
    TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> map) {
        if (preStart > preEnd || inStart > inStart) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = map.get(root.val);
        int len = index - inStart;
        root.left = buildTree(preorder,preStart + 1, preStart + len, inorder, inStart, index, map);
        root.right = buildTree(preorder, preStart + len + 1, preEnd, inorder, index + 1, inEnd, map);
        return root;
    }

    /*
     * @description: 106. 从中序与后序遍历序列构造二叉树
     * @author: edison 
     * @date: 2023/8/3 11:37
     * @param: [inorder, postorder]
     * @return: code.TreeNode
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
    TreeNode build(int[] inorder, int inBegin, int inEnd, int[] postorder, int postBegin, int postEnd, HashMap<Integer, Integer> map) {
        if (inBegin >= inEnd || postBegin >= postEnd) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int index = map.get(root.val);
        int len = index - inBegin;
        root.left = build(inorder, inBegin, index, postorder, postBegin, postBegin + len, map);
        root.right = build(inorder,index + 1, inEnd, postorder, postBegin + len, postEnd - 1, map);
        return root;
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

