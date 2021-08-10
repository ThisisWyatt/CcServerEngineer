---
title: Linux命令
categories: Code
tags: Linux
declare: true
wordCount: true
abbrlink: 82734d8
date: 2020-11-24 23:04:33
---

基础Linux命令
<!-- more -->


## 1.使用命令
1. 详细信息 `ls -l`
2. 删除 rm(remove)
	* 删除文件 `rm test.txt`
	* 删除文件夹  `rm -r dictory`
3. 移动 mv(move)
	* 改名 `mv file1.txt file2.txt`
	* 移动 `mv workspace/space2/helloworld.txt workspace/space1/helloworld.txt`
4. 复制 cp(copy)
	* cp `workspace/space2/helloworld.txt workspace/space1/helloworld.txt`
5. 显示文件信息
	* 显示全部 `cat file.txt`
	* 查看开头部分`head file.txt -n 4`
	* 查看结尾部分`tail file.txt -n 4`
	* 循环读取(每隔10s)动态改变的日志文件`tail -f -s 10 file.log`,
	* 暂时少量查看`less file.txt `

6. 文件权限     

|u(author)|g(group)|o(other)|
|-|-|-|
|rw\-(可读可写)|r\-x(可读可运行)|r\-\-(可读)|
<!-- gaodafeng shegnxia shegnxiashegnxi youshoushizhi -->
> r : 可读      
> w : 修改、删除      
> x : execute可执行

* author限制可读 `chmod u-r file.txt`
* author增加可读 `chmod u+r file.txt`
* group、other限制读`chmod go-r file.txt`
* 为所有用户增加可读 `chmod a-r file.txt`
* 为所有用户添加所有权限 `chmod 777 file.txt`
	> `rwx rwx rwx`二进制 `111 111 111`, 可利用二进制添加相应的权限

6. 设置目录权限
* 为user添加/user/local/的权限 chown -R user /usr/local/ 

7. 检索grep

* `grep keyWord file.txt`
* 配合正则表达式
	* `grep ^KeyWord file.txt`
8. 统计wc
	* 统计行数、单词数、字符数`wc file.txt`
	* 统计byte数`wc -c file.txt`
	* 统计字符数`wc -m file.txt`
	* 统计最新的一行的长度`wc -l file.txt`
	* 统计最长的一行的长度`wc -L file.txt`
	* 统计单词个数 `wc -w file.txt`

9. [查看系统信息](https://www.linuxprobe.com/linux-cat-system.html)
	* lsb_release -a 
		> Linux Standard Base
	* cat /etc/os-release

10. [查看是否安装某个软件]
	* Ubuntu `dpkg -s "软件或包的名字"`
	* CentOS `rpm -qa | grep "软件或者包的名字"`
		> `rpm: redhat package manager; -q: query; a: all` 

11. 查看网络活动相关信息
	* `netstat -nptl` 
		> connetction、tcp、programs、listening 
	```ini
		[root@cloudr etc]# netstat -h
        -r, --route              display routing table
        -I, --interfaces=<Iface> display interface table for <Iface>
        -i, --interfaces         display interface table
        -s, --statistics         display networking statistics (like SNMP)
        -M, --masquerade         display masqueraded connections

        -v, --verbose            be verbose //详细的
        -e, --extend             display other/more information
        -p, --programs           display PID/Program name for sockets	//PID、程序名
        -o, --timers             display timers
        -c, --continuous         continuous listing

        -l, --listening          display listening server sockets //监听中的套接口
        -a, --all                display all sockets (default: connected)
        -F, --fib                display Forwarding Information Base (default)
        -C, --cache              display routing cache instead of FIB
        -Z, --context            display SELinux security context for sockets

		-t, --tcp	//tcp相关
		-u, --udp	//udp相关
		-U, --udplite
		-w, --raw 	//原始
		-x, --unix	//unix相关
	```


## 2.用户和工作组管理类命令
### 2.1 查看用户
* 查看用户信息命令 `id`
* 显示用户名称命令 `logname`
* 查看用户操作命令 `history`
	* 执行第几条命令 `!数字` 
* 查看用户所属用户组 `groups`
### 2.2 用户组管理
* 查看所有用户组命令 `cat /etc/group`
* 创建一个用户组命令 `groupadd 用户组名` 
* 删除一个用户组命令 `groupdel 用户名`
* 更改用户组名命令 `groupmod 新用户组名 旧用户组名`
* 添加用户到用户组 `usermode -a -G groupName userName`
	> -a : appened，不能直接-G，否则会离开原有用户组
### 2.3 用户管理
* 查看当前登录用户名命令 `w、who、users、whoami`
* 切换用户 `su [选项] 目标用户`
* 新增用户账号 `useradd 用户名`
* 删除用户账号 `userdel 用户名`
	* 强制删除`userdel -f 用户名` 
	* 删除用户的同时，删除与用户相关的所有文件 `userdel -r 用户名`
* 为用户设置口令 `passwd 用户名`
* 查看用户所属组命令 `groups 用户名`
* 变更用户账号信息命令 `usermod [选项] 用户名`

### 2.4 用户权限管理
普通用户如何获得root权限
* 用户使用sudo命令并输入口令          
在文件/etc/sudoers第100行后添加`username  ALL=(ALL)   ALL`后保存修改，执行命令时输入当前用户口令，即可执行root权限
* 用户组使用sudo命令并输入口令          
在文件/etc/sudoers第100行后添加`%groupname  ALL=(ALL)   ALL`后保存修改，执行命令时输入当前用户口令，即可执行root权限
* 用户使用sudo命令并（不需要输入口令）
在文件/etc/sudoers第100行后添加`username  ALL=(ALL)   NOPASSWD: ALL`后保存修改，执行命令时输入当前用户口令，即可执行root权限
* 用户组使用sudo命令并（不需要输入口令）
在文件/etc/sudoers第100行后添加`%groupname  ALL=(ALL)   NOPASSWD: ALL`后保存修改，执行命令时输入当前用户口令，即可执行root权限


* 显示当前挂载在Linux系统的每个分区 df
	* `df -h` 将分区小转换为用户可读的形式

* 查看文件大小
	* `ll -h` h: human-readable



* 归档命令`tar`
	* `tar -cvf testTar.tar * `
		* c --create: 创建一个新的归档文件
		* v --verbose: 显示已处理文件
		* f --file=ARCHIVE: 指向归档文件的名字
	* `tar -cvzf testTar.tar.gz *`
		* z --zip: 压缩归档文件 

	* `tar -xvzf testTar.tar.gz`
		* x --extract : 提取（解压）

	* `tar czvf - importantstuff/ | ssh useranme@10.0.0.123  "cat > /home/username/myfiles.tar.gz` ： 归档importantstuff目录下的文件至10.0.0.123主机的/home/username/myfile.tar.gz文件
		* \- : 该连接符表示将数据输出到标准标准的输出设备上，这允许将归档文件文件名的详细信息推后至该命令的末尾。
		* | : 管道，将这个未命名的、压缩的归档文件传输至远程服务器的ssh登陆	
		* "cat > /home/username/myfiles.tar.gz" : 键入内容至myfiles.tar.gz(覆盖)


* 文件分割 split
	* `split -4 README.md`: 将README.md文件每4行分割为以x开头的文件,例如xaa、xab、xac；
	* `split 2KB README.md REAME.md.part`: 将README.md分割为以REAMD.md.part开头的文件，例如REAME.md.part.aa、REAME.md.part.ab

* 搜索文件命令locate
	* `locate *.mp4`
		> locate命令并非真正地文件系统本身，而是在一个已存在的索引中进行简单的搜索，但是该索引**并不是实时性的**，默认是在系统每次启动时自动更新，所以会因为索引过时时搜索不到文件，使用`updatedb`命令更新.<br>

		> centos安装命令`sudo yum install mlocate`

* 指定目录下查找文件find
	* `find readme.md` : 在当前目录下查找文件名为readme.md的文件（默认为当前目录下查找）
	* `find /home/cc1/dd/ -iname "*.java" -exec tar -rvf javaZip.tar {} \;`
		* -exec: shell的内建命令，在当前shell下用exec后面的命令替换掉当前执行的进程，并将老进程的环境清除掉。
		* -iname: 忽略名字字母大小写查找
		* {}: 告诉find命令，对于每个查找到的文件执行官exec后面的命令
		* \\;: 格式，必须 










https://www.cnblogs.com/zox2011/archive/2013/05/28/3103824.html
