##mybatis内容


###一、结构
 * 主配置文件: 由Resources.getResourceAsStream("SqlMapConfig.xml")解析、关联接口配置文件
 * 接口映射配置文件: 命名空间映射接口，id指定方法
 * 核心接口和类：SqlSessionFactoryBuilder、SqlSessionFactory、SqlSession
###二、缓存机制
* 一级缓存(同一个sqlSession),二级缓存（同一个sqlSessionFactory）
 
* 一级缓存：
         
      <setting name="localCacheScope" value="SESSION"/>
         
    不同sqlSession各有缓存，一个sqlSession修改数据了，另一个SqlSession因为存在查询缓存会出现脏读。
  * 总结:
  
    MyBatis一级缓存的生命周期和SqlSession一致。
    MyBatis一级缓存内部设计简单，只是一个没有容量限定的HashMap，在缓存的功能性上有所欠缺。
    MyBatis的一级缓存最大范围是SqlSession内部，有多个SqlSession或者分布式的环境下，数据库写操作会引起脏数据，建议设定缓存级别为Statement。

* 二级缓存：

  在上文中提到的一级缓存中，其最大的共享范围就是一个SqlSession内部，如果多个SqlSession之间需要共享缓存，则需要使用到二级缓存。开启二级缓存后，
  会使用CachingExecutor装饰Executor，进入一级缓存的查询流程前，先在CachingExecutor进行二级缓存的查询二级缓存开启后，同一个namespace下的所有操作语句，
  都影响着同一个Cache，即二级缓存被多个SqlSession共享，是一个全局的变量。
  当开启缓存后，数据的查询执行的流程就是 二级缓存 -> 一级缓存 -> 数据库。
  
 * 在MyBatis的配置文件中开启二级缓存。
  
        <setting name="cacheEnabled" value="true"/>
  
 * 在MyBatis的映射XML中配置cache或者 cache-ref 。
  cache标签用于声明这个namespace使用二级缓存，并且可以自定义配置。
  
        <cache/>   
          
 * cache-ref代表引用别的命名空间的Cache配置，两个命名空间的操作使用的是同一个Cache。
  
       <cache-ref namespace="mapper.StudentMapper"/>
    
    
 * 总结
 
 MyBatis的二级缓存相对于一级缓存来说，实现了SqlSession之间缓存数据的共享，同时粒度更加的细，能够到namespace级别，通过Cache接口实现类不同的组合，对Cache的可控性也更强。
 MyBatis在多表查询时，极大可能会出现脏数据，有设计上的缺陷，安全使用二级缓存的条件比较苛刻。
 在分布式环境下，由于默认的MyBatis Cache实现都是基于本地的，分布式环境下必然会出现读取到脏数据，需要使用集中式缓存将MyBatis的Cache接口实现，有一定的开发成本，直接使用Redis、Memcached等分布式缓存可能成本更低，安全性也更高。

###三、加载方式
* 懒加载
* 立即加载
