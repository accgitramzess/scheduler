package com.genericscheduler.hz;

public interface DistributedLockFactory {

    DistributedLock get(DistributedLockType provider);
}
