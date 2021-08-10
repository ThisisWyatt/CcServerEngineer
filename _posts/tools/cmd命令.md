---
title: cmd命令
categories: Tools
tags: windows
declare: true
wordCount: true
abbrlink: 8d229e40
date: 2020-04-29 16:57:10
---
❄
<!-- more -->


### 进程操作
#### 进程
* 查找占用4000端口的进程
    * `netstat -aon|findstr "1024"`
* 关闭PID为 1024的进程
    * `taskkill /pid 1024 /f`