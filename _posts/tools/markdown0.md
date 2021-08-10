---
title: markdown基本用法
categories: Tools
declare: true
wordCount: true
abbrlink: 57b3646e
date: 2020-04-29 11:40:10
---
Markdown是一种可以使用普通文本编辑器编写的标记语言，通过简单的标记语法，它可以使普通文本内容具有一定的格式。   
<!-- more -->
### 1.标题
\# 一级标题
\## 二级标题
\### 三级标题
\#### 四级标题
\##### 五级标题
\###### 六级标题
># 一级标题
>## 二级标题
>### 三级标题
>#### 四级标题
>##### 五级标题
>###### 六级标题

### 2.字体形态

\*斜体文本*
>*斜体文本*

\_斜体文本_  
>_斜体文本_  

\**粗体**
>**粗体**

\__粗体__
>__粗体__

\***粗斜体***
>***粗斜体***

\___粗斜体___
>___粗斜体___


### 3.分隔线
\***
分割线
\***

***
分割线
***

### 4.删除线

\~~删除线~~  
>~~删除线~~  

### 5.下划线
\<u>下划线</u>
><u>下划线</u>

### 6.引用

\中文 [second]
>中文 [second]

[^second]:英文

### 7.列表

#### 7.1无序列表

\* 第一项
\* 第二项
\* 第三项
\+ 第四项
\+ 第五项
\+ 第六项
\- 第七项
\- 第八项
\- 第九项
>* 第一项
>* 第二项
>* 第三项
>+ 第四项
>+ 第五项
>+ 第六项
>- 第七项
>- 第八项
>- 第九项

#### 7.2有序列表
\1. 第一项
\2. 第二项
\3. 第三项

\* 1   
\    * 1.1   
\       * 1.1.1   
\            * 1.1.1.1  



>1. 第一项
>2. 第二项
>3. 第三项

>* 1
>    * 1.1
>        * 1.1.1
>            * 1.1.1.1

### 8.引用

\>第一层嵌套
\>>第二次嵌套
\>>>第三层嵌套

>第一层嵌套
>>第二次嵌套
>>>第三层嵌套


### 9.段落上的代码

\`System.out.println("hello world")`

`System.out.println("hello world")`

### 10.代码区块
#### 10.1Tab键或四个空格

    public class helloWorld{
        public static void main(String[] args){
            System.out.println("hello world");
        }
    }

#### 10.2 

(```+指定语言)

```java
    public class helloWorld{
        public static void main(String[] args){
            System.out.println("hello world");
        }
    }
```

### 11.链接

这是一个\[链接](wht.im)
>这是一个[链接](wht.im)

\<http://www.baidu.com>
<http://www.baidu.com>


网址一\[^one]  
\[^one]:www.baidu.com
网址一[^one]  
[^one]:www.baidu.com

### 12.图片

([]中的为图片不显示时的提示文字)

\![img_caption]\(http://static.runoob.com/images/runoob-logo.png)
![img_caption](http://static.runoob.com/images/runoob-logo.png)


指定宽度

\<img src="http://static.runoob.com/images/runoob-logo.png" width="50%">
<img src="http://static.runoob.com/images/runoob-logo.png" width="50%">

### 13.表格


\| 字符 | 描述 |  例子 |  
\|-|-|-
\|   | 时代指示器 | AD |
\| y | 四位数年份 | 2001 |
\| M | 年中的月份 | July or 07 |
\| d | 月份中日期 | 10 |

| 字符 | 描述 |  例子 |  
|-|-|-
|   | 时代指示器 | AD |
| y | 四位数年份 | 2001 |
| M | 年中的月份 | July or 07 |
| d | 月份中日期 | 10 |

* 设置对齐方式  
    * -： 左对齐
    * :-  右对齐
    * :-：中间对齐


|表头|表头|表头|
|:----|----:| :--:
|左对齐|右对齐|中间对齐|

### 14.键盘文本

使用<kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>Esc</kbd>打开任务管理器

转义：(加斜杠)

### 15.高级技巧
><https://www.runoob.com/markdown/md-advance.html>







    
