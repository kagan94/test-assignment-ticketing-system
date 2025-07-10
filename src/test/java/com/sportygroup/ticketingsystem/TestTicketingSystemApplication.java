package com.sportygroup.ticketingsystem;

import org.springframework.boot.SpringApplication;

public class TestTicketingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(TicketingsystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}
}
