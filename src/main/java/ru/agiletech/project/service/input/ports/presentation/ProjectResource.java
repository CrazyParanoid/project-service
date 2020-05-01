package ru.agiletech.project.service.input.ports.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.agiletech.project.service.application.project.ProjectDTO;
import ru.agiletech.project.service.application.project.ProjectService;
import ru.agiletech.project.service.input.ports.presentation.hateoas.LinksUtil;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectResource {

    private final ProjectService projectService;

    @PostMapping(value = "/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO createProject(@Valid @RequestBody ProjectDTO projectDTO){
        var createdProject = projectService.createProject(projectDTO);
        LinksUtil.addLinks(createdProject);

        return createdProject;
    }

    @GetMapping(value = "/projects/{key}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO getProject(@PathVariable String key){
        var project = projectService.searchProject(key);
        LinksUtil.addLinks(project);

        return project;
    }

    @PutMapping(value = "/projects/{key}/lead")
    public ResponseEntity<Void> changeProjectLead(@PathVariable String key,
                                                  @RequestParam String leadId){
        projectService.changeProjectLead(key,
                leadId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/projects/{key}")
    public ResponseEntity<Void> changeProjectDescription(@PathVariable String key,
                                                         @RequestParam String description){
        projectService.changeProjectDescription(key,
                description);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/projects/{key}/teammates")
    public ResponseEntity<Void> addTeammate(@PathVariable String key,
                                            @RequestParam String teammateId){
        projectService.addTeammateToProject(key,
                teammateId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/projects/{key}")
    public ResponseEntity<Void> delete(@PathVariable String key){
        projectService.deleteProject(key);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/projects")
    @ResponseStatus(HttpStatus.OK)
    public Set<ProjectDTO> searchAll(){
        Set<ProjectDTO> projects = projectService.searchAllProjects();

        if(CollectionUtils.isNotEmpty(projects))
            projects.forEach(LinksUtil::addLinks);

        return projects;
    }

}
