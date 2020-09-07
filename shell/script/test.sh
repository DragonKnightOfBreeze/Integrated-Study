#!/bin/sh

### 传递参数

# $0 指定的文件名
# $n 传递的第n个参数

# 特殊参数说明
# $#	传递到脚本的参数个数
# $*	以一个单字符串显示所有向脚本传递的参数。
#       如果用双引号括起，则以"$1 $2 … $n"的形式输出所有参数。
# $$	脚本运行的当前进程ID号
# $!	后台运行的最后一个进程的ID号
# $@	与$*相同，但是使用时加引号，并在引号中返回每个参数。
#       如果用双引号括起，则以"$1" "$2" … "$n" 的形式输出所有参数。
# $-	显示Shell使用的当前选项，与set命令功能相同。
# $?	显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。

### 注释

## 单行注释

# ...

## 多行注释

:<<EOF
……
……
EOF

### 变量

## hello world

echo "hello world"

## 定义变量

# 注意：等号周围不能存在空格
# 命名规则
# * 只能使用字母，数字和下划线，不能以数字开头
# * 不能包含空格
# * 不能使用标点符号
# * 不能使用关键字

str='hello world'
echo $str
# echo ${str}

## 定义只读变量

readonly valStr='hello world'

## 删除变量

# 注意：不能删除只读变量
unset str

### 字符串
echo ""

# 仅双引号里面可以出现变量，可以使用转义字符
# name='windea'
name="Windea"

## 拼接字符串

# 使用双引号拼接
greeting="hello, "$name"!"
# greeting="hello, ${name}!"
# 使用单引号拼接
# greeting='hello, '$name'!'
# greeting='hello, ${name}!'
echo $greeting

## 获取字符串长度

# 第一种方式
echo ${#name}
# 第二种方式
# 注意：运算符周围必须存在空格
expr length "$name"

## 截取字符串

var="hello world"
# 简单截取
# 从左边开始截取指定范围内的字符串
echo ${var:0:5}
# 从右边开始截取指定范围内的字符串
echo ${var:0-10:5}

# 根据表达式截取
# 规则
# * #   从左边开始删除到第一个指定的字符
# * ##  从左边开始删除到最后一个指定的字符
# * %   从右边开始删除到第一个指定的字符
# * %%  从右边开始删除到最后一个指定的字符

var="http://www.runoob.com/linux/linux-shell-variable.html"
echo ${var%%t*} # h
echo ${var%t*} # http://www.runoob.com/linux/linux-shell-variable.h
echo ${var%%.*} # http://www
echo ${var#*/} # /www.runoob.com/linux/linux-shell-variable.html
echo ${var##*/} # linux-shell-variable.html

## 查找子字符串

# 查找字符i或o的位置（最先出现的）
str="runoob is a great site"
expr index "$str" io # 4

### 数组
echo ""

# 仅支持一维数组，不限制数组的长度
# 数组下标从0开始
# 可以使用不连续的下标

array=(1 2 3 4 5)
array[5]=6
echo array # array

## 获取数组长度

echo ${#array[@]}
echo ${#array[*]}
expr length array

## 获取元素

echo ${array[0]}

## 删除元素

# unset array[2]

## 遍历数组

echo "遍历数组："
for a in ${array[*]}
do
	echo $a
done

### 运算符
echo ""

a=10
b=20

## 算术运算符

val=`expr $a + $b`
echo "a + b : $val"

val=`expr $a - $b`
echo "a - b : $val"

val=`expr $a \* $b`
echo "a * b : $val"

val=`expr $b / $a`
echo "b / a : $val"

val=`expr $b % $a`
echo "b % a : $val"

val=`expr $a == $b`
echo "a == b : $val" # 0

val=`expr $a != $b`
echo "a != b : $val" # 1

## 关系运算符

# 关系运算符只支持数字，不支持字符串，除非字符串的值是数字

# if [[ $a -eq $b ]]
if [[ $a == $b ]]
then
   echo "$a == $b : a 等于 b"
else
   echo "$a == $b: a 不等于 b"
fi
# if [[ $a -ne $b ]]
if [[ $a != $b ]]
then
   echo "$a != $b: a 不等于 b"
else
   echo "$a != $b : a 等于 b"
fi
# if [[ $a -gt $b ]]
if [[ $a > $b ]]
then
   echo "$a > $b: a 大于 b"
else
   echo "$a > $b: a 不大于 b"
fi
# if [[ $a -lt $b ]]
if [[ $a < $b ]]
then
   echo "$a < $b: a 小于 b"
else
   echo "$a < $b: a 不小于 b"
fi
if [[ $a -ge $b ]]
then
   echo "$a >= $b: a 大于或等于 b"
else
   echo "$a >= $b: a 小于 b"
fi
if [[ $a -le $b ]]
then
   echo "$a <= $b: a 小于或等于 b"
else
   echo "$a <= $b: a 大于 b"
fi

## 逻辑运算符

if [[ $a -lt 100 && $b -gt 100 ]]
then
   echo "true"
else
   echo "false"
fi

if [[ $a -lt 100 || $b -gt 100 ]]
then
   echo "true"
else
   echo "false"
fi

## 字符串运算符

a="abc"
b="efg"

if [[ $a = $b ]]
then
   echo "$a = $b : a 等于 b"
else
   echo "$a = $b: a 不等于 b"
fi
if [[ $a != $b ]]
then
   echo "$a != $b : a 不等于 b"
else
   echo "$a != $b: a 等于 b"
fi
if [[ -z $a ]]
then
   echo "-z $a : 字符串长度为 0"
else
   echo "-z $a : 字符串长度不为 0"
fi
if [[ -n "$a" ]]
then
   echo "-n $a : 字符串长度不为 0"
else
   echo "-n $a : 字符串长度为 0"
fi
if [[ $a ]]
then
   echo "$a : 字符串不为空"
else
   echo "$a : 字符串为空"
fi

## 文件测试运算符

file="test.sh"

if [[ -r $file ]]
then
   echo "文件可读"
else
   echo "文件不可读"
fi
if [[ -w $file ]]
then
   echo "文件可写"
else
   echo "文件不可写"
fi
if [[ -x $file ]]
then
   echo "文件可执行"
else
   echo "文件不可执行"
fi
if [[ -f $file ]]
then
   echo "文件为普通文件"
else
   echo "文件为特殊文件"
fi
if [[ -d $file ]]
then
   echo "文件是个目录"
else
   echo "文件不是个目录"
fi
if [[ -s $file ]]
then
   echo "文件不为空"
else
   echo "文件为空"
fi
if [[ -e $file ]]
then
   echo "文件存在"
else
   echo "文件不存在"
fi

### 常见命令

## echo命令

echo ""

echo "hello world"
str="hello"
echo "$str world"
# 接受读取的字符串
#read name
#echo "hello $name"
# 将打印结果保存到文件
echo "hello world" > test.txt

## printf命令

# format-string为双引号
printf "%d %s\n" 1 "abc"
# 单引号与双引号效果一样
printf '%d %s\n' 1 "abc"
# 没有引号也可以输出
printf %s abcdef
# 格式只指定了一个参数，但多出的参数仍然会按照该格式输出，format-string 被重用
printf %s abc def
printf "%s\n" abc def
printf "%s %s %s\n" a b c d e f g h i j
# 如果没有 arguments，那么 %s 用NULL代替，%d 用 0 代替
printf "%s and %d \n"

## test命令

# 示例
a="a"
b="b"
if test $a = $b
then
    echo '两个字符串相等!'
else
    echo '两个字符串不相等!'
fi

### 流程控制
echo ""

# 注意：流程控制语句不能为空

## if语句

a=10
b=20
if [[ $a == $b ]]
then
   echo "a 等于 b"
elif [[ $a > $b ]]
then
   echo "a 大于 b"
elif [[ $a < $b ]]
then
   echo "a 小于 b"
else
   echo "没有符合的条件"
fi

## for语句

for e in 1 2 3 4 5
do
    echo $e
done

for((i=1;i<=5;i++))
do
    echo "这是第 $i 次调用"
done

## while语句

int=1
while(( $int<=5 ))
do
    echo $int
    let "int++" # 用于执行一个或多个表达式，不需要$占位符
done

## while无限循环语句

# while true
# do
# 	...
# done

# until语句

a=0
until [[ ! $a < 10 ]]
do
   echo $a
   a=`expr $a + 1`
done

## case语句

site="runoob"
case "$site" in
   "runoob") echo "菜鸟教程"
   ;;
   "google") echo "Google 搜索"
   ;;
   "taobao") echo "淘宝网"
   ;;
esac

### 函数
echo ""

# 可以以function functionName()定义，也可以直接以functionName()定义
# 以return或者最后一行命令的值作为返回值
# 调用函数仅使用其函数名即可
# 调用函数后通过立刻$?得到返回值，仅对上一个调用命令有效
# 在函数内部，通过$n向函数产地参数，当n大于等于10时，需要写成${n}

# 特殊参数
# * $#	传递到脚本或函数的参数个数
# * $*	以一个单字符串显示所有向脚本传递的参数
# * $$	脚本运行的当前进程ID号
# * $!	后台运行的最后一个进程的ID号
# * $@	与$*相同，但是使用时加引号，并在引号中返回每个参数。
# * $-	显示Shell使用的当前选项，与set命令功能相同。
# * $?	显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误。

helloWorld(){
	echo "hello world"
}
helloWorld

hello(){
	echo "hello $1 and $2"
}
hello "Windea" "Windeath"

sum(){
	# return expr $1 + $2
	expr $1 + $2
}
sum 1 2
echo $?

### 输入输出重定向

# command > file	将输出重定向到 file。
# command < file	将输入重定向到 file。
# command >> file	将输出以追加的方式重定向到 file。
# n > file	将文件描述符为 n 的文件重定向到 file。
# n >> file	将文件描述符为 n 的文件以追加的方式重定向到 file。
# n >& m	将输出文件 m 和 n 合并。
# n <& m	将输入文件 m 和 n 合并。
# << tag	将开始标记 tag 和结束标记 tag 之间的内容作为输入。

### 文件包含

# 注意：被包含的文件不需要被执行的 权限

# 使用 . 号来引用test1.sh 文件
. ./test2.sh

# 或者使用以下包含文件代码
# source ./test1.sh
