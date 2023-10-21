package com.genericscheduler.hz;

import java.time.Duration;

public interface DistributedLock<KEY> {

    boolean tryLock(KEY key, Duration ttl);

    boolean unlock(KEY key);
}
