use scheduler;

CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          contents TEXT,
                          name VARCHAR(100) NOT NULL,
                          password VARCHAR(100) NOT NULL,
                          created_at DATETIME NOT NULL,
                          modified_at DATETIME NOT NULL
);

CREATE TABLE userV2 (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      created_at DATETIME NOT NULL,
                      modified_at DATETIME NOT NULL
);

CREATE TABLE scheduleV2 (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          contents TEXT,
                          name VARCHAR(100) NOT NULL,
                          password VARCHAR(100) NOT NULL,
                          created_at DATETIME NOT NULL,
                          modified_at DATETIME NOT NULL,
                          user_id BIGINT,
                          FOREIGN KEY (user_id) REFERENCES userV2(id) ON DELETE CASCADE
);
