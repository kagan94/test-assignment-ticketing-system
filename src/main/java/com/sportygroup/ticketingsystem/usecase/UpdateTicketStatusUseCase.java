package com.sportygroup.ticketingsystem.usecase;

import com.sportygroup.ticketingsystem.dto.UpdateTicketStatusRequestDto;
import com.sportygroup.ticketingsystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateTicketStatusUseCase {

	private final TicketRepository ticketRepository;

	public void execute(UUID ticketId, UpdateTicketStatusRequestDto request) {
		final var ticket = this.ticketRepository.findById(ticketId)
				.orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
		ticket.setStatus(request.getStatus());
		this.ticketRepository.save(ticket);
	}
}
