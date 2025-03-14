CREATE TABLE users (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    otp VARCHAR(255) NOT NULL,
    seed VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    due_date DATE,
    CHECK (role IN ('ADMIN', 'USER')),
    CHECK (email LIKE '%@%'),
    UNIQUE (email)
);

CREATE TABLE tasks (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    due_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    assigned_to BIGINT,
    CHECK (status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED')),
    FOREIGN KEY (assigned_to) REFERENCES users(id) ON DELETE SET NULL
);
