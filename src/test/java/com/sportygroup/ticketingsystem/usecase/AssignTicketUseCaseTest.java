package com.sportygroup.ticketingsystem.usecase;

import com.sportygroup.ticketingsystem.dto.AssignTicketRequestDto;
import com.sportygroup.ticketingsystem.entity.Ticket;
import com.sportygroup.ticketingsystem.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.sportygroup.ticketingsystem.TestConstants.TEST_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignTicketUseCaseTest {

	private static final String ASSIGNEE_TEST_ID = "assignee-123";

	@Mock
	TicketRepository ticketRepository;

	@InjectMocks
	AssignTicketUseCase useCase;

	@Test
	void shouldUpdateAssigneeId() {
		// given
		final var ticket = Ticket.builder().id(TEST_ID).build();
		final var request = AssignTicketRequestDto.builder().assigneeId(ASSIGNEE_TEST_ID).build();
		when(this.ticketRepository.findById(TEST_ID)).thenReturn(Optional.of(ticket));
		// when
		this.useCase.execute(TEST_ID, request);
		// then
		verify(this.ticketRepository).save(ticket);
		assertThat(ticket.getAssigneeId()).isEqualTo(ASSIGNEE_TEST_ID);
	}

	@Test
	void shouldThrowExceptionIfTicketNotFound() {
		// given
		final var request = AssignTicketRequestDto.builder().assigneeId(ASSIGNEE_TEST_ID).build();
		when(this.ticketRepository.findById(TEST_ID)).thenReturn(Optional.empty());
		// when, then
		assertThatThrownBy(() -> this.useCase.execute(TEST_ID, request)).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Ticket not found");
	}
}
