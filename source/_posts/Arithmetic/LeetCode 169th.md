---
title: LeetCode 169th 多数元素
categories: Airthmetic
tags: Array
declare: true
wordCount: true
abbrlink: a7a4385e
data: 2020-05-01 16:30:10
---

## 1.题目
>寻找一个数组中出现次数最多的元素
<!-- more -->
## 2.解法

### 2.1 思路

    遍历数组并使用hashMap记录出现的次数

### 2.1 code
```java
public class multipleElement {


    private static int Solution169(int[] a) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int m : a) {
            map.put(m, map.getOrDefault(m, 0) + 1); //map.getOrDefault(a,b) 若map.get(a)存在则值为get(a)，否则为默认值b
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getValue());
            if (max < entry.getValue())
                max = entry.getValue();
        }
        return max;
    }

    public static void main(String[] args) {
        int[] num = {3, 2, 3};
        System.out.println(Solution169(num));
    }
}

```