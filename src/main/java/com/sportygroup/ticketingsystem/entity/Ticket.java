package com.sportygroup.ticketingsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Ticket {

	@Id
	@NotNull
	@Column(nullable = false, updatable = false)
	private UUID id;

	@NotNull
	private String subject;

	@Column(columnDefinition = "TEXT")
	private String description;

	@NotNull
	@Enumerated(STRING)
	@Column
	private TicketStatus status;

	@NotNull
	@Column
	private String userId;

	@Column
	private String assigneeId;

	@CreatedDate
	@Column(nullable = false)
	private Instant createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	private Instant updatedAt;

	public enum TicketStatus {
		OPEN, IN_PROGRESS, RESOLVED, CLOSED,
	}
}