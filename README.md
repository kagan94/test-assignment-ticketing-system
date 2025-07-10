# test-assignment-ticketing-system

Home assignment for Sporty Group.

## Task requirements

Task requirements are described in the [task requirements.pdf](documentation/task requirements.pdf) file.

## Setup and run instructions

todo: add section information

## Description of your locking strategy

This project uses distributed locking via Redisson and Redis to prevent concurrent modifications to ticket resources.

When assigning a person or updating the status of a ticket, a lock is acquired on a key based on the ticket ID. If the
lock is already held (i.e., another process is modifying the same ticket), a custom LockingException is thrown and a 423
error code ("Locked") response is returned. This ensures that only one operation can modify a ticket at a time,
preventing race conditions in a distributed environment.

## Sample concurrent update test case

todo: add section information

## AI tool usage and validation steps if used (we encourage using AI)

todo: add section information
