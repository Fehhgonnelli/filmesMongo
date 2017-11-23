CREATE TABLE voto(
   id          INT              NOT NULL IDENTITY,
   filme_id    INT              NOT NULL,
   avaliacao   INT              NOT NULL,
   FOREIGN KEY (filme_id) REFERENCES filme(id),
   PRIMARY KEY (id)
);