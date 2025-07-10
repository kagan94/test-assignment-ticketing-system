package com.sportygroup.ticketingsystem.testsetup;

import com.sportygroup.ticketingsystem.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BaseIT {

	@Autowired
	protected TicketRepository repository;

	@BeforeEach
	void beforeEach() {
		this.repository.deleteAll();
	}
}
