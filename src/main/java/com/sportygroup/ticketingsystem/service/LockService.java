package com.sportygroup.ticketingsystem.service;

import com.sportygroup.ticketingsystem.exception.LockingException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LockService {

	private final RedissonClient redissonClient;

	public void lockAndExecute(String key, Runnable consumer) {
		final var lock = this.redissonClient.getLock(key);
		var locked = false;
		try {
			locked = lock.tryLock();
			if (!locked) {
				throw new LockingException("Resource is locked: " + key);
			}
			consumer.run();
		} finally {
			if (locked) {
				lock.unlock();
			}
		}
	}
}
