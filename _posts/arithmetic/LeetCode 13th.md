---
title: LeetCode 13th 罗马数字转整型
categories: Airthmetic
tags: Array
declare: true
wordCount: true
abbrlink: 28c6af09
date: 2020-07-15 16:45:10
---

# 1.Subject
>罗马数字包含以下七种字符: I， V， X， L，C，D 和 M
```
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```
>例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II

>通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。       
>同样地，>数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

    1. I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
    2. X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
    3. C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。

给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

<!-- more -->

>输入: "LVIII"     
>输出: 58      
>解释: L = 50, V= 5, III = 3.   

# 2.Solution

## 2.1 全部HashMap
### 2.1.1 思路

1. 把规则全部存储在HashMap中
2. 首先判断是否符合两字符规则
3. 再判断是否符合单字符规则

### 2.1.2 Code
```java
public class cc13 {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(Solution2(s));
    }

    public static int Solution1(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        int i = 0, sum = 0;
        for (i = 0; i < s.length(); ) {
            if (i + 2 <= s.length() && map.containsKey(s.substring(i, i + 2))) { //substring左开右闭
                String ss = s.substring(i, i + 2);
                sum += map.get(ss);
                i += 2;
            } else {
                String ss = s.substring(i, i + 1);
                sum += map.get(ss);
                i++;
            }
        }
        return sum;
    }
}
```

## 2.2 部分HashMap

### 2.2.1 思路
1. 直将单字符规则存储于HashMap中
2. 判断前一个字符是否小于当前字符对于数值（正常情况下，小数值对应字符在大数值对应字符右边）
3. 如果是则减去2倍前一数值

### 2.2.2 Code
```java
public static int Solution2(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        String[] arrays = s.split("");
        int sum = map.get(arrays[0]);
        for (int i = 1; i < arrays.length; i++) {
            sum += map.get(arrays[i]);
            if (map.get(arrays[i]) > map.get(arrays[i - 1]))
                sum = sum - 2 * map.get(arrays[i - 1]); //注意减去2倍 因为之前多加了一次
        }

        return sum;
    }
```

