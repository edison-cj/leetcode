package second.dynamicprogramming;

import java.util.List;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/6/25 9:51
 */
public class DynamicProgrammingTest {

    Solution solution = new Solution();

}

class Solution {

    /*
     * @Description  70. 爬楼梯
     * @author   Edison
     * @date    2023/6/26 9:56
     * @Param   [n]
     * @return  int
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        if (n == 1) {
            return dp[1];
        }
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /*
     * @Description  746. 使用最小花费爬楼梯
     * @author   Edison
     * @date    2023/6/26 9:59
     * @Param   [cost]
     * @return  int
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[1] = 0;
        dp[0] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }

    /*
     * @Description  62. 不同路径
     * @author   Edison
     * @date    2023/6/26 10:07
     * @Param   [m, n]
     * @return  int
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * @Description  63. 不同路径 II
     * @author   Edison
     * @date    2023/6/26 10:18
     * @Param   [obstacleGrid]
     * @return  int
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < obstacleGrid[0].length && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    /*
     * @Description  343. 整数拆分
     * @author   Edison
     * @date    2023/6/26 10:24
     * @Param   [n]
     * @return  int
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, j * dp[i - j]));
            }
        }
        return dp[n];
    }

    /*
     * @Description  96. 不同的二叉搜索树
     * @author   Edison
     * @date    2023/6/26 10:32
     * @Param   [n]
     * @return  int
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /*
     * @Description  416. 分割等和子集
     * @author   Edison
     * @date    2023/6/28 10:10
     * @Param   [nums]
     * @return  boolean
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }

    /*
     * @Description  1049. 最后一块石头的重量 II
     * @author   Edison
     * @date    2023/6/28 10:26
     * @Param   [stones]
     * @return  int
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int target = sum / 2;
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }

    /*
     * @Description  494. 目标和
     * @author   Edison
     * @date    2023/6/29 9:27
     * @Param   [nums, target]
     * @return  int
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (Math.abs(target) > sum || (target + sum) % 2 == 1) return 0;
        int size = (target + sum) / 2;
        if (size < 0 ) size = -size;
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }

    /*
     * @Description  474. 一和零
     * @author   Edison
     * @date    2023/6/29 9:46
     * @Param   [strs, m, n]
     * @return  int
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeroNum = 0, oneNum = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') zeroNum++;
                else oneNum++;
            }
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }

    /*
     * @Description  518. 零钱兑换 II
     * @author   Edison
     * @date    2023/6/29 10:10
     * @Param   [amount, coins]
     * @return  int
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    /*
     * @Description  377. 组合总和 Ⅳ
     * @author   Edison
     * @date    2023/6/30 9:33
     * @Param   [nums, target]
     * @return  int
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    /*
     * @Description  322. 零钱兑换
     * @author   Edison
     * @date    2023/6/30 9:50
     * @Param   [coins, amount]
     * @return  int
     */
    public int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[i] = max;
        }
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != max) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }

    /*
     * @Description  279. 完全平方数
     * @author   Edison
     * @date    2023/6/30 10:06
     * @Param   [n]
     * @return  int
     */
    public int numSquares(int n) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = max;

        }
        dp[0] = 0;
        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }

    /*
     * @Description  139. 单词拆分
     * @author   Edison
     * @date    2023/7/4 16:00
     * @Param   [s, wordDict]
     * @return  boolean
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (i >= len && dp[i - len] && word.equals(s.substring(i - len, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /*
     * @Description  198. 打家劫舍
     * @author   Edison
     * @date    2023/7/4 16:30
     * @Param   [nums]
     * @return  int
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    /*
     * @Description  213. 打家劫舍 II
     * @author   Edison
     * @date    2023/7/4 16:35
     * @Param   [nums]
     * @return  int
     */
    public int rob2(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res1 = range(nums, 0, nums.length - 2);
        int res2 = range(nums, 1, nums.length - 1);
        return Math.max(res1, res2);
    }
    int range(int[] nums, int start, int end) {
        if (end == start) return nums[start];
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

    /*
     * @Description  337. 打家劫舍 III
     * @author   Edison
     * @date    2023/7/4 16:45
     * @Param   [root]
     * @return  int
     */
    public int rob(TreeNode root) {
        int[] res = robAction(root);
        return Math.max(res[0], res[1]);
    }
    int[] robAction(TreeNode root) {
        int[] res = new int[2];
        if (root == null) return res;
        int[] left = robAction(root.left);
        int[] right = robAction(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }

    /*
     * @Description  121. 买卖股票的最佳时机
     * @author   Edison
     * @date    2023/7/4 17:04
     * @Param   [prices]
     * @return  int
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }
        return dp[prices.length - 1][1];
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
