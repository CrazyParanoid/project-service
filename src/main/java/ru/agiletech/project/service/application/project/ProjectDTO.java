package ru.agiletech.project.service.application.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Представление модели agile проекта")
public class ProjectDTO extends RepresentationModel<ProjectDTO> {

    @NotEmpty(message = "Отсутствует наименование проекта")
    @ApiModelProperty(required = true, value = "Наименование проекта")
    private String      name;

    @NotEmpty(message = "Отсутствует описание проекта")
    @ApiModelProperty(position = 1, required = true, value = "Описание проекта")
    private String      description;

    @NotEmpty(message = "Отсутствует руководитель проекта")
    @ApiModelProperty(position = 2, required = true, value = "Идентификатор руководителя проекта")
    private String      lead;

    @ApiModelProperty(position = 3, value = "Ключ проекта")
    private String      key;

    @ApiModelProperty(position = 4, value = "Задачи проекта", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Set<String> tasks;

    @ApiModelProperty(position = 5, value = "Участники проекта", accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Set<String> teammates;

}
