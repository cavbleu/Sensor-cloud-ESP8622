CREATE TABLE sensors (
    id serial PRIMARY KEY,
    names TEXT);

INSERT INTO sensors VALUES (1,'Cabine-1');

CREATE TABLE sen_data (
                        id serial,
                        idsen integer not null,
                        datesend text,
                        timesend text,
                        temperature NUMERIC NOT NULL,
                        humidity NUMERIC NOT NULL,
                        pressure NUMERIC NOT NULL,
                        co NUMERIC,
                        PRIMARY KEY (id),
                        CONSTRAINT authorities_idx UNIQUE (id),
                            CONSTRAINT sensors_ibfk_1
                            FOREIGN KEY (idsen)
                            REFERENCES sensors (id)
);
