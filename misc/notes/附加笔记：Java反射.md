反射：将类的各个组成部分封装为其他对象，这就是反射机制
Java代码在计算机中经历的三个阶段，在一到二阶段时，便是将字节码的成员变量封装成Filed对象，构造方法封装成Constructor对象，成员方法封装成Method对象。这就是反射机制

好处：
	1、可以在程序的运行过程中去操作这些对象（比如定义一个字符串，当这个字符串后写个点时就会有一大堆的方法提示，那么是怎么知道有这些方法的呢？  当我们定义了这个字符串，这个字符串字节码文件就会被加载进内存，在内存中有一个Class类对象，Class类对象中已经把所有的方法都抽取出来封装为Method对象，然后把所有的方法都放到了一个Method[]数组里，当要提示时，只需要把Method数组里的每一个数组成员拿出来，把它们的名字展示出来就可以了    这就是一个反射机制  idea一直在运行过程中就能知道定义的字符串对象里有哪些方法）
	2、可以解耦，提高程序的可扩展性


获取Class对象的方式：
	1、Class.forName("全类名");将字节码文件加载进内内存，返回Class对象（第一阶段）（多用于配置文件，将类名定义在配置文件中。读取文件，加载类）
	2、类名.class：通过类名的属性class来获取（第二阶段）（多用于参数的传递）
	3、对象.getClass();getClass()方法在Object类中定义的。（第三阶段）（多用于对象的获取字节码的方式）

	结论：同一个字节码文件（*.class）在依次程序的运行过程中智慧被加载一次，不论通过哪一种方式获取的class对象都是同一个


Class对象功能：
	1、获取功能：
		1、获取成员变量们：Field[] getFields()  Field[] getFields(String name)
		2、获取构造方法们：
		3、获取成员方法们：String getName()获取方法名
		4、获取类名：


案例：
	需求：写一个框架，不能改变该类的任何代码，可以创建任意类的对象，可以执行任意方法
	实现：
		1、配置文件
		2、反射
	步骤：
		1、将需要创建的对象的全类名和需要执行的方法定义在配置文件中
		2、在程序中加载读取配置文件
		3、使用反射技术来加载类文件进内存
		4、创建对象
		5、执行方法


改java代码会造成代码一旦改了就需要重新上线，但是改配置文件，他是一个物理文件，不会影响，配置文件中如果用了全类名，那么第一时间应该反应过来用的反射机制