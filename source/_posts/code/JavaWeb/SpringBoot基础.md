---
title: SpringBoot基础
categories: Code
tags: JavaWeb
# photos:
#   - - 'https://i.loli.net/2020/04/26/WT5Mgd6G2h1Fefa.png'
abbrlink: 25912
data: 2020-04-22 02:40:10
declare: true
wordCount: true
---
Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，Spring Boot致力于在蓬勃发展的快速应用开发领域(rapid application development)成为领导者。
<!-- more -->



## 一、注解
### 1.1 Spring注解
* @GetMapping、@PostMapping和@RequestMapping的区别
    * @GetMapping
        >用于将HTTP GET请求映射到特定处理程序方法的注释。具体来说，@GetMapping是一个作为快捷方式的组合注释@RequestMapping(method = RequestMethod.GET)。

    * @PostMapping
        >用于将HTTP POST请求映射到特定处理程序方法的注释。具体来说，@PostMapping是一个作为快捷方式的组合注释@RequestMapping(method = RequestMethod.POST)。

    * @RequestMapping
        >一般情况下都是用@RequestMapping（method=RequestMethod.），因为@**RequestMapping可以直接替代以上两个注解**，但是以上两个注解并不能替代@RequestMapping，@RequestMapping相当于以上两个注解的父类！ （找到相应的控制器）
* @RestController
    * @Controller和@ResponseBody的合集，表示被标记的对象是REST风格的Bean
    * 返回结果默认为字符串
        
* @Controller 
    * 返回结果默认为视图。负责处理由DispatcherServlet分发的请求，将用户的请求经过业务层处理之后封装成一个Mode,然后将该model递交给相应的视图层，最后返回视图层。

* @ResponseBody      
    * 将内容或Java对象转换成响应报文的正文返回 
    * 修改了Controller的返回要求，返回结果为字符串

* @RequestBody
    * 用于**接收前端传给后端的一些非HTML页面的其他格式数据（例如XML、JSON）**，是利用将请求报文中的正文自动转换为绑定给方法参数的变量字符串，而Get方法无请求体，前端不能使用Get方法提交而需要使用Post方法提交
    
* [@RequestParam](https://www.cnblogs.com/tomingto/p/11377138.html) 将请求中的值赋给被注解的方法参数
    > `https://www.notecc.ink/username?q=123`
    ```java
        @RequestMapping("query")
        public ModelAndView query(@RequestParam(value = "q", required = false, defaultValue = "1234567") String q) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("q", q);
            mav.setViewName("blogQuery");
            return mav;
        }

        @RequestMapping("/returnView")
        public String hello(@RequestParam String name, Model model){
            model.addAttribute("name",name);
            return "hello";
        }
    ```

* [@PathVariable](https://blog.csdn.net/qq_29115715/article/details/80090403)
    * 可以将 **URL中占位符参数**绑定到控制器处理方法的入参中：URL 中的 {xxx} 占位符可以通过@PathVariable(“xxx“) 绑定到操作方法的入参中。
    > `https://www.note.ink/username/2`
    ```java
        @RequestMapping("/{id}")
        public ModelAndView show(@PathVariable("id") Integer id) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("id", id);
            mav.setViewName("blog");
            return mav;
        }

    ```
* @MatrixVariable： 用于获取复杂URI



* @Autowired 和 @Resource
    >@Autowired默认按照**byType**方式进行bean匹配，@Resource默认按照**byName**方式进行bean匹配

    >@Autowired是Spring的注解，@Resource是J2EE的注解，根据导入注解的包名就可以知道。

    >Spring属于第三方的，J2EE是Java自己的东西。因此，建议使用@Resource注解，以减少代码和Spring之间的耦合。

* @Service
    * service标注**业务层组件**,这个注解是写在类上面的，标注将这个类交给Spring容器管理，spring容器要为他创建对象

* @Scope 用来定义Bean的作用域 
    * singleton： 全局有且仅有一个实例
    * prototype： 每次获取时都会有一个新的实例
    * request：针对每一次HTTP请求都会产生一个新的Bean
    * session：针对每一次HTTP请求都会产生一个新的Bean，但该Bean只在当前HTTP session中有效
    * global session：与session类似，不过它仅仅只在基于Porlet的Web应用中才有效 
    > JSR 330标准中默认Bean的作用域**默认为singleton**

### 1.2 Spring Boot注解
* @Value
    * 注入Spring Boot配置的文件application.properties中配置的属性值

* @ConfigurationProperties(prefix="server")
    * 获取配置文件中内容，prefix为其前缀，例如获取serverProperties.properties中内容，prefix为server

* @PropertySource("classpath:serverProperties.properties")
    * 加载配置类

### 1.3 JPA注解
* @Entity
    * 表明被标注的类是一个实体类

* @Table
    * 指出实体类对应的表名
* @Id
    * 声明实体类对应的属性映射为数据库的主键列
* @GenerateValue
    * 用于标注主键的生成策略
        * `@GeneratedValue(strategy = GenerationType.IDENTITY)` 自增
        * `@GeneratedValue(strategy = GenerationType.SEQUENCE)` 通过序列产生主键
        * `@GeneratedValue(strategy = GenerationType.AUTO)` 自动选择主键生成策略，以适应不同的数据库移植。
* [@Column](https://www.cnblogs.com/lovetl/p/11685843.html)
    * 属性
        * name属性定义了被标注字段在数据库表中所对应字段的名称；
        * unique属性表示该字段是否为唯一标识，默认为false。如果表中有一个字段需要唯一标识，则既可以使用该标记，也可以使用@Table标记中的@UniqueConstraint。
        * nullable属性表示该字段是否可以为null值，默认为true。
        * insertable属性表示在使用“INSERT”脚本插入数据时，是否需要插入该字段的值。
        * updatable属性表示在使用“UPDATE”脚本插入数据时，是否需要更新该字段的值。insertable和updatable属性一般多用于只读的属性，例如主键和外键等。这些字段的值通常是自动生成的。
        * columnDefinition属性表示创建表时，该字段创建的SQL语句，一般用于通过Entity生成表定义时使用。（也就是说，如果DB中表已经建好，该属性没有必要使用。）
        * table属性定义了包含当前字段的表名。
        * length属性表示字段的长度，当字段的类型为varchar时，该属性才有效，默认为255个字符。
        * precision属性和scale属性表示精度，当字段类型为double时，precision表示数值的总长度，scale表示小数点所占的位数。

* @Modifying
    * 在@Query注解中编写JPQL实现DELETE和UPDATE操作的时候必须加上@modifying注解，以通知Spring Data 这是一个DELETE或UPDATE操作。
    * UPDATE或者DELETE操作需要使用事务，此时需要 定义Service层，在Service层的方法上添加事务操作。
    * 注意JPQL不支持INSERT操作。　
    * ~<https://www.cnblogs.com/zhaobingqing/p/6864223.html>~    


## 二、开发起步
### 2.1 自定义配置类
当要配置的自定义属性比较多时，可以考虑自定义一个配置类。    
* 在`src/main/resources`目录下定义配置文件
```
server.port=8888
server.servlet.context-path=/demo
```
* 编写自定义配置类
```java
@Component
@PropertySource("classpath:serverProperties.properties")
public class ServerProperties {
    @Value("${server.port}")
    private String port;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
```









<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

>参考：    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<https://blog.csdn.net/qq_41973208/article/details/85008962> <br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<https://www.cnblogs.com/lovetl/p/11685843.html><br>
>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<https://www.cnblogs.com/zhaobingqing/p/6864223.html><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;吴胜.Spring Boot开发实战. 北京: 清华大学出版社, 2019.


