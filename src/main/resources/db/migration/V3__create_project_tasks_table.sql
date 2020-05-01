CREATE TABLE PROJECT_TASKS(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  TASK_ID                     VARCHAR (100)             NULL ,
  PROJECT_ID                  bigint                    NULL
    CONSTRAINT TASK_ID_FK REFERENCES PROJECTS
);

CREATE INDEX PROJECT_TASKS_ID_INDEX
  ON PROJECT_TASKS(ID);
