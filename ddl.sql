DROP DATABASE crud;

CREATE DATABASE crud;

USE crud;

CREATE TABLE paises (
    codigo VARCHAR(5) NOT NULL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE principio_activo (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(60)
);

CREATE TABLE modelo_administracion (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(60)
);

CREATE TABLE unidad_medida (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(60)
);

CREATE TABLE region  (
    codigo_reg VARCHAR(5) PRIMARY KEY,
    nombre VARCHAR(50),
    codigo_pais VARCHAR(5),
    FOREIGN KEY (codigo_pais) REFERENCES paises (codigo) 
);

CREATE TABLE ciudad (
    codigo_ciudad VARCHAR(5) PRIMARY KEY,
    nombre VARCHAR(50),
    cod_region VARCHAR(5),
    FOREIGN KEY (cod_region) REFERENCES region (codigo_reg)
);

CREATE TABLE clientes (
    id VARCHAR(20) PRIMARY KEY,
    nombres VARCHAR(50),
    apellidos VARCHAR(50),
    email VARCHAR(100),
    fecha_nacimiento DATE,
    lon FLOAT,
    latitud FLOAT,
    cod_ciudad VARCHAR(5),
    FOREIGN KEY (cod_ciudad) REFERENCES ciudad (codigo_ciudad)
);

CREATE TABLE farmacia (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(60),
    direccion VARCHAR(100),
    `long` FLOAT,
    lat FLOAT,
    cod_ciudad VARCHAR(5),
    logo VARCHAR(50),
    FOREIGN KEY (cod_ciudad) REFERENCES ciudad (codigo_ciudad)
);

CREATE TABLE laboratorio (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50),
    cod_ciudad VARCHAR(5),
    FOREIGN KEY (cod_ciudad) REFERENCES ciudad (codigo_ciudad)
);

CREATE TABLE medicina (
    id SERIAL PRIMARY KEY,
    proceedings VARCHAR(10),
    nombre VARCHAR(100),
    healthregister VARCHAR(50),
    descripcion TEXT,
    descripcionshort VARCHAR(60),
    nombrerol VARCHAR(100),
    cod_admin BIGINT UNSIGNED,
    cod_activo BIGINT UNSIGNED,
    cod_medida BIGINT UNSIGNED,
    cod_lab BIGINT UNSIGNED,
    FOREIGN KEY (cod_admin) REFERENCES modelo_administracion (id),
    FOREIGN KEY (cod_activo) REFERENCES principio_activo (id),
    FOREIGN KEY (cod_medida) REFERENCES unidad_medida (id),
    FOREIGN KEY (cod_lab) REFERENCES laboratorio (id)
);

CREATE TABLE farmaciamedicina (
    id_farmacia BIGINT UNSIGNED,
    id_medicina BIGINT UNSIGNED,
    prince FLOAT,
    FOREIGN KEY (id_farmacia) REFERENCES farmacia (id),
    FOREIGN KEY (id_medicina) REFERENCES medicina (id)
);