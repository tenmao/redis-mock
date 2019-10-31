# redis-mock

A simple redis java mock for unit testing.

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.zxl0714/redis-mock/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.zxl0714/redis-mock)


```xml
<dependency>
  <groupId>com.github.tenmao</groupId>
  <artifactId>redis-mock</artifactId>
  <version>0.1</version>
</dependency>
```

## How to use it

The very simple way.

```java
private static RedisServer server = null;

@Before
public void before() throws IOException {
  server = RedisServer.newRedisServer();  // bind to a random port
}

@Test
public void test() {
  ...
  Jedis jedis = new Jedis(server.getHost(), server.getBindPort());
  ...
}

@After
public void after() {
  server.stop();
  server = null;
}
```

## Master and Slave

```java
RedisServer master = newRedisServer();
RedisServer slave = newRedisServer();
master.setSlave(slave);
```

## Fault tolerance testing

We can make a RedisServer close connection after every serveral commands. This will cause a connection exception for clients.

```java
RedisServer server = RedisServer.newRedisServer();
ServiceOptions options = new ServiceOptions();
options.setCloseSocketAfterSeveralCommands(3);
server.setOptions(options);
server.start();
```

## Support Commands

strlen

get

append

exists

set

ttl

decrby

pfadd

pfmerge

mget

mset

getset

del

expireat

pexpireat

setex

psetex

setnx

setbit

getbit

pttl

expire

pexpire

incr

incrby

decr

pfcount
