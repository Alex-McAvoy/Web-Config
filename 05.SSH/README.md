# 1.加入 Spring

1. 加入 jar 包
2. 配置 web.xml 文件
3. 加入 Spring 的配置文件

# 2.加入 Hibernate

1. 同时建立持久化类, 和其对应的 .hbm.xml 文件，生成对应的数据表
2. Spring 整合 Hibernate
3. 步骤:
   1. 加入 jar 包
   2. 在类路径下加入 hibernate.cfg.xml 文件，在其中配置 hibernate 的基本属性
   3. 建立持久化类, 和其对应的 .hbm.xml 文件
   4. 和 Spring 进行整合
        1. 加入 c3p0 和 MySQL 的驱动
        2. 在 Spring 的配置文件中配置：数据源、SessionFactory、声明式事务
   5. 启动项目, 会看到生成对应的数据表
   
# 3.加入 Struts2

1. 加入 jar 包：若有重复的 jar 包，则需要删除版本较低的 javassist.jar
2. 在 web.xml 文件中配置 Struts2 的 Filter
3. 加入 Struts2 的配置文件
4. 整合 Spring
   1. 加入 Struts2 的 Spring 插件的 jar 包
   2. 在 Spring 的配置文件中正常配置 Action，注意 Action 的 scope 为 prototype
   3. 在 Struts2 的配置文件中配置 Action 时，class 属性指向该 Action 在 IOC 中的 id

# 4.完成功能

## 4.1获取所有的员工信息

若在 Dao 中只查询 Employee 的信息，而且 Employee 和 Department 还是使用的懒加载

页面上还需要显示员工信息，此时会出现懒加载异常，代理对象不能被初始化：org.hibernate.LazyInitializationException: could not initialize proxy - no Session

解决方法:

1. 打开懒加载：不推荐使用
2. 获取 Employee 时使用迫切左外连接同时初始化其关联的 Department 对象
3. 使用 OpenSessionInViewFilter

## 4.2 删除员工信息

1. 正常删除，返回值需要是 redirect 类型，而且重定向到 emp-list
2. `确定要删除吗？`的提示使用 jQuery 完成
3. Ajax 的使用参见 struts-2.3.15.3-all/struts-2.3.15.3/docs/WW/docs/ajax.html

## 4.3 添加员工

1. 显示表单页面：需要先查询所有的部门信息

   使用 Struts2 的 ModelDriven 和 Preparable 拦截器

   时间是一个字符串，需要转为一个 Date 类型的对象