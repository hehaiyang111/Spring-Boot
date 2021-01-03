# SpringBoot第一节

## get started

> 万物的开始 Hello World！

目标：向浏览器发送/hello请求，响应Hello World。

那么如何创建一个SpringBoot的项目呢？

* 创建maven工程或者直接创建Spring initial,项目结构如下

![image-20201226224335992](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201226224335992.png)

* 导入相关依赖

![image-20201226224146739](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201226224146739.png)

**完整pom**：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.hhy</groupId>
    <artifactId>demo</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.1</version>
    </parent>


    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

</project>
```

* 创建主程序

```java
package com.hhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot主程序类
 * 使用@SpringBootApplication注解
 	@SpringBootApplication可用	
 	@EnableAutoConfiguration + @ComponentScan代替
 */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}

```

**注意** 主程序要放在package下不要直接放在java目录下了

![image-20201226230437576](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201226230437576.png)

* **实现**

![image-20201226230248336](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201226230248336.png)

**结果**

![image-20201226230513339](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201226230513339.png)

* 可执行jar

1. 导入

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

* 配置文件

```xml
server.port = 8989
```

SpringBoot可配置的属性：https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties

## using SpringBoot

### starters

spring-boot-starter-*     : This naming structure is intended to help when you need to find a starte

### 基础注解说明

#### @Configuration

> 给容器增加组件

Springmvc是通过xml的方式给容器增加组件。

```xml
	<bean id="person01" class="com.hhy.pojo.Person">
        <property name="name" value="hhy"></property>
        <property name="age" value="18"></property>
    </bean>

    <bean id="pet1" class="com.hhy.pojo.Pet">
        <property name="name" value=""></property>
    </bean>
```

使用@Configuration

```java
package com.hhy.config;

import com.hhy.pojo.Person;
import com.hhy.pojo.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration                       // ----->告诉容器这个一个配置类，相当于xml
public class MyConfig {
    @Bean
    public Person person() {
        return new Person("hhy",23);
    }

    @Bean
    public Person person1() {
        return new Person("lxy",23);
    }

    @Bean
    public Pet pet() {
        return new Pet("a miao");
    }
}

```

* 查看我们所注入的bean有没有成功

```java

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args); //获取上下文信息
        String[] beanDefinitionNames = run.getBeanDefinitionNames(); // 得到这些beans
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName); // 打印一下看一看有没有我们所注入的beans
        }
    }
}
```

* 容器内为单例对象

```java
// 单例
        Person person = run.getBean("person", Person.class);
        Person person1 = run.getBean("person", Person.class);
        System.out.println("isNot Instance " + (person == person1));//true
```

* 配置类本身就是一个bean,而且是**一个代理对象，每次调用都会去调用容器中的对象**

```java
		// bean:EnhancerBySpringCGLIB
        MyConfig bean = run.getBean(MyConfig.class);
        //  System.out.println(bean);
        Person person2 = bean.person();
        Person person3 = bean.person();
        System.out.println("isNotInstance " + (person2 == person3)); //true
```

* 单例模式是因为:

![image-20201228150315256](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201228150315256.png)

从名字可以看出，默认代理为true，**这样每次都要去容器里去找** 属于Full模型，而如果修改为false的话就是在外部构造新对象属于lite轻量模式。

> 解决组件依赖的问题

```java
@Configuration(proxyBeanMethods = true)                    // ----->告诉容器这个一个配置类，相当于xml
public class MyConfig {
    @Bean
    public Person person() {
        // person组件依赖了pet组件
        return new Person("hhy",23,pet());
    }


    @Bean
    public Pet pet() {
        return new Pet("a miao");
    }
}
```

```java
		//获取组件
        Person person2 = run.getBean("person", Person.class);
        Pet pet = run.getBean("pet", Pet.class);
        //看一看还是不是组件内的那个宠物（代理模式：true，lite模式：false）
        System.out.println(person2.getPet() == pet);
```

#### @import

> @Import

* 按照类名去注入bean，@Import({User.class})

#### @Conditional

满足xxx条件的时候就去执行（crtl + h:打开继承树)

![image-20201228153232828](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201228153232828.png)

Example:

首先将pet组件移除:

```java
@Configuration(proxyBeanMethods = true)                    // ----->告诉容器这个一个配置类，相当于xml
public class MyConfig {
    @Bean
    public Person person() {
        // person组件依赖了pet组件
        return new Person("hhy",23,pet());
    }


//    @Bean
    public Pet pet() {
        return new Pet("a miao");
    }
}
```

查看一下，容器中两个组件的存在情况：

```java
		boolean person = run.containsBean("person");
        System.out.println("person组件:"+ person);//true
        boolean pet = run.containsBean("pet");
        System.out.println("pet组件:"+ pet); //false
```

加上条件注解限制：

```java
@Configuration(proxyBeanMethods = true)                    // ----->告诉容器这个一个配置类，相当于xml
public class MyConfig {
    // 加上条件注解
    @ConditionalOnBean(name = "pet")
    @Bean
    public Person person() {
        // person组件依赖了pet组件
        return new Person("hhy",23,pet());
    }


//    @Bean
    public Pet pet() {
        return new Pet("a miao");
    }
}
```

这个时候：

```java
person组件:false
pet组件:false
```

#### @ConfigurationProperties

将配置文件的数据和容器中的beans绑定。 也只有容器中的beans可以与其绑定.

> 方法1：@Component与@ConfigurationProperties

```java
@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String name;
    private int price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
```

```properties
mycar.name = bmw
mycar.price = 250000
```

```java
@Autowired
    Car car;

@RequestMapping("/value")
    public Car getCar(){
        return car;
    }
```

```java
{"name":"bmw","price":250000}
```

> 方法2：@EnableConfiguationProperties + @ConfigurationProperties

* 这个可能用于导入第三方的那些本身不是一个component

在@Configuration下添加注解

```java
@Configuration(proxyBeanMethods = true)
//@ImportResource// ----->告诉容器这个一个配置类，相当于xml
@EnableConfigurationProperties(Car.class)
public class MyConfig {
//    @ConditionalOnBean(name = "pet")
    @Bean
    public Person person() {
        // person组件依赖了pet组件
        return new Person("hhy",23,pet());
    }


    @Bean
    public Pet pet() {
        return new Pet("a miao");
    }
}
```

```java
//@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String name;
    private int price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
```

```java
{"name":"bmw","price":250000}
```

#### springboot自动化配置说明

通过主程序@SpringBootApplication,可以看到除了元注解外，核心注解为

```java
@SpringBootConfiguration//就是个configuration
@EnableAutoConfiguration
@ComponentScan   //扫描包的
```

@EnableAutoConfiguration包含

```java
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
```

**首先看一下@AutoConfigurationPackage**:

```java
@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {
```

可以看到主要是导入了Registrar的组件，我们打开Register

![image-20201228195801715](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201228195801715.png)

从Register这里我们可以看到，这个Register类只要是注册当前包下的beans，这也就是为什么MainApplication默认要放在包下的原因之一嘞~。

**接下来看一下**@Import(AutoConfigurationImportSelector.class)

首先点开AutoConfigurationImportSelector，我们可以看到有一个selectImports方法，是通过，getAutoConfigurationEntry()去获得一些自动配置类最终返回一个String数组。

那么我们就点开getAutoConfigurationEntry()一探究竟~，从getAutoConfigurationEntry（）他的方法我们可以看出是为了获取到configurations，然后对configurations做一系列，例如去重的一些处理，最终返回。那么核心方法就是获取到configuation的getCandidateConfigurations()方法。

点开getCandidateConfigurations()方法。

此处打断点运行我们可以看到加载了130个configurations

![image-20201228201254672](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201228201254672.png)

主要方法是loadFactoryNames()，我们点进去看一下~，我们可以看到方法最终调用了loadSpringFactories方法，进入查看一下loadSpringFactories()方法。

进入以后我们可以看到所有的configuration都是从一个配置文件写死加载进来的。

![image-20201228201513309](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201228201513309.png)

我们可以打开响应文件看一下，在EnableAutoConfiguration注解下有130个可选择注入的bean。

![image-20201228202008969](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201228202008969.png)

#### 自定义注解配置说明

SpringBoot上文中配置的130 configuation并不是一次导入的，有很多都是根据条件去选择的，例如：

springboot-autoconfiguration------>spring-boot-autoconfigure-2.4.1.jar------->autoconfigure--------->web------------>servlet--------->DispatcherServletAutoConfiguration中的这段代码

```java
		@Bean
		@ConditionalOnBean(MultipartResolver.class)
		@ConditionalOnMissingBean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME) 	 			      	//MULTIPART_RESOLVER_BEAN_NAME=multipartResolver
		public MultipartResolver multipartResolver(MultipartResolver resolver) {
			// Detect if the user has created a MultipartResolver but named it incorrectly
			return resolver;
		}
```

* 在**springmvc**中配置上传文件的bean**必须叫做multipartResolver**要不然就会失败，在**springboot中**我们可以看到，**当默认名字不是multipartResolver的时候**(@ConditionalOnMissingBean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME) )我们把这个名字传入，但是返回的还是multipartResolver这个bean。

#### 小结

* SpringBoot首先加载所有的自动配置类。xxxxxAutoConfiguration
* 每个自动配置类按照条件进行生效，默认都会绑定配置文件指定的值。xxxxProperties里面拿。xxxProperties和配置文件进行了绑定。
  * 按照文档修改配置项。https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties
  * 或者查看xxxxproperties中有哪些配置项
* 生效的配置类就会给容器中装配很多组件。
  * debug=true可以看看哪些生效了 哪些没生效
* 只要容器中有这些组件，相当于功能也就都有了。
* 只要用户有自己配置的，就以用户优先。

xxxxAutoConfiguration----->组件------>xxxxProperties里面拿值--------->application.properties里修改

## 核心功能

### yml

> 小工具 Lombok

第一步：当然是在pom文件中加载lombok

第二步:  下载File | Settings | Plugins 搜索Lombok安装



> yml 用法

* 通过配置文件绑定各种数据类型。来学习yml的用法

```java
//使用这两个注解与配置文件绑定
@Component
@ConfigurationProperties(prefix = "person")
//lombok
@Data
@ToString
public class PersonTestYml {
    private String userName;
    private Boolean boss;
    private Date birth;
    private Integer age;
    private Pet pet;
    private String[] interesting;
    private List<String> animals;
    private Map<String,Object> score;
    private Set<Double> salarys;
    private Map<String, List<Pet>> allPets;
}
```

```java
@Data
@ToString
//@AllArgsConstructor  # 用yml的时候给对象传值的时候不要用这个
public class Pet {
    private String dogName;
    private Double weight;
}
```

```yaml
person:
  username: hhy
  boss: true
  birth: 1997/04/01
  age: 18
#  interesting: [篮球,足球]
  interesting:
    - 篮球
    - 足球
  animals: [dog,cat]
  score:
    english: 80
    math: 90
#  score: {enlish: 80,math: 90}
  salarys:
    - 999.99
    - 8989.88
  pet:
    dogName: 阿狗
    weight: 99.99
  allPets:
    sick:
      - dogName: 阿狗
        weight: 20
      - dogName: 啊狗二号
        weight: 25
    health:
      - dogName: 阿喵
        weight: 22
      - dogName: 阿喵二号
        weight: 23
```

**配置一下** (SpringBoot Configuration Annotation Processor)![image-20201230111047692](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201230111047692.png)

在官方文档附录中可以找到:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

官方文档中有一句话：If you have defined @ConfigurationProperties in your application, make sure to configure the
spring-boot-maven-plugin to prevent the repackage goal from adding the dependency into the fat jar:

总结起来就是节省资源，有些东西在打包的时候可以忽略掉

```xml
<project>
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-configuration-processor</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### web开发

#### 静态资源

`/static` (or `/public` or `/resources` or `/META-INF/resources`)放在这些目录下都可以

* 定制化拦截模式，默认是/**

```yml
spring:
  mvc:
    static-path-pattern: "/re/**"
```

![image-20201230181022199](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201230181022199.png)

* 定制化locations的位置就可以把图片放在自己配置的目录下覆盖默认地址。（没必要）

```yml
spring:
  web:
    resources:
      static-locations: classpath:/rr/
```



#### welcome page

1. 静待资源路径下的index.html
2. 处理index请求

#### 静态配置原理

首先进入WebMVCAutoConfiguration中

```java
// 第一步看一看这些存在条件
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
		ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {
```

```java
	@Configuration(proxyBeanMethods = false)
	@Import(EnableWebMvcConfiguration.class)
	@EnableConfigurationProperties({ WebMvcProperties.class,  //这里是这个confi绑定的一些preperties的类
			org.springframework.boot.autoconfigure.web.ResourceProperties.class, WebProperties.class })
	@Order(0)
	public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer {
```

然后这个配置类中只有**一个有参构造方法**，说明这些构造方法里的参数一旦启动都在容器中找。

```java
public WebMvcAutoConfigurationAdapter(
				org.springframework.boot.autoconfigure.web.ResourceProperties resourceProperties,
				WebProperties webProperties, WebMvcProperties mvcProperties, ListableBeanFactory beanFactory,
				ObjectProvider<HttpMessageConverters> messageConvertersProvider,
				ObjectProvider<ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider,
				ObjectProvider<DispatcherServletPath> dispatcherServletPath,
				ObjectProvider<ServletRegistrationBean<?>> servletRegistrations) {
			this.resourceProperties = resourceProperties.hasBeenCustomized() ? resourceProperties
					: webProperties.getResources();
			this.mvcProperties = mvcProperties;
			this.beanFactory = beanFactory;
			this.messageConvertersProvider = messageConvertersProvider;
			this.resourceHandlerRegistrationCustomizer = resourceHandlerRegistrationCustomizerProvider.getIfAvailable();
			this.dispatcherServletPath = dispatcherServletPath;
			this.servletRegistrations = servletRegistrations;
			this.mvcProperties.checkConfiguration();
```

视图解析器核心配置方法：

```java
@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			if (!this.resourceProperties.isAddMappings()) {
				logger.debug("Default resource handling disabled");
				return;
			}
			Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
			CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
			if (!registry.hasMappingForPattern("/webjars/**")) {
				customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
						.addResourceLocations("classpath:/META-INF/resources/webjars/")
						.setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl)
						.setUseLastModified(this.resourceProperties.getCache().isUseLastModified()));
			}
			String staticPathPattern = this.mvcProperties.getStaticPathPattern();
			if (!registry.hasMappingForPattern(staticPathPattern)) {
				customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
						.addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
						.setCachePeriod(getSeconds(cachePeriod)).setCacheControl(cacheControl)
						.setUseLastModified(this.resourceProperties.getCache().isUseLastModified()));
			}
		}
```

#### 请求参数处理

发送请求

```java
@RestController
public class HelloController {
//    @RequestMapping(value = "/user",method = RequestMethod.GET)
    @GetMapping(value = "/user")
    public String getUser(){
        return "GET-张三";
    }

//    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @PostMapping(value = "/user")
    public String saveUser(){
        return "POST-张三";
    }

//    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    @PutMapping(value = "/user")
    public String putUser(){
        return "PUT-张三";
    }

//    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    @DeleteMapping(value = "/user")
    public String deleteUser(){
        return "DELETE-张三";
    }
}
```

**如果是客户端直接发送请求** 如PostMan，还有前后端分离的环境，后端仅仅是提供一些接口，不需要我们写一些页面，他们可以直接发送相应请求。

**如果是表单发送请求**：因为表单的发送方式只有Get和Post并没有Put和Delete，那么该如何解决呢？我们可以看一看底层源码的实现。

```java
@Bean
	@ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
	@ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled", matchIfMissing = false)
	public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
```

首先要开启，spring.mvc.hiddenmethod.filter，如果不开启则不匹配，matchIfMissing = false。springboot默认不开，也只有我们自己写页面，有表单请求的时候会开启。

```yml
spring:
 mvc:
     hiddenmethod:
       filter:
         enabled: true
```

```java
public static final String DEFAULT_METHOD_PARAM = "_method";

/**
	这里可以看出表单类型填成post，加上一个_method,得到_method的值	
**/
if ("POST".equals(request.getMethod()) && request.getAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE) == null) {
    		//得到_method的值
			String paramValue = request.getParameter(this.methodParam);
			if (StringUtils.hasLength(paramValue)) {
				String method = paramValue.toUpperCase(Locale.ENGLISH);
				if (ALLOWED_METHODS.contains(method)) {
                    // 把method传进HttpMethodRequestWrapper重新包装，看一下HttpMethodRequestWrapper
					requestToUse = new HttpMethodRequestWrapper(request, method);
				}
			}
		}
```

![image-20201231154329359](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201231154329359.png)

可以看到包装器就是重写了方法，把_method传的方法重写出去。



#### 请求处理原理

![image-20201231161836037](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201231161836037.png)

**从doDIspatch那里打断点看源码**

```java
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpServletRequest processedRequest = request;
		HandlerExecutionChain mappedHandler = null;
		boolean multipartRequestParsed = false;

		WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

		try {
			ModelAndView mv = null;
			Exception dispatchException = null;

			try {
				processedRequest = checkMultipart(request);
				multipartRequestParsed = (processedRequest != request);
				
				// Determine handler for the current request.
                //找到当前映射给哪个HandleMapping处理
                //HandleMapping就是/xx请求交给xxx处理
				mappedHandler = getHandler(processedRequest);
```

![image-20201231162946866](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201231162946866.png)

**遍历handlerMapping**其中RequestMappingHanlerMapping保存了所有的请求映射。

![image-20201231163154595](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20201231163154595.png)

* SpringBoot自动配置欢迎页的 WelcomePageHandlerMapping 。访问 /能访问到index.html；
* SpringBoot自动配置了默认 的 RequestMappingHandlerMapping
* 请求进来，挨个尝试所有的HandlerMapping看是否有请求信息。
  * 如果有就找到这个请求对应的handler
  * 没有就接着遍历
  * 如果需要一些自定义的映射处理也可以自己给容器中放HandlerMapping。自定义**HandleMapping**

#### 普通参数与基本注解

```java
@PathVariable @RequestHeader @ModelAttribute @RequestParam @MatrixVariable @CookieValue @RequestBody
```

>@PathVariable

```java
@RestController
public class ParaMeterController {
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") int id,
                                      @PathVariable("username") String username,
                                      @PathVariable Map<String,String> map1) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("map1",map1);
        return map;
    }
}
```

![image-20210103203253178](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20210103203253178.png)

>@RequestHeader

```java
@RestController
public class ParaMeterController {
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") int id,
                                      @PathVariable("username") String username,
                                      @RequestHeader("Host") String host,
                                      @RequestHeader Map<String,String> map1) {
        Map<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("username",username);
        map.put("host", host);
        map.put("allCookies", map1);
        return map;
    }
}
```

![image-20210103204646884](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20210103204646884.png)

> @RequestParam

```java
//传参  car/1/owner/lisi?age=14&interests=basket&interests=swim

@RestController
public class ParaMeterController {
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") int id,
                                      @PathVariable("username") String username,
                                      @RequestHeader("Host") String host,
                                      @RequestHeader Map<String,String> map1,
                                      @RequestParam("age") int age,
                                      @RequestParam("interests") List<String> paramList) {
        Map<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("username",username);
//        map.put("host", host);
//        map.put("allCookies", map1);
        map.put("age", age);
        map.put("paramList", paramList);
        return map;
    }
}
```

![image-20210103205242468](C:\Users\86159\AppData\Roaming\Typora\typora-user-images\image-20210103205242468.png)

> @CookieValue

```java
@RestController
public class ParaMeterController {
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") int id,
                                      @PathVariable("username") String username,
                                      @RequestHeader("Host") String host,
                                      @RequestHeader Map<String,String> map1,
                                      @RequestParam("age") int age,
                                      @RequestParam("interests") List<String> paramList,
                                      @CookieValue("Idea-e63af95c") String ide,
                                      @CookieValue("Idea-e63af95c") Cookie cookie) {   //Cookie是http包的
        Map<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("username",username);
//        map.put("host", host);
//        map.put("allCookies", map1);
//        map.put("age", age);
//        map.put("paramList", paramList);
        map.put("Idea-e63af95c", ide);
//        System.out.println(cookie1.getClass().getName());
        System.out.println(cookie.getName() + "=====>" + cookie.getValue());
        return map;
    }
}
```

> @RequestBody

```java
@PostMapping("/save")
    public Map save(@RequestBody String content) {
        Map<String,Object> map = new HashMap<>();
        map.put("content", content);
        return map;
    }
```

