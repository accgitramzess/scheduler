package com.genericscheduler.listener;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.genericscheduler.hz.DistributedEnvironmentStateChangedEvent;
import com.genericscheduler.state.DistributedComponent;

@Component
@RequiredArgsConstructor
public class DistributedEnvironmentStateChangedEventListener {

    private final List<DistributedComponent> distributedComponents;
    @EventListener
    public void onDistributedEnvironmentStateChanged(DistributedEnvironmentStateChangedEvent event) {
        distributedComponents.forEach(distributedComponent -> distributedComponent.toggleActivity(event.isSafe()));
    }
}
