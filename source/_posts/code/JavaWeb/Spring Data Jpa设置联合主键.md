---
title: Spring Data Jpa设置联合主键
categories: Code
tags:
  - JavaWeb
  - ORM
declare: true
wordCount: true
abbrlink: b988190
data: 2020-04-27 23:20:10
---
对于三种比较常见的SpringBoot持久层框架中，作者最喜欢的Spring Data Jpa,因为相比于Hibernate和MyBatis，它更符合ORM的理念，更多的使用注解来实现各种持久化操作，简单易上手，而且也不需要写很多的相关文件，有点Spring和SpringBoot之间那么点意思，当然对于一些复杂CAUD场景可能会有不太方便的地方，但是对于需要快速开发的小项目时，它显得极为合适。<br>
言归正传，前几天在快速搭建的一个日志分析系统，最后对数据表进行重构的时候，需要设置联合主键，遇到了一些问题，最后发现**并只不是在实体类的属性中添加多个`@Id`**,所以在此记下：
<!-- more -->

## 两种实现方法

### 1.@IdClass + @Id

#### 1.1联合主键类
* 首先依照以下规则写一个**包含实体类所有组成联合主键的字段**的联合主键类，
    * 继承Serializable接口进行序列化
    * 重写hashCode()和equals()方法
    * 有一个无参构造函数
        ```java
        /**
        * Description 联合主键类
        * Author Cloudr 
        * Date 2020/4/26 22:50
        **/
        public class PhoneMultiKeys implements Serializable {

            private String band;
            private String series;
            private int size;

            public PhoneMultiKeys() {
            }

            public PhoneMultiKeys(String band, String series, int size) {
                this.band = band;
                this.series = series;
                this.size = size;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getBand() {
                return band;
            }

            public void setBand(String band) {
                this.band = band;
            }

            public String getSeries() {
                return series;
            }

            public void setSeries(String series) {
                this.series = series;
            }

            @Override
            public int hashCode(){
                int code=17;
                code=code*31+(band!=null?band.hashCode():0);
                code=code*31+(series!=null?series.hashCode():0);
                code=code*31+(int)size;
                return code;
            }

            @Override
            public boolean equals(Object object){
                if(object==null)
                    return false;
                if (!(object instanceof PhoneMultiKeys))
                    return false;
                PhoneMultiKeys obj=(PhoneMultiKeys)object;
                if(obj.band.equals(band)&&obj.series.equals(series))
                    return true;
                return false;
            }
        }
        ```

#### 1.2实体类
* 如常编写实体类
    * 注意使用@IdClass,参数为对应的联合主键类
    * @Id注解正常使用
    * 重写equals()和hashCode()
        ```java
        /**
        * Description 实体类
        * Author Cloudr 
        * Date 2020/4/26 22:49
        **/
        @Entity
        @Table(name = "phoneTestFroMultiKeys")
        @IdClass(PhoneMultiKeys.class)
        public class Phone implements Serializable {
            @Id
            private String band;
            @Id
            private String series;
            @Id
            private int size;
            private String model;

            public Phone() {
            }

            public Phone(String band, String series, String model,int size) {
                this.band = band;
                this.series = series;
                this.model = model;
                this.size=size;
            }

            public String getBand() {
                return band;
            }

            public void setBand(String band) {
                this.band = band;
            }

            public String getSeries() {
                return series;
            }

            public void setSeries(String series) {
                this.series = series;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            @Override
            public String toString() {
                return "Phone{" +
                        "band='" + band + '\'' +
                        ", series='" + series + '\'' +
                        ", size=" + size +
                        ", model='" + model + '\'' +
                        '}';
            }
        }
     
        ```

#### 1.3测试
* Dao层
    * JpaRepository<T,TD> //TD类型测试过String、Integer、以及无关的Date和Object都可以，所以任何类型都可以？
        ```java
        public interface PhoneDao extends JpaRepository<Phone, Object> { 

            public Optional<Phone> findByBandAndSeriesAndSize(String band, String series, int size);

        }
        ```
* Service层
    ```java
    @Service
    public interface PhoneService {

        void saveALl(List<Phone> phoneList);

    }

    @Service
    public class PhoneServiceImpl implements PhoneService {

        @Resource
        private PhoneDao phoneDao;

        @Override
        public void saveALl(List<Phone> phoneList) {
            phoneDao.saveAll(phoneList);
        }

        public Optional<Phone> findByBandAndSeriesAndSize(String mi, String mix, int i) {
            return phoneDao.findByBandAndSeriesAndSize(mi,mix,i);
        }
    }
    ```
* 测试
    ```java
        //SUCCEED
        @Test
        void TestSaveList() {
            Book book1 = new Book("cc1", "aa");
            Book book2 = new Book("cc2", "aa");
            List<Book> list = new ArrayList<>();
            list.add(book1);
            list.add(book2);
            bookService.saveAll(list);
        }
    ```

### 2.@Embeddable + @EmbedId 

#### 2.1 联合主键类

* 加入@Embeddable
* 序列化
* 无参构造函数
    > `UserPK`是一种命名风格并不是协议
    ```java
    @Embeddable
    public class UserPK implements Serializable  {
        private String userId;
        private String userName;

        public UserPK() {
        }

        public UserPK(String userId, String userName) {
            this.userId = userId;
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserPK userPK = (UserPK) o;
            return userId.equals(userPK.userId) &&
                    userName.equals(userPK.userName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, userName);
        }
    }
    ```
#### 2.2 实体类
* 使用@EmbeddedId标注Id
* Id类型为UserPK
    ```java
    @Entity
    @Table(name = "user_test")
    public class User implements Serializable {
        @EmbeddedId
        private UserPK id;
        private String nickName;

        public User() {
        }

        public User(UserPK id, String nickName) {
            this.id = id;
            this.nickName = nickName;
        }

        public UserPK getId() {
            return id;
        }

        public void setId(UserPK id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
    ```
* Dao层
    * JpaRepository<T, TD> //TD类型为联合主键类
        ```java
        public interface UserRepository extends JpaRepository<User, UserPK> {
        
        }
        ```
#### 2.3 测试
```java
    @Resource
    private UserRepository userDao;
    @Test
    void TestUserWithMultiKeys() {
        User user = new User(new UserPK("123", "Lou"), "cc");
        userDao.save(user);
        logger.info("存储成功");
    }
```




## 总结
* 作者猜测Jpa之所以不能只在实体类中添加多个@Id实现联合主键是因为可能只靠注释可能在一些情况下不够准确，也可能底层没有做的特别完善，期待Jpa后续的更新。而在联合主键类中必须重写equals()和hashCode()以及都必须实现序列化都已在一定程度上印证了这一点。当然这些都是猜测，期望和各位的交流。

* 关于以上两种方法，`@Embeddable + @EmbedId`更符合面向对象的设计思想，将联合主键直接封装成一个类，`@IdClass + @Id`更直观，目前作者还不知道其中的具体差异。





