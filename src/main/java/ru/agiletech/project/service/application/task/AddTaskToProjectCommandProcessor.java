package ru.agiletech.project.service.application.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agiletech.project.service.domain.project.Key;
import ru.agiletech.project.service.domain.project.Project;
import ru.agiletech.project.service.domain.project.ProjectRepository;
import ru.agiletech.project.service.domain.task.TaskId;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddTaskToProjectCommandProcessor
        implements CommandProcessor<AddTaskToProjectCommand>{

    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public void process(AddTaskToProjectCommand command) {
        log.info("Add task with id {} to project with key {}",
                command.getTaskId(), command.getKey());

        TaskId taskId = TaskId.identifyTaskFrom(command.getTaskId());
        Key key = Key.createFromKeyValue(command.getKey());

        Project project = projectRepository.projectOfKey(key);

        project.addTask(taskId);
        log.info("Task with id {} has been added to project with key {}",
                command.getTaskId(), command.getKey());

        projectRepository.save(project);
        log.info("Project with key {} has been saved", project.key());
    }

}
