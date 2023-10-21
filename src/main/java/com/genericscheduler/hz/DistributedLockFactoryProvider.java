package com.genericscheduler.hz;

import java.util.Map;

public class DistributedLockFactoryProvider {

    private Map<DistributedLockType, DistributedLockFactory> factories;

    public DistributedLockFactory getDistributedLockFactory(DistributedLockType type) {
        switch (type) {
            case HAZELCAST:
                return null;
            case REDIS:
                return null;
            default:
                throw new RuntimeException("Not supported DistributedLockType ["+type+"]");
        }
    }
}
