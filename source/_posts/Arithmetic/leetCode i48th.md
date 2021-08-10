---
title: LeetCode i48th 最长不含重复字符的子字符串
categories: Airthmetic
tags: String
declare: true
wordCount: true
abbrlink: e2dceda6
data: 2020-05-01 17:56:10
---

## 1.题目
>从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
<!-- more -->
## 2.解法

### 2.1 滑动窗口

```java
    private static int longest(String str) {
        int length = str.length();
        if (length == 0 || length == 1)
            return length;
        int left = 0;
        int longest = 0;
        for (int i = 1; i < length; ) {
            if (judge(str, left, i - 1, str.charAt(i))) {
                i++;
                longest = Math.max(longest, i - left);
            } else {
                left++;
            }
        }
        return longest;
    }
    private static boolean judge(String s, int left, int right, char c) {
        for (int i = left; i <= right; i++) {
            if (s.charAt(i) == c)
                return false;
        }
        return true;
    }
```

### 2.1HasHMap的滑动窗口
```java
    private static int LM(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int longest = 0;
        int head = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {

            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                longest = Math.max(longest, i - head);
            } else {
                longest = Math.max(longest, i - head); //即使此次重复了 但是要算上前一个 每次i-head都是算的前面的
                if (map.get(s.charAt(i)) + 1 > head) {
                    head = map.get(s.charAt(i)) + 1;
                }
                map.remove(s.charAt(i));
                map.put(s.charAt(i), i);
            }
        }
        return Math.max(longest, s.length() - head); //判断最后一次的是否与之前最大的longest相同
    }
   
```