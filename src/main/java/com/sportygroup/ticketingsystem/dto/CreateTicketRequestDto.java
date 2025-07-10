package com.sportygroup.ticketingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTicketRequestDto {

	@NotBlank
	private String subject;

	private String description;

	@NotBlank
	private String userId;
}
