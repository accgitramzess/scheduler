package com.genericscheduler.event;

import java.time.Instant;
import java.util.Optional;

import org.springframework.context.ApplicationEvent;

public interface EventFactory<E extends ApplicationEvent> {

    Optional<E> createEventAtIfAny(Instant instant);
}
