package leetcode.other;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/5/23 10:07
 */
public class OtherTest {

    Solution solution = new Solution();

    @Test
    public void moveZeroesTest() {
        int[] nums = {0,1,0,3,12};
        solution.moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void isLongPressedNameTest() {
        String name = "alex";
        String typed = "aaleexa";
        System.out.println(solution.isLongPressedName(name, typed));
    }

}

class Solution {

    /*
     * @Description  283. 移动零
     * @author   Edison
     * @date    2023/5/23 10:08
     * @Param   [nums]
     * @return  void
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

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode cur = dummyHead;
        while (cur.next != null && cur.next.next != null) {
            ListNode temp = cur.next.next.next;
            ListNode temp1 = cur.next.next;
            ListNode temp2 = cur.next;
            cur.next = temp1;
            temp1.next = temp2;
            temp2.next = temp;
            cur = temp2;
        }
        return dummyHead.next;
    }

    /*
     * @Description  143. 重排链表
     * @author   Edison
     * @date    2023/5/23 10:49
     * @Param   [head]
     * @return  void
     */
    public void reorderList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        right = reverseList(right);
        slow = head;
        while (right != null) {
            ListNode tmp1 = slow.next;
            ListNode tmp2 = right.next;
            slow.next = right;
            right.next = tmp1;
            slow = tmp1;
            right = tmp2;
        }
    }

    ListNode reverseList(ListNode cur) {
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
     * @Description  1002. 查找共用字符
     * @author   Edison
     * @date    2023/5/23 11:29
     * @Param   [words]
     * @return  List<String>
     */
    public List<String> commonChars(String[] words) {
        List<String> list = new ArrayList<>();
        int[] hash = new int[26];
        for (int i = 0; i < words[0].length(); i++) {
            hash[words[0].charAt(i) - 'a']++;
        }
        for (int i = 1; i < words.length; i++) {
            int[] hashOtherStr = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                hashOtherStr[words[i].charAt(j) - 'a']++;
            }
            for (int k = 0; k < 26; k++) {
                hash[k] = Math.min(hash[k], hashOtherStr[k]);
            }
        }
        for (int i = 0; i < 26; i++) {
            while (hash[i] != 0) {
                char ch = (char) (i + 'a');
                list.add(String.valueOf(ch));
                hash[i]--;
            }
        }
        return list;
    }

    /*
     * @Description  925. 长按键入
     * @author   Edison
     * @date    2023/5/24 10:10
     * @Param   [name, typed]
     * @return  boolean
     */
    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        while (i < name.length() && j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) return false;
                while (j < typed.length() - 1 && typed.charAt(j) == typed.charAt(j - 1)) j++;
                if (name.charAt(i) == typed.charAt(j)) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            }
        }
        if (i < name.length()) return false;
        while (j < typed.length()) {
            if (typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  129. 求根节点到叶节点数字之和
     * @author   Edison
     * @date    2023/5/24 10:46
     * @Param   [root]
     * @return  int
     */
    int sum;

    public int sumNumbers(TreeNode root) {
        sum = 0;
        childSum(0, root);
        return sum;
    }

    void childSum(int val, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            sum += 10 * val + root.val;
            return;
        }
        childSum(10 * val + root.val, root.left);
        childSum(10 * val + root.val, root.right);
    }

    /*
     * @Description  1382. 将二叉搜索树变平衡
     * @author   Edison
     * @date    2023/5/24 10:59
     * @Param   [root]
     * @return  leetcode.other.TreeNode
     */
    ArrayList<Integer> list = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        traversal(root);
        return getTree(list, 0, list.size() - 1);
    }

    void traversal(TreeNode root) {
        if (root == null) return;
        traversal(root.left);
        list.add(root.val);
        traversal(root.right);
    }

    TreeNode getTree(ArrayList<Integer> list, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = getTree(list, left, mid - 1);
        root.right = getTree(list, mid + 1, right);
        return root;
    }

    /*
     * @Description  100. 相同的树
     * @author   Edison
     * @date    2023/5/24 11:21
     * @Param   [p, q]
     * @return  boolean
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return getSameTree(p, q);
    }
    boolean getSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null || p.val != q.val) {
            return false;
        }
        boolean left = getSameTree(p.left, q.left);
        boolean right = getSameTree(p.right, q.right);
        return left && right;
    }

    /*
     * @Description  52. N 皇后 II
     * @author   Edison
     * @date    2023/5/24 14:46
     * @Param   [n]
     * @return  int
     */
    int count;
    public int totalNQueens(int n) {
        count = 0;
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        backTrack(n, 0, board);
        return count;
    }
    boolean isValid(int row, int col, int n, char[][] board) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
    void backTrack(int n, int row, char[][] board) {
        if (n == row) {
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, board)) {
                board[row][col] = 'Q';
                backTrack(n, row + 1, board);
                board[row][col] = '.';
            }
        }
    }

    /*
     * @Description  649. Dota2 参议院
     * @author   Edison
     * @date    2023/5/25 9:53
     * @Param   [senate]
     * @return  java.lang.String
     */
    public String predictPartyVictory(String senate) {
        boolean r = true, d = true;
        int flag = 0;
        byte[] st = senate.getBytes();
        while (r && d) {
            r = false;
            d = false;
            for (int i = 0; i < st.length; i++) {
                if (st[i] == 'R') {
                    if (flag < 0) st[i] = 0;
                    else r = true;
                    flag++;
                }
                if (st[i] == 'D') {
                    if (flag > 0) st[i] = 0;
                    else d = true;
                    flag--;
                }
            }
        }
        return r == true ? "Radiant" : "Dire";
    }

    /*
     * @Description  1221. 分割平衡字符串
     * @author   Edison
     * @date    2023/5/25 10:11
     * @Param   [s]
     * @return  int
     */
    public int balancedStringSplit(String s) {
        int count = 0;
        int res = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'R') count++;
            else count--;
            if (count == 0) res++;
        }
        return res;
    }

    /*
     * @Description  5. 最长回文子串
     * @author   Edison
     * @date    2023/5/25 10:18
     * @Param   [s]
     * @return  java.lang.String
     */
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        int length = 1;
        int index = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = s.length(); i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
                if (dp[i][j] && j - i + 1 > length) {
                    length = j - i + 1;
                    index = i;
                }
            }
        }
        return s.substring(index, index + length);
    }

    /*
     * @Description  132. 分割回文串 II
     * @author   Edison
     * @date    2023/5/25 10:35
     * @Param   [s]
     * @return  int
     */
    public int minCut(String s) {
        int len = s.length();
        boolean[][] isPalindromic = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {
                        isPalindromic[i][j] = true;
                    } else if (isPalindromic[i + 1][j - 1]) {
                        isPalindromic[i][j] = true;
                    }
                } else {
                    isPalindromic[i][j] = false;
                }
            }
        }

        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = i;
        }
        for (int i = 1; i < len; i++) {
            if (isPalindromic[0][i]) {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (isPalindromic[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }

    /*
     * @Description  673. 最长递增子序列的个数
     * @author   Edison
     * @date    2023/5/25 10:58
     * @Param   [nums]
     * @return  int
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        int[] count = new int[nums.length];
        for (int i = 0; i < count.length; i++) {
            count[i] = 1;
        }
        int maxCount = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
                if (dp[i] > maxCount) maxCount = dp[i];
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == maxCount) res += count[i];
        }
        return res;
    }

    /*
     * @Description  841. 钥匙和房间
     * @author   Edison
     * @date    2023/5/25 11:18
     * @Param   [rooms]
     * @return  boolean
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        List<Boolean> visited = new ArrayList<Boolean>() {{
            for (int i = 0; i < rooms.size(); i++) {
                add(false);
            }
        }};
        dfs(0, rooms, visited);
        for (boolean flag : visited) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }
    void dfs(int key, List<List<Integer>> rooms, List<Boolean> visited) {
        if (visited.get(key)) {
            return;
        }
        visited.set(key, true);
        for (int k : rooms.get(key)) {
            dfs(k, rooms, visited);
        }
    }

    /*
     * @Description  127. 单词接龙
     * @author   Edison
     * @date    2023/5/26 10:05
     * @Param   [beginWord, endWord, wordList]
     * @return  int
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Map<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int path = map.get(word);
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();
                for (char k = 'a'; k <= 'z'; k++) {
                    chars[i] = k;
                    String newWord = String.valueOf(chars);
                    if (newWord.equals(endWord)) {
                        return path + 1;
                    }
                    if (wordSet.contains(newWord) && !map.containsKey(newWord)) {
                        map.put(newWord, path + 1);
                        queue.offer(newWord);
                    }
                }
            }
        }
        return 0;
    }

    /*
     * @Description  657. 机器人能否返回原点
     * @author   Edison
     * @date    2023/5/26 10:50
     * @Param   [moves]
     * @return  boolean
     */
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'R': x++;break;
                case 'L': x--;break;
                case 'U': y++;break;
                case 'D': y--;break;
            }
        }
        return x == 0 && y == 0;
    }

    /*
     * @Description  463. 岛屿的周长
     * @author   Edison
     * @date    2023/5/26 11:13
     * @Param   [grid]
     * @return  int
     */
    public int islandPerimeter(int[][] grid) {
        int sum = 0;
        int cover = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    sum++;
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        cover++;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        cover++;
                    }
                }
            }
        }
        return sum * 4 - cover * 2;
    }

    /*
     * @Description  1356. 根据数字二进制下 1 的数目排序
     * @author   Edison
     * @date    2023/5/26 11:24
     * @Param   [arr]
     * @return  int[]
     */
    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr).boxed()
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        int cnt1 = cntInt(o1);
                        int cnt2 = cntInt(o2);
                        return cnt1 == cnt2 ? Integer.compare(o1, o2) : Integer.compare(cnt1, cnt2);
                    }
                })
                .mapToInt(Integer::intValue)
                .toArray();
    }
    int cntInt(int val) {
        int count = 0;
        while (val > 0) {
            val = val & (val - 1);
            count++;
        }
        return count;
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

