CREATE TABLE IF NOT EXISTS users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(50) NOT NULL,
                       name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS wishlists (
                           wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id INT NOT NULL,
                           name VARCHAR(50) NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (user_id)
                               REFERENCES users(user_id)
                               ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS wishes (
                        wish_id INT AUTO_INCREMENT PRIMARY KEY,
                        wishlist_id INT NOT NULL,
                        name VARCHAR(50) NOT NULL,
                        brand VARCHAR(50),
                        description VARCHAR(255),
                        type VARCHAR(50),
                        price DECIMAL(10,2),
                        link VARCHAR(255),
                        is_favorite BOOLEAN,
                        is_reserved BOOLEAN,
                        FOREIGN KEY (wishlist_id)
                            REFERENCES wishlists(wishlist_id)
                            ON DELETE CASCADE
);
