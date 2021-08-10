---
title: BigDecimal
categories: Code
tags: JavaSE
declare: true
wordCount: true
abbrlink: 5b0774c7
date: 2020-05-02 23:53:10
---

​&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Java在java.math包中提供的API类BigDecimal，用来**对超过16位有效位的数**进行精确的运算。双精度浮点型变量double可以处理16位有效数，但在实际应用中，可能需要对更大或者更小的数进行运算和处理。一般情况下，对于那些不需要准确计算精度的数字，我们可以直接使用Float和Double处理，但是Double.valueOf(String) 和Float.valueOf(String)会丢失精度。所以开发中，如果我们需要精确计算的结果，则必须使用BigDecimal类来操作。
<!-- more -->
​ ​&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**BigDecimal所创建的是对象**，故我们不能使用传统的+、-、*、/等算术运算符直接对其对象进行数学运算，而必须调用其相对应的方法。方法中的参数也必须是BigDecimal的对象。构造器是类的特殊方法，专门用来创建对象，特别是带有参数的对象。

### 1.构造方法

 ```java
    new BigDecimal(int)

    new BigDecimal(double)

    new BigDecimal(long)

    new BigDecimal(String)
```

> **尽量使用参数类型为String的构造函数**。因为参数类型为double的构造方法的结果有一定的不可预知性，因为`BigDecimal a =new BigDecimal(0.1)`,a可能等0.1000000000000000055511151231257827021181583

### 2.BigDecimal常用方法

#### 2.1 加法
```java
    new BigDecimal(0.01).add(new BigDecimal(0.02));
```
#### 2.2 减法
```java
    new BigDecimal(0.01).subtract(new BigDecimal(0.02));
```
#### 2.3 乘法
```java
    new BigDecimal(0.01).multiply(new BigDecimal(0.02));
```
#### 2.4 除法
```java
    //除数、保留小数位数、舍入模式
    new BigDecimal(0.01).divide(new BigDecimal(0.02), 5, ROUND_HALF_UP); 
```
* 舍入模式    
    1. **ROUND_UP**
        **舍入远离零**的舍入模式。
        在丢弃非零部分之前始终增加数字(始终对非零舍弃部分前面的数字加1)。
        注意：此舍入模式始终不会减少计算值的大小。 
    2. **ROUND_DOWN**
        **接近零**的舍入模式。
        在丢弃某部分之前始终不增加数字(从不对舍弃部分前面的数字加1，即截短)。
        注意：此舍入模式始终不会增加计算值的大小。
    3. **ROUND_CEILING**
        **接近正无穷大**的舍入模式。
        如果 BigDecimal 为正，则舍入行为与 ROUND_UP 相同;
        如果为负，则舍入行为与 ROUND_DOWN 相同。
        注意，此舍入模式始终不会减少计算值。
    4.**ROUND_FLOOR**
        **接近负无穷大**的舍入模式。
        如果 BigDecimal 为正，则舍入行为与 ROUND_DOWN 相同;
        如果为负，则舍入行为与 ROUND_UP 相同。
        注意，此舍入模式始终不会增加计算值。
    5.**ROUND_HALF_UP**
        **向“最接近的”数字舍入**，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
        如果舍弃部分 >= 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同。
        注意，这是我们大多数人在小学时就学过的舍入模式(四舍五入)。
    6.**ROUND_HALF_DOWN**
        **向“最接近的”数字舍入**，如果与两个相邻数字的距离相等，则为上舍入的舍入模式。
        如果舍弃部分 > 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同(五舍六入)。
    7.**ROUND_HALF_EVEN**
        **向“最接近的”数字舍入**，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
        如果舍弃部分左边的数字为奇数，则舍入行为与 ROUND_HALF_UP 相同;
        如果为偶数，则舍入行为与 ROUND_HALF_DOWN 相同。
        注意，在重复进行一系列计算时，此舍入模式可以将累加错误减到最小。
        此舍入模式也称为“银行家舍入法”，主要在美国使用。四舍六入，五分两种情况。
        如果前一位为奇数，则入位，否则舍去。
        以下例子为保留小数点1位，那么这种舍入方式下的结果。
        1.15>1.2 1.25>1.2
    8.**ROUND_UNNECESSARY**
        断言请求的操作具有精确的结果，因此不需要舍入。
        如果对获得精确结果的操作指定此舍入模式，则抛出ArithmeticException。 

#### 2.5 转换为字符串
```java
    new BigDecimal(0.01).toString();
```
#### 2.6 转换成双精度数
```java
    new BigDecimal(0.01).doubleValue();
```
#### 2.7 转换为单精度数
```java
    new BigDecimal(0.01).floatValue();
```
#### 2.8 转换为长整数
```java
    new BigDecimal(0.01).longValue();
```
#### 2.9 转换为整型
```java
    new BigDecimal(0.01).intValue();
```

### 3.BigDecimal比较大小
```java
    BigDecimal a=new BigDecimal(1);
    BigDecimal b=new BigDecimal(2);
    int c=a.compareTo(b);
```
```
 c =  0 ;  a = b
 c = -1 ;  a < b
 c =  1 ;  a > b
```
<br>

```
c > -1 ;  a >= b
c <  1 ;  a <= b
```

### 4.利用BigDecimal格式化参数

    保留两位小数

```java
    public class BigDecimalFormat {

        private static String formatToNumber(BigDecimal obj) {
            DecimalFormat format = new DecimalFormat("#.00");
            if (obj.compareTo(BigDecimal.ZERO) == 0) {
                return "0.00";
            } else if (obj.compareTo(BigDecimal.ZERO) > 0 && obj.compareTo(new BigDecimal(1)) < 0) {
                return "0" + format.format(obj).toString();
            } else {
                return format.format(obj).toString();
            }
        }

        public static void main(String[] s) {
            System.out.println(formatToNumber(new BigDecimal("3.435")));
            System.out.println(formatToNumber(new BigDecimal(0)));
            System.out.println(formatToNumber(new BigDecimal("0.00")));
            System.out.println(formatToNumber(new BigDecimal("0.001")));
            System.out.println(formatToNumber(new BigDecimal("0.012412536")));
        }
    }
```





        
>参考：https://www.cnblogs.com/zhangyinhua/p/11545305.html