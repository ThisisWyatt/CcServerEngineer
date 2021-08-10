---
title: LeetCode 26th 删除排序数组中的重复项
categories: Airthmetic
tags: Array
declare: true
wordCount: true
abbrlink: 3cb8c20c
data: 2020-07-14 18:25:10
---
# 1.Subject
>给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。      
>不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

<!-- more -->

>给定 nums = [0,0,1,1,1,2,2,3,3,4],    
>函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。   
>你不需要考虑数组中超出新长度后面的元素。

# 2.Solution

## 2.1 利用HashMap记录元素出现次数

### 2.1.1 思路
    1. 首先判定是否第一次出现
    2. 如果是，则此后的元素整体向前移动一个位置
    3. 如果不是，继续向后遍历

## 2.1.2 Code
```java
    public class cc26 {
        public static void main(String[] args) {
            int[] a = {1, 1, 2};
            System.out.println(Solution3(a));
        }

        public static int Solution1(int[] nums) {
            if (nums == null || nums.length == 0)
                return 0;
            Map<Integer, Integer> map = new HashMap<>();
            int length = nums.length;   //遍历的范围会变化

            for (int i = 0; i < length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
                if (map.get(nums[i]) != 1) {
                    for (int j = i + 1; j < length; j++) {
                        nums[j - 1] = nums[j];      //此后的元素整体向前移动一个位置
                    }
                    length--;   //遍历范围减小
                    i--;        //下次判定还是从此位置开始，所以先减1，后面遍历会加1
                }
            }
            return length;
        }
    }
```

## 2.2 双指针法

### 2.2.1 思路
    首先注意数组是有序的，那么重复的元素一定会相邻。

    要求删除重复元素，实际上就是将不重复的元素移到数组的左侧。

    考虑用 2 个指针，一个在前记作 p，一个在后记作 q，算法流程如下：

    比较 p 和 q 位置的元素是否相等。
        如果相等，q 后移 1 位
        如果不相等，将 q 位置的元素复制到 p+1 位置上，p 后移一位，q 后移 1 位
    重复上述过程，直到 q 等于数组长度。

    返回 p + 1，即为新数组长度。

### 2.2.2 Code
```java
    public static int Solution2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int p = 0, q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    //优化版
    public static int Solution3(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int p = 0, q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {      //排除由于没有重复元素，p、q一直紧挨着，但也会一直进行不必要的赋值
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;
    }
```

<br>
<br>

***

<br>
<br>

### 参考
https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/solution/shuang-zhi-zhen-shan-chu-zhong-fu-xiang-dai-you-hu/
