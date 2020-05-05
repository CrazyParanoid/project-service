package ru.agiletech.project.service.input.ports.eventbus;

public interface EventSubscriber<T> {

    void onEvent(T serializedEvent);

}
