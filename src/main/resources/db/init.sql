CREATE SCHEMA IF NOT EXISTS aston_task_CRUD;
USE aston_task_CRUD;

CREATE TABLE IF NOT EXISTS users
(
    id              bigserial   primary key,
    username        varchar(36) not null,
    password        varchar(80) not null,
    email           varchar(50) unique
);

CREATE TABLE IF NOT EXISTS roles
(
    id              bigserial   primary key,
    name            varchar(50) not null
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id         bigint      not null references users (id),
    role_id         bigint      not null references roles (id),
    primary key (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS tasks
(
    id              bigserial    primary key,
    title           VARCHAR(255) NOT NULL,
    status          VARCHAR(128) NOT NULL,
    description     TEXT,
    user_id         bigint       not null references users (id)
);

CREATE TABLE IF NOT EXISTS projects
(
    id              bigserial    primary key,
    title           VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_projects
(
    user_id         bigint       not null references users (id),
    projects_id     bigint       not null references projects (id),
    primary key     (user_id, projects_id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id              INT         NOT NULL PRIMARY KEY,
    content         TEXT        NOT NULL,
    time_create     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP AT TIMEZONE '+03' NOT NULL
    user_id         INT         NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS task_statuses
(
    id              INT         NOT NULL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS project_statuses
(
    id              INT         NOT NULL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks_task_statuses
(
    id              INT         PRIMARY KEY NOT NULL,
    tasks_id        INT         REFERENCES tasks(id) NOT NULL,
    task_statuses_id INT        REFERENCES task_statuses(id),
    time_update     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP AT TIMEZONE '+03' NOT NULL
);

CREATE TABLE IF NOT EXISTS projects_project_statuses
(
    id              INT         PRIMARY KEY NOT NULL,
    projects_id     INT         REFERENCES projects(id) NOT NULL,
    project_statuses_id INT     REFERENCES project_statuses(id),
    time_update     TIMESTAMP   DEFAULT CURRENT_TIMESTAMP AT TIMEZONE '+03' NOT NULL
);

INSERT INTO roles (name)
VALUES ('ROLE_WORKER'),
       ('ROLE_CHIEF'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, email)
VALUES ('Worker1', '100', 'Worker1@gmail.com'),
       ('Worker2', '100', 'Worker2@gmail.com'),
       ('Admin1', '100', 'Admin1@gmail.com'),
       ('Chief1', '100', 'Chief1@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (2, 1),
       (3, 3),
       (4, 2);