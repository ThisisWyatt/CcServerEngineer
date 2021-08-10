---
title: LeetCode 53th 最大子序和
categories: Airthmetic
tags: Array
declare: true
wordCount: true
abbrlink: a7a4385e
date: 2020-05-01 15:53:10
---
## 1.Subject
>* 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
>* 示例:
>* 输入: [-2,1,-3,4,-1,2,1,-5,4],
>* 输出: 6
>* 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

<!-- more -->
## 2.Solution

### 2.1暴力法

#### 2.1.1思路
    1. 从第一个元素开始，逐渐向后加上一个后续元素，每加一次，并判断确定这一个循环最大的连续子数组和
    2. 从第二个元素开始，逐渐向后加上一个后续元素，每加一次，并判断确定这一个循环最大的连续子数组和
    3. 从第三元素开始，逐渐向后加上一个后续元素，每加一次，并判断确定这一个循环最大的连续子数组和
    4. ...
    5. 第n个（最后一个）元素即为这个循环最大的元素和
    6. 在所以循环的连续子数组和中找出最大的一个

#### 2.1.2 code

```java
public class maxSubArray {
    private static int max(int[] a) {
            int[] singleMsx = new int[a.length];
            int start = 0;
            for (int i = 0; i < a.length; ++i) {
                singleMsx[i] = a[i];
                start = a[i];
                if (i != a.length - 1) {
                    for (int j = i + 1; j < a.length; ++j) {
                        start += a[j];
                        singleMsx[i] = Math.max(start, singleMsx[i]);
                    }
                }
            }

            int max = 0;
            for (int msx : singleMsx) {
                if (max < msx)
                    max = msx;
            }
            return max;
    }

    public static void main(String[] args) {

        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(max1(array));

    }
}

```

### 2.2贪心算法

#### 2.2.1 code
```java
public class maxSubArray {
    private static int max1(int[] a){
        int currentSum=a[0];
        int maxSum=a[0];
        for(int i=0;i<a.length;i++){
            currentSum=Math.max(a[i],currentSum+a[i]);  //如果之前的和加上nums[i]比直接用nums[i]重新开始还小则将nums[i]的值直接赋值给currentSum
            maxSum=Math.max(maxSum,currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {

        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        System.out.println(max1(array));

    }
}
```
