package second.tx;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/6/20 9:26
 */
public class TxTest {
    Solution solution = new Solution();

    @Test
    public void canJumpTest() {
        int[] nums = {3,2,1,0,4};
        System.out.println(solution.canJump(nums));
    }

}

class Solution {

    /*
     * @Description  455. 分发饼干
     * @author   Edison
     * @date    2023/6/20 9:27
     * @Param   [g, s]
     * @return  int
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int start = s.length - 1;
        for (int i = g.length - 1; i >= 0; i--) {
            if (start >= 0 && g[i] <= s[start]) {
                start--;
                count++;
            }
        }
        return count;
    }

    /*
     * @Description  376. 摆动序列
     * @author   Edison
     * @date    2023/6/20 9:34
     * @Param   [nums]
     * @return  int
     */
    public int wiggleMaxLength(int[] nums) {
        int len = 1;
        int curdiff = 0;
        int prediff = 0;
        for (int i = 1; i < nums.length; i++) {
            curdiff = nums[i] - nums[i - 1];
            if ((curdiff > 0 && prediff <= 0) || (curdiff < 0 && prediff >= 0)) {
                prediff = curdiff;
                len++;
            }
        }
        return len;
    }

    /*
     * @Description  53. 最大子数组和
     * @author   Edison
     * @date    2023/6/20 9:45
     * @Param   [nums]
     * @return  int
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int sum = nums[0];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (count > sum) {
                sum = count;
            }
            if (count < 0) count = 0;
        }
        return sum;
    }

    /*
     * @Description  122. 买卖股票的最佳时机 II
     * @author   Edison
     * @date    2023/6/20 9:57
     * @Param   [prices]
     * @return  int
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            int count = prices[i] - prices[i - 1];
            if (count > 0) {
                sum += count;
            }
        }
        return sum;
    }

    /*
     * @Description  55. 跳跃游戏
     * @author   Edison
     * @date    2023/6/20 10:02
     * @Param   [nums]
     * @return  boolean
     */
    public boolean canJump(int[] nums) {
        int len = 0;
        for (int i = 0; i <= len; i++) {
            int count = i + nums[i];
            len = Math.max(len, count);
            if (len >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /*
     * @Description  45. 跳跃游戏 II
     * @author   Edison
     * @date    2023/6/20 10:13
     * @Param   [nums]
     * @return  int
     */
    public int jump(int[] nums) {
        int index = 0;
        int count = 0;
        int cover = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            cover = Math.max(cover, nums[i] + i);
            if (i == index) {
                index = cover;
                count++;
            }
        }
        return count;
    }

    /*
     * @Description  134. 加油站
     * @author   Edison
     * @date    2023/6/21 9:32
     * @Param   [gas, cost]
     * @return  int
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int curSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            curSum += gas[i] - cost[i];
            if (curSum < 0) {
                start = i + 1;
                curSum = 0;
            }
        }
        if (sum < 0) return -1;
        return start;
    }

    /*
     * @Description  135. 分发糖果
     * @author   Edison
     * @date    2023/6/21 9:41
     * @Param   [ratings]
     * @return  int
     */
    public int candy(int[] ratings) {
        int[] candyVec = new int[ratings.length];
        candyVec[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            candyVec[i] = ratings[i] > ratings[i - 1] ? candyVec[i - 1] + 1 : 1;
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
        }
        int sum = 0;
        for (int num : candyVec) {
            sum += num;
        }
        return sum;
    }

    /*
     * @Description  860. 柠檬水找零
     * @author   Edison
     * @date    2023/6/21 9:55
     * @Param   [bills]
     * @return  boolean
     */
    public boolean lemonadeChange(int[] bills) {
        int fiveNum = 0;
        int tenNum = 0;
        for (int bill : bills) {
            if (bill == 5) fiveNum++;
            else if (bill == 10) {
                tenNum++;
                fiveNum--;
            } else {
                if (tenNum > 0) {
                    tenNum--;
                    fiveNum--;
                } else {
                    fiveNum -= 3;
                }
            }
            if (fiveNum < 0) return false;
        }
        return true;
    }

    /*
     * @Description  452. 用最少数量的箭引爆气球
     * @author   Edison
     * @date    2023/6/21 10:26
     * @Param   [points]
     * @return  int
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) count++;
            else points[i][1] = Math.min(points[i - 1][1], points[i][1]);
        }
        return count;
    }

    /*
     * @Description  435. 无重叠区间
     * @author   Edison
     * @date    2023/6/21 10:38
     * @Param   [intervals]
     * @return  int
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1])
                intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
            else count++;
        }
        return intervals.length - count;
    }

    /*
     * @Description  763. 划分字母区间
     * @author   Edison
     * @date    2023/6/21 10:50
     * @Param   [s]
     * @return  List<Integer>
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int[] ans = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            ans[chars[i] - 'a'] = i;
        }
        int index = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            index = Math.max(index, ans[chars[i] - 'a']);
            if (i == index) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }

    /*
     * @Description  56. 合并区间
     * @author   Edison
     * @date    2023/6/21 10:58
     * @Param   [intervals]
     * @return  int[][]
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                list.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        list.add(new int[]{start, end});
        return list.toArray(new int[list.size()][]);
    }

    /*
     * @Description  738. 单调递增的数字
     * @author   Edison
     * @date    2023/6/21 11:19
     * @Param   [n]
     * @return  int
     */
    public int monotoneIncreasingDigits(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int flag = chars.length;
        for (int i = chars.length - 2; i >= 0; i--) {
            if (chars[i] - '0' > chars[i + 1] - '0') {
                flag = i + 1;
                chars[i]--;
            }
        }
        for (int i = flag; i < chars.length; i++) {
            chars[i] = '9';
        }
        return Integer.valueOf(new String(chars));
    }

    /*
     * @Description  968. 监控二叉树
     * @author   Edison
     * @date    2023/6/21 11:26
     * @Param   [root]
     * @return  int
     */
    int count = 0;
    public int minCameraCover(TreeNode root) {
        if (backTrack(root) == 0) count++;
        return count;
    }
    int backTrack(TreeNode root) {
        if (root == null) return 2;
        int left = backTrack(root.left);
        int right = backTrack(root.right);
        /**
         节点的状态值：
         0 表示无覆盖
         1 表示 有摄像头
         2 表示有覆盖
         后序遍历，根据左右节点的情况,来判读 自己的状态
         */
        if (right == 0 || left == 0) {
            count++;
            return 1;
        } else if (right == 2 && left == 2) {
            return 0;
        } else {
            return 2;
        }
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
