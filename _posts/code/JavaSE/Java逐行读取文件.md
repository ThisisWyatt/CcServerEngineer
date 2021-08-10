---
title: Java 逐行读取文件
categories: Code
tags:
  - 工具类
declare: true
wordCount: true
abbrlink: af03e16b
date: 2020-05-18 19:38:10
---



```java
 BufferedReader br = new BufferedReader(
     new InputStreamReader(new FileInputStream(new File("C:/Users/LetGo/Desktop/messages")
     ),StandardCharsets.UTF_8));
String s=br.readLine() != null);
```
> File -> FileInputStream ->InputStreamReader -> BufferedReader

```java
    InputStream stream = getClass().getClassLoader().getResourceAsStream(
        "messages");
    assert stream != null;
    BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
    String s=br.readline();
```
> **只能读取当前工程下文件**
