package com.sportygroup.ticketingsystem.controller;

import com.sportygroup.ticketingsystem.dto.AssignTicketRequestDto;
import com.sportygroup.ticketingsystem.dto.CreateTicketRequestDto;
import com.sportygroup.ticketingsystem.dto.UpdateTicketStatusRequestDto;
import com.sportygroup.ticketingsystem.service.LockService;
import com.sportygroup.ticketingsystem.usecase.AssignTicketUseCase;
import com.sportygroup.ticketingsystem.usecase.CreateTicketUseCase;
import com.sportygroup.ticketingsystem.usecase.UpdateTicketStatusUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.sportygroup.ticketingsystem.utils.LockUtils.buildTicketLockKey;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/tickets")
public class TicketController {

	private final CreateTicketUseCase createTicketUseCase;
	private final UpdateTicketStatusUseCase updateTicketStatusUseCase;
	private final AssignTicketUseCase assignTicketUseCase;
	private final LockService lockService;

	@PostMapping
	public UUID createTicket(@Valid @RequestBody CreateTicketRequestDto request) {
		return this.createTicketUseCase.execute(request);
	}

	@PatchMapping("/{ticketId}/status")
	public void updateTicketStatus(@PathVariable UUID ticketId,
			@Valid @RequestBody UpdateTicketStatusRequestDto request) {
		final var lockKey = buildTicketLockKey(ticketId);
		this.lockService.lockAndExecute(lockKey, () -> this.updateTicketStatusUseCase.execute(ticketId, request));
	}

	@PatchMapping("/{ticketId}/assign")
	public void assignTicket(@PathVariable UUID ticketId, @Valid @RequestBody AssignTicketRequestDto request) {
		final var lockKey = buildTicketLockKey(ticketId);
		this.lockService.lockAndExecute(lockKey, () -> this.assignTicketUseCase.execute(ticketId, request));
	}
}
