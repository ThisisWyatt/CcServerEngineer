---
title: Hexo+Ocean添加字数统计
categories: Construct Blog
# photos:
#   - - 'https://i.loli.net/2020/04/26/CJeY94S82GitImM.png'
abbrlink: 26565
data: 2020-04-26 19:52:10
declare: true
wordCount: true
---
❄
<!-- more -->

### 1. `themes\ocean\layout\_partial`路径下创建文件`word-count.ejs`,并输入以下代码：
```javascript
    <div style="margin-top:10px;">
        <span class="post-time">
            <span class="post-meta-item-icon">
                <i class="fa fa-keyboard-o"></i>
                <span class="post-meta-item-text"> words count: </span>
                <span class="post-count"><%= wordcount(post.content) %></span>
            </span>
        </span>

        <span class="post-time">
            &nbsp; | &nbsp;
            <span class="post-meta-item-icon">
                <i class="fa fa-hourglass-half"></i>
                <span class="post-meta-item-text"> expected cost: </span>
                <span class="post-count"><%= min2read(post.content) >1 ? min2read(post.content)+" minutes" : min2read(post.content)+" minute"%> </span>
            </span>
        </span>
    </div>
```
### 2. 在`themes\ocean\layout\_partial\article.ejs`中添加以下代码
```javascript
    <!-- 开始添加字数统计-->
    <% if(theme.word_count ){%>
    <%- partial('word-count') %>
    <% } %>
    <!-- 结束 -->
```
* 具体位置是在`article-inner`的div中的header中，例如我的位置是
    <img style="align:left" src="https://i.loli.net/2020/04/26/MQ8jy37rkt5eLuD.png" width="70%">

### 3. 在主题配置文件`themes\ocean\_config.yml`中添加    
```javascript
    word_count: true
```

### 4. `hexo clean & hexo g & hexo s`



### 5. 总结：
* 创建一个ejs用来渲染前端显示的具体内容
* 设定ejs作用位置
* 创建一个变量选择是否开启此功能
    > 纯前端小白简单理解，期望与各路大佬多多交流

<br>
<br>
<br>

> 网络上都是以next的配置为主，但next很多是主题作者预先放置好的，所以作者找到了这篇为yilia添加此功能的，做了微小改动。    
> 参考 <https://chocolate.blog.csdn.net/article/details/104262008>