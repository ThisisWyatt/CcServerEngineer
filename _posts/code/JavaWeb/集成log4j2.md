---
title: Spring Boot集成Log4j2
categories: Code
tags:
  - JavaWeb
declare: true
wordCount: true
abbrlink: 6b344792
date: 2020-05-21 12:20:10
---
[Log4j2官方文档](http://logging.apache.org/log4j/2.x/index.html)     

<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Log4j2是Apache的一个开源项目，虽然Spring Boot默认集成来了Logback，但相比于此，我更喜欢使用Log4j2,它不但在Log4j的基础上新增了许多改进，而且也支持了Logback中的一些特性，更加灵活。通过它我们可以将日志信息输出到控制台中，在某些情况下更加方便调试；跟踪代码运行轨迹，方便Debug；也可周期性的记录到文件中，以供其他应用获取相关信息。以下将介绍Spring Boot如何简单集成Log4j2.<br>

<!-- more -->
 
## 1. 引入依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```
* 排除Spring Boot默认使用的Logback      

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
* 我看到一些资料上是在`spring-boot-starter-web`排除该包依赖，我的是在`spring-boot-starter`中排除，所以具体情况还是最好自己排除jar包冲突。

## 2. 配置文件
* Log4j2支持xml文件log4j2.xml,Java特性文件log4j2.properties,或者直接在项目配置文件中配置，但是xml配置灵活性更高。    
在`/src/main/resources`下添加`log4j2.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<configuration status="WARN">
    <appenders>
        <!-- 控制台 -->
        <Console name="Console" target="SYSTEM_OUT">
            <!--指定日志输出格式-->
            <PatternLayout pattern="[%d{HH:mm:ss:SS}] [%p] - %l - %m%n"/>
        </Console>
        <!-- 日志文件 -->
        <RollingFile name="RollingFileInfo" fileName="C:/Users/life/Desktop/goInfo.log"
                     filePattern="Y:/$${date:yyyy-MM}/info-%d{yyyy-MM-dd}-%i.log}">
            <Filters>
                <ThresholdFilter level="INFO"/>
            </Filters>
            <PatternLayout pattern="[%d{HH:mm:ss:SS}] [%p] - %l - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy/>
                <SizeBasedTriggeringPolicy size="500MB"/>
            </Policies>
        </RollingFile>

    </appenders>
    <loggers>
        <root level="WARN">  <!-- 输出级别 -->
            <appender-ref ref="Console"/>
            <!-- <appender-ref ref="RollingFileInfo"/>-->
        </root>
    </loggers>
</configuration>
```

> 输出级别：all< debug < info < warn < error < fatal < off ,**输出级别不低于设定级别的日志信息**

## 3. 添加Log4j2配置
在application.yml添加：
```yml
logging:
    config: classpath:log4j2.xml
```