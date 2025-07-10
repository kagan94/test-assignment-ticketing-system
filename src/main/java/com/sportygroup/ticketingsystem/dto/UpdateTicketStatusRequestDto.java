package com.sportygroup.ticketingsystem.dto;

import com.sportygroup.ticketingsystem.entity.Ticket.TicketStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketStatusRequestDto {

	@NotNull
	private TicketStatus status;
}