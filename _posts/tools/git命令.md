---
title: git命令
categories: Tools
tags: git
declare: true
wordCount: true
abbrlink: 335a5d78
date: 2020-04-29 16:41:10
---

Git是一个免费的开源**分布式版本控制**系统，它可以快速高效地处理从小型到大型的项目。

<!-- more -->

#### 基础
* 本地与远程库的关联与取消（可以一对多）
    * 关联：
        * `git remote add origin https:xxxx.git`, origin 为远程仓库别名
    * 取消：
        * `git remote remove origin` 
    * 查看远程仓库，以及与本地仓库的关系
        * `git remote show origin`
* 跟踪文件
    * 跟踪单个文件
        * `git add file_name`
    * 跟踪项目文件夹中的所有文件和文件夹
        * `git add .`
* 提交文件
    * `git commit -m 'commit'`
* 推送到远程仓库 `git push <远程主机名> <本地分支名>:<远程分支名>`
    * 推送到origin主机对应分支
        * `git push origin master`
     * 如果当前分支与多个主机存在追踪关系，那么这个时候-u选项会指定一个默认主机，这样后面就可以不加任何参数使用git push。
        * `git push -u origin master`
    * 强制推送，会将远程仓库存在而本地不存在的抹除，即强制使得远程仓库内容与本地保持一样
        * `git push -f origin master`
* 取回远程主机某个分支的更新，再与本地的指定分支合并  
    * `git pull = git fetch + git merge`   
    * `git pull <远程主机名> <远程分支名>:<本地分支名>`
    * 拉取远程主机origin的master分支与本地brantest分支合并
        * `git pull origin master:brantest //:brantest可以省略` 
        * 等同于`git fetch origin master:brantest + 
git merge brantest`
    

>参考   
><https://www.cnblogs.com/of-course/p/11577318.html>
><https://blog.csdn.net/JackLiu16/article/details/80952650>
><https://www.cnblogs.com/taohuaya/p/10761799.html>


