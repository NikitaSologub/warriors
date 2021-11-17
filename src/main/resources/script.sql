-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- создаем сиквенсы

-- DROP SEQUENCE public.car_id_seq;
CREATE SEQUENCE public.car_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;

-- DROP SEQUENCE public.cat_id_seq;
CREATE SEQUENCE public.cat_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;

-- DROP SEQUENCE public.engine_id_seq;
CREATE SEQUENCE public.engine_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;

-- DROP SEQUENCE public.military_formation_id_seq;
CREATE SEQUENCE public.military_formation_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;

-- DROP SEQUENCE public.warrior_id_seq;
CREATE SEQUENCE public.warrior_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;

-- DROP SEQUENCE public.warrior_info_id_seq;
CREATE SEQUENCE public.warrior_info_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;

-- DROP SEQUENCE public.weapon_id_seq;
CREATE SEQUENCE public.weapon_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;


-- создаем таблицы

-- DROP TABLE IF EXISTS public.car;
CREATE TABLE public.car
(
    id    serial4 NOT NULL,
    firm  varchar NOT NULL,
    speed int4    NOT NULL,
    CONSTRAINT car_pk PRIMARY KEY (id)
);

-- DROP TABLE IF EXISTS public.cat;
CREATE TABLE public.cat
(
    id    serial4 NOT NULL,
    name  varchar NULL,
    color varchar NULL,
    age   int4    NULL,
    CONSTRAINT cat_pk PRIMARY KEY (id),
    CONSTRAINT cat_un UNIQUE (name)
);

-- DROP TABLE IF EXISTS public.military_formation;
CREATE TABLE public.military_formation
(
    id    serial4 NOT NULL,
    title varchar NOT NULL,
    CONSTRAINT military_formation_pk PRIMARY KEY (id),
    CONSTRAINT military_formation_un UNIQUE (title)
);

-- DROP TABLE IF EXISTS public.military_formation_warrior;
CREATE TABLE public.military_formation_warrior
(
    militaryformation_id int4 NOT NULL,
    warriors_id          int4 NOT NULL
);

-- DROP TABLE IF EXISTS public.warrior;
CREATE TABLE public.warrior
(
    id        serial4 NOT NULL,
    "rank"    varchar NOT NULL,
    weapon_id int4    NULL,
    info_id   int4    NOT NULL,
    CONSTRAINT warrior_pk PRIMARY KEY (id)
);

-- DROP TABLE IF EXISTS public.warrior_info;
CREATE TABLE public.warrior_info
(
    id        serial4 NOT NULL,
    firstname varchar NOT NULL,
    lastname  varchar NOT NULL,
    age       int4    NOT NULL,
    CONSTRAINT warrior_info_pk PRIMARY KEY (id)
);

-- DROP TABLE IF EXISTS public.weapon;
CREATE TABLE public.weapon
(
    id            serial4 NOT NULL,
    "type"        varchar NOT NULL,
    manufacturer  varchar NOT NULL,
    serial_number varchar NOT NULL,
    CONSTRAINT weapon_pk PRIMARY KEY (id),
    CONSTRAINT weapon_un UNIQUE (serial_number)
);

-- DROP TABLE IF EXISTS public.engine;
CREATE TABLE public.engine
(
    id    int4    NOT NULL DEFAULT nextval('engine_id_seq'::regclass),
    model varchar NOT NULL,
    power int4    NOT NULL,
    CONSTRAINT engine_pk PRIMARY KEY (id),
    CONSTRAINT engine_fk FOREIGN KEY (id) REFERENCES public.car (id) ON DELETE CASCADE ON UPDATE CASCADE
);

--Заполняем таблицы данными

INSERT INTO public.cat (name, color, age)
VALUES ('Квазиморда', 'Пёстрый', 11),
       ('Жульбан', 'Серый', 4),
       ('гендальф', 'Белый', 3),
       ('Гуталин', 'Чёрный', 9),
       ('васька', 'Полосатый', 12),
       ('Бомба', 'Белый', 9);

INSERT INTO public.car (firm, speed)
VALUES ('BMW', 130),
       ('Lada', 100),
       ('Ferrary', 200),
       ('reno', 133);

INSERT INTO public.engine (model, power)
VALUES ('v6_special', 220),
       ('v6', 130),
       ('v8', 450),
       ('v4_turbo', 177);

INSERT INTO public.military_formation (title)
VALUES ('36 Дорожно-мостовая бригада'),
       ('Военный клуб досуга любителей шахмат'),
       ('Сержантские курсы повышения квалификации');

INSERT INTO public.military_formation_warrior (militaryformation_id, warriors_id)
VALUES (13, 13),
       (13, 12),
       (13, 14),
       (13, 15),
       (13, 8),
       (13, 17),
       (13, 9),
       (13, 10),
       (13, 11),
       (13, 16),
       (14, 9),
       (14, 10),
       (16, 11),
       (16, 12);

INSERT INTO public.warrior ("rank", weapon_id, info_id)
VALUES ('NO_INSIGNIA', 2, 12),
       ('NO_INSIGNIA', 4, 14),
       ('PRIVATE', 5, 15),
       ('PRIVATE_1ST_CLASS', 6, 16),
       ('PRIVATE', 7, 17),
       ('PRIVATE', 8, 18),
       ('PRIVATE', 9, 19),
       ('PRIVATE', 10, 20),
       ('SERGEANT', 12, 22);

INSERT INTO public.warrior_info (firstname, lastname, age)
VALUES ('Гриша', 'Стёпочкин', 19),
       ('Дмитрий', 'Рыштаков', 20),
       ('Вечеслав', 'Капустин', 24),
       ('Евгений', 'Плысак', 21),
       ('Евгений', 'Науменко', 22),
       ('Валентин', 'Захаренко', 21),
       ('Геннадий', 'Добкин', 23),
       ('Сергей', 'Круглов', 21),
       ('Андрей', 'Рыбников', 26);

INSERT INTO public.weapon ("type", manufacturer, serial_number)
VALUES ('GUN', 'AMERICAN_OUTDOOR_BRANDS_CORPORATION', 'sTR800_12093'),
       ('SUB_MACHINE_GUN', 'HECHLER_AND_KOCH_GMBH', 'sTR803_12093'),
       ('SUB_MACHINE_GUN', 'FABRICAD_ARMI_PIETRO_BERETTA_S_P_A', 'sTR918_12061'),
       ('AUTOMATIC_RIFLE', 'FABRICAD_ARMI_PIETRO_BERETTA_S_P_A', 'sTR809_12093'),
       ('AUTOMATIC_RIFLE', 'LOCKHEED_MARTIN_CORPORATION', 'sTR800_12RTY1'),
       ('RIFLE', 'FABRICAD_ARMI_PIETRO_BERETTA_S_P_A', 'sTR670_120dy11'),
       ('MACHINE_GUN', 'THALES_GROUP', 'sTR123_147893'),
       ('AUTOMATIC_RIFLE', 'NORTHROP_GRUMMAN_CORPORATION', 'sTR234_12725'),
       ('GUN', 'HECHLER_AND_KOCH_GMBH', 'sTR567_756593'),
       ('SUB_MACHINE_GUN', 'FN_HERSTAL', 'ght0978st');