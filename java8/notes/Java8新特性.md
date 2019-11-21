# 概述

## Java8新特性简介

* 速度更快
* 代码量更少
* 强大的Stream API
* 便于并行
* 最大化减少空指针异常

HashMap使用红黑树。  
数组-链表-红黑树。  
当碰撞的元素个数大于8，或者哈希表总大小大于64，则由数组链表转变为红黑树。除了添加元素之外，其他操作的效率都会变高。

ConcurrentHashMap
由锁分段转变为CAS无锁算法。效率提高了。

Jvm的种类：
* Oracle Sun：Hotspot
* Oracle：JRocket
* IBM：J9 JVM
* Alibaba：Taobao JVM

在jdk8之后，堆栈中就没有PremGen（永久区）了，取而代之的是MetaSpace（元空间），使用物理内存（RAM）。因此垃圾回收频率变低了。

一些JVM参数：
* `MetaSpaceSize`
* `MaxMetaSpaceSize`

# Lambda表达式

## 为什么使用Lambda表达式

lambda是一个匿名函数，我们可以把lambda表达式理解成一段可以传递的代码（将代码像数据一样进行传递）。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升。可以使用函数式编程。

* 语法：
    * `() -> ...` 当无参数时需要用空括号表示。
    * `a -> ...` 当方法体仅有一行时可以省略花括号。
    * `a -> { ... }` 当方法体为多行时需要用花括号包围。
    * `(a, b) -> { ... }` 当参数有多个时需要用括号包围。
    * `(int a, int b) -> { ... }` 参数类型可以省略不写。
* 口诀
    * 左右遇一括号省，左侧推断类型省。能省则省。

lambda表达式需要函数式接口的支持。
* 函数式接口必须仅包含一个抽象方法。
* 带有`@FunctionalInterface`注解的接口必须是函数式接口。
* 不带有此注解的接口，如果满足条件，仍然可用lambda表示。

# 函数式接口

* 当接口仅包含一个抽象方法时，可以使用lambda表达式表示它的一个匿名实现。
* 接口的默认方法、静态方法不在考虑范围之内。
* 继承自`Object`的`equals`、`hashcode`、`toString`方法不在考虑范围之内。

* `Consumer<T>`：等价于`(T) -> Unit`，抽象方法是`void accept(T t)`
* `Supplier<T>`：等价于`() -> T`，抽象方法是`T get()`
* `Function<T, R>`：等价于 `(T) -> R`，抽象方法是`R apply(T t)`
* `Predicate<T>`：等价于 `(T) -> Boolean`，抽象方法是`boolean test(T t)`

# 方法引用与构造器引用

* 若lambda体中的内容由方法已经实现了，我们就可以使用方法引用。
* 可以理解方法应用是lambda表达式的另外一种表现形式。
***
* 当lambda表达式的方法体仅包含一个匹配参数和返回值的方法时，可以将其替换成该方法的引用。
* 对于对象的实例方法，适用方法引用。
* 可以将首个参数前移作为接收者。然后使用类的实例方法引用。
* 对于类的静态方法，适用方法引用。
* 对于类的构造方法，同样适用方法引用。需要调用的构造方法的参数需要与lambda的参数匹配。
* 对于数组的构造方法，同样适用方法引用。
* 语法：
    * `someInstance::someMethod`
    * `SomeClass:someMethod`
    * `SomeClass::someStaticMethod`
    * `SomeClass::new`
    * `SomeClass[]::new`

# Stream API

## 基本介绍

* Stream是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。
* 集合关注数据，流关注计算。
* Stream自身不会存储元素。
* Stream不会改变源对象，而是返回一个持有结果的新Stream。
* Stream操作是延迟执行的，即，会等到需要结果时才执行。

创建Stream：
* `Arrays.stream(array)`
* `list.stream()`
* `set.stream()`
* `map.entrySet().stream()`
* `Stream.of(vararg)`
* `Stream.iterate(seed,biOperator)`
* `Stream.generate(supplier)`
* `stream()`用于创建串行流，`parallelStream()`用于创建并行流。

Stream的多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何处理。在终止操作时一次性全部处理，称为“惰性求值。”。

Stream高阶函数：
* `Stream.foreach(function)` 遍历操作（没有返回值）
* `Stream.peek(function)` 遍历操作（返回当前流自身）
* `Stream.distinct()` 涮选操作
* `Stream.limit(n)` 截断元素操作，与skip操作互补
* `Stream.skip(n)` 跳过元素操作，与limit操作互补
* `Stream.map(transform)` 转换操作（lambda返回转化后的元素）
* `Stream.flatMap(flatTransform)` 平滑转换操作（lambda返回转化后的元素的流）
* `Stream.filter(predicate)` 过滤操作
* `Stream.sorted(comparator)` 排序操作

Stream用于查找与匹配的高阶函数
* `Stream.allMatch` 检查是否匹配所有元素。
* `Stream.anyMatch` 检查是否至少匹配一个元素。
* `Stream.noneMatch` 检查是否没有匹配所有元素。
* `Stream.findFirst` 返回第一个元素。
* `Stream.findAny` 返回当前流中的任意随机元素。
* `Stream.count` 返回流中元素的个数。
* `max` 返回流中的最大值。
* `min` 返回流中的最小值。

归约操作
* `Stream.reduce(accumulator)` 归约操作（累积流中的元素）
* map和reduce的连接通常被称为map-reduce，因google用它来进行网络搜索而出名。

收集操作
* `Stream.collect(...)` 收集操作（将Stream转化为List、Set等）
* `Stream.collect(Collectors.toList())` 收集为列表
* `Stream.collect(Collectors.minBy(...))` 计算最小值
* `Stream.collect(Collectors.maxBy(...))` 计算最大值
* `Stream.collect(Collectors.averagingInt()` 计算平均值
* `Stream.collect(Collectors.groupingBy(...))` 分组操作（允许多级分组）
* `Stream.collect(Collectors.partitioningBy(...))` 分区操作
* `Stream.collect(Collectors.summarizingBy(...))` 汇总操作（包含最大值、最小值、平均值等）
* `Stream.collect(Collectors.joining(...))` 加入操作（如拼接字符串）

## 并行流与顺序流

并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。

Java8中将并行进行了优化，我们可以很容易地对数据进行并行操作。Stream API可以声明性地通过`parallel()`和`sequential()`在并行流和串行流之间切换。

## Fork/Join框架

Fork/Join框架，就是在必要的情况下，将一个大任务，进行拆分成若干个小任务（直到不可再拆时），再将一个个小任务运算的结果进行join汇总。

For/Join框架与传统线程池的区别：
工作窃取模式：当执行新的任务时可以拆分成更小的任务执行，并将其加入到线程队列的首部。如果没有任务了，再从一个随机线程的队列尾部偷取一个，并放到自己的队列的首部。

实现Fork/Join任务需要继承的两个类：
* `RecursiveAction`
* `RecursiveTask<V>`

使用并行流，代码更简洁。

# Optional容器

Optional类是一个容器类，代表一个值存在或者不存在。可用于避免空指针异常。

常用方法：
* `Optional.of(value)` 创建一个Optional实例
* `Optional.empty()` 创建一个空的Optional实例。
* `Optional.ofNullable(value)` 如果不为null，则创建Optional实例，否则创建空实例。
* `isPresent()` 判断是否有值。
* `orElse(value)` 如果有值，则返回该值，否则返回指定的另一个值。
* `orElseGet(supplier)` 如果有值，则返回该值，否则返回指定的另一个值。
* `orElseThrow(exception)` 如果有值，则返回该值，否则抛出指定的异常实例。
* `map(transform)` 如果有值，则进行转换操作，否则返回空实例。
* `flatMap(transform)` 要求返回的值也是Optional实例。

# 接口中的默认方法和静态方法

……

# 新时间日期API

原始的时间日期API：
* 这些API都不是线程安全的。
* `Date` 基本的日期类。
* `Calendar` 日历类，可以进行一些计算。

Java8的时间日期API：
* 这些API都是线程安全的，也即不可变的。不包含与相关的信息。
* `LocalDate` 本地日期。
* `LocalDateTime` 本地日期时间。
* `LocalTime` 本地时间。
* `Duration` 时长。
    * 计算两个时间之间的间隔。
* `Period` 时期。
    * 计算两个日期之间的间隔。
* `Instant` 时间戳。
    * 以Unit元年，即1970.1.1 00:00:00开始，到某个时间之间的毫秒值。
    * 默认获取UTC时区的时间。



# 其他新特性

