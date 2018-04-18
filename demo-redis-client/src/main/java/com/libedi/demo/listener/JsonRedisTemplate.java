package com.libedi.demo.listener;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonRedisTemplate - <a href="http://wonwoo.ml/index.php/post/729">spring boot redis pub sub</a>
 * @author Sangjun, Park
 *
 * @param <V>
 */
public class JsonRedisTemplate<V> extends RedisTemplate<String, V> {

	public JsonRedisTemplate(RedisConnectionFactory connectionFactory, ObjectMapper objectMapper, Class<V> valueType) {
		super.setKeySerializer(super.getStringSerializer());
		super.setHashKeySerializer(super.getStringSerializer());
		super.setHashValueSerializer(super.getStringSerializer());
		Jackson2JsonRedisSerializer<V> jsonRedisSerializer = new Jackson2JsonRedisSerializer<V>(valueType);
		jsonRedisSerializer.setObjectMapper(objectMapper);
		super.setValueSerializer(jsonRedisSerializer);
		super.setConnectionFactory(connectionFactory);
		super.afterPropertiesSet();
	}
}
