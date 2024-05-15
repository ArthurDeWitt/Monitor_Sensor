CREATE TABLE IF NOT EXISTS sensors (
                                       id BIGINT PRIMARY KEY,
                                       name VARCHAR(30) NOT NULL,
                                       model VARCHAR(15) NOT NULL,
                                       sensor_from INT NOT NULL,
                                       sensor_to INT NOT NULL,
                                       type VARCHAR(20) NOT NULL,
                                       unit VARCHAR(10) NOT NULL,
                                       location VARCHAR(40),
                                       description VARCHAR(200)
);