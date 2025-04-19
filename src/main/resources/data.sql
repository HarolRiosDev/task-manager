-- Insertar usuarios de prueba
INSERT INTO users (id, name, surname, email, password, role, created_at, updated_at, due_date) VALUES
('b482dfe2-9322-4d3d-8789-cf30e4a9203b', 'JOHN', 'TORS', 'admin@example.com', '$2a$10$oznTDaOhMgDbAVfcPBI5.uxg86KJMwX.l1C.0YQ7mKA5KbTruLWb6', 'ADMIN', '2024-03-01', '2024-03-01', '2024-03-01'),
('2b2089e5-2630-4f0c-a4f4-e534e2819b7e', 'JUAN', 'LOPEZ', 'juan@example.com', '$2a$10$O3XgW/HKUtLJoxB8u404JebSQ5DYdSXLQ3kR4gAd556XcBQpTvrNq	', 'USER', '2024-03-01', '2024-03-01', '2024-03-01'),
('3742f5f8-64db-429b-88cf-02c576ce0578', 'Ana', 'López', 'ana@example.com', '$2a$10$9KkcTTnGKfLHTVUberLyEu6cka13O2FrISfcRmNTh431nDlcvtrH2', 'USER', '2024-03-01', '2024-03-01', '2024-03-01');

-- Insertar tareas de prueba
INSERT INTO tasks (id, title, description, status, due_date, assigned_to, created_by) VALUES
('269ea5b4-fe9e-494f-8847-71d667ba6c97', 'Configurar el proyecto', 'Inicializar el proyecto con Spring Boot', 'COMPLETED', '2024-03-10', '2b2089e5-2630-4f0c-a4f4-e534e2819b7e', 'b482dfe2-9322-4d3d-8789-cf30e4a9203b'),
('45ebdd50-d53d-4d09-a4e5-61d4bd13f595', 'Implementar autenticación', 'Agregar JWT y seguridad con Spring Security', 'IN_PROGRESS', '2024-03-15', '3742f5f8-64db-429b-88cf-02c576ce0578', '2b2089e5-2630-4f0c-a4f4-e534e2819b7e'),
('6cd4343f-3b89-402b-9800-ba581d198cf4', 'Crear la documentación API', 'Definir los endpoints en OpenAPI', 'PENDING', '2024-03-20', '3742f5f8-64db-429b-88cf-02c576ce0578', 'b482dfe2-9322-4d3d-8789-cf30e4a9203b');
