package com.genericscheduler.event;

import java.time.Duration;

public interface DistributedEvent {

    String getLockKey();

    Duration getLockTimeout();
}
