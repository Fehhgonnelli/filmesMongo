CREATE TABLE diretor(
   id          INT              NOT NULL IDENTITY,
   nome        VARCHAR (250)    NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE ator(
   id          INT              NOT NULL IDENTITY,
   nome        VARCHAR (250)    NOT NULL,
   sexo        CHAR(1)              ,
   PRIMARY KEY (id)
);

CREATE TABLE filme(
   id          INT              NOT NULL IDENTITY,
   nome        VARCHAR (250)    NOT NULL,
   ano         INT              ,
   diretor_id  INT              NOT NULL,
   FOREIGN KEY (diretor_id) REFERENCES diretor(id),
   PRIMARY KEY (id)
);

CREATE TABLE filme_ator(
   filme_id          INT              NOT NULL,
   ator_id           INT              NOT NULL,
   FOREIGN KEY (filme_id) REFERENCES filme(id),
   FOREIGN KEY (ator_id)  REFERENCES ator(id),
   PRIMARY KEY (filme_id, ator_id)
);

