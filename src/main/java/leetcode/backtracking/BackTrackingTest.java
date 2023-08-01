package leetcode.backtracking;


import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/20 10:40
 */
public class BackTrackingTest {

    Solution solution = new Solution();

    @Test
    public void combineTest() {
        List<List<Integer>> list = new ArrayList<>();
        list = solution.combine(4, 2);
        for (List<Integer> l1 : list) {
            for (int num : l1) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

}

class Solution {

    /*
     * @Description  77. 组合
     * @author   Edison
     * @date    2023/4/20 10:41
     * @Param   [n, k]
     * @return  List<List<Integer>>
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        combineBackTracking(n, k, list, path, 1);
        return list;
    }
    void combineBackTracking(int n, int k, List<List<Integer>> list, LinkedList<Integer> path, int index) {
        if (path.size() == k) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            combineBackTracking(n, k , list, path, i + 1);
            path.removeLast();
        }
    }

    /*
     * @Description  216. 组合总和 III
     * @author   Edison
     * @date    2023/4/20 11:10
     * @Param   [k, n]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        combinationSum3BackTracking(k, n, 1, list, path);
        return list;
    }
    void combinationSum3BackTracking(int k, int n, int startIndex, List<List<Integer>> list, LinkedList<Integer> path) {
        if (n < 0) {
            return;
        }
        if (path.size() == k) {
            if (n == 0) {
                list.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            combinationSum3BackTracking(k, n - i, i + 1, list, path);
            path.removeLast();
        }
    }

    /*
     * @Description  17. 电话号码的字母组合
     * @author   Edison
     * @date    2023/4/20 11:20
     * @Param   [digits]
     * @return  java.util.List<java.lang.String>
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return list;
        }
        String[] strings = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letterCombinationsBackTracking(digits, 0, list, strings);
        return list;
    }
    StringBuffer sb = new StringBuffer();
    void letterCombinationsBackTracking(String digits, int index, List<String> list, String[] strings) {
        if (index == digits.length()) {
            list.add(sb.toString());
            return;
        }
        String str = strings[digits.charAt(index) - '0'];
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            letterCombinationsBackTracking(digits, index + 1, list, strings);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /*
     * @Description  39. 组合总和
     * @author   Edison
     * @date    2023/4/21 10:41
     * @Param   [candidates, target]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        combinationSumBackTracking(candidates, target, list, path, 0);
        return list;
    }
    void combinationSumBackTracking(int[] candidates, int target, List<List<Integer>> list, LinkedList<Integer> path, int startIndex) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            list.add(new ArrayList<>(path));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            combinationSumBackTracking(candidates, target - candidates[i], list, path, i);
            path.removeLast();
        }
    }

    /*
     * @Description  40. 组合总和 II
     * @author   Edison
     * @date    2023/4/21 10:51
     * @Param   [candidates, target]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        combinationSum2BackTracking(candidates, target, list, path, 0);
        return list;
    }
    void combinationSum2BackTracking(int[] candidates, int target, List<List<Integer>> list, LinkedList<Integer> path, int startIndex) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            list.add(new ArrayList<>(path));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            combinationSum2BackTracking(candidates, target - candidates[i], list, path, i + 1);
            path.removeLast();
        }
    }

    /*
     * @Description  131. 分割回文串
     * @author   Edison
     * @date    2023/4/21 11:03
     * @Param   [s]
     * @return  java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        partitionBackTracking(s, list, path, 0);
        return list;
    }
    void partitionBackTracking(String s, List<List<String>> list, LinkedList<String> path, int startIndex) {
        if (startIndex >= s.length()) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPartition(s, startIndex, i)) {
                path.add(s.substring(startIndex, i + 1));
                partitionBackTracking(s, list, path, i + 1);
            } else {
                continue;
            }
            path.removeLast();
        }
    }
    boolean isPartition(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  93. 复原 IP 地址
     * @author   Edison
     * @date    2023/4/21 11:18
     * @Param   [s]
     * @return  java.util.List<java.lang.String>
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();
        if (s.length() > 12) {
            return list;
        }
        IpAddresses(s, list, 0, 0);
        return list;
    }
    void  IpAddresses(String s, List<String> list, int startIndex, int pointNum) {
        if (pointNum == 3) {
            if (isValid(s, startIndex, s.length() - 1)) {
                list.add(s);
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                IpAddresses(s, list, i + 2, pointNum + 1);
                s= s.substring(0, i + 1) + s.substring(i + 2);
            } else {
                break;
            }
        }
    }
    private boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') {
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  78. 子集
     * @author   Edison
     * @date    2023/4/21 11:38
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        subsetsBackTracking(nums, list, path, 0);
        return list;
    }
    void subsetsBackTracking(int[] nums, List<List<Integer>> list, LinkedList<Integer> path,int startIndex) {
        list.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsBackTracking(nums, list, path, i + 1);
            path.removeLast();
        }
    }

    /*
     * @Description  90. 子集 II
     * @author   Edison
     * @date    2023/4/24 13:03
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Arrays.sort(nums);
        subsetsWithDupBack(nums, 0, list, path);
        return list;
    }
    void subsetsWithDupBack(int[] nums, int startIndex, List<List<Integer>> list, LinkedList<Integer> path) {
        list.add(new ArrayList<>(path));
        if (startIndex >= nums.length) {
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            subsetsWithDupBack(nums, i + 1, list, path);
            path.removeLast();
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        findSubsequencesBack(nums, 0, list, path);
        return list;
    }
    void findSubsequencesBack(int[] nums, int startIndex, List<List<Integer>> list, LinkedList<Integer> path) {
        if (path.size() > 1) {
            list.add(new ArrayList<>(path));
        }
        int used[] = new int[201];
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast() || (used[nums[i] + 100] == 1)) {
                continue;
            }
            used[nums[i] + 100] = 1;
            path.add(nums[i]);
            findSubsequencesBack(nums, i + 1, list, path);
            path.removeLast();
        }
    }

    /*
     * @Description  46. 全排列
     * @author   Edison
     * @date    2023/4/24 13:28
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        permuteBackTracking(nums, list, path, used);
        return list;
    }
    void permuteBackTracking(int[] nums, List<List<Integer>> list, LinkedList<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            permuteBackTracking(nums, list, path, used);
            used[i] = false;
            path.removeLast();
        }
    }

    /*
     * @Description  47. 全排列 II
     * @author   Edison
     * @date    2023/4/24 13:39
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUniqueBackTracking(nums, list, path, used);
        return list;
    }
    void permuteUniqueBackTracking(int[] nums, List<List<Integer>> list, LinkedList<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && used[i - 1] == false && nums[i] == nums[i - 1])) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            permuteUniqueBackTracking(nums, list, path, used);
            used[i] = false;
            path.removeLast();
        }
    }

    /*
     * @Description  332. 重新安排行程
     * @author   Edison
     * @date    2023/4/26 9:41
     * @Param   [tickets]
     * @return  java.util.List<java.lang.String>
     */
    LinkedList<String> res;
    LinkedList<String> path = new LinkedList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a,b) -> a.get(1).compareTo(b.get(1)));
        path.add("JFK");
        boolean[] used = new boolean[tickets.size()];
        bcakTracking((ArrayList) tickets, used);
        return res;
    }
    boolean bcakTracking(List<List<String>> tickets, boolean[] used) {
        if (tickets.size() + 1 == path.size()) {
            res = new LinkedList<>(path);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(path.getLast())) {
                path.add(tickets.get(i).get(1));
                used[i] = true;
                if (bcakTracking(tickets, used)) {
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
     * @date    2023/4/26 10:22
     * @Param   [n]
     * @return  java.util.List<java.util.List<java.lang.String>>
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        char[][] chessBoard = new char[n][n];
        for (char[] c : chessBoard) {
            Arrays.fill(c, '.');
        }
        backTracking(n, 0, chessBoard, list);
        return list;
    }
    void backTracking(int n, int row, char[][] chessBoard, List<List<String>> list) {
        if (row == n) {
            list.add(ArrayList2(chessBoard));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, chessBoard)) {
                chessBoard[row][col] = 'Q';
                backTracking(n, row + 1, chessBoard, list);
                chessBoard[row][col] = '.';
            }
        }
    }
    List ArrayList2(char[][] chessBoard) {
        List<String> list = new ArrayList<>();
        for (char[] c : chessBoard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }
    boolean isValid(int row, int col, int n, char[][] chessBoard) {
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  37. 解数独
     * @author   Edison
     * @date    2023/4/26 10:29
     * @Param   [board]
     * @return  void
     */
    public void solveSudoku(char[][] board) {
        suDuBack(board);
    }
    boolean suDuBack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                for (char ch = '1'; ch <= '9'; ch++) {
                    if (isValid(i, j, board, ch)) {
                        board[i][j] = ch;
                        if (suDuBack(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }
    boolean isValid(int row, int col, char[][] board, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == ch) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == ch) {
                return false;
            }
        }
        int h = row / 3 * 3;
        int l = col / 3 * 3;
        for (int i = h; i < h + 3; i++) {
            for (int j = l; j < l + 3; j++) {
                if (board[i][j] == ch) {
                    return false;
                }
            }
        }
        return true;
    }

}
