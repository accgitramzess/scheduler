package com.genericscheduler.state;

import java.util.concurrent.atomic.AtomicBoolean;

public interface ActivatedComponent {

    boolean isActive();

    boolean toggleActivity(boolean value);

    default void activate() {toggleActivity(true);}

    default void deactivate() {toggleActivity(false);}

    static ActivatedComponent flagBase(boolean initialValue) {
        AtomicBoolean flag = new AtomicBoolean(initialValue);

        return new ActivatedComponentState(
                flag::get,
                () -> flag.set(true),
                () -> flag.set(false)
        );
    }
}
