---
title: Java获取文件信息
categories: Code
tags:
  - 工具类
declare: true
wordCount: true
abbrlink: af03e16b
date: 2020-05-18 20:19:10
---

### readAttributes方法获取文件属性
```java

    Path path = Paths.get("C:/Users/LetGo/Desktop/", "16.png");
    SimpleDateFormat format=new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    BasicFileAttributes a= Files.readAttributes(path, BasicFileAttributes.class);
    System.out.println(a.size());
    System.out.println( "创建日期"+format.format(new Date(a.creationTime().toMillis())) );
    System.out.println("最后访问时间:"+format.format(new Date(a.lastAccessTime().toMillis())));
    System.out.println("最后修改时间:"+format.format(new Date(a.lastModifiedTime().toMillis())));

    System.out.println("是否为目录"+a.isDirectory());
    System.out.println("是否为其他"+a.isOther());
    System.out.println("是否为常规文件"+a.isRegularFile());
    System.out.println("是否为连接"+a.isSymbolicLink());

```


### 获取文件大小
```java
public class FileSizeUtil {

    public static String getPrintSize(File  file) {
        long size=file.length();
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            // 因为如果以MB为单位的话，要保留最后1位小数，
            // 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }
}


```

参考
> https://blog.csdn.net/lichongxyz/article/details/88925249
> https://www.tah1986.com/2298.html