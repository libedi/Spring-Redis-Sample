package com.libedi.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Redis Configuration
 * @author Sangjun, Park
 *
 */
@Configuration
@EnableRedisRepositories
public class RedisConfiguration {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
//		/*
//		 * Change configuration Redis Server info
//		 */
//		RedisStandaloneConfiguration redisStandaloneConfiguration = factory.getStandaloneConfiguration();
//		redisStandaloneConfiguration.setHostName("localhost");	// default setting value
//		redisStandaloneConfiguration.setPort(6379);	// default setting value
//		
//		/**
//		 * Change configuration Jedis Connection Pool info
//		 */
//		JedisClientConfiguration jedisClientConfiguration = factory.getClientConfiguration();
//		jedisClientConfiguration.getPoolConfig().ifPresent(jedisPoolConfig -> {
//			jedisPoolConfig.setMaxTotal(10);
//			jedisPoolConfig.setMaxIdle(10);
//		});
		return factory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		// redis-cli 에서 byte로 표현되어 아래와 같이 설정해준다.
		template.setKeySerializer(template.getStringSerializer());	// StringRedisSerializer
		template.setValueSerializer(template.getStringSerializer());	// StringRedisSerializer
		return template;
	}
	
	@Bean
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
