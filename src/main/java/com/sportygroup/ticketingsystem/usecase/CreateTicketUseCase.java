package com.sportygroup.ticketingsystem.usecase;

import com.sportygroup.ticketingsystem.dto.CreateTicketRequestDto;
import com.sportygroup.ticketingsystem.entity.Ticket;
import com.sportygroup.ticketingsystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.sportygroup.ticketingsystem.entity.Ticket.TicketStatus.OPEN;

@RequiredArgsConstructor
@Service
public class CreateTicketUseCase {

	private final TicketRepository ticketRepository;

	@Transactional
	public UUID execute(CreateTicketRequestDto request) {
		final var ticket = new Ticket();
		ticket.setId(UUID.randomUUID());
		ticket.setSubject(request.getSubject());
		ticket.setDescription(request.getDescription());
		ticket.setStatus(OPEN);
		ticket.setUserId(request.getUserId());
		this.ticketRepository.save(ticket);

		return ticket.getId();
	}
}
