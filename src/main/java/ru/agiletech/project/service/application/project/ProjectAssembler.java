package ru.agiletech.project.service.application.project;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.agiletech.project.service.domain.project.Project;
import ru.agiletech.project.service.domain.project.ProjectSnapshot;

@Component
@RequiredArgsConstructor
public class ProjectAssembler {

    private final ModelMapper modelMapper;

    ProjectDTO writeDTO(Project project){
        ProjectDTO projectDTO = modelMapper.map(project, ProjectDTO.class);

        ProjectSnapshot snapshot = project.makeSnapshot();

        projectDTO.setLead(snapshot.getLead());
        projectDTO.setKey(project.key());
        projectDTO.setTasks(snapshot.getTasks());
        projectDTO.setTeammates(snapshot.getTeammates());

        return projectDTO;
    }

}
