package com.sportygroup.ticketingsystem.service;

import com.sportygroup.ticketingsystem.exception.LockingException;
import com.sportygroup.ticketingsystem.testsetup.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

class LockServiceIT extends BaseIT {

	private static final String TEST_LOCK_KEY = "test-lock-key";

	@Autowired
	LockService lockService;

	@Test
	void shouldFailProcessingIfLockAlreadyAcquired() throws InterruptedException {
		// given
		final var latch = new CountDownLatch(1);
		final var secondThreadFailed = new AtomicBoolean(false);

		final var thread1 = this.createSleepingThread(latch);
		final var thread2 = this.createSecondThread(latch, secondThreadFailed);

		// when
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();

		// then
		assertThat(secondThreadFailed.get()).isTrue();
	}

	private Thread createSleepingThread(CountDownLatch latch) {
		return new Thread(() -> {
			this.lockService.lockAndExecute(TEST_LOCK_KEY, () -> {
				latch.countDown(); // Signal second thread to start
				try {
					Thread.sleep(1_000);
				} catch (final InterruptedException e) {
					throw new RuntimeException(e);
				}
			});
		});
	}

	private Thread createSecondThread(CountDownLatch latch, AtomicBoolean secondThreadFailed) {
		return new Thread(() -> {
			try {
				latch.await(); // Wait for the first thread to acquire the lock
				this.lockService.lockAndExecute(TEST_LOCK_KEY, () -> {
				});
			} catch (final LockingException e) {
				secondThreadFailed.set(true);
			} catch (final InterruptedException ignored) {
			}
		});
	}
}
