package com.yn.electricity.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName: RedisConfig
 * @Author: zzs
 * @Date: 2021/3/3 9:57
 * @Description: redis配置
 */
@Configuration
@RefreshScope
public class RedisConfiguration {
    @Value("${spring.redis.host}")
    private String hostName;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.lettuce.shutdown-timeout}")
    private int timeout;

    // 自定义RedisTemplate
    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate<String, Object> template = new RedisTemplate();

        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
        standaloneConfiguration.setHostName(hostName);
        standaloneConfiguration.setPassword(password);
        standaloneConfiguration.setPort(port);
        standaloneConfiguration.setDatabase(database);

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        LettucePoolingClientConfiguration poolingClientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(poolConfig).build();

        LettuceConnectionFactory factory = new LettuceConnectionFactory(standaloneConfiguration, poolingClientConfiguration);
        template.setConnectionFactory(factory);

        // Json 序列化配置
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        // ObjectMapper 转译
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(objectMapper);

        // 设置key\value序列化方式
        template.setKeySerializer(StringRedisSerializer.UTF_8);
        template.setValueSerializer(serializer);

        // 为Hash类型设置key\value序列化方式
        template.setHashKeySerializer(StringRedisSerializer.UTF_8);
        template.setHashValueSerializer(serializer);

        factory.afterPropertiesSet();

        return template;
    }

}
