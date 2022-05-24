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

## 3. 启动日志优化

### 3.1 logback日志样式修改

在resources目录下增加logback-spring.xml文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!-- 修改一下路径-->
    <property name="PATH" value="./log"></property>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <!--            <Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %highlight(%-5level) %blue(%-50logger{50}:%-4line) %thread %msg%n</Pattern>-->
            <Pattern>%d{ss.SSS} %highlight(%-5level) %blue(%-30logger{30}:%-4line) %thread %msg%n</Pattern>
        </encoder>
    </appender>

    <appender name="TRACE_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${PATH}/trace.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${PATH}/trace.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <layout>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level %-50logger{50}:%-4line %green(%-18X{LOG_ID}) %msg%n</pattern>
        </layout>
    </appender>

    <appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${PATH}/error.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <FileNamePattern>${PATH}/error.%d{yyyy-MM-dd}.%i.log</FileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <layout>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level %-50logger{50}:%-4line %green(%-18X{LOG_ID}) %msg%n</pattern>
        </layout>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <root level="ERROR">
        <appender-ref ref="ERROR_FILE" />
    </root>

    <root level="TRACE">
        <appender-ref ref="TRACE_FILE" />
    </root>

    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

在.gitignore文件中把log目录添加，以避免日志文件上传远程仓库：

```
/log/
```

### 3.2 修改启动文案

在启动类WikiApplication中添加启动成功日志：

![image-20220524134740712](wiki知识库.assets/image-20220524134740712.png)

在application.properties中添加一下代码：

```properties
# SpringBoot启动端口
server.port=8080
```

这样项目在启动的时候就会弹出启动成功显示端口号：

![image-20220524144639071](wiki知识库.assets/image-20220524144639071.png)

### 3.3 修改启动图案

自定义图案，在resources目录中新增banner.txt文件。

在线生成文字图案：http://patorjk.com/software/taag

将生成的文字图案粘贴在txt文件中即可。

例如：

```
                    _ooOoo_
                   o8888888o
                   88" . "88
                   (| -_- |)
                   O\  =  /O
                ____/`---'\____
              .'  \\|     |//  `.
             /  \\|||  :  |||//  \
            /  _||||| -:- |||||-  \
            |   | \\\  -  /// |   |
            | \_|  ''\---/''  |   |
            \  .-\__  `-`  ___/-. /
          ___`. .'  /--.--\  `. . __
       ."" '<  `.___\_<|>_/___.'  >'"".
      | | :  `- \`.;`\ _ /`;.`/ - ` : | |
      \  \ `-.   \_ __\ /__ _/   .-` /  /
 ======`-.____`-.___\_____/___.-`____.-'======
                    `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
              Buddha Bless, No Bug !
```

项目启动后图案就会修改：

![image-20220524145253177](wiki知识库.assets/image-20220524145253177.png)



## 4. 开发Hello World接口

1.  创建 `cn.ll.controller`包
2.  在 `cn.ll.controller`包下创建TestController类

```java
package cn.ll.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuli
 */
//@Controller
@RestController
public class TestController {

//  @RequestMapping("/hello")
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

}

```

运行启动类WikiApplication.java在浏览器输入localhost:8080/hello即可访问接口：

![image-20220325090647843](wiki知识库.assets/image-20220325090647843.png)



- @RestController注解用于声明返回文本数据，可以返回字符串或者JSON
- @Controller注解用于声明返回的界面（前后端分离项目基本用不到）
- @Controller + @ResponseBody = @RestController

Controller层是用于定义接口的，是请求的入口。

常见的HTTP请求：GET POST PUT DELETE

GET：查询

POST：新增

PUT：修改

DELETE：删除

对应的注解：

- @GetMapping
- @PostMapping
- @PutMapping
- @DeleteMapping

如果使用@RequestMapping注解，则表示这个接口支持所有的请求方式。

@GetMapping("/hello")

等价于

@RequestMapping(value="/hello", Method=RequestMethod.GET)

## 5. 更改启动类位置

1. 创建 `cn.ll.config` 包

2. 将LiuWikiApplication.java移动到该包下。

3. 添加@ComponentScan注解标注路径。以下是启动类：

   ```java
   package cn.ll.config;
   
   import org.slf4j.Logger;
   import org.slf4j.LoggerFactory;
   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;
   import org.springframework.context.annotation.ComponentScan;
   import org.springframework.core.env.Environment;
   
   @ComponentScan("cn.ll")
   @SpringBootApplication
   public class LiuWikiApplication {
   
       private static final Logger LOG = LoggerFactory.getLogger(LiuWikiApplication.class);
   
       public static void main(String[] args) {
           SpringApplication app = new SpringApplication(LiuWikiApplication.class);
           Environment env = app.run(args).getEnvironment();
           LOG.info("启动成功！！");
           LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
       }
   
   }
   
   ```

   

@SpringBootApplication注解包含@ComponentScan注解，而@ComponentScan注解只会扫描当前类下的包以及子包，扫描不到Controller层，所以更改启动类的位置需要重新填写需要扫描的路径。

需要注意的是，扫描的路径不要太通用，例如要扫描com.xx而不是com，如果单纯扫描com，会把第三方jar里的类扫描出来。

## 6. 使用IDEA自带的HTTP Client测试接口

1. 创建 `http` 包
2. 新建一个File，名称可以随意，后缀必须为http,例如：test.http。

以下是实例：

test.http

```
GET http://localhost:8080/hello
Accept: application/json


###
```

控制台会输出返回到的Hello World！

![image-20220524151756133](wiki知识库.assets/image-20220524151756133.png)

HTTP Client支持结果验证：

```
GET http://localhost:8080/hello
#Accept: application/json

> {%
client.test("test-hello", function() {
  client.log("测试/hello接口");
  client.log(response.body);
  client.log(JSON.stringify(response.body)); // 虽然idea没有提示JSON，但是可以用
  client.assert(response.status === 200, "返回码不是200");
  client.assert(response.body === "Hello World", "结果验证失败");
});
%}
###

POST http://localhost:8080/hello/post
Content-Type: application/x-www-form-urlencoded

name=TEST

###
```

![image-20220524152502288](wiki知识库.assets/image-20220524152502288.png)

## 7. SpringBoot配置文件

SpringBoot会自动识别下面这组配置文件：
application.properties/yml
config/application.properties/yml

如果是SpringCloud，还会自动识别下面这组配置文件：
bootstrap.properties/yml
config/bootstrap.properties/yml



配置文件优先级：

yml文件>properties

config目录下配置优先于其父目录。



自定义配置项：

在配置文件中定义：

```
test.hello = hello
```

在要读取的地方加上@Value注解即可读取。

```java
package cn.ll.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuli
 */
//@Controller
@RestController
public class TestController {

    @Value("${test.hello}")
    private String testHello;

//  @RequestMapping("/hello")
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "Hello World! Post，" + name;
    }

}

```

@Value支持默认值写法：

```
    @Value("${test.hello:test}")

```

如果配置文件中没有定义配置项，则显示默认值test，如果存在自定义配置项，则为配置项中的内容。

## 8. SpringBoot集成热部署

1. 引入依赖包，在pom.xml文件中添加：

   ```xml
    <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-devtools</artifactId>
           </dependency>
   ```

   SpringBoot内置的依赖不需要添加版本号

2. 开启静态自动编译，打开右上角File->Settings，找到Compiler，勾选上 `Build project automatically` :![image-20220325222645992](C:\Users\liu\AppData\Roaming\Typora\typora-user-images\image-20220325222645992.png)

3. 开启自动编译，开启静态自动编译，打开右上角File->Settings，找到 Advanced Settings，勾选Allow auto-make to start even if developed application is currently running

![image-20220524180235798](wiki知识库.assets/image-20220524180235798.png)
