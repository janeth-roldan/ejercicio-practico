CREATE DATABASE cliente;
CREATE DATABASE cuenta;

CREATE USER devsu WITH ENCRYPTED PASSWORD 'devsu_pass';

GRANT CONNECT ON DATABASE cliente TO devsu;
GRANT CONNECT ON DATABASE cuenta TO devsu;


\c cliente;


CREATE TABLE IF NOT EXISTS public.cliente
(
    cliente_id uuid NOT NULL,
    created_at timestamp,
    updated_at timestamp,
    version bigint,
    direccion character varying(255),
    edad integer,
    genero character varying(255),
    identificacion character varying(255),
    nombre character varying(255),
    telefono character varying(255),
    contrasena character varying(255),
    estado boolean,
    CONSTRAINT cliente_pkey PRIMARY KEY (cliente_id),
    CONSTRAINT uk_identificacion UNIQUE (identificacion)
);

GRANT USAGE ON SCHEMA public TO devsu;
GRANT INSERT, UPDATE, DELETE, SELECT ON ALL TABLES IN SCHEMA public TO devsu;

\c cuenta;


CREATE TABLE IF NOT EXISTS public.cuenta
(
    id uuid NOT NULL,
    created_at timestamp,
    updated_at timestamp,
    version bigint,
    cliente_id uuid,
    estado boolean,
    numero_cuenta character varying(255),
    saldo_inicial numeric(19,2),
    tipo_cuenta character varying(255),
    CONSTRAINT cuenta_pkey PRIMARY KEY (id),
    CONSTRAINT uk_numero_cuenta UNIQUE (numero_cuenta)
);

CREATE TABLE IF NOT EXISTS public.movimiento
(
    id uuid NOT NULL,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    version bigint,
    fecha timestamp without time zone,
    saldo numeric(19,2),
    tipo_movimiento character varying(255),
    valor numeric(19,2),
    cuenta_id uuid NOT NULL,
    CONSTRAINT movimiento_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_CUENTA_MOVIMIENTO" FOREIGN KEY (cuenta_id)
        REFERENCES public.cuenta (id)
);

GRANT USAGE ON SCHEMA public TO devsu;
GRANT INSERT, UPDATE, DELETE, SELECT ON ALL TABLES IN SCHEMA public TO devsu;


