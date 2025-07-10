package com.sportygroup.ticketingsystem.testsetup.api;

import com.sportygroup.ticketingsystem.dto.AssignTicketRequestDto;
import com.sportygroup.ticketingsystem.dto.CreateTicketRequestDto;
import com.sportygroup.ticketingsystem.dto.UpdateTicketStatusRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.sportygroup.ticketingsystem.testsetup.utils.TestRequestDtoUtils.buildCreateTicketRequestDto;
import static java.util.Objects.requireNonNull;

@RequiredArgsConstructor
@Component
public class TicketApi {

	private final TestRestTemplate restTemplate;

	public UUID create() {
		final var request = buildCreateTicketRequestDto();
		return this.create(request);
	}

	public UUID create(final CreateTicketRequestDto request) {
		final var httpEntity = new HttpEntity<>(request);
		final var response = this.restTemplate.postForEntity("/tickets", httpEntity, UUID.class);
		return requireNonNull(response.getBody());
	}

	public void assign(final UUID id, final AssignTicketRequestDto request) {
		final var httpEntity = new HttpEntity<>(request);
		this.restTemplate.exchange("/tickets/" + id + "/assign", HttpMethod.PATCH, httpEntity, Void.class);
	}

	public void updateStatus(final UUID id, final UpdateTicketStatusRequestDto request) {
		final var httpEntity = new HttpEntity<>(request);
		this.restTemplate.exchange("/tickets/" + id + "/status", HttpMethod.PATCH, httpEntity, Void.class);
	}
}
