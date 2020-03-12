# 第一天 Spring概念和IOC入门（IOC操作xml配置文件）

## Spring概念

1. Spring是一个开源的轻量级框架
2. Spring的两个核心：IOC和AOP
   * AOP
     * 面向切面编程
     * 扩展一个功能，不是通过修改源代码实现
   * IOC
     * 控制反转
     * 对象的创建，不是通过new的方式实现，而是交给Spring配置创建对象
3. Spring JavaSE/EE的一站式框架
   * Spring在JavaEE的三层结构中，每一层都提供了不同的解决技术
   * web层：SpringMVC
   * service层：Spring IOC
   * dao层：Spring JdbcTemplate

## Spring的入门

1. 把对象那个的创建直接交给Spring进行管理
2. IOC操作主要分为两部分：
   * IOC的配置文件方式
   * IOC的注解方式

**IOC低层原理**

1. IOC低层原理使用的技术
   * xml配置文件
   * dom4j解析xml
   * 工厂设计模式
   * 反射

第一步 创建xml配置文件，配置要创建的对象类  

```
<bean id="userService" class="service.UserService"/>
```

第二步 创建工厂类，使用dom4j解析配置文件+反射

```
public class UserFactory{
	public static UserService getService(){
		//使用dom4j解析xml文件
		String classPath = "...";
		Class<?> clazz = Class.forName(classPath);
		UserService service = clazz.newInstance();
		return service;
	}
}
```

**IOC入门案例**

1. 导入相关jar包
2. 创建类，在类里面创建方法
3. 创建spring配置文件，配置创建类
   * Spring核心配置文件名称和位置不是固定的
   * 建议放到src下面，官方建议名字为applicationContext.xml
   * 需要引入schema约束
   * 需要配置对象创建，使用bean标签
4. 写代码测试对象创建
   * 这段代码只在测试中使用

也可以通过给一个get方法加上`@Bean`注解，配置对应的Bean。

## Spring的bean管理（基于XML配置文件）

**bean实例化的三种方式**

1. 在Spring里面可以通过配置文件创建对象
2. bean实例化的三种方式
   * 使用类的无参构造方法创建（重点）
   * 使用静态工厂创建
   * 使用实例工厂创建

也可以使用带有`@Configuration`注解的工厂类创建，里面的get方法加上`@Bean`注解。

**bean标签常用属性**

1. id属性
   * 说明：bean的名字，可以任意命名，不能包含中文和特殊符号。
2. class属性
   * 说明：要创建的bean对象的类名全路径
3. name属性
   * 和id属性一样，可以加上特殊符号
4. scope属性
   * singleton：默认值，单例
   * prototype：多例（原型）
   * request：web项目中
   * session：web项目中
   * globalSession：web项目中，应用在Porlet环境

**属性注入方式介绍**

1. 属性注入：在创建对象时向属性中注入值
2. 属性注入的三种方式
   * 有参构造
   * set方法（重点）
   * 接口注入

* 在spring框架里面，只支持前面两种方式

**使用有参构造注入属性**

```
<bean id="propUser1" class="PropUser1">
	<constructor-arg name="userName" value="Windea"/>
</bean>
```

**使用set方法注入属性（重点）**

```
<bean id="propUser2" class="PropUser2">
	<property name="userName" value="Windea"/>
</bean>
```

**注入对象类型属性（重点）**

可以在一个类中注入另一个类的对象作为属性。

1. 创建一个service类和一个dao类
   * 在service里面得到dao对象
2. 具体实现过程
   * 在service里面定义一个dao类型的属性
   * 生成dao类型属性的set方法
   * 编写相关的配置文件
3. 或者使用`@Autowired`进行属性注入
   * 适用对象：构造方法（唯一的）、字段、set方法、config方法
   * 适用对象的可访问性不需要是公共的
   * 进行属性注入的类需要注解为Spring组件

```
<bean id="userDao" class="com.windea.test.spring.day01.UserDao"/>
<bean id="userService" class="com.windea.test.spring.day01.UserService">
	<property name="dao" ref="userDao"/>
</bean>
```

**p命名空间注入（用得少）**

首先要引入对应的xml约束：`xmlns:p="http://www.springframework.org/schema/p"`

```
<bean id="propUser1" class="PropUser1" p:userName="Windea"/>
```

**spring注入复杂数据**

在property标签里面编写list/map/properties子标签。

```
<bean id="users" class="Users">
	<!--数组也是使用list标签-->
	<property name="userList">
		<list>
			<value>Windea</value>
			<value>Evera</value>
		</list>
	</property>
	<property name="userMap">
		<map>
			<entry key="1" value="1"/>
			<entry key="2" value="2"/>
		</map>
	</property>
	<property name="userProps">
		<props>
			<prop key="1">1</prop>
			<prop key="2">2</prop>
		</props>
	</property>
</bean>
```

**IOC和DI的区别**

1. IOC：控制反转，将对象创建交给Spring配置
2. DI：依赖注入，向类里面的属性中设置值
3. 关系
   * 依赖注入不能单独存在，需要在IOC的基础之上完成

**Spring整合web项目原理**

1. 加载Spring核心配置文件
   * 需要实例化一个ApplicationContext对象，但是效率很低
2. 实现思想：把加载配置文件和创建对象的过程，在服务器启动时完成
   * Hibernate中的SessionFactory的处理方式也类似
3. 实现原理
   * ServletContext对象
   * 监听器（监听ServletContext对象创建）
   * 具体使用

* 在服务器启动的时候，为每个项目创建一个ServletContext对象
* 在创建的时候，使用监听器可以监听到在什么时候创建
* 使用监听器监听到该对象创建时，就加载Spring配置文件，把配置文件和配置对象创建
* 把创建出来的对象放到ServletContext域对象里面

------

# 第二天 Spring的IOC操作（注解）和AOP概念

## Spring的bean管理（注解）

**注解的介绍**

1. 注解：代码里面的特殊标记，是永久注解可以完成一些相关功能
2. 注解写法：`@Name(var1="...")`
3. 注解可以使用在很多地方（类、方法、属性...）

**使用注解开发准备**

1. 导入jar包
   * 导入基本的jar包
   * 导入aop的jar包
2. 创建类，创建方法
3. 创建Spring配置文件，引入约束
   * 使用ioc基本功能，需要引入beans约束
   * 使用ioc注解开发，还需要引入context约束
4. 在配置文件里面配置开启注解扫描

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
	
	<!--开启注解扫描，如果有多个包，一种方法是在属性值里面用逗号隔离，一种方法是写上一级包名-->
	<context:component-scan base-package="com.windea.test.spring.day02"/>

	<!--只会扫描属性上面的注解-->
	<context:annotation-config/>
</beans>
```

**使用注解创建对象**

以上四个注解功能都是一样的，后三个是为了让对应的类的用途更加清晰。

1. 使用`@Component`注解，作用在对应类上
   * value属性：组件的名字
2. 使用`@Controller`注解，作用在类上，表示web层
3. 使用`@Service`注解，作用在类上，表示业务层
4. 使用`@Repository`注解，作用在类上，表示持久层

配置作用范围：

1. 使用`@Scope`注解，作用在对应类上
   * 只能用于Spring组件或者注解有`@Bean`的类的工厂方法
   * value属性：同scopeName属性
   * scopeName属性：可选值为`prototype`、`singleton`（默认值）
   * proxyMode属性：

配置生命周期：

1. 使用`@PostConstruct`注解
   * 相当于init-method
   * 注解在相应的方法上
2. 使用`@PreDestroy`注解
   * 相当于destroy-method
   * 注解在相应的方法上

**使用注解注入属性**

1. 创建service类，创建dao类，在service类中得到到类对象
   * 在service类中创建dao字段，不需要set方法，在字段上加上`@Autowired`注解
   * 根据类型名自动装配，与组件名没有关系
   * 或者：使用`@Resource(name="...")`注解，name属性为组件的名字
   * 注意：一旦使用这种方式，就不能存在相同名字的组件，即使包名不同

注入默认值：

1. 使用`@Value注解`，作用在属性上
   * value属性：格式为`${systemProperties.myProp}`
   * 需要在所在类上加上`@PropertySource`注解，属性值为对应属性文件的路径
   * 例如：`classpath:/systemProperties.properties`

**xml和注解方式混合使用**

1. 创建对象操作一般使用配置文件方式实现
2. 注入属性操作使用注解方式实现

## AOP

**aop概述**

面向切面编程：扩展功能不通过修改源代码实现。  
AOP采取横向抽取机制，取代了传统纵向继承体系重复性代码。  
（性能监视、事务管理、安全检查、缓存）

**aop低层原理**

纵向抽取机制：提取父类、提取接口等

底层：动态代理方式实现  
使用动态代理方式，创建接口实现类代理对象，增强类中方法。  
创建一个平级的代理对象，实现相同的功能。

注意：  
针对有接口的方法，需要使用接口代理  
针对没有接口的方法，在子类里面调用父类的方法完成增强

**aop操作相关术语**

JoinPoint（连接点）：  
所谓连接点是指那些被拦截到的点。在Spring中，这些点指的是方法，因为Spring只支持方法类型的连接点。  
概述：类里面可以被增强的方法。

Pointcut（切入点）：  
所谓切入点是指我们要对哪些JoinPoint进行连接的定义。  
概述：类里面实际被增强的方法。

Advice（通知/增强）：  
所谓通知是指拦截到连接点后要做的事情。  
通知分为前置通知，后置通知，异常通知，最终通知，环绕通知（切面要完成的功能）。  
概述：要扩展的功能的逻辑。

* after 前置通知：在方法之前执行
* before后置通知：在方法之后执行
* after-throwing 异常通知：方法出现异常后执行
* after-returning 最终通知：在后置通知之后执行
* around 环绕通知：在方法之前和之后执行

Aspect（切面）：  
是切入点和通知（引介）的结合。一般为一个类。  
概述：把增强应用到具体方法（切入点）上面的过程。

Introduction（引介）：  
引介是一种特殊的通知，在不修改类代码的前提下，可以在运行期间为类动态地添加一些方法或字段。

Target（目标对象）：  
代理的目标对象。  
概述：增强方法所在的类。

Weaving（织入）：  
把增强应用到目标对象，来创建新的代理对象的过程。  
Spring采用动态代理织入，而AspectJ采用编译期织入和类装载期织入。

Proxy（代理）：  
一个类被AOP织入增强后，就产生一个结果代理类。

## Spring的aop操作（基于AspectJ的xml方式）

1. 在spring里面进行aop操作，需要使用到AspectJ
2. 使用AspectJ实现aop的两种方式
   * 基于AspectJ的xml配置
   * 基于AspectJ的注解配置

AspectJ是一个面向切面的框架，它扩展了Java语言。AspectJ定义了aop语法，所有有一个专门的编译器用来生成遵循java字节编码规范的class文件。

**aop操作准备**

1. 导入aop相关的jar包
   * `aopalliance-1.0.jar`
   * `aspectjweaver-1.8.7.jar`
   * `spring-aop-5.1.4.RELEASE.jar`
   * `spring-aspects-5.1.4.RELEASE.jar`
2. 创建Spring核心配置文件，导入aop的约束

**使用表达式来配置切入点**

1. 切入点：实际增强的方法
2. 常用的表达式
   * `execution(<访问修饰符，*表示任意>?<返回类型><方法名>(<参数，..表示任意参数>)<异常>)`
   * `execution(* windea.test.spring.day02.aop.AopBook.print(..))`
   * `execution(* *.*(..))`
   * 匹配所有以save开头的方法：`execution(* save*(..))`

**示例代码**

```
<!--配置对象-->
<bean id="aopBook" class="windea.test.spring.day02.aop.AopBook"/>
<bean id="aopBookEn" class="windea.test.spring.day02.aop.AopBookEnhance"/>

<!--配置aop操作-->
<aop:config>
	<!--配置切入点-->
	<aop:pointcut id="pointcut1" expression="execution(* windea.test.spring.day02.aop.AopBook.*(..))"/>
	<!--配置切面-->
	<aop:aspect id="aspect1" ref="aopBookEn">
		<!--配置增强类型为before，method：增强所用的方法，pointcut-ref：切入点引用-->
		<aop:before method="print" pointcut-ref="pointcut1"/>
	</aop:aspect>
</aop:config>
```

## log4j介绍

1. 通过log4j可以看到程序运行中的一些更详细的信息
2. 使用
   * 导入log4j的jar包
   * 复制log4j的配置文件到src下面
   * 进行个性化配置

3. 每次访问action的时候，都会加载spring配置文件
4. 解决方案：
   * 在服务器启动的时候，创建对象加载配置文件
   * 低层使用监听器，监听ServletContext对象
5. 在spring里面不需要我们自己写代码实现
   * 封装了一个监听器，只需要进行配置就可以了
   * 配置监听器前，需要导入spring整合web项目的jar包
   * `spring-web-5.1.4.RELEASE.jar`	
   * 指定加载spring配置文件的位置

在web.xml中：

```
<!--指定spring配置文件的位置-->
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>classpath:spring-config.xml</param-value>
</context-param>

<!--配置监听器-->
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

------

# 第三天 JdbcTemplate和Spring管理事务

## 基于AspectJ的注解操作

1. 编写注有`@Component`的类，以及另外一个注有`@Component`和`@Aspect`的类
   2.在后者里面编写注有`@Before`、`@After`的通知方法，增强前者的方法
   * 不需要在前者需要增强的方法上加上`@Pointcut`注解
2. 编写注有`@Configuration`的设置类
   * 需要再加上`@EnableAspectJAutoProxy`和`@ComponentScan`注解
   * 这样就不需要显示声明注有`@Bean`的工厂方法
3. 实例化`AnnotationConfigApplicationContext`类，注册相关设置类，然后刷新
4. `通过context.getBean(clazz)得到对应的bean`

**Spring的jdbcTemplate操作**

1. Spring是一一站式框架
   * 针对JavaEE三层，每一层都有解决技术
   * 在dao层，使用JdbcTemplate
   * Spring对不同的持久化技术都进行了封装
2. JdbcTemplate的使用和DBUtils的使用很相似

**准备工作**

1. 导入相关jar包
   * spring-jdbc、spring-tx
2. 创建对象，设置数据库信息
3. 创建jdbcTemplate对象，设置数据源
4. 调用jdbcTemplate对象里面的方法实现操作
   * 增加、修改和删除都使用`template.update(sql,args)`方法

5. 用JdbcTemplate实现查询，有一个RowMapper
   * 没有实现类，需要根据不同类型数据，自己进行数据封装

在一个项目中尽量避免使用过多的技术。

**查询的具体实现**

查询返回一个值

```
String sql = "select count(*) from t_user";
//参数：sql语句，返回值类型
Integer count = template.queryForObject(sql, Integer.class);
System.out.println(count);
```

查询返回一个对象

```
String sql = "select * from t_user where userName=?";
//参数：sql语句，RowMapper（lambda:rs,num->T），多个参数
var user = template.queryForObject(sql, (rs, num) -> {
	User u = new User();
	u.setUserName(rs.getString("userName"));
	u.setPassword(rs.getString("password"));
	u.setAddress(rs.getString("address"));
	return u;
}, "Windea");
System.out.println(user);
```

查询返回一组对象

```
String sql = "select * from t_user";
//参数：sql语句，RowMapper（lambda:rs,num->T），多个参数
List<User> list = template.query(sql, (rs, num) -> {
	User u = new User();
	u.setUserName(rs.getString("userName"));
	u.setPassword(rs.getString("password"));
	u.setAddress(rs.getString("address"));
	return u;
});
list.forEach(System.out::println);
```

## Spring配置连接池

**Spring配置c3p0连接池**

1. 导入jar包
2. 配置数据库连接参数
   * 使用xml文件配置
   * 使用注解的方式配置

用xml的方式进行配置

```
<!--配置c3p0连接池-->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
	<property name="driverClass" value="com.mysql.cj.jdbc.Driver"/>
	<property name="jdbcUrl" value="jdbc:mysql://localhost:3306/SSHLesson?serverTimezone=GMT%2B8"/>
	<property name="user" value="Windea"/>
	<property name="password" value="BreezesLanding"/>
</bean>
```

**dao使用JdbcTemplate**

## Spring事务管理

**事务的相关概念**

1. 什么是事务
2. 事务的特性
   * 原子性，一致性，隔离性（多个事务之间），持久性
3. 不考虑隔离性会产生读的问题
   * 脏读，虚读，重复读...

* 解决读的问题
  * 设置隔离级别

**Spring事务管理api**

1. spring事务管理的两种方式
   * 编程式事务管理（一般不用）
   * 声明式事务管理
     * 基于xml配置文件
     * 基于注解
2. Spring事务管理高层抽象主要包括3个接口
   * PlatformTransactionManager 事务管理器
   * TransactionDefinition 事务定义信息（隔离，传播，超时，只读）
   * TransactionStatus 事务具体运行状态
3. spring为不同的持久化框架提供了不同的PlatformTransactionManager接口实现

* `org.springframework.jdbc.datasource.DataSourceTransactionManager`
  * 使用Spring JDBC或MyBatis进行持久化数据时使用
* `org.springframework.orm.hibernate5.HibernateTrasactionManager`
  * 使用Hibernate5.X进行持久化数据时使用
* `org.springframework.jdo.JdoTransactionManager`
  * 当持久化机制是Jdo时使用
* `org.springframework.transaction.jta.JtaTransactionManager`
  * 使用一个JTA实现来管理事务，在一个事务跨越多个资源时必须使用

4. 无论何种方式，首先都需要配置事务管理器

**搭建转账环境**

1. 创建数据库表，添加一些数据
2. 创建service和dao类，完成注入关系
   * service层又叫业务逻辑层
   * dao层中不添加业务
3. 问题的解决
   * 添加事务解决，出现异常进行回滚操作

## Spring进行事务配置（声明式）

**声明式事务管理（xml）**

1. 配置文件使用aop思想配置
   1. 配置事务管理器
   2. 配置事务增强
   3. 配置切面

```xml
<beans>
	<!--（省略）配置其他bean，包括dataSource和jdbcTemplate-->

	<!--STEP 配置事务管理器-->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
	</bean>
	<!--STEP 配置事务增强-->
	<tx:advice id="txAdvice" transaction-manager="transactionManager">
		<tx:attributes>
			<!--参数：service层增强的方法名称/名称匹配规则，传播行为（默认为REQUIRED）-->
			<!--用*匹配任意位数的字符-->
			<tx:method name="transfer" propagation="REQUIRED"/>
		</tx:attributes>
	</tx:advice>
	<!--STEP 配置切面-->
	<aop:config>
		<!--切入点-->
		<aop:pointcut id="pointcut1" expression="execution(* windea.test.spring.day03.tx.TxService.*(..))"/>
		<!--切面-->
		<aop:advisor advice-ref="txAdvice" pointcut-ref="pointcut1"/>
	</aop:config>
</beans>
```

**声明式事务管理（注解）**

1. 配置事务管理器
2. 配置事务注解
   * 在注有`@Configuration`的设置类上，再加上`@EnableTransactionManagement`注解
3. 在要使用事物的方法的所在类上添加注解`@Transactional`

------

# 第四天 SSH框架整合开发

## 回顾SSH框架知识点

**Hibernate框架**

1. hibernate核心配置文件
   * orm思想（对象关系映射）
   * 数据库信息
   * hibernate信息
   * 映射配置
   * 如果单独使用hibernate框架，核心配置文件hibernate.xml的名称和位置是固定的
   * hibernate和spring整合的时候，则是没有固定要求的
2. hibernate映射配置文件
   * 实体类和数据库表类映射关系——使用orm思想
3. hibernate操作的步骤
   * 在spring框架对hibernate框架进行封装，使用HibernateTemplate

**Struts2框架**

1.Action操作
* Action创建的三种方式
* 继承ActionSupport
* 配置action访问路径
* 创建struts.xml文件，名字和位置是固定的
* 配置访问action的多个方法
* 使用通配符的方式配置
* 在action获取表单提交数据
* 获取request对象
* 属性封装
* 模型驱动（重点）
* 表达式封装
* 在action里面操作域对象
* 使用ServletActionContext获取域对象
* 配置struts2的过滤器

2. 值栈
   * 向值栈中存放数据
     * set方法
     * push方法
     * 定义变量，生成get方法
   * 从值栈中获取数据
     * 在jsp中使用struts2标签+ognl获取
     * `<s:property>`方法
     * `<s:iterator>`方法
3. 拦截器
   * aop和责任链模式
   * 自定义拦截器
     * 继承MethodFilterInterceptor
     * 重写类里面的方法
     * 配置拦截器和action的关联

**Spring框架**

1. spring核心配置文件
   * 名称和位置没有固定要求
   * 引入schema约束
2. 创建对象
   * xml配置方式：`<bean id="..." class="...">`
   * 注解方式：四个注解
3. 注入属性
   * xml配置方式
   * 注解方式：两个注解（`@Autowired`,`@Resource`,`@Value`,...）
4. 使用ServletContext对象监听和实现
   * 在服务器启动的时候，加载spring配置文件
   * 配置spring监听器
   * 指定spring配置文件的位置

在web.xml中：

```
<!--指定spring配置文件的位置-->
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>classpath:spring-config.xml</param-value>
</context-param>

<!--配置监听器-->
<listener>
	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

4. JdbcTemplate
5. Spring事务配置
   * xml配置方式
   * 注解配置方式

## SSH整合思想

web层：struts2 → service层：spring → dao层：hibernate

**Struts2框架和Spring框架整合**

1. 把action的创建交给Spring进行管理
   * 除了写上bean标签/@Bean注解外，还需要设置scope为prototype

**Spring框架和Hibernate框架整合**

1. 不需要在Hibernate核心配置文件中配置数据库连接参数
   * 直接写在Spring配置文件中，或者定义设置类的工厂方法
2. 把SessionFactory对象的创建交给Spring进行管理
   * 以在服务器启动时就创建

## SSH整合

**整合Struts2和Spring框架**

把Struts2的action交给spring管理。

1. 导入struts2的相关jar包
2. 导入用于整合的jar包（`struts2-sprig.plugin`）
3. 导入spring的jar包，另外还需特别导入web的jar包（`spring-web`）

**整合Spring和Hibernate框架**

1. 导入用于整合的jar包（`spring-orm`）
2. 搭建hibernate环境
   * 创建实体类
   * 配置实体类映射关系（JPA注解）
   * 创建核心配置文件
3. 去掉hibernate核心配置文件中的数据库配置，在spring中配置
4. 把Hibernate的sessionFactory交给Spring配置
   * 服务器启动时，加载spring配置文件，把配置文件中的对象创建（或者注册JPA注解类）
   * 因为创建sessionFactory的代码不是new出来的，而是多行代码实现的。
   * spring里面针对上面情况，封装了一个类，配置类对象可以创建sessionFactory

XML配置方式：

```
<bean id="sessioniFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
	<property name="dataSource" ref="dataSource"/>
	<property name="configLocations" value=classpath:hibernate.cfg.xml"/>
</bean>
```

5. 编写dao代码，注意注入HibernateTemplate属性
6. 配置事务
   * 没有配置事务，事务是只读的，不会自动提交
   * 配置事务管理器
   * 开启事务注解

## SSH框架整合过程

1. 导入相关的jar包
2. 搭建struts2环境
   * 创建action，创建struts.xml配置文件，配置action
   * 配置struts2的过滤器
3. 搭建hibernate环境
   * 创建实体类
   * 配置实体类和数据库表的映射关系
   * 创建hibernate核心配置文件
   * 引入核心配置文件
4. 搭建spring环境
   * 创建spring核心配置文件
   * 让spring配置文件在服务器启动时加载
   * 配置spring监听器
   * 指定spring配置文件位置
5. struts2和spring整合
   * 把action在spring中配置（多实例）
   * 在struts.xml中把action标签的class属性里面，写bean的id值
6. spring和hibernate整合
   * 把hibernate核心配置文件中数据库配置，放到spring里面配置
   * 把hibernate的sessionFactory在spring中配置
7. 在dao里面使用hibernateTemplate
   * 在dao中注入hibernateTemplate对象
   * 在hibernateTemplate对象中注入sessionFactory
8. 配置事务

## 整合其他方式

1. spring整合hibernate时，可以不写hibernate核心配置文件
   * 把hibernate核心配置文件中，基本信息配置和映射引入都放到spring中配置
   * 使用`localSessionFactoryBean.setHibernateProperties()`方法，配置Hibernate属性。
   * 使用`localSessionFactoryBean.mappingResources()`方法，配置映射配置文件。

## Spring分模块开发

1. 在Struts2中：
   * 使用`<include file="path"/>`标签

2. 在spring里面可以配置多个内容，造成配置混乱，不利于维护
3. 在spring核心配置文件中，可以把一部分配置拿出，然后再引入
   * `<import resource="classpath:config.xml"/>`
4. 也可以用注解代替：
   * `@ImportResource("classpath:config.xml")`
   * 对于注有`@Configuration`的配置类

------

# 拓展

## Spring核心配置文件的一些标签

**加载其他配置文件**

`<context:property-placeholder location="classpath:db.properties`/>

## Spring测试

* 在测试类上添加注解：
  * `@RunWith(SpringJUnit4ClassRunner.class)`
  * （如果使用xml配置）`@ContextConfiguration("classpath:applicationContext.xml")`
* 这样就可以直接使用`@Autowired`为测试类注入属性。

