package com.sportygroup.ticketingsystem.controller;

import com.sportygroup.ticketingsystem.dto.UpdateTicketStatusRequestDto;
import com.sportygroup.ticketingsystem.testsetup.BaseIT;
import com.sportygroup.ticketingsystem.testsetup.api.TicketApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.sportygroup.ticketingsystem.entity.Ticket.TicketStatus.IN_PROGRESS;
import static com.sportygroup.ticketingsystem.entity.Ticket.TicketStatus.OPEN;
import static org.assertj.core.api.Assertions.assertThat;

class UpdateTicketStatusIT extends BaseIT {

	@Autowired
	TicketApi ticketApi;

	@Test
	void shouldUpdateTicketStatus() {
		// given
		final var id = this.ticketApi.create();
		var entity = this.repository.findById(id).orElseThrow();
		assertThat(entity.getStatus()).isEqualTo(OPEN);
		final var updateStatusRequestDto = UpdateTicketStatusRequestDto.builder().status(IN_PROGRESS).build();
		// when
		this.ticketApi.updateStatus(id, updateStatusRequestDto);
		// then
		entity = this.repository.findById(id).orElseThrow();
		assertThat(entity.getStatus()).isEqualTo(IN_PROGRESS);
	}
}
