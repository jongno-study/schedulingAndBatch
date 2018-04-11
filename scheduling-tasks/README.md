# Scheduling Tasks

### Create a scheduled task

Now that you’ve set up your project, you can create a scheduled task.<br/>
이제 프로젝트를 설정했으니 스케쥴링 작업을 만들 수 있다.

```src/main/java/hello/ScheduledTasks.java```
```java
package hello;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
```
The ```Scheduled``` annotation defines when a particular method runs.<br/>
특정 메소드를 실행할 때 ```Scheduled``` 어노테이션을 정의 한다.

NOTE: This example uses ```fixedRate```, which specifies the interval between method invocations measured from the start time of each invocation.<br/>
NOTE: 이 예제는 각 호출의 시작 시간에서 측정된 메소드 실행간의 간격을 지정하기 위해서 ```fixedRate```를 사용했다.

There are other options, like ```fixedDelay```, which specifies the interval between invocations measured from the completion of the task.<br/>
```fixedDelay``` 같은 다른 옵션이 있다. 이 옵션은 작업 완료시 측정 된 호출 간격을 지정한다.

(```fixedRate```는 실행 간격 기준이 ```실행 시간```, ```fixedDelay```는 실행 ```완료 시간```이다.
```fixedRate```의 간격 시간이 메소드를 실행하는 시간 보다 짧을 경우 메소드 실행이 완료될 때까지 기다린 후 메소드를 실행한다.)

You can also use ```@Scheduled(cron=". . .")``` expressions for more sophisticated task scheduling.<br/>
더 정교한 작업 스케쥴링을 위해서 ```@Scheduled(cron=". . .")``` 표현식을 사용할 수도 있다.

### Enable Scheduling
Although scheduled tasks can be embedded in web apps and WAR files, the simpler approach demonstrated below creates a standalone application.<br/>
스케쥴 된 작업은 웹 어플리케이션들과 WAR 파일들에 포함 될 수 있지만, 아래에서 보여주는 간단한 방법은 독립형 어플리케이션을 만든다.

You package everything in a single, executable JAR file, driven by a good old Java ```main()``` method.<br/>
좋은 오래된 Java ```main()``` 메소드로 구동되는 실행 가능한 단일 JAR 파일의 모든 것을 패키징한다.

```src/main/java/hello/Application.java```

```java
package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);
    }
}
```

```@SpringBootApplication``` is a convenience annotation that adds all of the following:<br/>
```@SpringBootApplication```은 다음 모두를 추가하는 편리한 어노테이션이다:

- ```@Configuration``` tags the class as a source of bean definitions for the application context.<br/>
- ```@Configuration```은 클래스를 application context를 위한 빈 정의 소스로 태그한다.
- ```@EnableAutoConfiguration``` tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.<br/>
- ```@EnableAutoConfiguration```은 Spring Bootd에게 classpath 설정들, 다른 bean들, 다양한 properties 설정을 기반으로 bean 추가를 시작하라고 지시한다.  
- Normally you would add ```@EnableWebMvc``` for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath.<br/>
- 일반적으로 Spring MVC 어플리케이션을 위해서 ```@EnableWebMvc```를 추가 할 것이지만 Spring Boot는 classpath에서 spring-webmvc 볼 때 자동으로 추가한다.<br/>
This flags the application as a web application and activates key behaviors such as setting up a ```DispatcherServlet```.<br/>
이것은 어플리케이션을 웹 어플리케이션으로 표시하고, ```DispatcherServlet``` 설정과 같은 주요 동작을 활성화 한다.
- ```@ComponentScan``` tells Spring to look for other components, configurations, and services in the ```hello``` package, allowing it to find the controllers.<br/>
- ```@ComponentScan```은 Spring에게 컨트롤러를 찾을 수 있도록 허가된 ```hello``` 패키지에서 다른 컴포넌트, 설정, 서비스를 찾으라고 지시한다.

The ```main()``` method uses Spring Boot’s ```SpringApplication.run()``` method to launch an application.<br/>
```main()``` 메소드는 애플리케이션을 시작하기 위해 Spring Boot의 ```SpringApplication.run()``` 메소드를 사용한다.

Did you notice that there wasn’t a single line of XML? No web.xml file either.<br/>
XML이 한 줄도 없는 것을 알았나? web.xml 파일도 없다.

This web application is 100% pure Java and you didn’t have to deal with configuring any plumbing or infrastructure.<br/>
이 웹 어플리케이션은 100% 순수 자바이고, plumbing이나 인프라 설정을 다룰 필요가 없다.

```@EnableScheduling``` ensures that a background task executor is created. Without it, nothing gets scheduled.<br/>
```@EnableScheduling```은 백그라운드 테스크 실행자가 생성되는 것을 보장한다. 이것이 없으면 아무 것도 스케쥴링 되지 않는다.