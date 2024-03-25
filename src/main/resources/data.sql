CREATE TABLE IF NOT EXISTS users (
    user_id serial PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    login VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS posts (
    post_id serial PRIMARY KEY,
    user_id INTEGER REFERENCES users(user_id),
    title VARCHAR(50) NOT NULL,
    content TEXT
);

INSERT INTO users (first_name, last_name, login, password)
VALUES
    ('Billy', 'Herrington', 'billyherrington', 'Iamthebest'),
    ('Van', 'Darkholm', 'vandarkholm', 'Dungeonmaster'),
    ('Georgy', 'Gamidi', 'georgasm', 'Bossofthisgym');

INSERT INTO posts (user_id, title, content)
VALUES
    (1, 'About GYM', 'Come here and fight with me'),
    (2, 'About dungeon', 'Invite you to my dungeon, sure you will like it'),
    (3, 'About hospital', 'If you are sick, come here, i will cure you'),
    (3, 'About secret', 'We all have some secrets');
