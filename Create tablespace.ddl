BEGIN;

    CREATE DATABASE tm
        WITH
        OWNER = postgres
        ENCODING = 'UTF8'
        LC_COLLATE = 'Russian_Russia.1251'
        LC_CTYPE = 'Russian_Russia.1251'
        TABLESPACE = pg_default
        CONNECTION LIMIT = -1;

    CREATE SCHEMA IF NOT EXISTS tm
        AUTHORIZATION postgres;

    CREATE TABLE IF NOT EXISTS tm.tm_project
    (
        id varchar(100) NOT NULL,
        created timestamp(0) with time zone,
        name varchar(150),
        description varchar(250),
        status varchar(20),
        user_id varchar(100),
        PRIMARY KEY (id)
    );

    CREATE TABLE IF NOT EXISTS tm.tm_task
    (
        id varchar(100) NOT NULL,
        created timestamp(0) with time zone,
        name varchar(150),
        description varchar(250),
        status varchar(20),
        user_id varchar(100),
        project_id varchar(100),
        PRIMARY KEY (id)
    );

    CREATE TABLE IF NOT EXISTS tm.tm_session
    (
        id varchar(100) NOT NULL,
        user_id varchar(100),
        date timestamp with time zone,
        role varchar(15),
        PRIMARY KEY (id)
    );

    CREATE TABLE IF NOT EXISTS tm.tm_user
    (
        id varchar(100) NOT NULL,
        login varchar(50) NOT NULL,
        password varchar(500) NOT NULL,
        first_name varchar(100),
        last_name varchar(100),
        middle_name varchar(100),
        email varchar(100),
        role varchar(15),
        locked boolean,
        PRIMARY KEY (id)
    );

END;
