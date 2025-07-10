package com.sportygroup.ticketingsystem.usecase;

import com.sportygroup.ticketingsystem.dto.AssignTicketRequestDto;
import com.sportygroup.ticketingsystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AssignTicketUseCase {

	private final TicketRepository ticketRepository;

	@Transactional
	public void execute(UUID ticketId, AssignTicketRequestDto request) {
		final var ticket = this.ticketRepository.findById(ticketId)
				.orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
		ticket.setAssigneeId(request.getAssigneeId());
		this.ticketRepository.save(ticket);
	}
}
