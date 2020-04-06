# 特性概述

* 模块化系统
* JShell命令
* 多版本兼容jar包
* 接口的私有方法
* 钻石操作符的使用升级
* 语法改进：try语句
* 下划线使用限制
* String存储结构改变
* 便利的集合特性：of()
* 增强的Stream Api
* 多分辨图像API
* 全新的http客户端API
* Deprecated的相关API
* 智能Java编译工具
* 统一的JVM日志系统
* javadoc的HTML5支持
* javascript的引擎升级
* java的动态编译器

# Java9的新特性

## jep和jsr

* jep(jdk enhancement proposals)：jdk改进提案，每当需要有新的设想的时候，jep可以在jcp(java community process)之前或者同事提出非正式的规范(specification)，被正式认可的jep可以正式写进jdk的发展路径图并分配版本号。
* jsr(java specification requests)：java规范提案，新特性的规范出现在这一阶段，是指向jcp(java community process)提出新增一个标准化技术规范的正式请求。请求可以来自于小组/项目、jep、jcp成员或者java社区成员的甜。每个java版本都由相应的jsr支持。

## jdk与jre的改变

* jdk(java development kit)
* jre(java runtime environment)

说明：
* jdk = jre + 开发工具集（例如javac）
* jre = jvm + java se标准类库

## 模块化系统 jigsaw -> modularity

`module-info.java`文件

```
module myModule {
    //导入模块的包
    //requires com.sample;
    //导出模块的包
    exports com.sample;
} 
```

## Java的REPL工具：jshell命令

* REPL(read-evaluate-print-loop)：交互式编程环境。
* 设计理念：即写即得，快速运行

备注
* jshell中，默认导入的包是不同的。
* jshell中，语句最后可以省略分号。
* jshell中，可以直接定义顶层方法。
* jshell中，重复创建方法将会修改之前的方法。
* jshell中，将会忽略受捡异常。
* 可以通过`edit xxx`命令从临时文件修改当前代码。
* 可以通过`open xxx`命令读取临时文件并运行代码。

## 多版本兼容jar包

## 语法改进：接口的私有方法（非抽象）

接口中现在允许声明：
* 静态全局常量。
* 抽象方法。
* 默认方法。
* 静态方法。
* 私有方法（需要实现）。

面试题：抽象类与接口的不同
* 二者声明的方式不同。
* 二者内部的结构不同。
    * jdk7：抽象类可包括变量、常量、实例方法、抽象方法和静态方法等；接口只能包括抽象方法。
    * jdk8：接口还可以包括默认方法和静态方法。静态方法冲突时，实现类需要明确实现。
    * jdk9：接口还可以包括私有实例方法。
* 共同点：
    * 接口不能实例化。
* 不同点：
    * 抽象类只能单继承，接口可以多继承。
    
## 语法改进：钻石操作符的使用升级

* 明确泛型类型的变量，当实例化时，如果泛型类的泛型是相同的，则可以省略。

示例：

```
List<String> list = new ArrayList<>();
List<String> list = new ArrayList<>(){}; //创建一个匿名子类
```

## 语法改进：try语句

* try-with-resources：自动关闭资源
* java8：要求资源对象必须在`try()`中实例化。
* java9：可以在`try()`中调用已经实例化的资源对象。
* `try{}`中的资源对象是final的。
* 调用对个资源时，可以使用分号隔开。

示例：

```
var reader = new FileReader("test.txt")
try(reader){
    reader.read()    
}catch(IOException e){
    e.printStackTrace();
}
```

## 语法改进：下划线使用的限制

* 在java9中，不再可以单独使用`_`作为命名标识符。而是作为保留关键字。
* 在编程语言中，`_`一般用于未使用的解构变量和lambda参数。

## String低层存储结构的改变

* String、StringBuilder和StringBuffer的低层结构由`char[]`变为了`byte[]`。
* UTF8中一个字符可以是1~3个字节，而UTF16中一个字符是2个字节。
* 所以现在应该称之为“字节串”？

String、StringBuffer和StringBuilder的不同：
* String：
    * 低层结构在java8及之前是`char[]`，在java9及以后是`byte[]`。
    * 不可变的字符序列。
* StringBuffer：
    * 低层结构在java8及之前是`char[]`，在java9及以后是`byte[]`。
    * 可变的字符序列，线程安全，效率低。
* StringBuilder：
    * 低层结构在java8及之前是`char[]`，在java9及以后是`byte[]`。
    * 可变的字符序列，线程不安全，效率高（jdk5.0）。

## 快速创建只读集合

* `Arrays.asList(array)`：从数组创建只读列表。
* `List.of(vararg)`：创建只读列表。
* `Set.of(vararg)`：创建只读集。
* `Map.of(vararg)`：创建只读映射。
* `Map.ofEntries(vararg)`：创建只读映射。如：`Map.ofEntries(Map.entry("a",1))`。
* 注意：当对只读集合使用修改操作时会抛出`UnsupportedOperation`异常。
* 注意：这些工厂方法的可变参数或可变参数数组不能为null。
* 注意：`Arrays.asList()`和`List.of()`方法返回的只读列表是不同的。
* 注意：`Set.of()`、`Map.of()`方法添加重复元素时会抛出异常。

## 增强的Stream Api

在java9中，Stream接口添加了4个新的方法：
* `dropWhile(predicate)`：按条件从流的开头去除元素。
* `takeWhile(predicate)`：按条件从流的开头接受数据。
* `Stream.ofNullable(vararg)`：允许存放空元素。
* `Stream.iterable(predicate)`：按条件控制迭代的终止。

## Optional的stream方法

* `Optional<T> -> Stream<T>`
* 一般配合`stream.flatMap`方法使用。

## 多分辨率图像API

* 在不同分辨率设备上，显示的图片大小是相同的。

## 全新的HTTP客户端API

java9中有新的方式来处理http调用。它提供了一个新的http客户端（HttpClient），将替代仅适用于blocking模式的HttpURLConnection，并且提供对WebSocket和HTTP2的支持。

此外，HTTP客户端还提供API来处理HTTP2的特性，比如流和服务器推送等。

全新的HTTP客户端API可以从jdk.incubator.httpclient模块中使用。默认情况下，这个模块时不能根据classpath获取的，需要使用add modules命令配置这个模块，将这个模块添加到classpath。

从java11开始，HttpClient在`java.net.http`包下。

## 废弃的API

* 废弃了Applet API。主流浏览器已经取消了对java浏览器插件的支持。
* 废弃了applet viewer API。

## 智能的javac工具

智能java编译工具（sjavac），用于在多核处理器情况下提升jdk的编译速度。

## 统一的JVM日志系统

日志是解决问题的唯一有效途径；曾经很难知道jvm性能问题和导致jvm崩溃的根本原因。不同的JVM日志的碎片化和日志选项（例如：JVM组件对于日志使用的是不同的机制和规则），这使得jvm难以进行调试。

解决该问题的最佳方法：对所有JVM组件引入一个单一的系统，这些JVM组件支持细粒度的和易配置的JVM日志。

## javadoc的HTML5支持

javadoc的输出现在符合支持html5格式。

## javascript引擎升级：nashorn

* 通过java.scripting引擎，在java代码中动态调用javascript代码。
* 在java11中，nashore引擎已经废弃了。

## java动态编译器

jit（just in time）编译器可以在运行时将热点编译成本地代码，速度更快。

# 总结

## 在java9中看不到什么？

### 一个标准化和轻量级的json api

可以考虑使用`Gson`或者`Jackson`。

### 新的货币API

JSR354定义了一套新的Java货币API：JavaMoney。

可以考虑使用`org.javamoney:moneta`。
