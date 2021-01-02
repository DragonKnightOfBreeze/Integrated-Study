# 前置

## 参考链接

* [Git 教程 | 菜鸟教程](https://www.runoob.com/git/git-tutorial.html)
* [Git教程 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000)

# 学习笔记

Git生成并添加ssh key：

<https://www.cnblogs.com/mingyue5826/p/11141324.html>

Git忽略已提交的文件：

```
# 删除追踪状态
git rm -r --cached .
git add . 
git commit -m "fixed untracked files"
```
  
Git取消提交

```
git reset --soft <revision_number>
``` 
 
Git配置全局用户名和邮箱

```
git config  --global user.name xxx
git config  --global user.email xxx
```

Git修改push文件大小限制

```
git config --global http.receivepack true
git config --global http.postBuffer 157286400

# 或者：考虑使用ssh/https
```

```
Quick setup — if you’ve done this kind of thing before
or	
https://github.com/DragonKnightOfBreeze/GitBook-Study.git
Get started by creating a new file or uploading an existing file. We recommend every repository include a README, LICENSE, and .gitignore.

…or create a new repository on the command line
echo "# GitBook-Study" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/DragonKnightOfBreeze/GitBook-Study.git
git push -u origin main
                
…or push an existing repository from the command line
git remote add origin https://github.com/DragonKnightOfBreeze/GitBook-Study.git
git branch -M main
git push -u origin main
…or import code from another repository
You can initialize this repository with code from a Subversion, Mercurial, or TFS project.
```