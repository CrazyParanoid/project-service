package ru.agiletech.project.service.input.ports.presentation.hateoas;

import lombok.experimental.UtilityClass;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import ru.agiletech.project.service.application.project.ProjectDTO;
import ru.agiletech.project.service.input.ports.presentation.ProjectResource;

@UtilityClass
public class LinksUtil {

    public void addLinks(ProjectDTO projectDTO){
        addSelfLink(projectDTO);
        addAllProjectsLink(projectDTO);
        addDescriptionLink(projectDTO);
        addLeadLink(projectDTO);
        addTeammatesLink(projectDTO);
    }

    private void addSelfLink(ProjectDTO projectDTO){
        projectDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjectResource.class)
               .getProject(projectDTO.getKey()))
               .withSelfRel());
    }

    private void addAllProjectsLink(ProjectDTO projectDTO){
        projectDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjectResource.class)
                .searchAll())
                .withRel("allProjects"));
    }

    private void addDescriptionLink(ProjectDTO projectDTO){
        projectDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjectResource.class)
                .changeProjectDescription(projectDTO.getKey(), null))
                .withRel("description"));
    }

    private void addLeadLink(ProjectDTO projectDTO){
        projectDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjectResource.class)
                .changeProjectLead(projectDTO.getKey(), null))
                .withRel("lead"));
    }

    private void addTeammatesLink(ProjectDTO projectDTO){
        projectDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProjectResource.class)
                .addTeammate(projectDTO.getKey(), null))
                .withRel("teammates"));
    }

}
