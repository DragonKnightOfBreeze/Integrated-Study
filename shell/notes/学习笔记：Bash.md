# 准备

基本步骤

```
# 创建脚本
touch test.sh
# 赋予权限
chmod -x test.sh
# 编辑脚本
vim test.sh
# 运行脚本
sh test.sh
# 运行当前目录的脚本
./test.sh
```

在Windows上使用bash脚本
* 可以使用Git Bash运行bash脚本
* 可以使用`%GIT_HOME%\bin\sh.exe`作为bash脚本解释器
* 添加路径`%GIT_HOME%\bin`到PATH环境变量
* 首行注释为`#!/bin/sh`

# 注意事项

变量类型

* 自定义变量
* Linux环境变量：如`$PATH`，`$HOME`，使用`env`命令查看
* Shell变量：由Shell程序设置的特殊变量

常用环境变量
* `$PATH`
* `$HOME` 当前用户主目录
* `$SHELL` 当前Shell类型
* `$HISTSIZE` 历史记录数
* `$LOGNAME` 当前用户的登录名
* `$HOSTNAME` 指主机的名称
* `$LANGUGE` 语言相关的环境变量，多语言可以修改此环境变量
* `$MAIL` 当前用户的邮件存放目录
* `$PS1` 基本提示符，对于root用户是#，对于普通用户是$

其他
* 如果脚本中有空行，可能会出现`: command not found`的警告
* linux命令最好放到`$()`中执行，否则可能会出现权限错误
* 使用`sh -n xxx.sh`快速检查脚本中的错误
* 在vim中设置脚本的`:set ff=unix`，否则可能会出现`syntax error: unexpected end of file`的警告
