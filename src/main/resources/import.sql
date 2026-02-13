INSERT INTO doctors (id, nombre, apellido, especialidad, email, telefono, numOficina)
VALUES (1, 'Ana', 'Lopez', 'Cardiologia', 'ana.lopez@clinic.com', '0991111111', 'A-101');
INSERT INTO doctors (id, nombre, apellido, especialidad, email, telefono, numOficina)
VALUES (2, 'Bruno', 'Garcia', 'Pediatria', 'bruno.garcia@clinic.com', '0992222222', 'B-202');
INSERT INTO doctors (id, nombre, apellido, especialidad, email, telefono, numOficina)
VALUES (3, 'Carla', 'Mendez', 'Dermatologia', 'carla.mendez@clinic.com', '0993333333', 'C-303');

INSERT INTO patients (id, nombre, apellido, fechaNacimiento, telefono, email, direccion)
VALUES (1, 'Diego', 'Perez', '1992-05-14', '0981111111', 'diego.perez@mail.com', 'Av. 10 de Agosto 123');
INSERT INTO patients (id, nombre, apellido, fechaNacimiento, telefono, email, direccion)
VALUES (2, 'Elena', 'Vargas', '1987-11-30', '0982222222', 'elena.vargas@mail.com', 'Calle Quito 456');
INSERT INTO patients (id, nombre, apellido, fechaNacimiento, telefono, email, direccion)
VALUES (3, 'Fabian', 'Rojas', '2000-02-02', '0983333333', 'fabian.rojas@mail.com', 'Av. America 789');

INSERT INTO appointments (id, fechaCita, status, doctor_id, patient_id)
VALUES (1, TIMESTAMP '2024-03-01 09:00:00', 'ACTIVA', 1, 1);
INSERT INTO appointments (id, fechaCita, status, doctor_id, patient_id)
VALUES (2, TIMESTAMP '2024-03-01 10:30:00', 'ACTIVA', 2, 2);
INSERT INTO appointments (id, fechaCita, status, doctor_id, patient_id)
VALUES (3, TIMESTAMP '2024-03-02 08:15:00', 'INACTIVA', 3, 3);
INSERT INTO appointments (id, fechaCita, status, doctor_id, patient_id)
VALUES (4, TIMESTAMP '2024-03-03 14:00:00', 'INACTIVA', 1, 2);

ALTER SEQUENCE doctor_sequence RESTART WITH 4;
ALTER SEQUENCE patients_sequence RESTART WITH 4;
ALTER SEQUENCE appointment_sequence RESTART WITH 5;
