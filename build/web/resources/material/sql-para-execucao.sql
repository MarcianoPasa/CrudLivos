CREATE SEQUENCE sec_livroid;

ALTER SEQUENCE sec_livroid OWNER TO postgres;

CREATE TABLE public.livros
(
    id bigint NOT NULL DEFAULT nextval('sec_livroid'::regclass),
    nome character varying(100) COLLATE pg_catalog."default",
    autor character varying(100) COLLATE pg_catalog."default",
    datalancamento date,
    CONSTRAINT livros_pkey PRIMARY KEY (id)
)