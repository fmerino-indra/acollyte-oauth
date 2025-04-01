--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2 (Debian 15.2-1.pgdg110+1)
-- Dumped by pg_dump version 15.3 (Ubuntu 15.3-1.pgdg22.04+1)

-- SET statement_timeout = 0;
-- SET lock_timeout = 0;
-- SET idle_in_transaction_session_timeout = 0;
-- SET client_encoding = 'UTF8';
-- SET standard_conforming_strings = on;
-- SELECT pg_catalog.set_config('search_path', '', false);
-- SET check_function_bodies = false;
-- SET xmloption = content;
-- SET client_min_messages = warning;
-- SET row_security = off;

--
-- Data for Name: social_user; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

INSERT INTO social_user (id, name, email, image_url, email_verified, provider, user_id, provider_id) OVERRIDING SYSTEM VALUE VALUES (6, 'Felix Merino Martínez de Pinillos', 'felix.merino.fotografo@gmail.com', 'https://lh3.googleusercontent.com/a/AGNmyxYaEuwa1GJOxKbIVnf-ha9_JvEKyO_tvmPaRr5s=s96-c', true, 'google', NULL, '114749612519976518758');
INSERT INTO social_user (id, name, email, image_url, email_verified, provider, user_id, provider_id) OVERRIDING SYSTEM VALUE VALUES (3, 'Felix Merino', 'felix.merino@gmail.com', 'https://lh3.googleusercontent.com/a/AGNmyxZIpE99QKrAo6BHZhnM_Tw7fl4vbehpOuSBmHtNCA=s96-c', true, 'google', NULL, '110020632341192920624');
INSERT INTO social_user (id, name, email, image_url, email_verified, provider, user_id, provider_id) OVERRIDING SYSTEM VALUE VALUES (7, 'Felix Merino Cabanes', 'felix.cuarto.sensei@gmail.com', 'https://lh3.googleusercontent.com/a/AGNmyxaUuYU63D6Tg0909DgTqh92ATE4F7IdCkPVjh1a=s96-c', true, 'google', NULL, '111134883415880727197');


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

--SELECT pg_catalog.setval('user_id_seq', 7, true);


--
-- PostgreSQL database dump complete
--

