package com.sportygroup.ticketingsystem.controller;

import com.sportygroup.ticketingsystem.repository.TicketRepository;
import com.sportygroup.ticketingsystem.testsetup.BaseIT;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

class BaseTicketIT extends BaseIT {

	@Autowired
	protected TicketRepository repository;

	@BeforeEach
	void beforeEach() {
		this.repository.deleteAll();
	}
}
