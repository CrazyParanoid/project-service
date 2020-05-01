CREATE TABLE PROJECT_TEAMMATES  (
  ID                          bigint PRIMARY KEY        NULL ,
  TEAMMATE_ID                 VARCHAR (100)             NULL ,
  PROJECT_ID                  bigint                    NULL ,
  FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS
);

CREATE INDEX PROJECT_TEAMMATES_ID_INDEX
  ON PROJECT_TEAMMATES(ID);
