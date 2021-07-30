###Spring内容：
####一、IOC（）
  * 把对象的创建交给bean来管理
     * spring的管理细节：
     
          1、创建bean的三种方式
          
          2、bean对象的作用范围
          
          3、bean对象的生命周期
     * 创建对象的三种方式：
     
       1、使用默认构造函数创建：
                    
          在spring中使用bean标签，配上id与class属性，且无其他属性即为使用默认构造函数创建，
          此时若无默认构造函数则对象无法创建。
                
                <bean id="accountService" class="com.itheima.service.iml.AccountServiceImpl" scope=""></bean>

       2、使用普通工厂的方法创建对象（使用某个类的方法创建另一个类的对象，并存入spring容器中）
            
            <bean id="instance" class="com.itheima.factory.Instance"></bean>
            <bean id="accountService" factory-bean="instance" factory-method="getAccount"></bean>
    
       3、使用工厂的静态方法创建对象（使用某个类的静态方法创建另一个类的对象，并存入spring容器中）
        静态方法是直接使用类名即可调用，故需要class即可。
           
            <bean id="accountService" class="com.itheima.factory.Staticfactory" factory-method="getAccount"></bean>
  
     * bean的作用范围调整：

       bean标签的scope属性：
       
       作用：用于指定bean的作用范围
       
       取值：常用单例和多例
       
            singleton；单例的（默认）
            prototype：原型（多例的）
            以下在web环境下才有用
            request：作用于web应用的请求范围
            session：作用于web应用的会话范围
            global-session：作用于集群环境的会话范围（全局session），当不是集群时，就是session范围
  
     * bean对象的生命周期：
     
       * 单例对象：
        
         出生：当容器创建时对象出生
         
         活着：只要容器存在，对象就活着
         
         死亡：容器销毁，对象消失
         
       * 多例对象：
       
           出生：当我们使用对象时，容器为我们创建
           
           活着：对象只要是在使用过程中就一直活着
           
           死亡：当对象长时间不用，由java垃圾回收器回收

              <bean id="accountService" class="com.itheima.service.iml.AccountServiceImpl" scope="singleton"
               init-method="init" destroy-method="destroy"></bean>
               
               
  * spring中的依赖注入
  
       依赖注入：
           Dependency Injection
           
       IOC的作用：
           降低程序耦合（依赖关系）
           
       依赖关系的管理：
          以后都交给spring来维护
       
       在当前类需要用到的对象由spring为我们提供，我们只需在配置文件中说明
        
       依赖关系的维护：
       称之为依赖注入
       
       依赖注入：
       
           能注入的类型：
              基本数据类型和string类型
              其他bean类型（在配置文件中或注解配置过的bean）
              复杂类型/集合类型
           注入的方式：
             1.使用构造函数提供
             2.使用set方法提供
             3.使用注解提供
####二、AOP（）
#####1、切面
###### （1）切入点
######  （2）通知类型
#####2、事务
  * 声明式事务
    * 事务通知
    
    
            <tx:advice id="txAdvice" transaction-manager="transactionManager">
                <!--配置事务的属性
                      isolation：用于指定事务的隔离级别。默认值是DEFAULT，表示使用数据库的默认隔离级别
                      propagation：用于指定事务的传播行为。默认值是REQUIRED,表示一定会有事务，增删改的选择。查询方法可以选择SUPPORTS
                      read-only：用于指定事务是否只读。只有查询方法才能设置为true，默认值是false，表示读写
                      timeout：用于指定事务的超时时间，默认值的-1，表示永不超时，单位为秒
                      rollback-for：用于指定一个异常，当产生该异常时，事务回滚，产生其他异常时事务不回滚。无默认值。表示任何异常都回滚
                      no-rollback-for：用于指定一个异常，当产生该异常时，事务不回滚，产生其他异常时事务回滚。无默认值。表示任何异常都回滚-->
                <tx:attributes>
                    <tx:method name="*" propagation="REQUIRED" read-only="false"></tx:method>
                    <tx:method name="find*" propagation="SUPPORTS" read-only="true"></tx:method>
                </tx:attributes>
            </tx:advice>
        
            <!--配置aop-->
            <aop:config>
                <!--配置切入点表达式-->
                <aop:pointcut id="pt1" expression="execution(* com.itheima.service.impl.*.*(..))"></aop:pointcut>
                <!--建立切入点表达式和事务通知的对应关系-->
                <aop:advisor advice-ref="txAdvice" pointcut-ref="pt1"></aop:advisor>
            </aop:config>
  * 编程式事务
####三、三级缓存
