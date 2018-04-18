package com.libedi.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
	public RedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
//		RedisStandaloneConfiguration config = factory.getStandaloneConfiguration();
//		config.setHostName("localhost");	// default setting value
//		config.setPort(6379);	// default setting value
		return factory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory());
		return template;
	}
}
