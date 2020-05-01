package ru.agiletech.project.service.application.task;

public interface CommandProcessor<T extends Command> {

    void process(T command);

}
