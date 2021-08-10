---
title: LeetCode 20th 有效的括号
categories: Airthmetic
tags: Array
declare: true
wordCount: true
abbrlink: 8ce268b6
data: 2020-07-15 18:45:10
---

# 1.Subject
>给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。     
>有效字符串需满足：

    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
>注意空字符串可被认为是有效字符串。
>输入: "{[]}"    
>输出: true     

>输入: "()[]{}"   
>输出: true

>输入: "([)]"     
>输出: false     

<!-- more -->

# 2. Solution
## 2.1 栈
### 2.1.1 思路
    1.遇到左括号时，存入右括号
    2.遇到右括号时，判断是否是当前栈顶的元素
        2.1 若是则弹出栈顶元素并继续
        2.2 不是则返回false
    3.若最终栈为空则全部匹配上，否则未全部匹配上

### 2.2 Code
```java
public class cc20 {
    public static void main(String[] args) {
        String s = "{[]}";
        System.out.println(Solution(s));
    }

    public static boolean Solution(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');

        LinkedList<Character> list = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c))
                list.add(map.get(c)); //如果判断有左括号的时候，栈推入右括号
            else if (list.isEmpty() || c != list.removeLast()) //list为空(没有匹配的) 或 c不等于list的最新的个元素(移除）
                return false;
        }
        return list.isEmpty(); //无剩余则全部匹配上
    }
}
```


***

<br>
<br>

### 参考
https://leetcode-cn.com/problems/valid-parentheses/solution/java-you-xiao-gua-hao-si-lu-qing-xi-pan-duan-jian-/