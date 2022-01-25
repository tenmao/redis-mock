package com.github.tenmao.redismock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.io.IOException;


/**
 * @author timxia
 * @since 2019/10/16
 */
public class RedisServerTest {

    @Test
    public void newRedisServer() throws IOException {
        RedisServer redisServer = new RedisServer();
        redisServer.start();
        redisServer.getBindPort();

        Jedis jedis1 = new Jedis(redisServer.getHost(), redisServer.getBindPort());
        SetParams setParams = new SetParams().nx().ex(100);
        Assertions.assertEquals(jedis1.set("ab", "cd", setParams), "OK");
        Assertions.assertEquals("cd", jedis1.get("ab"));
        redisServer.stop();
    }
}