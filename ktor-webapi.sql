--
-- PostgreSQL database dump
--

-- Dumped from database version 10.4 (Ubuntu 10.4-2.pgdg18.04+1)
-- Dumped by pg_dump version 10.4 (Ubuntu 10.4-2.pgdg18.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: fruits; Type: TABLE; Schema: public; Owner: sgaxpg
--

CREATE TABLE public.fruits (
    id integer NOT NULL,
    no character varying(20) NOT NULL,
    description character varying(50)
);


ALTER TABLE public.fruits OWNER TO sgaxpg;

--
-- Name: fruits_id_seq; Type: SEQUENCE; Schema: public; Owner: sgaxpg
--

CREATE SEQUENCE public.fruits_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fruits_id_seq OWNER TO sgaxpg;

--
-- Name: fruits_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: sgaxpg
--

ALTER SEQUENCE public.fruits_id_seq OWNED BY public.fruits.id;


--
-- Name: fruits id; Type: DEFAULT; Schema: public; Owner: sgaxpg
--

ALTER TABLE ONLY public.fruits ALTER COLUMN id SET DEFAULT nextval('public.fruits_id_seq'::regclass);


--
-- Data for Name: fruits; Type: TABLE DATA; Schema: public; Owner: sgaxpg
--

COPY public.fruits (id, no, description) FROM stdin;
\.


--
-- Name: fruits_id_seq; Type: SEQUENCE SET; Schema: public; Owner: sgaxpg
--

SELECT pg_catalog.setval('public.fruits_id_seq', 1, false);


--
-- Name: fruits fruits_no_unique; Type: CONSTRAINT; Schema: public; Owner: sgaxpg
--

ALTER TABLE ONLY public.fruits
    ADD CONSTRAINT fruits_no_unique UNIQUE (no);


--
-- Name: fruits pk_fruits; Type: CONSTRAINT; Schema: public; Owner: sgaxpg
--

ALTER TABLE ONLY public.fruits
    ADD CONSTRAINT pk_fruits PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

