# 技术面试注意事项
 
## 自我介绍

表达流程，不要太差。

## 基础（必须）

自己百度常见面试题（不含答案）  

坑：面试题的答案，一定不要背标准答案，一定要有自己的思想（哪怕是错的）

常见的面试题，一定要会
* ArrayList和HashMap的源码，实现原理
* 冒泡排序、选择排序
* 单例模式、工厂模式、动态工厂
* 谈谈你对面向对象的理解
* 事务的隔离级别
* Spring IOC/AOP

将面试题中的问题在博客、github上搜，不要在面试题中搜

坑：不要写“精通”，建议“掌握、熟练、理解、会使用”

技能列表

* 不要中规中矩：培训、看某套视频出来的
* 缺失个人独立思考能力
* 一般而言，简历上写到的技术，很可能被问到
* 2-3个非培训机构标配课程（只要掌握该技术的1-2个核心点即可）

项目
坑：电商、管理系统（缺少真实项目的感觉）
建议
* 提前准备好回答的台词
* 技术本身的不足，如何弥补？  
  * mysql、大型、海量：如果数据超过的mysql容量怎么处理？
  * 高并发项目、缓存：在哪些场景见过缓存失效？怎么解决？
  * mq解决耦合性：哪些真实的场景，用到解耦合？
* 项目的重难点
  * 分布式项目：如何共享数据？
  * 高并发：几级缓存，如何限流，如何熔断？
* 真实性：实际的使用场景
  * 多线程、设计模式、算法：处理什么业务？什么场景？
  * 大数据的项目？数据从哪来？
  * 如何设计表？外键？一对一？一对多如何？
  * 项目能否访问？
* 描述：技术+文字
* 项目周期：半年以上
* 项目个数：
  * 三年以内：2-3
  * 实习或毕业：1-2 

表达沟通能力

##  加分项

* 高并发，分布式（高并发的解决方案）
  * 多线程（juc，aqs，线程安全，锁机制，生产消费者）
  * 数据处理（sql优化，mysql+mycat+haproxy+keepalived）
* 实际的解决问题能力
  * 主动引用
  * 回答项目：遇到什么问题、如何发现、排查、分析、解决、总结
* 绝杀
  * acm竞赛、出版、蓝桥杯等全国性竞赛
  * github上发布过一个项目（并且点赞众多）、博客、公众号
  * 个人已发布项目（如阿里云）
  * 牛人的推荐信
  * 研究过JDK/Spring/MyBatis源码
  * 电子简历：链接，纸质简历：二维码

## 注意事项

* 体现数字：几个项目、几篇博客、排名第几
* 工资：不要面议，写范围
* 坑：技术点，宁可少些，也不要多写
* 简历：1-2页满业，不要书皮，格式pdf（word可能出现兼容问题），外观不要太绚丽
* 细节：毕业时间、年龄、工作履历、期望薪资

聊天时要注意人文素养
* 不要秀智商
* 建议：个人解决的能力、团队、沟通


# 技术面试指导 基础部分

背景
* 自己知识储备不足
* 准备OK，但是仍然没找到offer
* 不会脱颖而出

## 主题：出奇制胜

### 反对和所有应聘者千篇一律

* 如：使用`"abc".equals(str)`或`Objects.equals("abc","123")`
* 积累：阿里巴巴编程规范，《effective java》

### 反对和《面试宝典》千篇一律

* 建议：只看题目，答案自己写，或者github/知乎/博客
  
示例：线程通信
* Object类的wait、notify和notifyAll方法
* Semaphore类的acquire和release方法
* google guava的Monitor类
* +PipedInputStream和PipedOutputStream

### 源码级解决问题

ArrayList的扩容机制：
ArrayList的低层是动态数组，默认容量是10，如果已满，则自动扩容1.5倍。我还观看了源码进行验证，发现是在数组已满时扩容，并且是右通过位移运算符扩容。

String类的+操作符：
String类的+操作符，低层调用的是`StringBuffer`或`StringBuilder`的`append`方法。

## 找对时机，秀技能

每个人都有一些独到的经验，要想办法在面试的时候讲给面试官。

聊聊自己的项目经验：项目讲完的时候，加一句：在开发项目时遇到了一个bug，花费了很长时间才解决。

如：只读的列表

## 比较通用的秀点

优化类（jvm，sql优化）

jvm优化：项目做完时，用jmeter进行了压力测试，经过测试发现响应时间太慢，内存使用率太高（发现问题），用jvisualvm发现是项目中短对象太多了。解决方法：调整新生代内存大小以及逃逸到老年代的阈值。

sql优化（项目做完时，发现某个功能相应时间太慢，如何解决？）

定位慢sql：使用“慢查询日志/mysqldumslow”发现到底是哪个sql执行太慢。

优化慢sql：使用explain查看sql执行计划，发现有些索引失效了。（例如：like的第一个字符是`%`）

数据库设置隔离级别。读类型？写类型？

查询字段：`com_select`

算法类：字符串算法（kmp算法）

## 技术沉淀

数据支撑：github、博客、专栏、微信公众号、项目发布到阿里云

## 大厂的区别

大公司：数据结构和算法、操作系统、网络、设计模式、分布式、逻辑思维

## 心态建设

回答正确，但被面试官否定
* 答案来自面试宝典，千篇一律
* 面试官自己在秀技术，调整好心态

正常的面试官：引导你来回答，而不是想法设法吓唬你

有没有用过List?哪些子类？数组？动态扩容，不越界？

如果遇到自己不会的？一定要答出自己的想法。

新dao框架？映射文件/注解：实体类-表，然后通过框架本身的api进行crud


## MyBatis 

### 基础

MyBatis重要组件

* Mapper配置：实体类Student.java - 数据表Student，xml或注解，推荐xml
* Mapper接口：dao接口（只需要写接口，不用写实际类）

Mapper接口的约定

* 方法名和配置文件中的id必须相同，并且配置文件的namespace需要完全匹配
* 方法的输入参数必须和配置文件中的parameterType相同
* 方法的返回值必须和sql配置文件的resultType相同

特殊情况

* 如果不存在parameterType，则代表一个无参方法
* 如果不存在resultType，则代表返回值是void
* 如果方法的返回值是一个集合类型，则实际的resultType仍然是元素类型

```xml
<select id="queryStudentByNo" parameterType="int" resultType="demo.entity.Student">
  select * from student where stuNo=#{stuNo}
</select>
```

```java
public interface StudentMapper {
	Student queryStudentByNo(int stuNo);
}
```

### MyBatis开发时的常用对象（调用过程）

* SqlSessionFactory：SqlSession工厂
  * 通过`openSession()`产生SqlSession对象
* SqlSession：类似于jdbc中的Connection
* Executor：执行MyBatis低层所有Mapper语句

### MyBatis四大核心对象（执行过程）

* StatementHandler：sql语句的处理对象
* ParameterHandler：处理sql中的参数对象
* Executor：执行sql
* ResultSetHandler：处理sql中的返回值对象

### MyBatis四大处理器

* StatementHandler
* ParameterHandler
* ResultSetHandler
* TypeHandler（如：处理字符串和数字类型的转换）

### MyBatis的执行流程

加载配置文件（mybatisConfig.xml，StudentMapper.xml）
→创建会话工厂（SqlSessionFactory）
→创建会话（SqlSession）
→创建执行器（Executor）
→封装sql对象（转化为MappedStatement）
→访问数据库

### 一对一、一对多、延迟加载

一对一

* 使用resultMap标签实现一对一关系
* 使用resultMap标签中的association标签的select属性指定延迟加载的sql语句

```xml
<resultMap>
  <association select="延迟加载的sql语句"/>
</resultMap>
```

一对多
将一对一中的association标签改为collection标签

## Spring

### IOC：控制反转/依赖注入

* 目的：解耦合（接触对象的业务逻辑和对象的创建逻辑的耦合）
* 总结：IOC可以让我们通过配置的方式来创建对象

### AOP：面向切面编程

OOP的补充，不是替代。

* JoinPoint（连接点）：方法可以被增强的位置。
* Pointcut（切入点）：方法实际被增强的位置。
* Advice（通知）：增强功能的逻辑。
    * before后置通知：在方法之前执行
    * after 前置通知：在方法之后执行
    * around 环绕通知：在方法之前和之后执行
    * after-throwing 异常通知：方法出现异常后执行
    * after-returning 最终通知：在后置通知之后执行
* Aspect（切面）：切入点和通知的结合。一般为一个类。  
* Introduction（引介）：一种特殊的通知，在不修改类代码的前提下，可以在运行期间为类动态地添加一些方法或字段。
* Target（目标对象）：代理的目标对象，增强方法所在的类。
* Weaving（织入）：把增强应用到目标对象，来创建新的代理对象的过程。
  * Spring采用动态代理织入。
  * AspectJ采用编译期织入和类装载期织入。
* Proxy（代理）：一个类被AOP织入增强后，就产生一个结果代理类。

使用场景：日志、安全统一校验

### Spring用到了哪些设计模式

* 工厂模式：创建bean、获取bean
* 单例模式和原理模式：bean的类型，singleton和prototype
* 监听模式：自定义事件发布，ApplicationListener
* 责任链模式：AOP，HandlerExecutionChain，拦截器
* 策略模式：创建代理
* 模版方法模式：IOC容器初始化时
* 解释器模式：SpringEL

### SpringMVC流程

（略）

### SpringBoot

最主要的功能：自动装备
* 不用SpringBoot：需要自己配置框架的配置文件。
* 使用SpringBoot：只需要配置可选的application.yml，将开发重点放在业务逻辑上，而不是配置上。

自动装配的原理：约定优于配置
* 核心：将一些配置功能，前置到源码低层实现好

自动装配的两个特点：
* 版本仲裁中心：配置依赖时，有时不需要明确版本（不用记忆，避免冲突）
* 提供了很多starter（场景启动器）：批量jar

## SpringCloud

微服务治理框架，内置了许许多多的组件

### Eureka

* 服务注册中心，类似于dubbo中的zookeeper
* 分为两个组件：Eureka Server和Eureka Client
* 需要注意：Eureka Client有两个角度：站在Eureka来说是一个客户端，站在系统角度来看是一个客户端。

### Ribbon

客户端负载均衡工具

### Feign

声明式客户端负载均衡工具

与Ribbon的区别：

* Feign是建立在Ribbon之上的。
* Ribbon是面向url地址的，Feign是面向接口的。

### Hystrix 

服务雪崩：请求链上一个服务出现问题，导致其他调用的服务依次等待。
解决服务雪崩：熔断器

# 分布式核心设计 缓存与锁

## 简介

现在什么技术最火？

大数据、人工智能、区块链、边缘计算、微服务 -> 分布式

分布式：将大项目拆成小项目

微服务与分布式的区别：
* 分布式：拆了就行
* 微服务：纵向拆分（根据业务逻辑拆分），最小化拆分

## CAP理论

任何一个分布式系统，都必须重点考虑的原则。
* C：一致性：所有子节点中的数据要保持一致性
* A：可用性：整体能用
* P：分区容错性：允许部分失败

CAP理论：在任何分布式系统中，CAP三者不可能共存，只能同时存在两个。

基础知识：一般而言，至少要保持P可行，因为分布式经常会出现弱网环节。

## BASE理论（Basically Available）

为了弥补CAP的不足。
尽最大努力近似的实现CAP三者，诗音最终一致性代替一致性

前置：
* 强一致性（时时刻刻一致，短时间内一致）
* 最终一致性（只要最后一致即可）

软状态：允许中间某个时刻数据不一致。

BASE理论：首选满足AP，因此不能男足C，但是可以用最终一致性来代替C。

## 分布式缓存

一般需要提前缓存。

缓存击穿：某一个热点数据过期造成大量用户请求直奔db的现象。
缓存雪崩：
缓存穿透：