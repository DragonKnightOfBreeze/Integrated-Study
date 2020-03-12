# 第一天 SpringMVC的基础知识

## SpringMVC框架

### 什么是SpringMVC

SpringMVC是Spring框架的一个模块，无需通过中间整合型整合。

SpringMVC是一个基于MVC的web框架。

### mvc在b/s系统下的应用

mvc是一种设计模式。

mvc在b/s系统下的应用：

* `模型层`→`控制层`：处理结果返回
* `控制层`→`模型层`：请求模型进行处理
* `控制层`→`视图层`：视图渲染，将模型数据填充到request域

* 控制层
  * controller
* 模型
  * domain
  * action
  * service
  * dao
* 视图层
  * view
* 在b/s系统下，模型层无法传递数据到视图层

#### SpringMVC

* `request`→请求url→`前端控制器`
* `前端控制器`→请求查找Handler→`处理器映射器`
  * 返回一个执行链 HandlerExecutionChain。
  * 包括HandlerInterceptor...，Handler。
* `前端控制器`→请求执行Handler→`处理器适配器`
* `处理器适配器`→执行→`处理器`
  * 返回模型与视图的结合体 ModelAndView到前端控制器
* `前端控制器`→请求视图解析→`视图解析器 ViewResolver`
  * 返回一个真正的视图。
* `前端控制器`→进行视图渲染→`视图`

* DispatcherServlet 前端控制器
  * 接受用户请求，响应。
* Handler 处理器
  * 平常叫做Controller。
* 处理器映射器 HandlerMapping
* 处理器适配器 HandlerAdapter
  * 执行Handler。	

* 第一步：发起请求到`前端控制器`。
* 第二步：`前端控制器`请求`处理器映射器`查找`处理器`。
  * 可以根据xml配置，也可以根据注解进行查找。
* 第三步：`处理器映射器`向`前端控制器`返回`处理器`。
* 第四步：`前端控制器`调用`处理器适配器`去执行`处理器`。
* 第五步：`处理器`执行完成，给`适配器`返回`ModelAndView`。
* 第六步：`处理器适配器`向`前端控制器`返回`ModelAndView`。
  * ModelAndView是SpringMVC的一个底层对象。
* 第七步：`前端控制器`请求`视图解析器`进行视图解析。
  * 根据逻辑视图名解析成真正的视图。
* 第八步：`视图解析器`向`前端控制器`返回`View`。
* 第九步：`前端控制器`进行视图渲染。
  * 将模型数据填充到request域。
* 第十步：`前端控制器`向用户响应结果。

**组件**

* 前端控制器 DispatcherServlet（不需要程序员开发）
  * 作用：接受请求，响应结果，相当于转发器。
  * 中央处理器，减少了其他组件之间的耦合度。
* 处理器映射器 HandlerMapping（不需要程序员开发）
  * 作用：根据请求的url查找Handler。
* 处理器 Handler（需要程序员开发）
  * 注意：编写Handler时要按照HandlerAdapter的要求去做。
* 处理器适配器 HandlerAdapter
  * 作用：按照特定的规则（适配器要求的规则）去执行Handler。
* 视图解析器 ViewResolver
  * 作用：进行视图解析，将逻辑视图解析成真正的视图（View对象）。
* 视图 View（需要程序员开发jsp）
  * 一个接口，实现类支持不同的View类型（jsp、freemarker、pdf...）

## SpringMVC入门程序

### 环境准备

* 数据库环境
* Java环境
* Tomcat
* Spring（包括SpringMVC）

### 需求

* 以案例作为驱动。
* SpringMVC和MyBatis使用一个案例：商品订单。
* 功能需求：商品列表

### 配置前端控制器

* 在web.xml中配置。
* 本质上就是在配置bean。

### 配置处理器适配器

* 都在SpringMVC的配置文件中配置。
* 本质上就是在配置bean。
* 对应的类：`SimpleControllerHandlerAdapter`。
* 此适配器能执行实现了Controller接口的Handler。

### 编写Handler

* 需要实现Controller接口，才能由适配器执行。

### 编写视图

* 即是jsp页面。

### 配置Handler

* 本质上就是在配置bean。
* 将编写的bean在Spring容器中加载。
* 继承Controller接口，实现handleRequest方法，具有request和response参数。

### 配置处理器映射器

* 对应的类：`BeanNameUrlHandlerMapping`。
* 本质上就是在配置bean。

### 配置视图解析器

* 需要配置解析jsp的视图解析器。
* 对应的类：`InternalResourceViewResolver`。
* 默认使用jstl。

## 非注解的处理器映射器和适配器

简单映射器

```xml
<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
```

另一个映射器

```xml
<!--简单url映射器-->
<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
	<property name="mappings">
		<props>
			<!--对控制器进行url映射，同一个bean可以有多个地址映射-->
			<prop key="/day01/item/findAll.action">itemController</prop>
		</props>
	</property>
</bean>
```

多个映射器可以共存。前端控制器会判断url能让哪些url映射，就让正确的映射器处理。

### 非注解的适配器

```xml
<!--配置处理器适配器（所有的处理器适配器都实现了HandlerAdapter接口）-->
<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
```

另一个适配器

```xml
<bean class="org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter"/>
```

让控制器继承HttpRequestHandler：  
要实现的handleRequest方法没有返回ModelAndView，可以通过修改response定义响应内容，例如返回json数据。

### DispatcherServlet.properties

前端控制器会从上述文件中加载默认配置，包括处理器映射器、适配器、视图解析器等。

## 注解的处理器映射器和适配器

* 映射器
  * 在Spring3.1之前使用DefaultAnnotationHandlerMapping注解映射器。
  * 在Spring3.1之后使用RequestMappingHandlerMapping注解映射器。
* 适配器
  * 在Spring3.1之前使用AnnotationMethodHandlerAdapter注解适配器。
  * 在Spring3.1之后使用RequestMappingHandlerMapping注解适配器。

### 配置注解的映射器和适配器

* 可以在配置文件中配置，也可以在配置类中配置。
* 启用MVC的注解驱动，可以代替上面两个配置。
  * `<mvc:annotation-driven/>`
* MVC的注解驱动还默认加载了很多参数绑定方法，比如json转换解析器。
* 实际开发中使用后者	

### 开发注解处理器

* 使用注解的映射器和适配器。（必须配套使用）
* 使用`@RequestMapping`注解来配置控制方法映射的url。
* 使用组件扫描。


## 源码分析

（略）

## 入门程序小结

* 重点：通过入门程序理解SpringMVC前端控制器、处理器控制器、处理器适配器、视图解析器的用法。
* 前端控制器配置
  * 第一种：`*.action`
  * 第二种：`\ `
* 处理器映射器
  * （了解）非注解的处理器映射器
  * （掌握）注解的处理器映射器：对注有`@Controller`的类中注有`@RequestMapping`的方法进行映射。
  * 使用注解的映射器，搭配注有`@Configuration`的配置类，可以完全避免xml配置。
* 处理器适配器
  * （了解）非注解处理器适配器
  * （掌握）注解处理器适配器：需要搭配使用

## SpringMVC和MyBatis整合

### 需求

使用SpringMVC和MyBatis完成商品列表查询

### 整合持久层

SpringMVC+MyBatis的系统架构

* 表现层
  * SpringMVC
* 业务层
  * service接口/类 
* 持久层
  * MyBatis
* 数据库
  * MySQL

Spring将各层进行整合。  
通过Spring可以管理持久层的mapper（相当于dao接口），业务层的service（可以调用mapper接口）以及控制层的controller（可以调用service接口）。  
Spring在service层中进行事务控制
mapper、service、controller都是JavaBean。

* 整合dao层
  * MyBatis和Spring整合，通过Spring管理mapper接口。
  * 在配置类上使用`@MapperScan`注解。
* 整理service层
  * 通过Spring管理service接口/类。
  * 在配置类上使用`@ComponentScan`注解。
  * 实现事务控制。
* 整合SpringMVC
  * SpringMVC是Spring的模块，不需要整合。

### 准备环境

（略，maven真是方便！）

### 整合dao

* SqlMapperConfig.xml
* Spring配置文件/Spring配置类
* 生成实体类
* 手动定义商品查询mapper（接口和配置文件）

### 整合service

* 定义service接口
* 实现service类
* 配置事务管理（事务管理器，启用事务性，为service注上`@Transactional`）

### 整合SpringMVC

* 创建springmvc.xml文件（或者配置类），配置处理器映射器、适配器、视图解析器。

### 配置视图控制器

* 在xml中配置servlet：`DispatcherServlet`。

### 配置控制器

* 注有`@Controller`的类。

### 加载spring容器3

* 将mapper、service、controller等都加载到Spring容器中。
* 使用`@ComponentScan`和`@MapperScan`注解指定要加载的bean，参数为对应的java包。
* 使用Spring监听器或者实现WebApplicationInitializer接口，在服务器启动时加载。

## 商品修改功能开发

### 需求

* 进入商品查询列表页面
* 点击修改，进入商品修改页面，显示要修改的信息
  * 根据商品id进行查询
* 在商品修改页面，修改商品信息，修改后点击提交

### 开发mapper

* 根据id查询商品信息
* 根据id更新商品信息

### 开发service

* 根据id查询商品信息
* 修改商品信息

### 开发controller

* 商品信息修改页面显示
* 商品信息修改页面提交

## RequestMapping

* url映射
  * 定义controller方法对应的url，进行处理器映射。
* 窄化访问路径
  * 使用`@RequestMapping`的value或path属性。
  * 为了对url进行分类管理，可以在类上定义根路径。
  * 真正的路径即是根路径+子路径
  * 如：`/item`+`/findAll.action`
* 限制请求方法
  * 使用`@RequestMapping`的method属性。
  * 出于安全性考虑，对http的链接进行方法限制。

## controller方法的返回值

### 返回ModelAndView

* 可能需要添加数据。

### 返回String

* 表示返回逻辑视图名。
* 真正的视图（jsp路径）=前缀+逻辑视图名+后缀。
* 可以有一个Model类型的参数,用于添加数据。
  * 使用`model.addAttribute()`方法。
* 可以有一个request参数或（和）一个response参数。
* 重定向
  * 在返回值中加上`redirect:`前缀。
  * 地址栏改变，数据不共享。
  * 例如：商品修改提交后，重定向到商品查询列表。
  * 返回值例如：`"redirect:findAll.action"`，不需要加上根路径。
* 页面转发
  * 在返回值中加上`forward:`前缀。
  * 地址栏不改变，数据共享。 

### 返回void

略

## 参数绑定

### SpringMVC参数绑定类型

* 从客户端请求key/value数据，经过参数绑定，将数据绑定到Controller方法的形参上。
* 在SpringMVC上，接受页面提交数据，是通过方法形参接收。

* 处理器适配器调用参数绑定组件，将数据转成controller方法的形参。
* 参数绑定组件
  * 早期版本使用PropertyEditor（只能讲字符串转换成java对象）。
  * 现在使用Converter（可以进行任意类型的转转换）。
  * SpringMVC提供了很多converter。
  * 在特殊情况下需要自定义converter。
    * 例如，对日期数据的绑定

### 默认支持的类型

直接在controller方法形参上定义以下类型的参数，就可以使用这些对象。

* HttpServletRequest
* HttpRequestResponse
* HttpSession
* Model/ModelMap
  * 前者是接口，后者是接口实现
  * 作用：将模型数据填充到request数据中

### 简单类型

* 加上简单类型/简单类型封装类类型的参数，即可得到传入数据。  
* 传入数据和参数的名字默认需要相同。
* `@RequestParam`
  * 用于处理简单类型的绑定。
  * 如果不使用这个注解，则要求同名。如果使用，则可以不同名。
  * value属性：传入的请求参数的名称。默认同名。
  * required属性：是否必须，默认为true。
  * defaultValue属性：设置默认值。

### 实体类类型

* 类似简单类型
* 页面中的输入表单的name要与实体类类型参数的属性名字一致。
* 可以同时存在简单类型的参数和实体类类型的参数。
* 解决post乱码问题
  * 添加过滤器：CharacterEncodingFilter，设置初始化参数encoding为utf-8。
* 解决get乱码问题
  * 修改tomcat配置文件添加编码与项目编码一致。
  * `<Connector URIEncoding="utf-8"/>`
  * 或者：对参数进行重新编码
  * `String(req.getParameter("userName").getBytes("ISO8859-1"),"utf-8")`

### 自定义参数绑定

* 对于日期类型，需要自定义参数绑定。
* 将传入的日期数据串转成日期类型，要转化的日期类型和实体类中的对应类型保持一致。
* 需要将处理器适配器中注入自定义的参数绑定组件。

**第一种配置方式**

```xml
<beans>
	<mvc:annotation-driven conversion-service="conversionService"/>
	<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
		<property name="converters">
			<list>
				<bean class="com.windea.study.springmvc.main.CustomDateConverter"/>
			</list>
		</property>
	</bean>
</beans>
```

**第二种配置方法**

配置RequestMappingHandlerAdapter的bean，设置属性webBindingInitializer为customBinder。

**配置类的方式**

实现WebMvcConfigurer接口，重载addFormatters方法。

## SpringMVC和Struts2的区别

* SpringMVC基于方法开发，Struts2基于方法开发
* SpringMVC将url和controller方法进行映射。
  * 映射成功后生成handler对象，只包括一个方法。
  * 方法执行结束后，形参数据会被销毁。
  * controller可以设置成单例，也可以设置成多例。
* 而Struts2通过类的成员变量接收传入参数。
  * action只能设置成多例。
* SpringMVC的controller开发更接近于service开发。
* 经过实例测试，struts2速度慢，在于使用了struts标签。

------

# 第二天 SpringMVC的高级应用

* 包装类型pojo参数绑定
* 集合类型的参数绑定：数组、列表、图标
* 商品修改添加校验：学习SpringMVC提供校验
* 数据回显
* 统一异常处理
* 上传图片
* Json数据交互
* Restful支持
* 拦截器

## 包装类型的实体类参数绑定

### 需求

商品查询controller方法中实现商品查询条件传入

### 实现方式

* 第一种方法：在形参中添加request参数通过request接收查询参数。
* 第二种方法：在形参中让包装类型的实体类接收查询参数。
  * 页面传参特点：复杂、多样性。
  * 如果将查询条件全部放到实体类中，属性比较多，不好维护。
  * 建议使用包装类型的实体类，实体类中的属性是实体类。

### 页面参数和controller方法参数定义

* 页面定义：`<input name="item.name">`。
* controller方法定义：`public String findByConditions(ItemQuery query)`。
* ItemQuery类定义：包含一个item属性，item属性中包含有name属性。

## 集合类型的参数绑定

### 数组绑定

#### 需求

* 商品的批量删除。用户在页面选择多个商品，批量删除。

#### 表现层实现

* 将页面选择的多选的商品id，传到controller方法的参数。
* 页面定义：可多选的input标签，例如，`<input name="ids">`。
* controller方法定义：`public String deleteByIdBatch(Integer[] ids)`。

### 列表绑定

#### 需求

* 通常在需要批量绑定提交数据时，将提交的数据绑定到List<T>中。
* 例如：批量修改商品

#### 表现层修改

* 类似于批量修改，首先要进入到批量修改商品页面。
* 页面定义：`<input name="itemList[${vs.index}].name"/>`。
* controller方法定义：`public String updateByIdBatch(ItemQueryVo queryVo)`。
* 使用List来接收页面提交的批量数据时，需要在包装的实体类中定义一个List。

### 图表绑定

* 也是通过包装类型绑定。
* 页面定义：`<input name="itemMap['str'].name"/>`。
* controller方法定义：`public String updateByIdBatch(ItemQueryVo queryVo)`。

## 服务端校验

### 校验理解

项目中，通常使用较多的是前端校验，比如页面中的js校验。对于安全要求较高的，建议在服务端进行校验。

服务端校验：

* 控制层controller
  * 校验页面请求的参数的合法性。
  * 在服务端控制层的校验，不区分客户端类型（网页，移动端，远程调用）。
* 业务层service
  * 主要校验关键业务参数，限于接口中的关键参数
* 持久层dao
  * 一把是不进行校验的。

### SpringMVC校验

使用Hibernate的校验框架validation（和Hibernate没有关系）。

*  校验思路
   * 页面提交请求参数，请求到controller方法中，使用validation进行校验。
   * 如果校验出错，需要将错误信息显示到页面。
* 具体需求
  * 校验商品名称长度，生产日期的非空校验

### 环境

Maven：

```xml
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.1.0.Alpha2</version>
</dependency>
```

### 配置校验器

* 配置Validator的bean。
* 配置MessageSource的bean。 
* 添加自定义的validator。

```
/**
	 * 校验器的bean。
	 */
	@Bean
	public Validator validator(){
		var bean = new LocalValidatorFactoryBean();
		bean.setProviderClass(HibernateValidator.class);
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	/**
	 * 校验错误信息资源的bean。
	 */
	@Bean
	public MessageSource messageSource(){
		var messageSource = new ReloadableResourceBundleMessageSource();
		//配置校验信息的属性文件
		messageSource.setBasenames("message/validationMessages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(120);
		return messageSource;
	}
	
	/**
     * 配置校验器。
     */
    @Override
    public Validator getValidator() {
        return validator();
    }
```

### 在实体类中添加校验规则

```
@Size(min = 1, max = 30, message = "{Item.name.Size.error}")
private String name;

@NotNull(message = "{Item.name.NotNull.error}")
private LocalDateTime createTime;
```

### 在资源文件中编写错误信息

```
# message/validationMessages.properties
# 添加校验错误提交信息
Item.name.length.error= 请输入1到30个字符的商品名称
Item.name.NotNull.error = 请输入合法的日期
```

### 启用校验

* 在需要校验的（controller/service方法）的参数上添加`@Validated`注解。
* 为对应方法添加一个BindingResult类型的参数，接收错误信息。
* 要校验的参数和bindingResult必须前后成对出现。

### 捕获校验信息

* 判断是否有错误：`bindingResult.hasErrors()`
* 得到所有错误信息：`bindingResult.getAllErrors():List<ObjectError>`

### 在页面中显示校验信息

* 在controller方法中
  * 将错误信息添加到model参数。
  * `model.addAttribute("errors",errors")`
* 在页面中
  * 通过c:foreach标签进行遍历。
  * 可能需要对集合进行非空判断。
* 得到对象名：`bindingResult.getAllErrors().get(0).getObjectName()`
* 得到错误信息：`bindingResult.getAllErrors().get(0).getDefaultMessage()`

## 分组校验

### 需求

在实体类中定义校验规则，而实体类是被多个controller方法所共用，当需要不同的controller方法对实体类进行校验，但是每个需要不同的校验规则。

解决方法：

定义多个校验分组（其实是一个接口），分组中定义有哪些规则。每个controller方法使用不同的校验分组。

* 定义多个标识接口，不需要添加任何方法，作为分组接口。
* 在校验注解中，设置groups属性为一个或多个分组接口的class。
  * 例如：`@NotNull(message = "{Item.name.NotNull.error}",groups = ValidationGroup1.class)`。
* 在controller方法上，设置@Validated的value属性为一个或多个分组接口的class。
  * 例如：`@Validated("ValidationGroup1.class"")`

## 数据回显

### 什么是数据回显

将提交的数据回显到刚才提交的页面。

### 实体类数据回显方法

* 方法1
  * SpringMVC默认对标准键名的实体类数据进行回显。
  * 实体类数据传入controller方法中，SpringMVC自动将数据放到request域，key等于pojo类型（首字母小写）。
* 方法2
  * 使用`@ModelAttribute`可以指定将实体类数据回显到request中的key。
* 方法3
  * 使用`@ModelAttribute`将方法的返回值传回页面。
  * 可以基于controller方法的返回值，自动添加数据到request域。
  * 可用于在页面中得到静态数据。
* 方法4
  * 最直接的方法，调用`model.addAttribute(key,value)`方法。

### 简单类型的数据回显

* SpringMVC不自动对简单类型数据进行回显。
* 调用`model.addAttribute(key,value)`方法。

## 异常处理

### 异常处理思路

==【重要】==  

系统中的异常包括两类：预期异常和运行时异常，前者通过捕获异常从而获取异常信息，后者主要通过规范代码开发、测试通过手段减少运行时异常的发生。  

系统的dao、service、controller层的异常都向上抛出，最后由SpringMVC的前端控制器交由异常处理器进行异常处理。

SpringMVC提供全局异常处理器进行处理（一个系统只有一个）。

### 自定义异常类

略

### 全局异常处理器

* 全局处理器处理思路
  * 解析出异常类型
  * 如果是自定义异常，则直接取出异常信息，在错误页面显示。
  * 如果不是自定义异常，则构造一个自定义异常（未知错误）。可能：加上错误代码。
* 自定义异常处理器
* 配置自定义异常处理器的bean。（multipartResolver）
* 添加自定义异常处理器。

### 异常抛出

* 如果是与业务功能相关的异常，则在service层抛出
* 与业务功能没有关系的异常，建议在controller中抛出。

## 上传图片

依赖于`commons-upload.jar`。

### 需求

在修改商品信息页面，添加上传商品图片功能。

### SpringMVC对多部件类型的解析

在页面中提交`enctype="multipart/form-data"`的数据时，需要SpringMVC对multipart类型的数据进行解析。

### 创建图片的虚拟目录

* 可以是在本地，也可以是在远程，也可以是在web根目录下。
* 本地方式
  * 在tomcat的配置文件（`conf/server.xml`）中，进行如下配置：
  * `<context docBase=""D:\temp"` path="/image" reloadable="false"/>`
* 注意
  * 在图片虚拟路径中，一定要讲图片目录分级创建。以便提高io性能。

### 修改controller方法

* 增加一个MultipartFile类型的参数，参数名要对应。
* 写入到本地文件：`image.transfer(file)`
* 得到web根目录对应的真实目录：`servletContext.getRealPath(path)`。

## Json数据交互

### 为什么要进行json数据交互

json数据格式在接口调用中、html页面中较常用，json格式简单，解析方便。  
例如：webService接口，请求调用。

最终都输入json数据：为了在前端页面方便对请求结果进行解析。

### SpringMVC进行json交互

* 请求json，输出json
  * 要求请求的是json，需要在前端页面中将请求内容转成json。
* 请求kv，输出json
  * 此方法比较常用。

### 环境准备

SpringMVC默认使用MappingJacksonHttpMessageConverter对json数据进行转换。需要加入jackson的包（`jackson-core.jar`，`jackson-databinding.jar`）。

可以理解为@RequestBody和@ResponseBody使用上面两个包进行转换。

配置json转换器：在requestMappingHandlerAdapter中配置messageConverters属性。

如果使用注解驱动，则不需要进行配置。

### @RequestBody和@ResponseBody

**@RequestBody**

作用：用户读取http请求的内容（字符串），通过SpringMVC提供的HttpMessageConverter接口，将读取到的内容转换为json、xml等格式的数据并绑定到controller方法的参数上。

将json串转化成java对象。

**@ResponseBody**

将java对象转成java对象。

* 将json对象字符串，转化为正确的java对象，返回js对象
  * 数据传递：`data: '{"name": "Iphone", "price": 123}'`
  * 数据接收：`@ResponseBody public Item requestJson(@RequestBody Item item)`
  * 数据返回：data，类型：Object，属性：name:"Iphone",price:123,其他可读属性...
* 将json数组字符串，转化为java数组/列表，返回js数组
  * 数据传递：`data: '["Iphone", "Android"]'`
  * 数据接收：`@ResponseBody public List<String> requestJson(@RequestBody List<String> list)`
  * 数据返回：data，类型：Array\[2]，元素："Iphone","Android"
  * **注意：尽量指定泛型**
* 将json对象字符串，转化为java映射，返回js对象
  * 数据传递：`data: '{"name": "Iphone", "price": 123}'`
  * 数据接收：`@ResponseBody public Map<String,Object> requestJson(@RequestBody Map<String,Object> item)`
  * 数据返回：data，类型：Object，属性：name:"Iphone",price:123
  * **注意：尽量指定泛型**

* `JSON.stringify()`：将js对象转换为json字符串
* `JSON.parse()`：将json字符串转换为js对象

## RESTful支持

### 什么是RESTful

RESTful是目前最流行的一种互联网软件架构，它结构清晰、符合标准、易于理解、扩展方便，所以正得到越来越多的网站的采用。

RESTful相当于一种开发理念，是对http的一个很好的诠释。

Representational State Transfer，表现层状态转化

* 资源：网络上的一个实体。
* 表现层：资源表现出来的形式。
* 状态转化：通过状态转化（GET，POST，PUT，DELETE）来操作资源。

* 对url进行规范，协程RESTful格式的url。
  * 非REST的url：`http://.../queryItems.action?id=001`
  * REST的url：`http://.../item/001`
  * 特点，url简洁，将参数通过url传到服务端。
* 不管是删除、添加、更新，使用的url都是一致的，如果要进行删除，需要设置方法为DELETE。
* 后台controller方法：判断http方法，POST则添加，DELETE则删除。
* 请求时指定contentType。
* 实际使用的时候，只考虑url规范。

* 综合理解
  * 每一个url表示一种资源
  * 客户端和服务器端之间，传递这种资源的某种表现层
  * 客户端通过四个HTTP方法，对服务器端资源进行操作，实现表现层状态转化。

### REST的例子

#### 需求

查询商品信息，返回json数据

### 编写controller

* 准备工作：配置一个rest的前端控制器，或者添加ServletMapping（`"/"`）。

* 定义方法，进行url映射，使用REST风格的url，将查询商品信息的id传入controller
* 输出json，使用@ResponseBody注解。
* 使用`@PathVariable`将controller方法参数绑定到`@RequestMapping`的url中。
  * 例如：`@RequestMapping("/rest/item/{id}")`
  * 如果是Map类型的参数，则绑定其中所有键值对。

### 对静态资源的解析

* 如果在前端控制器的url-pattern中添加`"/"`，对静态资源的解析会出现问题。
* 解决方案：在SpringMVC配置文件中添加静态资源的解析方法。
  * 使用addResourceHandlers()方法进行配置。

* `?`：匹配一个字符
* `*`：匹配多个字符
* `**`：匹配多个字符（可以是多级目录）
* `{spring:[a-z]+}`匹配正则表达式`[a-z]+`并作为一个名为spring的路径变量。

## 拦截器

### 拦截器定义

定义拦截器，实现HandlerInterceptor接口。

其中包含3个方法：

* preHandle(request,response,handler)
* postHandle(request,response,handler,mv)
* afterCompletion(request,response,handler,ex)

### 连接器配置

SpringMVC拦截器是针对handlerMapping进行拦截设置。  
如果在某个handlerMapping中配置拦截器，经过该handlerMapping，且映射成功的handler才会使用该拦截器。

* 配置beanNameUrlHandlerMapping的interceptors属性。

配置类似全局的拦截器（将配置的类似全局拦截器注入到每个handlerMapping中）：  

* 编写mvc:interceptors标签。

或者：

* 重载`addInterceptors()`方法。

### 总结

两个拦截器都放行：先放行的在handler方法执行结束时后拦截。
任意一个拦截器不放行：postHandle()不会执行。

* 统一日志拦截器
  * 该拦截器器的preHandle方法必须执行，且处于拦截器链中的第一个位置。
* 登录认证拦截器
  * 放在统一日志拦截器之后
* 权限认证拦截器
  * 放在登录认证拦截器之后。

### 拦截器应用：实现登录验证

#### 需求

* 用户请求url
* 拦截器进行拦截校验
  * 如果请求的url是公开地址，不需要登录即可访问，则放行
  * 如果用户session不存在，则跳转到登录页
  * 如果用户session存在，则放行

#### 实现

* 编写controller方法
  * 登录方法：注入session参数，传递username和password参数。如果登陆成功，则为session添加username属性，并返回到指定的页面。
  * 登出方法：注入session参数，清空session的username属性，然后重定向到首页。
* 编写拦截器
  * 重载preHandle方法，如果在session中 没有找到username，则准备跳转（用原始方式？）并返回false。
* 配置拦截器
  * 匹配需要进行登录验证的页面。
