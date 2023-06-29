package wcj.dynamicprogramming;

import org.testng.annotations.Test;

import javax.swing.*;
import java.util.stream.StreamSupport;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/3/24 10:29
 */
public class DynamicProgrammingTest {

    Solution solution = new Solution();

    @Test
    public void fibTest() {
        System.out.println(solution.fib(3));
    }

    @Test
    public void climbStairsTest() {
        System.out.println(solution.climbStairs(10));
    }

    @Test
    public void minCostClimbingStairsTest() {
        int[] cost = new int[]{1,100,1,1,1,100,1,1,100,1};
        System.out.println(solution.minCostClimbingStairs(cost));
    }

    @Test
    public void uniquePathsTest() {
        System.out.println(solution.uniquePaths(3, 7));
    }

    @Test
    public void uniquePathsWithObstaclesTest() {
        int[][] nums = {{0,1},{0,0}};
        System.out.println(solution.uniquePathsWithObstacles(nums));
    }

    @Test
    public void canPartitionTest() {
        int[] nums = {1, 5, 11, 5};
        System.out.println(solution.canPartition(nums));
    }

    @Test
    public void findTargetSumWaysTest() {
        int[] nums = {1, 1, 1, 1, 1};
        System.out.println(solution.findTargetSumWays(nums, 3));
    }


}

class Solution {

    /*
     * @Description  509. 斐波那契数
     * @author   Edison
     * @date    2023/3/24 10:44
     * @Param   [n]
     * @return  int
     */
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /*
     * @Description  70. 爬楼梯
     * @author   Edison
     * @date    2023/3/24 10:54
     * @Param
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /*
     * @Description  746. 使用最小花费爬楼梯
     * @author   Edison
     * @date    2023/3/24 11:07
     * @Param
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }

    /*
     * @Description  62. 不同路径
     * @author   Edison
     * @date    2023/3/24 15:04
     * @Param
     * @return
     */
    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * @Description  63. 不同路径 II
     * @author   Edison
     * @date    2023/3/24 15:22
     * @Param
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
     * @Description  343. 整数拆分
     * @author   Edison
     * @date    2023/3/24 15:49
     * @Param
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    /*
     * @Description  96. 不同的二叉搜索树
     * @author   Edison
     * @date    2023/3/24 16:14
     * @Param
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /*
     * @Description  416. 分割等和子集
     * @author   Edison
     * @date    2023/3/27 10:29
     * @Param
     * @return  
     */
    public boolean canPartition(int[] nums) {
        int dp[] = new int[10001];
        dp[0] = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }
        return target == dp[target];
    }

    /*
     * @Description  1049.最后一块石头的重量II
     * @author   Edison
     * @date    2023/3/27 15:54
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
     * @date    2023/3/28 10:21
     * @Param   [nums, target]
     * @return  int
     */
    int count = 0;
    public int findTargetSumWays(int[] nums, int target) {
//        BackTracking(nums, target, 0, 0);
//        return count;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (target < 0 && sum + target < 0) {
            return 0;
        }
        if ((target + sum) % 2 != 0) {
            return 0;
        }
        int size = (target + sum) / 2;
        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];

    }

    void BackTracking(int[] nums, int target, int sum, int index) {
        if (sum == target && index == nums.length) {
            count++;
            return;
        }
        if (index >= nums.length) {
            return;
        }
        BackTracking(nums, target, sum + nums[index], index + 1);
        BackTracking(nums, target, sum - nums[index], index + 1);
    }

    /*
     * @Description  474. 一和零
     * @author   Edison
     * @date    2023/3/28 11:35
     * @Param   [strs, m, n]
     * @return  int
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeroNum = 0;
            int oneNum = 0;
            for (char ch : str.toCharArray()) {
                if (ch == '0') {
                    zeroNum++;
                } else {
                    oneNum++;
                }
            }
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }
}

