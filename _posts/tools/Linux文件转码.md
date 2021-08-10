---
title: linux下文件转码
categories: Linux
tags: linux工具
declare: true
wordCount: true
abbrlink: f48fda8c
date: 2020-05-18 18:00:10
---

最近两天因为项目中的原始文件编码格式为cp936，而程序处理的是utf-8，所以需要对其转换，想来也不是很麻烦，但是还是遇到了很多坑，所以记下，可能会帮到别人。
linux下有多种转换文件编码格式的方法，但最常用的转换工具是vim和`iconv`。
<!-- more -->
在很多地方都看了使用vim编辑器转码，但是我对其表示怀疑，因为测试过多次都不咋成功，可能还需要手动添加支持的编码格式，总之还是比较麻烦。而且打开vim转码也只是适用于少量非生产环境中，总之我只推荐使用vim查看编码格式，**不推荐使用vim转码**。
## 一、VIM
### 1.1 查看文件编码格式
```ini
set fileencoding
```
### 1.2 格式转换
```
set fileencoing= encoding  //加上自己需要的编码格式即可
```
> 我在ubuntu和centos上都测试过，都没能转换成功。。。。 **所以对此方法表示怀疑**

## 二、iconv
官方文档
```ini
[root@master test]# iconv -?
用法： iconv [选项...] [文件...]
转换给定文件的编码。

 输入/输出格式规范：
  -f, --from-code=名称     原始文本编码
  -t, --to-code=名称       输出编码

 信息：
  -l, --list                 列举所有已知的字符集

 输出控制：
  -c                         从输出中忽略无效的字符
  -o, --output=FILE          输出文件
  -s, --silent               关闭警告
      --verbose              打印进度信息

  -?, --help                 给出该系统求助列表
      --usage                给出简要的用法信息
  -V, --version              打印程序版本号

长选项的强制或可选参数对对应的短选项也是强制或可选的。

For bug reporting instructions, please see:
<http://www.gnu.org/software/libc/bugs.html>.


```
### 2.1 查看支持的编码格式
```ini
iconv -l
```
### 2.2 转换
```ini
iconv -f encoding1 -t encoding2 filename1 -o filename2
```    

> -f : 原编码格式 &nbsp; -t: 转换后格式    
> **一定要输出到文件中**，否则只会标准输出到显示器中 

> 我这边无法将utf-8转换为cp936 ,gbk ,gb2312    
> 但可以将cp936转换为utf-8，对于日常也够了

### 2.3 常见问题
* 首次使用可能需要安装
```ini
ubuntu等：apt-get install iconv
centos等：yum install iconv
```

* `iconv: 未知 *** 处的非法输入序列`,加`-c`
```ini
iconv -c -f encoding1 -t encoding2 filename1 -o filename2
```

* 转换后乱码。建议转码输出到另外一个文件中，猜测输出到源文件中即直接转码可能会引起文件内容紊乱。。。

