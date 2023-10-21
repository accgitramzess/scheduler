package com.genericscheduler.hz;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DistributedEnvironmentStateChangedEvent extends ApplicationEvent {

    private final boolean safe;

    public DistributedEnvironmentStateChangedEvent(String impl, boolean safe) {
        super("distributed.env." + impl);
        this.safe = safe;
    }
}
