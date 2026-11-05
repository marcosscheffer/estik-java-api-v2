# Estik — Backend API

REST API for **Estik**, an IT and maintenance management system: support tickets with real-time chat, computer inventory, stock per facility and department, and live notifications.

This repository contains only the backend. The web interface lives in a separate project, **estik-frontend** (React), which consumes this API.

![Java](https://img.shields.io/badge/Java-25-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.1-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-4169E1?logo=postgresql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-migrations-CC0200?logo=flyway&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-000000?logo=jsonwebtokens&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?logo=docker&logoColor=white)

---

## Table of contents

- [Features](#features)
- [Tech stack](#tech-stack)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Running with Docker](#running-with-docker)
- [Running without Docker](#running-without-docker)
- [Environment variables](#environment-variables)
- [API documentation (Swagger)](#api-documentation-swagger)
- [Modules and endpoints](#modules-and-endpoints)
- [Authentication](#authentication)
- [Roles and permissions](#roles-and-permissions)
- [Real-time (SSE)](#real-time-sse)
- [Files and signed URLs](#files-and-signed-urls)
- [Database and migrations](#database-and-migrations)
- [Backups](#backups)
- [Project structure](#project-structure)

---

## Features

- **Tickets**: opening, triage and forwarding to a technician, taking, resolving, reopening, cancelling, priority and type (IT or maintenance), plus a status history.
- **Ticket chat**: each ticket has a chat between its participants. Messages can quote a previous message and carry attachments (images and PDF). New messages are pushed in real time over SSE.
- **Notifications**: created from domain events (ticket assigned, resolved, cancelled, reopened) and pushed live over SSE, with read/unread state.
- **Computers**: registration, configuration, moving between facilities and departments, with an automatic movement history.
- **Stock**: balances per facility and department. Balances are read-only and change only through movements: entry, exit, adjustment and transfer.
- **Catalog**: items and categories.
- **Administration**: facilities, departments and users (activation, role and facility).
- **Files**: uploads are stored on disk, their real type is detected with Apache Tika, and they are served through time-limited signed URLs.

## Tech stack

| Technology | Purpose |
|---|---|
| Java 25 + Spring Boot 4.1.1 | project foundation (Spring Web MVC) |
| Spring Security + JJWT 0.13 | stateless authentication with access and refresh tokens |
| Spring Data JPA + PostgreSQL | persistence |
| Flyway | versioned database migrations |
| Bean Validation | request validation |
| Lombok + MapStruct | boilerplate reduction and mapping between layers |
| springdoc-openapi 3 | Swagger UI and OpenAPI spec |
| Server-Sent Events (`SseEmitter`) | real-time chat and notifications |
| Apache Tika (via Spring AI document reader) | file type detection on upload |
| Spring Boot Actuator | health and metrics endpoints |
| Docker + Docker Compose | containers for the API, PostgreSQL, pgAdmin and backups |

## Architecture

The project follows a **hexagonal architecture (ports and adapters), organized by module**. Each module has the same three layers:

```
<module>/
├── domain/          entities, enums and business rules, with no framework code
├── application/
│   ├── port/in/     use case interfaces (what the module offers)
│   ├── port/out/    interfaces the module needs (persistence, other modules, push...)
│   ├── usecase/     implementations of the input ports
│   └── command/     input data for the use cases
└── adapter/
    ├── in/          web controllers, SSE, event listeners
    └── out/         JPA persistence, JWT, file storage, calls to other modules
```

Modules talk to each other only through ports. Cross-module reactions, such as creating a notification when a ticket changes, use Spring application events handled after the transaction commits.

## Prerequisites

- **Docker** and **Docker Compose** (recommended), or
- **JDK 25** and **PostgreSQL 17** to run it directly. Maven is not required, because the project ships with the Maven Wrapper (`mvnw`).

## Running with Docker

`docker-compose.yml` starts four services on the `estik-net` network:

| Service | Image | Port on the host | Purpose |
|---|---|---|---|
| `postgres` | `postgres:17-alpine` | `5433` | database, with a health check |
| `backend` | built from the `Dockerfile` | `8080` | this API |
| `pgadmin` | `dpage/pgadmin4` | `5050` | web UI for the database |
| `backup` | `prodrigestivill/postgres-backup-local:17` | none | scheduled database dumps into `./backups` |

The `Dockerfile` has two stages:

1. **build**: `eclipse-temurin:25-jdk` downloads the dependencies and runs `./mvnw package -DskipTests`.
2. **runtime**: `eclipse-temurin:25-jre` runs only the jar, as a non-root user.

```bash
git clone <this-repository-url>
cd estik-java-api

cp .env.example .env    # then fill in the passwords and secrets
docker compose up -d --build
```

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- pgAdmin: `http://localhost:5050` (host `postgres`, port `5432` inside the network)

The backend waits for PostgreSQL to be healthy, and Flyway creates the tables on the first start. Uploaded files live in the `estik_uploads` volume, so they survive container rebuilds.

Useful commands:

```bash
docker compose logs -f backend     # follow the API logs
docker compose down                # stop everything (data is kept in volumes)
docker compose up -d --build backend   # rebuild only the API after a code change
```

## Running without Docker

1. Create a PostgreSQL database. You can also start only the database from the compose file with `docker compose up -d postgres`, which listens on port `5433`.
2. Set the environment variables (see the table below). Without them, the application uses the development defaults from `application.properties`: `localhost:5432`, database `dev`, user and password `postgres`.
3. Start the application:

```bash
./mvnw spring-boot:run          # Linux / macOS
mvnw.cmd spring-boot:run        # Windows
```

Example on Windows PowerShell, pointing to the compose database:

```powershell
$env:DB_PORT="5433"; $env:DB_NAME="estik"; $env:DB_USERNAME="estik"; $env:DB_PASSWORD="<password>"
.\mvnw.cmd spring-boot:run
```

To build the jar only: `./mvnw clean package`. The output is `target/estik-0.0.1-SNAPSHOT.jar`.

## Environment variables

Copy `.env.example` to `.env`. The `.env` file is **not committed** to Git.

**Docker Compose (`.env`)**

| Variable | Used by | Example / default | Description |
|---|---|---|---|
| `POSTGRES_DB` | postgres, backend, backup | `estik` | database name |
| `POSTGRES_USER` | postgres, backend, backup | `estik` | database user |
| `POSTGRES_PASSWORD` | postgres, backend, backup | (required) | database password |
| `SECRET_KEY` | backend | (required) | key used to sign the JWTs. Use a long random value. |
| `STORAGE_SIGNING_SECRET` | backend | (required) | key used to sign the file URLs (HMAC). Use a long random value. |
| `PGADMIN_DEFAULT_EMAIL` | pgadmin | (required) | pgAdmin login e-mail |
| `PGADMIN_DEFAULT_PASSWORD` | pgadmin | (required) | pgAdmin login password |
| `BACKUP_SCHEDULE` | backup | `0 3 * * *` | cron expression for the dumps (every day at 03:00) |
| `BACKUP_KEEP_DAYS` | backup | `7` | daily backups to keep |
| `BACKUP_KEEP_WEEKS` | backup | `4` | weekly backups to keep |
| `BACKUP_KEEP_MONTHS` | backup | `6` | monthly backups to keep |
| `TZ` | all | `America/Sao_Paulo` | container time zone |

**Read by the application (`application.properties`)**

Compose fills these from the variables above. When running without Docker, set them yourself.

| Variable | Default | Description |
|---|---|---|
| `DB_HOST` | `localhost` | database host |
| `DB_PORT` | `5432` | database port |
| `DB_NAME` | `dev` | database name |
| `DB_USERNAME` | `postgres` | database user |
| `DB_PASSWORD` | `postgres` | database password |
| `SECRET_KEY` | development value | JWT signing key |
| `STORAGE_SIGNING_SECRET` | development value | file URL signing key |

> The default secrets exist only for local development. Always set your own values in any shared or production environment.

Other settings in `application.properties`: access token lifetime `15m`, refresh token `7d`, signed URL lifetime `1h`, upload limits `10MB` per file and `30MB` per request, `ddl-auto=validate` (the schema comes only from Flyway).

## API documentation (Swagger)

With the application running:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

To call protected endpoints in Swagger, log in with `POST /auth/login`, copy the `accessToken`, click **Authorize** and paste it.

## Modules and endpoints

| Module | Base path | Endpoints |
|---|---|---|
| auth | `/auth` | `POST /login`, `POST /register`, `POST /refresh` |
| user | `/users` | `GET /` (filters: `username`, `facilityId`, `role`), `GET /{id}`, `PUT /{id}`, `POST /{id}/activate`, `POST /{id}/deactivate`, `POST /{id}/assign` (role), `POST /{id}/facility` |
| facility | `/facilities` | `GET /`, `GET /{id}`, `POST /`, `PUT /{id}`, `DELETE /{id}` |
| departament | `/departaments` | `GET /`, `GET /{id}`, `POST /`, `PUT /{id}`, `DELETE /{id}` |
| ticket | `/tickets` | `GET /` (filters below), `GET /{id}`, `POST /`, `PUT /{id}`, `DELETE /{id}`, `POST /{id}/forward`, `POST /{id}/assume`, `POST /{id}/resolve`, `POST /{id}/reopen`, `POST /{id}/cancel`, `GET /{id}/histories` |
| chat | `/chats/{id}` | `GET /` (chat and participants), `GET /messages`, `POST /messages` (multipart: message + files), `GET /stream` (SSE) |
| notification | `/notifications` | `GET /`, `GET /unread`, `PATCH /{id}/read`, `PATCH /read` (all), `GET /stream` (SSE) |
| files | `/files` | `GET /{id}?expires=&signature=` (signed URL, no token needed) |
| computer | `/computers` | `GET /`, `GET /{id}`, `POST /`, `PUT /{id}`, `POST /{id}/move`, `DELETE /{id}` |
| computerhistory | `/histories` | `GET /`, `GET /{id}` |
| item | `/items` | `GET /`, `GET /{id}`, `POST /`, `PUT /{id}` |
| category | `/categories` | `GET /`, `GET /{id}`, `POST /`, `PUT /{id}` |
| stock | `/stock/balances` | `GET /`, `GET /{id}` (read-only) |
| stock | `/stock/movements` | `GET /`, `GET /{id}`, `POST /entry`, `POST /exit`, `POST /adjust`, `POST /transfer` |

List endpoints are paginated with `page` (default `0`) and `size` (default `20`).

`GET /tickets` filters: `title`, `status`, `priority`, `type`, `facilityId`, `requesterId`, `regulatorId`, `forwardedId`, `forwarded`, `assigneeId`.

**Main enums**

| Enum | Values |
|---|---|
| Role | `USER`, `TECHNICIAN`, `ADMINISTRATOR` |
| Ticket status | `OPEN`, `FORWARDED`, `PROGRESS`, `CANCELLED`, `RESOLVED` |
| Ticket priority | `MILD`, `MODERATE`, `URGENT` |
| Ticket type | `IT`, `MAINTENANCE` |
| Stock movement | `IN`, `OUT`, `TRANSFER`, `ADJUSTMENT` |
| Notification type | `ASSIGNED`, `RESOLVED`, `CANCELLED`, `REOPENED` |

## Authentication

1. `POST /auth/register` creates an account. New accounts start **inactive**, with role `USER`.
2. An administrator activates the account and sets its facility and role.
3. `POST /auth/login` returns an `accessToken` (valid for 15 minutes) and a `refreshToken` (valid for 7 days).
4. Every protected request sends `Authorization: Bearer <accessToken>`. The token carries the user id (`sub`), `username` and `role`.
5. `POST /auth/refresh` exchanges a valid refresh token for a new pair of tokens. Refresh tokens are stored in the database so they can be revoked.

The API is stateless: there is no server session, and CSRF is disabled.

**First administrator.** Since every new account is inactive, the first administrator must be promoted directly in the database, for example with pgAdmin:

```sql
UPDATE users SET role = 'ADMINISTRATOR', active = TRUE WHERE username = '<your-username>';
```

## Roles and permissions

Hierarchy: `USER < TECHNICIAN < ADMINISTRATOR`. Each role inherits everything from the roles below it (Spring Security `RoleHierarchy`). The rules live in `security/SecurityConfig.java`.

| Area | Minimum role |
|---|---|
| Login, register, refresh, Swagger, signed file URLs | public |
| Own tickets (open, view, edit, cancel, reopen, history), chat, notifications, own profile | USER |
| Read access to computers, history, stock, facilities and departments | USER |
| Taking and resolving tickets, computers, stock movements, items, categories | TECHNICIAN |
| Ticket forwarding (triage), users, writing facilities and departments, everything else | ADMINISTRATOR |

Business rules in the domain add finer checks on top of these, such as who can edit, cancel or resolve a given ticket.

## Real-time (SSE)

Real-time updates use **Server-Sent Events**, a one-way HTTP stream from the server to the browser.

| Stream | Event content |
|---|---|
| `GET /notifications/stream` | new notifications for the logged-in user |
| `GET /chats/{id}/stream` | new messages in that chat |

- Events are pushed only **after the database transaction commits**, so clients never receive data that was rolled back.
- The chat stream receives a scheduled heartbeat every 25 seconds, which keeps the connection open through proxies.
- The stream needs the JWT in the `Authorization` header. The browser's native `EventSource` cannot send headers, so the client must use `fetch` with a stream reader (as the frontend does) or a library such as `@microsoft/fetch-event-source`.
- Behind Nginx, response buffering must be turned off for these paths.

## Files and signed URLs

Chat attachments are saved under `storage.dir` (`uploads/`, or `/app/uploads` in Docker). Responses never expose the file path. Instead, they include a **signed URL**:

```
/files/{id}?expires=<epoch>&signature=<hmac>
```

The signature is an HMAC of the file id and the expiry time, made with `STORAGE_SIGNING_SECRET`. `GET /files/{id}` checks the signature and the expiry (1 hour) before returning the file, so the URL works in an `<img>` tag without a token but cannot be forged or reused forever. Files are returned with `Content-Disposition: inline` and `X-Content-Type-Options: nosniff`.

## Database and migrations

The schema is managed only by **Flyway** (`src/main/resources/db/migration`, `V1` to `V18`). Hibernate runs with `ddl-auto=validate`, which means it checks that the entities match the tables but never changes them.

To change the schema, add a new file `V<next-number>__DESCRIPTION.sql`. Never edit a migration that has already run.

## Backups

The `backup` service runs `pg_dump` on the `BACKUP_SCHEDULE` cron and writes compressed dumps into `./backups` (`daily/`, `weekly/`, `monthly/`), keeping as many as the `BACKUP_KEEP_*` variables allow. The `backups/` and `uploads/` folders are in `.gitignore`.

To restore a dump:

```bash
gunzip -c backups/daily/<file>.sql.gz | docker exec -i estik-postgres psql -U estik -d estik
```

## Project structure

```
src/main/java/com/marcos/estik/
├── auth/              login, register, refresh tokens, JWT and password adapters
├── user/              users, roles, activation, facility assignment
├── facility/          facilities (units)
├── departament/       departments inside a facility
├── ticket/            tickets, triage/forwarding, status history, domain events
├── chat/              ticket chat, messages, attachments, SSE stream and heartbeat
├── notification/      notifications from events, SSE stream
├── files/             file storage, type detection (Tika), signed URLs
├── computer/          computer inventory
├── computerhistory/   computer movement history
├── item/              catalog items
├── category/          catalog categories
├── stock/             stock balances and movements
├── security/          SecurityConfig, JWT filter, authenticated user
└── shared/            shared domain types, OpenAPI config, global exception handler
src/main/resources/
├── application.properties
└── db/migration/      Flyway SQL migrations
Dockerfile             multi-stage build (JDK → JRE)
docker-compose.yml     API + PostgreSQL + pgAdmin + backups
.env.example           template for the Docker environment variables
```
