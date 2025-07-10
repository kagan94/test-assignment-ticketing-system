# test-assignment-ticketing-system

Home assignment for Sporty Group.

## Task requirements

Task requirements are described in the [task requirements.pdf](documentation/task requirements.pdf) file.

## Setup and run instructions

### Prerequisites

- Java 21
- Docker (for running PostgreSQL and Redis via Docker Compose)
- Maven (or use the provided `mvnw` wrapper)

### Start dependencies

Start PostgreSQL and Redis using Docker Compose:

```sh
docker compose up -d
```

This will start:

- PostgreSQL (db: `postgres`, user: `test`, password: `pass`)
- Redis

### Configure application (optional)

Default configuration is in `src/main/resources/application.yml`. Adjust as needed.

### Build and run the application

```sh
./mvnw clean package
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

### Run tests

```sh
./mvnw test
```

## Description of your locking strategy

This project uses distributed locking via Redisson and Redis to prevent concurrent modifications to ticket resources.

When assigning a person or updating the status of a ticket, a lock is acquired on a key based on the ticket ID. If the
lock is already held (i.e., another process is modifying the same ticket), a custom LockingException is thrown and a 423
error code ("Locked") response is returned. This ensures that only one operation can modify a ticket at a time,
preventing race conditions in a distributed environment.

## API usage examples

### Create a ticket

```http
POST /tickets
Content-Type: application/json

{
  "subject": "Test subject",
  "description": "Test description",
  "userId": "user-123"
}
```

### Assign a ticket

```http
PATCH /tickets/{ticketId}/assign
Content-Type: application/json

{
  "assigneeId": "assignee-123"
}
```

### Update ticket status

```http
PATCH /tickets/{ticketId}/status
Content-Type: application/json

{
  "status": "IN_PROGRESS"
}
```

## Sample concurrent update test case

The integration test `LockServiceIT` (see `src/test/java/com/sportygroup/ticketingsystem/service/LockServiceIT.java`)
demonstrates and validates the locking behavior under concurrent operations. Refer to this test for examples of how the
system handles concurrent ticket updates and locking scenarios.

## AI tool usage and validation steps if used (we encourage using AI)

- ChatGPT (for documentation, research)
- Cursor AI (for code generation)
- GitHub Copilot (in IDEA, for autocomplete)
