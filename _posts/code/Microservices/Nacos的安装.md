---
title: Nacos的安装
categories: Code
tags: Microservices
declare: true
wordCount: true
date: 2021-08-13 16:23:24
---
Nacos是Alibaba推出的一款用于发现、配置和管理的一款中间件，它提供了一组简单易用的特性集，帮助您快速实现动态服务发现、服务配置、服务元数据及流量管理。
<!-- more -->

## 一、下载源码或安装包
### 1.1 从 Github 上下载源码方式
```ini
$ git clone https://github.com/alibaba/nacos.git
$cd nacos/
$ mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U  
$ ls -al distribution/target/

// change the $version to your actual path
cd distribution/target/nacos-server-$version/nacos/bin
```
### 1.2 下载编译后压缩包方式
先[下载](https://github.com/alibaba/nacos/releases)编译后的压缩包，然后解压到本地目录。

## 二、启动
### 2.1 配置数据库
登录Mysql，建立名称为nacos的数据库，执行conf目录下的nacos-mysql.sql文件，执行完后数据库中应有如下表：
![nacos-mysql.sql](https://i.loli.net/2021/08/13/AkMncpg5iLbdV3H.png)
### 2.2 修改配置配置文件
进入到nacos的conf目录下，修改application.properties文件
![nacos_applicaton_properties](https://i.loli.net/2021/08/13/opAYq8syZnMQCKD.png) 
进入到nacos的bin目录下，修改startup.cmd文件
![nacos_startup.cmd](https://i.loli.net/2021/08/13/opAYq8syZnMQCKD.png)
### 2.3 启动nacos
进入nacos的bin目录下，运行startup.cmd
![nacos_startup.cmd](https://i.loli.net/2021/08/13/EhrUZHDAOa8sdef.png)
### 2.4 [访问nacos图形化界面](http://localhost:8848/nacos/#/login)
![nacos图形化界面](https://i.loli.net/2021/08/13/pmwo1LFcA7IPirC.png)
> 初始用户名和密码：nacos/nacos



## 参考
https://nacos.io