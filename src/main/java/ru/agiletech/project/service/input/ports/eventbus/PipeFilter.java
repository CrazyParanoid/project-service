package ru.agiletech.project.service.input.ports.eventbus;

public interface PipeFilter<T> {

    void onEvent(T serializedEvent);

}
