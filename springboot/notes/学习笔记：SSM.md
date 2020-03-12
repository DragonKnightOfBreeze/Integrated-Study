* 51job
* 智联招聘
* BOSS直聘

## 软件公司岗位

* 业务员（销售）
* 项目经理（PM：项目进度，与客户沟通需求，写需求文档）
* 架构师
* 程序员
* UI
* 前端
* 测试工程师
* 功能测试（经验）
* 性能测试（代码）
* 运维工程师/实施（Linux）

## 找工作环节

* 网上编写好简历（设置成公开，但把自己的公司屏蔽）
* 搜索相关职位（看清楚任职要求、掌握技术）
* 投递简历（切记一份简历进行海投，适当针对性地改一下）
* 帐号或手机短信受到面试的offer
* 查看公司的基本信息，做好面试准备（面试题要到网上看看）
* 笔试：技术面试、人事面试（考察谈吐能力，谈待遇）

## 常见生命周期

* servlet生命周期
* spring bean生命周期 

* 一般情况下不作整表数据查询，都会做用户展示数据。
* 因为用户绝大多数情况下，都是看第一页的内容。

* 在实际开发中很少删除数据。
* 在数据库的服务器上写一个定时任务，凌晨三点左右自动执行数据库备份。
* 有些管理系统存在用户误操作。

* 写程序就是先模仿后创新。

## Spring Mvc控制器的使用方法

控制器方法的可选注解：

* @RequestMapping 声明地址、请求方法、查询参数等
* 注有@RequestMapping的注解 特定请求方法的地址映射，例如@GetMapping
* @ExceptionHandler 用于处理错误，例如：`@ExceptionHandler(MyException.class) public ResponseBody<?> handle(MyException e)`
* @ResponseStatus 声明返回的状态码
* 其他通用的注解

控制器方法的可选参数类型：

* @RequestParam 对于基本类型、数组、列表、映射，查询参数，得到表单数据，一般用于get请求
* @RequestBody 对于列表、映射、实体类，得到响应体中的数据，一般使用json格式传入，一般用于post请求
* @RequestHeader 得到头部信息
* @RequestPart 得到multipart/form-data类型的表单数据，文件可以直接使用`@RequestParam MultipartFile file`得到
* @CookieValue 
* @PathVariable 路径变量，用于构造restful风格的url，如：/user/{id}
* @MatrixVariable 矩阵变量，类似路径变量，如：/user;id=1,role=ADMIN;
* request，response，session servlet原生对象
* Model 得到模型对象，据此得到模型特性
* @ModelAttribute 模型特性
* @RequestAttribute
* @SessionAttribute
* Errors，BindingResult 跟在要验证的输入参数后面，以得到参数验证信息，之前的输入参数需要注上@Valid
* Principal 整合spring security，用于得到当前用户信息
* 其他不常用的带注解参数或者特定类型参数
* 不带注解的基本类型参数默认视为@RequestParam，实体类参数默认视为@ModelAttribute

控制方法的可选返回值：

* void 用于api调用，返回200
* any type 用于api调用，当使用@RestController时，返回的类型即为返回体中数据的类型。
* ResponseEntity 用于api调用，返回带有状态码、头部信息、响应体的封装对象
* @ResponseBody 注明使用json格式返回数据，一般直接在控制器类上添加@RestController替代
* ModelAndView 模型视图封装对象，包含模型特性、跳转地址等
* Model
* View
* String 跳转地址，可选`redirect:`、`forward:`前缀，默认转发
* 其他不常用的返回类型
