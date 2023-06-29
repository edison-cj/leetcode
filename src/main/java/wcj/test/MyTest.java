package wcj.test;

import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2022/11/24 15:22
 */
public class MyTest {

    @Test
    public void Test1(){

        int[] nums = {0,1,0,3,4,1,0};
        int slowIndex = 0;

        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != 0) {
                int temp = nums[slowIndex];
                nums[slowIndex++] = nums[fastIndex];
                nums[fastIndex] = temp;
            }
        }

        System.out.println(Arrays.toString(nums));
    }
}
