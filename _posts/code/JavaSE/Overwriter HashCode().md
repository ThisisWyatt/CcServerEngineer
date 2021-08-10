---
title: 重写HashCode()
date: '2020-4-24 23:15'
categories: Code
tags: JavaSE
# photos:
#   - - 'https://i.loli.net/2020/04/26/5lHToksgpxCIK9m.png'
abbrlink: 60908
declare: true
wordCount: true
---
重写HashCode方法并无绝对的标准答案，只要满足一个原则即可，即**相同对象的Hashcode必须相同**。

<br>
<!-- more -->
    
### 重写原因
* SpringBoot Data Jpa domian类设置关联主键时需要重写hashcode()和equals()方法   
* 判断两个两个对象相等时需要重写hashcode()和equals()方法。
    * 因为如果两个对象的引用相同，他们之前互相调用equals()为true即两个对象相同。如果不重写hashCode()方法，系统默认的hashCode()是本地计算对象的内存地址（经哈希算法），此时不会相同。就会产生矛盾。

        >两个对象equals相等，则它们的hashcode必须相等，反之则不一定。
        >两个对象=相等，则其hashcode一定相等，反之亦然。
        >两个对象\==相等，则其hashcode一定相等，反之不一定成立。 //有可能是Integer对象比较之类的
        >两个对象==相等，则其hashcode一定相等，反之不一定成立。

### 重写方法
* 第一步：取一个初值，一般取17
    `int code=17;`
* 第二步：解析类中与equals()相关的字段(如果某些字段值都相同，则没必要计算)
    * 字段a类型为boolean 则`[hashCode] = a ? 1 : 0;`
    * 字段b类型为byte/short/int/char, 则`[hashCode] = (int)b;`
    * 字段c类型为long， 则`[hashCode] = (int) (c ^ c>>>32);`
    * 字段d类型为float, 则`[hashCode] = d.hashCode()`(内部调用的是Float.hashCode(d)， 而该静态方法内部调用的另一个静态方法是Float.floatToIntBits(d))
    * 字段e类型为double, 则`[hashCode] = e.hashCode()`(内部调用的是Double.hashCode(e)， 而该静态方法内部调用的另一个静态方法是Double.doubleToLongBits(e),得到一个long类型的值之后，跟情况三进行类似的操作，得到一个int类型的值)
    * 引用类型，若为null则hashCode为0,否则`递归调用该引用类型的hashCode()`。
    * 数组类型。(要获取数组类型的hashCode,可采用如下方法：`s[0]*31 ^ (n-1) + s[1] * 31 ^ (n-2) + ..... + s[n-1]`， 该方法正是String类的hashCode实现所采用的算法）
* 第三步：整合
    * `code = code * 31 + [hashCode]`;
<br>

### 注意
* **如果重写hashcode方法是用到了对象类型，那么该对象类型也必须要重写hashcode()方法**，否则每次得到的hashcode值不一定一致，那么重写hashcode方法的意义就不大了
* 如果初始值result不取17而取0的话，则对于hashCode为0的字段来说就没有区分度了，这样更容易产生冲突。比如两个自定义类中，一个类比另一个类多出来一个或者几个字段，其余字段全部一样，分别new出来2个对象，这2个对象共有的字段的值全是一样的，而对于多来的那些字段的值正好都是0,并且在计算hashCode时这些多出来的字段又是最先计算的，这样的话，则这两个对象的hashCode就会产生冲突。
    >多出来的字段hashCode为0，且先计算则 多出来的字段哈希和为 0*31+hashcode(多出字段的值)= 0 (初始code为0)，这样加上共同对象产生的计算结果和较少字段那个对象冲突
* **不能包含equals方法中没有的字段**，否则会导致相等的对象可能会有不同的哈希值。（即对类中每一个重要字段，也就是影响对象的值的字段，也就是equals方法里有比较的字段，进行操作）
* String对象和Bigdecimal对象已经重写了hashcode方法，这些类型的值可以直接用于重写hashcode方法；(上述的是Java八中基本数据类型，这两个是封装类型)
* `code = 31 code + (dishCode !=null ?dishCode.hashCode() : 0);`，这里面为啥用个31来计算，而且很多人都是这么写的，这是因为**31是个神奇的数字，任何数n*31都可以被jvm优化为(n<<5)-n，移位和减法的操作效率比乘法的操作效率高很多**.
* Google首席Java架构师Joshua Bloch在他的著作《Effective Java》中提出了一种简单通用的hashCode算法：

    * 初始化一个整形变量，为此变量赋予一个非零的常数值，比如int result = 17;

    * 如果是对象应用（例如有String类型的字段），如果equals方法中采取递归调用的比较方式，那么hashCode中同样采取递归调用hashCode的方式。否则需要为这个域计算一个范式，比如当这个域的值为null的时候（即 String a = null 时），那么hashCode值为0

 
### demo
```java
    Class User {
        private String name;
        private String password;

        //get() 
        //set()方法

        @Override
            public boolean equals(Object o) {
            if (this == o) return true; //如果两个直接相等，就不用比较了
            if (o == null || getClass() != o.getClass()) return false; //o.getClass() 因为Object 是类似接口（父类） 所以判断o是否是User类，
            UserWrite userWrite = (UserWrite) o;
            return Objects.equals(name, userWrite.name) &&
                    Objects.equals(password, userWrite.password);
        }

        @Override
        public int hashCode() {
            int code = 17;
            code = code * 31 + (name != null ? name.hashCode() : 0);
            code = code * 31 + (password != null ? password.hashCode() : 0);
            return code;
        }
    }
```

参考:
1. <https://www.cnblogs.com/stitchZsx/p/9558843.html>
2. <https://blog.csdn.net/zhengchao1991/article/details/78916471>