# Maven基础

**Maven：**Apache组织的开源项目，项目构建工具。

## Maven的好处

同样的代码，实现相同的功能，Maven项目的占用空间可以显得非常小。  
Maven项目中没有jar包。  
jar包的坐标：公司名称/组织名称+项目名称+版本信息。
Maven项目查找jar包的过程：根据jar包的坐标，到Maven本地仓库中查找。

## Maven的好处如何实现

Maven的两大核心：

**依赖管理：**就是对jar包进行统一管理的过程。  
**项目构建：**项目在编码完成后，对项目进行编译、测试、打包、部署。

通过Maven命令将web项目发布到tomcat：  
（项目目录）`mvn tomcat:run`

## Maven的安装、配置本地仓库

**Maven的安装：**

前提条件：需要jdk。  
解压到本地磁盘，解压目录不要有中文。  
安装后需要配置相关的环境变量。

* `MAVEN_HOME`或`M2_HOME`：Maven安装目录。
* `PATH`：Maven安装目录下的bin子目录。

另外也需要配置jdk的环境变量（`JAVA_HOME`）。

**配置本地仓库：**

* 本地仓库：在个人笔记本上
* 私服：存在于本地的局域网内的一台服务器，存储jar包
  * 前提：安装私服
* 中央仓库：在互联网上，基本上存放了所有的开源jar包
  * 由Maven团队维护

本地仓库的默认位置：`C:\User\${Username}\.m2\repository`。  
打开`/conf/settings.xml`，编辑`<localRepository>`标签，最好是绝对路径。

## Maven项目的标准目录结构

```
- src        源目录
	- main      主目录
		- java      Java代码目录
		- resources 资源目录，例如配置文件
		- webapp    Web项目根目录，包括页面、页面素材、css、js等
	- test      测试目录
		- java       Java测试代码目录
		- resources  测试资源目录
- target     输出目录
* pom.xml    Maven项目的核心配置文件 
```

## Maven的常用命令（应用）

* 清理：`mvn clean`
  * 清理`/target`目录。
* 编译：`mvn compile`
  * 编译Java文件到``/target/classes`目录。
* 单元测试：`mvn test`
  * 首先进行编译，然后默认测试`src/test/java`下面的所有测试方法。
  * 单元测试类的类名必须以`Test`结尾。
* 打包：`mvn package`
  * 首先进行编译，然后进行测试，然后打包到`/target`目录。
  * 根据项目类型，打不同的包。
* 安装（发布到本地仓库）：`mvn install`
  * 首先进行编译、测试、打包，然后将jar包发布到本地仓库

## Maven的生命周期

在Maven中存在三套生命周期，每一套生命周期都是相互独立的，互不影响。  
在一套生命周期内，执行后面的命令，前面的命令都会执行

* CleanLifeCycle：清理生命周期
  * clean
* DefaultLifeCycle：默认生命周期
  * compile,test,package,install,deploy
* SiteLifeCycle：站点生命周期
  * site

## 创建Maven项目，查找依赖

**创建maven项目**

* Group Id
* Artifact Id
* Version
  * SNAPSHOT 快照/测试版本，RELEASE 发行版本
* Packaging
  * jar Java工程，war Web工程，pom 父工程

**查找依赖**

```
<dependencies>
	<dependency>
		<groupId>windea</groupId>
		<artifactId>utility</artifactId>
		<version>1.0</version>
	</dependency>
</dependencies>
```

* 每个依赖的scope属性（依赖范围）
  * compile：参加编译、测试和运行，例如`spring-core`
    * 默认的，代表部署到tomcat
  * test：参加测试和运行，例如`junit`
  * provided：参加编译和测试，例如`servlet-api`
    * 例如，部署到tomcat后就不需要了
    * 如果使用到tomcat自带的jar包，一定要标记为provided
  * runtime：参加测试和运行，例如`jdbc驱动`
  * system：参加编译和测试，本地的

## Maven概念模型

依赖管理

项目构建

`pom.xml`文件：

* 本项目的坐标信息
* 本项目的jdk编译版本信息
* 本项目的依赖的坐标信息

------

# Maven实战

## 传递依赖的冲突解决（了解）

传递依赖：A依赖于B，B依赖于C。C就是A的传递依赖，B就是A的直接依赖。  
导入依赖D，D依赖于C，但版本不同。

**Maven自己的调解原则**

* 第一声明者优先原则
  * 看谁先在`pom.xml`中声明
* 路径近者优先原则
  * 直接依赖级别高于传递依赖
* 排除依赖
  * 在每个依赖中添加配置：

```
<exclusions>
	<exclusion>
		...
	</exclusion>
</exclusions>
```

* 版本锁定
  * 在`pom.xml`中添加配置：
  * 并不会真正导入依赖

```
<dependencyManagement>
	<dependencies>
		<dependency>
			...
		</dependency>
	</dependencies>
</dependencyManagement>
```

**提取常量**

```
<properties>
		<spring.version>5.1.4.RELEASE</spring.version>
</properties>

<dependencies>
	<dependency>
    	<groupId>org.springframework</groupId>
    	<artifactId>spring-context</artifactId>
    	<version>${spring.version}</version>
    </dependency>
</dependencies>
```

## 通过Maven整合SSH框架（重点）

**搭建Struts2环境**

* 创建Struts2配置文件：struts.xml
* 在web.xml中配置struts2的核心过滤器：Struts2PrepareAndExecuteFilter

**搭建Spring环境**

* 创建Spring配置文件：applicationContext.xml
* 在web.xml中配置监听器：ContextLoadListener
* 通过上下文参数指定Spring配置文件路径：`<context-param>`

**搭建Hibernate环境**

* 创建hibernate核心配置文件：hibernate.cfg.xml

**整合Struts2和Spring**

* 整合关键点：
  * action对象的创建，交给spring创建

* 创建action类
* 将action对象配置到spring配置文件中
  * 注意设置范围为prototype
* 在struts.xml中，在action节点中，将class属性配置为spring工厂中的bean的id

**整合Spring和Hibernate**

* 整合关键点：
  * 数据源dataSource的创建交给spring
  * sessionFactory的创建交给spring
  * 配置模版
  * 配置事务管理

* 在spring配置文件中配置dataSource的bean
  * 注意注入数据库连接信息
* 在spring配置文件中配置sessionFactory的bean
  * 注意注入数据源和核心配置文件(configLocations)
* 在spring配置文件中配置事务管理器的bean
  * 注意注入sessionFactory
  * jdbc：使用DataSourceTransactionManager
  * hibernate：使用HibernateTransactionManager
* 在spring配置文件中配置HibernateTemplate的bean
  * 注意注入sessionFactory
* 通过xml或者注解的方式管理事务
  * xml方式：配置增强（匹配业务中的方法），然后配置aop
  * xml开启注解驱动：`<tx:annotation-driven transaction-manager="...">`
  * 为查询操作开启只读

* 加载属性文件：

```
<context:property-placeholder location="classpath:db.properties"/>
//之后使用${propName}来取得属性文件中的属性值
```

## 通过Maven对项目进行拆分、聚合（重点）

对已有的maven项目拆分。

* 拆分思路：
  * 将dao层的代码和配置文件全部拆分，拆分到一个表面上独立的工程上面。
  * 同样也对service、action进行拆分。
  * 拆分后的工程实例：`SSHDemo-Dao`、`SSHDemo-Service`、`SSHDemo-Web`

拆分完成后还要进行聚合。引出父工程的概念。

* 创建maven父项目时，需要注意配置package为pom。
* 创建maven模块时，需要注意配置父项目的信息。

创建好的maven父项目的目录结构，可以只有pom.xml，可以推断不进行编码。

* 项目需要的依赖信息，在父项目中定义，子模块继承父项目。
* 将各个子模块聚合到一起。

**将创建的父项目发布到本地仓库**

将来service、dao工程发布到本地仓库，发布的service工程会报错。  
如果忘记此步骤，需要把父项目发布到本地仓库。

**创建SSHDemo-Dao**

* 负责数据访问层，包括相关的代码和配置文件。
* 拆分Spring配置文件，分为基础的和dao层的。

**创建SSHDemo-Service**

* 在pom.xml文件中，添加对dao层的依赖
  * 可以依赖于本地仓库的jar包，也可以直接依赖于其他的pom.xml文件

**创建SSHDemo-Web**

* `classpath*:`前缀：匹配所有模块的classpath

**单元测试**

* 批量加载spring配置文件
  * `classpath:spring/applicationContext-*.xml`
  * `classpath*:`：匹配所有模块的classpath

**传递依赖的范围**


A依赖于C的范围：

| 直接依赖\传递依赖 | compile  | provided | runtime  | test |
|:-----------------|:---------|:---------|:---------|:-----|
| compile          | compile  | -        | runtime  | -    |
| provided         | provided | provided | provided | -    |
| runtime          | runtime  | -        | runtime  | -    |
| test             | test     | -        | test     | -    |

总结：当项目中需要的某一个依赖没有传递过来，在项目中自行添加即可。

**运行方式**

方式1：运行父项目。父项目将各个模块聚合到一起，将`SSHDemo-Web`打包成war包，发布到tomcat。

方式2：直接运行`SSHDemo-Web`项目。

其他方式：通过IDE部署到tomcat。

## 私服的应用（了解）

**私服安装（了解）**

* 下载安装包（nexus）
* 解压安装到本地磁盘（目录最好不要有中文）
* 在bin目录下执行命令（管理员）：`nexus install`
* 启动服务（命令行，或者任务管理器）
* 找到私服的访问路径：在`/config/nexus.properties`里面找到
  * 例如：`http://localhost:8081/nexus/#welcome`
* 登录
  * 默认用户/密码：`admin/admin123`

## 私服仓库类型

* Hosted 宿主仓库：存放本公司开发的jar包
  * Releases：发行版，正式的
  * Snapshots：快照版，测试的
  * ThirdParty：第三方的，存在版权问题的（如Oracle）
* Proxy 代理仓库
  * Central：代理中央仓库的jar包
  * Apache Snapshots：代理Apache下测试版本的jar包 
* Group 组仓库
  * 包含Hosted仓库，和Proxy仓库

## 上传jar包到私服（应用）

* 在maven安装目录下的`/conf/settings.xml`文件中，配置用户名和密码。

```xml
<settings>
	<server>
		<id>Releases</id>
		<username>admin</username>
		<password>admin123</password>
	</server>
	<server>
        <id>Snapshots</id>
        <username>admin</username>
        <password>admin123</password>
    </server>
</settings>
```

* 在将要上传的项目的pom.xml中，配置jar包的上传路径

```xml
<project>
	<distributionManagement>
		<repository>
			<id>Releases</id>
			<url>http://localhost:8081/nexus/content/repositories/releases</url>		
		</repository> 
		<repository>
	        <id>Releases</id>
	        <url>http://localhost:8081/nexus/content/repositories/snapshots</url>		
	    </repository>   
	</distributionManagement>
</project>
```

* 执行命令发布项目到私服（上传）：`mvn deploy`

## 下载jar包到本地仓库（应用）

* 在maven安装目录下的`/conf/settings.xml`文件中，配置模版

```xml
<settings>
	<profiles>
		<profile>
			<id>dev</id>
			<!--仓库-->
			<repositories>
				<repository>
					<id>nexus</id>
					<!--仓库地址-->
					<url>http://localhost:8081/nexus/content/groups/public/</url>
					<!--是否下载releases构件-->
					<releases>
						<enabled>true</enabled>
					</releases>
					<!--是否下载snapshots构件-->
					<snapshots>
						<enabled>true</enabled>
					</snapshots>
				</repository>
			</repositories>
			<!--插件仓库，maven的运行依赖插件，也要从私服下载-->
			<pluginRepositories>
				<pluginRepository>
					<id>public</id>
					<name>Public Repositories</name>
					<url>http://localhost:8081/nexus/content/groups/public/</url>
				</pluginRepository>
			</pluginRepositories>
		</profile>
	</profiles>
</settings>
```

* 在maven安装目录下的`/conf/settings.xml`文件中，激活模版

```xml
<settings>
	<activeProfiles>
		<activeProfile>div</activeProfile>
	</activeProfiles>
</settings>
```

## Maven的优点

* 不再需要拷贝jar包，项目中不需要存放jar包，导致项目源代码变小。
* 使用maven开发的项目，如果环境统一，导入别的maven项目不会报错。
* 通过拆分项目，代码耦合度进一步降低。
* 方便项目升级。
* 节省人力成本。

------

# 拓展 问题解决

## 配置项目的Java版本（为11） 

```
<properties>
	<maven.compiler.source>11</maven.compiler.source>
	<maven.compiler.target>11</maven.compiler.target>
</properties>
```

## maven-javadoc插件日志乱码问题解决：

* 设置环境变量  
* 变量名：JAVA_TOOL_OPTIONS  
* 变量值：-Dfile.encoding=UTF-8  

## maven-javadoc禁用docLink：

* 首先在`pom.xml`中为maven-javadoc-plugin进行如下配置。  
* 然后使用`javadoc:jar`命令即可正确生成项目的javadoc的jar包。

```
<build>
	<plugins>
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-javadoc-plugin</artifactId>
			<version>3.0.1</version>
			<configuration>
				<doclint>none</doclint>
			</configuration>
		</plugin>
	</plugins>
</build>
```
