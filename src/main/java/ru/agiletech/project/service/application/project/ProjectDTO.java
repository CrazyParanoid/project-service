package ru.agiletech.project.service.application.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectDTO extends RepresentationModel<ProjectDTO> {

    @NotEmpty(message = "Отсутствует наименование проекта")
    private String      name;

    @NotEmpty(message = "Отсутствует описание проекта")
    private String      description;

    @NotEmpty(message = "Отсутствует руководитель проекта")
    private String      lead;

    private String      key;
    private Set<String> tasks;
    private Set<String> teammates;

}
