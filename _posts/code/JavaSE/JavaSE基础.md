---
title: JavaSE基础
categories: Code
tags: JavaSE
photos: [https://cdn.jsdelivr.net/gh/cloud-r/GitakRepository/static_files/blog/img/121212java.jpg]
abbrlink: dc174266
date: 2020-04-29 19:38:10
declare: true
wordCount: true
---
❄
<!-- more -->

### JDK和JRE的区别

* JDK:Java开发工具包，提供了Java的**开发环境**和**运行环境**
* JRE:Java运行环境，提供Java的**运行环境**
* **JDK包含了JRE,还包含了java源码的编译器javac，以及很多java程序调试分析的工具**，如果只需要运行程序，JRE即可，如果还需要写程序则需要JDK <br>
***

### Java8新特性

* Date Time API :加强对日期和时间的处理
* Optional类：用来处理空指针的问题
* Lambda表达式
* 优化HashMap,当链表中的节点数据超过八个之后,该链表会转为红黑树来提高查询效率,从原来的O(n)到O(logn)

***

### Java的多态

* 多态：一种事物的多种存在形态，比如说男人和女人都是人，人可以分为男人和女人
* 好处：消除了类之间的**耦合关系**，大大提高了程序的**简洁性**和**可拓展性**
* 重写Overriding和重载Overloading
    * 重载：以类统一的方式处理不同数据的一种手段，实质就是**多个具有不同参数个数或者类型的同名函数，且返回类型可随意。**（*动态绑定*）
    * 重写：父类与子类之间的多态性，**实质就是子类对父类的函数进行重写定义**。子类的访问权限不能小于父类的访问权限。（否则调用的时候可能无法调用）（*静态绑定*）
        * 重写方法**参数列表、返回类型**必须与被重写的方法相同。
* 子类对象的多态性
    * 是**使用父类的引用，指向子类的对象**，然后虚拟方法调用子类重写父类的方法

***

### 数据类型 自动拆装箱

* 8种，**int long short char float double byte boolean**
* **自动装箱是Java编译器在基本数据类型和对于对象包装类型中做的一个转化**。比如int类型转化为Integer,double类型转为Double等，反之及时自动拆箱。

***

### Java中IO流分为几种

* 按照功能：**输入流**、**输出流**
* 按照类型:
    * **字节流**：按**8位传输以字节为单位**输出输出数据
    * **字符流**：按照**16位传输以字符为单位**输出输入数据

***

### 值传递和引用传递

* 值传递对于基本类型而言的，传递的是该变量的一个副本，改变副本不影响原变量
* 引用传递对于对象类型而言的，传递的是该对象地址的一个副本，并不是原对象本身，所以对引用对象操作会改变原对象。
* 一个对象被当做参数传递给一个方法后，此方法可改变它的属性，并返回变化后的结果，那么这里是什么传递？
    * 值传递。**Java没有引用传递**，方法的参数的值是对象引用（传入的变量）的一个副本，但指向同一个对象，所以在方法中改变对象也会改变原变量的值，因为他的引用的对象改变了。

***

### &和&&的区别

* &：按位于，**两边都要判断**

* &&：短路与，**左边的为true才会判断右边**

* 经常使用短路与，比如用户登陆时判定用户名不是null且不是空字符串时就只能用&&
    ````java
    username!=null&&username.equals("")
    ````

***

### String、StringBuffer和StringBuilder

* 都是可以储存和操作字符串
* **String是final类型**，每次声明的都是不可变更的对象，然后将指针指向新的String对象
* StringBuffer，StringBuilder是可变字符继承自AbstractStringBuilder，在原有对象上进行操作
* **StringBuffer是线程安全的，StringBuilder不是线程安全的**，但性能更高
* 使用场景：
    * **并发**必选StringBuffer
    * **迭代**必选StringBuilder
    * 普通场景使用String，避免不必要的类型转换开销

***

* Object若不重写hashCode()的话，hashCode()如何计算出来的？
    * Object的hashCode()方法是一个本地方法，**该方法是直接返回对象的内存地址**

***

### equals和hashcode

* 如果两个对象的引用相同，他们之前互相调用equals()为true即两个对象相同。如果不重写hashCode（）方法，系统默认的hashCode()是本地计算对象的内存地址（经哈希算法），此时不会相同。就会产生矛盾。
> 两个对象equals相等，则它们的hashcode必须相等，反之则不一定。<br>
>两个对象=相等，则其hashcode一定相等，反之亦然。<br>
>两个对象==相等，则其hashcode一定相等，反之不一定成立。 //有可能是Integer对象比较之类的<br>
>两个对象==相等，则其hashcode一定相等，反之不一定成立。<br>

***

### ==和equals的区别

* ==
    * **基本类型：比较值是否相同**
    * **引用类型：比较的引用是否相同**
* **equals默认情况下是引用比较**，只是**很多类重载了equals()方法 (也可自定义)** ，**比如String、Integer等将它变为值比较**

***

### final和static

* final
    * final可以修饰属性，**方法，类，局部变量**（静态变量、实例变量和局部变量）；
    * final修饰的方法**不能被子类重写**，但**可以被继承**；
    * final修饰的类**不能被继承**，没有子类，**final类中的方法默认是final的**
    * final**不能**用于修饰构造方法；
    * 对于基本类型数据，**final会将值变为一个常数（创建后不能被修改）**
* staic
    * static表示“全局”或者“静态”的意思，可以用来修饰：属性，方法，代码段，内部类（静态内部类或嵌套内部类），static**不可以修饰局部变量**
    * static修饰的属性的初始化在编译期（类加载的时候），初始化后能改变，并且每个对象都只有一个值，主要强调它们只有一个。
    * static修饰的属性、方法、代码段跟该类的**具体对象无关**，不创建对象也能调用static修饰的属性、方法等，
    * static和“this、super”势不两立，因为this、super正好跟具体对象有关。也就是说，它不依赖类特定的实例，被类的所有实例共享。只要这个类被加载，Java虚拟机就能根据类名在运行时数据区的方法区内定找到他们。因此，static对象可以在它的任何对象创建之前访问，无需引用任何对象。
    * 用public修饰的static属性和方法本质是全局变量和全局方法，当声明它类的对象时，不生成static变量的副本，而是类的所有实例共享同一个static变量。

* static final(等价于final staic)
    * static修饰的属性强调它们只有一个，final修饰的属性表明是一个常数（创建后不能被修改）。static final修饰的属性表示一旦给值，就不可修改，并且可以通过类名访问。static final也可以修饰方法，表示该方法不能重写，可以在不new对象的情况下调用

***

### 静态代码块与普通代码块的区别

* ````java
    public class HelloB extends HelloA 
    {
        public HelloB(){
        }

        {
            System.out.println("I’m B class");
        }

        static{
            System.out.println("static B");
        }

        public static void main(String[] args){
            new HelloB();
        }
    }
    class HelloA
    {
        public HelloA(){
        }

        {
            System.out.println("I’m A class");
        }
  
        static{
            System.out.println("static A");
        }
    }

    //输出结果
    static A
    static B
    I’m A class
    I’m B class
    ````
    * 执行顺序：
        1. 父类静态代码块
        2. 子类静态代码块
        3. 父类普通代码块
        4. 父类构造方法
        5. 子类普通代码块
        6. 子类构造方法

    * 相同点
        * 都是在JVM加载类时且**在构造方法执行之前执行**，在类中都可以定义多个，一般在代码块中对一些static变量进行赋值。
    * 不同点
        * 静态代码块在非静态代码块之前执行(**静态代码块—>非静态代码块—>构造方法**)。**静态代码块只在第一次new执行一次，之后不再执行，而非静态代码块在每new一次就执行一次**。非静态代码块可在普通方法中定义(不过作用不大)；而**静态**代码块不行。

***

### 接口和抽象类有什么区别

* 实现：抽象类的子类使用 **extends** 来继承；接口必须使用 **implements** 来实现接口。
* 构造函数：抽象类可以有构造函数；**接口不能有**。
* main 方法：抽象类可以有 main 方法，并且我们能运行它；**接口不能有 main 方法**。
* 实现数量：**类可以实现很多个接口**；但是**只能继承一个抽象类**。
* 访问**修饰符**：接口中的方法默认使用 public 修饰；抽象类中的方法可以是任意访问修饰符。

***

### String a="i" 和String b=new String("i")的区别

* 前者，Java虚拟机将其分配到**常量池**中,并把"i"的内存地址赋给a,如果还有一个String a1="i",也把"i"的内存地址赋给a1，即**a和a1这两个引用的是同一个地址，共享一个内存。**
* 后者，则会被分配到**堆内存**中，即新创建了一个对象，如果还有一个String b1=new String("i")，相当于又创建了一个对象，然后将其地址赋给b1，**虽然b和b1值相同，但是并不是一个对象。**

***

### String类的常用方法有哪些

* `indexof()`   ：放回指定字符索引
* `charAt()`    ：返回指定索引处字符
* `replace()`   ：字符串替换
* `trim()`      :去除字符串两端空白
* `spilt()`     ：分割字符串，返回一个分割后的字符串数组
* `getBytes()`  ：返回字符串的 byte 类型数组。ASCII码
* `length()`    ：返回字符串长度。
* `toLowerCase()`：将字符串转成小写字母。
* `toUpperCase()`：将字符串转成大写字符。
* substring()   ：截取字符串。
* equals()      ：字符串比较。

***

### Files的常用方法

* `Files.exists()`      ：    检测文件路径是否存在。
* `Files.createFile()`  ：创建文件。
* `Files.createDirectory()`：创建文件夹。
* `Files.delete()`      ：删除一个文件或目录。
* `Files.copy()`        ：复制文件。
* `Files.move()`        ：移动文件。
* `Files.size()`        ：查看文件个数。
* `Files.read()`        ：读取文件。
* `Files.write()`       ：写入文件。

***

### java创建对象的方法

1. 用new语句创建对象，是最常见的创建对象的方法。
2. 运用反射手段,调用java.lang.Class或者java.lang.reflect.Constructor类的newInstance()实例方法。
3. 调用对象的clone()方法。
4. 运用反序列化手段，调用java.io.ObjectInputStream对象的 readObject()方法。

***

### assert

* 断言。是在软件开发过程中的一种调试方法。它对一个boolean表达式进行检验。一个正确的程序必须保证这个表达式正确，如果不正确系统则会给出警告或者退出。一般来说，**asset用于保证程序最基本，最关键代码的正确性**。软件发布后，通常assert检查通常是关闭的。
* 

***

###  抽象类

* 抽象类不一定要有抽象方法
* 抽象类不能使用final,因为被final标记的类不能被继承，但抽象类就是为了让其子类继承的
* 抽象类和普通类的区别
    * **抽象类不能实例化**，普通类可以    绝不会拿3e
    * **普通类不能含有抽象方法**，抽象类可以有抽象方法
***

### lambda 表达式 
    * 示例：
        ```java
        // 1. 不需要参数,返回值为 5  
        () -> 5  
        
        // 2. 接收一个参数(数字类型),返回其2倍的值  
        x -> 2 * x  
        
        // 3. 接受2个参数(数字),并返回他们的差值  
        (x, y) -> x – y  
        
        // 4. 接收2个int型整数,返回他们的和  
        (int x, int y) -> x + y  
        
        // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)  
        (String s) -> System.out.print(s)
        ```
***


>参考：https://mp.weixin.qq.com/s/0KM4e3_zPczHh2TH2r_ncA