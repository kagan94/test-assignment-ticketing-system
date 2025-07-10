package com.sportygroup.ticketingsystem.config;

import com.sportygroup.ticketingsystem.config.property.RedisProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.sportygroup.ticketingsystem.repository"})
@ConfigurationPropertiesScan(basePackages = "com.sportygroup.ticketingsystem.config.property")
@Configuration
public class ApplicationConfiguration {

	@Bean
	public RedissonClient redissonClient(RedisProperties redisProperties) {
		final var config = new Config();
		config.useSingleServer().setAddress(redisProperties.getUrl());
		return Redisson.create(config);
	}
}
