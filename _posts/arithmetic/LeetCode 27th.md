---
title: LeetCode 27th 移除元素
categories: Airthmetic
tags: Array
declare: true
wordCount: true
abbrlink: 1e9266e0
date: 2020-07-18 22:25:10
---
# 1.Subject
>给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。       
>不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 **原地** 修改输入数组。       
>元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。       

<!-- more -->


>给定 nums = [3,2,2,3], val = 3,       
>函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。       
>你不需要考虑数组中超出新长度后面的元素。

# 2.Solution
## 2.1 双指针
### 2.1.1 思路
* 两个指针p,q
* 判断nums[q]的值是否与key值相等
    * 不相等，将nums[q]的值赋给nums[p]，p++,q++
    * 相等，q++

### 2.1.2 Code
```java
public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        int n = 3;
        System.out.println(removeElement(nums, n));
    }

    public static int removeElement(int[] nums, int val) {
        int left = 0, length = nums.length, p = 0, q = 0;
        while (q < nums.length) {
            if (nums[q] != val) {
                nums[p] = nums[q];
                q++;
                p++;
            } else {
                q++;
                length--;
            }
        }
        return length;
    }
}
```

## 2.2 前移数组（错误）
### 2.2.1 思路（错误）
遇到key值，就从下一位开始整体前移一位
** **但是遇到key结尾的就很麻烦！！**
### 2.2.2 Code（错误）
```java
public static int removeElement(int[] nums, int val) {
        int left = 0, length = nums.length;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < nums.length; j++) {
                    nums[j - 1] = nums[j];
                }
                length--;
                i--;
            }
        }
        return length;
    }
```



