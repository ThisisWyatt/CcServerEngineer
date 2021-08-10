---
title: Spring Boot集成Druid
categories: Code
tags:
  - JavaWeb
declare: true
wordCount: true
abbrlink: 3570abc7
date: 2020-05-19 21:10:10
---
[Druid官方文档](https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98)
Druid是阿里巴巴计算平台事业部出品，为监控而生的一个数据库连接池。它在监控、可扩展、稳定性和性能方面具有明显的优势，通过其提供的监控功能可以观察数据库连接池和SQL查询功能，使用Driud连接池可以提高数据库的访问性能。以下介绍一下Spring Boot如何简单集成Druid.

<!-- more -->

### 1.引入依赖
```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.1.9</version>
</dependency>
```

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.4</version>
</dependency>
```
以上有两种依赖，但是根据我的测试第二种也是很多书籍中的用到的，在Spring Boot的pom.xml中还是会报异常，无法正常使用，而使用第一种不会。我查询资料和询问多方后，基本可以理解Spring Boot中使用第一种就好，因为那不是写着`druid-spring-boot-starter`吗。。。。 至于为啥书籍中为什么用的第二种，我猜测可能是以前spring boot老版本还是能够兼容druid，或者是我的用法可能出错，此处暂不深究。

<br>
<br>
<br>

### 2.Druid配置

* 在`application.yml`添加  
   
```yml
spring:
  datasource:
    #  Mysql连接信息
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/hibernate?characterEncoding=utf-8
    username: root
    password: 
    #  数据源类别
    type: com.alibaba.druid.pool.DruidDataSource
    #  初始化大小
    druid:
      min-idle: 5
      initial-size: 5
      max-active: 20
      #      配置获取连接等待超时的时间（毫秒）
      max-wait: 60000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接（毫秒）
      time-between-eviction-runs-millis: 60000
      # 配置一个连接在池中最小的生存时间（毫秒）
      min-evictable-idle-time-millis: 30000
      validation-query: SELECT 1 FROM DUAL
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      # 打开PSCache ，并指定每个连接上PSCache的大小
      pool-prepared-statements: true
      max-pool-prepared-statement-per-connection-size: 20
      # 配置监控统计拦截的filters

      filters: stat,wall
```

<br>
<br>
<br>

### 3.开启监控功能
* 开启监控的方法有很多种，这里介绍在工程`/src/main/java/com.smart.demo.filter`下新建一个配置类DruidConfiguration.java,代码如下：
```java
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 白名单 也可添加黑名单 第一个参数改为“deny”即可(deny优先级高于allow)
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 监控页面登陆账号
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        // 监控页面登陆密码
        servletRegistrationBean.addInitParameter("loginPassword", "1111");
        servletRegistrationBean.addInitParameter
        // 是否能够重置数据
        ("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加需要忽略的格式
        filterRegistrationBean.addInitParameter("/exclusions", "*.js,*.jpg,*.png,*/css,/druid/*");
        return filterRegistrationBean;
    }
}
```

<br>
<br>
<br>

### 4.测试
写好以上配置后，重启一下项目，访问`http://localhost:8080/druid/index.html`,输入在上面配置的账号和密码，即可进入监控页面。
<br>
![登陆页面](https://cdn.jsdelivr.net/gh/cloud-r/GitakRepository/static_files/blog/img/20200519211112.png)
![监控](https://cdn.jsdelivr.net/gh/cloud-r/GitakRepository/static_files/blog/img/20200519211156.png)


<br>
<br>
<br>
<br>
<br>

参考
* [1]黄文毅.一步一步学Spring Boot。北京：清华大学出版社，2019.