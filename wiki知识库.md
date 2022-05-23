# 搭建SpringBoot项目

## 1. 新建SpringBoot项目

### 1.1 创建项目选择Spring Initializr填写项目信息

![image-20220515004920816](wiki知识库.assets/image-20220515004920816.png)

### 1.2 勾选需要的依赖创建项目

![image-20220515005051077](wiki知识库.assets/image-20220515005051077.png)



### 1.3 SpringBoot目录结构

![image-20220523105037470](wiki知识库.assets/image-20220523105037470.png)



**.idea: ** idea设置配置目录，idea特有，如果使用eclipse则没有该目录。

**.mvn:**  maven的配置目录，一般用不到，本地环境使用idea自带的maven。

**src: ** 项目目录，所有的代码存放在该目录下。

**WikiApplication: ** 项目启动入口。

**resources:**  静态资源存放目录。

**application.properties: ** 项目配置文件。

**test: ** 项目测试目录。

**wiki知识库.assets:**  wiki学习笔记图片目录

**.gitignore:**  git提交忽略文件配置。

**HELP.md:**   帮助文档。

**wiki.iml:**  项目工程配置文件。

**mvnw:**  maven配置文件，对应linux命令。

**mvnw.cmd:**  maven配置文件，对应windows命令。

**pom.xml:**  Maven管理文件，所有依赖都是通过这个文件进行管理的。

**wik知识库：** wiki学习笔记

**External Libraries:** 项目依赖。

### 1.4. 修改Spring Boot版本号

打开pom.xml文件进行修改：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.4.0</version>
    <relativePath/>
</parent>

```



## 2. 项目初始配置

### 2.1 编码配置

在IDEA左上角点击File -> settings，找到File Encodings，把所有可以更改UTF-8的地方全部改为UTF-8。

![image-20220523120724047](wiki知识库.assets/image-20220523120724047.png)



### 2.2 JDK配置

打开File -> Project Structure

![image-20220523120855280](wiki知识库.assets/image-20220523120855280.png)



### 2.3 Maven配置

#### 2.3.1 创建repository

在Maven目录中创建repository文件夹，该文件夹用来存放Maven下载的库文件。

#### 2.3.2 修改配置

打开config/settings.xml文件

- 修改第55行的标签内容，为本地电脑repository文件夹的路径：

```xml
<localRepository>G:\maven\repository</localRepository>

```

- 第160行~177行，已经配置好了阿里云仓库。Maven会自动从阿里云仓库下载Java项目依赖的库文件。如果不设置阿里云仓库，Maven会自动从国外服务器下载依赖库文件，速度会很慢：

```xml
<mirror>
	<id>alimaven</id>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    <mirrorOf>central</mirrorOf>
</mirror>
<mirror>
    <id>alimaven</id>
    <mirrorOf>central</mirrorOf>
    <name>aliyun maven</name>
    <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
</mirror>
<mirror>
    <id>repo2</id>
    <mirrorOf>central</mirrorOf>
    <name>Human Readable Name for this Mirror.</name>
    <url>http://repo2.maven.org/maven2/</url>
</mirror>

```

#### 2.3.3 在IDEA中修改配置

启动IDEA，打开Settings找到Maven，将选项和你本地的目录一一对应：

![image-20220523123720202](wiki知识库.assets/image-20220523123720202.png)



### 2.4 git配置

顶部工具栏找到VCS->Enable Version Control Intergration 然后选择git

![image-20220523144157492](wiki知识库.assets/image-20220523144157492.png)

随后项目目录颜色就发生了变化：

![image-20220523144241990](wiki知识库.assets/image-20220523144241990.png)



红色表示还没有交给git进行管理。



在IDEA左下角选择Commit，这时候发现有文件没有交给git进行管理：

![image-20220523152310521](wiki知识库.assets/image-20220523152310521.png)

这时候我们可以右键选择Add to VCS：

![image-20220523152333919](wiki知识库.assets/image-20220523152333919.png)

这样文件就会交给git进行管理了，文件也会变成绿色。

我们可以选中所有文件，在下方Commit Message填写想要提交的信息单击Commit就可以进行提交了。

![image-20220523152413138](wiki知识库.assets/image-20220523152413138.png)



### 2.5 使用IDEA连接Github

在IDEA打开File->Settings，找到Github：

![image-20220523152734314](wiki知识库.assets/image-20220523152734314.png)

单击+，选择log in via github在浏览器中点击即可。



点击顶部Git -> Github -> Share project on Github![image-20220523160051897](wiki知识库.assets/image-20220523160051897.png)



点击share即可在github创建同名远程库。

在IDEA中Ctrl+Shift+k即可调出push界面，点击push可以将本地仓库的代码上传到远程仓库。

































