package java8.wxf.redis.com.main;

import redis.clients.jedis.Jedis;

public class RedisJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost", 6379);
        // 如果 Redis 服务设置来密码，需要下面这行，没有就不需要
        // jedis.auth("123456");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        jedis.set("runKey","www.1234154.com");
        System.out.println(jedis.get("runKey"));
        jedis.sadd("java","hello1","hello2","hello3");
        System.out.println(jedis.scard("java"));
        System.out.println(jedis.smembers("java"));

    }
}
