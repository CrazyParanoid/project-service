package ru.agiletech.project.service.application.project;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.agiletech.project.service.domain.project.Project;

@Component
@RequiredArgsConstructor
public class ProjectAssembler {

    private final ModelMapper modelMapper;

    ProjectDTO writeDTO(Project project){
        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);

        projectDTO.setLead(project.lead());
        projectDTO.setKey(project.key());
        projectDTO.setTasks(project.tasks());
        projectDTO.setTeammates(project.teammates());

        return projectDTO;
    }

}
