---
title: Git
categories: Tools
tags: SCM
declare: true
wordCount: true
abbrlink: 341ecd43
date: 2020-11-29 23:54:34
---
<!-- git:
    --- : 未修改（提交过的）
    +++ : 修改过的  （未提交过的）
-->

<div align = "center">
    <img src="https://i.loli.net/2020/11/30/3YAsCch1igVaWyU.png" alt="Git组成" width="66%"/>
</div>


Git是一个免费、开源的开源分布式版本控制系统，它可以高效快速地处理大小项目。它易于学习、占用空间极小性能而又极为出色，超越了像Subversion、CVS、Perforce和ClearCase这样的SCM(soft configuration management)工具，具有像便宜的本地分支、方便的存储区域和多个工作流这样的特性。
<!-- more -->

## 一、Git简介
Git是一个**分布式系统**，即不同于集中式系统将所有的版本存放在中央服务器里，而是每一个设备上都是一个完整的版本库，这样做的好处不但使你不必依赖于互联网与中央服务器进行版本控制，而且即使一个设备损坏，只要在其他设备中存在你的项目，也不会造成你的数据丢失事故。
> 当然在实际使用过程中，会选择一台设备作为“中央服务器”（例如github服务器），但这样只是为了有利于你的数据存储、携带以及团队之间的合作开发，并不是真正的中央服务器。

### 1.1 Git的构成

<div align = "center">
    <img src="https://i.loli.net/2020/11/29/aphYo63R7glfOQM.png" alt="Git结构" width="80%"/>
</div>

> **workspace:工作区**      
> **index/stage:暂存区**      
> **repository:本地仓库**     
> **remote:远程仓库**     

1. **add**:将文件从工作区添加到暂存区；
2. **commit**:将文件从暂存区提交到本地仓库；
3. **push**:将文件本地仓库同步到远程仓库；
4. **pull**: `git fetch` 和 `git merge` 的简写，从远程仓库获取代码并合并本地的版本；
5. **fetch/clone**:`clone`将项目克隆到本地，`fetch`更新远程仓库文件到本地仓库；
6. **checkout**:从版本库中恢复文件到工作区

index和repository共同组成版本库（本地）

### 1.2 简单使用
* 在当前目录下新建git仓库
```ini
    git init
```
* 添加文件到暂存区中
```ini 
    git add fileName
```
* 提交文件到本地仓库中
```ini
    git commit -m "commit information"
```
* 推送本地仓库内容至远程仓库
```ini
    git push origin master
```

## 二、版本控制      
利用Git我们可以对项目的文件进行存储、对文件的修改进行控制，是软件开发过程中必备的开发工具。   


### 2.1 差异比较
**Git管理的是修改，而并非文件本身**，我们可以利用`git status`我们可以比较workspace和index文件的差异，但是如何比较具体文件的差异时，我们需要用到`git diff` 命令

* `git status`：比较workspace和index中的差异
* `git diff (filename)`：比较workspace和index中文件的差异
* `git diff --cached (filename)`、`git diff --staged (filename)`：比较index和上一次提交的差异
* `git diff HEAD (filename)`：比较workspace和repository的差别
    > HEAD指向repository中最新的提交的版本
* `git diff --stat`：统计各个文件的具体变化情况worksapce和index
* `git diff --numstat`：统计各个文件中变化的行数

**git diff输出格式分析**     
例如这里有一个文件gitDiffDemo.txt,有内容如下：
```txt
DragonBall1
DragonBall2
DragonBall3
DragonBall4
DragonBall5
DragonBall6
DragonBall7
DragonBall8
DragonBall9
```

将此文件提交至暂存区`$ git add gitDiffDemo.txt`后，将内容修改如下：
```txt
DragonBall1
DragonBall2
DragonBall3
DragonBall4
DragonBall5s 
DragonBall6
DragonBall7
DragonBall8
DragonBall9 
```
此时使用`$ git diff gitDiffDemo.txt`查看异同：
```ini
$ git diff gitDiffDemo.txt 
diff --git a/gitDiffDemo.txt b/gitDiffDemo.txt
index 9da8892..fa04dee 100644
--- a/gitDiffDemo.txt
+++ b/gitDiffDemo.txt
@@ -2,7 +2,7 @@ DragonBall1
 DragonBall2
 DragonBall3
 DragonBall4
-DragonBall5 
+DragonBall5s 
 DragonBall6
 DragonBall7
 DragonBall8
```

让我们来逐行来看输出内容
```ini
index 9da8892..fa04dee 100644
```
9da8892..fa04dee表示暂存区具体文件（此处指当前文件夹下的gitDiffDemo.txt）的哈希值，100644表示对象模式，100表示普通文件(regular file)，644表示文件操作权限，即`rw-r--r--`
> 101: symbolic link 110: git link 

```ini
--- a/gitDiffDemo.txt
+++ b/gitDiffDemo.txt
```
\-\-\-表示变化前的版本（提交过的）    
+++表示变化后的版本（修改过的）

```ini
@@ -2,7 +2,7 @@ DragonBall1
```
一对`@@`表示一个差异小结，一个差异小结的范围为**变更位置上下3行**，即一般为7行，上下行内容不足或两个差异点距离过近这个范围可能会缩小或变大。     
`-2,7`: 表示变化前的文件中，差异小结范围为第2行开始连续7行        
`+2,7`: 表示变化后的文件中，差异小结范围为第2行开始连续7行        

"DragonBall1"内容为差异小结范围上一行，不用理会。 


### 2.2 快照Id
在Git中，针对每一次commit都会生成相应的快照，即在Git系统中我们可以选择恢复某一个时间点上的版本快照以此达到版本控制的目的，使用`git log`命令查看commit历史
```ini
commit 7361c5145897d215c7bd9e9cf5a13c59f5908e7d
Author: WyattWang <cc7722@foxmail.com>
Date:   Sun Nov 29 20:14:41 2020 +0800

    git tracks changes

commit 4dbe0ff1cd9ade95b0ca83e22ac6dc27619b1cd9
Author: WyattWang <cc7722@foxmail.com>
Date:   Sun Nov 29 16:38:14 2020 +0800

    append GPL

commit 1076977642fedc84ef89be1a840850b991e434c7
Author: WyattWang <cc7722@foxmail.com>
Date:   Sun Nov 29 16:17:24 2020 +0800

    add distributed

commit a2fdd707b60ff78bada28410dc0ca8b6dd027e43
Author: WyattWang <cc7722@foxmail.com>
Date:   Sun Nov 29 14:11:51 2020 +0800

    wrote a readme file

```
如上所示，每一小段commit信息从上往下包含了commit id、提交者信息、commit时间和commit信息。       
如果只是想查看commit id和commit信息，加上`--pretty=oneline`参数即可
```ini
$ git log --pretty=oneline
7361c5145897d215c7bd9e9cf5a13c59f5908e7d git tracks changes
4dbe0ff1cd9ade95b0ca83e22ac6dc27619b1cd9 append GPL
1076977642fedc84ef89be1a840850b991e434c7 add distributed
a2fdd707b60ff78bada28410dc0ca8b6dd027e43 wrote a readme file
```

### 2.3 版本回退
在Git中，`HEAD`表示当前版本，上一个版本为`HEAD^`或`HEAD~1`，上上一个版本为`HEAD^^`或`HEAD~2`，上上个版本为`HEAD^^^`或`HEAD~3`......

使用`git reset`命令可以让版本进行回退, 例如回退到上一版本
```ini
$ git reset --hard HEAD^ 
$ git reset --hard HEAD~1 
```
回退到指定commit id的版本
```ini
$ git reset --hard 4dbe0ff1cd9ade95b0ca83e22ac6dc27619b1cd9
```
如果从版本B回退到之前的版本A，在A之后的提交信息将会在git log中**查询不到**commit id,但是如果在当前命令行窗口还可以看到版本B的commit id，或者使用`git reflog`可以看到版本变动的过程及其id
```ini
$ git reflog
cde5cd3 HEAD@{0}: reset: moving to cde5cd3cf18629fbc9d9f033447478b90355224c
4dbe0ff HEAD@{1}: reset: moving to HEAD^^
d5c7d80 HEAD@{2}: reset: moving to HEAD~1
cde5cd3 HEAD@{3}: commit: 提交2
d5c7d80 HEAD@{4}: commit: 提交
7361c51 HEAD@{5}: commit: git tracks changes
4dbe0ff HEAD@{6}: reset: moving to 4dbe0ff
a2fdd70 HEAD@{7}: reset: moving to a2fdd70
4dbe0ff HEAD@{8}: reset: moving to 4dbe0ff1cd9ade95b0ca83e22ac6dc27619b1cd9
1076977 HEAD@{9}: reset: moving to HEAD^
4dbe0ff HEAD@{10}: commit: append GPL
1076977 HEAD@{11}: commit: add distributed
a2fdd70 HEAD@{12}: commit (initial): wrote a readme file
```


在Git中除了使用`git reset`命令以外，还可以使用`git revert`命令来达到类似的的效果，但这两者还是有所区别：         
* `git reset`是将版本**回退**到之前的版本，但那个版本之后的版本将不会被保留
* `git revert`是将指定版本**反做**，例如按照时间线的版本为a->b->c, 当HEAD为版本c时反做版本b，即在提交版本b时，添加了file1.txt，则反做就是执行相反操作，删除file1.txt
    > 如果**在一个文件中**多次修改内容并且进行提交，反做**非最后一次提交**时，会提示发生冲突，需要手动解决

➦[Git恢复之前版本的两种方法reset、revert（图文详解）](https://blog.csdn.net/yxlshk/article/details/79944535)

### 2.4 撤销修改
如果需要**撤销在workspace中的修改**，可以使用`git checkout -- filename`命令(注意文件名前的空格)
```ini
$ git checkout -- readme.md 
```
* 如果readme.md自修改后第一次放进index中，则git checkout后版本和repository中的版本是一致的
* 如果readme.md之前放进过index中，则git checkout后版本和前一次添加到index中的版本保持一致       
 
即`git checkout`后的版本和最新的`git add`或`git commit`的文件保持一致 
<br>


如果需要**撤销workspace添加到index中的修改**，则可已使用`git reset HEAD filename`命令
```ini
$ git reset HEAD readme.md
```
`git reset`不仅可以回退版本，还可以回退index中的修改，即将repository中最新的版本`HEAD`替换掉index中修改过的文件。


### 2.5 删除文件
如果工作区中的文件和版本库中的**版本一致**，使用`git rm <filename>`命令删除工作区文件，并且将这次删除放入暂存区
```ini
$ git rm readme.md
$ git commit -m "rm readme.md"
```

如果在workspace中修改过文件，无论有没有添加到index中，即**工作区和版本库中文件版本不一致**，单纯使用`git rm`命令会出现错误，此时需要加上参数`-f`，即使用`git rm -f <filename>`进行删除
```ini
$ git rm -f readme.md
```

如果只是想删除版本库中的文件，**但保留工作区文件**，则使用`git rm --cached <filename>`可以删除暂存区文件，并将这次删除的操作添加到暂存区中，即此时该文件处于*untracked*状态
```ini
$ git rm --cached readme.md
$ git commit -m "untrack reame.md"
```


## 三、远程仓库
虽然Git是一个**分布式**的版本控制工具，并不存在着中央服务器，但是在实际使用中还是存在一个远程仓库，方便用于同步代码或协同开发的。    

### 3.1 添加远程仓库
使用`git remote add <仓库名称> <仓库地址>`命令可以建立本地仓库和远程仓库的连接
```ini
$ git remote add origin git@github.com:server-name/learn-git.git
```
添加完成后可以使用`git remote -v`查看已关联的远程仓库信息
```ini
$ git remote -v
origin	git@gitee.com:server-name/learn-git.git (fetch)
origin	git@gitee.com:server-name/learn-git.git (push)
```
以及可以使用`git remote rename <old name> <new name>`来更新远程仓库名称和使用`git remote remove <name>`来移除远程仓库名称

### 3.2 推送文件
使用`git push <远程主机名> <本地分支名>:<远程分支名>`命令可以推送本地仓库文件至远程仓库，特别的使用加上参数`-u`，可以给推送的分支加上（跟踪）引用，即使当前分子和多个远程主机存在关联，后面也可以不加任何参数使用git push推送到和本次相同的远程仓库中。
```ini
$ git push -u origin master #第一次
$ git push                  #后续可简写
```
> 如果远程仓库地址为ssh url，则需要提前配置当前主机和远程主机的ssh公私钥

特别的，使用`git push -f origin master`命令将本地文件推送到远程，并**强制覆盖**，使用时需要特别小心，**谨慎使用**。

### 3.3 代码克隆
使用`git clone <远程仓库地址>`命令可以将远程仓库的文件克隆到本地
```ini
$ git clone git@gitee.com:server-name/learn-git.git
```





## 参考
https://www.cnblogs.com/ims-/p/9747333.html

https://www.ruanyifeng.com/blog/2015/12/git-cheat-sheet.html

https://blog.csdn.net/qq_42780289/article/details/98353792












