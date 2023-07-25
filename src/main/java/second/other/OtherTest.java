package second.other;

import com.beust.ah.A;

import java.nio.channels.ClosedChannelException;
import java.util.*;

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

    /*
     * @description: 205. 同构字符串
     * @author: edison 
     * @date: 2023/7/17 17:02
     * @param: [s, t]
     * @return: boolean
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for (int i = 0, j = 0; i < s.length(); i++, j++) {
            if (!map1.containsKey(s.charAt(i))) map1.put(s.charAt(i), t.charAt(j));
            if (!map2.containsKey(t.charAt(j))) map2.put(t.charAt(j), s.charAt(i));
            if (map1.get(s.charAt(i)) != t.charAt(j) || map2.get(t.charAt(j)) != s.charAt(i)) return false;
        }
        return true;
    }

    /*
     * @description: 1002. 查找共用字符
     * @author: edison 
     * @date: 2023/7/17 17:13
     * @param: [words]
     * @return: List<String>
     */
    public List<String> commonChars(String[] words) {
        List<String> list = new ArrayList<>();
        int[] hash = new int[26];
        for (char ch : words[0].toCharArray()) {
            hash[ch - 'a']++;
        }
        for (int i = 1; i < words.length; i++) {
            int[] hash1 = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                hash1[words[i].charAt(j) - 'a']++;
            }
            for (int z = 0; z < 26; z++) {
                hash[z] = Math.min(hash[z], hash1[z]);
            }
        }
        for (int i = 0; i < 26; i++) {
            while (hash[i]-- > 0) {
                char ch = (char) (i + 'a');
                list.add(String.valueOf(ch));
            }
        }
        return list;
    }

    /*
     * @description: 925. 长按键入
     * @author: edison 
     * @date: 2023/7/17 18:07
     * @param: [name, typed]
     * @return: boolean
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
                while (j < typed.length() && typed.charAt(j) == typed.charAt(j - 1)) j++;
                if (j < typed.length() && name.charAt(i) != typed.charAt(j)) return false;
                i++;
                j++;
            }
        }
        if (i < name.length()) return false;
        if (j < typed.length()) {
            while (j < typed.length()) {
                if (typed.charAt(j) == typed.charAt(j - 1)) j++;
                else return false;
            }
        }
        return true;
    }

    /*
     * @description: 844. 比较含退格的字符串
     * @author: edison 
     * @date: 2023/7/17 18:19
     * @param: [s, t]
     * @return: boolean
     */
    public boolean backspaceCompare(String s, String t) {
        int lenS = s.length() - 1;
        int lenT = t.length() - 1;
        int count1 = 0;
        int count2 = 0;
        while (lenS >= 0 || lenT >= 0) {
            while (lenS >= 0) {
                if (s.charAt(lenS) == '#') {
                    count1++;
                    lenS--;
                } else if (count1 > 0) {
                    count1--;
                    lenS--;
                } else {
                    break;
                }
            }
            while (lenT >= 0) {
                if (t.charAt(lenT) == '#') {
                    count2++;
                    lenT--;
                } else if (count2 > 0) {
                    lenT--;
                    count2--;
                } else {
                    break;
                }
            }
            if (lenT >= 0 && lenS >= 0) {
                if (s.charAt(lenS) != t.charAt(lenT)) return false;
            } else {
                if (lenT >= 0 || lenS >= 0) {
                    return false;
                }
            }
            lenT--;
            lenS--;
        }
        return true;
    }

    /*
     * @description: 129. 求根节点到叶节点数字之和
     * @author: edison 
     * @date: 2023/7/18 11:17
     * @param: [root]
     * @return: int
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    int dfs(TreeNode root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) return sum;
        int left = dfs(root.left, sum);
        int right = dfs(root.right, sum);
        return left + right;
    }

    /*
     * @description: 1382. 将二叉搜索树变平衡
     * @author: edison 
     * @date: 2023/7/18 11:38
     * @param: [root]
     * @return: second.other.TreeNode
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
     * @description: 100. 相同的树
     * @author: edison 
     * @date: 2023/7/19 11:16
     * @param: [p, q]
     * @return: boolean
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        else if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /*
     * @description: 101. 对称二叉树
     * @author: edison
     * @date: 2023/7/19 11:21
     * @param: [root]
     * @return: boolean
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }
    boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (left == null || right == null) return false;
        else if (left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public Node connect(Node root) {
        if (root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                if (size > 0) node.next = queue.peek();
            }
        }
        return root;
    }

    /*
     * @description: 52. N 皇后 II
     * @author: edison
     * @date: 2023/7/21 11:24
     * @param: [n]
     * @return: int
     */
    int count;
    public int totalNQueens(int n) {
        count = 0;
        char[][] board = new char[n][n];
        for (char[] ch : board) Arrays.fill(ch, '.');
        backTrack(n, 0, board);
        return count;
    }
    boolean isValid(int row, int col, int n, char[][] board) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
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
     * @description: 649. Dota2 参议院
     * @author: edison 
     * @date: 2023/7/24 9:39
     * @param: [senate]
     * @return: java.lang.String
     */
    public String predictPartyVictory(String senate) {
        boolean R = true, D = true;
        int flag = 0;
        byte[] st = senate.getBytes();
        while (R && D) {
            R = false;
            D = false;
            for (int i = 0; i < st.length; i++) {
                if (st[i] == 'R') {
                    if (flag < 0) st[i] = 0;
                    else R = true;
                    flag++;
                }
                if (st[i] == 'D') {
                    if (flag > 0) st[i] = 0;
                    else D = true;
                    flag--;
                }
            }
        }
        return R == true ? "Radiant" : "Dire";
    }

    /*
     * @description: 1221. 分割平衡字符串
     * @author: edison 
     * @date: 2023/7/24 9:57
     * @param: [s]
     * @return: int
     */
    public int balancedStringSplit(String s) {
        int ans = 0;
        int flag = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'R') flag++;
            else flag--;
            if (flag == 0) ans++;
        }
        return ans;
    }

    /*
     * @description: 5. 最长回文子串
     * @author: edison 
     * @date: 2023/7/24 10:01
     * @param: [s]
     * @return: java.lang.String
     */
    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int len = 0;
        int index = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) dp[i][j] = true;
                    else if (dp[i + 1][j - 1]) dp[i][j] = true;
                }
                if (dp[i][j] && j - i + 1 > len) {
                    len = j - i + 1;
                    index = i;
                }
            }
        }
        return s.substring(index, index + len);
    }

    /*
     * @description: 132. 分割回文串 II
     * @author: edison 
     * @date: 2023/7/24 10:21
     * @param: [s]
     * @return: int
     */
    public int minCut(String s) {
        int len = s.length();
        boolean[][] isPalindromic = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) isPalindromic[i][j] = true;
                    else if (isPalindromic[i + 1][j - 1]) {
                        isPalindromic[i][j] = true;
                    }
                } else isPalindromic[i][j] = false;
            }
        }
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            dp[i] = i;
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
     * @description: 673. 最长递增子序列的个数
     * @author: edison 
     * @date: 2023/7/24 10:51
     * @param: [nums]
     * @return: int
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        int max = 0;
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
            }
            if (dp[i] > max) max = dp[i];
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] == max) ans += count[i];
        }
        return ans;
    }

    /*
     * @description: 841. 钥匙和房间
     * @author: edison
     * @date: 2023/7/25 9:42
     * @param: [rooms]
     * @return: boolean
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        List<Boolean> visited = new ArrayList<>(){{
            for (int i = 0; i < rooms.size(); i++) {
                add(false);
            }
        }};
        dfs(0, rooms, visited);
        for (boolean flag : visited) {
            if (!flag) return false;
        }
        return true;
    }
    void dfs(int key, List<List<Integer>> rooms, List<Boolean> visited) {
        if (visited.get(key)) return;
        visited.set(key, true);
        for (int k : rooms.get(key)) {
            dfs(k, rooms, visited);
        }
    }

    /*
     * @description: 127. 单词接龙
     * @author: edison 
     * @date: 2023/7/25 10:02
     * @param: [beginWord, endWord, wordList]
     * @return: int
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(beginWord, 1);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int path = hashMap.get(word);
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();
                for (char k = 'a'; k <= 'z'; k++) {
                    chars[i] = k;
                    String newWord = String.valueOf(chars);
                    if (newWord.equals(endWord)) return path + 1;
                    if (wordSet.contains(newWord) && !hashMap.containsKey(newWord)) {
                        hashMap.put(newWord, path + 1);
                        queue.offer(newWord);
                    }
                }
            }
        }
        return 0;
    }
    
    /*
     * @description: 657. 机器人能否返回原点
     * @author: edison 
     * @date: 2023/7/25 10:37
     * @param: [moves]
     * @return: boolean
     */
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'U') y++;
            else if (ch == 'D') y--;
            else if (ch == 'L') x--;
            else if (ch == 'R') x++;
        }
        return x == 0 && y == 0;
    }

    /*
     * @description: 31. 下一个排列
     * @author: edison 
     * @date: 2023/7/25 10:41
     * @param: [nums]
     * @return: void
     */
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    Arrays.sort(nums, i + 1, nums.length);
                    return;
                }
            }
        }
        Arrays.sort(nums);
    }

    /*
     * @description: 463. 岛屿的周长
     * @author: edison 
     * @date: 2023/7/25 10:49
     * @param: [grid]
     * @return: int
     */
    public int islandPerimeter(int[][] grid) {
        int[] dirx = {-1, 1, 0, 0};
        int[] diry = {0, 0, -1, 1};
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dirx[k];
                        int y = j + diry[k];
                        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) res++;
                    }
                }
            }
        }
        return res;
    }

    /*
     * @description: 1356. 根据数字二进制下 1 的数目排序
     * @author: edison 
     * @date: 2023/7/25 11:06
     * @param: [arr]
     * @return: int[]
     */
    public int[] sortByBits(int[] arr) {
        return Arrays.stream(arr).boxed().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int cnt1 = bitCount(o1);
                int cnt2 = bitCount(o2);
                return cnt1 == cnt2 ? Integer.compare(o1, o2) : Integer.compare(cnt1, cnt2);
            }
        }).mapToInt(Integer::valueOf).toArray();
    }
    int bitCount(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
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

class Node {
    int val;
    Node left;
    Node right;
    Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right, Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}

