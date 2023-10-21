package com.genericscheduler.event;

import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationEvent;

import com.genericscheduler.hz.DistributedLock;
import com.genericscheduler.state.ActivatedComponent;
import com.genericscheduler.state.DistributedComponent;

@RequiredArgsConstructor
public class DistributedEventFilter implements EventFilter, DistributedComponent {

    private final ActivatedComponent activity = ActivatedComponent.flagBase(false);
    private final DistributedLock.Factory<String> lockFactory;

    @Override
    public Set<ApplicationEvent> filter(Set<ApplicationEvent> events) {
        return events.stream().filter(event -> {
            if(event instanceof DistributedEvent) {
                return isActive() && tryLock((DistributedEvent) event);
            }

            return true;
        }).collect(Collectors.toSet());
    }

    private boolean tryLock(DistributedEvent event) {
        String key = event.getLockKey();
        Duration ttl = event.getLockTimeout();

        return lockFactory.getLock(key).tryLock(ttl);
    }

    @Override
    public boolean isActive() {
        return activity.isActive();
    }

    @Override
    public boolean toggleActivity(boolean value) {
        boolean toggled = activity.toggleActivity(value);
        if (toggled) {
            boolean active = activity.isActive();
            System.out.println(active);
        }
        return toggled;
    }
}
