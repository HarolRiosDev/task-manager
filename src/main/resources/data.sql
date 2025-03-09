-- Insertar usuarios de prueba
INSERT INTO users (id, name, email, otp, seed, role, created_at,updated_at,due_date) VALUES
(UUID(),'Admin', 'admin@example.com', '','', 'ADMIN', '2024-03-01', '2024-03-01', '2024-03-01'),
(UUID(),'Juan Pérez', 'juan@example.com', '','', 'USER', '2024-03-01', '2024-03-01', '2024-03-01'),
(UUID(),'Ana López', 'ana@example.com', '','', 'USER', '2024-03-01', '2024-03-01', '2024-03-01');

-- Insertar tareas de prueba
INSERT INTO tasks (id, title, description, status, due_date, assigned_to) VALUES
(UUID(), 'Configurar el proyecto', 'Inicializar el proyecto con Spring Boot', 'COMPLETED', '2024-03-10', 1),
(UUID(), 'Implementar autenticación', 'Agregar JWT y seguridad con Spring Security', 'IN_PROGRESS', '2024-03-15', 2),
(UUID(), 'Crear la documentación API', 'Definir los endpoints en OpenAPI', 'PENDING', '2024-03-20', 3);