BEGIN;
DROP TABLE IF EXISTS resultPoint CASCADE;
CREATE TABLE resultPoint(
    id SERIAL PRIMARY KEY,
    x FLOAT NOT NULL,
    y FLOAT NOT NULL,
    r FLOAT NOT NULL,
    isHit BOOLEAN,
    lead_time BIGINT NOT NULL,
    server_time TIMESTAMP NOT NULL
);
END;