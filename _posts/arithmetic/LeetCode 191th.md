---
title: LeetCode 191th 汉明重量
categories: Airthmetic
tags: Array
declare: true
wordCount: true
abbrlink: 6fbce6f0
date: 2020-07-16 22:10:10
---

# 1.Subject
编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。 
>输入：11111111111111111111111111111101     
>输出：31     
>解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。     

<!-- more -->

# 2.Solution
## 2.1 思路
* 将数值的每一位与1(000...001)进行按位与
    * 如果此位为1则按位与结果为1
    * 如果此位为0则按位与结果为0

## 2.2 Code
```java
public class cc191 {
    public static void main(String[] args) {
        int n = 110;
        System.out.println(Solution(n));
    }

    public static int Solution(int n) {
        int count = 0;
        while (n != 0) {
            System.out.println("n=" + n);
            count += n & 1; //最后一位和000...0001进行按位与
            n = n >>> 1;    //无符号数右移动1位
        }
        return count;
    }
}
```

<br><br><br>
***
<br><br><br>

**参考**    
[1]https://leetcode-cn.com/problems/number-of-1-bits/solution/javade-17chong-jie-fa-by-sdwwld/

