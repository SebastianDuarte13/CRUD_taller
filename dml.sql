
INSERT INTO paises (codigo, nombre) VALUES 
('COL', 'Colombia');

INSERT INTO principio_activo (nombre) VALUES 
('Acetaminofén'),
('Ibuprofeno'),
('Amoxicilina'),
('Clorfenamina'),
('Dextrometorfano');

INSERT INTO modelo_administracion (descripcion) VALUES 
('Oral'),
('Intravenosa'),
('Intramuscular'),
('Tópica'),
('Inhalatoria');

INSERT INTO unidad_medida (nombre) VALUES 
('Miligramos'),
('Gramos'),
('Mililitros'),
('Unidades'),
('Tabletas');

INSERT INTO region (codigo_reg, nombre, codigo_pais) VALUES 
('01', 'Cundinamarca', 'COL'),
('02', 'Antioquia', 'COL'),
('03', 'Valle del Cauca', 'COL'),
('04', 'Santander', 'COL'),
('05', 'Bolívar', 'COL');

INSERT INTO ciudad (codigo_ciudad, nombre, cod_region) VALUES 
('001', 'Bogotá', '01'),
('002', 'Medellín', '02'),
('003', 'Cali', '03'),
('004', 'Bucaramanga', '04'),
('005', 'Cartagena', '05');

INSERT INTO clientes (id, nombres, apellidos, email, fecha_nacimiento, lon, latitud, cod_ciudad) VALUES 
('CC12345678', 'Carlos', 'Rodríguez', 'carlos.rodriguez@example.com', '1985-05-15', -74.08175, 4.60971, '001'),
('CC23456789', 'María', 'González', 'maria.gonzalez@example.com', '1990-09-23', -75.56359, 6.25184, '002'),
('CC34567890', 'Jorge', 'Pérez', 'jorge.perez@example.com', '1978-11-11', -76.52975, 3.45164, '003'),
('CC45678901', 'Luisa', 'Martínez', 'luisa.martinez@example.com', '1995-02-28', -73.1198, 7.11935, '004'),
('CC56789012', 'Ana', 'Ramírez', 'ana.ramirez@example.com', '1982-07-04', -75.56359, 10.40043, '005');

INSERT INTO farmacia (nombre, direccion, `long`, lat, cod_ciudad, logo) VALUES 
('Farmatodo Bogotá', 'Carrera 15 # 94-45, Bogotá', -74.04663, 4.6829, '001', 'farmatodo_bogota.png'),
('Drogas La Rebaja Medellín', 'Calle 50 # 51-55, Medellín', -75.5763, 6.24756, '002', 'rebaja_medellin.png'),
('Cruz Verde Cali', 'Carrera 100 # 11-42, Cali', -76.52964, 3.37356, '003', 'cruz_verde_cali.png'),
('Farmatodo Bucaramanga', 'Calle 36 # 21-35, Bucaramanga', -73.12159, 7.12539, '004', 'farmatodo_bucaramanga.png'),
('Droguería Colsubsidio Cartagena', 'Avenida San Martín # 10-11, Cartagena', -75.5518, 10.39972, '005', 'colsubsidio_cartagena.png');

INSERT INTO laboratorio (nombre, cod_ciudad) VALUES 
('Laboratorio Roche', '001'),
('Laboratorio Pfizer', '002'),
('Laboratorio Sanofi', '003'),
('Laboratorio Bayer', '004'),
('Laboratorio Tecnoquímicas', '005');

INSERT INTO medicina (proceedings, nombre, healthregister, descripcion, descripcionshort, nombrerol, cod_admin, cod_activo, cod_medida, cod_lab) VALUES 
('PROC001', 'Dolex', 'HEALTH001', 'Medicamento analgésico y antipirético.', 'Analgesico', 'Paciente', 1, 1, 1, 1),
('PROC002', 'Advil', 'HEALTH002', 'Medicamento antiinflamatorio no esteroideo (AINE).', 'Antiinflamatorio', 'Paciente', 1, 2, 1, 2),
('PROC003', 'Amoxidal', 'HEALTH003', 'Antibiótico de amplio espectro.', 'Antibiotico', 'Paciente', 1, 3, 1, 3),
('PROC004', 'Allerblock', 'HEALTH004', 'Antihistamínico para el alivio de alergias.', 'Antihistaminico', 'Paciente', 1, 4, 1, 4),
('PROC005', 'Notos', 'HEALTH005', 'Medicamento para el alivio de la tos.', 'Antitusivo', 'Paciente', 1, 5, 1, 5);

INSERT INTO farmaciamedicina (id_farmacia, id_medicina, prince) VALUES 
(1, 1, 10000),
(2, 2, 15000),
(3, 3, 20000),
(4, 4, 5000),
(5, 5, 2500);
