---
title: Spring Data JPA分页查询
categories: Code
tags:
  - JavaWeb
  - ORM
  - JPA
declare: true
wordCount: true
abbrlink: 2d8953d4
date: 2020-05-22 12:10:10
---

### 控制层
```java
    @RequestMapping("/allUser")
    public ModelAndView listByPages(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        ModelAndView mav = new ModelAndView();
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum - 1, 4, sort); //页数、每页大小、排序方法
        Page<User> users = userService.findAll(pageable);
        mav.addObject("allUser", users);
        mav.setViewName("allUser");
        return mav;

    }
```
### 业务层
```java
    @Override
    public Page<User> findAll(Pageable pageable) {
        return userDao.findAll(pageable);
    }
```