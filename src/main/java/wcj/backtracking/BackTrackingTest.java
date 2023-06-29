package wcj.backtracking;

import org.testng.annotations.Test;

import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/3/16 10:28
 */
public class BackTrackingTest {

    Solution solution = new Solution();

    @Test
    public void combinationSum3Test() {

    }

    @Test
    public void combinationSumTest() {
        int[] candidates = {2,3,5};
        int target = 8;
        List<List<Integer>> list = new ArrayList<>();
        list = solution.combinationSum(candidates,target);
        for (List<Integer> l : list) {
            System.out.println(l);
        }
    }

    @Test
    public void combinationSum2Test() {
        int[] candidates = {10,1,2,7,6,1,5};
        List<List<Integer>> list;
        list = solution.combinationSum2(candidates,8);
        for (List<Integer> l : list) {
            System.out.println(l);
        }
    }

    @Test
    public void subsetsWithDupTest() {
        List<List<Integer>> list;
        int[] nums = {1,2,2};
        list = solution.subsetsWithDup(nums);
        for (List<Integer> l : list) {
            System.out.println(l);
        }
    }

    @Test
    public void permuteTest() {
        List<List<Integer>> list;
        int[] nums = {0,1};
        list = solution.permute(nums);
        for (List<Integer> l : list) {
            System.out.println(l);
        }
    }

    @Test
    public void permuteUniqueTest() {
        List<List<Integer>> list;
        int[] nums = {1,1,2};
        list = solution.permuteUnique(nums);
        for (List<Integer> l : list) {
            System.out.println(l);
        }
    }

    @Test
    public void solveNQueensTest() {
        List<List<String>> lists;
        lists = solution.solveNQueens(3);
        for (List<String> list : lists) {
            System.out.println(list);
        }
    }

    @Test
    public void solveSudokuTest() {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}
        };
        solution.solveSudoku(board);
        for (char[] c : board) {
            System.out.println(c);
        }
    }

}

class Solution {
    /*
     * @Description  77. 组合
     * @author   Edison
     * @date    2023/3/16 10:29
     * @Param   [n, k]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    List<List<Integer>> list = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        list.clear();
        path.clear();
        combineBackTracking(n, k, 1);
        return list;
    }
    void combineBackTracking(int n, int k, int startIndex) {
        if (path.size() == k) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < n - (k - path.size()) + 1; i++) {
            path.add(i);
            combineBackTracking(n, k, i + 1);
            path.removeLast();
        }
    }

    /*
     * @Description  216. 组合总和 III
     * @author   Edison
     * @date    2023/3/16 11:24
     * @Param
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        list.clear();
        path.clear();
        combinationSum3BackTracking(n, k, 0, 1);
        return list;
    }
    void combinationSum3BackTracking(int target, int k, int sum, int startIndex) {
        if (sum > target) {
            return;
        }
        if (path.size() == k) {
            if (sum == target) {
                list.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            combinationSum3BackTracking(target, k, sum, i + 1);
            sum -= i;
            path.removeLast();
        }
    }

    /*
     * @Description  17. 电话号码的字母组合
     * @author   Edison
     * @date    2023/3/16 15:53
     * @Param
     * @return
     */
    List<String> result = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }
        String[] numString = {"","","abc", "def", "ghi", "jkl", "mno","pqrs", "tuv", "wxyz"};
        letterCombinationsBackTracking(digits, numString, 0);
        return result;
    }
    StringBuffer sb = new StringBuffer();
    void letterCombinationsBackTracking(String digits, String[] numString, int index) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }
        String str = numString[digits.charAt(index) - '0'];
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            letterCombinationsBackTracking(digits, numString, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /*
     * @Description  39. 组合总和
     * @author   Edison
     * @date    2023/3/16 16:25
     * @Param
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        list.clear();
        path.clear();
        Arrays.sort(candidates);
        combinationSumBackTracking(candidates, target, 0, 0);
        return list;
    }
    void combinationSumBackTracking(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            list.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) return;
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            combinationSumBackTracking(candidates, target, sum, i);
            sum -= candidates[i];
            path.removeLast();
        }
    }
    
    /*
     * @Description  40. 组合总和 II
     * @author   Edison
     * @date    2023/3/16 16:52
     * @Param
     * @return  
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        list.clear();
        path.clear();
//        boolean[] used = new boolean[candidates.length];
//        Arrays.fill(used, false);
        Arrays.sort(candidates);
//        combinationSum2BackTracking(candidates, target, 0, 0, used);
        combinationSum2BackTracking(candidates, target, 0, 0);
        return list;
    }
//    void combinationSum2BackTracking(int[] candidates, int target, int startIndex, int sum, boolean[] used) {
    void combinationSum2BackTracking(int[] candidates, int target, int startIndex, int sum) {
        if (sum == target) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
//            if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == false) {
//                continue;
//            }
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            sum += candidates[i];
//            used[i] = true;
//            combinationSum2BackTracking(candidates, target, i + 1, sum, used);
            combinationSum2BackTracking(candidates, target, i + 1, sum);
//            used[i] = false;
            sum -= candidates[i];
            path.removeLast();
        }
    }
    
    /*
     * @Description  131. 分割回文串
     * @author   Edison
     * @date    2023/3/17 11:23
     * @Param
     * @return  
     */
    public List<List<String>> partition(String s) {
        List<List<String>> lists = new ArrayList<>();
        List<String> path = new ArrayList<>();
        partitionBackTracking(s, lists, path, 0);
        return lists;
    }
    void partitionBackTracking(String s, List<List<String>> list, List<String> path, int startIndex) {
        if (startIndex >= s.length()) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrome(s, startIndex, i)) {
                path.add(s.substring(startIndex, i + 1));
                partitionBackTracking(s, list, path, i + 1);
            } else {
                continue;
            }
            path.remove(path.size() - 1);
        }
    }
    boolean isPalindrome(String s, int start, int end) {
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
     * @date    2023/3/17 15:17
     * @Param
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> list = new ArrayList<>();
        restoreIpAddressesBackTracking(s, list, 0, 0);
        return list;
    }
    //pointNUm 逗点的数量
    void restoreIpAddressesBackTracking(String s, List<String> list, int startIndex, int pointNUm) {
        if (pointNUm == 3) {
            if (isValid(s, startIndex, s.length() - 1)){
                list.add(s);
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s, startIndex, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                pointNUm++;
                restoreIpAddressesBackTracking(s, list, i + 2, pointNUm);
                s = s.substring(0, i + 1) + s.substring(i + 2);
                pointNUm--;
            } else {
                break;
            }
        }
    }
    boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (int i = start; i < end; i++) {
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
     * @date    2023/3/17 15:54
     * @Param   
     * @return  
     */
    public List<List<Integer>> subsets(int[] nums) {
        subsetsBackTracking(nums, 0);
        return list;
    }
    void subsetsBackTracking(int[] nums, int startIndex) {
        list.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsBackTracking(nums, i + 1);
            path.removeLast();
        }
    }

    /*
     * @Description  90. 子集 II
     * @author   Edison
     * @date    2023/3/20 13:22
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        subsetsWithDupBackTracking(nums, 0);
        return  list;
    }
    void subsetsWithDupBackTracking(int[] nums, int startIndex) {
        list.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            subsetsWithDupBackTracking(nums, i + 1);
            path.removeLast();
        }
    }

    /*
     * @Description  491. 递增子序列
     * @author   Edison
     * @date    2023/3/20 13:39
     * @Param
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        findSubsequencesBackTracking(nums, 0);
        return list;
    }
    void findSubsequencesBackTracking(int[] nums, int startIndex) {
        if (path.size() > 1) {
            list.add(new ArrayList<>(path));
        }
        int[] used = new int[201];
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) || (used[nums[i] + 100] == 1)) {
                continue;
            }
            used[nums[i] + 100] = 1;
            path.add(nums[i]);
            findSubsequencesBackTracking(nums, i + 1);
            path.removeLast();
        }
    }

    /*
     * @Description  46. 全排列
     * @author   Edison
     * @date    2023/3/20 15:23
     * @Param
     * @return
     */

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        permuteBackTracking(nums, used);
        return list;
    }
    void permuteBackTracking(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            permuteBackTracking(nums, used);
            used[i] = false;
            path.removeLast();
        }
    }
    
    /*
     * @Description  47. 全排列 II
     * @author   Edison
     * @date    2023/3/20 15:47
     * @Param
     * @return  
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        permuteUniqueBackTracking(nums, used, lists, path);
        return lists;
    }

    void permuteUniqueBackTracking(int[] nums, boolean[] used, List<List<Integer>> list, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            list.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
           if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
               continue;
           }
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                permuteUniqueBackTracking(nums, used, list, path);
                path.removeLast();
                used[i] = false;
            }
        }
    }
    
    /*
     * @Description  332. 重新安排行程
     * @author   Edison
     * @date    2023/3/21 10:13
     * @Param
     * @return  
     */
    private LinkedList<String> res;
    private LinkedList<String> path1 = new LinkedList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
        path1.add("JFK");
        boolean[] used = new boolean[tickets.size()];
        findItineraryBackTracking((ArrayList) tickets, used);
        return res;
    }

    boolean findItineraryBackTracking(ArrayList<List<String>> tickets, boolean[] used) {
        if (path1.size() == tickets.size() + 1) {
            res = new LinkedList<>(path1);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(path1.getLast())) {
                path1.add(tickets.get(i).get(1));
                used[i] = true;
                if (findItineraryBackTracking(tickets, used)) {
                    return true;
                }
                used[i] = false;
                path1.removeLast();
            }
        }
        return false;
    }

    /*
     * @Description  51. N 皇后
     * @author   Edison
     * @date    2023/3/21 11:44
     * @Param
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        char[][] chessBoard = new char[n][n];
        for (char[] c : chessBoard) {
            Arrays.fill(c, '.');
        }
        solveNQueensBackTracking(n,  list, chessBoard, 0);
        return list;
    }
    void solveNQueensBackTracking(int n, List<List<String>> list, char[][] chessBoard, int row) {
        if (row == n){
            list.add(ArraysList2(chessBoard));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, chessBoard)) {
                chessBoard[row][col] = 'Q';
                solveNQueensBackTracking(n, list, chessBoard, row + 1);
                chessBoard[row][col] = '.';
            }
        }

    }
    public List<String> ArraysList2(char[][] chessBoard) {
        List<String> list = new ArrayList<>();
        for (char[] c : chessBoard) {
            list.add(new String(c));
        }
        return list;
    }
    boolean isValid(int row, int col, int n, char[][] chessBoard) {
        //
        for (int i = 0; i < row; i++) {
            if (chessBoard[i][col] == 'Q') {
                return false;
            }
        }
        //
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        //
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
            if (chessBoard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  37. 解数独
     * @author   Edison
     * @date    2023/3/21 15:33
     * @Param
     * @return
     */
    public void solveSudoku(char[][] board) {
        solveSudokuBackTracking(board);
    }
    boolean solveSudokuBackTracking(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.') {
                    continue;
                }
                for (char z = '1'; z <= '9'; z++) {
                    if (isValid(board, i, j, z)) {
                        board[i][j] = z;
                        if (solveSudokuBackTracking(board)) {
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
    boolean isValid(char[][] board, int row, int col, char z) {
        //同一行
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == z) {
                return false;
            }
        }
        //同一列
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == z) {
                return false;
            }
        }
        //同一个九宫格
        int h = row / 3 * 3;
        int l = col / 3 * 3;
        for (int i = h; i < h + 3; i++) {
            for (int j = l; j < l + 3; j++) {
                if (board[i][j] == z) {
                    return false;
                }
            }
        }
        return true;
    }
}
