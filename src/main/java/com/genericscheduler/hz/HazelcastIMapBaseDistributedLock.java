package com.genericscheduler.hz;

import java.time.Duration;

public class HazelcastIMapBaseDistributedLock implements DistributedLock<String> {

    private final IMap

    @Override
    public boolean tryLock(String s, Duration ttl) {
        return false;
    }

    @Override
    public boolean unlock(String s) {
        return false;
    }



    /*default <R> Optional<R> tryLockAndCall(KEY key, Duration ttl, Callable<R> action, Function<Exception, R> errorHandler) {
        R result = null;

        DistributedLock<KEY> lock = getLock(key);

        if (lock.tryLock(ttl)) {
            try {
                result = action.call();
            } catch (Exception ex) {
                result = errorHandler.apply(ex);
            } finally {
                lock.unlock();
            }
        }

        return Optional.ofNullable(result);
    }*/
}
