package com.volunteer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

	@Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.max-active}")
    private int maxActive;

    @Value("${spring.redis.max-wait}")
    private long maxWait;

    @Value("${spring.redis.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.min-idle}")
    private int minIdle;

    public final JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxActive(maxActive);
        jedisPoolConfig.setMaxWait(maxWait);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool getJedisSentinelPool(){
    	JedisPool jedisPool = new JedisPool(getJedisPoolConfig(), hostName);
        return jedisPool;
    }
}
