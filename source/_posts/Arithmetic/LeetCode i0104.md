---
title: LeetCode i0104 回文排列
categories: Airthmetic
tags: String
declare: true
wordCount: true
abbrlink: 885418c8
data: 2020-05-01 22:58:10
---

<!-- 面试题 01.04. 回文排列 -->

## Subject
>给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列.回文串不一定是字典当中的单词。
<!-- more -->
```
实例    
    输入："tactcoa"
    输出：true（排列有"tacocat"、"atcocta"，等等）
```
## Solution

    回文数的特征为只有一个字符的个数为奇数个。

```java
    public class i0104 {
        private static boolean judge(String s) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            int n=0;
            for(Map.Entry<Character,Integer> entry:map.entrySet()){
                if(entry.getValue()% 2 == 1)
                    n++;
                if(n==2){
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            String s = "tactcoa";
            System.out.println(judge(s));
        }
    }
```