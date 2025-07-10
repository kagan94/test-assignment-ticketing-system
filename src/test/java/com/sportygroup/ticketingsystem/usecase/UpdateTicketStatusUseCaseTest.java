package com.sportygroup.ticketingsystem.usecase;

import com.sportygroup.ticketingsystem.dto.UpdateTicketStatusRequestDto;
import com.sportygroup.ticketingsystem.entity.Ticket;
import com.sportygroup.ticketingsystem.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.sportygroup.ticketingsystem.TestConstants.TEST_ID;
import static com.sportygroup.ticketingsystem.entity.Ticket.TicketStatus.RESOLVED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateTicketStatusUseCaseTest {

	@Mock
	TicketRepository ticketRepository;

	@InjectMocks
	UpdateTicketStatusUseCase useCase;

	@Test
	void shouldUpdateTicketStatus() {
		// given
		final var entity = Ticket.builder().id(TEST_ID).build();
		final var request = UpdateTicketStatusRequestDto.builder().status(RESOLVED).build();
		when(this.ticketRepository.findById(TEST_ID)).thenReturn(Optional.of(entity));
		// when
		this.useCase.execute(TEST_ID, request);
		// then
		assertThat(entity.getStatus()).isEqualTo(RESOLVED);
		verify(this.ticketRepository).save(entity);
	}

	@Test
	void shouldThrowExceptionIfTicketNotFound() {
		// given
		final var request = UpdateTicketStatusRequestDto.builder().status(RESOLVED).build();
		when(this.ticketRepository.findById(TEST_ID)).thenReturn(Optional.empty());
		// when, then
		assertThatThrownBy(() -> this.useCase.execute(TEST_ID, request)).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Ticket not found");
	}
}
