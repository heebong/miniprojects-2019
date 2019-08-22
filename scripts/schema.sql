CREATE TABLE IF NOT EXISTS video(
	id BIGINT AUTO_INCREMENT,
	youtube_id VARCHAR(255) NOT NULL,
	title VARCHAR(100) NOT NULL,
	contents TEXT NOT NULL,
	creator_id BIGINT NOT NULL,
	create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(id),
	FOREIGN KEY(creator_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS user(
	id BIGINT AUTO_INCREMENT,
	user_name VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
	create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	update_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(ID)
);
