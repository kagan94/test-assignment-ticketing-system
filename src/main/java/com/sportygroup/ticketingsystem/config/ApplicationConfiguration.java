package com.sportygroup.ticketingsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"com.sportygroup.ticketingsystem.repository"})
@Configuration
public class ApplicationConfiguration {
}
