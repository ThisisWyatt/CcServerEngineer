---
title: Docker command
categories: Tools
tags: Cloud
declare: true
wordCount: true
abbrlink: 8f5fd259
date: 2021-03-20 11:54:34
---

## 一、Docker的基本使用和管理
### 1. Docker配置
* centos安装docker `yum install docker`
* 设置普通用户使用docker
    * 创建`docker`用户组 `groupadd docker`
    * 添加用户到`docker`用户组 `usermod -a -G docker userName`
    * 重启docker `systemctl restart docker`

<!-- more -->

/usr/share/zoneinfo/Asia/Shanghai /etc/localtime

### 2. Docker镜像操作
* 搜索镜像 `docker search keyword`
* 获取镜像 `docker pull imageName`
* 查看镜像信息 `docker iamges`
    * REPOSITORY：镜像所属的仓库
    * TAG：镜像的标签信息，用于标记同一仓库的不同镜像
        > 为了后续工作中方便使用镜像，可以使用`docker tag 名称:[标签] 新名称:[新标签]`
    * IMAGE ID：镜像的唯一ID号
        > 可通过ID号来获取对应镜像信息：`docker inspect id`
* 删除镜像 `docker rmi 仓库名称:标签 | ID号`
* 存出镜像 `docker save -o 存出文件名 存出的镜像`
* 载入镜像 `docker load < | -i 目标镜像文件`
* 上传镜像 
    * 打上私有的标签 `docker tag 名称:[标签] 新名称:[新标签]`
    * 上传 `docker push 仓库名称:标签`

### 3. Docker容器操作
* 创建 `docker create [OPTIONS] 镜像 运行的程序`
    > `docker crate -it hivemq/hivemq4 /bin/bash`
    * -i：让容器的输入保持打开状态
    * -t：让Docker分配一个伪终端
* 查看容器运行状态 `docker ps -a`
* 启动容器 `docker start 容器ID | 名称`
* 创建并运行容器 `docker [OPTIONS] run 仓库名称:标签`
    * -d 后台运行
    * --name：自定义一个名称
    * -p：指定端口映射，指定容器端口映射到主机端口，`主机端口:容器端口`
    * -P：随机端口映射，容器内部端口映射到主机的一个随机端口
    * -e：设置相关环境变量
    * TZ:设置容器内时区，例如`TZ="Asia/Shanghai"`
    * -v：设置数据卷       
        * 创建数据卷：`-v 目录`
        * 创建数据卷并挂载到主机目录： `-v 宿主机目录:容器数据卷目录`
        * 挂载数据卷容器：`--volumes-from 容器名`
* 容器的进入 `docker exec -it 容器ID | 名称 /bin/bash`
> 容器是一个与其中运行的shell命令共存亡的众安，shell命令运行则容器运行，shell命令停止则容器停止，使用exit命令会在退出容器的同时关闭容器（但可以docker ps、docker restart恢复），所以使用`ctrl+P & ctrl + Q`可以退出容器
* 容器终止运行 `docker stop 容器ID | 名称`
* 容器导出 `docker export 容器ID | 名称 > 文件名`
* 容器导入 `cat 文件名 | docker import - 生成镜像名称:标签`
* 容器的删除 `docker rm 容器ID | 名称`

<br><br>

***
<br><br>


## 二、Docker镜像管理
### 2.1 Docker的镜像结构
镜像不是一个单一的文件，而是由多层构成的。可以由`docker history`命令来查看镜像中各层的内容及大小，**每一层对应着Dockerfile中的一条指令**，并且有如下特点：    
* Dockerfile中的每个指令都会创建一个新的镜像层
* 镜像层可以被缓存和复用
* 当Dockerfile的指令修改了，复制的文件变化了，或者构建镜像时指定的变量灵不同了，对应的镜像层缓存就会失效
* 当某一层的镜像缓存失效，它之后的镜像层缓存都会失效
* 镜像层是不可变的，如果在某一层添加一个文件，然后在下一层删除它，则镜像中依然会包含该文件，只是这个文件在Docker容器中不可见了。













































<br><br><br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

***
<br><br>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>























/var/lib/docker/containers/3141e6fc8b6f094abf1e4887cffd5a1e256d2f6107cc2d38800a169c36dbf970

"PortBindings":{"1883/tcp":[{"HostIp":"","HostPort":"1884444"}],"8080/tcp":[{"HostIp":"","HostPort":"8081"}]},