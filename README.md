# Web Config

学习 Web 技术栈时的 demo 与配置文件，自用

## 1.Web 基础

1. JDBC：使用 db.properties 连接数据库并进行 CRUD 的工具类
2. Struts2：学习使用 Struts2 框架实现简单页面跳转
3. Hibernate：学习使用 Hibernate，包括：基本使用、表映射、事务管理

## 2.Spring 与 SpringMVC

1. Spring：学习使用 Spring 框架，包括：JDBC、控制反转 IOC、组件注入、切面编程、基本注解、事务管理
2. Spring MVC：学习使用 Spring MVC 框架，包括：模型与视图、RequestMapping 请求映射、POJO 绑定、异常抛出
3. Spring Annotation：学习使用 Spring 注解驱动开发，包括：组件注册与组件生命周期、自动注入、切面编程、事务管理

## 3.JPA 与 Spring Data

1. JPA：学习使用 JPA，包括：基本 CRUD（一对一关系、单向一对多关系、单向多对一关系、双向多对多关系、双向一对一关系、双向一对多关系）、事务管理、二级缓存、JPQL
2. Spring Data：学习使用 Spring Data，包括：基本 CRUD、级联查询、排序与分页

## 4.MyBatis

1. MyBatis
   1. Basic：MyBatis 的基本使用，包括：基本 CRUD、级联查询、动态 SQL、二级缓存、分页查询
   2. Generator：MyBatis Generator 逆向工程
2. MyBatis Common Mapper
   1. Basic：MyBatis 通用 Mapper 的 CRUD
   2. Generator：MyBatis Common Mapper Generator 逆向工程
3. MyBatis Plus
   1. Basic：MyBatis Plus 的基本使用，包括：通用 CRUD、条件构造器 EntityWrapper、活动记录 ActiveRecord、扩展插件（分页、执行分析、性能分析、乐观锁）
   2. Generator：MyBatis Plus Generator 逆向工程

## 5.Spring Boot

1. Environment：Spring Boot 环境切换与控制器的基本使用
2. Bean：Spring Boot 注入 Bean
3. MVC&Exception：Spring Boot 的 MVC 静态资源配置、映射处理与拦截器、自定义异常
4. Thymeleaf：Spring Boot 的 Thymeleaf 模板使用、POST 请求伪装 PUT、DELETE 请求
5. Mybatis Plus：Spring Boot 整合 Druid 数据源与 MyBatis Plus
6. Task：Spring Boot 的计划任务，包括异步任务、定时任务、邮件任务
7. Monitor：Spring Boot 日志管理、监控审计的使用

## 6.Spring Cloud

1. SpringCloud2020：SpringBoot 版本 2.4.6，SpringCloud 版本 2020.0.3
   - CommonAPI：通用 API
   - ConsumerOrder8000LoadBalanced：服务消费者模块，服务注册中心采用 Eureka，负载均衡使用 LoadBalanced
   - ConsumerOrder8000OpenFegin：服务消费者模块，负载均衡使用 OpenFegin
   - ConsumerStream8101RabbitMQ：SpringCloud Stream 消息消费者模块，消息队列采用 RabbitMQ
   - ConsumerStream8101RabbitMQ：SpringCloud Stream 消息消费者模块，消息队列采用 RabbitMQ
   - ConsumerStream8101RabbitMQ：SpringCloud Stream 消息消费者模块，消息队列采用 RabbitMQ
   - Eureka7001：Eureka 服务注册中心
   - Gateway9527：SpringCloud Gateway 网关模块
   - ProviderPayment8001Eureka：服务生产者模块
   - ProviderPayment8002Eureka：服务生产者模块
   - ProviderPayment8003Eureka：服务生产者模块
   - ProviderStream8100RabbitMQ：SpringCloud Stream 消息提供者模块，消息队列采用 RabbitMQ
2. SpringCloudAlibaba：学习使用 SpringCloud Alibaba，SpringBoot 版本 2.4.6，SpringCloud 版本 2020.0.3，SpringCloud Alibaba 版本 2021.1
   - CommonAPI：通用 API
   - ConfigCenterNacos3377：Nacos 配置中心
   - ConsumerOrder8000Nacos：服务消费者模块
   - ProviderPayment8001Nacos：服务生产者模块
   - ProviderPayment8002Nacos：服务生产者模块
   - ProviderPayment8003Nacos：服务生产者模块
   - Sentinel8401：Sentinel 流量治理模块

## 7.中间件

1. Redis
   1. Basic：Redis 的基本使用，包括：Redis 连接、常用 API、事务控制、主从复制、连接池
   2. Spring Boot：Spring Boot 整合 Redis，包括：运行结果缓存、缓存清除、缓存更新
2. RabbitMQ
   1. Basic：学习 RabbitMQ 的基本使用，包括：生产消费模型、轮询工作队列、消息应答、持久化、不公平分发与预取值、发布确认、交换机、死信
   2. Spring Boot：学习使用 SpringBoot 整合 Rabbit，包括：基于 TTL 的延迟队列、基于 rabbitmq_delayed_message_exchange 插件的延迟队列、高级发布确认
3. Elasticsearch
      1. Basic：学习 Elasticseach 的基本使用，包括：索引基本操作、文档基本操作、文档高级搜索
      2. Spring Boot：学习使用 SpringBoot 整合 Elasticsearch，包括：索引基本操作、文档基本操作、文档高级搜索
4. Shiro
      1. Basic：学习 Shiro 的基本使用，包括：登录认证、Session 的使用、用户角色、权限与行为、登出、MD5 加密
      2. Spring Boot：学习使用 SpringBoot 整合 Shiro，包括：权限认证、角色认证、登出、Realm 认证策略设置、Remember Me、会话管理、结合 Ehcache 实现缓存
5. Spring Security：学习 Spring Security 的基本使用，包括：权限认证、角色认证、基于注解的认证、登出、Remember Me、CSRF 防护

## 8.整合

1. SSH：使用 Struts2+Hibernate+Spring 整合编写的员工信息管理的 demo
2. SSSP：使用 SpringMvc+Spring+Spring Data+JPA 整合编写的员工信息管理的 demo
3. SSM：使用 Spring+Spring MVC+MyBatis 整合编写的员工信息管理的 demo