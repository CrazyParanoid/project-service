package ru.agiletech.project.service.eventbus;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.agiletech.project.service.Application;
import ru.agiletech.project.service.application.project.ProjectDTO;
import ru.agiletech.project.service.application.project.ProjectService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@DirtiesContext
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class TestAddTaskToProjectEvent {

    private static final String PROJECT_KEY = "key";
    private static final String TASK_ID     = "taskId";

    @Autowired
    private Sink sink;

    @Autowired
    private ProjectService projectService;

    @Test
    public void testTestAddTaskToProject(){
        ProjectDTO projectDTO = new ProjectDTO();
        String leadId = UUID.randomUUID().toString();

        projectDTO.setDescription("Test description");
        projectDTO.setName("Agile Test Software");
        projectDTO.setLead(leadId);

        String taskId = UUID.randomUUID().toString();

        var createdProject = projectService.createProject(projectDTO);

        Map<String, Object> event = new HashMap<>();

        event.put(PROJECT_KEY, createdProject.getKey());
        event.put(TASK_ID, taskId);

        sink.input()
                .send(MessageBuilder
                        .withPayload(event)
                        .build());
    }

}
