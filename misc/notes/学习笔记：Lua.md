# Lua初级  

## 引言  

## Lua语言与热更新简述  

lua的发展历史：  
巴西大学的研究项目，设计目的是嵌入到应用程序中，提供灵活的扩展和定制功能。lua是由C语言编写而成，是1993年完成开发，lua最新是5.3版本，但是版本提升速度很慢。  

lua很方便的与其他语言集成，lua无需编译，利于集成与拓展。（C#/Java/C++等）  

## 热更新原理  

因为C#属于编译型语言无法热更新，传统技术上游戏更新，需要下载整个下载包，游戏体验较差。  

如何利用lua来热更新：  
在游戏客户端嵌入lua的解析器，因为lua无需编译，可以在任何操作系统运行，利于“热更新”。对于C#来说因为必须编译成dll等，所以C#语言不能在手机端完成编译，所以必须重新下载。  

热更新技术类别：  

* 使用lua脚本实现  
* 使用C# light  
* 使用C#反射技术（苹果平台也不支持）（基础的例外）  

静态编译和动态编译  

lua的解析技术  

* [ulua](http://ulua.org)，[nlua](http://nlua.org)，UniLua，sLua等  
* tolua技术（目前公司在用的多）  
* xlua技术（腾讯维护的技术）  

lua热更新学习路线：  

* 首先学习lua语言编程。  
* 学习xlua等热更新技术，实现lua与C#的嵌入开发。  
* 学习AssetBundle进行资源更新。  
* xlua技术嵌入到商业项目，再配合AssetBundle框架进行商业开发。  

lua5.3参考手册：[www.runoob.com](https://www.runoob.com/manual/lua53doc/manual.html)  

## 基础知识  

### 标识符与关键字  

lua标识符：  
以字母或下划线开头，数字不能作开头，不允许使用特殊字符。区分大小写。  
避免使用“下划线+字母”，因为系统使用。  

lua保留关键字：（略）  

推荐命名规则：  
常量：使用全大写和下划线，如：`MY_ACCOUNT`  
变量：第一个字母小写，如：`strNumber`  
全局变量：第一个字母使用小写的g表示，如：`gMyAccount`  
函数名：第一个字母大写，如：`function MyFunction`  

### 变量  

变量不需要在使用前声明，且不需要指定类型（string，number等）。  
变量都是弱类型，类似JavaScript语言，无需指定变量类型。  
lua中语句是否分号结尾都可以正常运行。  
`print()`是lua中内置的方法。  
双引号和单引号都可以表示字符串。  
变量可以直接使用，无需定义。  
变量的类型可以随时改变。  
nil关键字可以对变量起到销毁的作用（清空变量所占空间）  

### 注释  

使用`--`进行单行注释。  
使用`--[[`和`--]]`进行多行注释。只有一行的情况下使用`--[[`和`]]`。  

### 使用type得到变量类型  

使用type关键字，可以显示变量的类型，类似C#中的GetType()。  
例子：`age = 100;	print(type(age));`  

### 范例代码  

```lua  
--[[基础知识]]  

--使用`--`进行单行注释。  
--使用`--[[`和`--]]`进行多行注释。  
abc = "abc"             --声明一个变量并赋值，每行语句不需要以分号结尾  
abc = 20                --变量的类型可以随时改变  
print(type(abc))        --打印变量的类型，结果为：number  
abc = nil			    --通过空类型关键字，销毁一个变量  
```  

## 数据类型与运算符  

### 数据类型  

nil，空类型，等同于C#的null，可以用于释放资源  
boolean，布尔类型，规定除了nil与false以外都表示“真”。  
string，字符串类型，可以使用双引号，也可以使用单引号。  
number，数字类型，注意lua中没有整数类型。  
table，表类型，表示一个集合，索引从1开始。（lua没有类的概念，lua的面向对象就是用table实现的）  
function，函数类型，表示由lua（或者C）编写的函数。  
userdata，用户数据类型，表示任意存储在变量中的C数据类型。  
thread，协程类型，表示执行的独立线程（伪多线程，协程）。  

### 运算符  

算术运算符：`+`、`-`、`*`、`/`（除）、`//`（整除）、`%`（取模）、`^`（次方）  
lua中没有自增和自减运算符。  

关系运算符：`<`、`<=`、`>`、`>=`、`==`、`~=`（不等于）  
注意：nil只与自身相等，对于table、function、userdata是作引用比较的，即只有当它们引用同一对象使才认为其相等。  

逻辑运算符：`and`（逻辑与）、`or`（逻辑或）、`not`（逻辑非）  
规定除了nil和false以外，其他的值都为真。  
短路规则：不做多余的判断（例如：对于逻辑与运算，有假为假，全真为真）  

其他：`=`（赋值）、`..`（字符串连接）  

### 全局与局部变量  

lua变量默认都是全局的，定义局部变量需要要local关键字修饰。  
lua语句块（if，for等）与函数中，定义的变量默认也是全局变量。  
lua可以直接使用`do ... end`来定义一个语句块。  
如果要删除全局变量，只需要给变量赋值nil。  

### 范例代码  

```lua  
--[[数据类型与运算符]]  

boo = true          --布尔类型变量  
str = "你好"         --字符串类型变量  
num = 123           --数字类型变量  
--表类型变量  
numTable = {1,2,3}  
--函数类型变量  
function myFunction()  
    print("这是一个函数")  
end  

--算术运算符：`+`、`-`、`*`、`/`（除）、`//`（整除）、`%`（取模）、`^`（次方）  
--lua中没有自增和自减运算符。  
--  
--关系运算符：`<`、`<=`、`>`、`>=`、`==`、`~=`（不等于）  
--注意：nil只与自身相等，对于table、function、userdata是作引用比较的，即只有当它们引用同一对象使才认为其相等。  
--  
--逻辑运算符：`and`（逻辑与）、`or`（逻辑或）、`not`（逻辑非）  
--规定除了nil和false以外，其他的值都为真。  
--短路规则：不做多余的判断（例如：对于逻辑与运算，有假为假，全真为真）  
--  
--其他：`=`（赋值）、`..`（字符串连接）  

--lua的多重赋值  
num1,num2,num3 = 1,2,3      --左边多，则多的变量为nil；右边多，则舍去  

globalNum = 1               --全局变量  
local localNum = 1          --局部变量（如果定义在语句块内，在语句块外不能访问）  

--一个自定义的语句块  
do  
    print("这是一个语句块！")  
end  
```  

## 流程控制语句  

### if条件语句  

```lua  
--单分支语句  
if(exp) then  
    --[body]  
end  
--双分支语句  
if(exp) then  
    --[body]  
else  
    --[body]  
end  
--多分支语句  
if(exp) then  
    --[body]  
elseif(exp2) then  
    --[body]  
else  
    --[body]  
end  
```  

### while循环语句  

```lua  
--先判断条件  
while(exp) do  
    --[body]  
end  
--先执行一次语句块中的代码  
repeat  
    --[body]  
until(exp)  
```  

### for循环  

```lua  
--开始的值，结束的值，间隔（默认为1）  
--注意：不能用括号括起这些参数  
for i = iStart,iEnd,iSep do  
    --[body]  
end  
```  

### ipairs和pairs关键字  

```lua  
--泛型for循环语句，遍历形似列表的表  
for i,v in ipairs(table) do  
   --[body]   
end  
--泛型for循环语句，一般用于遍历形似字典的表（键也可以是索引）  
--k、v可以由任何字母或合法的关键字代替  
for k,v in pairs(table) do  
   --[body]   
end  
```  

### 流程控制关键字  

循环中断关键字：`break`  
注意：lua中没有continue关键字语法。  

lua标准库中提供了几种迭代器：  

* 迭代文件每行的`io.lines`；  
* 迭代table中的元素（形似列表）的`ipairs`；  
* 迭代table中的元素（形似字典，或者将索引作为键）的`pairs`；  
* 迭代字符串中单词的`string.gmatch`；  
* ……  

### 范例代码  

```lua  
--[[流程控制语句]]  

--if条件语句  
if(num == 1) then  
    print("num等于1")  
elseif (num == 2) then  
    print("num等于2")  
else  
    print("num等于其他的值")  
end  

--while循环语句  
i=1  
while(i <=10) do  
    print(i)  
    i = i+1  
end                --先判断条件  
--repeat...until(exp)           --先执行一次语句块中的代码  

--for循环语句  
--相关参数：开始的值，结束的值，间隔（默认为1）  
for i = 1,10 do  
    print(i)  
end  

--泛型for循环语句，遍历形似列表的表  
--i是索引，v是值  
myArray1 = {1,2,3}  
for i, v in ipairs(myArray1) do  
    print(i.."\t"..v)  
end  

--泛型for循环语句，一般用于遍历形似字典的表（键也可以是索引）  
--k是键，v是值  
--k、v可以由任何字母或合法的关键字代替  
myArray2 = {item1 = 1,item2 = 2,item3 = 3}  
for k, v in pairs(myArray2) do  
    print(k.."\t"..v)  
end  

--循环中断关键字：break  

--lua标准库中提供了几种迭代器：  
--迭代文件每行的`io.lines`；  
--迭代table中的元素（形似列表）的`ipairs`；  
--迭代table中的元素（形似字典，或者将索引作为键）的`pairs`；  
--迭代字符串中单词的`string.gmatch`；  
--……  
```  

## 函数  

### 定义函数  

```lua  
--函数的格式  
function funName(para1,para2,...)  
	--[body]  
end  
```  

lua函数的基本性质：  

* 函数无需定义返回类型，可以返回任意类型、任意数量的数值。  
* 函数的参数，无需定义参数类型。在运行时才能发现相关的异常。  
* 函数无需大括号。  
* 可以定义变量，把函数直接赋值给它，从而得到相同的功能。  

### 函数中全局与局部变量，局部函数  

lua中定义的变量默认是全局变量，在函数中的也是如此。  
在函数中定义的全局变量，在外部照样可以访问。  
函数可以声明为全局的或局部的，默认是全局的。  
局部函数中的都是局部变量。  

### 函数的多返回值  

函数可以返回多个值，且返回的值的类型是任意的。  
返回多个值的场合，用多个变量接受。  

### 函数的赋值与匿名函数  

函数可以为变量赋值，也可以作为参数进行传递（相当于C#的委托）。  

匿名函数定义：没有函数名称的函数。  
匿名函数，只有通过赋值给一个变量（相当于C#的委托注册方法），我们通过调用这个函数变量来间接调用这个匿名函数。  

### 范例代码  

```lua  
--[[函数]]  

--定义一个函数  
function myFun1(para1)  
    print("这是一个函数，参数：")  
    para1Str = tostring(para1)  
    return para1Str  
end  
--调用函数  
myFun1(1)  
--得到函数的返回值  
result = myFun1(1)  
--将函数赋值给变量，得到这个函数  
myFun1Copy = myFun1  

--lua中定义的变量默认是全局变量，在函数中的也是如此。  
--在函数中定义的全局变量，在外部照样可以访问。  
--函数可以声明为全局的或局部的，默认是全局的。  
--局部函数中的都是局部变量。  

--函数可以返回多个值  
function getNum()  
    return 1,2,3  
end  
num1,num2,num3 = getNum()       --左边多，则多的变量为nil；右边多，则舍去  

--函数可以为变量赋值，也可以作为参数进行传递（相当于C#的委托）  

--匿名函数及其调用  
namelessFunc = function(para1) print(tostring(para1)) end  
namelessFunc(233)  
--lua中没有lambda表达式  
```  

## 字符串  

### 字符串定义  

单行字符串：由单引号或双引号括起。  
多行字符串：由`[[`和`]]`括起。  

### 转义字符串  

（略）  

### 字符串函数  

输出字符串长度：  
可以使用`string.len(str)`。（一个汉字的长度为2），返回number。  
可以使用`#str`。  

转别成大写和小写：  
可以分别使用`string.upper(str)`和`string.lower(str)`，返回string。  

查找：  
可以使用`string.find(str,pattern)`，返回number。  

截取字符串：  
可以使用`string.sub(str,i,j)`，返回string。  

字符串替换：  
可以使用`string.gsub(str,pattern,repl)`。  

字符串反转：  
可以使用`string.reverse(str)`，返回string。  

格式化字符串：  
可以使用`string.format(formatString,...)`，返回string。  

### 范例代码  

--[[字符串]]  

```lua  
--定义单行字符串  
str = "abc"  
--定义多行字符串  
strMultiLine[[  
    太阳骑士 索拉尔  
    太阳万岁！  
]]  
--字符串的连接  
print("太阳骑士 ".."索拉尔")       --输出：太阳骑士 索拉尔  
--字符串的转换  
print("123"+"100")              --输出：223  
--其他情况  
print("数字："..123)               --输出：数字：123  
print("123"+100)                --输出：223  
--求字符串的长度  
print(#"123")                   --输出：3  
print(#"数字")                    --输出：4  

--字符串中两个重要的转换函数  
--tonumber()、tostring()  
numStr1 = "233"  
num2=100  
num3 = tonumber(numStr1) - num2  
numStr3 = "233"..tostring(num2)  

--必须用到tostring()的场合1  
numTable = {1,2,3}  
--print("表的内容："..numTable)              --错误，表不能自动转化为字符串  
print("表的内容："..tostring(numTable))      --输出的是表的地址，而非内容  
--必须用到tostring()的场合2  
num = nil  
--print("num = "..num)                    --错误，不能连接空类型  
print("num1 = "..tostring(num))  

--输出字符串长度：可以使用`string.len(str)`。（一个汉字的长度为2），返回number。可以使用`#str`。  
--转别成大写和小写：可以分别使用`string.upper(str)`和`string.lower(str)`，返回string。  
--查找：可以使用`string.find(str,pattern)`，返回number。  
--截取字符串：可以使用`string.sub(str,i,j)`，返回string。  
--字符串替换：可以使用`string.gsub(str,pattern,repl)`。  
--字符串反转：可以使用`string.reverse(str)`，返回string。  
--格式化字符串：可以使用`string.format(formatString,...)`，返回string。  

--格式化字符串的使用  
print("select ? from ? where id=? and ......")      --示例的SQL语句  
strResult = string.format("select %s from %s where id=%d and ......",content,db,id)  
```  

## 表  

### 表的定义  

表是lua的一种数据结构，用来帮助我们创建不同的数据类型，如：数组，键值对集合等。  

表的基本特征与定义：  
表（形似列表）的下标可以是负数。  
表的长度可以动态改变。  
根据初始化方式的不同，可以把表当作一个列表对待，可以把表当作一个字典对待。  

表可以是空表，可以直接声明并定义，也可以声明然后逐一赋值。  
访问表中数据（形似字典），可以直接用`.`符号访问，也可以使用中括号访问，但是一定要以字符串的形式。  

表中数值的修改方式。  
规律：使用nil直接移除数组中的数据，索引不会变化。  
使用表的专用删除函数，索引或自动排序。  
推荐表中数据的删除，用专门的函数处理。  

### 表的赋值与迭代输出  

使用#号可以得到表的长度（只适合形似列表的表）。  

通过ipairs函数输出连续的数值。  
通过pairs输出连续与非连续（键值对）的数值。  

### 表的函数  

得到表的长度：`table.getn()`。  
表的连接：`table.concat(table[,sep[,i[,j]])`，把表中的数据进行连接后输出，可以自定义分隔符和连接范围。  
表的插入：`table.insert(table[,pos],value)`，注意索引从1开始，可以自定义插入位置。  
表的移除：`table.remove(table[,pos])`，默认移除最后的序号。  
表的排序：`table.sort(table[,comp])`，可以自定义排序方式。（默认排序方式：大写字母，小写字母，汉字）  
得到表中最大元素的索引：`table.maxn()`。  

> `.`与`:`的区别在于使用`:`定义的函数隐含`self`参数，使用`:`调用函数会自动传入`table`至 `self`参数，  

### 表资源的释放  

无论是释放表的元素，还是释放整个表，都可以通过将之赋值为nil实现。  

### 范例代码  

```lua  
--[[表]]  

--表的定义  
numTable1 = {}                      --一个空表  
numTable2 = {1,2,3}                 --一个列表形式的表  
numTable3 = {i1 = 1,i2 = 2,i3 = 3}  --一个字典形式的表  
numTable3.i4 = 5                    --表的元素可以继续添加  

print(numTable1)                    --输出表的地址  
print(numTable2[1])                 --输出表的第一个元素（索引以1开始）  
print(numTable3.i1)                 --输出表中键为i1的元素（也可以用索引）（类似属性的形式）  
print(numTable3["i1"])              --输出表中键为i1的元素（也可以用索引）（必须是字符串）  

--表的迭代输出  
--也可以使用ipairs()和pairs()函数进行迭代输出。对于键值对形式的表，不能保证顺序。  
myTable = {1,2,3}  
for i =1,#myTable do  
    print(myTable[i])  
end  
for i, v in ipairs(myTable) do  
    print(i)  
end  

--自定义得到表中最大数值的函数  
function getMax(tab)  
    if type(tab) == "table" then  
        return  tab[table.maxn(tab)]  
    else  
        print("给定的参数不是table！")  
        return nil  
    end  
end  
```  

## 面向对象基础  

```lua  
--定义空表，相当于一个类  
Person = {}  
--定义局部表引入变量，降低方法引用表字段的耦合性  
--local this = Person  

--定义类的字段  
Person.Name = "微风的龙骑士 风游迩"  
Person.Race = "伊咪"  
Person.Weapon1 = "风临"  
Person.Weapon2 = "《微风的吹息》"  
Person.Magic1 = "连环风切"  

--定义类的方法（使用this代替类名）  
--function Person.Acton1()  
--	print("魔法触媒："..this.Weapon2)  
--	print("魔法："..this.Magic1)  
--end  

--使用self关键字，直接在方法中引用表自身字段与方法（方法名中的点改为冒号）  
function Person:Acton1()  
	print("魔法触媒："..self.Weapon2)  
	print("魔法："..self.Magic1)  
end  

--调用类的字段和方法（使用this代替类名）  
--print(Person.Name)  
--Person.Acton1()  
--更加完善的调用类的字段和方法的方式（使用self关键字）  
print(Person.Name)  
Person:Acton1()  
```  

  

# Lua中级  

## 目录  

* 函数进阶  
    * 参数的简化  
    * 可变参数  
    * 标准函数库  
    * 函数尾调用  
    * 函数的本质  
    * 闭包  
    * 文件互调用  
    * 函数前置声明与unpack函数  
* 字符串进阶  
    * 常用函数汇总  
    * 模式匹配  
    * 字符串不变性原理  
* Table进阶  
    * Table本质  
    * 二维数组（矩阵）  
    * 链表  
* 元表  
    * 元表作用于性质  
    * 算术与关系元方法（__add()、__sub()）  
    * 库定义类元方法（__tostring()）  
    * Table访问类元方法（__index(),__newindex(),__call()）      
* 面向对象进阶  
    * 封装  
    * 继承  
    * 方法覆盖  
* 协同程序  
    * 协同程序  
    * 创建协同与挂起  
    * 带参协同与协同生命周期  
    * yield返回数值  
    * 生产者和消费者问题  
* IO操作  
    * 文件只读操作  
    * 文件写入操作  
* 调试与运行  
    * 执行外部代码  
    * lua错误与异常处理  
    * lua垃圾收集机制  

## 函数进阶  

### 参数的简化  

函数如果只有一个参数，并且此参数是一个字符串或者table构造式，则（实参）圆括号可以省略，这种语法现象称为“参数的简化”。  

```lua  
--参数的简化（字符串）  
function MyFun(nickname)  
    print("Hello world!")  
    print(nickname)  
end  
MyFun("龙骑士")  
MyFun "龙骑士"         --简化形式  

--参数的简化（表）  
function MyFun2(nicknames)   
    for i, v in ipairs(nicknames) do  
        print(v)  
    end  
end  
nicknameArray = {"喵喵","武藏","小次郎"}  
MyFun2(nicknameArray)  
MyFun2 {"小智","皮卡丘"} --简化形式  
```  

### 可变参数  

使用符号`...`来表示可变参数，主要应用在形参中，以表的形式接受那些参数。  
（类似C#中的para关键字）  

```lua  
--定义一个具有可变参数的函数  
function MyFun3(...)  
    for i, v in ipairs(...) do  
        print(v)  
    end  
end  
MyFun3 {"小智","皮卡丘"}  

--编写简化的可变参数的写法  
--lua有一个内置函数“arg”来代替“{...}”  
function MyFun3(...)  
    for i, v in ipairs(arg) do  
        print(v)  
    end  
end  

```  

注意：  
ipairs迭代器会忽略nil和后面的元素，pairs迭代器则会迭代nil以外的所有元素。  

如果变长参数中可能包含nil，则必须使用select关键字来访问变长参数。调用select时必须传入一个固定的实参select（开关）和一系列变长参数。select关键字不能和arg关键字一起使用  

```lua  
--select(index,...)   --返回从index下标开始，一直到变长参数列表结尾的元素  
--select('#',...)     --返回变长参数列表的长度  
function myFun4(...)  
    print("可变参数的个数：",select('#',...))  
    local elem  
    for i=1,select('#',...) do  
        elem = select(i,...)    --右边的值有多个，包括所有从该索引开始的元素  
        print(elem)  
    end  
end  
myFun4("亚斯特拉","索尔隆德","大沼",nil,"卡塔利那")  

```  

### 标准函数库  

数字函数  

`math.abs(x)`，求绝对值  
`math.max(x)`，求最大值  
`math.min(x)`，求最小值  
`math.sin(x);   math.cons(x);   math.tan(x)`，三角函数  
`math.sqrt(x)`，求平方根  
`math.ceil(x)`，向上取整  
`math.floor(x)`，向下取整  
`math.randomseed(x)`，设置随机种子  
`math.random(m,n)`，求随机数（给定的两个整数之间的整数），和随机种子有很大关系。  

操作系统库  

`os.date([format,time])`，得到日期（月/日/年 时:分:秒）  
`os.time([table])`，得到时间（时间戳）  
`os.exit(1)`，退出  

```lua  
---范例：得到一个真正的随机数  

function GetRandom(min,max)  
    --得到时间戳  
    local strTime = tostring(os.time())  
    --得到一个反转字符串  
    local strRev = string.reverse(strTime)  
    --得到前6位  
    local strRandomTime = string.sub(strRev,1,6)  
      
    --设置时间种子  
    math.randomseed(strRandomTime)  
    return math.random(min,max)  
end  
```  

### 函数尾调用  

lua中可以在一个函数中，使用return返回另外一个函数，这种语法称为“尾调用”。（即：一个函数调用是另一个函数的最后一个动作）。  

```lua  
function MyFun5()  
    return math.abs(-100)   --尾部调用系统函数  
end  
function MyFun6()  
    return MyFun6()         --尾部调用自定义函数  
end  

--演示递归算法中，尾调用的作用  
--尾调用不占用堆栈的空间，不会出现栈溢出，所以可以起到优化存储空间的作用。  
--其他应用：状态机  
function RecurFun(num)  
    if(num>0) then  
        print(num)  
        return RecurFun(num-1)  --尾部调用（递归函数）  
        --return RecurFun(num-1)+0  --不是尾部调用，会导致栈溢出  
    else  
        return "END!"  
    end  
end  

res = RecurFun(10)  
print(res)  

--如果尾调用时使用“return (Func())”的方式，则只返回一个数值  
```  

### 函数的本质  

lua函数本质是匿名的，即没有名称。讨论一个函数，本质是讨论一个持有此函数的变量。  

函数与普通类型的数值的权利相同。  
函数可以存储在变量中，可以作为其他函数的参数或返回值。  
函数本质上是一条语句，可以将其存储在全局变量或局部变量中。  

### 闭包  

一个函数中可以嵌套子函数，子函数可以使用父函数中的局部变量，这种行为就是“闭包”。  
闭包=函数+引用环境。  

```lua  
--无参数的闭包  
--多次调用，值会增加  
--只要不是第一次，以后都只会执行内嵌的部分  
function Fun()  
    local i=0    --称为内嵌函数的upValue，既不是全局变量，也不是局部变量，这里应该称为“非局部变量”  
    return function()   --内部嵌入的匿名函数  
        i = i+i  
        return i  
    end  
end  

--带参数的闭包  
--多次调用，值也会增加  
--只要不是第一次，以后都只会执行内嵌的部分  
function FunWithPara(i)     --参数i是内嵌函数的upValue  
    return function()   
        i = i+i  
        return i  
    end  
end  
```  

闭包的特点：  
闭包中的内嵌函数可以访问外部函数已经创建的所有局部变量，这些变量称为该内嵌函数的upValue。  

闭包与一般函数的区别：  
闭包只是在形式和表现上像函数，但是实际上不是函数。函数是一个实例，定义后逻辑就确定了，不会执行时发生变化。  
闭包中的upValue，能够保存自身的概念。  

```lua  
--具备多个内嵌函数的闭包  
function FunMulti()   
    local numUpValue = 10   --upValue  
    function InnerFun1()       --内嵌函数  
        print(numUpValue)  
    end  
    function InnerFun2()   
        numUpValue = numUpValue+100  
    end  
      
    return InnerFun1,InnerFun2   
end  

--调用测试  
local res1,res2 = FunMulti()  
res1()      --输出：10  
res2()  
res1()      --输出：110  
```  

```lua  
--带参数的内嵌构造函数  
function Fun6(num)   
    return function(value)   
        num = num*value  
        return num  
    end  
end  

--调用测试  
Fun7 = Fun6(10)     --对应的参数是num  
print(Fun7(2))      --对应的参数是value   --输出：20  
print(Fun7(2))                          --输出：40  
```  

闭包也被称为“词法域”。  

闭包的典型应用：  
“迭代器”的实现可以借助于闭包函数实现，闭包函数能够保持每次调用之间的一些状态。  

> 测试失败

```lua  
--使用闭包技术开发自己的迭代器  
function Itrs(tabArray)   
    local i=0  
      
    return function()   
        i = i+1  
        return tabArray[i]  
    end  
end  

myTab = {10,20,40,66}  
iterator = Itrs(myTab)  

--使用for循环输出  
for e in iterator do  
    print("输出：",m)  
end  
```  

### 模块&文件互调用  

模块的定义：  
由变量、函数等组成的table，因此创建模块本质就是创建一个table，此table最后需要返回。  

模块的作用：  
类似于一个封装库，把一些公用的代码放在一个文件里，以api接口的形式在其他地方调用，有利于代码的重用和降低代码耦合度。  

注意：  
定义local的函数名，就不要加模块限定，否则会出错。  

使用require关键字实现lua文件的加载  
`require(<模块名>)`  

说明：  
执行require后，返回一个由模块常量和函数组成的table。  
模块名称与lua文件名称必须相同。  

```lua  
---C1_IsInvokedModel.lua  

--本质上定义一个模块，就是定义一个含有数值和函数的table  

--定义一个局部表  
local myModel = {}  

--定义表中字段  
myModel.Name = "环印骑士直剑"  

--定义模块中的函数  
function myModel.Fun1()   
    print("古时跟随葛温大王狩猎古龙的骑士团，其使用的经过深渊淬炼的直剑。")  
end  

return myModel  
```  

```lua  
--lua文件的相互调用  
local model = require("C1_IsInvokedModel")  
if(not model) then  
    print("异常：模块不存在！")  
    return  
end   

--模块的调用  
model.Fun1()    --测试模块的函数  
print(model.Name)   --测试模块的变量  
```  

注意事项：  
* 被调用的lua文件，必须定义为模块的形式  
* 调用的变量与函数必须不能是局部的，否则无法访问。  
* 可以给加载的模块定义一个别名变量，方便使用。  
* 给require赋值一个变量时，还可以加入local关键字，表示本别名方式只有在本文件中起作用。  

### 函数的前置声明  

函数本质是匿名的，即没有名称。讨论一个函数本质是讨论一个持有该函数的变量。  

函数的前置声明，可以增加程序整体的易读性。  
（源自C语言，声明本lua文件中有哪些函数）  

```lua  
--函数声明，可以提高程序的易读性  
local Sleep  

function Sleep(name)   
    print(name.."睡着了！")  
end  
```  

### unpack()函数  

函数unpack()：  
接受一个数组作为参数，并从下标1开始访问该数组的所有元素  

注意事项：  
* unpack()函数可以很容易地把table集合中的数据“解包”输出  
* 与之对应的是`table.concat()`函数，可以把table集合中的数据压缩为一个字符串输出。  

```lua  
tab1 = {"幽鬼兔","浮幽樱","灰流丽"}  
function testFun(str1,str2,str3)  
    print(str1..str2..str3)  
end  
--调用解包函数  
testFun(unpack(tab1))  
--concat()函数相当于“压包函数”  
print(table.concat(tab1," "))  
```  

## 字符串进阶  

### 常用函数汇总  

字符串与ASCII之间的转换：  
`string.byte()`，字符串转为ASCII码  
`string.char()`，ASCII码转为字符  
`string.rep(s,n)`，返回字符串多个拷贝  

### 模式匹配  

使用类似正则表达式的方式进行模式匹配。  

* `.`与任何字符匹配  
* `%a`与任何字母匹配  
* `%d`与任何数字匹配  
* `%w`与任何字母/数字匹配  
* `%s`与任何空白匹配  
* `%l`与任何小写字母匹配  
* `%u`与任何大写字母匹配  
* `%p`与任何标点匹配  
* `%c`与任何控制符匹配  
* `%x`与任何十六进制树匹配  
* `%z`与任何代表0的字符匹配  
* `%D`与任何数字以外的字符匹配  
* `^`,`[]`,`*`,`+`,`?`等与真正的正则表达式中的作用相同  
* `%n`，n可以是1-9，匹配1到n个符合条件的子串（具体使用有待测试）  

常用函数：  
`string.match(s,pattern[,init])`，返回匹配正则的第一个结果  
`string.gmatch(s,pattern)`，带有迭代器，返回多个结果  
`string.find(s,pattern[,init]):number`，返回匹配正则的第一个结果的起始位置和结束位置，参数`init`表示查找的起始位置  
`string.gsub(s,pattern,repl[,n])`，替换  

```lua  
s = "今天是2018/08/05"  
date = "%d%d%d%d/%d%d/%d%d" --模式匹配字符串  
--datePattern = "\d{4}/\d{2}/\d{2}"  
print(string.sub(s,string.find(s,date))) --输出：2018/08/05  

--match()函数，返回匹配正则的第一个结果  
strRes1 = string.match("Hello,world!","%a")  
print("查找字母：",strRes1)              --返回第一个字母，即“H”  

--find()函数，返回匹配正则的第一个结果的起始位置和结束位置  
print(string.find("你的本学期成绩是102分","%d+",1))  

--gmatch()函数，带有迭代器，返回多个结果  
s = "Hello@World%Every!One"  
pat = "%a+"  
for word in string.gmatch(s,pat) do  
    print(word)         --依次输出给定字符串中的4个单词  
end   

--示例：输出字符串“Mobile: 131666688888,My Phone: 01066668888中所有电话号码的信息”  
s = "Mobile: 13166668888,My Phone: 01066668888"  
pat = "%d+"   --"\d{11}"  
for num in string.gmatch(s,pat) do  
    print(num)  
end   

--示例：输出字符串“luaC#JavaPascalC++”中所有以C开头的编程语言的名称  
s = "LuaC#JavaPascalC++"  
pat = "C%U*"  
for lan in string.gmatch(s,pat) do  
    print(lan)  
end  
```  

```lua  
--综合示例：对登录名称（帐号、密码）等进行合法性检查  
--帐号：以字母、数字、下划线组成，且数字不能作为开头  
--密码：必须包含字母、数字和特殊字符，不能少于6位  
strName = "DragonKnightOfBreeze"  
namePat = "[^%w_]"  

checkPos = string.find(strName,namePat) --尝试查找非法的内容  
firstLet = string.sub(strName,1,1)     --得到帐号的首字母  
checkBoo = string.match(firstLet,"%d")  --尝试匹配非法的内容  

if(checkPos==nil and checkBoo == nil) then  
	print("输出的帐号合法。")  
else  
	print("输入的帐号不合法。")  
end  
```  

## table进阶  

### 表的本质  

lua变量没有预定义类型，任何变量都可以包含任何类型的值。  
表可以表示各种数据结构。  
表是lua中主要的数据结构机制，可以作为其他数据结构的基础，具有强大的功能。  

基于表可以以一种简单、同一和高效的方式来表示数组、二维数组、键值对集合、链表、双向队列和其他数据结构等，同时lua也是通过表来表示模块、类的对象等。  

表在lua中既不是值也不是变量，而是“对象”。  
lua不会暗中产生表的副本或创建新的表，也不需要声明一个表。  
表是通过构造表达式完成的，最简单的构造表达式就是`{...}`。  

表永远是匿名的，一个持有表的变量与表之间是没有固定关联性的。  
可以将表认为是一种动态分配的对象，程序仅保存对他们的引用。  
当程序没有对一个表进行引用时，lua的垃圾回收机制最终会删除这个表。  

### 二维数组（矩阵）  

```lua  
--定义一个二维数组  
local doubleArray = {}      --定义一个二维数组  
local arrayRow_1 ={}        --第一行的一维数组  
local arrayRow_2 ={}  

--二维数组元素的赋值  
arrayRow_1[1] = 10  
arrayRow_1[2] =20  
arrayRow_2[1] = 40  
arrayRow_2[2] = 60  

--循环输入二维数组的内容  
for i,v in ipairs(doubleArray) do   
    --for sub_i,sub_v in ipairs(v) do  
    --    print(v)  
    --end  
    print(unpack(v))  
end   

```  

【有用的代码】  

```lua  
--定义二维数组的初始化函数  
--参数：row,行  column,列  
function CreateDoubleArray(row,column)   
    local doubleArray = {}  
    for i=1,#row do  
        doubleArray[i] = {}             --定义行  
        for j =i,column do  
            doubleArray[i][j] = 0       --初始化所有数值  
        end  
    end  
end  
```  

### 链表  

由于lua中table是动态的实体，所以在lua中实现链表是非常方便的  
每一个链表结点由table来表示，结合包含两个值next和value，注意尾节点的next应该是nil  

【有用的代码】  

```lua  
--定义链表的初始化函数  
function CreateLinkedList(value)  
	local linkedList = {}  
	linkedList.Value = value  
	linkedList.Next = nil  
	return linkedList  
end  

--增加一个结点  
function Add(linkedList,value)  
	--参数类型检查  
	if type(linkedList) ~= "table" then  
		return nil  
	end  

	local temp = {}  
	temp.Value = value  
	temp.Next = nil  
	--定位最后一个结点的Next  
	local index = linkedList  
	while(index ~= nil) do  
		index = index.Next  
	end  
	--连接到最后一个节点  
	index = temp  
end  

--定义链表的迭代器  
function Iterator(linkedList)  
	--参数类型检查  
	if(type(linkedList) ~= "table") then  
		return nil  
	end  

	--开始迭代链表中的Value  
	return function()  
		if(not list) then  
			return nil  
		end  
		returnValue = list.Value  
		list = list.Next  
		return returnValue  
	end  
end  
```  

## 元表  

### 元表的作用与性质  

为什么需要元表：  
在lua中每个值都有一套预定义的操作集合，例如可以将数字相加，可以连接字符串，还可以在table中插入一对键值对等。  
但是我们无法将两个table相加，无法对函数做比较，也无法调用一个字符串。  

元表的作用：  
Lua提供了元表和元方法来修改一个值的行为，使其在面对一个非预定义的操作时能执行一个指定的操作。  
例如：对两个表进行“加法”操作  

（元表是设置了自身的元方法的表，为其他表设置元表以修改对应的操作）  

```lua  
--第一步：定义原始表  
tab1={10,20,30}  
tab2={30,40,50}  

--第二步：定义元表  
local setTable={}  
--定义元表的加法函数  
function setTable.Add(tab1,tab2)   
    local result = {}  
    for i, v in ipairs(tab1) do  
        if(v == nil) then  
            break  
        end  
        result = tab1[i]+tab2[i]  
    end  
      
    return result  
end  

--第三步：设置元方法  
setTable.__add=setTable.Add     --Add函数作为“__add()”的实际执行方法  

--第四步：设置元表  
setmetatable(tab1,setTable)  
setmetatable(tab2,setTable)  

--第五步：测试输出  
tabResult = tab1+tab2  

--输出结果  
for i,v in ipairs(tabResult) do  
    print(v)  
end  
```  

设置元方法的步骤：  
* 定义原始表  
* 定义元表以及元表的相关方法  
* 设置元表的元方法  
* 设置原始表对应的元表  

### 元表的算术类和关系类的方法  

算术类的元方法：  
`__add()`，加  
`__sub()`，减  
`__mul()`，乘  
`__div()`，除  
`__unm()`，相反数  
`__pow()`，次幂  
`__concat()`，连接  

关系类的元方法：  
`__eq()`，等于  
`__lt()`，小于  
`__le()`，小于等于  

### 库定义的元方法  

库定义的元方法：  
`__tostring()`，转换成字符串  
`__metatable()`，设置后不可修改元表  

```lua  

--第一步：定义原始表  
tab1={10,20,30}  
tab2={"AK-47突击步枪","原型轨道枪","帝国磁轨炮"}  

--第二步：定义元表  
local setTable={}  

--定义元表的字符串转化函数  
function setTable.ToString(tab)   
    local result = {}  
    for i, v in ipairs(tab) do  
        result[#result+1] = v  
    end  
    return table.concat(result,",")  
end  

--第三步：设置元方法  
setTable.__tostring =setTable.ToString  
--规定元方法不允许被修改  
setTable.__metatable = "不能修改受保护的元表！"  

--第四步：设置元表  
setmetatable(tab1,setTable)  
setmetatable(tab2,setTable)  

--第五步：测试输出  
print(tab1)  
print(tab2)  

--得到一个元表  
metatable = getmetatable(tab_2)  
```  

### 表访问类元方法  

`__index()`，查询表的新字段  
当访问一个表中不存在的字段时，得到的结果为nil。实际上，这些访问会促使解释器去查找一个叫做`__index()`的元方法，如果该方法没有，则访问结果是nil，否则由这个元方法提供最终的结果。  

`__newindex()`，给表的新字段赋值  
当对一个表中不存在的索引赋值时，解释器就会查找这个元方法，如果有，则会调用它，而不是执行赋值。  

```lua  
tab = {Rifle1="AK-47",Rifle2="M4A1",Rifle3="SCAR-L"}  

----定义元表的核心函数  
--local setTable = {}  
--function setTable.IndexNotFound()  
--    return "要查询的数据不存在！"  
--end  
--  
----设置元方法  
--setTable.__index = setTable.IndexNotFound  
--setmetatable(tab,setTable)  

--function IndexNotFound()  
--   return "要查询的数据不存在！"  
--end  
--setmetatable(tab,{__index = IndexNotFound})  

--设置元表的元方法  
setmetatable(tab,{  
    __index = function()   
        return "要查询的数据不存在！"   
    end,  
    __newindex = function(tab,k,v)   
        print("检查到添加了新的数据。","Key:"..k,"Value:"..v)   
    end  
})  

--测试  
print(tab["Rifle4"])  
```  

注意：  
`rawset(t,k,v)`方法可以绕过元方法对表进行赋值，可以使得增加的记录直接生效，而元表函数继续执行。  

`__call()`，调用表  
`__mode()`，弱引用表  

```lua  
--方法被执行时调用的函数，即是__call()对应的函数  
tab1 = {}  

setmetatable(tab1,{  
    __call = function()   
        print("本表被调用。")  
    end  
})  

print(tab1())  
```  

## 面向对象进阶  

### 封装  

```lua  
--定义空表，相当于一个类  
Person = {}  

--定义类的字段  
Person.Name = "微风的龙骑士 风游迩"  
Person.Race = "伊咪"  
Person.Weapon1 = "风临"  
Person.Weapon2 = "《微风的吹息》"  
Person.Magic1 = "连环风切"  

--使用self关键字，直接在方法中引用表自身字段与方法（方法名中的点改为冒号）  
function Person:Acton1()  
	print("魔法触媒："..self.Weapon2)  
	print("魔法："..self.Magic1)  
end  

--使用元方法__index()，实现继承的机制  
local BreezeKnight ={}  

BreezeKnight.Name = "微风骑士"  
BreezeKnight.Race = "伊咪"  

function BreezeKnight:Action2()  
    print("落风斩！！！")  
end  

--设置元方法  
setmetatable(BreezeKnight,{ __index = Person})  

print(BreezeKnight.Name)  
print(BreezeKnight.Magic1)      --调用模父类的字段  
BreezeKnight:Acton1()       --调用模拟父类的方法  
BreezeKnight:Action2()  
```  

```lua  
--定义空表，相当于一个类  
BreezeKnight = {}  

--定义类的字段  
BreezeKnight.Name = "微风骑士"  
BreezeKnight.Race = "伊咪"  

--定义类的方法  
function BreezeKnight:Acton1()  
	print("魔法：风切")  
end  

  
--定义一个实现继承机制的特殊方法  
function BreezeKnight:New(name,race)   
    local subClass = {}     --定义返回的子类  
    self.__index = self  
    setmetatable(subClass,self)  
      
    subClass.Name = name  
    subClass.Race = race  
    return subClass  
end  

--定义子类  
DragonKnightOfBreeze = {}  
DragonKnightOfBreeze = BreezeKnight:New("微风的龙骑士 风游迩","伊咪")  

--重写父类的方法  
--定义类的方法  
function BreezeKnight:Acton1()  
	print("魔法：连环风切")  
end  

--测试  
print(DragonKnightOfBreeze.Name)     
DragonKnightOfBreeze:Acton1()         
```  

```lua  
--定义类的继承方法  
function Extends(supperClass)   
    local subClass = {}  
    supperClass.__index = supperClass  
    setmetatable(subClass,supperClass)  
    return subClass  
end  

  
--定义空表，相当于一个类  
Knights = {}  

--定义类的字段  
Knights.Name = nil  
Knights.Race = nil  
--定义类的方法  
function Knights:Acton1() print("攻击！") end  

--定义类的构造方法  
function Knights:Init(name,race)   
    local newClass = {}       
    self.__index = self  
    setmetatable(subClass,self)  
      
    newClass.Name = name  
    newClass.Race = race  
    return newClass  
end  

--定义子类  
Knight = Extends(Knights)  
Knight.Weapon1 = nil  
Knight.Weapon2= nil  

--定义子类的方法  
function Knight:Acton2() print("互动！") end  

--定义子类的构造方法  
function Knights:Init(name,race,w1,w2)   
    local newClass = {}       
    self.__index = self  
    setmetatable(subClass,self)  
      
    newClass.Name = name  
    newClass.Race = race  
    newClass.Weapon1 = w1  
    newClass.Weapon2 = w2  
    return newClass  
end  

--实例化  
BreezeKnights = Knights:Init("微风骑士团","伊咪")  
DragonKnightOfBreeze = Knight:Init("微风的龙骑士 风游迩","伊咪")  

--测试  
DragonKnightOfBreeze:Acton1()  
DragonKnightOfBreeze:Acton2()  
```  

## 协同程序  

### 协同程序基础  

什么是协同程序：  
lua所支持的协程全程是协同式多线程  
lua为每个coroutine提供一个独立的运行线路，然而与多线程不同的地方是，coroutine只有在显示调用yield函数后才被挂起，同一时间内只有一个协程正在运行。  

与真正的多线程的区别：  
一个具有多个线程的程序可以同时运行数个线程，而协同程序却需要彼此协作的运行。  
也就是说多个协同程序在任意时刻只能运行一个协同程序，只有当正在运行的协同程序显示的要求挂起时，它的执行才会暂停。  
   
```lua  
--定义一个协程  
cor1 =  coroutine.create(function()    
    for i = i,10 do  
        print("协同程序：",i)  
        if(i == 5) then  
            coroutine.yield()       --线程挂起  
        end  
    end  
end  
)  

print("类型：",type(cor1))     --thread  
--暂停或启动一个协程  
coroutine.resume(cor1)  
```  

基本语法：  
协程创建1：coroutine.create（创建一个thread类型的值表示新的协同程序，返回一个协同程序）  
协程创建2：coroutine.wrap（同样创建一个新的协同程序，返回一个函数。）  
启动协程：coroutine.resume（启动、恢复执行一个协程，并将其状态由挂起改为运行）  
检查协程状态：coroutine.status（检查协同程序的状态，挂起suspended、运行running、死亡dead、正常normal）  
挂起协程程序：coroutine.yield（让一个协同程序挂起）  

### 创建协程与挂起  

创建协程（create）：  
调用函数`coroutine.create()`可以创建一个协程，其唯一的参数是该协程的主函数。create函数只负责新建一个协程并返回其句柄（一个thread类型的对象），而不会启动该协程。  

创建协程（wrap）：  
也会创建一个协程，但返回一个函数。  
更容易使用，提供了一个可以唤醒程序的函数，但缺乏灵活性，无法检查所创建的协同程序的状态，也无法检测出运行时的错误。  

```lua  
--使用wrap()定义协程（应用场合较少）  
--原因：不能显示当前协同的状态  
cor3 = coroutine.wrap(  
function(inputInfo)  
    print("使用wrap函数定义协程，输入参数：",inputInfo)  
    for i =  1,5 do    
        print(i)  
    end  
end  
)  

--启动协程  
cor3("这是一个协程")  
```  

### 创建协程与挂起  

协程的挂起：  
调用`coroutine.yield()`使协程暂停执行且让出执行权。此时协程让出执行权后，对应的最近`coroutine.resume()`函数会立刻返回。  

### 带参协程与协程生命周期  

```lua  
--协同程序的参数传递  
cor5 = coroutine.create(  
    function(num1)  
        print("输入参数1=",num1)  
    end  
)  

--测试运行  
coroutine.resume(cor5(200))   
--可以有多个参数，第一个表示 启动的协程，后面的表示协程对应函数的参数  
```  

演示带参协程之间的状态管理：  
以上势示例中的“A协程”中启动“B协程”，然后在B协程中查看A协程的状态。  
这个示例充分表明Lua的一项非常重要的特性：多个协同程序在任意时刻只能运行一个协同程序。  

进一步总结Lua提供一种非对称协同程序，也就是lua提供了两个函数来控制协同程序的执行，一个用于挂起执行，另一个用于恢复执行。  

```lua  
local corB = coroutine.create(  
    function(corAPara)  
        print("协同B，检查输入参数协程B的状态",coroutine.status(corBPara))  
    end  
)  

local corA = coroutine.create(  
    function(corBPara)  
        print("协同A，检查输入参数协程A的状态",coroutine.status(corAPara))  
        coroutine.resume(corB,corA)  
    end  
)  

--测试  
print("协同A一开始的状态：",coroutine.status(corA))  
print("协同B一开始的状态：",coroutine.status(corB))  
coroutine.resume(corA,corA)  

--测试结果  
--任意时刻只能运行一个协同程序，必须挂起一个，然后运行另一个  
--在一个协程中，无法引发另外一个协程  
```  

### yield返回数值  

演示yield返回数值技术：  
当挂起正在调用的协程的执行，传递给yield的参数会转为resume的额外返回值，即这里的yield参数，可以作为启动本协程的返回数值使用。  
可以把一个协程作为带有返回数值的函数使用  
返回的第一个参数必定是协程状态的布尔值  

```lua  
local cor = coroutine.create(  
    function()  
        coroutine.yield("Hello",68)     --但参数的协同的挂起  
    end     
)  

--接受返回的参数  
result,v1,v2 = coroutine.resume(cor)  
```  

### 生产者与消费者问题  

```lua  
--范例：生产者与消费者问题  

--具体产生一个累加数值的迭代函数  
--用闭包实现一个迭代器函数  
function GetNumber()   
    local num = 0  
    return function()   
        num = num+1  
        return num  
    end  
end  
--这一步必须要有  
local getNum = GetNumber()  --得到一个返回函数  

  
--生产者程序  
--在循环内得到迭代器函数的引用  
Producer = coroutine.create(  
    function()   
        while(true) do  
            local num = getNum()    --得到一个迭代器函数返回的具体数值  
            print("生产的数据=",num)  
            coroutine.yield(coroutine.status(), num)       --返回迭代生成的数值  
        end  
    end  
)  

--接收者程序  
--接收生产者，传给消费者  
--接收的第一个数据是协程状态的布尔值  
function Receiver()   
    local status,value = coroutine.resume(Producer)  
    return value  
end  

  
--消费者程序  
function Consumer(printNum)   
    for i=1,printNum do  
        local receiveValue = Receiver()  
        print("消费数据=",receiveValue)  
    end  
end  

--程序的测试  
Consumer(10)  
```  

生产者-消费者模式是并发、多线程编程中经典的设计模式，生产者和消费者通过分离的执行工作解耦，简化了开发模式，生产者和消费者可以以不同的速度  

## IO操作  

### IO分类与适用场合  

luaI/O库用于读取和处理文件，分为简单模式（和C一样）和完全模式。  

简单模式：  
拥有一个当前输入文件和一个当前输出文件，并且提供针对这些文件的相关操作。  

完全模式：  
使用外部的文件句柄来实现。它以一种面向对象的形式，将所有的文件操作定义为文件句柄的方法  

我们需要在同一时间处理多个文件。  
使用file:function_name来代替io.function_name方法  

两种模式的适用场合区分：  
简单模式在做一些简单的文件操作时较为合适。但是在进行一些高级的文件操作的时候，简单模式就显得力不从心。例如同时读取多个文件这样的操作，适用完全模式则较为合适。  

打开文件：`file = io.open(filename[,mode])`  
模式：  
r，只读，文件必须存在。  
w，只写，文件存在则先清空，否则创建。  
a，追加，文件存在则加到文件尾部，否则先创建。  
r+，可读写，文件必须存在。  
w+，可读写，文件存在则先清空，否则创建。  
a+，可读写追加，文件存在则加到文件尾部，否则先创建。  
b，二进制模式，如果文件是二进制文件，可以加上。  
+，表示对文件即可读也可写。  

### 文件只读操作  

```lua  
--以只读方式打开文件，读取一行  
file = io.open("test1.txt","r")     --只读打开  
io.input(file)                      --传入输入流  
print(io.read())                    --读取一行（无参数时）  
io.close(file)                      --关闭文件  
```  

```lua  
--以只读方式打开文件，读取多行  
file = io.open("test1.txt","r")     --只读打开  
for line in file:lines() do  
    print(line)                     --每次读取一行  
end   
io.close(file)  
```  

### 文件写入操作（覆盖写入）  

```lua  
--覆盖写入文本文件  
file = io.open("test1.txt","w")     --覆盖写入  
io.output(file)                     --传入输出流  
io.write("这是一个文本文件。\r\n这是第二行。")  
io.close(file)  
```  

### 文件的追加写入  

```lua  
--追加写入文本文件  
file = io.open("test1.txt","a")     --追加写入  
io.output(file)                         
io.write("这是一个文本文件。\r\n这是第二行。")  
io.close(file)  
```  

`file:flush()`:缓冲，清空缓存  
`file:seek()`：移动文档中的游标  

## lua调试与运行      

### 执行外部代码  

编译与运行lua外部代码块有三种形式：  
`loadfile()`：编译lua外部代码，但不会运行代码，将会以函数的方式返回编译结果。  
`dofile()`：直接编译运行lua外部代码，并不返回任何结果，是loadfile()的进一步简化封装。  
`loadstring()`：编译字符串中的代码，而非从文件读取。  

```lua  
--InvokedLua.lua  

print("这个脚本是测试用的被调用的脚本")  
num1=10  
local num2 = 20  

function ShowInfo1()   
    print("测试方法1被调用")  
end  

local function ShowInfo2()   
    print("测试方法2被调用")  
end  
```  

```lua  
--调用lua外部脚本  
--注意：必须先调用此函数  

--可以是相对路径也可以是绝对路径  
local externalFile = loadfile("InvokedLua.lua")  

--测试得到的外部lua变量的类型  
print("变量的类型：",type(externalFile))  

--调用此函数  
externalFile()  

--调用外部lua文件函数与变量  
print(num1)  
--print(num2)     --不能得到局部变量  
ShowInfo1()  
--ShowInfo2()      --不能调用局部函数  
```  

```lua  
--使用dofile函数调用lua外部脚本  
--直接编译且运行外部脚本  
local externalFile2 = dofile("InvokedLua.lua")  

--测试得到的外部lua变量的类型  
print("变量的类型：",type(externalFile2))     --类型为空  

--调用外部lua文件函数与变量  
print(num1)  
ShowInfo1()  
```  

dofile和loadfile的优缺点分析：  
* 对于简单任务dofile非常的便捷，在一次调用中完成整个编译和运行。  
* 而loadfile更加的灵活，在发生错误的情况下loadfile会返回nil以及错误信息，这可以按自定义的方式去处理错误。  
* 当需要多次运行一个文件时，只需要调用一次loadfile并保存编译结果函数，然后多次调用编译结果函数即可。优点是只编译一次可多次运用。而dofile每次运行都要从新编译，相对而言loadfile的开销将小很多。      

方法三：`loadstring()`  
编译字符串中的代码，而非从文件读取。  

```lua  
local result1 = loadstring("print('Hello World!')")  

--调用返回函数（也就是运行）  
result1()  
```  

注意：`loadstring()`因编译时不涉及词法域（即闭包），因此与function定义的函数不等价。  
`loadstring()`总是在全局环境中编译它的字符串。  

注意：因为`loadstring()`在编译时不涉及词法域，所以`loadstring()`在编译时不会显示错误信息，只有当使用assert断言时才可以显示loadstring中的错误。  

```lua  
--解决的方法：  
local res2 = assert(loadstring(""))  
print(res2)  
```  

### lua错误与异常处理  

lua所遇到的任何未预期条件都会引发一个错误。因此在发生错误时不能简单的崩溃或者退出，而是结束当前程序块并返回应用程序。当错误引发时进行恰当的处理是最合适的。  

lua主要使用`error()`、`assert()`函数来抛出错误，使用`pcall()`、`xpcall()`来捕捉错误。  

```lua  
local num = nil  
if(not num) then  
    error("错误：请输入合法的数值")      --主动引发异常  
else  
    print("输入的数值合法。")  
end   
```  

assert()函数是error()函数的进一步封装，简化处理。  
assert()函数定义：  
* 首先检查第一个参数是否返回错误，如果不返回错误，则简单返回，否则以第二个参数抛出异常信息。  
* assert(a,b)，参数a是要检查是否有错误的第一个参数，b是a错误时抛出的错误信息，是可选的。  

```lua  
local num = nil  
result = assert(num,"错误：请输入合法数值")   --判断是否需要抛出异常  
print(result)  

  
numInput = "abc"  
res = assert(tonumber(numInput),"请输入一个数值形式的数据")  
print(res)  
```   

pcall()捕获异常：  
可以捕获函数执行中的任何错误，返回false及错误信息。错误信息不一定是一个字符串。  

```lua  
--定义引发异常的函数  
function MyFun()   
    error({error = "本方法有异常发生！"})  
end  

--捕获异常  
local resFlag,errorInfo = pcall(MyFun)  
if(resFlag) then  
    print("程序运行正确！")  
else  
    print("程序有异常！")  
end   
```  

xpcall()捕获错误：  
* pcall()函数的优点是简单，但是缺点是不能输出错误堆栈信息。  
* 如果希望我们捕获错误信息，且显示完整的堆栈错误信息则需要使用xpcall()函数。  
* xpcall()函数必须输入两个函数，前者是可能引发错误的函数，后者是错误处理函数。  

```lua  
--定义引发异常的函数  
function MyFun()   
    error({error = "本方法有异常发生！"})  
end  

--定义错误处理的函数  
function HandlerFun()   
    print("发生错误，详细堆栈信息如下：")  
    print(debug.traceback(thread,message,level))  
end  

--捕获异常  
local resFlag,errorInfo = xpcall(MyFun,HandlerFun)  
if(resFlag) then  
    print("程序运行正确！")  
end  
print("后续语句，继续执行……")  
```  

### lua垃圾收集机制  

Lua语言中使用`collectgarbage()`函数来做垃圾收集机制  

```lua  
print(collectgarbage("count"))  --显示lua占用的总内存数  
```  

  

  

  

  

# EmmyLua 注解的使用

## @class

用于模拟面向对象中的类，支持继承和字段。

```lua
--其中：Car是模拟的类名，Transport是继承自的父类名，@define后面是类的定义的注释
---@class Car : Transport @define class Car extends Transport
local cls = class()

function cls:test()
end
```

## @type

用于注明指定目标对象的类型。

```lua
--局部变量

---@type Car @instance of car
local car1 = {}

---@type Car|Ship @transport tools, car or ship. Since lua is dynamic-typed, a variable may be of different types
---use | to list all possible types
local transport = {}

--全局变量

---@type Car @global variable type
global_car = {}

--性能

local obj = {}
---@type Car @property type
obj.car = getCar()
```

## @alias

将复杂类型注明为一个新的类型，同时给出类型的注释。

```lua
---@alias Handler fun(type: string, data: any):void
---@param handler Handler some handler
function addHandler(handler)
end
```

## @param

为参数进行注释，包括回调函数的参数，包括for循环语句中的参数。

```lua
--对于普通函数

---@param car Car
local function setCar(car)
    --...
end

--对于回调函数

---@param car Car
setCallback(function(car)
    --...
end)

--对于for循环语句的循环变量

---@param car Car
for k, car in ipairs(list) do
	--...
end
```

## @return

为函数的返回值进行注释，可以有多个。

```lua
---@return Car|Ship
local function create()
    --...
end

---Here car_or_ship doesn't need @type annotation, EmmyLua has already inferred the type via "create" function
local car_or_ship = create()
```

## @field

向现有类添加额外字段，j即使没有出现在代码中。

```lua
---@class Car
---@field public name string @add name field to class Car, you'll see it in code completion
local cls = class()
```

## @generic

用于模拟泛型。

```lua
---@generic T : Transport, K
---@param param1 T
---@param param2 K
---@return T
local function test(param1, param2)
    -- todo
end

---@type Car
local car = ...

local value = test(car)
```

## @vararg

用于注释不定参数。

```lua
---@vararg string
---@return string
local function format(...)
    local tbl = { ... } -- inferred as string[]
end
```

## @language

用于为指定的字符串提供语言注入。非常棒！

```lua
---@language JSON
local jsonText = [[{
    "name": "Emmy"
}]]
```

## @see

用于进行引用。

```lua
---@class Car : Transport @define class Car extends Transport
local cls = class()


function cls:test()
end

---@see Car#test
local function testCar() 
end
```

## 数组类型

用于模拟并注释数组/列表类型。

```lua
---@type Car[]
local list = {}

local car = list[1]
-- car. and you'll see completion

for i, c in ipairs(list) do
    -- car. and you'll see completion
end
```

## 表格类型

用于模拟并注释字典/映射类型。

```lua
---@type table<string, Car>
local dict = {}

local car = dict['key']
-- car. and you'll see completion

for key, car in pairs(dict) do
    -- car. and you'll see completion
end
```

## 方法类型

用于注释方法类型，包括参数类型和返回值类型

```lua
---@type fun(key:string):Car
local carCreatorFn1

local car = carCreatorFn1('key')
-- car. and you see code completion

---@type fun():Car[]
local carCreatorFn2

for i, c in ipairs(carCreatorFn2()) do
    -- car. and you see completion
end
```

## 字面量类型（测试未显示提示）

用于指定固定的字符串作为代码提示。

```lua
---@alias Handler fun(type: string, data: any):void
---@param event string | "'onClosed'" | "'onData'"
---@param handler Handler | "function(type, data) print(data) end"
function addEventListener(event, handler)
end
```

```lua
--模拟枚举
---@alias Handler fun(type: string, data: any):void
---@alias IOEventEnum string | "'onClosed'" | "'onData'"
---@param event IOEventEnum
---@param handler Handler | "function(type, data) print(data) end"
function addEventListener(event, handler)
end
```

## 完整的例子

```lua
---@class Transport @parent class
---@public field name string
local transport = {}

function transport:move()end

---@class Car : Transport @Car extends Transport
local car = {}
function car:move()end

---@class Ship : Transport @Ship extends Transport
local ship = {}

---@param type number @parameter type
---@return Car|Ship @may return Car or Ship
local function create(type)
-- ignored
end

local obj = create(1)
---now you can see completion for obj

---@type Car
local obj2
---now you can see completion for obj2

local list = { obj, obj2 }
---@param v Transport
for _, v in ipairs(list) do
---not you can see completion for v
end
```
