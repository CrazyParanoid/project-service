package ru.agiletech.project.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.agiletech.project.service.Application;
import ru.agiletech.project.service.application.project.ProjectDTO;
import ru.agiletech.project.service.domain.project.Project;
import ru.agiletech.project.service.domain.project.ProjectFactory;
import ru.agiletech.project.service.domain.task.TaskId;
import ru.agiletech.project.service.domain.teammate.TeammateId;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@Slf4j
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class TestProject {

    @Autowired
    private ProjectFactory projectFactory;

    private Project project;

    @Test
    public void testCreate(){
        this.project = createProject();

        assertNotNull(this.project.key());
        assertNotNull(this.project.lead());
    }

    @Test
    public void testAddTeammate(){
        this.project = createProject();

        TeammateId teammateId = TeammateId
                .identifyTeammateFrom(UUID.randomUUID().toString());

        project.addTeammate(teammateId);

        assertNotNull(this.project.key());
        assertNotNull(this.project.lead());
        assertNotNull(this.project.teammates());
    }

    @Test
    public void testChangeLead(){
        this.project = createProject();

        TeammateId leadId = TeammateId
                .identifyTeammateFrom(UUID.randomUUID().toString());

        project.changeLead(leadId);

        assertNotNull(this.project.key());
        assertNotNull(this.project.lead());
    }

    @Test
    public void testChangeDescription(){
        this.project = createProject();

        String description = "New description";

        project.changeDescription(description);

        assertNotNull(this.project.key());
        assertNotNull(this.project.lead());
    }

    @Test
    public void testAddTask(){
        this.project = createProject();

        TaskId taskId = TaskId
                .identifyTaskFrom(UUID.randomUUID().toString());

        project.addTask(taskId);

        assertNotNull(this.project.key());
        assertNotNull(this.project.lead());
        assertNotNull(this.project.tasks());
    }

    private Project createProject(){
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setName("SCRUM project management system");
        projectDTO.setLead(UUID.randomUUID().toString());

        return projectFactory.create(projectDTO.getName(),
                projectDTO.getDescription(),
                projectDTO.getKey(),
                projectDTO.getLead(),
                projectDTO.getTasks());
    }

}
