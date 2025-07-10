package com.sportygroup.ticketingsystem.dto;

import com.sportygroup.ticketingsystem.entity.Ticket.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateTicketStatusRequestDto {

	@NotNull
	private TicketStatus status;
}