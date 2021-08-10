---
title: JavaSE容器
categories: Code
tags: JavaSE
photos: [https://cdn.jsdelivr.net/gh/cloud-r/GitakRepository/static_files/blog/img/121212java.jpg]
date: 2020-04-30 19:42:10
abbrlink: 6ea65bec
declare: true
wordCount: true
---
❄
<!-- more -->


> ➱https://www.cnblogs.com/daimasanjiaomao/p/11005764.html➲<br>

Java容器主要包括 Collection 和 Map 两种，Collection 存储着对象的集合，而 Map 存储着键值对（两个对象）的映射表。

### 常用容器目录
![常用容器目录](https://img-blog.csdnimg.cn/20190317184953342.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NoYWRvd196ZWQ=,size_16,color_FFFFFF,t_70)
***

### Collection和Collections的区别
* Collection是**集合类的上层接口**，继承他的接口主要有Set和List
* Collections是针对于集合类的一个**帮助类**，他提供了一系列的静态方法实现对集合的搜索、排序、线程安全化等操作。 
***

### map的分类和常见的情况 
* java.util.Map接口主要用于存储键值对，根据键得到值，**因此不允许键重复，但允许值重复**，它有四个实现类：
    * Hashmap:根据键的hashCode值存储数据，具有很快的访问速度，**遍历时，具有很快的访问速度，但取得的速度是随机的。Hashmap不支持线程的同步**,多个线程同时读取会导致数据的不一致，**若需要同步，可以使用Collections的synchronizedMap()方法使Hashmap具有同步能力或者使用ConcurrentHashMap**.
        * ConcurrentHashMap分段锁
        * HashMap允许任何类型的键和值对象，并**允许将null用作键或值**
    * Hashtable与HashMap类似，不同的是**它不允许记录的键或值为空，它支持线程同步**， 即任何时候只有一个线程能写Hashtable但也因此**导致了hashtable访问较慢**。
    * LinkedHashMap是Hashmap的一个子类，**保存了记录的插入顺序，用iterator遍历时先得到的记录肯定是先插入的。遍历速度比HashMap慢**，除非HashMap容量很大但实际数据较少。
    * TreeMap实现的是SortMap接口，**能够把它保存的记录按照键排序**，用iterator遍历的数据是排过序的。
        * TreeMap的实现底层实现**基于二叉树的红黑树**，插入的值是按一定顺序排序的。
    >一般情况下，用的最多的是HashMap，用它插入，删除，定位是最好的选择。但如果按照自然顺序或按照自定义顺序，TreeMap更好，如果要输入输出顺序一样，LinkedHashMap更好。
***

* HashMap的实现原理
    * HashMap概述： HashMap是基于哈希表的Map接口的非同步实现。此实现提供所有可选的映射操作，并允许使用null值和null键。此类不保证映射的顺序，特别是它不保证该顺序恒久不变。<br>
    HashMap的数据结构： **在java编程语言中，最基本的结构就是两种，一个是数组，另外一个是模拟指针（引用）**，所有的数据结构都可以用这两个基本结构来构造的，HashMap也不例外。HashMap实际上是一个“**链表散列**”的数据结构，即数组和链表的结合体。<br>
    当我们往Hashmap中put元素时,**首先根据key的hashcode重新计算hash值,根绝hash值得到这个元素在数组中的位置(下标),如果该数组在该位置上已经存放了其他元素,那么在这个位置上的元素将以链表的形式存放,新加入的放在链头,最先加入的放入链尾.如果数组中该位置没有元素,就直接将该元素放到数组的该位置上。**<br>
    需要注意Jdk 1.8中对HashMap的实现做了优化,当链表中的节点数据超过八个之后,该链表会转为红黑树来提高查询效率,从原来的O(n)到O(logn)
***

### HashSet
* 实现原理
    * HashSet底层由HashMap实现，HashSet的值存放于HashMap的Key上，HashMap的**value统一为PRESENT**
* 特性
    * 它存储**唯一**元素并**允许空值**
    * 它由HashMap支持
    * 它**不保持插入顺序**
    * 它**不是线程安全**的
* API
    * add():可用于**将元素添加到一组**。方法声明只有当元素尚未存在于集合中时才会添加元素。如果成功添加了元素，则该方法返回true，否则返回false。
    * contains():目的是**检查给定HashSet中是否存在元素**。如果找到该元素，则返回true，否则返回false。
    * remove():如果存在，该方法将从集合中**删除指定的元素**。如果集合包含指定的元素，则此方法返回true。
    * clear():当我们打算从集合中**删除所有项目时**，我们使用此方法。底层实现只是清除底层HashMap中的所有元素。
    *  size():这是API中的基本方法之一。它被大量使用，因为它有助于识别HashSet中存在的元素数量。底层实现只是将计算委托给HashMap的size（）方法。
    *  isEmpty():我们可以使用此方法来确定**HashSet的给定实例是否为空**。如果集合不包含任何元素，则此方法返回true
    * Iterator():该方法返回Set中元素的**迭代器**。这些元素**没有特定的顺序**访问，Iterator是fail-fast的。
***


### 数组(Array)和列表(ArrayList)的区别
* **Array可以包含基本类型或者对象类型，Arraylist只能包含对象类型；**
* Array大小是固定对的，ArrayList不是。
* 对于基本数据类型，Array使用自动装箱来减少编码工作量，但当处理固定大小的基本上数据类型时这种方式较慢。
***

### ArrayList、LinkedList和Vector
* ArrayList和LinkedList最明显的区别是ArrayList的底层数据结构是**数组，支持随机访问，因此插入慢，读取快**。而LinkedList的底层数据结构是**双向循环链表，不支持随机访问,因此写入块，读取慢**，Array的时间复杂度是O(1),LinkedList是O(n);
    
    >随机访问只是读取时索引顺序每次有差别，但是可以保证遍历出来的结果顺序是一样的
* Vector也是数组方式存储数据，和ArrayList一样数组元素数大于实际存储的数据，不同的是**Vector支持线程安全**，所以性能比ArrayList弱一些。
***

### [如何实现数组和List之间的转换 ](https://blog.csdn.net/qq_41097354/article/details/90403953)
* List转为数组：调用list的**toArray()**方法
* 数组转为List：调用Arrays的**asList**方法    
* [List合并](https://www.cnblogs.com/Luouy/p/6482733.html)
***

### 迭代器Iterator
* 什么是Iterator
    * 迭代器是一种设计模式，它是一个对象，它可以**遍历并选择序列中的对象**，而开发人员不需要了解该序列的底层结构。迭代器通常被称为“**轻量级**”对象，因为创建它的代价小。
* 如何使用？有何特点？
    * Java中的Iterator功能比较简单，并且**只能单向**
    * *lk--9移动**：
    * (1) 使用方法iterator()要求容器返回一个Iterator。**第一次调用Iterator的next()方法时，它返回序列的第一个元素**。注意：iterator()方法是java.lang.Iterable接口,被Collection继承。
    * (2) **使用next()获得序列中的下一个元素。**
    * (3) **使用hasNext()检查序列中是否还有元素。**
    * (4) 使用remove()将迭代器**新返回的元素删除**。　
* Iterator和ListIterator的区别
    * Iterator可用来**遍历Set和List集合**，但是**ListIterator只能用来遍历List**
    * Iterator对集合只能是向前遍历，ListIterator**既可以前向也可以后向。**
* ListIterator实现了Iterator接口，**并包含其他的功能**，比如：**增加元素**，**替换元素**，**获取前一个和后一个元素的索引**，等等。

````java
        ArrayList<Student> arrays= new ArrayList<>();
        arrays.add(new Student("wang",21));
        arrays.add(new Student("li",22));
        Iterator<Student> iterator=arrays.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
````

    
### Iterator和Enumeration 
* Iterator除了能读取集合的数据之外，也能数据进行删除操作；而**Enumeration只能读取集合的数据，而不能对数据进行修改**。
* Iterator是基于Enumeration实现的，同时Iterator支持fail-fast机制，所以**Iterator遍历集合时会比Enumeration遍历集合慢一些**。

***

>参考：https://blog.csdn.net/fangchao2011/article/details/89184615




