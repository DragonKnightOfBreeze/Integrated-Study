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
* 注意`var`并不是真正意义上的关键字，对应的变量名仍然可为`var`。
* var关键字可用于lambda表达式的参数上。也可以显示声明lambda表达式参数的类型。
* 当lambda参数需要添加注解时，以上语法非常有用。

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

## 改进的文件API

* `inputStream.transferTo`：可以将数据直接传输到`OutputStream`。

## HttpClient API

略。

## 移除的内容

移除项：
* 移除了`com.sun.awt.AWTUtilities`。
* 移除了`sum.misc.Unsafe.defineClass`，使用`java.lang.invoke.MethodHandles.Lookup.defineClass`替代。
* 移除了`Thread.destroy()`和`Thread.stop(Throwable)`方法。
* 移除了`sum.nio.ch.disableSystemWideOverlappingFileLookCheck`和`sum.locale.formatasdefault`属性。
* 移除了`jdk.snmp`模块
* 移除了javafx。需要额外添加依赖。
* 移除了Java Mission Control，需要自己单独下载。
* ……

废弃项：
* `-XX+AggressiveOpts`选项
* `-XX:+UnlockCommercialFeatures`选项
* `-XX:+LogCommercialFeatures`选项

## 更简化的编译运行程序

JEP330：增强java启动类支持运行单个java源代码文件的程序。

注意：
* 执行源文件中的第一个类的静态main方法。
* 不可以使用其他文件中的自定义类。

命令：`java HelloJava.java`

## Unicode 10

略

## 移除CORBA模块

已废弃的API：
* `java.xml.ws`
* `java.xml.bind`
* `java.xml.ws.annotation`
* `jdk.xml.bind`
* `jdk.xml.ws`
* `java.corba`
* `java.se.ee`
* `java.activation`
* `java.transaction`
* 新增`java.transaction.xa`

## JEP335：废弃Nashorn Javascript引擎

可以考虑使用GraalJVM代替。

## 新的Epsilon垃圾收集器

一个处理内存分配但不实现任何实际内存回收机制的GC，一旦可用堆内存用完，JVM就会退出。

如果有`System.gc()`的调用，实际上什么都不会发生（和`-XX:+DisableExplicitGC`效果一样）。因为没有内存回收，这个实现可能会警告用户尝试强制CG是徒劳。

用法：添加JVM参数`-XX:+UnlockExperimentalVMOptions`和`-XX:+UseEpsilonGC`。

## 新的ZGC垃圾收集器

可伸缩的、低延迟的垃圾收集器。同时也是并发的。
