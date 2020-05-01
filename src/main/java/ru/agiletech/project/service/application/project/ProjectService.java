package ru.agiletech.project.service.application.project;

import java.util.Set;

public interface ProjectService {

    ProjectDTO createProject(ProjectDTO projectDTO);

    ProjectDTO searchProject(String rawKey);

    void changeProjectDescription(String rawKey,
                                  String description);

    void changeProjectLead(String rawKey,
                           String rawLeadId);

    void addTeammateToProject(String rawKey,
                              String rawTeammateId);

    Set<ProjectDTO> searchAllProjects();

    void deleteProject(String rawKey);

}
