# 特性概述

* JShell
* Dynamic Class-File Constants类文件新增的一种结构
* 局部变量类型推断（var关键字）
* 新加的一些更实用的API
* 移除的一些其他内容
* 标准的Java异步Http客户端
* 更简化的编译运行
* Unicode10支持
* 移除JavaEE Corba模块
* JEP335：废弃Nashorn Javascript引擎
* JEP336：移除Pack Tools和相关API 
e* 新的Epsilon垃圾回收器
* 新的ZGC垃圾回收器

# Java11的新特性

## JSHell

可以通过JShell方便、快捷地执行java语句。

jshell中合法的java语句语法与通常的语法有一些不同。

## Dynamic Class-File

略。

## 局部变量类型推断（var关键字）

* 当可以推断出局部变量的类型时，变量声明中的类型可以用`var`关键字推断。
* 注意`var`并不是真正意义上的关键字，对应的变量名仍然刻为`var`。
* var关键字可用于lambda表达式的参数上。也可以显示声明lambda表达式参数的类型。
* 当lambda参数需要添加注解视，以上语法非常有用。

## 新的字符串处理方法。

* `str.isBlank()`
* `str.strip()` 
* `str.stripTrailing()`
* `str.stripLeading()`
* `str.repeat(n)`
* `str.lines()`

注意：
* `str.trim()`中的空白字符指码点小于等于"U+0020"的特殊字符。
* `str.strip()`中的空白字符包括英文和其他所有语言中的空白，例如全角空白。

