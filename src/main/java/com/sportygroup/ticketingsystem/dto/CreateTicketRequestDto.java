package com.sportygroup.ticketingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequestDto {

	@NotBlank
	private String subject;

	private String description;

	@NotBlank
	private String userId;
}
