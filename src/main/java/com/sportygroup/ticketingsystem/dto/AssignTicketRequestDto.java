package com.sportygroup.ticketingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignTicketRequestDto {

	@NotNull
	@NotBlank
	private String assigneeId;
}
