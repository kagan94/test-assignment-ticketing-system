package com.sportygroup.ticketingsystem.controller;

import com.sportygroup.ticketingsystem.testsetup.api.TicketApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.sportygroup.ticketingsystem.entity.Ticket.TicketStatus.OPEN;
import static com.sportygroup.ticketingsystem.testsetup.utils.TestRequestDtoUtils.buildCreateTicketRequestDto;
import static org.assertj.core.api.Assertions.assertThat;

class CreateTicketIT extends BaseTicketIT {

	@Autowired
	TicketApi ticketApi;

	@Test
	void shouldCreateTicket() {
		// given
		final var requestDto = buildCreateTicketRequestDto();
		// when
		final var id = this.ticketApi.create(requestDto);
		// then
		final var entity = this.repository.findById(id).orElseThrow();
		assertThat(entity.getSubject()).isNotNull().isEqualTo(requestDto.getSubject());
		assertThat(entity.getDescription()).isNotNull().isEqualTo(requestDto.getDescription());
		assertThat(entity.getStatus()).isNotNull().isEqualTo(OPEN);
		assertThat(entity.getUserId()).isNotNull().isEqualTo(requestDto.getUserId());
		assertThat(entity.getAssigneeId()).isNull();
		assertThat(entity.getCreatedAt()).isNotNull();
		assertThat(entity.getUpdatedAt()).isNotNull();
	}
}
