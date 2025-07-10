package com.sportygroup.ticketingsystem.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.sportygroup.ticketingsystem.repository"})
@Configuration
public class ApplicationConfiguration {

	@Bean
	public RedissonClient redissonClient() {
		final var config = new Config();
		config.useSingleServer().setAddress("redis://localhost:6379");
		return Redisson.create(config);
	}
}
