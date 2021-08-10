---
title: Spring Data Jpa模糊查询
categories: Code
tags:
  - JavaWeb
  - ORM
  - JPA
declare: true
wordCount: true
abbrlink: b1de1f1
date: 2020-05-03 23:55:10
---

晚上在用Spring Data Jpa做一个复杂查询的时候，用到了模糊查询，遇到了一个坑，花了很多时间，严重打击了我的积极性... ，可能也是很多人即使看了官方文档也容易忽略的地方。

* Spring Data JPa模糊查询
```java
    List<MoveInfo> moveInfoList2=moveInfoService.findByLocationLike("%"+location+"%");
```
 
**需要注意的是一定要在要在`location`左右加`%`!!!**    
说实话用了`findByLocationLike`还需要手动写%,确实不怎么合理，希望未来版本官方会改进吧。
<!-- more -->


