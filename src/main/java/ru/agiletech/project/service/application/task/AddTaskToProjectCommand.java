package ru.agiletech.project.service.application.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTaskToProjectCommand implements Command{

    private String key;
    private String taskId;

}
