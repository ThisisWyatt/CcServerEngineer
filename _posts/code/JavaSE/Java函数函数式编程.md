---
title: Java函数式编程
categories: Code
tags: JavaSE
declare: true
wordCount: true
date: 2021-08-12 16:42:10
---
使用函数式编程风格（lambda表达式、方法引用）对字符串数组进行排序。
<!-- more -->

## 一、普通编程风格
```java
List<String> names = Arrays.asList("wyatt2", "wyatt1", "wyatt3");
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
});
for (String name : names) {
    System.out.println(name);
}
```

## 二、函数式编程风格
```java
List<String> names = Arrays.asList("wyatt2", "wyatt1", "wyatt3");
// 普通Lambda表达式
Collections.sort(names, (String a, String b) -> {
    return a.compareTo(b);
});
// 省略return 
Collections.sort(names, (String a, String b) -> a.compareTo(b));
// 省略入参类型
Collections.sort(names, (a,  b) -> a.compareTo(b));
// 使用方法引用（compareTo()是String类的方法
Collections.sort(names, String::compareTo);
// 也可使用List自身方法
names.sort(String::compareTo);
```