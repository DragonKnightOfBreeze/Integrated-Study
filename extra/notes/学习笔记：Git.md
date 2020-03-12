# 前置

## 参考链接

* [Git 教程 | 菜鸟教程](https://www.runoob.com/git/git-tutorial.html)
* [Git教程 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)

# 学习笔记

Git忽略已提交的文件：

```
 #删除追踪状态
git rm -r --cached .
git add . 
git commit -m "fixed untracked files"
```
