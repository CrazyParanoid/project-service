CREATE TABLE PROJECTS(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  CREATE_DATE                 TIMESTAMP                 NOT NULL ,
  NAME                        TEXT                      NOT NULL ,
  DESCRIPTION                 TEXT                      NOT NULL ,
  PROJECT_KEY                 VARCHAR (50)              NOT NULL ,
  LEAD_ID                     VARCHAR (100)             NOT NULL
);

CREATE INDEX PROJECTS_ID_INDEX
  ON PROJECTS(ID);
