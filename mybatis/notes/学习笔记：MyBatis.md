# 前置

## MyBatisCodeHelperPro激活码

* 63a72cee-0c25-485c-af20-3df5e6a12417
* 92eaff66-bcf4-4114-82f2-cb11b171e605

# 第一天 MyBatis的基础知识（重点，内容量多）

## 原生态JDBC编程中的问题总结（单独使用jdbc开发）

使用jdbc查询mysql数据库中用户表的记录。

导入sql脚本：

* sql_table.sql：记录表结构。
* sql_data.sql：记录测试数据。
  * 在实际企业开发中，最后都要提供一个初始化数据脚本。

**问题总结**

* 数据库连接，使用时就创建，不使用就释放。
  * 对数据库进行频繁的开启和关闭，耗费性能。
  * 解决方案：连接池，框架。
* 将sql语句硬编码到java代码中。
  * 如果sql语句修改，需要重新编译java代码，不利于维护。
  * 设想：将sql语句配置到xml配置文件中。
* 向pstmt中设置参数时，对占位符位置和参数值，存在硬编码问题。
  * 设想：同样配置到xml文件中。
* 从rs中遍历结果集数据时，存在硬编码问题。
  * 设想：将查询的结果集映射成java对象。

## MyBatis框架原理（掌握）

> 持久层的框架都是对jdbc的封装。

**MyBatis是什么**

MyBatis是一个持久层的框架，是Apache下的顶级项目。  
MyBatis让程序员将主要精力放在sql上，通过MyBatis提供的映射方式，自由灵活地生成满足需求的sql语句。  
半自动化，大部分需要程序员编写。  
MyBatis可以将对于pstml的输入参数自动就行输入映射，将查询结果集灵活映射成java对象（输出映射）。    
MyBatis现在托管在[github](https://github.com/mybatis/mybatis-3/release)。

**MyBatis框架**

* SqlMapConfig.xml
  * MyBatis全局配置文件（名称不是固定的）
  * 配置数据源、事务等MyBatis运行配置
  * **配置映射关系，主要就是配置sql语句（mapper.xml）**
* SqlSessionFactory（会话工厂）
  * 作用：创建SqlSession
* SqlSession（会话）
  * 一个面向用户的接口
  * 作用：操作数据库（发出sql语句）
* Executor（执行器）
  * 一个接口，实现有基本执行器、缓存执行器
  * 作用：在SqlSession内部通过它来操作数据库
* MappedStatement（底层封装对象）
  * 作用：对操作数据库存储进行封装
  * 包括：sql语句，输入参数，输出结果类型
* （MySQL）

* 输入参数类型
  * java简单类型
  * HashMap
  * JavaBean
* 输出结果类型
  * ...

## MyBatis入门程序

> 通过订单商品案例来驱动。    
> > 用户的增删改查。

**需求**

* 根据用户id查询用户信息
* 根据用户名称模糊查询用户信息
* 添加用户
* 删除用户
* 更新用户

**准备工作**

* 导入相关jar包
* 创建log4j的属性文件。

**编写SqlMapConfig.xml**

---

**功能实现**

* 根据用户id查询用户信息
  * 创建实体类
  * 创建映射文件
    * 映射文件命名：User.xml，如果是mapper代理开发，则叫UserMapper.xml
    * 在映射文件中配置sql语句
  * 在SqlMapConfig.xml中加载映射文件
  * 程序的编写
* 根据用户名称模糊查询用户信息
* 添加用户
* 主键返回
  * 自增主键的返回
    * mysql自增主键，在执行insert提交之前自动生成一个自增主键
    * 通过`LAST_INSERT_ID()`函数获取（在插入之后调用）
    * `select LAST_INSERT_ID()`
  * 非自增主键的返回
    * 可以使用`UUID()`函数生成，需要修改id字段的类型为`varchar(35)`
    * 先通过uuid()查询到主键，然后插入到sql语句中，在插入之前获取
  * 通过oracle序列生成主键
    * `select 序列名.nextval()`
* 删除用户
* 更新用户

**总结**

* parameterType
  * 在映射文件中，用于指定输入参数的类型
* resultType
  * 在映射文件中，用于指定输出参数的类型
* `#{}`
  * 表示一个占位符号
  * 如果接受的是简单类型，花括号中可以写成value或其他名称
  * 接受对象类型的属性值，通过`#{属性.属性.属性...}`的方式获取对象属性值
* `${}`
  * 表示一个拼接符号，会引起sql注入。不建议使用
  * 如果接受的是简单类型，花括号中可以写成value
* `session.selectOne()`
  * 表示查询出一条记录进行映射
  * 需要使用`selectList()`的场合，不能使用`selectOne()`
* `session.selectList()`
  * 表示查询出一个列表进行映射
* 注意：插入、更新和删除操作，需要提交事务

**MyBatis和Hibernate的本质区别和应用场景**

* Hibernate
  * 一个标准的orm框架，入门门槛较高。不需要程序书写sql，自动生成。
  * 对sql语句进行优化、修改是比较困难的。
  * 应用场景：
    * 适用于需求变化不多的中小型项目。
    * 例如：后台管理系统，erp、orm、oa。
* MyBatis
  * 专注的是sql语句本身，需要程序员自己编写sql语句，利于sql的修改和优化。
  * 可以说是一个不完全的orm框架。
  * 虽然程序员需要自己写sql，MyBatis也可以使用输入映射和输出映射。
  * 应用场景：
    * 适用于需求变化较多的项目。
    * 例如：互联网项目
* 注意
  * 企业在进行技术选型时，以低成本、高回报作为技术选型的原则。
  * 根据项目组的技术力量进行选择。

## MyBatis开发dao

* SqlSession的使用范围
* SqlSessionFactoryBuilder
  * 通过它创建SqlSessionFactory。
  * 只需要当成工具类使用即可，不需要使用单例模式管理。
* SqlSessionFactory
  * 通过SqlSessionFactory创建SqlSession。
  * 使用单例模式管理。
* SqlSession
  * 一个面向用户（程序员）的接口。
  * 提供了许多操作数据库的方法。
  * 线程不安全。实现类中，除了有操作数据库的方法，还有数据域的属性。
  * 最佳应用场合是放在方法体内，定义成局部变量使用。

**原始dao开发方法（掌握）**

> 程序员需要编写dao的接口和实现类。

* 思路
  * 程序员需要些dao接口和dao实现类
  * 需要向dao实现类中注入SqlSessionFactory，在方法体内创建SqlSession

**mapper代理开发方法（掌握）**

> mapper相当于dao接口。

* 原始dao接口实现的问题
  * 重复代码。dao接口实现类方法中存在大量模版方法。
  * 调用sqlSession方法时，将statement的id硬编码了。
  * 调用sqlSession方法时传入的变量的类型，可以是不正确的。
* 思路
  * 程序员只需要写mapper接口。（相当于dao接口）
  * 需要编写mapper.xml映射文件。（或者添加相关注解？）
  * MyBatis可以自动生成mapper接口实现类的代理对象。
  * 需要遵循一些开发规范。
  * 仍然需要在核心配置文件中加载mapper配置文件。
* 开发规范
  * mapper.xml中的namespace等于mapper接口的地址。
  * mapper.java接口中的方法名和mapper.xml中的statement的id一致。
  * mapper.java中方法的输入参数类型和mapper.xml中parameterType指定的类型一致。
  * 方法返回值类型也要相等。
* 问题总结
  * 代理对象内部调用selectOne或selectList
    * 根据mapper方法的返回值类型，来确定需要调用的方法。
  * mapper接口方法参数只有一个，是否影响系统开发
    * 在系统框架中，dao层的代码应该是被业务层公用的
    * 即使mapper接口只有一个参数，也可以使用包装类型的实体类满足不同的业务需求。
    * 持久层方法的参数可以使用包装类型，但是service层中不建议使用。

## MyBatis配置文件SqlMapConfig.xml

**注意：**配置文件的属性是顺序的。

**properties（属性）**

* 需求：  
  * 将数据库的一些连接参数单独配置在db.properties中，在核心配置文件中加载其中的参数。
  * 使用`${bean.name}`的方式得到那些参数的值。
* MyBatis加载属性的顺序
  * 首先加载在properties标签体内定义的属性
  * 然后读取properties元素中resource或url加载的属性，覆盖已读取的同名属性。
  * 最后读取parameterType传递的属性，覆盖已读取的同名属性。
* 建议
  * 不要在properties标签体内添加属性值
  * 在properties文件中定义的属性名要具有一定的特殊性。例如`xxx.xxx`

```xml
<properties url="database.properties">
	<property name="..." value="..."/>
</properties>
```

**settings（全局配置参数）**

* 在运行时调整一些运行参数。
* 例如：开启二级缓存、开启延迟加载。
* 全局参数会影响MyBatis的行为

```xml
<settings>
	<setting name="..." value="..."/>
</settings>
```

**typeAliases（类型别名）（重点）**

* 需求
  * 如果在指定statement时，需要输入类型的全路径，不方便开发。
  * 可以针对parameterType和resultType指定一些别名。
  * 在mapper.xml中通过起别名方便开发。
* 自定义别名
  * 单个别名的定义
  * 批量别名的定义

```xml
<typeAliases>
	<!--单个别名：-->
	<typeAlias type="com.windea.study.mybatis.main.day01.domain.User" alias="user"/>
	<!--批量别名：指定包名，自动扫描，自动定义别名-->
	<package name="com.windea.study.mybatis.main.day01.domain"/>
</typeAliases>
```

* 如何引用

```xml
<select id="findUserByUsername" parameterType="string" resultType="user">
	select * from User where username like '%${value}%'
</select>
```

**typeHandlers（类型处理器）**

* 完成sql类型和java类型的转化。
* 一般不需要我们自定义。

```xml
<typeHandlers>
	<typeHandler handler="" javaType="" jdbcType=""/>
</typeHandlers>
```

**objectFactory（对象工厂）**

**plugins（插件）**

**environments（环境集合属性对象）**

**mappers（映射器）**

* 通过resource属性加载单个映射文件。
  * 直接配置。
* 通过mapper接口加载。
  * 遵循规范：将mapper接口类名和mapper.xml映射文件名称保持一致，且在同一目录中。
  * 前提是使用mapper代理的方法。
* 批量加载
  * 使用package子标签。

```xml
<mappers>
	<mapper resource="com/windea/study/mybatis/main/day01/sqlmap/User.xml"/>
	<mapper resource="com/windea/study/mybatis/main/day01/mapper/UserMapper.xml"/>
	<package name="com.windea.study.mybatis.main.day01.mapper"/>
</mappers>
```

## MyBatis核心：输入映射（掌握）

* 通过parameterType指定输入参数的类型。
* 包括：简单类型、HashMap、实体类类型
* 需求
  * 完成用户信息的综合查询。
  * 需要传入的查询条件可能很复杂。
* 实现
  * 针对上述需求，包装自己需要的查询条件。
  * 在UserMapper.xml中定义用户信息综合查询（查询条件复杂，通过高级查询进行复杂关联查询）
  * 在mapper接口中定义对应的方法。

```xml
<!--用户信息的综合查询-->
<select id="findUserByConditions"
	    parameterType="com.windea.study.mybatis.main.day01.domain.view.UserQuery"
	    resultType="com.windea.study.mybatis.main.day01.domain.ExtendedUser">
	select * from user
	where user.username like '%${user.username}%'
	  and user.sex = #{user.sex} ;
</select>
```

## MyBatis核心：输出映射（掌握）

* resultType
  * 简单类型或者实体类。
  * 只有查询出来的列名和实体类的属性名一致，才能映射成功。
  * 如果没有一个一致，则不会输出实体类对象。
  * 小结
    * 如果查询出来的数据只有一个值，可以用简单类型进行映射。
    * 不管输出的是单个还是多个实体类对象，resultType指定的类型都是一样的。
* resultMap
  * 如果起了字段别名的情况。
  * 注意：引用时可能需要加上namespace。
  * 可以实现高级输出结果映射。
  * 如果查询出来的列名和实体类属性名不一致，可以通过定义一个resultMap，对列名和属性名之间作映射关系。

```xml
<configuration>
	<!--
		定义resultMap（在这个namespace下，可能需要在前面加上namespace）
		id：resultMap的唯一标志 type：最终映射成的对象类型
		id：对表的主键对应的列的映射 result：对普通列的映射
	-->
	<resultMap id="userResultMap" type="com.windea.study.mybatis.main.day01.domain.User">
		<id column="_id" property="id"/>
		<result column="_username" property="username"/>
	</resultMap>

	<!--使用resultMap进行输出映射-->
	<select id="findUserById2" parameterType="int" resultMap="userResultMap">
		select id _id,username _username from user where id = #{value}
	</select>
</configuration>
```

* 总结
  * 使用resultType进行输出映射，列名和属性名需要保持一致。
  * 如果不一致，可以通过定义resultMap作列名和属性名之间的映射。

## MyBatis的动态sql（掌握）

**if判断**

* 需求
  * 用户信息综合查询列表的statement的定义需要使用动态sql。
  * 如果输入参数不为空，才进行查询条数的拼接。

```xml
<!--修改过的用户信息的综合查询-->
<!--动态sql-->
<!--where标签：可以自动去掉条件中的第一个and-->
<select id="findUserByConditions2"
	    parameterType="com.windea.study.mybatis.main.day01.domain.view.UserQuery"
	    resultType="com.windea.study.mybatis.main.day01.domain.ExtendedUser">
	select * from user
	<where>
		<if test="user != null">
			<if test="user.username != null and user.username != ''">
				and user.username like '%${user.username}%'
			</if>
			<if test="user.sex != null and user.sex != ''">
				and user.sex = #{user.sex} ;
			</if>
		</if>
	</where>
</select>
```

**sql标签**

* 需求
  * 将上面实现的动态sql判断代码块抽取出来，组成一个sql片段，在其他的statement中引用。
* 实现
  * 使用sql标签定义sql片段，标签体内即是可带标签的动态sql语句。
  * 经验
    * 基于单表定义sql字段。
    * 在sql片段中不要包括where标签
  * 使用include标签引用sql片段。
  * 注意：引用时可能需要加上namespace。


**foreach标签**

* 如果向sql中传递了一个数组或列表，MyBatis使用foreach来解析

* 需求
  * 在用户查询列表和查询总数的statement中增加d多个id查询
  * sql语句如下：（两种方法皆可）
  * `select * from User where id=1 or id=10 or id=16`
  * `select * from user where id in (1,10,16)`
* 目标
  * 在输入参数类型中添加List<Integer>来传入多个id
  * 在mapper配置文件中使用foreach标签

------

# 第二天 MyBatis高级知识

## 订单商品数据模型分析

* Item
* OrderDetail
* Order
* User

**数据模型分析思路**

* 每张表记录的数据内容
  * 分模块对每张表记录的内容进行熟悉，相当于学习需求（功能）的过程。
* 每张表重要字段的设置
  * 非空字段、外键字段。
* 数据库级别表与表之间的关系
  * 外键关系。
* 表与表之间的业务关系
  * 一定要建立在某个业务意义的基础上。
* 先分析数据级别之间有关系的表之间的业务关系


**分析**

> 数据库分析工具：PowerDesigner

* 用户表 User
  * 记录了购买商品的用户信息。
  * 字段分析
    * id：主键
* 订单表 Order
  * 记录了用户所创建的订单（购买商品的订单）。
  * 字段分析
    * id：主键
    * number：订单号
    * userId：外键，用户的id
* 订单明细表 OrderDetail
  * 记录了订单的详细信息，即购买商品的信息。
  * 字段分析
    * id：主键
    * userId：外键，用户的id
    * itemId：外键，商品的id
* 商品表
  * 记录了商品信息
  * 字段分析
    * id：主键

**数据库表关系分析**

* User到Order
  * 一对多，一个用户可以创建多个订单
* Order到User
  * 一对一，一个订单只由一个用户创建

* Order到OrderDetail
  * 一对多，一个订单可以包括多个订单明细，因为一个订单可以购买多个商品
* OrderDetail到Order
  * 一对一，一个订单明细只能关联查出一个订单

* OrderDetail到Item
  * 一对一
* Item到OrderDetail
  * 一对多


再分析数据库级别没有关系的表之间是否有业务关系。

* Order到Item
  * 一对多，可以通过OrderDetail表建立关系
* Item到Order
  * 一对多，可以通过OrderDetail表建立关系

* User到Item
  * 一对多
* Item到User
  * 多对多

## 高级映射 一对一查询（了解）

**需求**

查询订单信息，关联查询创建订单的用户信息

**分析**

* 确定查询的主表：订单表
* 确定查询的关联表：用户表
  * 关联查询使用内连接？还是外连接？
  * 在Order表中有一个外键userId，通过外键关联查询用户表只能查询1条记录，可以使用内连接。

```sql
select `Order`.* ,User.username,User.sex,User.address
from `Order`,User
where `Order`.userId = User.id;
```

**使用resultType实现**

* 编写mapper.xml
* 编写mapper.java接口
* 在核心配置文件中导入所在的包
* 创建实体类
  * 原始的实体类不能映射全部的字段，需要新建实体类。
  * 继承包含字段较多的实体类

**使用resultMap实现**

* 使用resultMap将查询结果中的订单信息映射到Order对象中。
* 在Order中添加User属性。
* 将关联查询出来的用户信息映射到该属性中。

* 编写mapper.xml，定义resultMap
* 编写mapper.java接口
* 在核心配置文件中导入所在的包
* 创建实体类

**小结**

* 实现一对一查询
  * resultType
    * 较为简单。如果实体类中没有包括查询出来的列名，需要增加列名对应的属性，即可完成映射。
  * resultMap
    * 需要单独定义。如果对查询结果有特殊要求，使用resultMap即可将关联查询映射到pojo的属性中。
    * 可以实现延迟加载。

## 高级映射 一对多查询（了解） 

**需求**

查询订单及订单

**分析**

* 确定主查询表：订单表
* 确定关联查询表：订单明细表
* 在一对一查询基础上添加订单明细表关联即可。

```sql
select `Order`.*,
	       User.username, User.sex, User.address, User.birthday,
	       OrderDetail.id odId, OrderDetail.itemId, OrderDetail.itemCount, OrderDetail.orderId
	from `Order`, User, OrderDetail
	where `Order`.userId = User.id
	  and OrderDetail.orderId = `Order`.id;
```

* 使用resultType进行映射时，订单信息会重复。
* 要求：对Order表的映射，不能出现重复信息。
* 解决思路：在Order类中添加`List<OrderDetail> orderDetailList`属性。


**实现**

* 创建实体类
* 编写mapper.xml，定义resultMap
* 编写mapper.java接口
* 在核心配置文件中导入所在的包

**小结**

* 使用resultMap实现
  * 使用collection子标签配置列和属性的映射
* 使用resultType实现
  * 将订单明细映射到Order表中的orderDetailList中
  * 使用双重循环遍历，去重复

## 高级映射 多对多查询（了解）

**需求**

查询用户以及用户所购买的商品信息

**分析**

* 主查询表：用户表
* 关联表：Order、OrderDetail、Item

```sql
select u.id u_id, u.username u_username, u.birthday u_birthday, u.sex u_sex, u.address u_address,
    o.id o_id, o.number o_number, o.createTime o_createTime, o.note o_note,
    od.id od_id,od.itemCount od_itemCount,
    i.id i_id, i.name i_name, i.price i_price, i.detail i_detail, i.imageUrl i_imageUrl, i.createTime i_createTime
from User u,`Order` o,OrderDetail od,Item i
	where o.userId = u.id and o.id = od.orderId and od.itemId = i.id
order by u.id,o.id,od.id,i.id;
```

* 映射思路
  * 将用户信息映射到User中，在User类中添加订单订单列表的属性
  * 在Order中添加订单明细列表属性
  * 在OrderDetail中添加Item属性

**实现**

* 创建实体类（包含实体类集合属性）
* 编写mapper.xml，定义resultMap
* 编写mapper.java接口
* 在核心配置文件中导入所在的包

**总结**

* 一对多是多对多的特例。
* 使用resultMap是针对那些对查询结果映射有特殊要求的功能。

## resultMap总结

**resultType**

* 作用
  * 将查询结果按照sql列名和实体类属性名一致性地映射到实体类中。
* 场合
  * 常见于一些明细记录的展示。实体类列表。

**resultMap**

* 作用
  * 使用association和collection完成一对一和一对多高级映射。

**association**

* 作用
  * 将关联查询信息映射到一个pojo对象中。
* 场合
  * 为了方便关联查询信息，可以使用association将关联订单信息映射为用户对象的实体类属性。
  * 使用resultType无法将查询结果映射到pojo对象的pojo属性中，根据对结果集查询遍历的需要，选择使用resultType还是resultMap。


**collection**

* 作用
  * 将关联查询信息映射到一个list集合中
* 场合
  * 为了方便查询遍历关联信息，可以使用collection将关联信息映射到list集合中。
  * 例如，查询用户权限范围模块以及模块下的菜单，可使用collection将模块映射到模块list中，将菜单列表映射到模块对象的菜单list属性中。方便对查询结果集进行遍历查询。
  * 如果使用resultType，则无法将查询结果映射到list集合中。

## MyBatis延迟加载

**什么是延迟加载**

* resultMap可以实现高级映射（使用association、collection，它们具备延迟加载功能）。
* 需求
  * 查询订单信息，关联查询用户信息。当需要查询用户信息时，再进行查询。
  * 把对用户信息的按需查询称为延迟加载。
* 延迟加载：先从单表查询，需要时再从关联表进行查询，以提高数据库性能。

**使用association实现延迟加载**

* 需求
  * 查询订单信息，关联查询用户信息。
* 实现
  * mapper.xml
    * 需要定义两个mapper方法对应的statement
    * 只查询用户信息，使用association实现延迟加载。
    * 通过上边查询到的订单信息中的userId，关联查询用户信息。
    * 使用association标签中的select属性指定延迟加载的statement的id
  * mapper.java


**延迟加载的配置**

```xml
<settings>
	<!--启用延迟加载-->
	<setting name="lazyLoadingEnabled" value="true"/>
	<!--让延迟加载的对象任何延迟属性全部加载，按需加载-->
	<setting name="aggressiveLazyLoading" value="false"/>
</settings>
```

**延迟加载思考**

* 不使用mybatis提供的association和collection中的延迟加载功能，如何实现
* 实现
  * 定义两个mapper方法（查询订单列表、根据id查询用户信息）。
  * 先驱查询第一个mapper方法，获取订单列表。
  * 在service类中，按需调用第二个mapper方法查询用户信息。
* 总结
  * 先去查询简单的sql（最好是单表查询）。
  * 再去按需加载关联的其他信息。

## 查询缓存 一级缓存

**什么是查询缓存**

* 用于减轻数据库压力，提高数据库性能。
* MyBatis提供了一级缓存和二级缓存。
* 一级缓存是SqlSession级别的缓存
  * * 在操作数据库中需要构造sqlSession对象，在对象中有一个HashMap用于存储缓存数据。
    * 不同的sqlSession之间的缓存数据区域互不影响。
* 二级缓存是mapper级别的缓存。
  * 多个sqlSession操作数据库得到的数据，会存在二级缓存区域。
  * 多个sqlSession可以共用二级缓存。
* 缓存的意义
  * 如果缓存中有数据，就可以不从数据库中获取，大大提高系统性能。

**原理**

发出查询操作时，先去从缓存中。如果没有，则从数据库中查询，将得到的用户信息存储到一击缓存中。如果有，则直接从缓存中获取。

如果sqlSession执行了提交操作，则会清空sqlSession中的一级缓存。这是为了让缓存中存储的永远是最新的信息，避免脏读。

**一级缓存的应用**

* 正式开发时，是将MyBatis和spring进行整合开发，事务控制在service中。
* 一个service方法中包含很多mapper方法的调用。
* service开始执行时，开启事务，创建sqlSession对象。
* 只要service结束，sqlSession就会关闭。
* 一级缓存仅在一个service中调用。
* 如果执行两次service层方法调用，查询相同的用户信息，不走一级缓存。

## 查询缓存 二级缓存

**原理**

（首先要开启二级缓存）

sqlSession查询到数据时，**当关闭sqlSession后**，会将数据存储到二级缓存中。另一个sqlSession再去查询相同的数据时，会从缓存中读取。

二级缓存与一级缓存的区别：二级缓存的范围更大，多个sqlSession可以共享一个mapper的二级缓存区域。缓存区域是按照namespace区分的。

**开启二级缓存**

* 在核心配置文件中配置（默认是开启的）
* 在mapper映射文件中添加一行`<cache/>`
* 让实体类实现序列化接口。
  * 为了将缓存数据取出，执行序列化操作。
  * 二级缓存的存储介质不一定在内存。

```xml
<settings>
	<setting name="cacheEnabled" value="true"/>
</settings>
```

**禁用二级缓存**

在statement中设置`useCache="false"`，可以禁用当前select语句的二级缓存。

适用情况：例如，根据id查询数据，每次查询都需要最新数据的查询。

**刷新缓存（清空缓存）**

在mapper的同一个namespace中，如果有其他的insert、update、delete操作数据后需要刷新缓存，如果不执行刷新缓存会出现脏读。

设置statement中的`flushCache="true"`来刷新缓存。默认为true。

一般情况下执行提交操作都需要刷新缓存。不需要手动设置。

**cache标签的属性**

* flushInterval：刷新间隔，可以设为任意的正整数，默认只在调用语句时刷新。
* size：引用数目，可以设为任意的正整数，默认为1024。
* readOnly：只读。只读缓存不能被修改，提供了性能优势，可读写缓存会通过序列化返回缓存对象的拷贝，更慢但更安全。默认为false。
* eviction：回收策略，可设为LRU（最少使用移除），FIFO（先进先出）等。

## MyBatis整合ehcache

**分布缓存**

encache：一个分布式的缓存框架。

分布式：为了提高系统并发、性能，一般都会对系统进行分布式部署。  
如果不使用分布式缓存，缓存数据会在各个服务器单独存储。

对缓存数据进行集中管理，使用分布式缓存框架。  
例如：redis、memcached、ehcache。

MyBatis无法实现分布式缓存，需要和其他分布式缓存框架进行整合。

**整合方法**

* MyBatis提供了一个Cache接口，如果要实现自己的缓存逻辑，实现Cache接口开发即可。
* 例如，MyBatis和ehcache整合，MyBatis和ehcache整合包中就提供了一个Cache接口的实现类。
* MyBatis默认实现的Cache类：PerpetualCache。  
* 要和ehcache整合，需要配置cache标签的type属性。
  * 首先需要导入插件`mybatis-ehcache-1.0.0.jar`。
  * `<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>`


**应用场景**

对于访问多的查询请求，且用户对查询结果的实时性要求不高，此时可以采用二级缓存技术降低数据库访问量，提高访问速度。例如：耗时较高的统计分析查询、电话账单查询等。

实现方法如下：通过设置刷新间隔时间，让MyBatis每隔一定时间自动清空缓存。根据数据变化频率设置缓存刷新间隔。比如30分钟、60分钟、24小时。根据需求而定。

（应用场景范围不大。）

**局限性**

MyBatis二级缓存对细粒度的数据级别的缓存实现不好，比如如下需求：对商品信息进行缓存，由于商品信息查询访问量大，但是要求用户每次都能查询最新的商品信息，此时如果使用二级缓存就无法实现党一个商品变化时，只刷新该商品的缓存信息，而不刷新其他商品信息。因为MyBatis的二级缓存区域以mapper为单位划分，当一个商品信息变化时，会将所有商品信息的缓存数据全部清空。解决此类问题需要在业务层根据需求，对数据有针对性地进行缓存。

## MyBatis和Spring整合（重点）

**整合思路**

* 需要spring通过单例方式管理sqlSessionFactory。
* Spring和MyBatis整合生成代理对象，使用sqlSessionFactory创建sqlSession。
* 持久层的mapper、dao都需要由Spring进行管理。

**整合环境**

* 创建一个新的java工程。
* 导入jar包。
  * MyBatis的jar包。
  * MyBatis和Spring的整合包。
    * 早期ibatis和spring的整合由spring提供，现在由MyBatis提供。
  * Spring的jar包。
* 在Spring中配置DataSource和sqlSessionFactory的bean。

**原始dao开发（和Spring整合后）**

* 编写User.xml。
* 编写Dao接口和Dao接口实现类。
  * 让Dao接口实现类继承SqlSessionDaoSupport类。
  * 添加一个sqlSessionFactory属性（重写get方法）。
  * 而是通过getSqlSession方法获得sqlSession。
* 配置dao的bean。

```
@Autowired
@Override
public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
	super.setSqlSessionFactory(sqlSessionFactory);
}
```

**mapper代理开发**

* 编写UserMapper.xml。
* 编写UserMapper接口
* 配置mapperFactoryBean（mapper代理对象）的bean。

```xml
<bean id="userMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
	<property name="sqlSessionFactory" value="sqlSessionFactory"/>
	<property name="mapperInterface" value="com.windea.study.mybatis.main.integration.mapper.UserMapper"/>
</bean>
```

* 问题
  * 需要针对每一个mapper进行配置，非常麻烦。
* 解决
  * 使用mapper的批量扫描。
  * 从mapper包中，扫描出mapper接口，自动创建代理对象，在Spring中注册。
  * 配置MapperScannerConfigurer类的bean。
  * 需要遵循的规范：mapper接口和配置文件在同一目录，名字一一对应。
  * 自动扫描出来的mapper，bean的id为mapper类名的首字母小写。
  * 如果扫描多个包，每个包之间用半角逗号分隔。
  * 在这之后，MyBatis核心配置文件中对mapper文件的扫描就可以去掉了。
* 注意：
  * 不需要扫描mapper接口所在的包，也没必要扫描config类所在的包。
* 特别注意：
  * 配置MapperScannerConfigurer类的bean时，如果使用注解方式开发，需要将之定义在一个单独的Config类中。
  * 否则会由于过早地创建了单例实例，导致无法进行加强。
* 注意：
  * 也可以使用`@MapperScan`注解替代扫描器的bean。

## MyBatis的逆向工程（会用）

**什么是逆向工程**

MyBatis需要程序员自己编写sql语句，MyBatis已经提供了逆向工程，针对单表执行所需要的代码（mapper接口，mapper配置文件，实体类）。

企业实际开发中，常用的逆向工程方式：

* 由数据库表生成java代码。

**下载逆向工程**

`mybatis-generator-core-LATEST-bundle.jar`

**使用方法（会用）**

建议使用java程序方式去运行逆向工程，不依赖开发工具。

生成代码的配置文件（`generatorConfig.xml`）。

* 需要指定mapper接口的位置。
* 需要指定mapper配置文件的位置。
* 需要指定数据库表。
* 有些表的字段需要指定java类型。
* 执行方式：`GeneratorSqlmap.main(args)`。需要配置配置文件的尾椎。

**使用生成的代码**

* 拷贝到自己的工程中。
* 使用XxxExample.Criteria，拼接查询条件。
* `mapper.updateByPrimaryKey()`
  * 对所有数据进行更新。
* `mapper.updateByPrimaryKeySelective()`
  * 数据（属性）不为空时才进行更新。
  * 用于批量更新，不需要先查询，再更新。

------

# 拓展 

## 注解的使用

## 使用MyBatisCodeHelperPro时遇到的一些问题

虽然可以互相导航mapper接口和mapper配置文件、以及其中的数据库操作方法，但是当输入和输出参数类型不正确时，仍然不会给出提示。

选择Service接口/类时，应该将类名包含`Service`的类提前。

生成service方法时，如果service类已经注入了对应的mapper属性，则使用那个mapper。

添加设置：生成service方法时，生成mapper属性，可选：
* 字段自动注入
* 构造方法自动注入
* 简单mapper名称，如`mapper`
* 更长的mapper名称，如`userMapper`

对Statement的ParamName（即`@Param`注解的参数值）进行重构（重命名）。

可以选择是否在dao层、service层不处理异常（`throws Exception`）。且自动识别上一层是否抛出了（全部即可）异常。

当statement的parameterType为对象类型时，可以向下引用对象的属性。例如`#{user.username}`。
