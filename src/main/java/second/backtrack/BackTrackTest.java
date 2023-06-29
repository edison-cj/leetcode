package second.backtrack;

import javax.print.DocFlavor;
import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/6/12 9:48
 */
public class BackTrackTest {
}

class Solution {

    /*
     * @Description  77. 组合
     * @author   Edison
     * @date    2023/6/12 9:54
     * @Param   [n, k]
     * @return  List<List<Integer>>
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backTrack(list, path, n, k, 1);
        return list;
    }
    void backTrack(List<List<Integer>> list, List<Integer> path, int n, int k, int index) {
        if (path.size() == k) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backTrack(list, path, n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /*
     * @Description  216. 组合总和 III
     * @author   Edison
     * @date    2023/6/12 10:12
     * @Param   [k, n]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backTrack(k, n, 1, 0, list, path);
        return list;
    }
    void backTrack(int k, int n, int index, int sum, List<List<Integer>> list, LinkedList<Integer> path) {
        if (sum > n) {
            return;
        }
        if (path.size() == k) {
            if (sum == n) {
                list.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = index; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            backTrack(k, n, i + 1, sum + i, list, path);
            path.removeLast();
        }
    }

    /*
     * @Description  17. 电话号码的字母组合
     * @author   Edison
     * @date    2023/6/12 10:22
     * @Param   [digits]
     * @return  java.util.List<java.lang.String>
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        String[] str = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTrack(digits, str, 0, list);
        return list;
    }
    StringBuilder sb = new StringBuilder();
    void backTrack(String digits, String[] str, int index, List<String> list) {
        if (index == digits.length()) {
            list.add(sb.toString());
            return;
        }
        String string = str[digits.charAt(index) - '0'];
        for (int i = 0; i < string.length(); i++) {
            sb.append(string.charAt(i));
            backTrack(digits, str, index + 1, list);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /*
     * @Description  39. 组合总和
     * @author   Edison
     * @date    2023/6/13 9:37
     * @Param   [candidates, target]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backTrack(candidates, target, 0, list, path);
        return list;
    }
    void backTrack(int[] candidates, int target, int index, List<List<Integer>> list, LinkedList<Integer> path) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            path.add(candidates[i]);
            backTrack(candidates, target - candidates[i], i, list, path);
            path.removeLast();
        }
    }

    /*
     * @Description  40. 组合总和 II
     * @author   Edison
     * @date    2023/6/13 9:47
     * @Param   [candidates, target]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        backTrack(list, path, candidates, target, 0);
        return list;
    }
    void backTrack(List<List<Integer>> list, LinkedList<Integer> path, int[] candidates, int target, int index) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length && target > 0; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            path.add(candidates[i]);
            backTrack(list, path, candidates,target - candidates[i], i + 1);
            path.removeLast();
        }
    }

    /*
     * @Description  131. 分割回文串
     * @author   Edison
     * @date    2023/6/13 10:07
     * @Param   [s]
     * @return  java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        backTrack(s, 0, list, path);
        return list;
    }
    void backTrack(String s, int index, List<List<String>> list, LinkedList<String> path) {
        if (index >= s.length()) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {
                path.add(s.substring(index, i + 1));
                backTrack(s, i + 1, list, path);
                path.removeLast();
            } else {
                continue;
            }
        }
    }
    boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  93. 复原 IP 地址
     * @author   Edison
     * @date    2023/6/13 10:20
     * @Param   [s]
     * @return  java.util.List<java.lang.String>
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();
        if (s.length() > 12) {
            return list;
        }
        backTrack(list, s, 0, 0);
        return list;
    }
    void backTrack(List<String> list, String s, int index, int pointNum) {
        if (pointNum == 3) {
            if (isValid(s, index, s.length() - 1)) {
                list.add(s);
            }
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isValid(s, index, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                backTrack(list, s, i + 2, pointNum + 1);
                s = s.substring(0, i + 1) + s.substring(i + 2);
            } else {
                break;
            }
        }
    }
    boolean isValid(String s, int start, int end) {
        if (start > end) return false;
        if (s.charAt(start) == '0' && start != end) return false;
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') return false;
            num = num * 10 + s.charAt(i) - '0';
            if (num > 255) return false;
        }
        return true;
    }

    /*
     * @Description  78. 子集
     * @author   Edison
     * @date    2023/6/13 10:42
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backTrack(list, path, 0, nums);
        return list;
    }
    void backTrack(List<List<Integer>> list, LinkedList<Integer> path, int index, int[] nums) {
        list.add(new ArrayList<>(path));
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            backTrack(list, path, i + 1, nums);
            path.removeLast();
        }
    }

    /*
     * @Description  90. 子集 II
     * @author   Edison
     * @date    2023/6/14 10:03
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(nums);
        backTrack(nums, 0, list, path);
        return list;
    }
    void backTrack(int[] nums, int index, List<List<Integer>> list, LinkedList<Integer> path) {
        list.add(new ArrayList<>(path));
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            backTrack(nums, i + 1, list, path);
            path.removeLast();
        }
    }

    /*
     * @Description  491. 递增子序列
     * @author   Edison
     * @date    2023/6/14 10:10
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backTrack(nums, list, path, 0);
        return list;
    }
    void backTrack(int[] nums, List<List<Integer>> list, LinkedList<Integer> path, int index) {
        if (path.size() > 1) {
            list.add(new ArrayList<>(path));
        }
        boolean[] used = new boolean[201];
        for (int i = index; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast() || (used[nums[i] + 100])) continue;
            used[nums[i] + 100] = true;
            path.add(nums[i]);
            backTrack(nums, list, path, i + 1);
            path.removeLast();
        }
    }

    /*
     * @Description  46. 全排列
     * @author   Edison
     * @date    2023/6/14 10:23
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(list, path, used, nums);
        return list;
    }
    void backTrack(List<List<Integer>> list, LinkedList<Integer> path, boolean[] used, int[] nums) {
        if (path.size() == nums.length) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            path.add(nums[i]);
            used[i] = true;
            backTrack(list, path, used, nums);
            path.removeLast();
            used[i] = false;
        }
    }

    /*
     * @Description  47. 全排列 II
     * @author   Edison
     * @date    2023/6/14 10:28
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backTrack(nums, list, path, used);
        return list;
    }
    void backTrack(int[] nums, List<List<Integer>> list, LinkedList<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && !used[i - 1] && nums[i - 1] == nums[i])) continue;
            path.add(nums[i]);
            used[i] = true;
            backTrack(nums, list, path, used);
            used[i] = false;
            path.removeLast();
        }
    }

    /*
     * @Description  332. 重新安排行程
     * @author   Edison
     * @date    2023/6/19 10:04
     * @Param   [tickets]
     * @return  java.util.List<java.lang.String>
     */
    LinkedList<String> path = new LinkedList<>();
    List<String> ans = new ArrayList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
        path.add("JFK");
        boolean[] used = new boolean[tickets.size()];
        backTrack(tickets, used);
        return ans;
    }
    boolean backTrack(List<List<String>> tickets, boolean[] used) {
        if (tickets.size() + 1 == path.size()) {
            ans = new ArrayList<>(path);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(path.getLast())) {
                used[i] = true;
                path.add(tickets.get(i).get(1));
                if (backTrack(tickets, used)) {
                    return true;
                }
                used[i] = false;
                path.removeLast();
            }
        }
        return false;
    }

    /*
     * @Description  51. N 皇后
     * @author   Edison
     * @date    2023/6/19 10:24
     * @Param   [n]
     * @return  java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        char[][] chess = new char[n][n];
        for (char[] c : chess) {
            Arrays.fill(c, '.');
        }
        backTrack(n, 0, list, chess);
        return list;
    }
    void backTrack(int n, int row, List<List<String>> list, char[][] chess) {
        if (row == n) {
            list.add(Arrays2(chess));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, chess)) {
                chess[row][col] = 'Q';
                backTrack(n, row + 1, list, chess);
                chess[row][col] = '.';
            }
        }
    }
    List Arrays2(char[][] chess) {
        List<String> list = new ArrayList<>();
        for (char[] chars : chess) {
            list.add(String.copyValueOf(chars));
        }
        return list;
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
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  37. 解数独
     * @author   Edison
     * @date    2023/6/19 10:48
     * @Param   [board]
     * @return  void
     */
    public void solveSudoku(char[][] board) {
        backTrack(board);
    }
    boolean backTrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char ch = '1'; ch <= '9'; ch++) {
                    if (isValid(board, i, j, ch)) {
                        board[i][j] = ch;
                        if (backTrack(board)) return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }
    boolean isValid(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            if (ch == board[row][i]) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (ch == board[i][col]) {
                return false;
            }
        }
        int r = row / 3 * 3;
        int c = col / 3 * 3;
        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board[i][j] == ch) {
                    return false;
                }
            }
        }
        return true;
    }

}
