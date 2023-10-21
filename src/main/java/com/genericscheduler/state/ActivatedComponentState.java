package com.genericscheduler.state;

import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActivatedComponentState implements ActivatedComponent {

    private final Supplier<Boolean> activity;

    private final Runnable activationAction;

    private final Runnable deactivationAction;

    @Override
    public boolean isActive() {
        return activity.get();
    }

    @Override
    public boolean toggleActivity(boolean active) {
        boolean needChanged = isActive() ^ active;
        if(needChanged) {
            if(active) {
                activationAction.run();
            } else {
                deactivationAction.run();
            }
        }

        return needChanged;
    }
}
