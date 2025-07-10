package com.sportygroup.ticketingsystem.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class LockUtils {

	public static String buildTicketLockKey(UUID ticketId) {
		return "ticket:" + ticketId;
	}
}
