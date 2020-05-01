CREATE TABLE PROJECT_TASKS(
  ID                          bigserial PRIMARY KEY     NULL ,
  TASK_ID                     VARCHAR (100)             NULL ,
  PROJECT_ID                  bigint                    NULL ,
  FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS
);

CREATE INDEX PROJECT_TASKS_ID_INDEX
  ON PROJECT_TASKS(ID);
