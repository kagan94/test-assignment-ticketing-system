package com.sportygroup.ticketingsystem.usecase;

import com.sportygroup.ticketingsystem.dto.CreateTicketRequestDto;
import com.sportygroup.ticketingsystem.entity.Ticket;
import com.sportygroup.ticketingsystem.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sportygroup.ticketingsystem.entity.Ticket.TicketStatus.OPEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateTicketUseCaseTest {

	@Captor
	ArgumentCaptor<Ticket> ticketCaptor;

	@Mock
	TicketRepository ticketRepository;

	@InjectMocks
	CreateTicketUseCase useCase;

	@Test
	void shouldCreateTicket() {
		// given
		final var request = CreateTicketRequestDto.builder().subject("subject").description("description")
				.userId("userId").build();
		// when
		final var actual = this.useCase.execute(request);
		// then
		verify(this.ticketRepository).save(this.ticketCaptor.capture());
		final var actualEntity = this.ticketCaptor.getValue();
		assertThat(actualEntity.getId()).isNotNull();
		assertThat(actualEntity.getSubject()).isEqualTo("subject");
		assertThat(actualEntity.getDescription()).isEqualTo("description");
		assertThat(actualEntity.getUserId()).isEqualTo("userId");
		assertThat(actualEntity.getStatus()).isEqualTo(OPEN);
		assertThat(actual).isEqualTo(actualEntity.getId());
	}
}
