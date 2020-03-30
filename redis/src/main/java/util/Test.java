package util;

import redis.clients.jedis.*;

import java.util.*;

/**
 * @author lyy
 */
public class Test {

    Jedis jedis = null;
    JedisPoolConfig jedisPoolConfig = null;
    ShardedJedis shardedJedis =null;
    ShardedJedisPool jedisPool=null;
    List<JedisShardInfo> infoList=null;

    public void init() {

        jedis = new Jedis("39.97.119.183");
        jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxActive(500);
        jedisPoolConfig.setMaxIdle(1);
        jedisPoolConfig.setMaxWait(1000 * 10);
        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setTestOnReturn(false);
        //设置Redis信息
        String host = "39.97.119.183";
        JedisShardInfo shardInfo1 = new JedisShardInfo(host, 6379, 500);
        JedisShardInfo shardInfo2 = new JedisShardInfo(host, 6380, 500);
//        shardInfo2.setPassword("test123");
        JedisShardInfo shardInfo3 = new JedisShardInfo(host, 6381, 500);
        //初始化ShardedJedisPool
        infoList = Arrays.asList(shardInfo1, shardInfo2, shardInfo3);
        jedisPool = new ShardedJedisPool(jedisPoolConfig, infoList);
        shardedJedis = jedisPool.getResource();
    }

    public void KeyOperate() {
        System.out.println("======================key==========================");
        // 清空数据
        System.out.println("清空库中所有数据：" + jedis.flushDB());
        // 判断key否存在
        System.out.println("判断key999键是否存在：" + shardedJedis.exists("key999"));
        System.out.println("新增key001,value001键值对：" + shardedJedis.set("key001", "value001"));
        System.out.println("判断key001是否存在：" + shardedJedis.exists("key001"));
        // 输出系统中所有的key
        System.out.println("新增key002,value002键值对：" + shardedJedis.set("key002", "value002"));
        System.out.println("系统中所有键如下：");
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
        // 删除某个key,若key不存在，则忽略该命令。
        System.out.println("系统中删除key002: " + jedis.del("key002"));
        System.out.println("判断key002是否存在：" + shardedJedis.exists("key002"));
        // 设置 key001的过期时间
        System.out.println("设置 key001的过期时间为5秒:" + jedis.expire("key001", 5));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
        System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
        // 移除某个key的生存时间
        System.out.println("移除key001的生存时间：" + jedis.persist("key001"));
        System.out.println("查看key001的剩余生存时间：" + jedis.ttl("key001"));
        // 查看key所储存的值的类型
        System.out.println("查看key所储存的值的类型：" + jedis.type("key001"));

        Client client1 = shardedJedis.getShard("key001").getClient();
        System.out.println("key001:" + client1.getHost() + " and port is:" + client1.getPort());
        Client client2 = shardedJedis.getShard("key002").getClient();
        System.out.println("key002:" + client1.getHost() + " and port is:" + client1.getPort());
////        Client client2 = jedis.getShard("redis").getClient();
////        Client client3 = jedis.getShard("test").getClient();
////        Client client4 = jedis.getShard("123456").getClient();
////
////        ////打印key在哪个server中
////        System.out.println("redis  in server:" + client2.getHost() + " and port is:" + client2.getPort());
////        System.out.println("test   in server:" + client3.getHost() + " and port is:" + client3.getPort());
////        System.out.println("123456 in server:" + client4.getHost() + " and port is:" + client4.getPort());

        /*
         * 一些其他方法：1、修改键名：jedis.rename("key6", "key0");
         *             2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)
         */
    }

    public void yy() {
        try {
//            jedis.flushDB();
            System.out.println("清空库中所有数据：" + jedis.flushDB());
            System.out.println("判断key999键是否存在：" + shardedJedis.exists("key999"));
            System.out.println("新增key001,value001键值对：" + shardedJedis.set("key777", "test"));
//            System.out.println("新增key test:"+shardedJedis.set("test111", "test"));
//            shardedJedis.set("test", "test");
//            shardedJedis.set("test1", "test1");
            System.out.println(shardedJedis.get("key999"));
//            String test = shardedJedis.get("test111");
//            System.out.println(test);

//            shardedJedis.set("cnblog", "cnblog");
//            shardedJedis.set("redis", "redis");
//            shardedJedis.set("test", "test");
//            shardedJedis.set("123456", "1234567");
//            System.out.println("插入完成");
//            Client client1 = shardedJedis.getShard("cnblog").getClient();
//            Client client2 = shardedJedis.getShard("redis").getClient();
//            Client client3 = shardedJedis.getShard("test").getClient();
//            Client client4 = shardedJedis.getShard("123456").getClient();
//
            //打印key在哪个server中
//            System.out.println("cnblog in server:" + client1.getHost() + " and port is:" + client1.getPort());
//            System.out.println("redis  in server:" + client2.getHost() + " and port is:" + client2.getPort());
//            System.out.println("test   in server:" + client3.getHost() + " and port is:" + client3.getPort());
//            System.out.println("123456 in server:" + client4.getHost() + " and port is:" + client4.getPort());
        } finally {
            //使用后一定关闭，还给连接池
//            if (shardedJedis != null) {
//                shardedJedis.disconnect();
//            }
        }
    }

    public static void main(String[] args) {
        Test test=new Test();
        test.init();
//        test.KeyOperate();
        test.yy();
    }

}
