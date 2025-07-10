package com.sportygroup.ticketingsystem.service;

import com.sportygroup.ticketingsystem.exception.LockingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LockServiceTest {

	private static final String TEST_LOCK_KEY = "test-lock-key";

	@Mock
	RedissonClient redissonClient;

	@Mock
	RLock rLock;

	@InjectMocks
	LockService lockService;

	@BeforeEach
	void beforeEach() {
		when(this.redissonClient.getLock(TEST_LOCK_KEY)).thenReturn(this.rLock);
	}

	@Test
	void shouldRunConsumerWhenLockAcquired() {
		// given
		when(this.rLock.tryLock()).thenReturn(true);
		final var consumer = mock(Runnable.class);
		// when
		this.lockService.lockAndExecute(TEST_LOCK_KEY, consumer);
		// then
		verify(consumer).run();
		verify(this.rLock).unlock();
	}

	@Test
	void shouldThrowLockingExceptionWhenLockNotAcquired() {
		// given
		when(this.rLock.tryLock()).thenReturn(false);
		final var consumer = mock(Runnable.class);
		// when
		assertThatThrownBy(() -> this.lockService.lockAndExecute(TEST_LOCK_KEY, consumer))
				.isInstanceOf(LockingException.class).hasMessageContaining("Resource is locked");
		// then
		verify(consumer, never()).run();
		verify(this.rLock, never()).unlock();
	}
}
