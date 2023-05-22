# Prueba Backend Nexos 'CrediBanco'
En este repositorio están alojado el codigo fuente de la solución para la prueba tecnica para el desarrollo backend para el empresa Nexos Software, junto con los archivos necesarios para su despliegue y uso.

Esta solucion se desarrollo de manera reactiva con base a flujo de datos Spring Webflux, usando el patron de diseño Observer bajo una arquitectura limpia hexagonal



## Creación de la base de datos en PostgreSQL y sus tablas

### Primer Script:  

CREATE DATABASE credibanco WITH
OWNER = postgres
ENCODING = 'UTF8'
CONNECTION LIMIT = -1
IS_TEMPLATE = False;

### Segundo Script:

CREATE SCHEMA credibanco_schema
AUTHORIZATION postgres;

## Creacion de tablas:

### Clients:

CREATE TABLE IF NOT EXISTS credibanco_schema.clients
(
client_id character varying COLLATE pg_catalog."default" NOT NULL,
full_name character varying COLLATE pg_catalog."default",
CONSTRAINT clients_pkey PRIMARY KEY (client_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS credibanco_schema.clients
OWNER to postgres;

### Products:


CREATE TABLE IF NOT EXISTS credibanco_schema.products
(
product_id integer NOT NULL,
document_client character varying COLLATE pg_catalog."default",
card_id character varying COLLATE pg_catalog."default",
CONSTRAINT products_pkey PRIMARY KEY (product_id),
CONSTRAINT card_unique UNIQUE (card_id)
INCLUDE(card_id),
CONSTRAINT fk_product_card FOREIGN KEY (card_id)
REFERENCES credibanco_schema.cards (card_id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION,
CONSTRAINT fk_product_client FOREIGN KEY (document_client)
REFERENCES credibanco_schema.clients (client_id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS credibanco_schema.products
OWNER to postgres;

### Cards:

CREATE TABLE IF NOT EXISTS credibanco_schema.cards
(
card_id character varying COLLATE pg_catalog."default" NOT NULL,
type_of_card character varying(10) COLLATE pg_catalog."default",
titular_name character varying(100) COLLATE pg_catalog."default",
is_activated boolean,
is_blocked boolean,
balance integer,
product_id integer,
expiration_date date,
CONSTRAINT cards_pkey PRIMARY KEY (card_id),
CONSTRAINT product_unique UNIQUE (product_id),
CONSTRAINT fk_card_product FOREIGN KEY (product_id)
REFERENCES credibanco_schema.products (product_id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS credibanco_schema.cards
OWNER to postgres;

### Transactions:

CREATE TABLE IF NOT EXISTS credibanco_schema.transactions
(
transaction_id integer NOT NULL,
card_id character varying COLLATE pg_catalog."default",
transaction_date date,
is_valid boolean,
price integer,
CONSTRAINT transactions_pkey PRIMARY KEY (transaction_id),
CONSTRAINT fk_transaction_card FOREIGN KEY (card_id)
REFERENCES credibanco_schema.cards (card_id) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS credibanco_schema.transactions
OWNER to postgres;

**Esta base de datos se probó de manera local, conectantose a jdbc:postgresql://localhost:5432/credibanco?currentSchema=credibanco_schema**


**Esta configuracion esta establecida en el archivo 'application.yaml'**

## Para desplegar el servicio corra la MainApplication después de haber levantado la base de datos local 

# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**
