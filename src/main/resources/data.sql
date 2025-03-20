-- Insertar usuarios de prueba
INSERT INTO users (id, name,surname, email, password, role, created_at,updated_at,due_date) VALUES
(UUID(),'Admin', 'admin@example.com', 'sdfsdfds', 'ADMIN', '2024-03-01', '2024-03-01', '2024-03-01'),
(UUID(),'Juan','Pérez', 'juan@example.com', 'sdfsaf', 'USER', '2024-03-01', '2024-03-01', '2024-03-01'),
(UUID(),'Ana','López' 'ana@example.com', 'sadfdsafsd', 'USER', '2024-03-01', '2024-03-01', '2024-03-01');

-- Insertar tareas de prueba
INSERT INTO tasks (id, title, description, status, due_date, assigned_to, created_by) VALUES
(UUID(), 'Configurar el proyecto', 'Inicializar el proyecto con Spring Boot', 'COMPLETED', '2024-03-10', UUID(),UUID()),
(UUID(), 'Implementar autenticación', 'Agregar JWT y seguridad con Spring Security', 'IN_PROGRESS', '2024-03-15', UUID(),UUID()),
(UUID(), 'Crear la documentación API', 'Definir los endpoints en OpenAPI', 'PENDING', '2024-03-20', UUID(),UUID());