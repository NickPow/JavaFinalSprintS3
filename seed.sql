
INSERT INTO users (username, password_hash, email, phone_number, address, role)
VALUES 
('admin1', '$2a$10$RkOJ8BZBhNFRrA386PSPX.IzqQko4DnZ/UCN64X0XKbOrYw8bn2yu', 'admin@example.com', '1234567890', 'Admin Street', 'Admin'),
('trainer1', '$2a$10$1VRbZI2ZvzBwqU9AMgzYO.wbdHXm6fUIOD2xvYoDmTiomOrrM6Xpa', 'trainer@example.com', '1234567890', 'Trainer Street', 'Trainer'),
('member1', '$2a$10$KeYXP9gVmZu6hfVlvzdGuuRuaxyyEmWQdNFAkVRaDFOQgwwiEfbRC', 'member@example.com', '1234567890', 'Member Street', 'Member');

INSERT INTO workout_classes (workout_class_type, workout_class_description, trainer_id)
VALUES 
('Yoga', 'Morning yoga session to improve flexibility', 2),
('HIIT', 'High Intensity Interval Training for fat burning', 2),
('Strength Training', 'Focus on compound lifts and full-body strength', 2),
('Zumba', 'Dance cardio class to boost endurance', 2),
('Pilates', 'Core-focused training and posture improvement', 2);
