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

打开`pom.xml`文件进行修改：

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

在IDEA左上角点击`File -> settings`，找到`File Encodings`，把所有可以更改`UTF-8`的地方全部改为`UTF-8`。

![image-20220523120724047](wiki知识库.assets/image-20220523120724047.png)



### 2.2 JDK配置

打开`File -> Project Structure`

![image-20220523120855280](wiki知识库.assets/image-20220523120855280.png)



### 2.3 Maven配置

#### 2.3.1 创建repository

在`Maven`目录中创建`repository`文件夹，该文件夹用来存放`Maven`下载的库文件。

#### 2.3.2 修改配置

打开`config/settings.xml`文件

- 修改第55行的标签内容，为本地电脑`repository`文件夹的路径：

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

启动IDEA，打开`Settings`找到`Maven`，将选项和你本地的目录一一对应：

![image-20220523123720202](wiki知识库.assets/image-20220523123720202.png)



### 2.4 git配置

顶部工具栏找到`VCS->Enable Version Control Intergration` 然后选择git

![image-20220523144157492](wiki知识库.assets/image-20220523144157492.png)

随后项目目录颜色就发生了变化：

![image-20220523144241990](wiki知识库.assets/image-20220523144241990.png)



红色表示还没有交给git进行管理。



在IDEA左下角选择`Commit`，这时候发现有文件没有交给git进行管理：

![image-20220523152310521](wiki知识库.assets/image-20220523152310521.png)

这时候我们可以右键选择`Add to VCS`：

![image-20220523152333919](wiki知识库.assets/image-20220523152333919.png)

这样文件就会交给git进行管理了，文件也会变成绿色。

我们可以选中所有文件，在下方`Commit Message`填写想要提交的信息单击`Commit`就可以进行提交了。

![image-20220523152413138](wiki知识库.assets/image-20220523152413138.png)



### 2.5 使用IDEA连接Github

在IDEA打开`File->Settings`，找到Github：

![image-20220523152734314](wiki知识库.assets/image-20220523152734314.png)

单击+，选择`log in via github`在浏览器中点击即可。



点击顶部`Git -> Github -> Share project on Github`![image-20220523160051897](wiki知识库.assets/image-20220523160051897.png)



点击`share`即可在github创建同名远程库。

在IDEA中Ctrl+Shift+k即可调出push界面，点击push可以将本地仓库的代码上传到远程仓库。

## 3. 启动日志优化

### 3.1 logback日志样式修改

在`resources`目录下增加`logback-spring.xml`文件：

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

在`.gitignore`文件中把`log`目录添加，以避免日志文件上传远程仓库：

```
/log/
```

### 3.2 修改启动文案

在启动类`WikiApplication`中添加启动成功日志：

![image-20220524134740712](wiki知识库.assets/image-20220524134740712.png)

在`application.properties`中添加一下代码：

```properties
# SpringBoot启动端口
server.port=8080
```

这样项目在启动的时候就会弹出启动成功显示端口号：

![image-20220524144639071](wiki知识库.assets/image-20220524144639071.png)

### 3.3 修改启动图案

自定义图案，在`resources`目录中新增`banner.txt`文件。

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
2.  在 `cn.ll.controller`包下创建`TestController`类

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

运行启动类`WikiApplication.java`在浏览器输入localhost:8080/hello即可访问接口：

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

2. 将`LiuWikiApplication.java`移动到该包下。

3. 添加`@ComponentScan`注解标注路径。以下是启动类：

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

   

`@SpringBootApplication`注解包含`@ComponentScan`注解，而`@ComponentScan`注解只会扫描当前类下的包以及子包，扫描不到Controller层，所以更改启动类的位置需要重新填写需要扫描的路径。

需要注意的是，扫描的路径不要太通用，例如要扫描`com.xx`而不是`com`，如果单纯扫描`com`，会把第三方`jar`里的类扫描出来。

## 6. 使用IDEA自带的HTTP Client测试接口

1. 创建 `http` 包
2. 新建一个`File`，名称可以随意，后缀必须为`http`，例如：test.http。

以下是实例：

test.http

```
GET http://localhost:8080/hello
Accept: application/json


###
```

控制台会输出返回到的Hello World！

![image-20220524151756133](wiki知识库.assets/image-20220524151756133.png)

`HTTP Client`支持结果验证：

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

`SpringBoot`会自动识别下面这组配置文件：
application.properties/yml
config/application.properties/yml

如果是`SpringCloud`，还会自动识别下面这组配置文件：
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

在要读取的地方加上`@Value`注解即可读取。

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

`@Value`支持默认值写法：

```
    @Value("${test.hello:test}")

```

如果配置文件中没有定义配置项，则显示默认值test，如果存在自定义配置项，则为配置项中的内容。

## 8. SpringBoot集成热部署

1. 引入依赖包，在`pom.xml`文件中添加：

   ```xml
    <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-devtools</artifactId>
           </dependency>
   ```

   SpringBoot内置的依赖不需要添加版本号

2. 开启静态自动编译，打开右上角`File->Settings`，找到`Compiler`，勾选上 `Build project automatically` :![image-20220325222645992](C:\Users\liu\AppData\Roaming\Typora\typora-user-images\image-20220325222645992.png)

3. 开启自动编译，开启静态自动编译，打开右上角`File->Settings`，找到 `Advanced Settings`，勾选`Allow auto-make to start even if developed application is currently running`

![image-20220524180235798](wiki知识库.assets/image-20220524180235798.png)

# 后端架构完善与接口开发

## 1. IDEA数据库插件配置

右侧栏找到`Database`，点击加号，选择`Data Source->Mysql`:

![image-20220525113440875](wiki知识库.assets/image-20220525113440875.png)

填写地址以及用户名密码和所选数据库：

![image-20220525113528398](wiki知识库.assets/image-20220525113528398.png)

随后点击`Test Connection`，第一次需要下载相关驱动。

测试连接成功后点击ok即可。

新建测试表：

```sql
drop table if exists `test`;
create table `test` (
  `id` bigint not null comment 'id',
  `name` varchar(50) comment '名称',
  `password` varchar(50) comment '密码',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试';
```

全选运行即可执行：

![image-20220525114418029](wiki知识库.assets/image-20220525114418029.png)

在项目根目录新建doc文件夹，然后新建 `all.sql` 文件用来存放数据库脚本：

```sql
drop table if exists `test`;
create table `test` (
        `id` bigint not null comment 'id',
        `name` varchar(50) comment '名称',
        `password` varchar(50) comment '密码',
        primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试';
```

以后需要修改数据库信息可以在这里面填写sql语句执行，不需要切屏到数据库操作软件，很方便。

## 2. 集成持久层框架Mybatis

### 2.1 添加依赖

在`pom.xml`中添加依赖：

```xml
   <!-- 集成mybatis-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.3</version>
        </dependency>
        <!-- 集成mysql连接 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.22</version>
        </dependency>
```

### 2.2 配置数据源

在`application.properties`中添加数据库连接：

```properties
# 增加数据库连接
spring.datasource.url=jdbc:mysql://localhost:3306/wiki?characterEncoding=UTF8&autoReconnect=true&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=Hlsdzpdw123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```



### 2.3. Mybatis示例

#### 2.3.1 创建实体类

在`cn.ll`目录下新建 `domain` 包，创建 `Test` 实体类

```java
package cn.ll.domain;

/**
 * @author liu
 */
public class Test {

    private String name;

    private Integer id;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

```

#### 2.3.2 新建mapper包

在`cn.ll`目录下新建`mapper`包，创建`TestMapper`接口，在启动类中添加注解

`TestMapper.java`：

```java
package cn.ll.mapper;

import cn.ll.domain.Test;

import java.util.List;

public interface TestMapper {
    public List<Test> list();
}

```

在启动类中添加`@MapperScan`注解：

```java
@MapperScan("cn.ll.mapper")

```

`@MapperScan`作用：指定要变成实现类的接口所在的包，然后包下面的所有接口在编译之后都会生成相应的实现类。



#### 2.3.3 mapper映射文件

在`resources`目录下新建`mapper`目录，新建`TestMapper.xml`:

```xml
<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="cn.ll.mapper.TestMapper">

    <select id="list" resultType="cn.ll.domain.Test">
        select
            `id`, `name`, `password`
        from
            `test`
    </select>


</mapper>

```

在`application.properties`中配置`mybatis`所有`Mapper.xml`所在路径：

```properties
# 配置mybatis所有Mapper.xml所在的路径
mybatis.mapper-locations=classpath:/mapper/**/*.xml
```

`mybatis.mapper-locations`在`SpringBoot`配置文件中使用，作用是扫描`Mapper`接口对应的xml文件。

#### 2.3.4 新建service层

在`cn.ll`目录下新建`service`包，在`service`包下新建`TestService.java`:

```java
package cn.ll.service;

import cn.ll.domain.Test;
import cn.ll.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    //  @Autowired
    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }

}
```

这里可以使用`@Autowired`也可以使用`@Resource`。

`@Autowired`是spring注解，`@Resource`是jdk注解，推荐@Resource。

#### 2.3.5 在controller层中新增get请求

在`TestController.java`中注入`testService`并添加list方法：

```java
@Resource
    private TestService testService;



@GetMapping("/test/list")
    public List<Test> list(){
        return testService.list();
    }
```

#### 2.3.6 在数据库中添加数据

```sql
insert into `test` (id, name, password) VALUES (1, '测试', 'password');
```

#### 2.3.7 使用测试脚本测试

```http
GET http://localhost:8080/test/list

```

运行结果：

![image-20220525152736322](wiki知识库.assets/image-20220525152736322.png)

### 2.4 集成官方Mybatis代码生成器

#### 2.4.1 集成Mybatis Generator

##### 2.4.1.1 添加依赖

在`pom.xml`中添加依赖：

```xml
  <!-- mybatis generator 自动生成代码插件 -->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.4.0</version>
                <configuration>
                    <configurationFile>src/main/resources/generator/generator-config.xml</configurationFile>
                    <overwrite>true</overwrite>
                    <verbose>true</verbose>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.22</version>
                    </dependency>
                </dependencies>
            </plugin>
```

##### 2.4.1.2 新建配置文件

在`resources`目录下新建`generator`目录，存放配置文件`generator-config.xml`：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <context id="Mysql" targetRuntime="MyBatis3" defaultModelType="flat">

        <!-- 自动检查关键字，为关键字增加反引号 -->
        <property name="autoDelimitKeywords" value="true"/>
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>

        <!--覆盖生成XML文件-->
        <plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin" />
        <!-- 生成的实体类添加toString()方法 -->
        <plugin type="org.mybatis.generator.plugins.ToStringPlugin"/>

        <!-- 不生成注释 -->
<!--        <commentGenerator>-->
<!--            <property name="suppressAllComments" value="true"/>-->
<!--        </commentGenerator>-->

        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/wikidev?serverTimezone=Asia/Shanghai"
                        userId="root"
                        password="Hlsdzpdw123">
        </jdbcConnection>

        <!-- domain类的位置 -->
        <javaModelGenerator targetProject="src\main\java"
                            targetPackage="cn.ll.domain"/>

        <!-- mapper xml的位置 -->
        <sqlMapGenerator targetProject="src\main\resources"
                         targetPackage="mapper"/>

        <!-- mapper类的位置 -->
        <javaClientGenerator targetProject="src\main\java"
                             targetPackage="cn.ll.mapper"
                             type="XMLMAPPER"/>

        <table tableName="demo" domainObjectName="Demo"/>
    </context>
</generatorConfiguration>
```

##### 2.4.1.3 新建demo表

在`all.sql`中添加以下代码：

```sql
drop table if exists `demo`;
create table `demo` (
                        `id` bigint not null comment 'id',
                        `name` varchar(50) comment '名称',
                        primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试';

insert into `demo` (id, name) values (1, '测试');
```

##### 2.4.1.4 新增启动命令

 项目运行位置点击`Edit Configurations`

![image-20220526111408443](wiki知识库.assets/image-20220526111408443.png)

右上角点击+，选择`maven`，在`Command line`位置填写`mybatis-generator:generate -e`：

![image-20220526120017786](wiki知识库.assets/image-20220526120017786.png)

点击项目运行即可生成文件

![image-20220526120433572](wiki知识库.assets/image-20220526120433572.png)

#### 2.4.2 示例

##### 2.4.2.1 新建Service层代码

新建`DemoService.java`：

```java
package cn.ll.service;

import cn.ll.domain.Demo;
import cn.ll.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {
    
    private DemoMapper demoMapper;
    
    public List<Demo> list(){
        return demoMapper.selectByExample(null);
    }
    
}

```



##### 2.4.2.2 新建Controller层代码

新建`DemoController.java`:

```java
package cn.ll.controller;

import cn.ll.domain.Demo;
import cn.ll.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuli
 */
//@Controller
@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/demo/list")
    public List<Demo> list(){
        return demoService.list();
    }

}
```

##### 2.4.2.3 新建demo测试脚本

在`http`目录下新建`demo.http`:

```http
GET http://localhost:8080/demo/list

```

运行结果：

![image-20220526121248861](wiki知识库.assets/image-20220526121248861.png)



## 3. 电子书列表查询接口开发

### 3.1 电子书表结构设计

```sql
# 电子书表
drop table if exists `ebook`;
create table `ebook` (
  `id` bigint not null comment 'id',
  `name` varchar(50) comment '名称',
  `category1_id` bigint comment '分类1',
  `category2_id` bigint comment '分类2',
  `description` varchar(200) comment '描述',
  `cover` varchar(200) comment '封面',
  `doc_count` int comment '文档数',
  `view_count` int comment '阅读数',
  `vote_count` int comment '点赞数',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='电子书';

insert into `ebook` (id, name, description) values (1, 'Spring Boot 入门教程', '零基础入门 Java 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (2, 'Vue 入门教程', '零基础入门 Vue 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (3, 'Python 入门教程', '零基础入门 Python 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (4, 'Mysql 入门教程', '零基础入门 Mysql 开发，企业级应用开发最佳首选框架');
insert into `ebook` (id, name, description) values (5, 'Oracle 入门教程', '零基础入门 Oracle 开发，企业级应用开发最佳首选框架');

```

### 3.2 使用代码生成器生成Ebook相关代码

修改`generator-config.xml`文件中的`table`：

```xml
        <table tableName="ebook" domainObjectName="Ebook"/>

```

点击运行生成`ebook`相关代码。

### 3.3 新建service层代码

新建`EbookService.java`:

```java
package cn.ll.service;

import cn.ll.domain.Ebook;
import cn.ll.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;



    public List<Ebook> list(){
        return ebookMapper.selectByExample(null);
    }


}

```

### 3.4 新建Controller层代码

新建`EbookController.java`：

```java
package cn.ll.controller;

import cn.ll.domain.Ebook;
import cn.ll.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public List<Ebook> list(){
        return ebookService.list();
    }


}

```

### 3.5 新建测试脚本ebook.http

在`http`目录下新建`ebook.http`:

```http
GET http://localhost:8080/ebook/list

```

运行结果：

![image-20220526162415133](wiki知识库.assets/image-20220526162415133.png)



## 4. 封装通用返回类CommonResp

后端会有很多接口，为了让前端能够统一处理多级（登录校验、权限管理），需要统一后端的返回值。

### 4.1 新建resp包，创建CommonResp类

`resp`是`response`的缩写

```java
public class CommonResp<T> {

    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    private T content;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseDto{");
        sb.append("success=").append(success);
        sb.append(", message='").append(message).append('\'');
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }
}
```



### 4.2 改造Centroller层代码

```java
package cn.ll.controller;

import cn.ll.domain.Ebook;
import cn.ll.resp.CommonResp;
import cn.ll.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp list(){
        CommonResp<List<Ebook>> resp = new CommonResp<>();
        List<Ebook> list = ebookService.list();
        resp.setContent(list);
        return resp;
    }


}

```

运行测试脚本得到的返回值：

![image-20220527141352529](wiki知识库.assets/image-20220527141352529.png)

这样做的好处是前端可以根据返回值获取想要的东西。

以后所有后端的返回值全部都是`CommonResp`，只有`Content`是不一样的。



## 5. 根据名称模糊查询

### 5.1 修改Controller层代码

修改`EbookController.java`中的参数：

```java
package cn.ll.controller;

import cn.ll.domain.Ebook;
import cn.ll.resp.CommonResp;
import cn.ll.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp list(String name){
        CommonResp<List<Ebook>> resp = new CommonResp<>();
        List<Ebook> list = ebookService.list();
        resp.setContent(list);
        return resp;
    }


}

```

### 5.2 修改Service层代码

修改`EbookService.java`：

```java
package cn.ll.service;

import cn.ll.domain.Ebook;
import cn.ll.domain.EbookExample;
import cn.ll.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;



    public List<Ebook> list(String name){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + name + "%");
        return ebookMapper.selectByExample(ebookExample);
    }


}

```

`Criteria`相当于`Where`条件。

不管哪张表的`Example`，一下两行写法是固定的：

![image-20220530161057262](wiki知识库.assets/image-20220530161057262.png)



运行测试结果，`name`传值`spring`，运行结果：

![image-20220530161316261](wiki知识库.assets/image-20220530161316261.png)



## 6. 封装请求参数

### 6.1 新建req包，创建EbookReq类

`req`是`request`的缩写。

`EbookReq.java`:

```java
package cn.ll.req;

public class EbookReq {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}

```

`toString`主要是用来打印日志的。

### 6.2 修改Controller层代码

修改`EbookController.java`:

```java
package cn.ll.controller;

import cn.ll.domain.Ebook;
import cn.ll.req.EbookReq;
import cn.ll.resp.CommonResp;
import cn.ll.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp list(EbookReq req){
        CommonResp<List<Ebook>> resp = new CommonResp<>();
        List<Ebook> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }


}

```

### 6.3 修改Service层代码

修改`EbookService.java`:

```java
package cn.ll.service;

import cn.ll.domain.Ebook;
import cn.ll.domain.EbookExample;
import cn.ll.mapper.EbookMapper;
import cn.ll.req.EbookReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;



    public List<Ebook> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        return ebookMapper.selectByExample(ebookExample);
    }


}

```

运行测试脚本同样可以查询到结果：

![image-20220530170455005](wiki知识库.assets/image-20220530170455005.png)



`Spring`会自动将参数映射到类属性。

##  7. 封装返回参数

### 7.1 新建EbookResp类

修改`EbookResp.java`:

```java
package cn.ll.resp;

public class EbookResp {
    private Long id;

    private String name;

    private Long category1Id;

    private Long category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategory1Id() {
        return category1Id;
    }

    public void setCategory1Id(Long category1Id) {
        this.category1Id = category1Id;
    }

    public Long getCategory2Id() {
        return category2Id;
    }

    public void setCategory2Id(Long category2Id) {
        this.category2Id = category2Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getDocCount() {
        return docCount;
    }

    public void setDocCount(Integer docCount) {
        this.docCount = docCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", category1Id=").append(category1Id);
        sb.append(", category2Id=").append(category2Id);
        sb.append(", description=").append(description);
        sb.append(", cover=").append(cover);
        sb.append(", docCount=").append(docCount);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", voteCount=").append(voteCount);
        sb.append("]");
        return sb.toString();
    }
}
```

### 7.2 修改Service层代码

修改`EbookService.java`:

```
package cn.ll.service;

import cn.ll.domain.Ebook;
import cn.ll.domain.EbookExample;
import cn.ll.mapper.EbookMapper;
import cn.ll.req.EbookReq;
import cn.ll.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;



    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook, ebookResp);
            respList.add(ebookResp);
        }

        return respList;

    }


}

```

持久层返回`List<Ebook>`需要转成`List<EbookResp>`在返回`controller`。

### 7.3 修改Controller层代码

修改`EbookController.java`:

```java
package cn.ll.controller;

import cn.ll.req.EbookReq;
import cn.ll.resp.CommonResp;
import cn.ll.resp.EbookResp;
import cn.ll.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;


    @GetMapping("/list")
    public CommonResp list(EbookReq req){
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }


}

```

运行测试脚本同样可以查询到结果。



## 8. 制作CopyUtil封装BeanUtils

### 8.1 新建CopyUtil类

新建`util`包，创建`CopyUtil`：

```java
public class CopyUtil {

    /**
     * 单体复制
     */
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

    /**
     * 列表复制
     */
    public static <T> List<T> copyList(List source, Class<T> clazz) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object c: source) {
                T obj = copy(c, clazz);
                target.add(obj);
            }
        }
        return target;
    }
}

```



### 8.2 实际用法

修改`EbookService.java`:

```java
package cn.ll.service;

import cn.ll.domain.Ebook;
import cn.ll.domain.EbookExample;
import cn.ll.mapper.EbookMapper;
import cn.ll.req.EbookReq;
import cn.ll.resp.EbookResp;
import cn.ll.util.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuli
 */
@Service
public class EbookService {

//  @Autowired
    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");

        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        // List<EbookResp> respList = new ArrayList<>();
        // for (Ebook ebook : ebookList) {
        //     // EbookResp ebookResp = new EbookResp();
        //     // BeanUtils.copyProperties(ebook, ebookResp);
        //     // 对象复制
        //     EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
        //
        //     respList.add(ebookResp);
        // }

        // 列表复制
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);
        return list;
    }

}

```







# Vue + Vue CLI项目搭建

## 1. Vue和Vue CLI是什么

- Vue.js是构建用户界面的渐进式框架。

  Vue3文档 - vuejs
  https://www.vue3js.cn/docs/zh/

  Vue核心功能：数据绑定

- Vue CLI是基于快速开发的完整系统。

  Vue CLI文档
  https://cli.vuejs.org/zh/guide/

  Vue CLI = Vue.js + 一堆组件

## 2. 创建Vue CLI项目

### 2.1 安装Vue CLI

在IDEA下方点击`Terminal`， 这个`Terminal`就相当于我们`windows`的`cmd`命令行，在命令行中输入以下代码用来更改镜像查看镜像：

```
npm get registry  --显示当前的镜像网址
npm config set registry http://registry.npm.taobao.org  -- 使用淘宝的镜像网址
如果不行，改成用https试试
```

![image-20220531160822783](wiki知识库.assets/image-20220531160822783.png)

安装`Vue CLI`命令：

```
npm install -g @vue/cli@4.5.9

```

### 2.2 创建web应用

创建`web`命令：

```
vue create web

```



![image-20220330133118225](wiki知识库.assets/image-20220330133118225.png)

选择最下面的选项  `Manually select features`手动选择功能

勾选`TypeScript`、`Router`（页面路由）、`vuex`（用于全局变量）。

空格选中，回车确定。

![image-20220330133602554](wiki知识库.assets/image-20220330133602554.png)

选择3.x。

![image-20220330133715074](wiki知识库.assets/image-20220330133715074.png)

`pick a linter/formatter config` 选择`ESLint with error prevention only`，其余按照下方选择：

![image-20220330143711355](wiki知识库.assets/image-20220330143711355.png)

### 2.3 启动web应用

找到`web`->`package.json`，右键点击`Show npm Scripts`。

![image-20220531202021780](wiki知识库.assets/image-20220531202021780.png)

双击`serve`即可启动`web`应用。

![image-20220531202150701](wiki知识库.assets/image-20220531202150701.png)

## 3. Vue CLI项目结构讲解

![image-20220601183924562](wiki知识库.assets/image-20220601183924562.png)

**web**： `web`文件夹就是我们上节课创建的`web`项目，所有的代码都存放在其中。

**node_modules**： 这个文件夹存放的是整个`web`项目依赖的所有`JS`模块。

**public**： 启动的首页就放在该文件夹下。`favicon.ico`是一个`vue`图标，它相当于一个静态资源，如果想要引用静态资源可以参考`index.html`中的以下代码：

```vue
    <link rel="icon" href="<%= BASE_URL %>favicon.ico">

```

**src**： 以后所写的vue代码全部存放在该目录下。

**assets**： 静态资源目录，静态资源可以存放在该目录和`public`目录下。这里的静态资源引用和`public`目录下的不同。

**components**： 组件目录，以后会写很多组件都会存放在该目录下。

**router**： 路由目录。

**store**： 用于全局存数据。

**views**： 页面目录。

**App.vue**： `App.vue`是初始内容页面。

**main.ts**： 初始启动（配置）文件。`Vue CLI`初始执行`main.ts`，将内容也`App.vue`渲染到`index.html`，完成页面显示。

**shims-vue.d.ts**： 定义文件，平时不需要去管。

**.browserslistrc**：  浏览器兼容，平时不需要去管。

**.eslintrc.js**： 语法检查插件。

**.gitignore**： 配置不需要交给git管理的文件。**任意目录下都可以增加`.gitignore`**。

**package.json**： 相当于`pom.xml`，定义了所有依赖的`JS`。

**package-lock.json**： 用于锁定小的版本号。

**README.md**： 说明文件，在子目录没什么用。

**tsconfig.json**： 整个项目的配置文件。

以后项目开发主要集中在`src`文件夹里。





## 4. 集成Ant Design Vue

### 4.1 Ant Design Vue简介

`ant-design-vue`是蚂蚁金服 `Ant Design` 官方唯一推荐的Vue版UI组件库，它其实是`Ant Design`的`Vue`实现，组件的风格与`Ant Design`保持同步，组件的`html`结构和`css`样式也保持一致。 



### 4.2 集成Ant Design Vue

打开`IDEA`自带的控制台，进入`web`目录：

```
cd web
```

然后输入安装命令：

```
npm install ant-design-vue --save

```



### 4.3 按钮示例

我们要使用组件需要引入组件，我们可以要完整引入需要在`main.ts`中添加代码：

```
import Antd from 'ant-design-vue'; 
import 'ant-design-vue/dist/antd.css';
use(Antd)
```

第一行是引入`ant-design-vue`，第二行是引入`ant-design-vue`的`css`样式，最后一行是使用`ant-design-vue`组件。

![image-20220602202501897](wiki知识库.assets/image-20220602202501897.png)

接下来可以去官网中选择你自己想要的按钮样式复制下来，在`home`页面添加：

按钮样式：

```vue
<a-button type="primary">Primary Button</a-button>
```

完整`home.vue`：

```vue
<template>
  <div class="home">
    <a-button type="primary">Primary Button</a-button>
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'Home',
  components: {
    HelloWorld,
  },
});
</script>

```

这样运行项目可以看到我们的按钮就添加好了。

![image-20220602203001328](wiki知识库.assets/image-20220602203001328.png)







## 5. 网站首页布局开发

### 5.1 网站首页示例介绍

网站仿照慕课网`wiki`：http://www.imooc.com/wiki/

网站最上方有一个头部。

头部下边的左边是分类（一级分类），右边是上边是二级分类，二级分类下边是具体课程。

![image-20220602211946354](wiki知识库.assets/image-20220602211946354.png)

网站最下面有个页脚。

![image-20220602212125828](wiki知识库.assets/image-20220602212125828.png)

### 5.2 加入Ant Design Vue布局

在`Ant Design Vue`官网中选择合适的布局。

我们可以观察到下面图片中的布局和我们的预期大致相符，我们可以复制其中`<a-layout>`标签粘贴到`App.vue`中。

![image-20220603001126068](wiki知识库.assets/image-20220603001126068.png)

```vue
 <a-layout>
    <a-layout-header class="header">
      <div class="logo" />
      <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
      >
        <a-menu-item key="1">nav 1</a-menu-item>
        <a-menu-item key="2">nav 2</a-menu-item>
        <a-menu-item key="3">nav 3</a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout>
      <a-layout-sider width="200" style="background: #fff">
        <a-menu
          v-model:selectedKeys="selectedKeys2"
          v-model:openKeys="openKeys"
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
        >
          <a-sub-menu key="sub1">
            <template #title>
              <span>
                <user-outlined />
                subnav 1
              </span>
            </template>
            <a-menu-item key="1">option1</a-menu-item>
            <a-menu-item key="2">option2</a-menu-item>
            <a-menu-item key="3">option3</a-menu-item>
            <a-menu-item key="4">option4</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub2">
            <template #title>
              <span>
                <laptop-outlined />
                subnav 2
              </span>
            </template>
            <a-menu-item key="5">option5</a-menu-item>
            <a-menu-item key="6">option6</a-menu-item>
            <a-menu-item key="7">option7</a-menu-item>
            <a-menu-item key="8">option8</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub3">
            <template #title>
              <span>
                <notification-outlined />
                subnav 3
              </span>
            </template>
            <a-menu-item key="9">option9</a-menu-item>
            <a-menu-item key="10">option10</a-menu-item>
            <a-menu-item key="11">option11</a-menu-item>
            <a-menu-item key="12">option12</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-sider>
      <a-layout style="padding: 0 24px 24px">
        <a-breadcrumb style="margin: 16px 0">
          <a-breadcrumb-item>Home</a-breadcrumb-item>
          <a-breadcrumb-item>List</a-breadcrumb-item>
          <a-breadcrumb-item>App</a-breadcrumb-item>
        </a-breadcrumb>
        <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
        >
          Content
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>
```

然后复制其`css`样式：

```vue
<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;vue
}
</style>
```

由于该样式没有页脚，所以我们还需要去官网找到页脚的样式添加：

```vue
<a-layout-footer :style="{ textAlign: 'center' }">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer>
```

我们可以在`IDEA`中左下角找到`Structure`，点击我们可以看到页面结构：

![image-20220603043452705](wiki知识库.assets/image-20220603043452705.png)

我们需要折叠`header`和中间部分，把我们的页脚部分粘贴到中间部分（`a-layout`）的下方。

这样我们的首页就会变成这个样子：

![image-20220603043808888](wiki知识库.assets/image-20220603043808888.png)

我们可以发现分类和内容比例不协调，我们可以改造一下代码，同时删除面包屑导航。

![image-20220603044125548](wiki知识库.assets/image-20220603044125548.png)

可以看到中间部分内容分为两部分，左边和右边。而右边同样有两部分，一个是面包屑导航，一个是内容。我们可以复制内容部分，然后删除右边整个大块的部分，把复制的内容代替删除的部分。这样就变成了左边的分类和右边的`Content`内容部分。

![image-20220603044532403](wiki知识库.assets/image-20220603044532403.png)

具体的页面也就变成了这样：

![image-20220603044551928](wiki知识库.assets/image-20220603044551928.png)



完整的`App.vue`：

```vue
<template>
  <a-layout>
    <a-layout-header class="header">
      <div class="logo" />
      <a-menu
          v-model:selectedKeys="selectedKeys1"
          theme="dark"
          mode="horizontal"
          :style="{ lineHeight: '64px' }"
      >
        <a-menu-item key="1">nav 1</a-menu-item>
        <a-menu-item key="2">nav 2</a-menu-item>
        <a-menu-item key="3">nav 3</a-menu-item>
      </a-menu>
    </a-layout-header>
    <a-layout>
      <a-layout-sider width="200" style="background: #fff">
        <a-menu
            v-model:selectedKeys="selectedKeys2"
            v-model:openKeys="openKeys"
            mode="inline"
            :style="{ height: '100%', borderRight: 0 }"
        >
          <a-sub-menu key="sub1">
            <template #title>
              <span>
                <user-outlined />
                subnav 1
              </span>
            </template>
            <a-menu-item key="1">option1</a-menu-item>
            <a-menu-item key="2">option2</a-menu-item>
            <a-menu-item key="3">option3</a-menu-item>
            <a-menu-item key="4">option4</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub2">
            <template #title>
              <span>
                <laptop-outlined />
                subnav 2
              </span>
            </template>
            <a-menu-item key="5">option5</a-menu-item>
            <a-menu-item key="6">option6</a-menu-item>
            <a-menu-item key="7">option7</a-menu-item>
            <a-menu-item key="8">option8</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub3">
            <template #title>
              <span>
                <notification-outlined />
                subnav 3
              </span>
            </template>
            <a-menu-item key="9">option9</a-menu-item>
            <a-menu-item key="10">option10</a-menu-item>
            <a-menu-item key="11">option11</a-menu-item>
            <a-menu-item key="12">option12</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-sider>
      <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
        Content
      </a-layout-content>
    </a-layout>
    <a-layout-footer :style="{ textAlign: 'center' }">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer>
  </a-layout>
</template>

<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>


```



### 5.3 加入首页路由

要完成路由功能，需要有以下两个标签：

**router-link to**： 这个是路由跳转，标签就相当于`a`标签，`router-link = a`， `to = href`。

**router-view**： 用来填充路由内容。

网站都有页头和页脚，这两部分都是固定的。既然`router-view`可以用来填充内容，那我们可以使用`router-view`来填充网站中间动态的部分。

我们可以剪切中间部分的代码，粘贴到`home.vue`中，在被剪切的位置使用`router-view`填充。

`App.vue`：

```vue
<template>
  <a-layout>
    <a-layout-header class="header">
      <div class="logo" />
      <a-menu
          v-model:selectedKeys="selectedKeys1"
          theme="dark"
          mode="horizontal"
          :style="{ lineHeight: '64px' }"
      >
        <a-menu-item key="1">nav 1</a-menu-item>
        <a-menu-item key="2">nav 2</a-menu-item>
        <a-menu-item key="3">nav 3</a-menu-item>
      </a-menu>
    </a-layout-header>
    <router-view/>
    <a-layout-footer :style="{ textAlign: 'center' }">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer>
  </a-layout>
</template>

<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>

```



`home.vue`：

```
<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          v-model:selectedKeys="selectedKeys2"
          v-model:openKeys="openKeys"
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
      >
        <a-sub-menu key="sub1">
          <template #title>
              <span>
                <user-outlined />
                subnav 1
              </span>
          </template>
          <a-menu-item key="1">option1</a-menu-item>
          <a-menu-item key="2">option2</a-menu-item>
          <a-menu-item key="3">option3</a-menu-item>
          <a-menu-item key="4">option4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
              <span>
                <laptop-outlined />
                subnav 2
              </span>
          </template>
          <a-menu-item key="5">option5</a-menu-item>
          <a-menu-item key="6">option6</a-menu-item>
          <a-menu-item key="7">option7</a-menu-item>
          <a-menu-item key="8">option8</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub3">
          <template #title>
              <span>
                <notification-outlined />
                subnav 3
              </span>
          </template>
          <a-menu-item key="9">option9</a-menu-item>
          <a-menu-item key="10">option10</a-menu-item>
          <a-menu-item key="11">option11</a-menu-item>
          <a-menu-item key="12">option12</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      Content
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'Home',
  components: {
    HelloWorld,
  },
});
</script>

```



改造完代码后我们可以发现一个错误，因为`HelloWorld`组件没有使用，所以会报一个错，这就是校验规则的双刃剑。我们有两种方法可以解决，第一个是直接删除HelloWorld，另外一种就是把校验规则删除掉。

删除校验需要打开`.eslintrc.js`在`rules`中添加一下代码：

```js
    'vue/no-unused-components' : 'off'

```

![image-20220605020831922](wiki知识库.assets/image-20220605020831922.png)

校验规则删除后即可顺利运行。填充后，网页依旧可以正常显示。

![image-20220605020635300](wiki知识库.assets/image-20220605020635300.png)

首页`logo`未显示，我们可以把`App.vue`中的`style`标签里`.ant-row-rtl #components-layout-demo-top-side-2` 删除，即可正常显示`logo`。

```vue
<style>
.logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>

```

改在完的`App.vue`:

```vue
<template>
  <a-layout>
    <a-layout-header class="header">
      <div class="logo" />
      <a-menu
          v-model:selectedKeys="selectedKeys1"
          theme="dark"
          mode="horizontal"
          :style="{ lineHeight: '64px' }"
      >
        <a-menu-item key="1">nav 1</a-menu-item>
        <a-menu-item key="2">nav 2</a-menu-item>
        <a-menu-item key="3">nav 3</a-menu-item>
      </a-menu>
    </a-layout-header>
    <router-view/>
    <a-layout-footer :style="{ textAlign: 'center' }">
      Ant Design ©2018 Created by Ant UED
    </a-layout-footer>
  </a-layout>
</template>

<style>
.logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>

```

网页画面：

![image-20220605234952629](wiki知识库.assets/image-20220605234952629.png)



## 6. 制作Vue自定义组件

### 6.1 制作the-header组件

复制`header`代码，在`components`目录下新建`the-header.vue`，用一个`template`标签包含复制的`header`代码：

```vue
<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">nav 1</a-menu-item>
      <a-menu-item key="2">nav 2</a-menu-item>
      <a-menu-item key="3">nav 3</a-menu-item>
    </a-menu>
  </a-layout-header>
</template>
```



接下来我们复制`HelloWorld`组件的`script`标签，然后仿照`HelloWorld`的写法写自己的。下面我们来分析一下`HelloWorld`中的`script`标签。

```vue
<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'HelloWorld',
  props: {
    msg: String,
  },
});
</script>
```

`name`属性支持驼峰命名。多个单词拼接，首字母大写，如`HelloWorld`，也叫大驼峰。小驼峰：首字母小写，如`helloWorld`，同时也支持中间-的写法。也就是说起一个`the-header`或者`theHeader`都是可以的。

所以`name`我们可以起一个`the-header`。

`props`是用于父子组件之间传递数据，我们这里不需要所以可以删除。

这样我们的组件就完成了。

```vue
<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'the-header',
  
});
</script>
```

要使用组件首先需要导入组件，我们把`script`标签的内容复制到`App.vue`中。

引入使用需要三个步骤，首先需要import组件页面，然后comonents加入该组件，最后在template中使用组件。

```vue
<script lang="ts">
import { defineComponent } from 'vue';
import TheHeader from "@/components/the-header.vue";

export default defineComponent({
  name: 'app',
  components:{
    TheHeader,
  }

});
</script>
```

注意在`import`里的`TheHeader`就不能使用-连接了，`the-header`会报错的。这里的`name`要改为`app`。

这样我们就可以把`App.vue`中的`header`部分代码删除，替换成`<the-header></the-header>`。这个标签的名字，就跟我们组件的`name`是一样的。

### 6.2 制作the-footer组件

制作`footer`组件的步骤和`header`一样，所以不在阐述，直接上代码。

`the-footer.vue`：

```vue
<template>
  <a-layout-footer :style="{ textAlign: 'center' }">
    WiKi
  </a-layout-footer>

</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'the-footer',

});
</script>
```

`App.vue`：

```vue
<template>
  <a-layout>
    <the-header></the-header>
    <router-view/>
    <the-footer></the-footer>
  </a-layout>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
import TheHeader from "@/components/the-header.vue";
import TheFooter from "@/components/the-footer.vue";

export default defineComponent({
  name: 'app',
  components:{
    TheHeader,TheFooter
  }

});
</script>


<style>
.logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>

```



# 前后端交互整合

## 1. 集成HTTP库axios

### 1.1 集成HTTP库axios

打开`IDEA`自带`Terminal`控制台，`cd`进入`web`目录，输入以下命令进行安装：

```
npm install axios --save
```



### 1.2 使用axios调用电子书列表接口

打开`Home.vue`，使用`axios`需要在`script`标签中`import`：

```
import axios from "axios";

```



然后新增`setup`方法：

```
setup(){
    console.log("setup")
    axios.get("GET http://localhost:8080/ebook/list?name=Spring").then((response) => {
      console.log(response);
    })
  }
```

需要注意的是

```
then((response) => {
      console.log(response);
    })
```

等价于

```
then(function (response) {
      console.log(response);
    })
```

两种写法都可以。

我们可以启动前端和后端，因为数据还没有显示到对面，我们需要按F12来查看。这时我们会发现

![image-20220606225341163](wiki知识库.assets/image-20220606225341163.png)

一旦看到`No 'Access-Control-Allow-Origin'`这种错误，这就是一个跨域的问题。

跨域可以这样来理解，来自一个IP端口的页面（vue项目），要访问另一个IP端口的资源（SpringBoot请求接口），会产生跨域访问。

要解决跨域问题，我们需要添加配置类，在config包下新建CorsConfig.java：

```
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 映射的请求地址，这样写是表示针对所有的请求地址
                .allowedOriginPatterns("*") // 允许来源
                .allowedHeaders(CorsConfiguration.ALL) //表示通过的请求头
                .allowedMethods(CorsConfiguration.ALL) // 表示可行的请求方法
                .allowCredentials(true) // 凭证
                .maxAge(3600); // 1小时内不需要再预检（发OPTIONS请求）
    }

}
```

OPTIONS请求的意思是，在调用电子书接口之前，会发一个OPTIONS请求，来检查接口是否存在

![image-20220606225925957](wiki知识库.assets/image-20220606225925957.png)



在跨域上面还有一些警告，这主要是一个数据绑定的问题，我们的界面需要绑定参数，而我们的js还没有定义，所以会有一个警告，如果不想看到这些警告可以在IDEA中搜索这些参数然后删除这行代码。

问题都解决后重新运行前后端项目

![image-20220607002120847](wiki知识库.assets/image-20220607002120847.png)



成功查询到数据。



## 2. Vue3数据绑定显示列表数据

### 2.1   vue2和vue3的区别

`vue2`会有`data`，`Mounted`（生命周期）还有`method`，但是在`vue3`中这些都没有了，全部被`setup`代替。

`mounted` 生命周期函数，所谓的生命周期，就是指的这个界面或者说这个组件它从加载进来到销毁。整个过程就叫一个生命周期函数。

### 2.2 vue3生命周期函数

之间说到`Mounted`生命周期函数在`vue3`中被`setup`代替。在`setup`里面，我们会变成一个`onMounted`。

要使用`onMounted`需要`import`进来:

```vue
import {onMounted} from "vue";

```

然后我们就可以在`setup`里面增加`onMounted`。

`onMounted`是一个函数，所以我们加上一个括号。括号里面有一个回调方法，我们就可以写一个`function`：

```
setup(){
	onMounted(function (){
      
    })；
}
```

在这个方法里我们就可以写初始化的方法。

我们可以把上一节`axios`的内容粘贴到生命周期函数中。

```
setup(){
    console.log("setup");
onMounted(function (){
      console.log("onMounted");
      axios.get("http://localhost:8080/ebook/list?name=Spring").then((response) => {
        console.log(response);
      });
    });
    }
```

一般初始化的逻辑建议都写到`onMounted`方法里，`setup`就放一些参数定义、方法定义。

`setup`就相当于一个入口。

我们可以运行前端项目然后刷新一下页面按F12查看：

![image-20220607104210059](wiki知识库.assets/image-20220607104210059.png)



首先会输出`setup`，然后会输出`onMoutend`，然后就会输出我们加载后端数据的结果。

### 2.3 使用vue3 ref实现数据绑定

从上面的日志输出可以看出有个`data`，在`response`里面有一个`data`，这个`data`对应的就是我们后端的`CommonResp`的数据结构。

所以我们在`axios`中声明一个`const`，然后把data中的`content`取出来，`content`对应的就是电子书列表：

```
const data = response.data;
        data.content
```

我们要把内容显示到页面上，就需要定义一个变量，所以在生命周期函数外面定义一个`ebooks`：

```
const ebooks = ref();

```

`ref`是一个响应式数据，所谓的响应式数据就是说在`js`里面，动态的修改其中的值，它需要实时的反馈到页面上。   

要使用`ref`需要`import`：

```
import {ref} from "vue";

```

然后我们就可以把`data.content`赋值给`ebooks`，`ref`赋值不能直接`=`，要`.value`才能赋值：

```
ebooks.value = data.content;
```

最后还需要在`setup`中把数据`return`出去，这样界面就能拿到:

```
return {
      ebooks
    }
```

在页面使用`{{xxxx}}`来获取变量，为了美观点可以使用`pre`标签包裹：

```vue
<pre>
        {{ebooks}}
     </pre>
```

然后我们运行前端项目：

![image-20220607205324927](wiki知识库.assets/image-20220607205324927.png)

### 2.4 使用vue3 reactive实现数据绑定

`reactive`是`vue3`新增的，要使用的话同样需要`import`:

```
import {reactive} from "vue";

```

`reactive`同样是个方法，括号里面一般是放一个对象。

先定义一个变量，里面放一个空对象，对象里面有一个`books`属性，属性值用来存放电子书列表：

```
const ebooks1 = reactive({books: [] });

```

然后就可以赋值了：

```
ebooks1.books = data.content;
```

赋值完成后还需要把值返回出去，返回的话使用`toRef`方法，`toRef`方法同样是`vue`内置的，新增的一个方法，我们需要`import`：

```
import {toRef} from "vue";

```

`import`后我们就可以在`return`中写`toRef`方法了，`toRef`方法有两个参数，第一个是`ebooks1`，第二个参数就是里面的属性，比如说把`books`，把这个属性变成一个响应式变量。直接写`toRef`方法不行，必须要个它定义一个变量：

```
return {
      ebooks,
      ebooks2:toRef(ebooks1, "books")
    }
```

需要注意的是，这几行的`books`是一一对应的，`ebooks2`是我们自己随便起的。

要	在页面中显示我们可以新增一行：

```
<pre>
        {{ebooks}}
        {{ebooks2}}
</pre>

```

![image-20220608020007167](wiki知识库.assets/image-20220608020007167.png)

上面的是`ebook`的数据，下面的是`ebooks2`的数据，可以看到两个都成功的绑定了数据。

以下是完整的`Home.vue`代码：

```vue
<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu

          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
      >
        <a-sub-menu key="sub1">
          <template #title>
              <span>
                <user-outlined />
                subnav 1
              </span>
          </template>
          <a-menu-item key="1">option1</a-menu-item>
          <a-menu-item key="2">option2</a-menu-item>
          <a-menu-item key="3">option3</a-menu-item>
          <a-menu-item key="4">option4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
              <span>
                <laptop-outlined />
                subnav 2
              </span>
          </template>
          <a-menu-item key="5">option5</a-menu-item>
          <a-menu-item key="6">option6</a-menu-item>
          <a-menu-item key="7">option7</a-menu-item>
          <a-menu-item key="8">option8</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub3">
          <template #title>
              <span>
                <notification-outlined />
                subnav 3
              </span>
          </template>
          <a-menu-item key="9">option9</a-menu-item>
          <a-menu-item key="10">option10</a-menu-item>
          <a-menu-item key="11">option11</a-menu-item>
          <a-menu-item key="12">option12</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <pre>
        {{ebooks}}
        {{ebooks2}}
      </pre>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import {onMounted} from "vue";
import {ref} from "vue";
import {reactive} from "vue";
import {toRef} from "vue";
import axios from "axios";

export default defineComponent({
  name: 'Home',
  setup(){
    console.log("setup");
    const ebooks = ref();
    const ebooks1 = reactive({books: []});
    onMounted(function (){
      console.log("onMounted");
      axios.get("http://localhost:8080/ebook/list?name=Spring").then((response) => {
        const data = response.data;
        ebooks.value = data.content;
        ebooks1.books = data.content;
        console.log(response);
      });
    });

    return {
      ebooks,
      ebooks2:toRef(ebooks1, "books")
    }
  }

});
</script>

```

从两个示例的演示可以看出，`reactive`用起来稍微麻烦一点。在项目开发中尽量做到统一，要么全用`ref`，要么全用`reactive`。使用ref比较麻烦的一点是要去使用变量的话都要加一个`.value`，不管是获取还是赋值，都需要加一个`.value`。

## 3. 电子书列表界面展示

### 3.1 在ant design vue官网寻找组件

通过寻找，下面的列表样式比较符合我的预期：

![image-20220609164151288](wiki知识库.assets/image-20220609164151288.png)

我们可以复制其中的代码到`home.vue`。

`Home.vue`：

```vue
<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu

          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
      >
        <a-sub-menu key="sub1">
          <template #title>
              <span>
                <user-outlined />
                subnav 1
              </span>
          </template>
          <a-menu-item key="1">option1</a-menu-item>
          <a-menu-item key="2">option2</a-menu-item>
          <a-menu-item key="3">option3</a-menu-item>
          <a-menu-item key="4">option4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
              <span>
                <laptop-outlined />
                subnav 2
              </span>
          </template>
          <a-menu-item key="5">option5</a-menu-item>
          <a-menu-item key="6">option6</a-menu-item>
          <a-menu-item key="7">option7</a-menu-item>
          <a-menu-item key="8">option8</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub3">
          <template #title>
              <span>
                <notification-outlined />
                subnav 3
              </span>
          </template>
          <a-menu-item key="9">option9</a-menu-item>
          <a-menu-item key="10">option10</a-menu-item>
          <a-menu-item key="11">option11</a-menu-item>
          <a-menu-item key="12">option12</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-list item-layout="vertical" size="large" :pagination="pagination" :data-source="listData">
        <template #footer>
          <div>
            <b>ant design vue</b>
            footer part
          </div>
        </template>
        <template #renderItem="{ item }">
          <a-list-item key="item.title">
            <template #actions>
          <span v-for="{ type, text } in actions" :key="type">
            <component :is="type" style="margin-right: 8px" />
            {{ text }}
          </span>
            </template>
            <template #extra>
              <img
                  width="272"
                  alt="logo"
                  src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
              />
            </template>
            <a-list-item-meta :description="item.description">
              <template #title>
                <a :href="item.href">{{ item.title }}</a>
              </template>
              <template #avatar><a-avatar :src="item.avatar" /></template>
            </a-list-item-meta>
            {{ item.content }}
          </a-list-item>
        </template>
      </a-list>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import {onMounted} from "vue";
import {ref} from "vue";
import {reactive} from "vue";
import {toRef} from "vue";
import axios from "axios";
import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
import { defineComponent } from 'vue';



const listData: Record<string, string>[] = [];

for (let i = 0; i < 23; i++) {
  listData.push({
    href: 'https://www.antdv.com/',
    title: `ant design vue part ${i}`,
    avatar: 'https://joeschmoe.io/api/v1/random',
    description:
        'Ant Design, a design language for background applications, is refined by Ant UED Team.',
    content:
        'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  });
}

export default defineComponent({
  components: {
    StarOutlined,
    LikeOutlined,
    MessageOutlined,
  },

  name: 'Home',
  setup(){
    console.log("setup");
    const ebooks = ref();
    const ebooks1 = reactive({books: []});
    onMounted(function (){
      console.log("onMounted");
      axios.get("http://localhost:8080/ebook/list?name=Spring").then((response) => {
        const data = response.data;
        ebooks.value = data.content;
        ebooks1.books = data.content;
        console.log(response);
      });

    });

    const pagination = {
      onChange: (page: number) => {
        console.log(page);
      },
      pageSize: 3,
    };
    const actions: Record<string, string>[] = [
      { type: 'StarOutlined', text: '156' },
      { type: 'LikeOutlined', text: '156' },
      { type: 'MessageOutlined', text: '2' },
    ];

    return {
      ebooks,
      ebooks2:toRef(ebooks1, "books"),
      listData,
      pagination,
      actions,
    }
  }

});
</script>

```

我们的页面：

![image-20220610133732255](wiki知识库.assets/image-20220610133732255.png)

### 3.2 电子书列表展示

![image-20220610134252680](wiki知识库.assets/image-20220610134252680.png)

列表内容众多，我们只保留左侧封面，和内容标题内容描述，下面的内容和右侧封面就不保留了。

![image-20220610174945717](wiki知识库.assets/image-20220610174945717.png)

分页也暂时不需要了，因为我们的电子书不会很多，所以我们一次显示出来。

列表的页脚可以显示也可以不显示，这里我们就暂时不需要了。要改动的就这些，下面我们来改动代码。

![image-20220610232258640](wiki知识库.assets/image-20220610232258640.png)

`data-source`里的内容改为`ebooks`。

`footer`可以直接删除了。

`item`指的是一个一个的电子书，它会自动循环`ebooks`，将每一个电子书设置为`item`这个变量，你可以叫`ebook`或者`a`，或者自己定义都可以。

使用`item.XX`可以访问电子书数据。

`item.title`改为`item.name`。

`#extra`指的是右边的大图，我们不需要可以直接删除掉。

`item.avatar`是图标，然后看一下`sql`，我们的封面是`cover`，所以改为`item.cover`。

`item.content`不需要可以直接删除。

刷新页面：

![image-20220613182500588](wiki知识库.assets/image-20220613182500588.png)

### 3.3 更改list栅格列表

一行显示一条电子书信息会显得肯空旷，我们可以通过修改`List`的`grid`属性来实现栅格列表，`column`可设置期望显示的列数。

下面我们回到`Home.vue`中在`a-list`标签中加入以下代码：

```
grid="{gutter: 20, column: 3}" 
```

这段代码可以把一行变成三列，每列的间距是`20px`。

分页不需要，可以删除：

```
:pagination="pagination"
```



### 3.4 修改电子书列表参数

现在我们查询到的电子书只有一个name=Spring，我们可以删除它将所有的列表全部一次性显示出来。删除后如果测试的话是显示不出来数据的。因此我们还要看一下后端，修改一下EbookService。

![image-20220614142747379](wiki知识库.assets/image-20220614142747379.png)在EbookService中我们现在这里是写死的一个条件，就是不管你传什么参数进来，都会根据这个name去like去查找。这里我们就要改成一个动态的sql。也就是说如果传进name这个参数进来就按照name去查找。如果没有就不加这个条件，这个就是一个动态sql。

![image-20220614143336035](wiki知识库.assets/image-20220614143336035.png)

这样我们的页面就能正常查询出所有数据：

![image-20220614144042692](wiki知识库.assets/image-20220614144042692.png)

### 3.4 修改小封面样式

在`a-avatar`标签中添加以下内容：

```
shape="square" :size="50"
```

![image-20220614155913500](wiki知识库.assets/image-20220614155913500.png)

我们的封面图标就会变成这样：

![image-20220614155944049](wiki知识库.assets/image-20220614155944049.png)





## 4. Vue CLI多环境配置

### 4.1 增加开发和生产配置文件

我们现在都是在本地环境进行调试的，但是最后我们还要发布到生产。有些公司它可能还有测试环境和集成环境。所以我们一个项目会发布到很多环境，会针对不同的环境进行一些配置。 

现在我们的代码都是写死的`localhost`或者`127.0.0.1`,但是我们项目发布的时候，前端和后端不一定在同一台机子。为了解决这个问题，我们一般会加一个不同环境的配置。我们需要在`web`目录下新建一个文件，文件的名字用`.env`开头，后面就是环境的名称，比如开发环境就起一个`dev`即可。这个配置文件里要配置一下内容：

```
NODE_ENV=development
VUE_APP_SERVER=http://127.0.0.1:8080
```

第一行是环境变量，它的环境变量是`development`，是开发环境。

第二行是我们的自己定义的参数。我们自己定义的参数的写法有一个规则，必须要以`VUE_APP_`开头，后面就可以任意了，比如`server`。后面就是我的服务端的地址。

这是开发环境的配置文件，我们还要新建一个生产环境的配置文件。

新建`.env.prod`:

```
NODE_ENV=production
VUE_APP_SERVER=http://server.hlsdzpdw.com
```

这里的服务端地址是随便写的，以后发布在来这里改。

### 4.2 修改编译和启动支持多环境

打开`package.json`，我们的编译命令是这个：

![image-20220616161853614](wiki知识库.assets/image-20220616161853614.png)

启动命令是这个：

![image-20220616161929120](wiki知识库.assets/image-20220616161929120.png)

这个是本机启动命令，生产启动不会用这个命令，生产启动我们会用`nginx`。

想要读多环境需要在启动命令后面加一个参数：

```
"serve": "vue-cli-service serve --mode dev"

```

这里面的`dev`就是我们刚才新建的`dev`，要匹配起来，我们执行`server`这个命令的时候它就会去读`.env.dev`文件里面的变量了。

既然有一个`dev`，我们还要加一个`prod`，就是我们的生产环境。我们可以`Ctrl + D`，然后把后面的`dev`改为`prod`。

前面的`serve`只是一个命令的名称，我们是可以改的，我们可以把两个环境的启动名称分别改为`serve-dev`和`serve-prod`。

改完名称后我们可以刷新`npm`就可以看到两个启动命令了。

![image-20220616200735879](wiki知识库.assets/image-20220616200735879.png)

最后找到我们的`main.ts`，这是启动文件，在最下面加上一下代码：

```
console.log('环境：', process.env.NODE_ENV);

```

这段代码的意思就是打印一个日志环境。`process.env`是内置的，是固定的 ，要读环境变量就用这段代码，后面的`NODE_ENV`就是我们配置文件里名称。我们也可以把服务端地址输出出来：

```
console.log('服务端：', process.env.VUE_APP_SERVER);

```

运行项目打开首页就可以看到我们启动之后可以打印日志：![image-20220616204839836](wiki知识库.assets/image-20220616204839836.png)



这两个日志就是我们对应`dev`配置文件里面的配置。

启动命令改完了，编译命令也是一样的：

```
"build-dev": "vue-cli-service build --mode dev",
"build-prod": "vue-cli-service build --mode prod",
```



### 4.3 修改axios请求地址支持多环境

打开`home.vue`，找到`axios`的请求地址， 把地址替换为：

```
axios.get(process.env.VUE_APP_SERVER + "/ebook/list")
```



### 4.4 修改axios的baseUrl读配置文件

以后写接口每次都要写`process.env.VUE_APP_SERVER`非常的麻烦，`axios`提供了全局的配置，一般我们想到有一些配置的代码的话，我们就要想到写在`main.ts`里面。

我们打开`main.ts`， 在上面引入`axios`：

```typescript
import axios from 'axios';

```

引入之后添加以下代码：

```typescript
axios.defaults.baseURL = process.env.VUE_APP_SERVER;

```

这段代码的意思就是去修改它的`baseURL`，这里的`baseURL`和我们`vue`中的`baseURL`不一样，一个是`vue`框架的，一个是`axios`自己的。

添加完成后请求地址里的`process.env.VUE_APP_SERVER`就不需要了。

![image-20220617152652097](wiki知识库.assets/image-20220617152652097.png)

启动项目可以发现：

![image-20220617152923640](wiki知识库.assets/image-20220617152923640.png)



这里的baseURL就是我们刚才配置的环境变量。



## 5. 使用axios拦截器打印前端日志

在很多时候我们需要对前端进行调试，前端最简单的调试方法就是用打日志的方式。以后我们会写很多的接口，前后端的接口之间的交互需要经常进行调试，有一种方法，就是每次发送（调用后端接口）之前打印日志，发送的结果的回调函数里面，我们在打印一个日志。但是用这种方式需要每次我们都自己去写代码。`axios`提供了拦截器的功能，我们可以在拦截器里面，把请求日志和返回参数一起打印出来。这种统一的代码配置，我们写在`main.ts`里面。

```
/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log('请求参数：', config);
  return config;
}, error => {
  return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
  console.log('返回结果：', response);
  return response;
}, error => {
  console.log('返回错误：', error);
  return Promise.reject(error);
});
```

  第一段是拦截请求，第二段是拦截返回。

## 6. SpringBoot过滤器使用

### 6.1 配置过滤器，打印接口耗时

接口耗时在我们应用监控里面，是一个非常重要的监控点。可以看出来你的应用处理能力。

在我们的后端代码里新建一个`filter`包。过滤器的代码不用手写，一般都是比较固定的。

`LogFilter.java`：

```java
package cn.ll.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LogFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 打印请求信息
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        LOG.info("------------- LogFilter 开始 -------------");
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("远程地址: {}", request.getRemoteAddr());

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        LOG.info("------------- LogFilter 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
    }
}
```

这就是一个线程的通用的一个过滤器。

测试的话只需要启动后端项目，打开`ebook.http`执行。

![image-20220621132207370](wiki知识库.assets/image-20220621132207370.png)



## 7. SpringBoot拦截器的使用

### 7.1 配置拦截器，打印接口耗时

在后端代码中新建一个`interceptor`包，创建`LogInterceptor.java`：

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器：Spring框架特有的，常用于登录校验，权限校验，请求日志打印 /login
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 打印请求信息
        LOG.info("------------- LogInterceptor 开始 -------------");
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("远程地址: {}", request.getRemoteAddr());

        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("requestStartTime");
        LOG.info("------------- LogInterceptor 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
    }
}


```

拦截器跟过滤器有一点区别，就是它前跟后是分成两个方法的。过滤器的话是整个一起，中间用链去调用业务方法。

我们还需要增加一个全局的配置`SpringMvcConfig.java`，这个配置写在`config`包下。

`SpringMvcConfig.java`：

```java
package cn.ll.config;

import cn.ll.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LogInterceptor logInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/login");
    }
}
```

我们在这个配置中增加一个拦截器，把拦截器注入进来，然后有一个`add`增加拦截器的方法，这个方法是`WebMvcConfigurer`这个接口里面的。注册`logInterceptor`这样一个拦截器，然后针对所有的请求。但是并不是所有的接口都需要这个拦截器，比如我们的登录，所以我们需要通过`.excludePathPatterns`排除请求排除掉login。

启动我们的后端项目：

![image-20220621144737229](wiki知识库.assets/image-20220621144737229.png)

我们可以看到它的执行顺序，先去执行过滤器，然后把过滤器里面的内容全部执行了，然后再去执行拦截器。拦截器里面先去执行第一个方法，然后再去执行业务方法，业务方法结束了，拦截器结束了打印耗时，然后过滤器结束了打印耗时。可以看到过滤器的范围更大，因为他是在容器里面，就是`tomcat`里面。所以接口一进来先会到容器，然后容器会发到应用。我们的`SpringBoot`它就是一个应用，一个`web`应用。在进入`web`应用我们的拦截器就拿到了，拿到后就去处理，然后再去执行我们`web`应用里的业务逻辑。

## 8. SpringBootAOP的使用

### 8.1 配置AOP，打印接口耗时、请求参数、返回参数

一般我们会把`AOP`放到一个单独的层，所以我们新建一个`aspect`包，在这个包下新建一个`LogAspect.java`。`AOP`、过滤器、拦截器三个代码格式都是差不多的，都是比较固定的。以后要用到直接拷贝代码即可。

`LogAspect.java`：

```java
package cn.ll.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /** 定义一个切点 */
    @Pointcut("execution(public * cn.ll.controller..*Controller.*(..))")
    public void controllerPointcut() {}

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        // 打印请求信息
        LOG.info("------------- 开始 -------------");
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("远程地址: {}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        // LOG.info("请求参数: {}", JSONObject.toJSONString(args));

        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password", "file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("请求参数: {}", JSONObject.toJSONString(arguments, excludefilter));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password", "file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("返回结果: {}", JSONObject.toJSONString(result, excludefilter));
        LOG.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}

```

使用`AOP`的话需要在`pom.xml`里添加新的依赖：

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.70</version>
        </dependency>
```

我们这里添加了两个依赖，一个是`aop`的依赖，这个`aop`是`Springboot`自带的内置的，所以我们不需要加版本号。另一个是`fastjson`依赖，这个不是必须的，就是使用`aop`和`json`没什么关系。因为在`aop`里做的一些处理，使用到了`JSONObject`，所以需要引入`fastjson`。

引入依赖后，我们启动后端项目，运行测试脚本：![image-20220621201536463](wiki知识库.assets/image-20220621201536463.png)

我们可以看到运行的顺序是过滤器，拦截器，最后才是进入`aop`。

过滤器、拦截器和`aop`我们三个选一个就行，这里我们选择`aop`，我们需要把过滤器和拦截器所有的代码注释掉。
