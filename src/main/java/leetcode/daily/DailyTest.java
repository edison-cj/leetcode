package leetcode.daily;

import org.testng.annotations.Test;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/17 11:10
 */
public class DailyTest {

    Solution solution = new Solution();

    @Test
    public void countDaysTogetherTest() {
        String arriveAlice = "08-15";
        String leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19";
        int day = solution.countDaysTogether(arriveAlice, leaveAlice, arriveBob, leaveBob);
        System.out.println(day);
    }

    @Test
    public void romanToIntTest() {
        String s = "MCMXCIV";
        System.out.println(solution.romanToInt(s));
    }

    @Test
    public void spiralOrderTest() {
        int[][] nums = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        List<Integer> list = new ArrayList<>();
        list = solution.spiralOrder(nums);
        for (Integer num : list) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void addBinaryTest() {
        String a = "11";
        String b = "1";
        System.out.println(solution.addBinary(a, b).toString());
    }

    @Test
    public void isPalindromeTest() {
        String s = "race a car";
        System.out.println(solution.isPalindrome(s));
    }

    @Test
    public void numberOfWaysTest() {
        String s = "001101";
        System.out.println(solution.numberOfWays(s));
    }

    @Test
    public void queryStringTest() {
        String s = "110101011011000011011111000000";
        int num = 15;
        System.out.println(solution.queryString(s, num));
    }

    @Test
    public void plusOne1Test() {
        int[] num = {9, 9};
        int[] res = solution.plusOne1(num);
        for (int l : res) {
            System.out.print(l + " ");
        }
    }

    @Test
    public void addNegabinaryTest() {
        int[] arr1 = {1, 1, 1, 1, 1};
        int[] arr2 = {1, 0, 1};
        int[] res = solution.addNegabinary(arr1, arr2);
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

class Solution {

    /*
     * @Description  2409. 统计共同度过的日子数
     * @author   Edison
     * @date    2023/4/17 11:08
     * @Param   [arriveAlice, leaveAlice, arriveBob, leaveBob]
     * @return  int
     */
    int[] month = {0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int day = 0;
        int d1 = getDay(arriveAlice);
        int l1 = getDay(leaveAlice);
        int d2 = getDay(arriveBob);
        int l2 = getDay(leaveBob);
        if (d2 <= l1 && d2 > d1 && l2 > l1) {
            day = l1 - d2 + 1;
        } else if (d2 >= d1 && l2 <= l1) {
            day = l2 - d2 + 1;
        } else if (d1 > d2 && l2 > d1 && l2 < l1) {
            day = l2 - d1 + 1;
        } else if (d1 > d2 && l1 < l2) {
            day = l1 - d1 + 1;
        }
        return day;
    }
    int getDay(String date) {
        int day = 0;
        int mon = Integer.parseInt(date.substring(0, 2));
        for (int i = 1; i < mon; i++) {
            day += month[i];
        }
        day += Integer.parseInt(date.substring(3,5));
        return day;
    }

    /*
     * @Description  1026. 节点与其祖先之间的最大差值
     * @author   Edison
     * @date    2023/4/18 10:55
     * @Param   [root]
     * @return  int
     */
    int res = 0;
    public int maxAncestorDiff(TreeNode root) {
        dfs(root, root.val, root.val);
        return res;
    }
    void dfs(TreeNode root, int max, int min) {
        if (root == null) {
            return;
        }
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        dfs(root.left, max, min);
        dfs(root.right, max, min);
        res = Math.max(res, max - min);
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int num = x;
        int digit = 0;
        int res = 0;
        while (num > 0) {
            digit = num % 10;
            res = res * 10 + digit;
            num /= 10;
        }
        return res == x;
    }

    public int romanToInt(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                if (i < s.length() - 1 && s.charAt(i + 1) == 'V') {
                    num += 4;
                    i++;
                    continue;
                }
                if (i < s.length() - 1 && s.charAt(i + 1) == 'X') {
                    num += 9;
                    i++;
                    continue;
                }
                num += 1;
            } else if (s.charAt(i) == 'V') {
                num += 5;
            } else if (s.charAt(i) == 'X') {
                if (i < s.length() - 1 && s.charAt(i + 1) == 'L') {
                    num += 40;
                    i++;
                    continue;
                }
                if (i < s.length() - 1 && s.charAt(i + 1) == 'C') {
                    num += 90;
                    i++;
                    continue;
                }
                num += 10;
            } else if (s.charAt(i) == 'L') {
                num += 50;
            } else if (s.charAt(i) == 'C') {
                if (i < s.length() - 1 && s.charAt(i + 1) == 'D') {
                    num += 400;
                    i++;
                    continue;
                }
                if (i < s.length() - 1 && s.charAt(i + 1) == 'M') {
                    num += 900;
                    i++;
                    continue;
                }
                num += 100;
            } else if (s.charAt(i) == 'D') {
                num += 500;
            } else if (s.charAt(i) == 'M') {
                num += 1000;
            }
        }
        return num;
    }

    public int smallestEvenMultiple(int n) {
        return n % 2 == 1 ? n * 2 : n;
    }

    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        StringBuffer sb = new StringBuffer();
        String str = strs[0];
        String str1 = strs[strs.length - 1];
        for (int i = 0; i < str.length() && i < str1.length(); i++) {
            if (str.charAt(i) != str1.charAt(i)) {
                break;
            }
            sb.append(str.charAt(i));
        }
        return new String(sb.toString());
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode res = list1.val < list2.val ? list1 : list2;
        res.next = mergeTwoLists(res.next, list1.val >= list2.val ? list1 : list2);
        return res;
    }

    public String lastSubstring(String s) {
        int left = 0;
        int right = 1;
        int i = left, j = right;
        while (j < s.length()) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 > c2) {
                right = j + 1;
                i = left;
                j++;
            } else if (c1 < c2) {
                left = Math.max(i + 1, right);
                right = left + 1;
                i = left;
                j = right;
            } else {
                i++;
                j++;
            }
        }
        return s.substring(left);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int top = 0;
        int bot = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (left <= right && top <= bot) {
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bot; i++) {
                list.add(matrix[i][right]);
            }
            right--;

            for (int i = right; i >= left && top <= bot; i--) {
                list.add(matrix[bot][i]);
            }
            bot--;

            for (int i = bot; i >= top && left <= right; i--)
            {
                list.add(matrix[i][left]);
            }
            left++;
        }
        return list;
    }

    public double myPow(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode cur = head;
        while (k-- > 0) {
            cur = cur.next;
        }
        while (cur != null) {
            head = head.next;
            cur = cur.next;
        }
        return head;
    }

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

    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] += 1;
        return digits;
    }

    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        StringBuilder sb = new StringBuilder();
        int lenA = a.length() - 1;
        int lenB = b.length() - 1;
        int c = 0;
        while (lenA >= 0 || lenB >= 0 || c > 0) {
            if (lenA >= 0) c += a.charAt(lenA--) - '0';
            if (lenB >= 0) c += b.charAt(lenB--) - '0';
            sb.append(c % 2);
            c /= 2;
        }
        return new String(sb.reverse());
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = head;
        while (pre != null && pre.next != null) {
            if (pre.val == pre.next.val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return head;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i < 0 || nums2[j] > nums1[i]) {
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }
        }
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> path = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    path.add(1);
                } else {
                    path.add(list.get(i - 2).get(j) + list.get(i - 2).get(j - 1));
                }
            }
            list.add(new ArrayList<>(path));
        }
        return list;
    }

    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> path = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    path.add(1);
                } else {
                    path.add(list.get(i - 1).get(j) + list.get(i - 1).get(j - 1));
                }
            }
            if (i == rowIndex) {
                return path;
            }
            list.add(new ArrayList<>(path));
        }
        return null;
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                sb.append(s.charAt(i));
            }
        }
        int left = 0;
        int right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public int integerReplacement(int n) {
        int count = 0;
        long temp = n;
        while (temp != 1) {
            if ((temp & 3) == 3 && temp != 3) {
                temp++;
            } else if ((temp & 1) == 1) {
                temp--;
            } else {
                temp >>= 1;
            }
            count++;
        }
        return count;
    }

    public int singleNumber(int[] nums) {
        int temp = 0;
        for (int num : nums) {
            temp ^= num;
        }
        return temp;
    }

    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public int majorityElement(int[] nums) {
        int count = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    num = nums[i + 1];
                }
            }
        }
        return num;
    }

    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode Ahead = headA;
        ListNode Bhead = headB;
        int lenA = 0;
        int lenB = 0;
        while (Ahead != null) {
            lenA++;
            Ahead = Ahead.next;
        }
        while (Bhead != null) {
            lenB++;
            Bhead = Bhead.next;
        }
        Ahead = headA;
        Bhead = headB;
        int len = lenA - lenB;
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
            if (Ahead == Bhead) {
                return Ahead;
            }
            Ahead = Ahead.next;
            Bhead = Bhead.next;
        }
        return null;
    }

    int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return Math.max(left, right) + 1;
    }

    int getHigh(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHigh(root.left);
        if (left == -1) {
            return -1;
        }
        int right = getHigh(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for (int i = 0, j = 0; i < s.length(); i++, j++) {
            if (!map1.containsKey(s.charAt(i))) {
                map1.put(s.charAt(i), t.charAt(j));
            }
            if (!map2.containsKey(t.charAt(j))) {
                map2.put(t.charAt(j), s.charAt(i));
            }
            if (map1.get(s.charAt(i)) != t.charAt(j) || map2.get(t.charAt(j)) != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsDuplicate(int[] nums) {
//        Arrays.sort(nums);
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i] == nums[i - 1]) {
//                return true;
//            }
//        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            boolean flag = set.add(num);
            if (!flag) {
                return true;
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            if (!(i + 1 < nums.length && nums[i] == nums[i + 1] - 1)) {
                if (sb.length() > 0) {
                    sb.append("->");
                }
                sb.append(nums[i]);
                list.add(sb.toString());
                sb = new StringBuilder();
            } else {
                if (sb.length() == 0) {
                    sb.append(nums[i]);
                }
            }
        }
        return list;
    }

    public boolean isPowerOfTwo(int n) {
        if (n == 1) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        if (n % 2 != 0) {
            return false;
        }
        return isPowerOfTwo(n / 2);
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverse(slow.next);
        while (slow != null) {
            if (slow.val != head.val) {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;
    }
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    public int addDigits(int num) {
        if (num / 10 == 0) {
            return num;
        }
        int temp = 0;
        temp += num % 10 + addDigits(num / 10);
        return addDigits(temp);
    }

    public boolean isUgly(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }

    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    public boolean wordPattern(String pattern, String s) {
        if (pattern == null || s == null) {
            return false;
        }
        String[] string = s.split(" ");
        if (string.length != pattern.length()) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char temp = pattern.charAt(i);
            if (map.containsKey(temp)) {
                if (!map.get(temp).equals(string[i])) {
                    return false;
                }
            } else {
                if (map.containsValue(string[i])) {
                    return false;
                }
                map.put(temp, string[i]);
            }
        }
        return true;
    }

    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int ids = map.getOrDefault(num, 0);
            map.put(num, ids + 1);
            if (list.size() <= ids) {
                list.add(new ArrayList<>());
            }
            list.get(ids).add(num);
        }
        return list;
    }

    int count;
    public long numberOfWays(String s) {
//        count = 0;
//        LinkedList<Character> list = new LinkedList<>();
//        waysTraversal(s, list, 0);
//        return count;
        char[] chars = s.toCharArray();
        char[] chars1 = {'0', '1', '0'};
        char[] chars2 = {'1', '0', '1'};
        return help(chars, chars1) + help(chars, chars2);
    }
    void waysTraversal(String s, LinkedList<Character> list, int index) {
        if (list.size() == 3) {
            count++;
            return;
        }
        for (int i = index; i < s.length() - list.size(); i++) {
            if (list.size() > 0 && list.getLast() == s.charAt(i)) {
                continue;
            }
            list.add(s.charAt(i));
            waysTraversal(s, list, i + 1);
            list.removeLast();
        }
    }

    public long help(char[] chars, char[] goal) {
        long a = 0, b = 0, c = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == goal[0]) a++;
            if (chars[i] == goal[1]) b += a;
            if (chars[i] == goal[2]) c += b;
        }
        return c;
    }

    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0, mx = values[0] + 0;
        for (int j = 1; j < values.length; j++) {
            ans = Math.max(ans, mx + values[j] - j);
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }

    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        int sum = 0;
        for (int i = 1; i <= k; i++) {
            sum = (sum * 10 + 1) % k;
            if (sum == 0) {
                return i;
            }
        }
        return -1;
    }

    public boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            String str = getBinary(i);
            if (str.length() > s.length()) {
                return false;
            }
            if (!s.contains(str)) {
                return false;
            }
        }
        return true;
    }
    String getBinary(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 1) {
            sb.append(n % 2);
            n /= 2;
        }
        sb.append(n);
        sb.reverse();
        return sb.toString();
    }

    public int[] plusOne1(int[] digits) {
//        LinkedList<Integer> list = new LinkedList<>();
//        for (int i = digits.length - 1; i >= 0; i--) {
//            if (i == digits.length - 1) {
//                digits[i]++;
//            }
//            if (digits[i] == 10) {
//                digits[i] = 0;
//                if (i > 0) {
//                    digits[i - 1]++;
//                } else {
//                    list.add(digits[i]);
//                    list.add(0, 1);
//                    break;
//                }
//            }
//            list.add(0, digits[i]);
//        }
//        return list.stream().mapToInt(Integer::valueOf).toArray();
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] temp = new int[digits.length + 1];
        temp[0] = 1;
        return temp;
    }

    public boolean haveConflict(String[] event1, String[] event2) {
        return !(event1[1].compareTo(event2[0]) < 0 || event1[0].compareTo(event2[1]) > 0);
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        if (root == null) {
            return list;
        }
        path.add(root.val);
        pathSumBack(root, target, list, path);
        return list;
    }

    void pathSumBack(TreeNode root, int target, List<List<Integer>> list, LinkedList<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && target == root.val) {
            list.add(new ArrayList<>(path));
        }
        pathSumBack(root.left, target - root.val, list, path);
        pathSumBack(root.right, target - root.val, list, path);
        path.removeLast();
    }

    public int[] addNegabinary(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        if (arr1.length < arr2.length) {
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int index = 0;
        while (i >= 0) {
            int tmp = arr1[i] + index;
            if (j >= 0) tmp += arr2[j];
            index = 0;
            if (tmp == -1) {
                index = 1;
                tmp = 1;
            } else if (tmp >= 2) {
                index = -1;
                tmp -= 2;
            }
            list.add(0, tmp);
            i--;
            j--;
        }
        if (index == 1) list.add(0, 1);
        else if (index == -1) {
            list.add(0, 1);
            list.add(0, 1);
        }
        int l = 0;
        while (l < list.size() - 1 && list.get(l) == 0) list.remove(l);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length() + 1][s.length() + 1];
        int maxLen = 0;
        int left = 0;
        int right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            return root.val < limit ? null : root;
        }
        root.left = sufficientSubset(root.left, limit - root.val);
        root.right = sufficientSubset(root.right, limit - root.val);
        return root.left == null && root.right == null ? null : root;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int[][] dir = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        grid[0][0] = 1;
        int path = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur= queue.poll();
                int x = cur[0];
                int y = cur[1];
                if (x == m - 1 && y == n - 1) {
                    return path;
                }
                for (int[] d : dir) {
                    int x1 = x + d[0];
                    int y1 = y + d[1];
                    if (x1 < 0 || x1 >= m || y1 < 0 || y1 >= m || grid[x1][y1] == 1) {
                        continue;
                    }
                    queue.add(new int[]{x1, y1});
                    grid[x1][y1] = 1;
                }
            }
            path++;
        }
        return -1;
    }
}

class MinStack {

    Stack<Integer> stack;
    int min;

    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        stack.push(min);
        min = min > val ? val : min;
        stack.push(val);
    }

    public void pop() {
        stack.pop();
        min = stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}


