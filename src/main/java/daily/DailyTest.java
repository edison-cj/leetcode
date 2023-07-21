package daily;

import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.util.*;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/5/29 11:36
 */
public class DailyTest {
    Solution solution = new Solution();

    @Test
    public void equalPairsTest() {
        int[][] grid = {{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};
        System.out.println(solution.equalPairs(grid));
    }
}

class Solution {

    /*
     * @Description  2455. 可被三整除的偶数的平均值
     * @author   Edison
     * @date    2023/5/29 11:36
     * @Param   [nums]
     * @return  int
     */
    public int averageValue(int[] nums) {
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            if (num % 3 == 0 && num % 2 == 0) {
                count++;
                sum += num;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

    /*
     * @Description  TDOO
     * @author   Edison
     * @date    2023/5/30 11:11
     * @Param   [root, to_delete]
     * @return  List<TreeNode>
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int num : to_delete) {
            set.add(num);
        }
        dfs(list, null, root, set);
        if (!set.contains(root.val)) list.add(root);
        return list;
    }
    void dfs(List<TreeNode> list, TreeNode parent, TreeNode root, Set<Integer> set) {
        if (root == null) {
            return;
        }
        dfs(list, root, root.left, set);
        dfs(list, root, root.right, set);
        if (set.contains(root.val)) {
            if (parent != null && parent.left == root) parent.left = null;
            if (parent != null && parent.right == root) parent.right = null;

            if (root.left != null) list.add(root.left);
            if (root.right != null) list.add(root.right);
        }
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = words.length;
        int[] prefixSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int val = isVowel(words[i].charAt(0)) && isVowel(words[i].charAt(words[i].length() - 1)) ? 1 : 0;
            prefixSums[i + 1] = prefixSums[i] + val;
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < res.length; i++) {
            int start = queries[i][0], end = queries[i][1];
            res[i] = prefixSums[end + 1] - prefixSums[start];
        }
        return res;
    }
    boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    /*
     * @Description  2352. 相等行列对
     * @author   Edison
     * @date    2023/6/6 15:14
     * @Param   [grid]
     * @return  int
     */
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int[][] grid2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid2[j][i] = grid[i][j];
            }
        }
        for (int[] num : grid2) {
            for (int n1 : num) {
                System.out.print(n1 + " ");
            }
            System.out.println();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isSame(grid[i], grid2[j])) {
                    count++;
                }
            }
        }
        return count;
    }
    boolean isSame(int[] grid1, int[] grid2) {
        int n = grid1.length;
        for (int i = 0; i < n; i++) {
            if (grid1[i] != grid2[i]) {
                return false;
            }
        }
        return true;
    }

    /*
     * @Description  1262. 可被三整除的最大和
     * @author   Edison
     * @date    2023/6/19 11:17
     * @Param   [nums]
     * @return  int
     */
    public int maxSumDivThree(int[] nums) {
        final int inf = 1 << 30;
        int[][] dp = new int[nums.length + 1][3];
        dp[0][1] = dp[0][2] = -inf;
        for (int i = 1; i <= nums.length; i++) {
            int num = nums[i - 1];
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][(j - num % 3 + 3) % 3] + num);
            }
        }
        return dp[nums.length][0];
    }

    /*
     * @Description  剑指 Offer II 007. 数组中和为 0 的三个数
     * @author   Edison
     * @date    2023/6/19 11:37
     * @Param   [nums]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
//        LinkedList<Integer> path = new LinkedList<>();
//        backTrack(list, path, 0, 0, nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] > 0) return list;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                if (sum > 0) right--;
                else if (sum < 0) {
                    left++;
                } else {
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
    void backTrack(List<List<Integer>> list, LinkedList<Integer> path, int index, int sum, int[] nums) {
        if (path.size() == 3) {
            if (sum == 0) {
                list.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            backTrack(list, path, i + 1, sum + nums[i], nums);
            path.removeLast();
        }
    }

    /*
     * @Description  剑指 Offer II 008. 和大于等于 target 的最短子数组
     * @author   Edison
     * @date    2023/6/19 14:30
     * @Param   [target, nums]
     * @return  int
     */
    public int minSubArrayLen(int target, int[] nums) {
        int count = nums.length + 1;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            target -= nums[fast];
            while (target <= 0) {
                if (fast - slow + 1 < count) {
                    count = fast - slow + 1;
                }
                target += nums[slow++];
            }
        }
        return count == nums.length + 1 ? 0 : count;
    }

    /*
     * @Description  2485. 找出中枢整数
     * @author   Edison
     * @date    2023/6/26 10:44
     * @Param   [n]
     * @return  int
     */
    public int pivotInteger(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        int left = 0;
        for (int i = 1; i <= n; i++) {
            left += i;
            if (left == sum) return i;
            sum -= i;
        }
        return -1;
    }

    /*
     * @Description  1253. 重构 2 行二进制矩阵
     * @author   Edison
     * @date    2023/6/29 11:28
     * @Param   [upper, lower, colsum]
     * @return  java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<List<Integer>> list = new ArrayList<>();
        return list;
    }

    /*
     * @Description  2490. 回环句
     * @author   Edison
     * @date    2023/6/30 10:24
     * @Param   [sentence]
     * @return  boolean
     */
    public boolean isCircularSentence(String sentence) {
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) return false;
        for (int i = 0; i < sentence.length(); i++) {
            while (i < sentence.length() && sentence.charAt(i) != ' ') i++;
            if (i < sentence.length()) {
                if (sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * @Description  2679. 矩阵中的和
     * @author   Edison
     * @date    2023/7/4 17:26
     * @Param   [nums]
     * @return  int
     */
    public int matrixSum(int[][] nums) {
        int score = 0;
        for (int[] num : nums) {
            Arrays.sort(num);
        }
        for (int i = 0; i < nums[0].length; i++) {
            int max = 0;
            for (int j = 0; j < nums.length; j++) {
                max = Math.max(max, nums[j][i]);
            }
            score += max;
        }
        return score;
    }

    /*
     * @Description  2600. K 件物品的最大和
     * @author   Edison
     * @date    2023/7/5 10:46
     * @Param   [numOnes, numZeros, numNegOnes, k]
     * @return  int
     */
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int ans = 0;
        while (k > 0 && numOnes-- > 0) {
            k--;
            ans++;
        }
        while (k > 0 && numZeros-- > 0) {
            k--;
        }
        while (k > 0 && numNegOnes-- > 0) {
            k--;
            ans--;
        }
        return ans;
    }

    /*
     * @description: 16. 最接近的三数之和
     * @author: edison 
     * @date: 2023/7/10 11:15
     * @param: [nums, target]
     * @return: int
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < Math.abs(ans - target)) ans = sum;
                if (sum > target) r--;
                else if (sum < target) l++;
                else return target;
            }
        }
        return ans;
    }

    /*
     * @description: 1911. 最大子序列交替和
     * @author: edison 
     * @date: 2023/7/11 11:16
     * @param: [nums]
     * @return: long
     */
    public long maxAlternatingSum(int[] nums) {
        long[] dp = new long[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 1] - nums[i - 1] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    /*
     * @description: 2544. 交替数字和
     * @author: edison 
     * @date: 2023/7/12 11:22
     * @param: [n]
     * @return: int
     */
    public int alternateDigitSum(int n) {
        int flag = 1;
        int sum = 0;
        while (n > 0) {
            sum += n % 10 * flag;
            flag = -flag;
            n /= 10;
        }
        return sum * -flag;
    }

    /*
     * @description: 931. 下降路径最小和
     * @author: edison 
     * @date: 2023/7/13 17:31
     * @param: [matrix]
     * @return: int
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[0][i] = matrix[0][i];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                else if (j == n - 1) dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + matrix[i][j];
                else dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i -1][j -1], dp[i - 1][j + 1])) + matrix[i][j];
            }
        }
        int ans = dp[n - 1][0];
        for (int j = 1; j < n; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }
        return ans;
    }

    /*
     * @description: 979. 在二叉树中分配硬币
     * @author: edison
     * @date: 2023/7/14 17:18
     * @param: [root]
     * @return: int
     */
    int sum = 0;
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return sum;
    }
    int dfs(TreeNode root) {
        if (root == null) return 0;
        int l = dfs(root.left);
        int r = dfs(root.right);
        sum += Math.abs(l) + Math.abs(r);
        return l + r + root.val - 1;
    }

    /*
     * @description: 415. 字符串相加
     * @author: edison 
     * @date: 2023/7/17 18:00
     * @param: [num1, num2]
     * @return: java.lang.String
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            if (i >= 0) carry += num1.charAt(i--) - '0';
            if (j >= 0) carry += num2.charAt(j--) - '0';
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
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
