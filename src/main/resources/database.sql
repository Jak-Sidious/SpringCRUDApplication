CREATE DATABASE LibraryApi;
USE LibraryAPI;
CREATE TABLE book (
    id LONG GENERATED BY DEFAULT AS IDENTITY
    title VARCHAR(200)
    author VARCHAR(100)
)