package com.sportygroup.ticketingsystem.controller;

import com.sportygroup.ticketingsystem.dto.AssignTicketRequestDto;
import com.sportygroup.ticketingsystem.testsetup.api.TicketApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class AssignTicketIT extends BaseTicketIT {

	private static final String ASSIGNEE_ID = "assignee-123";

	@Autowired
	TicketApi ticketApi;

	@Test
	void shouldAssignTicket() {
		// given
		final var id = this.ticketApi.create();
		final var assignRequestDto = AssignTicketRequestDto.builder().assigneeId(ASSIGNEE_ID).build();
		// when
		this.ticketApi.assign(id, assignRequestDto);
		// then
		final var entity = this.repository.findById(id).orElseThrow();
		assertThat(entity.getAssigneeId()).isNotNull().isEqualTo(ASSIGNEE_ID);
	}
}
