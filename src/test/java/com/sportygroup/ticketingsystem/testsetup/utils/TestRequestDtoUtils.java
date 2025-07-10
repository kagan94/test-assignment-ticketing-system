package com.sportygroup.ticketingsystem.testsetup.utils;

import com.sportygroup.ticketingsystem.dto.CreateTicketRequestDto;

public class TestRequestDtoUtils {

	public static CreateTicketRequestDto buildCreateTicketRequestDto() {
		return CreateTicketRequestDto.builder().subject("Test Subject").description("Test Description")
				.userId("user-123").build();
	}
}