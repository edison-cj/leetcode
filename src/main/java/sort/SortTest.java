package sort;

import org.testng.annotations.Test;


/**
 * @version 1.0
 * @Description:
 * @author: Edison
 * @date: 2023/4/3 16:39
 */
public class SortTest {

    Solution solution = new Solution();

    int[] nums = {45,45,32,15,41,56,23,65,12,36,21,2,3,2222,1};
    @Test
    public void BubbleSortTest() {
        solution.BubbleSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void SelectionSortTest() {
        solution.SelectionSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void InsertSortTest() {
        solution.InsertSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void shellSortTest() {
        solution.ShellSort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    @Test
    public void QuickSort() {
        solution.QuickSort(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

}

class Solution {

    /*
     * @Description  冒泡排序
     * @author   Edison
     * @date    2023/4/3 16:45
     * @Param   [num]
     * @return  void
     */
    public void BubbleSort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length - 1 - i; j++) {
                if (num[j] > num[j + 1]) {
                    int temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                }
            }
        }
    }

    /*
     * @Description  选择排序
     * @author   Edison
     * @date    2023/4/3 16:45
     * @Param   []
     * @return  void
     */
    public void SelectionSort(int[] num) {
        for (int i = 0; i < num.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[j] < num[index]) {
                    index = j;
                }
            }
            int temp = num[index];
            num[index] = num[i];
            num[i] = temp;
        }
    }

    /*
     * @Description  插入排序
     * @author   Edison
     * @date    2023/4/3 17:20
     * @Param   [num]
     * @return  void
     */
    public void InsertSort(int[] num) {
        for (int i = 1; i < num.length; i++) {
            int index = i;
            int value = num[i];
            while (index - 1 >= 0 &&  num[index - 1] > num[index]) {
                num[index] = num[index - 1];
                num[index - 1] = value;
                index--;
            }
        }
    }

    /*
     * @Description 希尔排序
     * @author   Edison
     * @date    2023/4/3 18:31
     * @Param   [num]
     * @return  void
     */
    public void ShellSort(int[] num) {
        for (int gap = num.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < num.length; i++) {
                int j = i;
                int temp = num[i];
                while (j - gap >= 0 && num[j - gap] > num[j]) {
                    num[j] = num[j - gap];
                    num[j - gap] = temp;
                    j -= gap;
                }

            }
        }
    }

    /*
     * @Description  快速排序
     * @author   Edison
     * @date    2023/4/3 19:05
     * @Param   [num]
     * @return  void
     */
    public void QuickSort(int[] num, int left, int right) {
        if (left > right) {
            return;
        }
        int temp = num[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (temp <= num[j] && j > i) {
                j--;
            }
            while (temp >= num[i] && j > i) {
                i++;
            }
            if (j > i) {
                int t = num[i];
                num[i] = num[j];
                num[j] = t;
            }
        }
        num[left] = num[i];
        num[i] = temp;
        QuickSort(num, left, i - 1);
        QuickSort(num, i + 1, right);
    }

    /*
     * @Description  归并排序
     * @author   Edison
     * @date    2023/4/3 20:15
     * @Param   [num]
     * @return  void
     */
    public void MergeSort(int[] num) {

    }

}


