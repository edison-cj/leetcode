package second.dynamicprogramming;

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
