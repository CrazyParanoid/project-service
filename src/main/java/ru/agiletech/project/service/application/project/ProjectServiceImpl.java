package ru.agiletech.project.service.application.project;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.agiletech.project.service.domain.project.Key;
import ru.agiletech.project.service.domain.project.Project;
import ru.agiletech.project.service.domain.project.ProjectFactory;
import ru.agiletech.project.service.domain.project.ProjectRepository;
import ru.agiletech.project.service.domain.teammate.TeammateId;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectFactory    projectFactory;
    private final ProjectRepository projectRepository;
    private final ProjectAssembler  projectAssembler;

    @Override
    @Transactional
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        log.info("Create project");

        String rawKey = projectDTO.getKey();

        Project project = projectFactory.create(projectDTO.getName(),
                projectDTO.getDescription(),
                rawKey,
                projectDTO.getLead(),
                projectDTO.getTasks());

        String key = project.key();
        log.info("Project with key {} has been created", key);

        projectRepository.save(project);
        log.info("Project with key {} has been saved", key);

        return projectAssembler.writeDTO(project);
    }

    @Override
    public ProjectDTO searchProject(String rawKey) {
        log.info("Search project with key {}", rawKey);

        Key key = Key.createFromKeyValue(rawKey);

        Project project = projectRepository.projectOfKey(key);
        log.info("Project with key {} has been found", rawKey);

        return projectAssembler.writeDTO(project);
    }

    @Override
    public void changeProjectDescription(String rawKey,
                                         String description) {
        log.info("Change description for project with key {}", rawKey);

        Key key = Key.createFromKeyValue(rawKey);

        Project project = projectRepository.projectOfKey(key);
        log.info("Project with key {} has been found", rawKey);

        project.changeDescription(description);

        projectRepository.save(project);
        log.info("Project with key {} has been saved", key);
    }

    @Override
    public void changeProjectLead(String rawKey,
                                  String rawLeadId) {
        log.info("Change lead for project with key {}", rawKey);

        Key key = Key.createFromKeyValue(rawKey);

        Project project = projectRepository.projectOfKey(key);
        log.info("Project with key {} has been found", rawKey);

        TeammateId leadId = TeammateId.identifyTeammateFrom(rawLeadId);
        project.changeLead(leadId);

        projectRepository.save(project);
        log.info("Project with key {} has been saved", key);
    }

    @Override
    public void addTeammateToProject(String rawKey,
                                     String rawTeammateId) {
        log.info("Add teammate with id {} to project with key {}",
                rawTeammateId, rawKey);

        Key key = Key.createFromKeyValue(rawKey);

        Project project = projectRepository.projectOfKey(key);
        log.info("Project with key {} has been found", rawKey);

        TeammateId teammateId = TeammateId.identifyTeammateFrom(rawTeammateId);
        project.addTeammate(teammateId);

        projectRepository.save(project);
        log.info("Project with key {} has been saved", key);
    }

    @Override
    public Set<ProjectDTO> searchAllProjects() {
        log.info("Search all created projects");

        Set<Project> projects = projectRepository.allProjects();
        log.info("All created projects have been found");

        Set<ProjectDTO> projectDTOS = new HashSet<>();

        if(CollectionUtils.isNotEmpty(projects))
            projects.forEach(project -> projectDTOS.add(projectAssembler.writeDTO(project)));

        return projectDTOS;
    }

    @Override
    @Transactional
    public void deleteProject(String rawKey) {
        log.info("Delete project with key {}", rawKey);

        Key key = Key.createFromKeyValue(rawKey);
        Project project = projectRepository.projectOfKey(key);

        projectRepository.deleteProject(project);
        log.info("Project with key {} has been deleted", rawKey);
    }

}
