DROP TABLE FILE_INFO IF EXISTS;

CREATE TABLE FILE_INFO  (
    file_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    file_name VARCHAR(128),
    file_path VARCHAR(1024),
    file_created DATE,
    file_created_by VARCHAR(128),
    file_last_modified DATE,
    file_md5 BINARY(16)
);