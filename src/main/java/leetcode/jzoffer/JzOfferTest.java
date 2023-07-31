package leetcode.jzoffer;

import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.Test;

import javax.sql.rowset.spi.SyncResolver;
import java.math.BigInteger;
import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/10 16:38
 */
public class JzOfferTest {

    Solution solution = new Solution();

    @Test
    public void existTest() {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCB";
        System.out.println(solution.exist(board, word));
    }

    @Test
    public void levelOrder2Test() {
        List<List<Integer>> list = new ArrayList<>();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        list = solution.levelOrder2(root);
        for (List<Integer> l : list) {
            for (int num : l) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void reverseWordsTest() {
        String str = " 1";
        System.out.println(solution.reverseWords(str) + "#");
    }

    @Test
    public void lengthOfLongestSubstringTest() {
        String s = "abba";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }

    @Test
    public void findContinuousSequenceTest() {

    }

    @Test
    public void isPalindromeTest() {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(solution.isPalindrome(s));
    }

    @Test
    public void findAnagramsTest() {
        String s = "aa";
        String p = "bb";
        List<Integer> list = solution.findAnagrams(s, p);
        for (int num : list) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void strToIntTest() {
        String str = "-91283472332";
        System.out.println(solution.strToInt(str));
    }

}

class Solution {

    /*
     * @Description  剑指 Offer 03. 数组中重复的数字
     * @author   Edison
     * @date    2023/4/10 16:39
     * @Param   [nums]
     * @return  int
     */
    public int findRepeatNumber(int[] nums) {
        int[] res = new int[nums.length];
        for (int num : nums) {
            res[num]++;
            if (res[num] > 1) {
                return num;
            }
        }
        return -1;
    }

    /*
     * @Description  剑指 Offer 04. 二维数组中的查找
     * @author   Edison
     * @date    2023/4/10 16:52
     * @Param   [matrix, target]
     * @return  boolean
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch == ' ') {
                sb.append("%20");
            } else {
                sb.append(ch);
            }
        }
        return new String(sb);
    }

    public int[] reversePrint(ListNode head) {
//        Stack<Integer> stack = new Stack<>();
//        while (head != null) {
//            stack.push(head.val);
//            head = head.next;
//        }
//        int[] res = new int[stack.size()];
//        int k = 0;
//        while (!stack.isEmpty()) {
//            res[k++] = stack.pop();
//        }
//        return res;

        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        int[] res = new int[count];
        for (int i = count - 1; i >= 0; i--) {
            res[i] = head.val;
            head = head.next;
        }
        return res;
    }

    /*
     * @Description  剑指 Offer 11. 旋转数组的最小数字
     * @author   Edison
     * @date    2023/4/14 14:26
     * @Param   [numbers]
     * @return  int
     */
    public int minArray(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--){
            if (numbers[i - 1]> numbers[i]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        char[] chs = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == chs[0]) {
                    if (isExist(board, i, j, isVisited, chs, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    boolean isExist(char[][] board, int i, int j, boolean[][] isVisited, char[] chs, int index) {
        if (index == chs.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != chs[index] || isVisited[i][j]) {
            return false;
        }
        isVisited[i][j] = true;
        boolean res = isExist(board, i + 1, j, isVisited, chs, index + 1) ||
                isExist(board, i - 1, j, isVisited, chs, index + 1) ||
                isExist(board, i, j + 1, isVisited, chs, index + 1) ||
                isExist(board, i, j - 1, isVisited, chs, index + 1);
        isVisited[i][j] = false;
        return res;
    }

    public int hammingWeight(int n) {
        int bit = 1;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) == 1){
                count++;
            }
        }
        return count;
    }

    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeTraversal(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    TreeNode buildTreeTraversal(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd) {
        if (preBegin >= preEnd || inBegin >= inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preBegin]);
        int rootIndex = map.get(root.val);
        int leftNum = rootIndex - inBegin;
        root.left = buildTreeTraversal(preorder, preBegin + 1, preBegin + leftNum + 1, inorder, inBegin, rootIndex);
        root.right = buildTreeTraversal(preorder, preBegin + leftNum + 1, preEnd, inorder, rootIndex + 1, inEnd);
        return root;
    }

    public int[] printNumbers(int n) {
        int num = 1;
        for (int i = 0; i < n; i++) {
            num *= 10;
        }
        num -= 1;
        int[] res = new int[num];
        for (int i = 1; i <= num; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode temp = cur.next.next;
                cur.next = temp;
                break;
            }
            cur = cur.next;
        }
        return dummyHead.next;
    }

    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums;
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) {
            return false;
        }
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);

    }
    boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        return A.val == B.val && dfs(A.left, B.left) && dfs(A.right, B.right);
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        TreeNode node = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(node);

//        mirrorTreeTraversal(root);
        return root;
    }
    void mirrorTreeTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        mirrorTreeTraversal(root.left);
        mirrorTreeTraversal(root.right);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricTraversal(root.left, root.right);
    }
    boolean isSymmetricTraversal(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetricTraversal(left.left, right.right) && isSymmetricTraversal(left.right, right.left);
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int elem : pushed) {
            stack.push(elem);
            while (j < popped.length && !stack.isEmpty() && stack.peek() == popped[j]) {
                j++;
                stack.pop();
            }
        }
        return j == popped.length;
    }

    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            list.add(node.val);
            if (node.left != null) {
                deque.add(node.left);
            }
            if (node.right != null) {
                deque.add(node.right);
            }
        }
        int i = 0;
        int[] res = new int[list.size()];
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }

    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> cur = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = deque.pop();
                cur.add(node.val);
                if (node.left != null) {
                    deque.add(node.left);
                }
                if (node.right != null) {
                    deque.add(node.right);
                }
            }
            list.add(cur);
        }
        return list;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        boolean flag = false;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> res = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = deque.pop();
                res.add(node.val);
                if (node.left != null) {
                    deque.add(node.left);
                }
                if (node.right != null) {
                    deque.add(node.right);
                }
            }
            if (flag) {
                Collections.reverse(res);
                flag = false;
            } else {
                flag = true;
            }
            list.add(res);
        }
        return list;
    }

    public int numWays(int n) {
        if (n < 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int res = 0;
        int sum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            res += nums[i];
            sum = Math.max(res, sum);
            if (res <= 0) {
                res = 0;
            }
        }
        return sum;
    }

    public char firstUniqChar(String s) {
        char[] chars = new char[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    /*
     * @Description  剑指 Offer 13. 机器人的运动范围
     * @author   Edison
     * @date    2023/5/29 14:29
     * @Param   [m, n, k]
     * @return  int
     */
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }
    int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= m || j >= n || (i / 10 + i % 10 + j /10 + j % 10 > k) || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        return dfs(i - 1, j, m, n, k, visited) + dfs(i + 1, j, m, n, k, visited)
                 + dfs(i, j - 1, m, n, k, visited) + dfs(i, j + 1, m, n, k, visited) + 1;
    }

    /*
     * @Description  剑指 Offer 33. 二叉搜索树的后序遍历序列
     * @author   Edison
     * @date    2023/5/29 14:39
     * @Param   [postorder]
     * @return  boolean
     */
    public boolean verifyPostorder(int[] postorder) {
        Deque<Integer> stack = new LinkedList<>();
        int pervElem = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > pervElem) {
                return false;
            }
            while (!stack.isEmpty() && postorder[i] < stack.peek()) {
                pervElem = stack.pop();
            }
            stack.push(postorder[i]);
        }
        return true;
    }

    /*
     * @Description  剑指 Offer 35. 复杂链表的复制
     * @author   Edison
     * @date    2023/6/1 11:33
     * @Param   [head]
     * @return  leetcode.jzoffer.Node1
     */
    public Node1 copyRandomList(Node1 head) {
        if (head == null) {
            return null;
        }
        Map<Node1, Node1> map = new HashMap<>();
        for (Node1 cur  = head; cur != null; cur = cur.next) {
            map.put(cur, new Node1(cur.val));
        }
        for (Node1 cur = head; cur != null; cur = cur.next) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
        }
        return map.get(head);
    }

    /*
     * @Description  剑指 Offer 36. 二叉搜索树与双向链表
     * @author   Edison
     * @date    2023/6/1 11:34
     * @Param   [root]
     * @return  leetcode.jzoffer.Node
     */
    Node pre, head;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    void dfs(Node root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == null) head = root;
        else if (pre != null) {
            pre.right = root;
        }
        root.left = pre;
        pre = root;
        dfs(root.right);
    }

    /*
     * @Description  剑指 Offer 20. 表示数值的字符串
     * @author   Edison
     * @date    2023/6/2 15:00
     * @Param   [s]
     * @return  boolean
     */
    public boolean isNumber(String s) {
        s = s.trim();
        boolean numFlag = false;
        boolean dotFlag = false;
        boolean eFlag = false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch >= '0' && ch <= '9') {
                numFlag = true;
            } else if (ch == '.' && !dotFlag && !eFlag) {
                dotFlag = true;
            } else if ((ch == 'e' || ch == 'E') && !eFlag && numFlag) {
                eFlag = true;
                numFlag = false;
            } else if ((ch == '+' || ch == '-') && (i == 0 || chars[i - 1] == 'e' || chars[i -1] == 'E' )) {

            } else {
                return false;
            }
        }
        return numFlag;
    }

    /*
     * @Description  剑指 Offer 38. 字符串的排列
     * @author   Edison
     * @date    2023/6/2 15:27
     * @Param   [s]
     * @return  java.lang.String[]
     */
    public String[] permutation(String s) {
        String[] str = new String[s.length()];
        return str;
    }

    /*
     * @Description  剑指 Offer 53 - I. 在排序数组中查找数字 I
     * @author   Edison
     * @date    2023/6/7 14:38
     * @Param   [nums, target]
     * @return  int
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                count++;
                int temp = mid;
                while (temp - 1 >= 0  && nums[--temp] == target) count++;
                while (mid + 1 < nums.length && nums[++mid] == target) count++;
                return count;
            }
        }
        return count;
    }

    /*
     * @Description  剑指 Offer 53 - II. 0～n-1中缺失的数字
     * @author   Edison
     * @date    2023/6/7 14:53
     * @Param   [nums]
     * @return  int
     */
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == mid) {
                left = mid + 1;
            } else if (nums[mid] > mid) {
                right = mid - 1;
            }
        }
        return left + 1;
    }

    /*
     * @Description  剑指 Offer 57. 和为s的两个数字
     * @author   Edison
     * @date    2023/6/7 15:01
     * @Param   [nums, target]
     * @return  int[]
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] > target) right--;
            else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                res[0] = nums[left];
                res[1] = nums[right];
                return res;
            }
        }
        return res;
    }

    /*
     * @Description  TDOO
     * @author   Edison
     * @date    2023/6/7 16:01
     * @Param   [s]
     * @return  java.lang.String
     */
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length - 1);
        int k = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') continue;
            int index = i;
            while (i < chars.length && chars[i] != ' ') i++;
            reverse(chars, index, i - 1);
            for (int j = index; j < i; j++) {
                chars[k++] = chars[j];
            }
            if (k < chars.length) {
                chars[k++] = ' ';
            }
        }
        if (k == 0) {
            return new String();
        }
        return new String(chars, 0, k == chars.length && chars[k - 1] != ' ' ? k : k - 1);
    }
    void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char ch = chars[start];
            chars[start++] = chars[end];
            chars[end--] = ch;
        }
    }

    /*
     * @Description  剑指 Offer 61. 扑克牌中的顺子
     * @author   Edison
     * @date    2023/6/7 16:01
     * @Param   [nums]
     * @return  boolean
     */
    public boolean isStraight(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < 5; i++) {
            if (nums[i] == 0) {
                count++;
                continue;
            }
            if (i > 0 && nums[i - 1] != 0) {
                if (nums[i] == nums[i - 1]) {
                    return false;
                }
                if (nums[i] - nums[i - 1] != 1) {
                    int temp = nums[i] - nums[i -1] - 1;
                    if (count - temp < 0) {
                        return false;
                    }
                    count -= temp;
                }
            }
        }
        return true;
    }

    /*
     * @Description  剑指 Offer 54. 二叉搜索树的第k大节点
     * @author   Edison
     * @date    2023/6/8 10:53
     * @Param   [root, k]
     * @return  int
     */
    int num = 0;
    public int kthLargest(TreeNode root, int k) {
        dfs(root, k);
        return num;
    }
    void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        dfs(root.right, k);
        if (--k == 0) {
            num = root.val;
            return;
        }
        dfs(root.left, k);
    }

    /*
     * @Description  剑指 Offer 56 - I. 数组中数字出现的次数
     * @author   Edison
     * @date    2023/6/8 11:18
     * @Param   [nums]
     * @return  int[]
     */
    public int[] singleNumbers(int[] nums) {
        int x = 0, y = 0, m = 1, n = 0;
        for (int num : nums) {
            n ^= num;
        }
        while ((n & m) == 0) {
            m <<= 1;
        }
        for (int num : nums) {
            if ((num & m) != 0) x ^= num;
            else y ^= num;
        }
        return new int[]{x, y};
    }

    /*
     * @Description  剑指 Offer 48. 最长不含重复字符的子字符串
     * @author   Edison
     * @date    2023/6/9 10:41
     * @Param   [s]
     * @return  int
     */
    public int lengthOfLongestSubstring(String s) {
        int length = 0;
        Map<Character, Integer> map1 = new HashMap<>();
        for (int l = 0, i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map1.containsKey(ch)) {
                l = Math.max(map1.get(ch), l);
            }
            map1.put(ch, i + 1);
            length = Math.max(length, i - l + 1);
        }
        return length;
    }

    /*
     * @Description  剑指 Offer 57 - II. 和为s的连续正数序列
     * @author   Edison
     * @date    2023/6/12 14:42
     * @Param   [target]
     * @return  int[][]
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> res = new ArrayList<>();
        for (int l = 1, r = 1, sum = 0; r < target; r++) {
            sum += r;
            while (sum > target) {
                sum -= l++;
            }
            if (sum == target) {
                int[] temp = new int[r - l + 1];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = l + i;
                }
                res.add(temp);
            }
        }
        int[][] ans = new int[res.size()][];
        for (int k = 0; k < res.size(); k++) {
            ans[k] = res.get(k);
        }
        return ans;
    }

    /*
     * @Description  剑指 Offer 38. 字符串的排列
     * @author   Edison
     * @date    2023/6/12 10:56
     * @Param   [s]
     * @return  java.lang.String[]
     */
    public String[] permutation1(String s) {
        List<String> list = new ArrayList<>();
        boolean[] used = new boolean[s.length()];
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        backTrack(list, chars, used);
        return list.toArray(new String[0]);
    }
    StringBuilder sb = new StringBuilder();
    void backTrack(List<String> list, char[] chars, boolean[] used) {
        if (sb.length() == chars.length) {
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i] || (i > 0 && chars[i] == chars[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            sb.append(chars[i]);
            backTrack(list, chars, used);
            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /*
     * @Description  剑指 Offer 62. 圆圈中最后剩下的数字
     * @author   Edison
     * @date    2023/6/13 11:07
     * @Param   [n, m]
     * @return  int
     */
    public int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }

    /*
     * @Description  剑指 Offer 65. 不用加减乘除做加法
     * @author   Edison
     * @date    2023/6/13 11:21
     * @Param   [a, b]
     * @return  int
     */
    public int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }

    /*
     * @Description  剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
     * @author   Edison
     * @date    2023/6/13 11:33
     * @Param   [root, p, q]
     * @return  leetcode.jzoffer.TreeNode
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    /*
     * @Description  剑指 Offer 68 - II. 二叉树的最近公共祖先
     * @author   Edison
     * @date    2023/6/13 11:33
     * @Param   [root, p, q]
     * @return  leetcode.jzoffer.TreeNode
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if (left != null && right != null) return root;
        if (left == null) return right;
        return left;
    }

    /*
     * @Description  剑指 Offer II 002. 二进制加法
     * @author   Edison
     * @date    2023/6/13 15:08
     * @Param   [a, b]
     * @return  java.lang.String
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int c = 0;
        while (i >= 0 || j >= 0 || c > 0) {
            int ii = i >= 0 ? a.charAt(i--) - '0' : 0;
            int jj = j >= 0 ? b.charAt(j--) - '0' : 0;
            c += ii + jj;
            sb.append(c % 2);
            c /= 2;
        }
        return sb.reverse().toString();
    }

    /*
     * @Description  剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
     * @author   Edison
     * @date    2023/6/13 15:16
     * @Param   [n]
     * @return  int[]
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }

    /*
     * @Description  剑指 Offer II 004. 只出现一次的数字
     * @author   Edison
     * @date    2023/6/13 15:31
     * @Param   [nums]
     * @return  int
     */
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    /*
     * @Description  剑指 Offer II 006. 排序数组中两个数字之和
     * @author   Edison
     * @date    2023/6/13 15:42
     * @Param   [numbers, target]
     * @return  int[]
     */
    public int[] twoSum1(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                return new int[]{left, right};
            }
        }
        return new int[]{-1, -1};
    }

    /*
     * @Description  剑指 Offer II 009. 乘积小于 K 的子数组
     * @author   Edison
     * @date    2023/6/20 10:42
     * @Param   [nums, k]
     * @return  int
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int cur = 1;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            cur *= nums[right];
            while (left <= right && cur >= k) {
                cur /= nums[left++];
            }
            res += right - left + 1;
            right++;
        }
        return res;
     }

     /*
      * @Description  剑指 Offer II 010. 和为 k 的子数组
      * @author   Edison
      * @date    2023/6/20 11:41
      * @Param   [nums, k]
      * @return  int
      */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;
        map.put(0, 1);
        int pre = 0;
        for (int num : nums) {
            pre += num;
            cnt += map.getOrDefault(pre - k, 0);
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return cnt;
    }

    /*
     * @Description  剑指 Offer II 012. 左右两边子数组的和相等
     * @author   Edison
     * @date    2023/6/20 14:43
     * @Param   [nums]
     * @return  int
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
     * @Description  剑指 Offer II 018. 有效的回文
     * @author   Edison
     * @date    2023/6/20 15:04
     * @Param   [s]
     * @return  boolean
     */
    public boolean isPalindrome(String s) {
        String str = s.toLowerCase();
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            while (left < right && !((str.charAt(left) >= 'a' && str.charAt(left) <= 'z') || (str.charAt(left) >= '0' && str.charAt(left) <= '9'))) left++;
            while (left < right && !((str.charAt(right) >= 'a' && str.charAt(right) <= 'z') || (str.charAt(right) >= '0' && str.charAt(right) <= '9'))) right--;
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    /*
     * @Description  剑指 Offer II 019. 最多删除一个字符得到回文
     * @author   Edison
     * @date    2023/6/20 15:25
     * @Param   [s]
     * @return  boolean
     */
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isValid(s, left + 1, right) || isValid(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }
    boolean isValid(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }

    /*
     * @Description  剑指 Offer II 022. 链表中环的入口节点
     * @author   Edison
     * @date    2023/6/20 15:35
     * @Param   [head]
     * @return  leetcode.jzoffer.ListNode
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    /*
     * @Description  剑指 Offer II 014. 字符串中的变位词
     * @author   Edison
     * @date    2023/6/21 15:44
     * @Param   [s1, s2]
     * @return  boolean
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] a = new int[26];
        int[] b = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            a[s1.charAt(i) - 'a']++;
            b[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(a, b)) return true;
        int left = 0;
        int right = s1.length();
        while (right < s2.length()) {
            b[s2.charAt(right) - 'a']++;
            b[s2.charAt(left) - 'a']--;
            if (Arrays.equals(a, b)) return true;
            left++;
            right++;
        }
        return false;
    }

    /*
     * @Description  剑指 Offer II 015. 字符串中的所有变位词
     * @author   Edison
     * @date    2023/6/21 15:51
     * @Param   [s, p]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (p.length() > s.length()) return list;
        int[] a = new int[26];
        int[] b = new int[26];
        for (int i = 0; i < p.length(); i++) {
            a[p.charAt(i) - 'a']++;
            b[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(a, b)) list.add(0);
        int left = 0, right = p.length();
        while (right < s.length()) {
            b[s.charAt(right) - 'a']++;
            b[s.charAt(left) - 'a']--;
            left++;
            right++;
            if (Arrays.equals(a, b)) list.add(left);
        }
        return list;
    }


    /*
     * @Description  剑指 Offer II 023. 两个链表的第一个重合节点
     * @author   Edison
     * @date    2023/6/25 17:51
     * @Param   [headA, headB]
     * @return  ListNode
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

    /*
     * @Description  剑指 Offer II 024. 反转链表
     * @author   Edison
     * @date    2023/6/25 18:00
     * @Param   [head]
     * @return  second.dynamicprogramming.ListNode
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
     * @Description  剑指 Offer II 020. 回文子字符串的个数
     * @author   Edison
     * @date    2023/6/25 18:05
     * @Param   [s]
     * @return  int
     */
    public int countSubstrings(String s) {
        int count = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(i) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * @Description  剑指 Offer II 025. 链表中的两数相加
     * @author   Edison
     * @date    2023/6/26 10:49
     * @Param   [l1, l2]
     * @return  leetcode.jzoffer.ListNode
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseListNode(l1);
        l2 = reverseListNode(l2);
        ListNode head = new ListNode();
        ListNode cur = head;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int val = carry;
            if (l1 != null) {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }
            ListNode temp = new ListNode(val % 10);
            carry = val / 10;
            cur.next = temp;
            cur = temp;
        }
        return reverseListNode(head.next);
    }
    ListNode reverseListNode(ListNode l) {
        ListNode pre = null;
        while (l != null) {
            ListNode temp = l.next;
            l.next = pre;
            pre = l;
            l = temp;
        }
        return pre;
    }

    /*
     * @Description  剑指 Offer II 021. 删除链表的倒数第 n 个结点
     * @author   Edison
     * @date    2023/6/26 11:10
     * @Param   [head, n]
     * @return  leetcode.jzoffer.ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1, head);
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;
        while (n-- >= 0) fast = fast.next;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyHead.next;
    }

    /*
     * @Description  剑指 Offer II 026. 重排链表
     * @author   Edison
     * @date    2023/6/26 11:24
     * @Param   [head]
     * @return  void
     */
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(j).next = list.get(i).next;
            list.get(i++).next = list.get(j--);
        }
        list.get(i).next = null;
    }

    /*
     * @Description  剑指 Offer II 027. 回文链表
     * @author   Edison
     * @date    2023/6/26 11:35
     * @Param   [head]
     * @return  boolean
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        slow = head;
        right = reverseListNode(right);
        while (right != null) {
            if (slow.val != right.val) return false;
            slow = slow.next;
            right = right.next;
        }
        return true;
    }

    /*
     * @Description  剑指 Offer 45. 把数组排成最小的数
     * @author   Edison
     * @date    2023/6/26 14:59
     * @Param   [nums]
     * @return  java.lang.String
     */
    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        list.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        return String.join("", list);
    }

    /*
     * @Description  剑指 Offer 46. 把数字翻译成字符串
     * @author   Edison
     * @date    2023/6/26 15:05
     * @Param   [num]
     * @return  int
     */
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int[] dp = new int[str.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= str.length(); i++) {
            String tmpStr = str.substring(i - 2, i);
            if (tmpStr.compareTo("10") >= 0 && tmpStr.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[dp.length - 1];
    }

    /*
     * @Description  剑指 Offer 47. 礼物的最大价值
     * @author   Edison
     * @date    2023/6/26 15:16
     * @Param   [grid]
     * @return  int
     */
    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    /*
     * @Description  TDOO
     * @author   Edison
     * @date    2023/6/26 15:27
     * @Param   [n]
     * @return  int
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        int id2 = 0, id3 = 0, id5 = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[id2] * 2, Math.min(dp[id3] * 3, dp[id5] * 5));
            if (dp[id2] * 2 == dp[i]) id2++;
            if (dp[id3] * 3 == dp[i]) id3++;
            if (dp[id5] * 5 == dp[i]) id5++;
        }
        return dp[n - 1];
    }

    /*
     * @Description  剑指 Offer 63. 股票的最大利润
     * @author   Edison
     * @date    2023/6/28 10:37
     * @Param   [prices]
     * @return  int
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }
        return dp[prices.length - 1][1];
    }

    /*
     * @Description  剑指 Offer 66. 构建乘积数组
     * @author   Edison
     * @date    2023/6/28 10:56
     * @Param   [a]
     * @return  int[]
     */
    public int[] constructArr(int[] a) {
        int[] ans = new int[a.length];
        for (int i = 0, temp = 1; i < a.length; i++) {
            ans[i] = temp;
            temp *= a[i];
        }
        for (int i = a.length - 1, temp = 1; i >= 0; i--) {
            ans[i] *= temp;
            temp *= a[i];
        }
        return ans;
    }

    /*
     * @Description  剑指 Offer 67. 把字符串转换成整数
     * @author   Edison
     * @date    2023/6/28 11:11
     * @Param   [str]
     * @return  int
     */
    public int strToInt(String str) {
        if (str.length() == 0 || str == null) {
            return 0;
        }
        long ans = 0;
        int i = 0;
        int INT_MIN = -(1 << 31);
        int INT_MAX = (1 << 31) - 1;
        boolean nagetive = true;
        while (i < str.length() && str.charAt(i) == ' ') i++;
        if (i < str.length() && !(str.charAt(i) >= '0' && str.charAt(i) <= '9') && str.charAt(i) != '+' && str.charAt(i) != '-') return 0;
        if (i < str.length() && !(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
            if (str.charAt(i) == '-') nagetive = false;
            i++;
        }
        while (i < str.length()) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') break;
            ans = ans * 10 + str.charAt(i) - '0';
            if (nagetive && ans > INT_MAX) return INT_MAX;
            if (!nagetive && -ans < INT_MIN) return INT_MIN;
            i++;
        }
        return nagetive == true ? (int) ans : (int) -ans;
    }

    /*
     * @Description  剑指 Offer II 011. 0 和 1 个数相同的子数组
     * @author   Edison
     * @date    2023/6/28 15:37
     * @Param   [nums]
     * @return  int
     */
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) nums[i] = -1;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                len = Math.max(len, i - map.get(sum));
            } else map.put(sum, i);
        }
        return len;
    }

    /*
     * @Description  剑指 Offer II 032. 有效的变位词
     * @author   Edison
     * @date    2023/6/28 15:53
     * @Param   [s, t]
     * @return  boolean
     */
    public boolean isAnagram(String s, String t) {
        if (s.equals(t) || s.length() != t.length()) return false;
        int[] ans = new int[26];
        for (char ch : s.toCharArray()) {
            ans[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            ans[ch - 'a']--;
            if (ans[ch - 'a'] < 0) return false;
        }
        return true;
    }

    /*
     * @Description  剑指 Offer II 033. 变位词组
     * @author   Edison
     * @date    2023/6/29 15:00
     * @Param   [strs]
     * @return  java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String key = new String(ch);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /*
     * @Description  剑指 Offer II 044. 二叉树每层的最大值
     * @author   Edison
     * @date    2023/6/29 15:26
     * @Param   [root]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int max = queue.peek().val;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            list.add(max);
        }
        return list;
    }

    /*
     * @Description  剑指 Offer II 045. 二叉树最底层最左边的值
     * @author   Edison
     * @date    2023/6/29 15:36
     * @Param   [root]
     * @return  int
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode ans = null;
        while (!queue.isEmpty()) {
            ans = queue.poll();
            if (ans.right != null) queue.add(ans.right);
            if (ans.left != null) queue.add(ans.left);
        }
        return ans.val;
    }

    /*
     * @Description  剑指 Offer II 046. 二叉树的右侧视图
     * @author   Edison
     * @date    2023/6/29 15:46
     * @Param   [root]
     * @return  java.util.List<java.lang.Integer>
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return list;
    }

    /*
     * @Description  剑指 Offer II 047. 二叉树剪枝
     * @author   Edison
     * @date    2023/6/29 15:52
     * @Param   [root]
     * @return  leetcode.jzoffer.TreeNode
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) return null;
        return root;
    }

    /*
     * @Description  剑指 Offer II 049. 从根节点到叶节点的路径数字之和
     * @author   Edison
     * @date    2023/6/29 16:10
     * @Param   [root]
     * @return  int
     */
    public int sumNumbers(TreeNode root) {
        return dfsSum(root, 0);
    }
    int dfsSum(TreeNode root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) return sum;
        int left = dfsSum(root.left, sum);
        int right = dfsSum(root.right, sum);
        return left + right;
    }

    /*
     * @Description  剑指 Offer II 050. 向下的路径节点之和     * @author   Edison
     * @date    2023/6/29 16:31
     * @Param   [root, targetSum]
     * @return  int
     */
    int count = 0;
    HashMap<Long, Integer> map1 = new HashMap<>();
    public int pathSum(TreeNode root, int targetSum) {
        if (root != null) {
            map1.put(0L, 1);
            dfs(root, targetSum, 0l);
        }
        return count;
    }
    void dfs(TreeNode root, int targetSum, Long sum) {
        sum += root.val;
        if (map1.containsKey(sum - targetSum)) count += map1.get(sum - targetSum);
        map1.put(sum, map1.getOrDefault(sum, 0) + 1);
        if (root.left != null) dfs(root.left, targetSum, sum);
        if (root.right != null) dfs(root.right, targetSum, sum);
        map1.put(sum, map1.get(sum) - 1);
    }

    /*
     * @Description  剑指 Offer 14- I. 剪绳子
     * @author   Edison
     * @date    2023/6/29 16:50
     * @Param   [n]
     * @return  int
     */
    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(dp[i - j] * j, (i - j) * j));
            }
        }
        return dp[n];
    }

    /*
     * @Description  剑指 Offer 14- II. 剪绳子 II
     * @author   Edison
     * @date    2023/6/29 16:58
     * @Param   [n]
     * @return  int
     */
    public int cuttingRope2(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        Arrays.fill(dp, BigInteger.valueOf(1));
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = dp[i].max(BigInteger.valueOf(j * (i - j))).max(dp[i - j].multiply(BigInteger.valueOf(j)));
            }
        }
        return dp[n].mod(BigInteger.valueOf(1000000007)).intValue();
    }

    /*
     * @Description  剑指 Offer 43. 1～n 整数中 1 出现的次数
     * @author   Edison
     * @date    2023/6/30 10:38
     * @Param   [n]
     * @return  int
     */
    public int countDigitOne(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        int ans = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int cur = list.get(i);
            int left = 0, right = 0, power = 1;
            for (int j = list.size() - 1; j > i; j--) left = left * 10 + list.get(j);
            for (int j = i - 1; j >= 0; j--) {
                right = right * 10 + list.get(j);
                power *= 10;
            }
            if (cur == 0) ans += left * power;
            else if (cur == 1) ans += left * power + right + 1;
            else ans += (left + 1) * power;
        }
        return ans;
    }

    /*
     * @Description  剑指 Offer II 028. 展平多级双向链表
     * @author   Edison
     * @date    2023/7/4 17:42
     * @Param   [head]
     * @return  leetcode.jzoffer.Node
     */
    public Node2 flatten(Node2 head) {
        return dfs(head);
    }
    Node2 dfs(Node2 head) {
        Node2 last = head;
        while (head != null) {
            Node2 next = head.next;
            if (head.child != null) {
                Node2 childLast = dfs(head.child);
                head.next = head.child;
                head.child.prev = head;
                head.child = null;
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }
                last = childLast;
            } else {
                last = head;
            }
            head = next;
        }
        return last;
    }

    /*
     * @Description  剑指 Offer II 052. 展平二叉搜索树
     * @author   Edison
     * @date    2023/7/4 18:26
     * @Param   [root]
     * @return  leetcode.jzoffer.TreeNode
     */
    TreeNode ans = null;
    public TreeNode increasingBST(TreeNode root) {
        dfs(root);
        return ans;
    }
    void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        root.right = ans;
        ans = root;
        dfs(root.left);
        root.left = null;
    }

    /*
     * @Description  剑指 Offer II 029. 排序的循环链表
     * @author   Edison
     * @date    2023/7/5 15:28
     * @Param   [head, insertVal]
     * @return  leetcode.jzoffer.Node3
     */
    public Node3 insert(Node3 head, int insertVal) {
        //空节点
        if (head == null) {
            Node3 node = new Node3(insertVal);
            node.next = node;
            return node;
        }
        //只有一个节点
        if (head.next == head) {
            head.next = new Node3(insertVal, head.next);
            return head;
        }
        Node3 cur = head;
        do {
            if (cur.next.val >= cur.val) {
                if (cur.next.val >= insertVal && cur.val <= insertVal) {
                    cur.next = new Node3(insertVal, cur.next);
                    return head;
                }
            } else {
                if (cur.next.val > insertVal || insertVal > cur.val) {
                    cur.next = new Node3(insertVal, cur.next);
                    return head;
                }
            }
            cur = cur.next;
        } while (cur != head);
        head.next = new Node3(insertVal, head.next);
        return head;
    }

    /*
     * @description: 剑指 Offer II 038. 每日温度
     * @author: edison 
     * @date: 2023/7/10 11:24
     * @param: [temperatures]
     * @return: int[]
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.poll();
            }
            stack.push(i);
        }
        return res;
    }

    /*
     * @description: 剑指 Offer II 053. 二叉搜索树中的中序后继
     * @author: edison 
     * @date: 2023/7/10 11:40
     * @param: [root, p]
     * @return: leetcode.jzoffer.TreeNode
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (root.val > p.val) {
                res = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }

    /*
     * @description: 剑指 Offer II 054. 所有大于等于节点的值之和
     * @author: edison 
     * @date: 2023/7/10 11:50
     * @param: [root]
     * @return: leetcode.jzoffer.TreeNode
     */
    int preSum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return root;
        convertBST(root.right);
        root.val += preSum;
        preSum = root.val;
        convertBST(root.left);
        return root;
    }

    /*
     * @description: 剑指 Offer II 039. 直方图最大矩形面积
     * @author: edison 
     * @date: 2023/7/11 11:12
     * @param: [heights]
     * @return: int
     */
    public int largestRectangleArea(int[] heights) {
        int[] height = new int[heights.length + 2];
        System.arraycopy(heights, 0, height, 1, heights.length);
        height[0] = height[heights.length + 1] = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        int ans = 0;
        for (int i = 1; i < height.length; i++) {
            while (height[i] < height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = i - stack.peek() - 1;
                ans = Math.max(ans, h * w);
            }
            stack.push(i);
        }
        return ans;
    }

    /*
     * @description:
     * @author: edison
     * @date: 2023/7/11 11:37
     * @param: [s, t]
     * @return: java.lang.String
     */
    public String minWindow(String s, String t) {
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
        return str;
    }

    /*
     * @description: 剑指 Offer II 056. 二叉搜索树中两个节点之和
     * @author: edison 
     * @date: 2023/7/24 11:24
     * @param: [root, k]
     * @return: boolean
     */
    Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if (root != null) {
            if (set.contains(k - root.val)) return true;
            else {
                set.add(root.val);
                return findTarget(root.left, k) || findTarget(root.right, k);
            }
        }
        return false;
    }

    /*
     * @description: 剑指 Offer II 057. 值和下标之差都在给定的范围内
     * @author: edison
     * @date: 2023/7/24 11:35
     * @param: [nums, k, t]
     * @return: boolean
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        HashMap<Long, Long> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long idx = getId((long) nums[i], (long) t + 1);
            if (hashMap.containsKey(idx)) return true;
            else {
                hashMap.put(idx, (long) nums[i]);
                if (hashMap.containsKey(idx - 1) && (long) nums[i] - hashMap.get(idx - 1) <= (long) t) return true;
                if (hashMap.containsKey(idx + 1) && hashMap.get(idx + 1) - (long) nums[i] <= (long) t) return true;
            }
            if (i - k >= 0) {
                hashMap.remove(getId(nums[i - k], t + 1));
            }
        }
        return false;
    }
    long getId(long x, long w) {
        return x < 0 ? x / w - 1 : x / w;
    }

    /*
     * @description: 剑指 Offer II 060. 出现频率最高的 k 个数字
     * @author: edison 
     * @date: 2023/7/24 15:38
     * @param: [nums, k]
     * @return: int[]
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair1[1] - pair2[1]);
        for (HashMap.Entry<Integer, Integer> map : hashMap.entrySet()) {
            if (pq.size() < k) pq.add(new int[]{map.getKey(), map.getValue()});
            else {
                if (map.getValue() > pq.peek()[1]) {
                    pq.poll();
                    pq.add(new int[]{map.getKey(), map.getValue()});
                }
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = pq.poll()[0];
        }
        return res;
    }

    /*
     * @description: 剑指 Offer II 061. 和最小的 k 个数对
     * @author: edison 
     * @date: 2023/7/24 15:56
     * @param: [nums1, nums2, k]
     * @return: java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> list = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> ((x[0] + x[1]) - (y[0] + y[1])));
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                pq.offer(new int[]{nums1[i], nums2[j]});
            }
        }
        k = Math.min(k, pq.size());
        for (int i = 0; i < k; i++) {
            int[] tmp = pq.poll();
            list.add(Arrays.asList(tmp[0], tmp[1]));
        }
        return list;
    }

    /*
     * @description: 剑指 Offer 51. 数组中的逆序对
     * @author: edison 
     * @date: 2023/7/31 10:01
     * @param: [nums]
     * @return: int
     */
    int[] tmp, nums;
    public int reversePairs(int[] nums) {
        tmp = new int[nums.length];
        this.nums = nums;
        return mergeSort(0, nums.length - 1);
    }
    int mergeSort(int l, int r) {
        if (l >= r) return 0;
        int mid = l + (r - l) / 2;
        int res = mergeSort(l, mid) + mergeSort(mid + 1, r);
        int i = l, k = mid + 1;
        for (int j = l; j <= r; j++) {
            tmp[j] = nums[j];
        }
        for (int j = l; j <= r; j++) {
            if (i == mid + 1)
                nums[j] = tmp[k++];
            else if (k == r + 1 || tmp[i] <= tmp[k])
                nums[j] = tmp[i++];
            else {
                nums[j] = tmp[k++];
                res += mid - i + 1; // 统计逆序对
            }
        }
        return res;
    }

    /*
     * @description: 剑指 Offer 19. 正则表达式匹配
     * @author: edison 
     * @date: 2023/7/31 10:28
     * @param: [s, p]
     * @return: boolean
     */
    public boolean isMatch(String s, String p) {
        //dp[i][j]含义：以 i - 1索引结尾的 s字符串，是否能够由 j - 1索引结尾的p字符串 模式匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 2; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    else dp[i][j] = dp[i][j - 2];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /*
     * @description: 剑指 Offer 60. n个骰子的点数
     * @author: edison 
     * @date: 2023/7/31 11:08
     * @param: [n]
     * @return: double[]
     */
    public double[] dicesProbability(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        double[] ans = new double[5 * n + 1];
        double all = Math.pow(6, n);
        for (int i = 1; i <= 6; i++)
            dp[1][i] = 1;
        for (int i = 1; i <= n; i++) { //i个骰子
            for (int j = i; j <= 6 * i; j++) { //点数j
                for (int k = 1; k <= 6; k++) { //第i骰子的6种情况
                    dp[i][j] += j >= k ? dp[i - 1][j - k] : 0;
                    if (i == n)
                        ans[j-i]=dp[i][j]/all;
                }
            }
        }
        return ans;
    }

    /*
     * @description: 剑指 Offer II 001. 整数除法
     * @author: edison 
     * @date: 2023/7/31 11:30
     * @param: [a, b]
     * @return: int
     */
    public int divide(int a, int b) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        int min_limit = min >> 1;
        if (a == min && b == -1) return max;
        boolean isPos = (a < 0 && b > 0) || (a > 0 && b < 0) ? false : true;
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int ans = 0;
        while (a <= b) {
            int d = b, c = 1;
            while (d >= min_limit && d + d >= a) {
                d <<= 1;
                c <<= 1;
            }
            a -= d;
            ans += c;
        }
        return isPos ? ans : -ans;
    }

}

/*
 * @description: 剑指 Offer II 059. 数据流的第 K 大数值
 * @author: edison 
 * @date: 2023/7/24 15:12
 * @param: 
 * @return: 
 */
class KthLargest {
    PriorityQueue<Integer> queue;
    int len;
    public KthLargest(int k, int[] nums) {
        this.len = k;
        queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add(num);
        }
        while (queue.size() > k) queue.poll();
    }

    public int add(int val) {
        queue.add(val);
        while (queue.size() > len) queue.poll();
        return queue.peek();
    }
}

/*
 * @description: 剑指 Offer II 058. 日程表
 * @author: edison 
 * @date: 2023/7/24 14:59
 * @param: 
 * @return: 
 */
class MyCalendar {

    List<int[]> list;
    public MyCalendar() {
        list = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] num : list) {
            if (start >= num[1] || end < num[0]) continue;
            return false;
        }
        list.add(new int[]{start, end});
        return true;
    }
}

/*
 * @description: 剑指 Offer II 055. 二叉搜索树迭代器
 * @author: edison 
 * @date: 2023/7/24 11:19
 * @param: 
 * @return: 
 */
class BSTIterator {

    List<Integer> list;
    int index;
    int len;
    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        dfs(root);
        this.len = list.size();
        index = 0;
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index < len ? true : false;
    }

    void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
}

/*
 * @Description  剑指 Offer II 031. 最近最少使用缓存
 * @author   Edison
 * @date    2023/7/5 16:12
 * @Param
 * @return  
 */
class LRUCache {

    HashMap<Integer, Integer> map;
    Queue<Integer> queue;
    int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        queue = new ArrayDeque<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            queue.remove(key);
            queue.offer(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            queue.remove(key);
        } else {
            if (queue.size() == capacity) {
                map.remove(queue.peek());
                queue.poll();
            }
        }
        queue.offer(key);
        map.put(key, value);
    }
}

/*
 * @Description  剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
 * @author   Edison
 * @date    2023/7/5 15:29
 * @Param   
 * @return  
 */
class RandomizedSet {

    HashMap<Integer, Integer> map;
    List<Integer> list;
    Random random = new Random();
    int size;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        size = 0;
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, size++);
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        int last = list.get(--size);
        list.set(index, last);
        map.put(last, index);
        list.remove(size);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(size));
    }

    /*
     * @description: 剑指 Offer II 036. 后缀表达式
     * @author: edison
     * @date: 2023/7/7 11:42
     * @param: [tokens]
     * @return: int
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+" : stack.add(stack.pop() + stack.pop());
                    break;
                case "-" : stack.add(-stack.pop() + stack.pop());
                    break;
                case "*" : stack.add(stack.pop() * stack.pop());
                    break;
                case "/" : int num1 = stack.pop();
                    int num2 = stack.pop();
                    stack.add(num2 / num1);
                    break;
                default:
                    stack.add(Integer.valueOf(token));
                    break;
            }
        }
        return stack.peek();
    }

    /*
     * @description: 剑指 Offer II 037. 小行星碰撞
     * @author: edison
     * @date: 2023/7/7 16:11
     * @param: [asteroids]
     * @return: int[]
     */
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        stack.push(asteroids[0]);
        Gread1:
        for (int i = 1; i < asteroids.length; i++) {
            int cur = asteroids[i];
            while (!stack.isEmpty() && cur < 0 && stack.peek() > 0) {
                if (-cur > stack.peek()) {
                    stack.pop();
                } else if (-cur < stack.peek()) {
                    continue Gread1;
                } else if (-cur == stack.peek()) {
                    stack.pop();
                    continue Gread1;
                }
            }
            stack.push(cur);
        }
        int[] res = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}

/*
 * @description: 剑指 Offer II 043. 往完全二叉树添加节点
 * @author: edison
 * @date: 2023/7/7 15:46
 * @param:
 * @return:
 */
class CBTInserter {

    TreeNode root;
    Queue<TreeNode> queue = new LinkedList<>();
    public CBTInserter(TreeNode root) {
        this.root = root;
        queue.offer(root);
        while (queue.peek().left != null && queue.peek().right != null) {
            TreeNode node = queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }
    }

    public int insert(int v) {
        TreeNode node = queue.peek();
        if (node.left == null) {
            node.left = new TreeNode(v);
        } else {
            node.right = new TreeNode(v);
            queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

class Node3 {
    int val;
    Node3 next;

    public Node3() {
    }

    public Node3(int val) {
        this.val = val;
    }

    public Node3(int val, Node3 next) {
        this.val = val;
        this.next = next;
    }
}
class Node2 {
    public int val;
    public Node2 prev;
    public Node2 next;
    public Node2 child;
}

/*
 * @Description  剑指 Offer 59 - II. 队列的最大值
 * @author   Edison
 * @date    2023/6/30 11:07
 * @Param
 * @return  
 */
class MaxQueue {

    Queue<Integer> queue;
    Deque<Integer> deque;
    public MaxQueue() {
        this.queue = new LinkedList<>();
        this.deque = new LinkedList<>();
    }

    public int max_value() {
        if (!deque.isEmpty()) return deque.peek();
        return -1;
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!deque.isEmpty() && value > deque.peekLast()) deque.pollLast();
        deque.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) return -1;
        int val = queue.poll();
        if (deque.peekFirst() == val) deque.pop();
        return val;
    }
}


/*
 * @Description  剑指 Offer 41. 数据流中的中位数
 * @author   Edison
 * @date    2023/6/29 17:33
 * @Param
 * @return
 */
class MedianFinder {

    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    /** initialize your data structure here. */
    public MedianFinder() {
        left = new PriorityQueue<>((n1, n2) -> n2 - n1);
        right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        left.add(num);
        right.add(left.poll());
        if (left.size() + 1 < right.size()) {
            left.add(right.poll());
        }
    }

    public double findMedian() {
        if (right.size() > left.size()) return right.peek();
        return (double) (right.peek() + left.peek()) / 2;
    }
}

/*
 * @Description  剑指 Offer II 041. 滑动窗口的平均值
 * @author   Edison
 * @date    2023/6/29 15:03
 * @Param
 * @return  
 */
class MovingAverage {

    int size;
    int sum;
    LinkedList<Integer> list = new LinkedList<>();

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        if (list.size() == size) {
            sum -= list.removeFirst();
        }
        sum += val;
        list.add(val);
        return (double) sum / list.size();
    }
}

/*
 * @Description  剑指 Offer II 042. 最近请求次数
 * @author   Edison
 * @date    2023/6/29 15:18
 * @Param
 * @return  
 */
class RecentCounter {

    LinkedList<Integer> list;
    public RecentCounter() {
        list = new LinkedList<>();
    }
    public int ping(int t) {
        int min = t - 3000;
        list.add(t);
        while (list.getFirst() < min) {
            list.removeFirst();
        }
        return list.size();
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null,";
        }
        String res = root.val + ",";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strings = data.split(",");
        Queue<String> queue = new LinkedList<>();
        for (String str : strings) {
            queue.add(str);
        }
        return help(queue);
    }
    TreeNode help(Queue<String> queue) {
        String str = queue.poll();
        if (str.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(str));
        root.left = help(queue);
        root.right = help(queue);
        return root;
    }


}

class MinStack {

    Stack<Integer> stack;
    int min;
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        stack.push(min);
        min = min > x ? x : min;
        stack.push(x);
    }

    public void pop() {
        stack.pop();
        min = stack.pop();
    }

    public int top() {
       return stack.peek();
    }

    public int min() {
        return min;
    }
}

class Node1 {
    int val;
    Node1 next;
    Node1 random;

    public Node1() {
    }

    public Node1(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Node {
    int val;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
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

class CQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.add(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                return -1;
            }
            else {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
        }
        return stack2.pop();
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
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

