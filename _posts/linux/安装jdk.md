---
title: Liunx下安装JDK
categories: Linux
tags: 环境配置
declare: true
wordCount: true
abbrlink: 2be0aa3a
date: 2020-07-20 19:02:10
---

* 解压
* 创建储存位置
* 移动jdk到指定位置
* 配置环境变量
* 更新环境变量
* 更新环境变量
* 测试

<!-- more -->


## 1.解压
```ini
[root@cc resource]# tar -zxvf jdk-8u161-linux-x64.tar.gz 
```

## 2.创建储存位置
```ini
[root@cc resource]# cd /usr
[root@cc resource]# mkdir java
[root@cc resource]# cd java
```

## 3.移动jdk到指定位置
```ini
[root@cc resource]# mv  jdk1.8.0_161/ /usr/java
```

## 4.配置环境变量
```ini
[root@cc resource]# vim /etc/profile

添加如下代码：

export JAVA_HOME=/usr/java/jdk1.8.0_161
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$CLASSPATH
export JAVA_PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin
export PATH=$PATH:${JAVA_PATH}

```

## 5.更新环境变量
```ini
[root@cc resource]# source /etc/profile
```

## 6.测试
```ini
[root@cc resource]# java -version
```