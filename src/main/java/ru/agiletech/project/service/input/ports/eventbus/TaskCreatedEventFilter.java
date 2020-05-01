package ru.agiletech.project.service.input.ports.eventbus;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;
import ru.agiletech.project.service.application.task.AddTaskToProjectCommand;
import ru.agiletech.project.service.application.task.CommandProcessor;

import javax.validation.Valid;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TaskCreatedEventFilter implements PipeFilter<Map<String, Object>>{

    private static final String PROJECT_KEY = "key";
    private static final String TASK_ID     = "taskId";

    private final CommandProcessor<AddTaskToProjectCommand> addTaskToProjectCommandProcessor;

    @Override
    @StreamListener(Sink.INPUT)
    public void onEvent(@Valid Map<String, Object> serializedEvent) {
        String rawProjectKey = (String) serializedEvent.get(PROJECT_KEY);
        String rawTaskId = (String) serializedEvent.get(TASK_ID);

        var command = new AddTaskToProjectCommand(rawProjectKey, rawTaskId);

        addTaskToProjectCommandProcessor.process(command);
    }

}
