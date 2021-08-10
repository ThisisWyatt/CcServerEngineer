---
title: LeetCode LCP 06 拿硬币
categories: Airthmetic
tags: String
declare: true
wordCount: true
abbrlink: d93986a2
date: 2020-05-01 23:25:10
---

## Subject
>桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
<!-- more -->
```
示例 1：
    输入：[4,2,1]
    输出：4
    解释：第一堆力扣币最少需要拿 2 次，第二堆最少需要拿 1 次，第三堆最少需要拿 1 次，总共 4 次即可拿完。
```

## Solution
 ```java
    public class Lcp06 {
        private static int solution(int[] coins) {
            int time = 0;
            for (int m : coins) {
                int n = 0;
                if (m % 2 == 0) {
                    n = m / 2;
                } else {
                    n = m / 2 + 1;
                }
                time += n;
            }
            return time;
        }

        public static void main(String[] args) {
            int[] a = {2, 3, 10};
            System.out.println(solution(a));
        }
    }
 ```