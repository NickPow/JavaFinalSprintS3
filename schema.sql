CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    address TEXT,
    role VARCHAR(10) CHECK (role IN ('Admin', 'Trainer', 'Member')) NOT NULL
);

CREATE TABLE memberships (
    membership_id SERIAL PRIMARY KEY,
    membership_type VARCHAR(50),
    membership_description TEXT,
    membership_cost DECIMAL(10,2),
    member_id INT REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE workout_classes (
    workout_class_id SERIAL PRIMARY KEY,
    workout_class_type VARCHAR(50),
    workout_class_description TEXT,
    trainer_id INT REFERENCES users(user_id) ON DELETE SET NULL
);
