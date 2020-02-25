CREATE TABLE IF NOT EXISTS user (
    user_id int NOT NULL AUTO_INCREMENT,
    age int,
    user_name varchar(255),
    borrowedBooks varchar(255),
    primary key (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE IF NOT EXISTS book (
    id int NOT NULL AUTO_INCREMENT,
    title varchar(255),
    author varchar(255) ,
    user_user_id int,
    primary key (id),
    foreign key (user_user_id)
        references user(user_id)
        on delete CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;